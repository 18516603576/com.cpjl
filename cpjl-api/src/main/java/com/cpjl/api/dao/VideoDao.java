package com.cpjl.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cpjl.api.entity.Video;

@Mapper
public interface VideoDao {

	/*
	 * 分页
	 */
	Integer paging_count(Map<String, Object> params);

	List<Video> paging_datas(Map<String, Object> params);

	/*
	 * 获取一条记录
	 */
	Video get(@Param("id") Integer id);

}
