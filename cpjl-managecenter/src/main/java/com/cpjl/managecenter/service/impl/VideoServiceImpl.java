package com.cpjl.managecenter.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.cpjl.managecenter.dao.VideoDao;
import com.cpjl.managecenter.entity.User;
import com.cpjl.managecenter.entity.Video;
import com.cpjl.managecenter.model.DataGrid;
import com.cpjl.managecenter.service.VideoService;

@Service("videoService")
public class VideoServiceImpl implements VideoService {
	@Resource
	private VideoDao videoDao;
	@Resource
	private HttpServletRequest request;

	@Override
	public Video getOneByVideocatid(Integer videocatid) {
		// TODO Auto-generated method stub
		return videoDao.getOneByVideocatid(videocatid);
	}

	@Override
	public DataGrid<Video> paging(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Integer numRows = videoDao.paging_count(params);
		List<Video> rows = videoDao.paging_datas(params);

		return new DataGrid<Video>(rows, numRows);
	}

	@Override
	public Video insert(Video entity) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		entity.setUserid(user.getId());
		entity.setCreatetime(new Date());

		int rows = videoDao.insert(entity);
		return entity;
	}

	@Override
	public Video get(Integer id) {
		// TODO Auto-generated method stub
		return videoDao.get(id);
	}

	@Override
	public Video update(@Valid Video entity) {
		// TODO Auto-generated method stub
		Video video = this.get(entity.getId());
		video.setTitle(entity.getTitle());

		video.setVideocatid(entity.getVideocatid());

		video.setImg(entity.getImg());

		video.setContent(entity.getContent());

		video.setIdx(entity.getIdx());

		int rows = videoDao.update(video);
		return video;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return videoDao.delete(id);
	}

	@Override
	public int BatchDelete(Integer[] ids) {
		// TODO Auto-generated method stub
		int rows = 0;
		for (Integer id : ids) {
			rows = rows + this.delete(id);
		}
		return rows;
	}

}
