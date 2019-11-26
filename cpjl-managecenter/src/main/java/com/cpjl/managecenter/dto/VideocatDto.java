package com.cpjl.managecenter.dto;

import java.util.List;

import com.cpjl.managecenter.entity.Videocat;

public class VideocatDto extends Videocat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4542151225133718783L;

	private List<VideocatDto> children;

	public List<VideocatDto> getChildren() {
		return children;
	}

	public void setChildren(List<VideocatDto> children) {
		this.children = children;
	}

}
