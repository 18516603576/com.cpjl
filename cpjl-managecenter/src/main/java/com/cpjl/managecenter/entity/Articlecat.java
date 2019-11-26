package com.cpjl.managecenter.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 实体对象：
 */
public class Articlecat implements Serializable {

	private static final long serialVersionUID = 2261655918538744383L;

	// ~~~~实体属性
	private Integer id;
	// 栏目名
	@NotBlank(message = "请填写栏目名")
	private String name;
	// 排序
	private String idx;
	// 父id
	private Integer parentid;
	// 1单页 2文字列表 2图片列表
	@NotNull(message = "请选择栏目类型")
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
	 * 1单页 2文字列表 2图片列表
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 1单页 2文字列表 2图片列表
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
