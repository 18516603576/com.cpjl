package com.cpjl.managecenter.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.cpjl.managecenter.dao.ArticleDao;
import com.cpjl.managecenter.entity.Article;
import com.cpjl.managecenter.entity.User;
import com.cpjl.managecenter.model.DataGrid;
import com.cpjl.managecenter.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleDao articleDao;
	@Resource
	private HttpServletRequest request;

	@Override
	public Article getOneByArticlecatid(Integer articlecatid) {
		// TODO Auto-generated method stub
		return articleDao.getOneByArticlecatid(articlecatid);
	}

	@Override
	public DataGrid<Article> paging(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Integer numRows = articleDao.paging_count(params);
		List<Article> rows = articleDao.paging_datas(params);

		return new DataGrid<Article>(rows, numRows);
	}

	@Override
	public Article insert(Article entity) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		entity.setUserid(user.getId());
		entity.setCreatetime(new Date());

		int rows = articleDao.insert(entity);
		return entity;
	}

	@Override
	public Article get(Integer id) {
		// TODO Auto-generated method stub
		return articleDao.get(id);
	}

	@Override
	public Article update(@Valid Article entity) {
		// TODO Auto-generated method stub
		Article article = this.get(entity.getId());
		article.setTitle(entity.getTitle());

		article.setArticlecatid(entity.getArticlecatid());

		article.setImg(entity.getImg());

		article.setContent(entity.getContent());

		article.setIdx(entity.getIdx());

		int rows = articleDao.update(article);
		return article;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return articleDao.delete(id);
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
