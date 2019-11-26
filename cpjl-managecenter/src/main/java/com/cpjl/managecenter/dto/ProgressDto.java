package com.cpjl.managecenter.dto;

import java.io.Serializable;

public class ProgressDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6727631991615791472L;
	/*
	 * 已传输字节byte
	 */
	private Long transferredBytes;
	/*
	 * 文件总大小
	 */
	private Long totalBytes;

	public Long getTransferredBytes() {
		return transferredBytes;
	}

	public void setTransferredBytes(Long transferredBytes) {
		this.transferredBytes = transferredBytes;
	}

	public Long getTotalBytes() {
		return totalBytes;
	}

	public void setTotalBytes(Long totalBytes) {
		this.totalBytes = totalBytes;
	}

}
