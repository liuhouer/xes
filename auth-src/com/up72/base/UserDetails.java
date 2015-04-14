package com.up72.base;

import java.io.Serializable;
import java.util.List;

import com.up72.auth.model.Role;
import com.up72.auth.model.WorkGroup;

/**
 * 用户接口
 * 
 * @author jhe
 * @version 1.0
 * @since 1.0
 */

public abstract interface UserDetails  extends Serializable  {
	
	public abstract Long getId();
	
	public abstract String getPassword();

	public abstract String getUserName();
	
	/** 用户类型 */
	public abstract Integer getCode();
	

	/** 账号是否禁用 */
	public abstract Integer getEnabled();
	
	/** 获取用角色列表 */
	public abstract List<Role> getRoles();
	/** 获得用户所在用户组 */
	public WorkGroup getWorkGroup();
	
	public String getStyle();
	
	public String getSkin();
}
