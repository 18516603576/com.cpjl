package com.cpjl.api.service;

import java.util.List;

import com.cpjl.api.dto.VideocatDto;
import com.cpjl.api.entity.Videocat;

public interface VideocatService {

	/*
	 * 获取当前栏目及其子栏目
	 */
	VideocatDto findSelfAndChildren(Integer id);

	/*
	 * 获取子栏目
	 */
	public List<VideocatDto> findByParentid(Integer parentid);

	/*
	 * 获取一条记录
	 */
	Videocat get(Integer articlecatid);

	/*
	 * 获取一级栏目
	 */
	List<VideocatDto> findAll();

}
