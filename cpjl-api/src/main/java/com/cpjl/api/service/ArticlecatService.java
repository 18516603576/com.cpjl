package com.cpjl.api.service;

import java.util.List;

import com.cpjl.api.dto.ArticlecatDto;
import com.cpjl.api.entity.Articlecat;

public interface ArticlecatService {

	/*
	 * 获取当前栏目及其子栏目
	 */
	ArticlecatDto findSelfAndChildren(Integer id);

	/*
	 * 获取子栏目
	 */
	public List<ArticlecatDto> findByParentid(Integer parentid);

	/*
	 * 获取一条记录
	 */
	Articlecat get(Integer articlecatid);

	/*
	 * 获取一级栏目
	 */
	List<ArticlecatDto> findAll();

}
