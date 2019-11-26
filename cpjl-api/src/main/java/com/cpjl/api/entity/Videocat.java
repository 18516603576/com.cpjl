package com.cpjl.api.entity;

import java.io.Serializable;

/**
 * 实体对象：
 */
public class Videocat implements Serializable {

	private static final long serialVersionUID = 6785653260530649175L;

	// ~~~~实体属性
	private Integer id;
	// 栏目名
	private String name;
	// 排序
	private String idx;
	// 父id
	private Integer parentid;
	// 2文字列表 3图片列表
	private Integer type;
	//
	private java.util.Date createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 栏目名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 栏目名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 排序
	 */
	public String getIdx() {
		return this.idx;
	}

	/**
	 * 排序
	 */
	public void setIdx(String idx) {
		this.idx = idx;
	}

	/**
	 * 父id
	 */
	public Integer getParentid() {
		return this.parentid;
	}

	/**
	 * 父id
	 */
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	/**
	 * 2文字列表 3图片列表
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 2文字列表 3图片列表
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 
	 */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	/**
	 * 
	 */
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
}
