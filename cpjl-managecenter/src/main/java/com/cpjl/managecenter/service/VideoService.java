package com.cpjl.managecenter.service;

import java.util.Map;

import com.cpjl.managecenter.entity.Video;
import com.cpjl.managecenter.model.DataGrid;

public interface VideoService {

	/*
	 * 获取栏目下是否有文章
	 */
	Video getOneByVideocatid(Integer videocatid);

	/*
	 * 分页
	 */
	DataGrid<Video> paging(Map<String, Object> params);

	/*
	 * 插入一条记录
	 */
	Video insert(Video entity);

	/*
	 * 获取一条记录
	 */
	Video get(Integer id);

	/*
	 * 更新一条记录
	 */
	Video update(Video entity);

	/*
	 * 删除一条记录
	 */
	int delete(Integer id);

	/*
	 * 批量删除
	 */
	int BatchDelete(Integer[] id);

}
