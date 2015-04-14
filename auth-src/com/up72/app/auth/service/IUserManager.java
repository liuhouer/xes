/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.app.auth.service;

import java.util.List;
import java.util.Map;

import com.up72.auth.bean.PermissionTree;
import com.up72.auth.model.MemberRole;
import com.up72.auth.model.Product;
import com.up72.auth.model.Role;
import com.up72.auth.model.WorkGroup;
import com.up72.auth.model.WorkGroupMember;
import com.up72.base.UserDetails;


/**
 * 用户业务接口
 * 
 * @author jhe
 * @version 1.0
 * @since 1.0
 */

public interface IUserManager  {
	
	/**
	 * 是否为匿名资源
	 * @author jhe
	 * @param url 资源地址
	 * @param userDetails 当前用户 默认可为空
	 * @return 返回true为当前资源是匿名资源，反之为权限控制资源
	 */
	public boolean isAnonymity(String url, UserDetails userDetails);
	
	/**
	 * 是否有访问资源权限
	 * @author jhe
	 * @param url 资源地址
	 * @param roleId 当前角色ID
	 * @param userDetails 用户
	 * @return true 为拥有该资源权限
	 */
	public abstract boolean isAccessRights(String url, Long roleId, UserDetails userDetails);
	
	
	/**
	 * 获取菜单树
	 * @author jhe
	 * @param roleId 角色
	 * @param productCode 产品ID
	 * @param userDetails 当前用户 
	 * @return 返回菜单树
	 */
	public abstract Map<String, PermissionTree> getMenus(Long roleId, String productCode, UserDetails userDetails);
	
	/**
	 * 获取当前角色产品
	 * @author jhe
	 * @param roleId 当前角色ID
	 * @param userDetails 当前用户
	 * @return 返回产品列表
	 */
	public abstract List<Product> getProducts(Long roleId, UserDetails userDetails);
	
	/**
	 * 获取当前用户的角色集合
	 * @author jhe
	 * @param userDetails 当前用
	 * @return 返回当前用户角色列表
	 */
	public abstract List<Role> getRoles(UserDetails userDetails);
	
	/**
	 * 获得某一用户所在的部门 
	 * @author bxmen
	 * @return 用户所在部门的分页列表（一个用户可能在多个部门）
	 */
	@SuppressWarnings("unchecked")
	public abstract WorkGroup getWorkGroupByUserId(Long userId);
	
//	/**
//	 * 根据用户所在部门提取角色
//	 * @author bxmen
//	 * @param userId
//	 * @return 
//	 * @summary 根据用户ID得到所在的用户组，然后根据用户组ID得到用户组所拥有的角色
//	 */
//	@SuppressWarnings("unchecked")
//	public abstract Role getMemberRoleByWorkGroup(Long userId);
	
	/**
	 * 根据用户得到WorkGroupMember
	 * @author bxmen
	 */
	public abstract WorkGroupMember getWorkGroupMemberByUserId(Long userId);
	/**
	 * 根据用户得到MemberRole
	 * @author bxmen
	 */
	public abstract MemberRole getMemberRoleByUserId(Long userId);
	
	/**
	 * 根据workGroupId得WorkGroup用户组
	 * @author bxmen
	 * @param workGroupId
	 */
	public abstract WorkGroup getWorkGroup(Long workGroupId);
	
	/**
	 * 获得全部用户组（部门）
	 * @author bxmen
	 */
	public abstract List<WorkGroup> getAllWorkGroup();
	
	/**
	 * 根据部门ID得该部门下的角色
	 * @author bxmen
	 */
	public abstract List<Role> getRoleByWorkGroupId(Long workGroupId);
	
	/**
	 * 获得指定用户的角色
	 * @author bxmen
	 */
	@SuppressWarnings("unchecked")
	public abstract List<Role> getRoleByMemberId(Long userId);
}
