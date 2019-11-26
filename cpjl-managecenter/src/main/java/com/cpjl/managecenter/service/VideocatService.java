package com.cpjl.managecenter.service;

import java.util.List;

import com.cpjl.managecenter.dto.VideocatDto;
import com.cpjl.managecenter.entity.Videocat;
import com.cpjl.managecenter.model.BaseResult;

public interface VideocatService {

	/*
	 * 获取设备下所有栏目
	 */
	List<VideocatDto> findAll();

	/*
	 * 第一层栏目
	 */
	public List<VideocatDto> findFirstLevel();

	/*
	 * 添加一条记录
	 */
	Videocat insert(Videocat entity);

	/*
	 * 获取一条记录
	 */
	Videocat get(Integer id);

	/*
	 * 更新一条记录
	 */
	void update(Videocat entity);

	/*
	 * 删除一条
	 */
	void delete(Integer id);

	/*
	 * 级联删除
	 */
	BaseResult<Object> deleteCascade(Integer id);

}
