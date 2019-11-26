package com.cpjl.managecenter.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.cpjl.managecenter.dto.ProgressDto;
import com.cpjl.managecenter.model.BaseResult;
import com.cpjl.managecenter.service.UploadService;

@RestController
@RequestMapping(value = "/upload", produces = "application/json;charset=UTF-8")
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Resource
	private UploadService uploadService;

	/*
	 * 上传图片并裁切,固定宽高比
	 * 
	 * @param MultipartFile file
	 * 
	 * @param int width 宽度
	 * 
	 * @param int height 高度
	 */
	@PostMapping("/thumbnail")
	public Object thumbnail(List<MultipartFile> file, int width, int height) {
		BaseResult<Object> baseResult = new BaseResult<Object>();
		try {
			baseResult = uploadService.thumbnail(file, width, height);
			return baseResult;
		} catch (Exception e) {
			baseResult.setResult(false);
			baseResult.setMessage(e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
			return baseResult;
		}
	}

	/*
	 * 上传图片并裁切，固定宽度
	 * 
	 * @param MultipartFile file
	 * 
	 * @param int width 宽度
	 */
	@PostMapping("/toWidth")
	public Object toWidth(List<MultipartFile> file, int width) {
		BaseResult<Object> baseResult = new BaseResult<Object>();
		try {
			baseResult = uploadService.toWidth(file, width);
			logger.info(JSON.toJSONString(baseResult));
			return baseResult;
		} catch (Exception e) {
			baseResult.setResult(false);
			baseResult.setMessage(e.getMessage());
			logger.info(JSON.toJSONString(baseResult));

			e.printStackTrace();
			return baseResult;
		}
	}

	/*
	 * 上传视频，并记录进度
	 * 
	 * @param MultipartFile file
	 * 
	 * @param String uuid 上传唯一编号
	 */
	@PostMapping("/video")
	public Object video(MultipartFile file, @RequestParam("uuid") String uuid) {
		BaseResult<Object> baseResult = new BaseResult<Object>();
		try {
			baseResult = uploadService.video(file, uuid);
			return baseResult;
		} catch (Exception e) {
			baseResult.setResult(false);
			baseResult.setMessage(e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
			return baseResult;
		}
	}

	/*
	 * 获取文件上传进度
	 */
	@ResponseBody
	@RequestMapping(value = "/videoProgress", method = RequestMethod.GET)
	public Object videoProgress(String uuid, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object transferredBytes = session.getAttribute(uuid + "transferredBytes");
		Object totalBytes = session.getAttribute(uuid + "totalBytes");
		Long total = 0L;
		Long transferred = 0L;
		if (totalBytes != null) {
			total = Long.valueOf(String.valueOf(totalBytes));
		}
		if (transferredBytes != null) {
			transferred = Long.valueOf(String.valueOf(transferredBytes));
		}

		ProgressDto progressDto = new ProgressDto();
		progressDto.setTotalBytes(total);
		progressDto.setTransferredBytes(transferred);

		BaseResult<ProgressDto> baseResult = new BaseResult<ProgressDto>();
		baseResult.setResult(true);
		baseResult.setMessage("获取文件传输进度信息成功");
		baseResult.setModel(progressDto);
		return baseResult;
	}

}
