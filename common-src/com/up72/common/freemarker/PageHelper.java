package com.up72.common.freemarker;

import javax.servlet.http.HttpServletRequest;

public class PageHelper {
	private HttpServletRequest request;

	public PageHelper(HttpServletRequest request) {
		this.request = request;
	}

	public Object getAttribute(String key) {
		return request.getAttribute(key);
	}

	public void setAttribute(String key, Object value) {
		request.setAttribute(key, value);
	}
}
