package com.cpjl.api.service;

import com.cpjl.api.dto.VideoDto;
import com.cpjl.api.entity.Video;
import com.cpjl.api.model.DataGrid;
import com.cpjl.api.model.Page;

public interface VideoService {

	/*
	 * 分页
	 */
	DataGrid<Video> paging(VideoDto entity, Page page);

	/*
	 * 获取一条记录
	 */
	Video get(Integer id);

}