package com.cpjl.managecenter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cpjl.managecenter.dto.VideocatDto;
import com.cpjl.managecenter.entity.Videocat;

@Mapper
public interface VideocatDao {

	/*
	 * 获取顶层栏目
	 */
	List<VideocatDto> findFirstLevel();

	/*
	 * 获取子栏目
	 */
	List<VideocatDto> findByParentid(@Param("parentid") Integer parentid);

	/*
	 * 添加一条记录
	 */
	int insert(Videocat entity);

	/*
	 * 获取一条记录
	 */
	Videocat get(@Param("id") Integer id);

	/*
	 * 更新一条记录
	 */
	int update(Videocat entity);

	/*
	 * 删除一条记录
	 */
	int delete(@Param("id") Integer id);

}
