package com.up72.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.up72.auth.member.model.AuthUser;
import com.up72.auth.model.Product;
import com.up72.base.UserDetails;

public interface IAuthController<T, PK> {

	public abstract Product getCurrentProduct(HttpServletRequest request);

	/**
	 * session登陆
	 * 
	 * @param request
	 */
	public abstract void setSessionLogin(HttpServletRequest request,
			UserDetails user);

	/**
	 * cookie登陆
	 * 
	 * @param request
	 * @param userType
	 *            用户类型[code] CommonConstants.USER_TYPE_*
	 */
	public abstract void setCookieLogin(HttpServletResponse response,
			String value, Integer cookieTime, Integer userType);

	/**
	 * 获取当前前台登录用户
	 * 
	 * @param request
	 * @return
	 */
	public abstract UserDetails getLoginUser(HttpServletRequest request);

	public abstract AuthUser getLoginMember(HttpServletRequest request);

	public abstract AuthUser getAdminLoginMember(HttpServletRequest request);

	/**
	 * 获取当前后台登录用户
	 * 
	 * @param request
	 * @return
	 */
	public abstract UserDetails getAdminLoginUser(HttpServletRequest request);

	/**
	 * 通过session判断是否登陆
	 * 
	 * @param request
	 * @param userType
	 *            用户类型 CommonConstants.MEMBER_TYPE_*
	 */
	public abstract boolean isSessionLogin(HttpServletRequest request,
			Integer userType);

	/**
	 * 通过cookie判断是否登陆
	 * 
	 * @param request
	 * @param userType
	 *            用户类型 CommonConstants.MEMBER_TYPE_*
	 */
	public abstract boolean isCookieLogin(HttpServletRequest request,
			Integer userType);

	/**
	 * 清除session
	 * 
	 * @param request
	 */
	public abstract void clearSessionLogin(HttpServletRequest request);

	/**
	 * 清除Cookie记录
	 * 
	 * @param response
	 */
	public abstract void clearCookieLogin(HttpServletResponse response);

	public abstract void logout(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 设置用户角色
	 * 
	 * @param request
	 * @param roleId
	 */
	public abstract void setSessionRoleId(HttpServletRequest request,
			Long roleId);

	/**
	 * 用户角色
	 * 
	 * @param request
	 * @return
	 */
	public abstract Long getSessionRoleId(HttpServletRequest request);

	/**
	 * 设置用户当前选择的产品
	 * 
	 * @param request
	 * @param productId
	 */
	public abstract void setSessionProductId(HttpServletRequest request,
			String productId);

	/**
	 * 用户当前选择产品
	 * 
	 * @param request
	 * @return
	 */
	public abstract String getSessionProductId(HttpServletRequest request);

	public abstract String getPermissionId(HttpServletRequest request);

	public abstract String getUrl(String url, HttpServletRequest request);

	public abstract void addPermissionAttribute(ModelMap model,
			HttpServletRequest request);

}