package com.cpjl.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cpjl.api.dao.ArticlecatDao;
import com.cpjl.api.dto.ArticlecatDto;
import com.cpjl.api.entity.Articlecat;
import com.cpjl.api.service.ArticlecatService;

@Service("articlecatService")
public class ArticlecatServiceImpl implements ArticlecatService {
	@Resource
	private ArticlecatDao articlecatDao;

	@Override
	public ArticlecatDto findSelfAndChildren(Integer id) {
		// TODO Auto-generated method stub
		ArticlecatDto dto = articlecatDao.get(id);
		int parentid = id;
		if (dto.getParentid() != null) {
			parentid = dto.getParentid();
			dto = articlecatDao.get(parentid);
		}
		List<ArticlecatDto> children = this.findByParentid(parentid);
		dto.setChildren(children);
		return dto;
	}

	public List<ArticlecatDto> findByParentid(Integer parentid) {
		// TODO Auto-generated method stub
		return articlecatDao.findByParentid(parentid);
	}

	@Override
	public Articlecat get(Integer articlecatid) {
		// TODO Auto-generated method stub
		return articlecatDao.get(articlecatid);
	}

	@Override
	public List<ArticlecatDto> findAll() {
		// TODO Auto-generated method stub
		List<ArticlecatDto> list = articlecatDao.findFirstLevel();
		for (ArticlecatDto dto : list) {
			List<ArticlecatDto> children = this.findByParentid(dto.getId());
			dto.setChildren(children);
		}
		return list;
	}

}
