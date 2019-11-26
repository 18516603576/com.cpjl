package com.cpjl.managecenter.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.cpjl.managecenter.dto.ArticlecatDto;
import com.cpjl.managecenter.entity.Articlecat;
import com.cpjl.managecenter.model.BaseResult;
import com.cpjl.managecenter.service.ArticlecatService;
import com.cpjl.managecenter.util.ValidUtil;

@Controller
@RequestMapping(value = "/articlecat", produces = "application/json;charset=UTF-8")
public class ArticlecatController {
	@Resource
	private ArticlecatService articlecatService;

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
	public String list(Integer deviceid, Model model, String cName) {

		// 分页查询
		List<ArticlecatDto> list = articlecatService.findAll();

		model.addAttribute("list", list);
		model.addAttribute("cName", cName);
		return "articlecat/list";
	}

	/*
	 * 添加页面
	 * 
	 * @param Articlecat entity
	 * 
	 * Integer deviceid 设备id
	 * 
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Articlecat entity, Model model) {
		List<ArticlecatDto> articlecatList = articlecatService.findFirstLevel();

		model.addAttribute("articlecatList", JSON.toJSONString(articlecatList));
		model.addAttribute("entity", entity);
		return "articlecat/insert";
	}

	/*
	 * 添加do
	 * 
	 * @param Articlecat entity
	 * 
	 * String name
	 * 
	 * String idx
	 * 
	 * Integer parentid
	 * 
	 * Integer type 栏目类型
	 * 
	 * Integer deviceid
	 * 
	 * 
	 */
	@RequestMapping(value = "/insertDo", method = RequestMethod.POST)
	public String insertDo(@Valid Articlecat entity, BindingResult bindingResult, Model model,
			RedirectAttributes attr) {
		// 1.基本数据校验
		Map<String, String> map = ValidUtil.error2map(bindingResult);
		if (map.size() > 0) {
			List<ArticlecatDto> articlecatList = articlecatService.findFirstLevel();

			model.addAttribute("articlecatList", JSON.toJSONString(articlecatList));
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "articlecat/insert";
		}

		// 写入数据库
		Articlecat articlecat = articlecatService.insert(entity);
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
		Articlecat articlecat = articlecatService.get(id);
		List<ArticlecatDto> articlecatList = articlecatService.findFirstLevel();

		model.addAttribute("articlecatList", JSON.toJSONString(articlecatList));
		model.addAttribute("entity", articlecat);
		return "articlecat/update";
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
	public String updateDo(@Valid Articlecat entity, BindingResult bindingResult, Model model,
			RedirectAttributes attr) {
		// 数据校验
		Map<String, String> map = ValidUtil.error2map(bindingResult, entity.getId());
		if (map.size() > 0) {
			List<ArticlecatDto> articlecatList = articlecatService.findFirstLevel();

			model.addAttribute("articlecatList", JSON.toJSONString(articlecatList));
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "articlecat/update";
		}

		// 写入数据库
		articlecatService.update(entity);
		attr.addAttribute("id", entity.getId());
		return "redirect:/message/success.jsp";
	}

	/*
	 * 单个删除
	 * 
	 * @param Long id 编号
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Object delete(Integer id) {
		BaseResult<Object> baseResult = new BaseResult<Object>();

		baseResult = articlecatService.deleteCascade(id);

		return baseResult;
	}

}
