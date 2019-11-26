package com.cpjl.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cpjl.api.dao.VideoDao;
import com.cpjl.api.dto.VideoDto;
import com.cpjl.api.entity.Video;
import com.cpjl.api.model.DataGrid;
import com.cpjl.api.model.Page;
import com.cpjl.api.service.VideoService;

@Service("videoService")
public class VideoServiceImpl implements VideoService {
	@Resource
	private VideoDao videoDao;

	@Override
	public DataGrid<Video> paging(VideoDto entity, Page page) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		params.put("page", page);

		Integer numRows = videoDao.paging_count(params);

		List<Video> rows = videoDao.paging_datas(params);

		return new DataGrid<Video>(rows, numRows);

	}

	@Override
	public Video get(Integer id) {
		// TODO Auto-generated method stub
		return videoDao.get(id);
	}

}
