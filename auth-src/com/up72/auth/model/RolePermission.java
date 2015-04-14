/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import com.up72.auth.dao.PermissionDao;
import com.up72.auth.dao.RoleDao;
import com.up72.base.BaseEntity;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 权限与角色多对多关系
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_role_permission")
public class RolePermission extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "权限与角色多对多关系";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ROLE_ID = "角色";
	public static final String ALIAS_PERMISSION_ID = "权限";
	public static final String ALIAS_ORGANIZATION_ID = "机构";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 角色*/
	@NotNull 
	private java.lang.Long roleId;
	
	/** 权限*/
	@NotNull 
	private java.lang.Long permissionId;
	
	/** 机构*/
	@NotNull 
	private java.lang.Long organizationId;
	
	// 访问条件
	private String filterDatas; // addTime > 10000 and status=1
	// 访问数据列
	private String filterColumns; 
	
	//columns END


	public RolePermission(){
	}

	public RolePermission(
		java.lang.Long id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getId() {
		return this.id;
	}
	
	@Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(java.lang.Long value) {
		this.roleId = value;
	}
	
	@Column(name = "PERMISSION_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getPermissionId() {
		return this.permissionId;
	}
	
	public void setPermissionId(java.lang.Long value) {
		this.permissionId = value;
	}
	
	@Column(name = "ORGANIZATION_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getOrganizationId() {
		return this.organizationId;
	}
	
	public void setOrganizationId(java.lang.Long value) {
		this.organizationId = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("RoleId",getRoleId())
			.append("PermissionId",getPermissionId())
			.append("OrganizationId",getOrganizationId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RolePermission == false) return false;
		if(this == obj) return true;
		RolePermission other = (RolePermission)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient 
	public Permission getPermission(){
		Permission result = null;
		if(ObjectUtils.isNotEmpty(this.permissionId) && this.permissionId!=0){
			PermissionDao permissionDao = (PermissionDao) ApplicationContextHolder.getBean("permissionDao");
			result = permissionDao.getById(this.permissionId);	
		}
		return result;
	}
	
	@Transient
	public Role getRole(){
		Role result = null;
		if(ObjectUtils.isNotEmpty(this.roleId) && this.roleId!=0){
			RoleDao roleDao = (RoleDao) ApplicationContextHolder.getBean("roleDao");
			result = roleDao.getById(this.roleId);	
		}
		return result;
	}
}

