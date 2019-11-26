package com.cpjl.managecenter.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.cpjl.managecenter.model.BaseResult;

/**
 * 文件操作工具类
 */
public class FileUtil {
	/**
	 * 读取文件内容为二进制数组
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] read2ByteArray(String filePath) throws IOException {

		InputStream in = new FileInputStream(filePath);
		byte[] data = inputStream2ByteArray(in);
		in.close();

		return data;
	}

	/**
	 * 流转二进制数组
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private static byte[] inputStream2ByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
			out.write(buffer, 0, n);
		}
		return out.toByteArray();
	}

	public static void save(String filePath, String fileName, byte[] content) {
		try {
			File filedir = new File(filePath);
			if (!filedir.exists()) {
				filedir.mkdirs();
			}
			File file = new File(filedir, fileName);
			OutputStream os = new FileOutputStream(file);
			os.write(content, 0, content.length);
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 是否图片扩展名
	 */
	public static boolean isImageExt(String ext) {
		boolean flag = false;
		String[] allowedExt = { ".jpg", ".jpeg", ".png", ".gif", ".bmp" };
		for (String allowed : allowedExt) {
			if (allowed.equals(ext)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/*
	 * 获取文件扩展名 如：.jpg
	 */
	public static String getExt(String file) {
		int pos = file.lastIndexOf(".");
		String ext = file.substring(pos).toLowerCase();
		return ext;
	}

	/*
	 * 如果文件夹不存在，则创建所有文件夹，子文件、父文件夹
	 */
	public static void mkdirs(String realFilePath) {
		int pos = realFilePath.lastIndexOf(File.separator);
		String realPath = realFilePath.substring(0, pos);
		new File(realPath).mkdirs();
	}

	/*
	 * 判断远程文件是否存在
	 */
	public static boolean remoteFileIsExists(String source) {
		try {
			URL url = new URL(source);
			URLConnection uc = url.openConnection();
			InputStream in = uc.getInputStream();
			if (source.equalsIgnoreCase(uc.getURL().toString()))
				in.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * url http://www.baidu.com/ss/a.docx
	 * 
	 * file E:\china\a.docx
	 * 
	 * 获取文件名称
	 * 
	 */
	public static String getFileName(String file) {
		int end = file.lastIndexOf(".");
		int start = 0;
		String fileName = "";
		int pos_sep_file = file.lastIndexOf(File.separator);
		int pos_sep_url = file.lastIndexOf("/");
		if (pos_sep_file > 0) {
			start = pos_sep_file + 1;
		} else if (pos_sep_url > 0) {
			start = pos_sep_url + 1;
		}

		if (end > 0) {
			fileName = file.substring(start, end);
		}
		return fileName;
	}

	/*
	 * url http://www.baidu.com/ss/a.docx
	 * 
	 * 返回远程文件的base64编码
	 * 
	 */
	public static String urltobase64(String fileUrl) {
		byte[] data = null;
		try {
			// 创建URL
			URL url = new URL(fileUrl);
			// 创建链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();
			int size = inStream.available();
			data = readStream(inStream);

			System.out.println("urltobase64:" + size);

		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		Base64 encoder = new Base64();
		// 返回Base64编码过的字节数组字符串
		return encoder.encodeAsString(data);
	}

	/**
	 * 读取流
	 * 
	 * @param inStream
	 * @return 字节数组
	 * @throws IOException
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inStream) throws IOException {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

	/*
	 * 判断文件是否超过大小
	 * 
	 * 
	 * @param Long size 当前文件大小，不能为0
	 * 
	 * @param Long maxSize 最大大小，maxSize=0即不受限制
	 * 
	 */
	public static BaseResult<Object> isExceedingInSize(Long size, Long maxSize) {
		BaseResult<Object> baseResult = new BaseResult<Object>();

		Boolean flag = false;
		if (size == null || size == 0) {
			throw new IllegalArgumentException("size,当前文件大小为0");
		}
		if (maxSize == null || maxSize == 0) {
			flag = true;
		} else if (size <= maxSize) {
			flag = true;
		}

		if (flag) {
			baseResult.setResult(true);
			baseResult.setMessage("success");
		} else {
			baseResult.setResult(false);
			baseResult.setMessage("文件超过大小");
		}
		return baseResult;
	}

	/*
	 * 判断是否为允许的ext
	 * 
	 * @param String ext 当前扩展名 如：.jpg
	 * 
	 * @param List<String> allowExt 允许的ext列表
	 */
	public static BaseResult<Object> isAllowedExt(String ext, List<String> allowedExt) {
		BaseResult<Object> baseResult = new BaseResult<Object>();
		Boolean flag = false;
		if (StringUtils.isBlank(ext)) {
			throw new IllegalArgumentException("ext,不能为空");
		}
		if (null == allowedExt || allowedExt.size() == 0) {
			throw new IllegalArgumentException("allowedExt,不能为空");
		}

		for (String one : allowedExt) {
			if (ext.equals(one)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			baseResult.setResult(true);
			baseResult.setMessage("允许上传");
		} else {
			baseResult.setResult(false);
			baseResult.setMessage("请上传正确的文件类型");
		}
		return baseResult;
	}

	public static ByteArrayOutputStream write2ByteArrayOutputStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = is.read(buffer)) > -1) {
			baos.write(buffer, 0, len);
		}
		baos.flush();
		is.close();
		is = null;
		return baos;
	}
}
