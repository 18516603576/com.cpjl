package com.cpjl.managecenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.cpjl.managecenter.dto.ArticleDto;
import com.cpjl.managecenter.dto.ArticlecatDto;
import com.cpjl.managecenter.entity.Article;
import com.cpjl.managecenter.model.BaseResult;
import com.cpjl.managecenter.model.DataGrid;
import com.cpjl.managecenter.model.Page;
import com.cpjl.managecenter.service.ArticleService;
import com.cpjl.managecenter.service.ArticlecatService;
import com.cpjl.managecenter.util.ValidUtil;

@Controller
@RequestMapping("article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Resource
	private ArticlecatService articlecatService;

	/*
	 * 列表页
	 * 
	 * @param ArticleDto entity
	 * 
	 * page.currentPage 当前页码
	 * 
	 * articlecatid 栏目id
	 * 
	 * wd 搜索关键词
	 * 
	 * @param String cName 显示的栏目名
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ArticleDto entity, Model model, String cName) {

		Page page = entity.getPage();
		// page.setPageSize(1);
		DataGrid<Article> dataGrid = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		params.put("page", page);

		// 分页查询
		dataGrid = articleService.paging(params);
		page.setRecordCount(dataGrid.getNumRows());
		// 用于分页点击的url
		String url = "?page.currentPage=$currentPage" //
				+ "&articlecatid=" + (entity.getArticlecatid() == null ? "" : entity.getArticlecatid()) //
				+ "&cName=" + cName //
				+ "&wd=" + (entity.getWd() == null ? "" : entity.getWd());
		page.setUrl(url);

		List<ArticlecatDto> articlecatList = articlecatService.findAll();
		model.addAttribute("articlecatList", JSON.toJSONString(articlecatList));
		model.addAttribute("entity", entity);
		model.addAttribute("cName", cName);
		model.addAttribute("list", dataGrid.getItems());
		model.addAttribute("page", page);
		return "article/list";
	}

	/*
	 * 添加页面
	 * 
	 * @param Article article
	 * 
	 * int articlecatid
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Article entity, Model model) {
		List<ArticlecatDto> articlecatList = articlecatService.findAll();
		model.addAttribute("articlecatList", JSON.toJSONString(articlecatList));
		model.addAttribute("entity", entity);
		return "article/insert";
	}

	/*
	 * 添加do
	 *
	 * @param Article entity
	 *
	 * String title 标题
	 * 
	 * Integer articlecatid
	 * 
	 * String img 图片
	 * 
	 * String content
	 *
	 * Integer idx 排序
	 *
	 * 
	 */

	@RequestMapping(value = "/insertDo", method = RequestMethod.POST)
	public String insertDo(@Valid Article entity, BindingResult bindingResult, Model model, RedirectAttributes attr) {
		// 数据校验
		Map<String, String> map = ValidUtil.error2map(bindingResult);
		if (map.size() > 0) {
			List<ArticlecatDto> articlecatList = articlecatService.findAll();
			model.addAttribute("articlecatList", JSON.toJSONString(articlecatList));
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "article/insert";
		}

		// 写入数据库
		articleService.insert(entity);
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
		Article entity = articleService.get(id);
		List<ArticlecatDto> articlecatList = articlecatService.findAll();

		model.addAttribute("articlecatList", JSON.toJSONString(articlecatList));
		model.addAttribute("entity", entity);
		return "article/update";
	}

	/*
	 * 修改do
	 *
	 * @param Article
	 * 
	 * id 编号
	 *
	 * String title 标题
	 * 
	 * Integer articlecatid
	 * 
	 * String img 图片
	 * 
	 * String content
	 *
	 * Integer idx 排序
	 * 
	 */
	@RequestMapping(value = "/updateDo", method = RequestMethod.POST)
	public String updateDo(@Valid Article entity, BindingResult bindingResult, Model model, RedirectAttributes attr) {
		// 数据校验
		Map<String, String> map = ValidUtil.error2map(bindingResult, entity.getId());
		if (map.size() > 0) {
			List<ArticlecatDto> articlecatList = articlecatService.findAll();
			model.addAttribute("articlecatList", JSON.toJSONString(articlecatList));
			model.addAttribute("entity", entity);
			model.addAttribute("errors", map);
			return "article/update";
		}

		// 写入数据库
		articleService.update(entity);
		attr.addAttribute("id", entity.getId());
		return "redirect:/message/success.jsp";
	}

	/*
	 * 单个删除do
	 *
	 * @param Long id 编号
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Object delete(Integer id) {
		BaseResult<Object> baseResult = new BaseResult<Object>();
		try {
			int rows = articleService.delete(id);
			baseResult.setResult(true);
			baseResult.setMessage("删除成功");
		} catch (Exception e) {
			baseResult.setResult(false);
			baseResult.setMessage("失败：" + e.getMessage());
		}

		return baseResult;
	}

	/*
	 * 批量删除do
	 *
	 * @param Long[] id 编号数组
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.GET)
	public Object deleteBatch(Integer[] id) {
		BaseResult<Object> baseResult = new BaseResult<Object>();
		try {
			int rows = articleService.BatchDelete(id);
			baseResult.setResult(true);
			baseResult.setMessage("批量删除成功");
		} catch (Exception e) {
			baseResult.setResult(false);
			baseResult.setMessage("失败：" + e.getMessage());
		}

		return baseResult;
	}

}
