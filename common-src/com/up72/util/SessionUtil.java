/**
 * session工具类
 */
package com.up72.util;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xes.jtzs.JTZSConstants;

public class SessionUtil {
	
	
	
	/**
	 * 将session put 进 models里
	 * @param request
	 * @param model
	 * @throws ServletException
	 */
	@SuppressWarnings("unchecked")
	public static void putSessionModels(HttpServletRequest request,Map model) throws ServletException{
	        HttpSession session = request.getSession(false);   
	        if (session != null) {
	            for (Enumeration en = session.getAttributeNames(); en.hasMoreElements();) {   
	                String attribute = (String) en.nextElement();   
	                if (model.containsKey(attribute)) {  
	                    throw new ServletException("Cannot expose session attribute '" + attribute +   
	                        "' because of an existing model object of the same name");   
	                }
	                Object attributeValue = session.getAttribute(attribute);
	                model.put(attribute, attributeValue);
	            }   
	        }
	}
	
	/**
	 * 设置手机token
	 */
	public static void setToken(HttpServletRequest request,String token,String loginName){
		HttpSession session = request.getSession(false);
		if(null != session){
			session.setAttribute(JTZSConstants.TOKEN+loginName,token);
			session.setMaxInactiveInterval(-1);
		}
	}
	
	/**
	 * 获得手机token
	 * @return
	 */
	public static String getToken(HttpServletRequest request,String loginName){
		String result = null;
		HttpSession session = request.getSession(false);
		if(null != session){
			result = (String)session.getAttribute(JTZSConstants.TOKEN+loginName);
		}
		return result;
	}
	
}
