package com.cpjl.managecenter.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpjl.managecenter.dto.LoginUserDto;
import com.cpjl.managecenter.entity.User;
import com.cpjl.managecenter.service.UserService;
import com.cpjl.managecenter.util.ImageCode;
import com.cpjl.managecenter.util.PropertiesUtil;
import com.cpjl.managecenter.util.ValidUtil;
 

 

@Controller
@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
public class LoginController {
	@Autowired
	private UserService userService;
	 
	private String md5key = PropertiesUtil.getProperty("app.user.md5key");

	/**
	 * 登录页
	 */
	@RequestMapping(value = "/index" )
	public Object index() {
		return "login/index";
	}

	

	/**
	 * 获取图片验证码
	 * 
	 * @throws IOException
	 */
	@GetMapping(value = "/imageCode" )
	public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		response.setContentType("image/png");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		ImageCode imageCode = new ImageCode(100, 30, 4, 10);
		session.setAttribute("code", imageCode.getCode());
		imageCode.write(response.getOutputStream());

	}

	/**
	 * 验证登录 
	 */
	@PostMapping(value = "/verify" )
	public Object verify(@Valid LoginUserDto entity, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
 
		// 1.基本数据校验
		Map<String, String> map = ValidUtil.error2map(bindingResult);
		if (map.size() > 0) {
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "login/index";
		}
		//2、验证码是否正确
		HttpSession session = request.getSession();
		Object sessionCode = session.getAttribute("code");
		if (sessionCode == null || !sessionCode.equals(entity.getCode())) {
			map.put("code", "验证码错误");
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "login/index";
		} else {
			session.removeAttribute("user");
		}

		User user = userService.getByUserName(entity.getUserName());
		 
		String md5Password = DigestUtils.md5Hex(md5key + entity.getPassword());

		if (user == null) {
			//3.用户名不存在
			map.put("user", "用户名或密码错误");
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "login/index";
		} else if (!user.getIsAble()) {
			//3.用户名被锁定
			map.put("user", "该用户被锁定");
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "login/index";
		} else if (user.getPasswordErrorTimes() > 100) {
			//4.密码错误超过100次			 
			map.put("user", "密码错误超过100次，请联系管理员");
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "login/index";
		} else if (!user.getPassword().equals(md5Password)) {
			//5.密码错误
			userService.updatePasswordErrorTimes(user);
			map.put("user", "密码错误，还有" + (100 - user.getPasswordErrorTimes()) + "次机会");
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "login/index";
		} else {
			//6.登录成功
			userService.updateUserWhenLoginSuccess(user);
			session.setAttribute("user", user);
			return "redirect:/home/index";
		}

	}
	/**
	 * 登出页
	 */
	@GetMapping(value = "/logout" )
	public Object logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "redirect:/login/index";
	}

}
