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
import com.cpjl.api.dto.VideoDto;
import com.cpjl.api.entity.Video;
import com.cpjl.api.model.BaseResult;
import com.cpjl.api.model.DataGrid;
import com.cpjl.api.model.DataPaging;
import com.cpjl.api.model.Page;
import com.cpjl.api.service.VideoService;
import com.cpjl.api.service.VideocatService;

@CrossOrigin
@RestController
@RequestMapping(value = "/video", produces = "application/json;charset=UTF-8")
public class VideoController {
	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
	@Resource
	private VideocatService videocatService;
	@Resource
	private VideoService videoService;

	/*
	 * type=3获取栏目文章列表
	 * 
	 * @param VideoDto
	 * 
	 * Integer videocatid
	 * 
	 * Integer page.currentPage
	 * 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(VideoDto entity) {
		BaseResult<Object> baseResult = new BaseResult<Object>();

		try {
			Page page = entity.getPage();
			DataGrid<Video> dataGrid = null;

			dataGrid = videoService.paging(entity, page);
			page.setRecordCount(dataGrid.getNumRows());

			DataPaging<Video> dataPaging = new DataPaging<Video>();
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
		BaseResult<Video> baseResult = new BaseResult<Video>();

		try {
			Video video = videoService.get(id);
			baseResult.setResult(true);
			baseResult.setMessage("SUCCESS");
			baseResult.setModel(video);
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
