package com.cpjl.managecenter.service;

import com.cpjl.managecenter.entity.DeviceSource;

/**
 * 服务接口。
 */
public interface DeviceSourceService {

	/*
	 * 根据deviceId查找
	 */
	DeviceSource getByDeviceId(Integer deviceId);

}
