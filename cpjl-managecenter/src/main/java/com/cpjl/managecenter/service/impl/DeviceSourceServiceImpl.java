package com.cpjl.managecenter.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cpjl.managecenter.dao.DeviceSourceDao;
import com.cpjl.managecenter.entity.DeviceSource;
import com.cpjl.managecenter.service.DeviceSourceService;

@Service("deviceSourceService")
public class DeviceSourceServiceImpl implements DeviceSourceService {

	@Resource
	private DeviceSourceDao deviceSourceDao;

	@Override
	public DeviceSource getByDeviceId(Integer deviceId) {
		// TODO 自动生成的方法存根
		return deviceSourceDao.getByDeviceId(deviceId);
	}
}
