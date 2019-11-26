package com.cpjl.managecenter.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cpjl.managecenter.dao.UserDao;
import com.cpjl.managecenter.entity.User;
import com.cpjl.managecenter.model.DataGrid;
import com.cpjl.managecenter.service.UserService;
import com.cpjl.managecenter.util.PropertiesUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	private String md5key = PropertiesUtil.getProperty("app.user.md5key");;

	private String defaultPassword = PropertiesUtil.getProperty("app.user.defaultPassword");;

	@Override
	public User getByUserName(String userName) {
		// TODO 自动生成的方法存根
		return userDao.getByUserName(userName);
	}

	@Override
	public void updatePasswordErrorTimes(User user) {
		// TODO 自动生成的方法存根
		user.setPasswordErrorTimes(user.getPasswordErrorTimes() + 1);
		userDao.update(user);
	}

	@Override
	public void updateUserWhenLoginSuccess(User user) {
		// TODO 自动生成的方法存根
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String ip = request.getRemoteAddr();

		user.setLastLoginTime(new Date());
		user.setLastLoginIp(ip);
		user.setPasswordErrorTimes(0);
		user.setLoginTimes(user.getLoginTimes() + 1);
		userDao.update(user);

	}

	@Override
	public DataGrid<User> paging(Map<String, Object> params) {
		// TODO 自动生成的方法存根

		Integer numRows = userDao.paging_count(params);
		List<User> rows = userDao.paging_datas(params);

		return new DataGrid<User>(rows, numRows);

	}

	@Override
	@Transactional
	public void deleteBatchDo(Long[] id) {
		// TODO 自动生成的方法存根
		for (Long i : id) {
			this.delete(i);
		}
	}

	/*
	 * 删除用户，如果是保留用户则报错
	 * 
	 */
	@Override
	public void delete(Long id) {
		// TODO 自动生成的方法存根
		if (id == 1) {
			throw new IllegalArgumentException("此为保留用户");
		} else {
			userDao.delete(id);
		}
	}

	/*
	 * 添加用户
	 */
	@Override
	public User insert(User entity) {
		// 默认密码
		String md5Password = DigestUtils.md5Hex(md5key + defaultPassword);
		entity.setPassword(md5Password);
		// 字段默认值
		entity.setRoleId(0);
		entity.setLoginTimes(0);
		entity.setPasswordErrorTimes(0);
		int rows = userDao.insert(entity);
		return entity;
	}

	@Override
	public User get(Integer id) {
		// TODO Auto-generated method stub
		return userDao.get(id);
	}

	@Override
	public int update(User entity) {
		// TODO Auto-generated method stub
		return userDao.update(entity);
	}

}
