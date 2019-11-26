package com.cpjl.managecenter.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 实体对象：
 */
public class Article implements Serializable {

	private static final long serialVersionUID = 2753398599682039130L;

	// ~~~~实体属性
	private Integer id;
	// 标题
	@NotBlank(message = "请填写标题")
	private String title;
	// 栏目id
	@NotNull(message = "请选择栏目")
	private Integer articlecatid;
	// 缩略图
	private String img;
	// 内容
	private String content;
	// 管理员id
	private Integer userid;
	//
	private java.util.Date createtime;
	// 排序
	private Integer idx;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 标题
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 栏目id
	 */
	public Integer getArticlecatid() {
		return this.articlecatid;
	}

	/**
	 * 栏目id
	 */
	public void setArticlecatid(Integer articlecatid) {
		this.articlecatid = articlecatid;
	}

	/**
	 * 缩略图
	 */
	public String getImg() {
		return this.img;
	}

	/**
	 * 缩略图
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * 内容
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 管理员id
	 */
	public Integer getUserid() {
		return this.userid;
	}

	/**
	 * 管理员id
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
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

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}
}
