package com.up72.util;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.up72.common.CommonConstants;

public class TokenUtil {
	
	private static TokenUtil instance = new TokenUtil();
	
	private TokenUtil() {
	}

	public static TokenUtil getInstance() {
		return instance;
	}

	public synchronized boolean isTokenValid(HttpServletRequest request) {
		// 没有session,判为非法
		HttpSession session = request.getSession(false);
		if (session == null)
			return false;

		// session中不含token,
		// 说明form被提交过后执行了resetToken()清除了token
		// 判为非法
		String stoken = (String) session.getAttribute(CommonConstants.FORM_SUBMIT_TOKEN);
		if (stoken == null)
			return false;

		// request请求参数中不含token,
		// 判为非法
		String rtoken = request.getParameter(CommonConstants.FORM_SUBMIT_TOKEN);
		if (rtoken == null)
			return false;

		// request请求中的token与session中保存的token不等,判为非法
		return stoken.equals(rtoken);
	}

	/*
	 * 重新设置token，当页面被请求后，将session中的token属性去除
	 */
	public synchronized void destroyToken(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(CommonConstants.FORM_SUBMIT_TOKEN);
		}
	}

	/*
	 * 为请求新建一个token标记，此标记由一个随机的double数toString形成，并把字符值存入session中
	 */
	public synchronized void createToken(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Random rand = new Random();
		Double d = rand.nextDouble();
		session.setAttribute(CommonConstants.FORM_SUBMIT_TOKEN, d.toString());
	}
	
	public synchronized String writeHidden(HttpServletRequest request, HttpServletResponse response) throws IOException{
		createToken(request);
		StringBuffer result = new StringBuffer("<input type='hidden' name='"+CommonConstants.FORM_SUBMIT_TOKEN+"' id='"+CommonConstants.FORM_SUBMIT_TOKEN+"'");
		HttpSession session = request.getSession(false);
		String submitToken = (String) session.getAttribute(CommonConstants.FORM_SUBMIT_TOKEN);
		if (submitToken == null){
			submitToken = "";
		}
		if(!"".equals(submitToken)){
			result = result.append(" value='" + submitToken + "'");
		}
		result = result.append(" />");
		return result.toString();
	}
}