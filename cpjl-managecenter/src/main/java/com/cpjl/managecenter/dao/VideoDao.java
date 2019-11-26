package com.cpjl.managecenter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cpjl.managecenter.entity.Video;

@Mapper
public interface VideoDao {

	/*
	 * 栏目下是否存在文章
	 */
	Video getOneByVideocatid(@Param("videocatid") Integer videocatid);

	/*
	 * 分页
	 */
	Integer paging_count(Map<String, Object> params);

	List<Video> paging_datas(Map<String, Object> params);

	/*
	 * 插入一条记录
	 */
	int insert(Video entity);

	/*
	 * 获取一条记录
	 */
	Video get(@Param("id") Integer id);

	/*
	 * 更新一条记录
	 */
	int update(Video video);

	/*
	 * 删除一条记录
	 */
	int delete(Integer id);

}
