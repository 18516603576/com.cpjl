package com.cpjl.managecenter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cpjl.managecenter.dto.ArticlecatDto;
import com.cpjl.managecenter.entity.Articlecat;

@Mapper
public interface ArticlecatDao {

	/*
	 * 获取顶层栏目
	 */
	List<ArticlecatDto> findFirstLevel();

	/*
	 * 获取子栏目
	 */
	List<ArticlecatDto> findByParentid(@Param("parentid") Integer parentid);

	/*
	 * 添加一条记录
	 */
	int insert(Articlecat entity);

	/*
	 * 获取一条记录
	 */
	Articlecat get(@Param("id") Integer id);

	/*
	 * 更新一条记录
	 */
	int update(Articlecat entity);

	/*
	 * 删除一条记录
	 */
	int delete(@Param("id") Integer id);

}
