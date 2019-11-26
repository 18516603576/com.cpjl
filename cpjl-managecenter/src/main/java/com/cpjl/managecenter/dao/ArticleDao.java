package com.cpjl.managecenter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cpjl.managecenter.entity.Article;

@Mapper
public interface ArticleDao {

	/*
	 * 栏目下是否存在文章
	 */
	Article getOneByArticlecatid(@Param("articlecatid") Integer articlecatid);

	/*
	 * 分页
	 */
	Integer paging_count(Map<String, Object> params);

	List<Article> paging_datas(Map<String, Object> params);

	/*
	 * 插入一条记录
	 */
	int insert(Article entity);

	/*
	 * 获取一条记录
	 */
	Article get(@Param("id") Integer id);

	/*
	 * 更新一条记录
	 */
	int update(Article article);

	/*
	 * 删除一条记录
	 */
	int delete(@Param("id") Integer id);

}
