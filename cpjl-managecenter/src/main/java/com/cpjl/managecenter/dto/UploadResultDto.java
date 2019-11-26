package com.cpjl.managecenter.dto;

import java.io.Serializable;

public class UploadResultDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4672438052125199595L;
	// 原始文件名
	private String originalFileName;
	// 上传后的文件名
	private String fileName;
	// 文件类型 1:图片 2：文件 3：视频
	private Integer fileType;
	// 文件扩展名
	private String fileExt;
	// 文件大小
	private Long fileSize;
	// 文件访问地址
	private String url;

	// 上传结果
	private Boolean result;
	// 错误编码
	private int errorCode;
	// 错误信息
	private String message;

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

}
