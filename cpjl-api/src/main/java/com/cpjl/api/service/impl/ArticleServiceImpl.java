package com.cpjl.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cpjl.api.dao.ArticleDao;
import com.cpjl.api.dto.ArticleDto;
import com.cpjl.api.entity.Article;
import com.cpjl.api.model.DataGrid;
import com.cpjl.api.model.Page;
import com.cpjl.api.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleDao articleDao;

	@Override
	public Article getByArticlecatid(Integer articlecatid) {
		// TODO Auto-generated method stub
		return articleDao.getByArticlecatid(articlecatid);
	}

	@Override
	public DataGrid<Article> paging(ArticleDto entity, Page page) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		params.put("page", page);

		Integer numRows = articleDao.paging_count(params);

		List<Article> rows = articleDao.paging_datas(params);

		return new DataGrid<Article>(rows, numRows);

	}

	@Override
	public Article get(Integer id) {
		// TODO Auto-generated method stub
		return articleDao.get(id);
	}

}
