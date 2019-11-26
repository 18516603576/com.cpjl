package com.cpjl.managecenter.service;

import java.util.Map;

import com.cpjl.managecenter.entity.Article;
import com.cpjl.managecenter.model.DataGrid;

public interface ArticleService {

	/*
	 * 获取栏目下是否有文章
	 */
	Article getOneByArticlecatid(Integer articlecatid);

	/*
	 * 分页
	 */
	DataGrid<Article> paging(Map<String, Object> params);

	/*
	 * 插入一条记录
	 */
	Article insert(Article entity);

	/*
	 * 获取一条记录
	 */
	Article get(Integer id);

	/*
	 * 更新一条记录
	 */
	Article update(Article entity);

	/*
	 * 删除一条记录
	 */
	int delete(Integer id);

	/*
	 * 批量删除
	 */
	int BatchDelete(Integer[] id);

}
