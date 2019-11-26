package com.cpjl.managecenter.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体对象：
 */
public class File implements Serializable {

	private static final long serialVersionUID = 2890582835746481753L;

	// ~~~~实体属性
	private Long id;
	// 文件名
	private String name;
	// 文件大小
	private Long size;
	// 文件访问地址
	private String url;
	// 文件物理地址
	private String src;
	// 设备id
	private Integer deviceId;
	// 文件扩展名
	private String ext;
	// 文件类型 1为图片 2为文件 3视频
	private Integer type;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 文件名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 文件名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 文件大小
	 */
	public Long getSize() {
		return this.size;
	}

	/**
	 * 文件大小
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * 文件访问地址
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * 文件访问地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 文件物理地址
	 */
	public String getSrc() {
		return this.src;
	}

	/**
	 * 文件物理地址
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * 设备id
	 */
	public Integer getDeviceId() {
		return this.deviceId;
	}

	/**
	 * 设备id
	 */
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * 文件扩展名
	 */
	public String getExt() {
		return this.ext;
	}

	/**
	 * 文件扩展名
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

	/**
	 * 文件类型 1为图片 2为文件 3视频
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 文件类型 1为图片 2为文件 3视频
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
