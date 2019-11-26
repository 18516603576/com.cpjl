package com.cpjl.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cpjl.api.dao.VideocatDao;
import com.cpjl.api.dto.VideocatDto;
import com.cpjl.api.entity.Videocat;
import com.cpjl.api.service.VideocatService;

@Service("videocatService")
public class VideocatServiceImpl implements VideocatService {
	@Resource
	private VideocatDao videocatDao;

	@Override
	public VideocatDto findSelfAndChildren(Integer id) {
		// TODO Auto-generated method stub
		VideocatDto dto = videocatDao.get(id);
		int parentid = id;
		if (dto.getParentid() != null) {
			parentid = dto.getParentid();
			dto = videocatDao.get(parentid);
		}
		List<VideocatDto> children = this.findByParentid(parentid);
		dto.setChildren(children);
		return dto;
	}

	public List<VideocatDto> findByParentid(Integer parentid) {
		// TODO Auto-generated method stub
		return videocatDao.findByParentid(parentid);
	}

	@Override
	public Videocat get(Integer videocatid) {
		// TODO Auto-generated method stub
		return videocatDao.get(videocatid);
	}

	@Override
	public List<VideocatDto> findAll() {
		// TODO Auto-generated method stub
		List<VideocatDto> list = videocatDao.findFirstLevel();
		for (VideocatDto dto : list) {
			List<VideocatDto> children = this.findByParentid(dto.getId());
			dto.setChildren(children);
		}
		return list;
	}

}
