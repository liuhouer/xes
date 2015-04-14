package com.up72.auth;

import static com.up72.common.CommonUtils.cookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.up72.User;
import com.up72.app.auth.service.UserManager;
import com.up72.base.UserDetails;
import com.up72.framework.util.holder.ApplicationContextHolder;

public class UserUtils {
	
	/**
	 * 用户是否登陆
	 * @param request
	* @param userType 用户类型 AuthConstants.MEMBER_TYPE_*
	 * @return
	 */
	public  boolean isLogin(HttpServletRequest request, Integer userType){
		boolean result = true;
		Object object = getSessionUser(request, userType);
		if(null == object){
			object = getLoginName(request, userType);
		}
		if(null == object){
			result = false;
		}
		return result;
	}
	
	/**
	 * 获取用户登陆信息
	 * @param request
	 * @param userType 用户登陆类型 AuthConstants.MEMBER_TYPE*
	 * @return
	 */
	public  UserDetails getSessionUser(HttpServletRequest request, Integer userType){
		HttpSession session = request.getSession();
		Object object = session.getAttribute(AuthConstants.SESSION_ADMIN_MEMBER_KEY);
		if(object != null){
			return (UserDetails)object;
		}else{
			return null;
		}
	}
	
	/**
	 * 设置用户登陆SESSION
	 * @param request
	 * @param user
	 * @param userType 用户类型 AuthConstants.MEMBER_TYPE_*
	 */
	public  void setSessionUser(HttpServletRequest request,UserDetails user){
		HttpSession session = request.getSession();
		//清除当前登陆SESSION
		clearSessionLogin(request);
		if(AuthConstants.MEMBER_TYPE_ADMIN.equals(user.getCode())){
			/** 后台用户 */
			session.setAttribute(AuthConstants.SESSION_ADMIN_MEMBER_KEY, user);
		}
		session.setAttribute(AuthConstants.SESSION_ADMIN_MEMBER_KEY, user);
	}

	
	/**
	 * 清除用户登陆SESSION
	 * @param request
	 */
	public  void clearSessionLogin(HttpServletRequest request){
		HttpSession session = request.getSession();
		try {
			session.removeAttribute(AuthConstants.SESSION_ADMIN_MEMBER_KEY);
		} catch (Exception e) {
			throw new AuthException(e);
		}
	}
	/**
	 * 用户角色
	 * @param request
	 * @return
	 */
	public  Long getSessionRoleId(HttpServletRequest request){
		HttpSession session = request.getSession();
		Long roleId = (Long)session.getAttribute(AuthConstants.SESSION_ROLE_KEY);
		return roleId;
	}
	
	/**
	 * 设置用户角色
	 * @param request
	 * @param roleId
	 */
	public  void setSessionRoleId(HttpServletRequest request, Long roleId){
		HttpSession session = request.getSession();
		session.removeAttribute(AuthConstants.SESSION_ROLE_KEY);
		session.setAttribute(AuthConstants.SESSION_ROLE_KEY, roleId);
	}
	
	/**
	 * 当前选择产品
	 * @param request
	 * @return
	 */
	public  String getSessionProductId(HttpServletRequest request){
		HttpSession session = request.getSession();
		String result = (String)session.getAttribute(AuthConstants.SESSION_PRODUCT_KEY);
		return result;
	}
	

	/**
	 * 设置用户当前选择产品
	 * @param request
	 * @param productId
	 */
	public  void setSessionProductId(HttpServletRequest request, String productId){
		HttpSession session = request.getSession();
		session.removeAttribute(AuthConstants.SESSION_PRODUCT_KEY);
		session.setAttribute(AuthConstants.SESSION_PRODUCT_KEY, productId);
	}
	
	/**
	 * 清除用户角色SESSION
	 * @param request
	 */
	public  void clearSessionRoleId(HttpServletRequest request){
		HttpSession session = request.getSession();
		try {
			session.removeAttribute(AuthConstants.SESSION_ROLE_KEY);
		} catch (Exception e) {
			throw new AuthException(e);
		}
	}
	
	/**
	 * 清除用户当前产品SESSION
	 * @param request
	 */
	public  void clearSessionProductId(HttpServletRequest request){
		HttpSession session = request.getSession();
		try {
			session.removeAttribute(AuthConstants.SESSION_PRODUCT_KEY);
		} catch (Exception e) {
			throw new AuthException(e);
		}
	}
	

	/**
	 * 根据登陆用户key和用户名判断用户是否登陆
	 * 
	 * @param request
	 * @param userType
	 *            用户登陆类型 AuthConstants.USER_TYPE*
	 * @return
	 */
	public  boolean isCookieLogin(HttpServletRequest request, Integer userType) {
		String userName = getLoginName(request, userType);

		if (userName != null && !"".equals(userName)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 清除用户Cookie
	 * 
	 * @param response
	 * @param request
	 */
	public  void clearCookieLogin(HttpServletResponse response) {
		cookieUtil.setCookie(response, AuthConstants.COOKIE_ADMIN_MEMBER_KEY, "", 0);
	}
	
	/**
	 * 获取用户登录名称
	 * 
	 * @param request
	 * @param userType
	 *            用户登陆类型 AuthConstants.MEMBER_TYPE*
	 * @return
	 */
	public  String getLoginName(HttpServletRequest request,
			Integer userType) {
		String userName = cookieUtil.getString(request,
					AuthConstants.COOKIE_ADMIN_MEMBER_KEY);
		return userName;
	}
	
	/**
	 * 获取用户登录名称
	 * 
	 * @param request
	 * @param userType
	 *            用户登陆类型 AuthConstants.MEMBER_TYPE*
	 * @return
	 */
	public  String getNameByid(Long id ) {
		UserManager userManager = (UserManager)ApplicationContextHolder.getBean("userManager");
		String userName = userManager.getNameById(id);
		return userName;
	}

	/**
	 * 用户登陆设置COOKIE
	 * 
	 * @param response
	 * @param value
	 * @param seconds
	 *            秒
	 * @param userType
	 *            用户登陆类型 AuthConstants.MEMBER_TYPE*
	 */
	public  void setLoginName(HttpServletResponse response, String value,
			int seconds, Integer userType) {
		// 清除当前登陆用户
		clearCookieLogin(response);
		cookieUtil.setCookie(response, AuthConstants.COOKIE_ADMIN_MEMBER_KEY, value, seconds);
	}
}	
