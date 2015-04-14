/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.up72.base.BaseQuery;

/**
 * 权限与角色多对多关系查询辅助bean
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
public class RolePermissionQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 角色 */
	private java.lang.Long roleId;
	/** 权限 */
	private java.lang.Long permissionId;
	/** 机构 */
	private java.lang.Long organizationId;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(java.lang.Long value) {
		this.roleId = value;
	}
	
	public java.lang.Long getPermissionId() {
		return this.permissionId;
	}
	
	public void setPermissionId(java.lang.Long value) {
		this.permissionId = value;
	}
	
	public java.lang.Long getOrganizationId() {
		return this.organizationId;
	}
	
	public void setOrganizationId(java.lang.Long value) {
		this.organizationId = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

