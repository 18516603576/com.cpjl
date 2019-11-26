package com.cpjl.api.dto;

import java.util.List;

import com.cpjl.api.entity.Videocat;

public class VideocatDto extends Videocat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 205995402889206416L;
	private List<VideocatDto> children;

	public List<VideocatDto> getChildren() {
		return children;
	}

	public void setChildren(List<VideocatDto> children) {
		this.children = children;
	}

}
