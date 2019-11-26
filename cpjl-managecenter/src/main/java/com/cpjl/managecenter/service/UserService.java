package com.cpjl.managecenter.service;

import java.util.Map;

import com.cpjl.managecenter.entity.User;
import com.cpjl.managecenter.model.DataGrid;

/**
 * 服务接口。
 */
public interface UserService {

	/*
	 * 登录时，根据用户名获取用户
	 */
	User getByUserName(String userName);

	/*
	 * 登录时，密码输入错误，passwordErrorTimes+1
	 */
	void updatePasswordErrorTimes(User user);

	/*
	 * 登录成功，更新用户信息
	 */
	void updateUserWhenLoginSuccess(User user);

	/*
	 * 分页
	 */
	DataGrid<User> paging(Map<String, Object> params);

	/*
	 * 批量删除
	 */
	void deleteBatchDo(Long[] id);

	/*
	 * 添加
	 */
	public User insert(User entity);

	/*
	 * 删除一个
	 */
	public void delete(Long id);

	/*
	 * 获取一个
	 */
	User get(Integer id);

	/*
	 * 更新用户
	 */
	int update(User entity);

}
