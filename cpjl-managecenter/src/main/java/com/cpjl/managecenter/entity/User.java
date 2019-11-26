package com.cpjl.managecenter.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

/**
 * 实体对象：
 */
public class User implements Serializable {

	private static final long serialVersionUID = 7627036881043986212L;

	// ~~~~实体属性
	private Integer id;
	// 用户名
	@NotBlank(message = "请填写用户名")
	private String userName;
	// 真实姓名
	@NotBlank(message = "请填写真实姓名")
	private String realName;
	// 密码
	private String password;
	// 手机号
	@NotBlank(message = "请填写手机号")
	private String mobile;
	// 头像
	private String avartar;
	// 职位
	private String position;
	// 是否启用
	private Boolean isAble;
	// 角色id
	private Integer roleId;
	// 最后一次登录ip
	private String lastLoginIp;
	// 最后一次登录日期
	private java.util.Date lastLoginTime;
	// 登录次数
	private Integer loginTimes;
	// 密码错误次数，超过100次锁定
	private Integer passwordErrorTimes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 用户名
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 真实姓名
	 */
	public String getRealName() {
		return this.realName;
	}

	/**
	 * 真实姓名
	 */
	public void setRealName(String realName) {
		this.realName = realName;
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
	 * 手机号
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * 手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 头像
	 */
	public String getAvartar() {
		return this.avartar;
	}

	/**
	 * 头像
	 */
	public void setAvartar(String avartar) {
		this.avartar = avartar;
	}

	/**
	 * 职位
	 */
	public String getPosition() {
		return this.position;
	}

	/**
	 * 职位
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 是否启用
	 */
	public Boolean getIsAble() {
		return this.isAble;
	}

	/**
	 * 是否启用
	 */
	public void setIsAble(Boolean isAble) {
		this.isAble = isAble;
	}

	/**
	 * 角色id
	 */
	public Integer getRoleId() {
		return this.roleId;
	}

	/**
	 * 角色id
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 最后一次登录ip
	 */
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	/**
	 * 最后一次登录ip
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * 最后一次登录日期
	 */
	public java.util.Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	/**
	 * 最后一次登录日期
	 */
	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 登录次数
	 */
	public Integer getLoginTimes() {
		return this.loginTimes;
	}

	/**
	 * 登录次数
	 */
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	/**
	 * 密码错误次数，超过100次锁定
	 */
	public Integer getPasswordErrorTimes() {
		return this.passwordErrorTimes;
	}

	/**
	 * 密码错误次数，超过100次锁定
	 */
	public void setPasswordErrorTimes(Integer passwordErrorTimes) {
		this.passwordErrorTimes = passwordErrorTimes;
	}
}
