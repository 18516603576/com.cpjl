package com.cpjl.managecenter.model;

/**
 * 分页数据封装类.
 */
public class Page implements java.io.Serializable {

	private static final long serialVersionUID = -5823482162101235168L;

	/*
	 * 当前页码
	 */
	private int currentPage = 1;
	/*
	 * 每页显示几条
	 */
	private int pageSize = 10;

	/*
	 * 总共查询到多少条记录
	 */
	private int recordCount;
	/*
	 * 总共多少页
	 */
	private int pageCount;
	/*
	 * 分页栏中的 数字页码开始
	 */
	private int beginPageIndex;
	/*
	 * 分页栏中的 数字页码结束
	 */
	private int endPageIndex;
	/*
	 * 分页url
	 */
	private String url;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// 后续处理=================
	// 分页的起始页
	public int getOffset() {
		return (currentPage - 1) * pageSize;
	}

	// 分页的每页多少条
	public int getLimit() {
		return pageSize;
	}

	// 分页的获取前面多少条
	public int getOffsetLimit() {
		return this.getOffset() + this.getLimit();
	}

	public int getRecordCount() {
		return recordCount;
	}

	/*
	 * 查询完成后，设置recordCount
	 * 
	 * 同时计算出 pageCount，beginPageIndex，endPageIndex
	 */
	public void setRecordCount(int recordCount) {

		this.recordCount = recordCount;

		pageCount = recordCount / pageSize;
		if (recordCount % pageSize > 0) {
			pageCount = pageCount + 1;
		}

		// 显示7个数字页
		if (pageCount <= 7) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		} else {
			beginPageIndex = currentPage - 3;
			endPageIndex = currentPage + 3;
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 7;
			}
			if (endPageIndex > pageCount) {
				beginPageIndex = pageCount - 6;
				endPageIndex = pageCount;
			}
		}

	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String toString() {
		return "[Page: currentPage=" + currentPage + ", pageSize=" + pageSize + "]";
	}
}
