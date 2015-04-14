/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */


package com.up72.app.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

import com.up72.app.auth.model.User;
import com.up72.app.auth.service.UserManager;
import com.up72.auth.model.MemberRole;
import com.up72.auth.model.WorkGroupMember;
import com.up72.base.BaseRestSpringController;



/**
 * 用户数据提取跳转
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */


public class UserController extends BaseRestSpringController<User,java.lang.Long> implements IUserController{
	
	@Autowired
	private UserManager userManager;

	public String userWorkGroup(ModelMap model,@RequestParam("userId") Long userId,@RequestParam("roleId") Long roleId, HttpServletRequest request,
			HttpServletResponse response) {
		userManager.addNewRole(userId,roleId);
		return null;
	}

	/**
	 * 获得某一用户所在的部门 
	 * @author bxmen
	 * @param model
	 * @param userId
	 * @param request
	 * @param response
	 * @return 
	 * @summary 
	 */
	public String getWorkGroupMember(ModelMap model, Long userId) {
		User user = new User();
		user.setId(userId);
		//WorkGroup workGroup = userManager.getWorkGroupMember(userId);
		model.addAttribute("workGroup", user.getWorkGroup());
		return null;
	}

	/**
	 * 根据用户所在部门提取角色
	 * @author bxmen
	 * @param model
	 * @param userId
	 * @param request
	 * @param response
	 * @return 
	 * @summary 
	 */
	public String getMemberRoleByWorkGroup(ModelMap model, Long userId) {
		User user = new User();
		user.setId(userId);
		
		model.addAttribute("roleList", user.getRoles());
		return null;
	}


	/**
	 * 给用户分配用户组（部门)
	 * @author bxmen
	 */
	public void createWorkGroupMember(Long workGroupId, Long userId){
		WorkGroupMember workGroupMember = new WorkGroupMember();
		
		workGroupMember.setMemberId(userId);
		workGroupMember.setWorkGroupId(workGroupId);
		
		userManager.createWorkGroupMember(workGroupMember);
	}
	
	/**
	 * 给用户分配角色
	 * @author bxmen
	 */
	public void createMemberRole(Long roleId, Long userId){
		MemberRole memberRole = new MemberRole();
		
		memberRole.setMemberId(userId);
		memberRole.setRoleId(roleId);
		
		userManager.createMemberRole(memberRole);
	}

	/**
	 * 修改用户所拥有角色
	 * @author bxmen
	 */
	public void updateMemberRole(Long roleId, Long userId) {
		MemberRole memberRole = new MemberRole();
		
		memberRole.setMemberId(userId);
		memberRole.setRoleId(roleId);
		
		userManager.updateMemberRole(memberRole);
	}
	
	/**
	 * 修改用户所在用户组（部门）
	 * @author bxmen
	 */
	public void updateWorkGroupMember(Long workGroupId, Long userId) {
		WorkGroupMember workGroupMember = new WorkGroupMember();
		
		workGroupMember.setMemberId(userId);
		workGroupMember.setWorkGroupId(workGroupId);
		
		userManager.updateWorkGroupMember(workGroupMember);
	}
}

