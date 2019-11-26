package com.cpjl.managecenter.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * 数据列表返回格式封装实体。
 * 
 * @author zhuangchao
 * @param <T>
 *            数据列表类型
 */
public class DataPaging<T> implements Serializable {

	private static final long serialVersionUID = -5232724467080317253L;

	private Page page;
	// 当前分页记录列表
	private Collection<T> list;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Collection<T> getList() {
		return list;
	}

	public void setList(Collection<T> list) {
		this.list = list;
	}

}
