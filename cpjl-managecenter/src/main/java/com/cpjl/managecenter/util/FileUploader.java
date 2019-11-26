package com.cpjl.managecenter.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cpjl.managecenter.dto.UploadResultDto;

public class FileUploader {
	private static final Logger logger = LoggerFactory.getLogger(FileUploader.class);
	private static int BUFFER_SIZE = 8192;

	public static UploadResultDto saveFileByInputStream(InputStream is, String path, String rootPath) {
		UploadResultDto result = new UploadResultDto();
		File tmpFile = getTmpFile();

		byte[] dataBuf = new byte[2048];
		BufferedInputStream bis = new BufferedInputStream(is, BUFFER_SIZE);

		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile), BUFFER_SIZE);

			int count = 0;
			while ((count = bis.read(dataBuf)) != -1) {
				bos.write(dataBuf, 0, count);
			}
			bos.flush();
			bos.close();

			result = saveTmpFile(tmpFile, path, rootPath);
			if (!result.getResult()) {
				tmpFile.delete();
			}

			return result;
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		result.setResult(false);
		result.setMessage("IOException");
		return result;
	}

	private static File getTmpFile() {
		File tmpDir = FileUtils.getTempDirectory();
		String tmpFileName = (Math.random() * 10000 + "").replace(".", "");
		logger.info("tmpfile:" + tmpDir + "_" + tmpFileName);
		return new File(tmpDir, tmpFileName);
	}

	private static UploadResultDto saveTmpFile(File tmpFile, String path, String rootPath) {
		UploadResultDto result = new UploadResultDto();
		File targetFile = new File(rootPath + path);

		if (targetFile.canWrite()) {
			result.setResult(false);
			result.setMessage("无写入权限");
			return result;
		}
		try {
			FileUtils.moveFile(tmpFile, targetFile);
		} catch (IOException e) {
			result.setResult(false);
			result.setMessage("IOException:" + e.getMessage());
			return result;
		}

		result.setResult(true);
		result.setMessage("SUCCESS");

		result.setFileSize(targetFile.length());
		result.setFileName(targetFile.getName());
		String imageDomain = PropertiesUtil.getProperty("app.upload.domain");
		result.setUrl(imageDomain + path);
		return result;

	}

	public static String getRootPath(String rootPath, String contextPath) {

		rootPath = rootPath.replace("\\", "/");

		if (contextPath.length() > 0) {
			rootPath = rootPath.substring(0, rootPath.length() - contextPath.length());
		}

		return rootPath;
	}

	public static UploadResultDto saveVideoByInputStream(InputStream is, String path, String rootPath, String uuid,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UploadResultDto result = new UploadResultDto();
		File tmpFile = getTmpFile();

		byte[] dataBuf = new byte[2048];
		BufferedInputStream bis = new BufferedInputStream(is, BUFFER_SIZE);

		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile), BUFFER_SIZE);
			long transferredBytes = 0;
			int count = 0;
			while ((count = bis.read(dataBuf)) != -1) {
				bos.write(dataBuf, 0, count);
				bos.flush();
				transferredBytes = transferredBytes + count;
				session.setAttribute(uuid + "transferredBytes", transferredBytes);
			}
			bos.flush();
			bos.close();

			result = saveTmpFile(tmpFile, path, rootPath);
			if (!result.getResult()) {
				tmpFile.delete();
			}

			return result;
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		result.setResult(false);
		result.setMessage("IOException");
		return result;
	}
}
