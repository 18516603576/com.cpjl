package com.cpjl.api.entity;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 实体对象：
 */
public class Article implements Serializable {

	private static final long serialVersionUID = 2753398599682039130L;

	// ~~~~实体属性
	private Long id;
	// 标题
	private String title;
	// 栏目id
	private Integer articlecatid;
	// 缩略图
	private String img;
	// 内容
	private String content;
	// 管理员id
	private Integer userid;
	//
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JSONField(format = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private java.util.Date createtime;
	// 排序
	private Integer idx;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
