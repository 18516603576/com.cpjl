package com.cpjl.api.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cpjl.api.dto.ArticleDto;
import com.cpjl.api.entity.Article;
import com.cpjl.api.model.BaseResult;
import com.cpjl.api.model.DataGrid;
import com.cpjl.api.model.DataPaging;
import com.cpjl.api.model.Page;
import com.cpjl.api.service.ArticleService;
import com.cpjl.api.service.ArticlecatService;

@CrossOrigin
@RestController
@RequestMapping(value = "/article", produces = "application/json;charset=UTF-8")
public class ArticleController {
	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	@Resource
	private ArticlecatService articlecatService;
	@Resource
	private ArticleService articleService;

	/*
	 * type=1,获取单个页面
	 * 
	 * @param ArticleDto
	 * 
	 * Integer articlecatid
	 * 
	 */
	@RequestMapping(value = "/listone", method = RequestMethod.GET)
	public String listone(ArticleDto entity) {
		BaseResult<Article> baseResult = new BaseResult<Article>();

		try {
			Article article = articleService.getByArticlecatid(entity.getArticlecatid());
			baseResult.setResult(true);
			baseResult.setMessage("SUCCESS");
			baseResult.setModel(article);
		} catch (Exception e) {
			baseResult.setResult(false);
			baseResult.setMessage("查询失败：" + e.getMessage());
		}

		String result = JSONObject.toJSONString(baseResult, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullListAsEmpty);
		logger.info(result);
		return result;
	}

	/*
	 * type=2，type=3获取栏目文章列表
	 * 
	 * @param ArticleDto
	 * 
	 * Integer articlecatid
	 * 
	 * Integer page.currentPage
	 * 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ArticleDto entity) {
		BaseResult<Object> baseResult = new BaseResult<Object>();

		try {
			Page page = entity.getPage();
			DataGrid<Article> dataGrid = null;

			dataGrid = articleService.paging(entity, page);
			page.setRecordCount(dataGrid.getNumRows());

			DataPaging<Article> dataPaging = new DataPaging<Article>();
			dataPaging.setList(dataGrid.getItems());
			dataPaging.setPage(page);

			baseResult.setResult(true);
			baseResult.setMessage("SUCCESS");
			baseResult.setModel(dataPaging);
		} catch (Exception e) {
			baseResult.setResult(false);
			baseResult.setMessage("查询失败：" + e.getMessage());
		}

		String result = JSONObject.toJSONString(baseResult, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullListAsEmpty);
		logger.info(result);
		return result;

	}

	/*
	 * 获取一篇文章
	 * 
	 * @param Integer id
	 * 
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String get(Integer id) {
		BaseResult<Article> baseResult = new BaseResult<Article>();

		try {
			Article article = articleService.get(id);
			baseResult.setResult(true);
			baseResult.setMessage("SUCCESS");
			baseResult.setModel(article);
		} catch (Exception e) {
			baseResult.setResult(false);
			baseResult.setMessage("查询失败：" + e.getMessage());
		}

		String result = JSONObject.toJSONString(baseResult, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullListAsEmpty);
		logger.info(result);
		return result;
	}
}
