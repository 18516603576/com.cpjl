package com.cpjl.managecenter.dto;

import org.hibernate.validator.constraints.NotBlank;

public class LoginUserDto {
	@NotBlank(message = "请填写用户名")
	private String userName;
	@NotBlank(message = "请填写密码")
	private String password;
	@NotBlank(message = "请填写验证码")
	private String code;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
