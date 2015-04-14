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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import com.up72.base.BaseEntity;

/**
 * 用户与权限表
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_member_permission")
public class MemberPermission extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "用户与权限表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MEMBER_ID = "用户";
	public static final String ALIAS_PERMISSION_ID = "权限";
	public static final String ALIAS_STATE = "状态";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 用户*/
	@NotNull 
	private java.lang.Long memberId;
	
	/** 权限*/
	@NotNull 
	private java.lang.Long permissionId;
	
	/** 状态*/
	@NotNull @Max(127)
	private Integer state;
	
	//columns END


	public MemberPermission(){
	}

	public MemberPermission(
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
	
	@Column(name = "PERMISSION_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getPermissionId() {
		return this.permissionId;
	}
	
	public void setPermissionId(java.lang.Long value) {
		this.permissionId = value;
	}
	
	@Column(name = "STATE", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getState() {
		return this.state;
	}
	
	public void setState(Integer value) {
		this.state = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MemberId",getMemberId())
			.append("PermissionId",getPermissionId())
			.append("State",getState())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MemberPermission == false) return false;
		if(this == obj) return true;
		MemberPermission other = (MemberPermission)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

