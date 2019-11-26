package com.cpjl.managecenter.dto;

import com.cpjl.managecenter.entity.Video;
import com.cpjl.managecenter.model.Page;

public class VideoDto extends Video {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4393192976239602864L;

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
