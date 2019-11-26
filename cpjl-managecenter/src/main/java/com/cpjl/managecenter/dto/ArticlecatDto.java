package com.cpjl.managecenter.dto;

import java.util.List;

import com.cpjl.managecenter.entity.Articlecat;

public class ArticlecatDto extends Articlecat {

	private static final long serialVersionUID = -8696991700283241495L;

	private List<ArticlecatDto> children;

	public List<ArticlecatDto> getChildren() {
		return children;
	}

	public void setChildren(List<ArticlecatDto> children) {
		this.children = children;
	}

}
