package com.up72.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	public static boolean isFormSubmit(HttpServletRequest request) {
		return "POST".equalsIgnoreCase(request.getMethod());
	}
	
	public static String getUrlFileName(HttpServletRequest request) {
		String uri = request.getRequestURI();
		if (uri == null) {
			return "";
		}
		int index = uri.lastIndexOf("/");
		if (index >= 0) {
			uri = uri.substring(index + 1);
		}
		index = uri.indexOf(".");
		if (index >= 0) {
			uri = uri.substring(0, index);
		}
		
		return uri;
	}
}
