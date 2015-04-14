/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */


package com.up72.app.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 用户数据提取跳转接口
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */

/**
 * @author Administrator
 *
 */
public interface IUserController{
	
	/**
	 * 用户用户组
	 * @author bxmen
	 */
	public String userWorkGroup(ModelMap model,@RequestParam("userId") Long userId,@RequestParam("roleId") Long roleId, HttpServletRequest request,
			HttpServletResponse response);
	
	
	/**
	 * 获得该用户所在的部门
	 * @author bxmen
	 */
	public String getWorkGroupMember(ModelMap model, @RequestParam("userId") Long userId);
	
	/**
	 * 根据用户所在的部门获得角色
	 * @author bxmen
	 */
	public String getMemberRoleByWorkGroup(ModelMap model, @RequestParam("userId") Long userId);
	
	
	/**
	 * 修改用户所在用户组（部门）
	 * @author bxmen
	 */
	public void updateWorkGroupMember(Long workGroupId, Long userId);
	/**
	 * 修改用户所拥有角色
	 * @author bxmen
	 */
	public void updateMemberRole(Long roleId, Long userId);
}

