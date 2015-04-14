package com.up72.common.excel;

import java.io.Serializable;

/**
 * 导入导出系统配置javabean
 * 
 * @author wqtan
 */

public class ActionConfig implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String action;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
