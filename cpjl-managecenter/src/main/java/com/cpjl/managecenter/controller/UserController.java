package com.cpjl.managecenter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cpjl.managecenter.dto.UserDto;
import com.cpjl.managecenter.entity.User;
import com.cpjl.managecenter.model.BaseResult;
import com.cpjl.managecenter.model.DataGrid;
import com.cpjl.managecenter.model.Page;
import com.cpjl.managecenter.service.UserService;
import com.cpjl.managecenter.util.PropertiesUtil;
import com.cpjl.managecenter.util.ValidUtil;

@Controller
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {
	@Autowired
	private UserService userService;

	private String md5key = PropertiesUtil.getProperty("app.user.md5key");;

	private String ftpDomain = PropertiesUtil.getProperty("ftp.domain");;

	/*
	 * 列表页
	 * 
	 * @param UserDto page.currentPage 当前页码 wd 搜索关键词
	 * 
	 * @param Model
	 * 
	 * @param String cName 显示的栏目名
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(UserDto entity, Model model, String cName) {
		Page page = entity.getPage();
		// page.setPageSize(1);
		DataGrid<User> dataGrid = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		params.put("page", page);
		// 分页查询
		dataGrid = userService.paging(params);
		page.setRecordCount(dataGrid.getNumRows());
		// 用于分页点击的url
		String url = "?page.currentPage=$currentPage" //
				+ "&cName=" + cName //
				+ "&wd=" + (entity.getWd() == null ? "" : entity.getWd());
		page.setUrl(url);

		model.addAttribute("entity", entity);
		model.addAttribute("cName", cName);
		model.addAttribute("list", dataGrid.getItems());
		model.addAttribute("page", page);
		model.addAttribute("ftpDomain", ftpDomain);
		return "user/list";
	}

	/*
	 * 添加页面
	 * 
	 * @param Model model
	 * 
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Model model) {
		model.addAttribute("ftpDomain", ftpDomain);
		return "user/insert";
	}

	/*
	 * 添加do
	 * 
	 * @param User entity avartar 头像 userName 用户名 realName 真实姓名 mobile 手机号
	 * position职位 isAble 是否启用用户
	 * 
	 * @param BindingResult 绑定验证结果
	 * 
	 * @param Model
	 * 
	 * @param RedirectAttributes 重定向jsp参数
	 * 
	 * 
	 */
	@RequestMapping(value = "/insertDo", method = RequestMethod.POST)
	public String insertDo(@Valid User entity, BindingResult bindingResult, Model model, RedirectAttributes attr) {
		// 1.基本数据校验
		Map<String, String> map = ValidUtil.error2map(bindingResult);
		if (map.size() > 0) {
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "user/insert";
		}
		// 2.校验用户名是否已存在
		User user = userService.getByUserName(entity.getUserName());
		if (user != null) {
			map.put("user", "用户名已存在");
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "user/insert";
		}

		// 写入数据库
		userService.insert(entity);
		attr.addAttribute("id", entity.getId());
		return "redirect:/message/success.jsp";
	}

	/*
	 * 修改页面
	 * 
	 * @param Long id
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Integer id, Model model) {
		User user = userService.get(id);
		model.addAttribute("entity", user);
		model.addAttribute("ftpDomain", ftpDomain);
		return "user/update";
	}

	/*
	 * 修改do
	 * 
	 * @param user id 编号 img 图片 title 标题 imgOrder 排序
	 * 
	 * @param BindingResult 绑定验证结果
	 * 
	 * @param Model
	 * 
	 * @param RedirectAttributes 重定向jsp参数
	 * 
	 */
	@RequestMapping(value = "/updateDo", method = RequestMethod.POST)
	public String updateDo(@Valid User entity, BindingResult bindingResult, Model model, RedirectAttributes attr) {
		// 数据校验
		Map<String, String> map = ValidUtil.error2map(bindingResult, entity.getId());
		if (map.size() > 0) {
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "user/update";
		}

		// 2.校验用户名是否已存在
		User user = userService.getByUserName(entity.getUserName());
		if (user != null && !user.getId().equals(entity.getId())) {
			map.put("user", "用户名已存在");
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "user/update";
		}

		// 写入数据库
		userService.update(entity);
		attr.addAttribute("id", entity.getId());
		return "redirect:/message/success.jsp";
	}

	/*
	 * 单个删除do
	 * 
	 * @param Long id 编号
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteDo", method = RequestMethod.GET)
	public Object deleteDo(Long id) {
		BaseResult<User> baseResult = new BaseResult<User>();

		userService.delete(id);
		baseResult.setResult(true);
		baseResult.setMessage("删除成功" + id.toString());
		return baseResult;
	}

	/*
	 * 批量删除do
	 * 
	 * @param Long[] id 编号数组
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteBatchDo", method = RequestMethod.GET)
	public Object deleteBatchDo(Long[] id) {
		BaseResult<User> baseResult = new BaseResult<User>();
		userService.deleteBatchDo(id);
		baseResult.setResult(true);
		baseResult.setMessage("删除成功" + id.toString());
		return baseResult;
	}

	/*
	 * 重置密码页面
	 * 
	 * @param user id 编号
	 * 
	 * @param Model
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword(User entity, Model model) {
		// 校验用户名是否已存在
		User user = userService.get(entity.getId());
		if (user == null) {
			throw new IllegalArgumentException("用户不存在");
		}

		model.addAttribute("entity", user);
		model.addAttribute("ftpDomain", ftpDomain);
		return "user/resetPassword";
	}

	/*
	 * 重置密码do
	 * 
	 * @param User entity id 用户编号 password 新密码
	 * 
	 * @param origPassword 原密码
	 * 
	 * @param BindingResult 绑定验证结果
	 * 
	 * @param Model
	 * 
	 * @param RedirectAttributes 重定向jsp参数
	 * 
	 * 
	 */
	@RequestMapping(value = "/resetPasswordDo", method = RequestMethod.POST)
	public String resetPasswordDo(User entity, String origPassword, Model model, RedirectAttributes attr) {
		// 1.基本数据校验
		Map<String, String> map = new HashMap<String, String>();
		if (entity.getId() == null) {
			map.put("id", "id不能为空");
		}
		if (StringUtils.isBlank(origPassword)) {
			map.put("origPassword", "请填写原密码");
		}
		if (StringUtils.isBlank(entity.getPassword())) {
			map.put("password", "请填写新密码");
		} else if (entity.getPassword().length() < 8) {
			map.put("password", "新密码长度不能少于8位");
		}

		if (map.size() > 0) {
			model.addAttribute("entity", entity);
			model.addAttribute("origPassword", origPassword);
			model.addAttribute("errors", map);
			return "user/resetPassword";
		}

		// 2.校验用户名是否已存在

		String md5OrigPassword = DigestUtils.md5Hex(md5key + origPassword);
		User user = userService.get(entity.getId());
		if (user == null) {
			map.put("user", "用户不存在");
			model.addAttribute("entity", entity);
			model.addAttribute("origPassword", origPassword);
			model.addAttribute("errors", map);
			return "user/resetPassword";
		} else if (!user.getPassword().equals(md5OrigPassword)) {
			map.put("origPassword", "原密码错误");
			model.addAttribute("entity", entity);
			model.addAttribute("origPassword", origPassword);
			model.addAttribute("errors", map);
			return "user/resetPassword";
		}

		// 写入数据库
		String md5Password = DigestUtils.md5Hex(md5key + entity.getPassword());
		entity.setPassword(md5Password);
		userService.update(entity);
		attr.addAttribute("id", entity.getId());
		return "redirect:/message/success.jsp";
	}
}
