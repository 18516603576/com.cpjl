package com.cpjl.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cpjl.api.entity.Article;

@Mapper
public interface ArticleDao {

	/*
	 * 获取栏目下第一篇文章
	 */
	Article getByArticlecatid(@Param("articlecatid") Integer articlecatid);

	/*
	 * 分页
	 */
	Integer paging_count(Map<String, Object> params);

	List<Article> paging_datas(Map<String, Object> params);

	/*
	 * 获取一条记录
	 */
	Article get(@Param("id") Integer id);

}
