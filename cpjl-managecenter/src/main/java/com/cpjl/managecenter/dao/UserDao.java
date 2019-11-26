package com.cpjl.managecenter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cpjl.managecenter.entity.User;

@Mapper
public interface UserDao {
	/*
	 * 登录时，根据用户名获取用户
	 */
	User getByUserName(String userName);

	/*
	 * 分页
	 */
	Integer paging_count(Map<String, Object> params);

	List<User> paging_datas(Map<String, Object> params);

	/*
	 * 删除一个
	 */
	int delete(Long id);

	/*
	 * 更新用户
	 */
	int update(User user);

	/*
	 * 添加用户
	 */
	int insert(User entity);

	/*
	 * 获取一个
	 */
	User get(@Param("id") Integer id);

}
