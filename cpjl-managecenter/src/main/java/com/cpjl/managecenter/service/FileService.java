package com.cpjl.managecenter.service;

import java.util.Map;

import com.cpjl.managecenter.entity.File;
import com.cpjl.managecenter.model.DataGrid;
import com.cpjl.managecenter.model.Page;

/**
 * 服务接口。
 */
public interface FileService {
	/*
	 * ueditor文件分页
	 */
	DataGrid<File> ueditorPaging(Map<String, Object> params, Page page);

	/*
	 * 插入一条记录
	 */
	File insert(File file);
}
