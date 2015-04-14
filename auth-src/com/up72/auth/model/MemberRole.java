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

import com.up72.auth.dao.RoleDao;
import com.up72.auth.member.dao.AuthUserDao;
import com.up72.auth.member.model.AuthUser;
import com.up72.auth.service.PermissionManager;
import com.up72.base.BaseEntity;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 权限用户与角色多对多关系
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_member_role")
public class MemberRole extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "权限用户与角色多对多关系";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MEMBER_ID = "用户";
	public static final String ALIAS_ROLE_ID = "角色";
	public static final String ALIAS_PERMISSION_ID = "入口权限地址";
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 用户*/
	@NotNull 
	private java.lang.Long memberId;
	
	/** 角色*/
	@NotNull 
	private java.lang.Long roleId;
	
	private java.lang.Long permissionId;
	
	private Integer isDefault;
	
	//columns END


	public MemberRole(){
	}

	public MemberRole(
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
	
	@Column(name = "MEMBER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getMemberId() {
		return this.memberId;
	}
	
	public void setMemberId(java.lang.Long value) {
		this.memberId = value;
	}
	
	@Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(java.lang.Long value) {
		this.roleId = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MemberId",getMemberId())
			.append("RoleId",getRoleId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MemberRole == false) return false;
		if(this == obj) return true;
		MemberRole other = (MemberRole)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public AuthUser getMember(){
		AuthUserDao memberDao = (AuthUserDao)ApplicationContextHolder.getBean("memberDao");
		AuthUser member = null;
		if(ObjectUtils.isEmpty(this.memberId)){
			member = memberDao.getById(this.memberId);
		}
		return member;
	}
	
	@Transient
	public Role getRole(){
		RoleDao roleDao = (RoleDao)ApplicationContextHolder.getBean("roleDao");
		Role role = null;
		if(ObjectUtils.isEmpty(this.roleId)){
			role = roleDao.getById(this.roleId);
		}
		return role;
	}

	@Column(name = "PERMISSION_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getPermissionId() {
		return permissionId;
	}
	
	@Transient
	public Permission getPermission(){
		Permission result = null;
		if(null != this.permissionId && this.permissionId > 0){
			PermissionManager permissionManager = 
				(PermissionManager)ApplicationContextHolder.getBean("permissionManager");
			result = permissionManager.getById(this.permissionId);
		}
		return result;
	}

	public void setPermissionId(java.lang.Long permissionId) {
		this.permissionId = permissionId;
	}

	@Column(name = "IS_DEFAULT", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
}

