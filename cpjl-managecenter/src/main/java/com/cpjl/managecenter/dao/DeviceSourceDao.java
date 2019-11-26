package com.cpjl.managecenter.dao;

import org.apache.ibatis.annotations.Mapper;

import com.cpjl.managecenter.entity.DeviceSource;

@Mapper
public interface DeviceSourceDao {

	/*
	 * 根据deviceId查找
	 */
	DeviceSource getByDeviceId(Integer deviceId);

}
