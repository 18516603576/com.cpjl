package com.cpjl.managecenter.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cpjl.managecenter.dto.UploadResultDto;
import com.cpjl.managecenter.model.BaseResult;
import com.cpjl.managecenter.service.UploadService;
import com.cpjl.managecenter.util.UploadLocalUtil;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {
	private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);
	@Resource
	private HttpServletRequest request;

	@Override
	public BaseResult<Object> thumbnail(List<MultipartFile> files, int width, int height) {
		// TODO 自动生成的方法存根
		BaseResult<Object> baseResult = new BaseResult<Object>();
		try {
			baseResult.setResult(true);
			baseResult.setMessage("SUCCESS");
			List<UploadResultDto> list = new ArrayList<UploadResultDto>();
			for (MultipartFile file : files) {
				// 上传到本地
				UploadResultDto dto = UploadLocalUtil.thumnail(file, width, height);
				list.add(dto);
			}

			baseResult.setModel(list);
		} catch (IOException e) {
			baseResult.setResult(false);
			baseResult.setMessage("上传失败" + e.getMessage());
		}
		return baseResult;
	}

	@Override
	public BaseResult<Object> toWidth(List<MultipartFile> files, int width) {
		// TODO 自动生成的方法存根
		BaseResult<Object> baseResult = new BaseResult<Object>();
		try {
			baseResult.setResult(true);
			baseResult.setMessage("SUCCESS");
			List<UploadResultDto> list = new ArrayList<UploadResultDto>();
			for (MultipartFile file : files) {
				// 上传到本地
				UploadResultDto dto = UploadLocalUtil.toWidth(file, width);
				list.add(dto);
			}

			baseResult.setModel(list);
		} catch (IOException e) {
			baseResult.setResult(false);
			baseResult.setMessage("上传失败" + e.getMessage());
		}
		return baseResult;
	}

	@Override
	public BaseResult<Object> video(MultipartFile file, String uuid) {
		// TODO Auto-generated method stub
		BaseResult<Object> baseResult = new BaseResult<Object>();
		try {
			// 上传到本地
			UploadResultDto dto = UploadLocalUtil.video(file, uuid, request);

			baseResult.setResult(true);
			baseResult.setMessage("SUCCESS");
			baseResult.setModel(dto);
		} catch (IOException e) {
			baseResult.setResult(false);
			baseResult.setMessage("上传失败" + e.getMessage());
		}
		return baseResult;
	}

}
