package com.cpjl.api.service;

import com.cpjl.api.dto.ArticleDto;
import com.cpjl.api.entity.Article;
import com.cpjl.api.model.DataGrid;
import com.cpjl.api.model.Page;

public interface ArticleService {

	/*
	 * 获取栏目下第一篇文章
	 */
	Article getByArticlecatid(Integer articlecatid);

	/*
	 * 分页
	 */
	DataGrid<Article> paging(ArticleDto entity, Page page);

	/*
	 * 获取一条记录
	 */
	Article get(Integer id);

}