package com.cpjl.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cpjl.api.dto.VideocatDto;

@Mapper
public interface VideocatDao {

	/*
	 * 获取一条记录
	 */
	VideocatDto get(@Param("id") Integer id);

	/*
	 * 获取子栏目
	 */
	List<VideocatDto> findByParentid(@Param("parentid") Integer parentid);

	/*
	 * 获取一级栏目
	 */
	List<VideocatDto> findFirstLevel();

}
