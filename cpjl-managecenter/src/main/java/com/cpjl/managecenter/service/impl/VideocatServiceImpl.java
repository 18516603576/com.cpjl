package com.cpjl.managecenter.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpjl.managecenter.dao.VideocatDao;
import com.cpjl.managecenter.dto.VideocatDto;
import com.cpjl.managecenter.entity.Video;
import com.cpjl.managecenter.entity.Videocat;
import com.cpjl.managecenter.model.BaseResult;
import com.cpjl.managecenter.service.VideoService;
import com.cpjl.managecenter.service.VideocatService;

@Service("videocatService")
public class VideocatServiceImpl implements VideocatService {
	@Resource
	private VideocatDao videocatDao;
	@Resource
	private VideoService videoService;

	@Override
	public List<VideocatDto> findAll() {
		// TODO Auto-generated method stub
		List<VideocatDto> list = this.findFirstLevel();
		for (VideocatDto dto : list) {
			List<VideocatDto> children = this.findByParentid(dto.getId());
			dto.setChildren(children);
		}
		return list;
	}

	private List<VideocatDto> findByParentid(Integer id) {
		// TODO Auto-generated method stub
		return videocatDao.findByParentid(id);
	}

	@Override
	public List<VideocatDto> findFirstLevel() {
		// TODO Auto-generated method stub
		return videocatDao.findFirstLevel();
	}

	@Override
	public Videocat insert(Videocat entity) {
		// TODO Auto-generated method stub
		entity.setCreatetime(new Date());
		int rows = videocatDao.insert(entity);
		return entity;
	}

	@Override
	public Videocat get(Integer id) {
		// TODO Auto-generated method stub
		return videocatDao.get(id);
	}

	@Override
	public void update(Videocat entity) {
		// TODO Auto-generated method stub
		Videocat articlecat = videocatDao.get(entity.getId());

		articlecat.setName(entity.getName());

		articlecat.setIdx(entity.getIdx());

		articlecat.setParentid(entity.getParentid());

		articlecat.setType(entity.getType());

		int rows = videocatDao.update(articlecat);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		videocatDao.delete(id);
	}

	@Override
	@Transactional
	public BaseResult<Object> deleteCascade(Integer id) {
		// TODO Auto-generated method stub
		// 1.验证栏目下是否有文章
		BaseResult<Object> baseResult = new BaseResult<Object>();
		Video article = videoService.getOneByVideocatid(id);
		if (article != null) {
			baseResult.setResult(false);
			baseResult.setMessage("请先删除栏目下的文章，栏目编号：" + id);
			return baseResult;
		}

		List<VideocatDto> list = this.findByParentid(id);
		for (VideocatDto dto : list) {
			article = videoService.getOneByVideocatid(dto.getId());
			if (article != null) {
				baseResult.setResult(false);
				baseResult.setMessage("请先删除栏目下的文章，栏目编号：" + dto.getId());
				return baseResult;
			}
		}

		// 2、级联删除栏目
		this.delete(id);
		for (VideocatDto dto : list) {
			this.delete(dto.getId());
		}
		baseResult.setResult(true);
		baseResult.setMessage("删除成功");
		return baseResult;

	}

}
