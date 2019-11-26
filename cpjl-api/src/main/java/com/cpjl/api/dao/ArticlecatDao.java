package com.cpjl.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cpjl.api.dto.ArticlecatDto;

@Mapper
public interface ArticlecatDao {

	/*
	 * 获取一条记录
	 */
	ArticlecatDto get(@Param("id") Integer id);

	/*
	 * 获取子栏目
	 */
	List<ArticlecatDto> findByParentid(@Param("parentid") Integer parentid);

	/*
	 * 获取一级栏目
	 */
	List<ArticlecatDto> findFirstLevel();

}
