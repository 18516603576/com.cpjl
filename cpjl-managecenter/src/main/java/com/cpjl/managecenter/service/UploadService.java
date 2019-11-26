package com.cpjl.managecenter.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cpjl.managecenter.model.BaseResult;

public interface UploadService {

	/*
	 * 上传图片并裁切,固定宽高比
	 */
	BaseResult<Object> thumbnail(List<MultipartFile> files, int width, int height);

	/*
	 * 上传图片并裁切，固定宽度
	 */
	BaseResult<Object> toWidth(List<MultipartFile> file, int width);

	/*
	 * 上传视频
	 */
	BaseResult<Object> video(MultipartFile file, String uuid);

}
