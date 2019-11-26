package com.cpjl.managecenter.service;

import java.util.List;

import com.cpjl.managecenter.dto.ArticlecatDto;
import com.cpjl.managecenter.entity.Articlecat;
import com.cpjl.managecenter.model.BaseResult;

public interface ArticlecatService {

	/*
	 * 获取设备下所有栏目
	 */
	List<ArticlecatDto> findAll();

	/*
	 * 第一层栏目
	 */
	public List<ArticlecatDto> findFirstLevel();

	/*
	 * 添加一条记录
	 */
	Articlecat insert(Articlecat entity);

	/*
	 * 获取一条记录
	 */
	Articlecat get(Integer id);

	/*
	 * 更新一条记录
	 */
	void update(Articlecat entity);

	/*
	 * 删除一条
	 */
	void delete(Integer id);

	/*
	 * 级联删除
	 */
	BaseResult<Object> deleteCascade(Integer id);

}
