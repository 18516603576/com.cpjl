package com.cpjl.managecenter.entity;

import java.io.Serializable;

 

/**
 * 实体对象：
 */
public class DeviceSource implements Serializable {

	private static final long serialVersionUID = 5957433911006187454L;

	// ~~~~实体属性
	// 设备id
	private Integer deviceId;
	// ip
	private String ip;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 端口
	private Integer port;

	 
	
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
	 * ip
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	 * 用户名
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 端口
	 */
	public Integer getPort() {
		return this.port;
	}

	/**
	 * 端口
	 */
	public void setPort(Integer port) {
		this.port = port;
	}
}
