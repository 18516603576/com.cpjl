package com.cpjl.managecenter.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cpjl.managecenter.entity.File;
import com.cpjl.managecenter.model.DataGrid;
import com.cpjl.managecenter.model.Page;

@Mapper
public interface FileDao {

	/*
	 * ueditor文件分页
	 */
	DataGrid<File> ueditorPaging(Map<String, Object> params, Page page);

	/*
	 * 插入一条记录
	 */
	int insert(File file);

}
