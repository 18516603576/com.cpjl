package com.cpjl.managecenter.dto;

import com.cpjl.managecenter.entity.User;
import com.cpjl.managecenter.model.Page;

public class UserDto extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1365324634900346609L;
	/*
	 * 搜索关键词
	 */
	private String wd;

	/*
	 * 分页
	 */
	private Page page = new Page();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

}
