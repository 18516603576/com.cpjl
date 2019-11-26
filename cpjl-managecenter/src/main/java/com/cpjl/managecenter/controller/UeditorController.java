package com.cpjl.managecenter.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.ConfigManager;
import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileTypeMap;
import com.baidu.ueditor.define.MultiState;
import com.baidu.ueditor.define.State;
import com.cpjl.managecenter.entity.DeviceSource;
import com.cpjl.managecenter.entity.File;
import com.cpjl.managecenter.model.DataGrid;
import com.cpjl.managecenter.model.Page;
import com.cpjl.managecenter.service.DeviceSourceService;
import com.cpjl.managecenter.service.FileService;

@Controller
@RequestMapping(value = "/static/ueditor/jsp/", produces = "application/json;charset=UTF-8")
public class UeditorController {
	private static final Logger logger = LoggerFactory.getLogger(UeditorController.class);

	@Resource
	private FileService fileService;
	@Resource
	private DeviceSourceService deviceSourceService;

	/**
	 * 添加页面属性
	 * 
	 * @param
	 * @return
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", method = { RequestMethod.GET, RequestMethod.POST })
	public Object upload(HttpServletRequest request, HttpServletResponse response)
			throws JSONException, UnsupportedEncodingException {
		ServletContext application = request.getServletContext();
		String rootPath = application.getRealPath("/");

		String action = request.getParameter("action");

		String deviceIdStr = request.getParameter("deviceid");
		Integer deviceId = 0;
		if (deviceIdStr != null) {
			deviceId = Integer.valueOf(deviceIdStr);
			DeviceSource deviceSource = deviceSourceService.getByDeviceId(deviceId);
			request.setAttribute("deviceSource", deviceSource);
		}

		int actionCode = ActionMap.getType(action);
		String contextPath = request.getContextPath();
		Map<String, Object> conf = null;
		State state = null;
		String result = null;
		JSONObject json = null;
		int start = 0;
		ConfigManager configManager = ConfigManager.getInstance(rootPath, contextPath, request.getRequestURI());

		switch (actionCode) {
		case ActionMap.CONFIG:
			result = new ActionEnter(request, rootPath).exec();
			break;
		case ActionMap.LIST_IMAGE:
			conf = configManager.getConfig(actionCode);
			start = this.getStartIndex(request);
			state = this.getFileList(start, conf.get("managerUrlPrefix").toString(), FileTypeMap.IMAGE);
			result = state.toJSONString();
			break;
		case ActionMap.LIST_FILE:
			conf = configManager.getConfig(actionCode);
			start = this.getStartIndex(request);
			state = this.getFileList(start, conf.get("managerUrlPrefix").toString(), FileTypeMap.FILE);
			result = state.toJSONString();
			break;

		case ActionMap.UPLOAD_IMAGE:
		case ActionMap.UPLOAD_SCRAWL:
			result = new ActionEnter(request, rootPath).exec();
			// 保存到数据库
			// 添加属性 filesize fileType,更新到数据库

			json = JSON.parseObject(result);
			this.insertDB(json, FileTypeMap.IMAGE);
			break;
		case ActionMap.UPLOAD_VIDEO:
			result = new ActionEnter(request, rootPath).exec();
			// 保存到数据库
			// 添加属性 filesize fileType,更新到数据库

			json = JSON.parseObject(result);
			this.insertDB(json, FileTypeMap.VIDEO);
			break;
		case ActionMap.UPLOAD_FILE:

			result = new ActionEnter(request, rootPath).exec();
			// 保存到数据库
			// 添加属性 filesize fileType,更新到数据库

			json = JSON.parseObject(result);
			this.insertDB(json, FileTypeMap.FILE);
			break;

		case ActionMap.CATCH_IMAGE:
			result = new ActionEnter(request, rootPath).exec();
			// 保存到数据库
			// 添加属性 filesize fileType,更新到数据库

			json = JSON.parseObject(result);

			this.insertDB(json, FileTypeMap.IMAGE);
			break;

		}

		return result;

	}

	private State getFileList(int start, String rootPath, Integer fileType) {
		File entity = new File();
		entity.setType(fileType);
		Page page = new Page();
		page.setCurrentPage(start / page.getLimit() + 1);

		DataGrid<File> dataGrid = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		dataGrid = fileService.ueditorPaging(params, page);

		MultiState state = new MultiState(true);
		state.putInfo("start", start);
		state.putInfo("total", dataGrid.getNumRows());

		for (File f : dataGrid.getItems()) {
			BaseState fileState = new BaseState(true);
			fileState.putInfo("url", f.getUrl().replaceAll(rootPath, "/"));
			state.addState(fileState);
		}
		return state;

	}

	private int getStartIndex(HttpServletRequest request) {
		String start = request.getParameter("start");
		try {
			return Integer.parseInt(start);
		} catch (Exception e) {
			return 0;
		}
	}

	/*
	 * 根据返回的结果，保存到数据库
	 */
	private void insertDB(JSONObject json, Integer fileType) {
		if ("SUCCESS".equals(json.getString("state"))) {

			if (json.containsKey("list")) {
				JSONArray jsonArray = (JSONArray) json.get("list");
				for (Object obj : jsonArray) {
					JSONObject jsonObj = (JSONObject) obj;
					if ("SUCCESS".equals(jsonObj.getString("state"))) {
						File file = new File();

						file.setSize(jsonObj.getLong("size"));
						file.setName(jsonObj.getString("source"));
						file.setUrl(jsonObj.getString("url"));
						file.setExt(json.getString("type"));
						file.setType(fileType);
						fileService.insert(file);
					}
				}
			} else {
				File file = new File();
				file.setSize(json.getLong("size"));
				file.setName(json.getString("original"));
				file.setUrl(json.getString("url"));
				file.setExt(json.getString("type"));
				file.setType(fileType);
				fileService.insert(file);
			}

		}
	}
}
