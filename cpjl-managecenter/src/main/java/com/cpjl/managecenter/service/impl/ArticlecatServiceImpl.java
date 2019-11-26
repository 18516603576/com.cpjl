package com.cpjl.managecenter.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpjl.managecenter.dao.ArticlecatDao;
import com.cpjl.managecenter.dto.ArticlecatDto;
import com.cpjl.managecenter.entity.Article;
import com.cpjl.managecenter.entity.Articlecat;
import com.cpjl.managecenter.model.BaseResult;
import com.cpjl.managecenter.service.ArticleService;
import com.cpjl.managecenter.service.ArticlecatService;

@Service("articlecatService")
public class ArticlecatServiceImpl implements ArticlecatService {
	@Resource
	private ArticlecatDao articlecatDao;
	@Resource
	private ArticleService articleService;

	@Override
	public List<ArticlecatDto> findAll() {
		// TODO Auto-generated method stub
		List<ArticlecatDto> list = this.findFirstLevel();
		for (ArticlecatDto dto : list) {
			List<ArticlecatDto> children = this.findByParentid(dto.getId());
			dto.setChildren(children);
		}
		return list;
	}

	private List<ArticlecatDto> findByParentid(Integer id) {
		// TODO Auto-generated method stub
		return articlecatDao.findByParentid(id);
	}

	@Override
	public List<ArticlecatDto> findFirstLevel() {
		// TODO Auto-generated method stub
		return articlecatDao.findFirstLevel();
	}

	@Override
	public Articlecat insert(Articlecat entity) {
		// TODO Auto-generated method stub
		entity.setCreatetime(new Date());
		int rows = articlecatDao.insert(entity);
		return entity;
	}

	@Override
	public Articlecat get(Integer id) {
		// TODO Auto-generated method stub
		return articlecatDao.get(id);
	}

	@Override
	public void update(Articlecat entity) {
		// TODO Auto-generated method stub
		Articlecat articlecat = articlecatDao.get(entity.getId());

		articlecat.setName(entity.getName());

		articlecat.setIdx(entity.getIdx());

		articlecat.setParentid(entity.getParentid());

		articlecat.setType(entity.getType());

		int rows = articlecatDao.update(articlecat);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		articlecatDao.delete(id);
	}

	@Override
	@Transactional
	public BaseResult<Object> deleteCascade(Integer id) {
		// TODO Auto-generated method stub
		// 1.验证栏目下是否有文章
		BaseResult<Object> baseResult = new BaseResult<Object>();
		Article article = articleService.getOneByArticlecatid(id);
		if (article != null) {
			baseResult.setResult(false);
			baseResult.setMessage("请先删除栏目下的文章，栏目编号：" + id);
			return baseResult;
		}

		List<ArticlecatDto> list = this.findByParentid(id);
		for (ArticlecatDto dto : list) {
			article = articleService.getOneByArticlecatid(dto.getId());
			if (article != null) {
				baseResult.setResult(false);
				baseResult.setMessage("请先删除栏目下的文章，栏目编号：" + dto.getId());
				return baseResult;
			}
		}

		// 2、级联删除栏目
		this.delete(id);
		for (ArticlecatDto dto : list) {
			this.delete(dto.getId());
		}
		baseResult.setResult(true);
		baseResult.setMessage("删除成功");
		return baseResult;

	}

}
