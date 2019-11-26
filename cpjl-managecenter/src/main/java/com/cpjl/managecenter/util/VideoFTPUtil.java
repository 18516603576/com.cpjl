package com.cpjl.managecenter.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import com.cpjl.managecenter.entity.DeviceSource;

public class VideoFTPUtil {
	/**
	 * 文件上传
	 * 
	 * 如果文件夹不存在，则创建
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean put(String filePath, InputStream is, FTPClientTemplate ftp, HttpServletRequest request,
			String sessionName) {
		String folderPath = filePath.substring(0, filePath.lastIndexOf("/"));
		ftp.mkdir(folderPath);
		boolean result = ftp.put(filePath, is, null, request, sessionName);
		return result;
	}

	/**
	 * 删除ftp文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean delete(String filePath, FTPClientTemplate ftp) {
		boolean ret = ftp.delete(filePath);
		return ret;
	}

	/*
	 * 根据设备所在主机的ftp信息，实例化一个ftp客户端对象
	 * 
	 * @param DeviceSource deviceSource 设备ftp信息
	 */
	public static FTPClientTemplate getFTPClientTemplate(DeviceSource deviceSource) {
		FTPClientTemplate ftp = new FTPClientTemplate();
		ftp.setHost(deviceSource.getIp());
		ftp.setPort(deviceSource.getPort());
		ftp.setUsername(deviceSource.getUsername());
		ftp.setPassword(deviceSource.getPassword());
		ftp.setBinaryTransfer(false);
		ftp.setPassiveMode(false);
		ftp.setEncoding("utf-8");
		return ftp;
	}

	/*
	 * 返回一个默认ftp客户端对象
	 */
	private static FTPClientTemplate getFTPClientTemplate() {
		FTPClientTemplate ftp = new FTPClientTemplate();
		ftp.setHost(PropertiesUtil.getProperty("ftp.host"));
		ftp.setPort(Integer.parseInt(PropertiesUtil.getProperty("ftp.port")));
		ftp.setUsername(PropertiesUtil.getProperty("ftp.username"));
		ftp.setPassword(PropertiesUtil.getProperty("ftp.password"));
		ftp.setBinaryTransfer(false);
		ftp.setPassiveMode(false);
		ftp.setEncoding("utf-8");
		return ftp;

	}

	/**
	 * 下载ftp文件
	 * 
	 * @param url
	 *            请求的路径
	 * @param filePath
	 *            文件将要保存的目录
	 * @return
	 */
	public static File download(String url, String filePath, String fileName) {
		System.out.println("fileName---->" + filePath);
		// 创建不同的文件夹目录
		File file = new File(filePath);
		// 判断文件夹是否存在
		if (!file.exists()) {
			// 如果文件夹不存在，则创建新的的文件夹
			file.mkdirs();
		}
		FileOutputStream fileOut = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;

		try {
			// 建立链接
			URL httpUrl = new URL(url);
			conn = (HttpURLConnection) httpUrl.openConnection();
			// 以Post方式提交表单，默认get方式
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			// post方式不能使用缓存
			conn.setUseCaches(false);
			// 连接指定的资源
			conn.connect();
			// 获取网络输入流
			inputStream = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			// 判断文件的保存路径后面是否以/结尾
			if (!filePath.endsWith("/")) {
				filePath += "/";
			}
			// 写入到文件（注意文件保存路径的后面一定要加上文件的名称）
			fileOut = new FileOutputStream(filePath + fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fileOut);
			byte[] buf = new byte[4096];
			int length = bis.read(buf);
			// 保存文件
			while (length != -1) {
				bos.write(buf, 0, length);
				length = bis.read(buf);
			}
			bos.close();
			bis.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
}
