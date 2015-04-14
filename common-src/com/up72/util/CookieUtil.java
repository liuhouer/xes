/**
 * cookie工具类
 */
package com.up72.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CookieUtil {

	public static Logger logger = Logger.getLogger(sun.reflect.Reflection
			.getCallerClass(1));

	/**
	 * 写数据到cookies中
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param seconds
	 */
	public  void setCookie(HttpServletResponse response, String key,
			String value, int seconds) {
		try {
			logger.debug("key:" + key);
			logger.debug("value:" + URLEncoder.encode(value, "UTF-8"));
			Cookie cookie = new Cookie(key, URLEncoder.encode(value, "UTF-8"));
			cookie.setMaxAge(seconds);
			// cookie.setDomain(".192.117");
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			logger.error("用户登陆写数据到cookies中出错!", e);
		}
	}

	/**
	 * 通过key得到cookies中的value
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public  String getString(HttpServletRequest request, String key) {
		String value = null;
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				logger.info("cookies.length" + cookies.length);
				logger.info("cookies[i].getName()" + cookies[i].getName());
				logger.info("cookies[i].getValue()" + cookies[i].getValue());
				if (cookies[i].getName().equals(key)) {
					cookie = cookies[i];
				}
			}

		}
		if (cookie != null)
			value = cookie.getValue();
		if (value == null)
			value = "";
		try {
			value = URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("通过key得到cookies中的value出错!", e);
		}
		return value;
	}


}
