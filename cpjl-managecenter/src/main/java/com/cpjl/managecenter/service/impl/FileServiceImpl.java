package com.cpjl.managecenter.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cpjl.managecenter.dao.FileDao;
import com.cpjl.managecenter.entity.File;
import com.cpjl.managecenter.model.DataGrid;
import com.cpjl.managecenter.model.Page;
import com.cpjl.managecenter.service.FileService;

@Service("fileService")
public class FileServiceImpl implements FileService {

	@Resource
	private FileDao fileDao;

	@Override
	public DataGrid<File> ueditorPaging(Map<String, Object> params, Page page) {
		// TODO Auto-generated method stub
		return fileDao.ueditorPaging(params, page);
	}

	@Override
	public File insert(File file) {
		// TODO Auto-generated method stub
		int rows = fileDao.insert(file);
		return file;
	}
}
