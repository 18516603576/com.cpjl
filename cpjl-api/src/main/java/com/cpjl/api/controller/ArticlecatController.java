package com.cpjl.api.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cpjl.api.dto.ArticlecatDto;
import com.cpjl.api.model.BaseResult;
import com.cpjl.api.service.ArticlecatService;

@CrossOrigin
@RestController
@RequestMapping(value = "/articlecat", produces = "application/json;charset=UTF-8")
public class ArticlecatController {
	private static final Logger logger = LoggerFactory.getLogger(ArticlecatController.class);
	@Resource
	private ArticlecatService articlecatService;

	/*
	 * 获取当前栏目及其子栏目
	 * 
	 * @param Integer id 当前栏目
	 */
	@GetMapping(value = "/selfAndChildren")
	public String selfAndChildren(Integer id) {
		BaseResult<Object> baseResult = new BaseResult<Object>();

		try {
			ArticlecatDto dto = articlecatService.findSelfAndChildren(id);
			baseResult.setResult(true);
			baseResult.setMessage("SUCCESS");
			baseResult.setModel(dto);
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
	 * 所有一级栏目
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public String findAll() {
		BaseResult<Object> baseResult = new BaseResult<Object>();

		try {
			List<ArticlecatDto> list = articlecatService.findAll();
			baseResult.setResult(true);
			baseResult.setMessage("SUCCESS");
			baseResult.setModel(list);
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
