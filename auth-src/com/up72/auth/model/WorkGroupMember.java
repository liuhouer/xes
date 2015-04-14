/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.model;

import static com.up72.common.CommonUtils.dateUtils;

import java.util.Date;

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

import com.up72.auth.dao.WorkGroupDao;
import com.up72.auth.member.dao.AuthUserDao;
import com.up72.auth.member.model.AuthUser;
import com.up72.base.BaseEntity;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 工作组(部门)与用户多对多关系表
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_work_group_member")
public class WorkGroupMember extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "工作组(部门)与用户多对多关系表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_WORK_GROUP_ID = "所在部门";
	public static final String ALIAS_MEMBER_ID = "用户";
	public static final String ALIAS_IS_MANAGER = "是否是部门管理员";
	public static final String ALIAS_ADD_TIME = "添加时间";
	public static final String ALIAS_STATUS = "审核状态";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 所在部门*/
	@NotNull 
	private java.lang.Long workGroupId;
	
	/** 用户*/
	@NotNull 
	private java.lang.Long memberId;
	
	/** 是否为管理员 */
	private java.lang.Integer isManager;
	
	/** 添加时间 */
	private java.lang.Long addTime;
	
	/** 审核状态 */
	private java.lang.Integer status;
	
	//columns END


	public WorkGroupMember(){
	}

	public WorkGroupMember(
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
	
	@Column(name = "WORK_GROUP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getWorkGroupId() {
		return this.workGroupId;
	}
	
	public void setWorkGroupId(java.lang.Long value) {
		this.workGroupId = value;
	}
	
	@Column(name = "MEMBER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getMemberId() {
		return this.memberId;
	}
	
	public void setMemberId(java.lang.Long value) {
		this.memberId = value;
	}
	
	@Column(name = "IS_MANAGER", unique = false, nullable = false, insertable = true, updatable = true, length = 4)
	public java.lang.Integer getIsManager() {
		return this.isManager;
	}
	
	public void setIsManager(java.lang.Integer value) {
		this.isManager = value;
	}
	
	@Column(name = "ADD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAddTime() {
		return this.addTime;
	}

	public void setAddTime(java.lang.Long value) {
		this.addTime = value;
	}
	
	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("WorkGroupId",getWorkGroupId())
			.append("MemberId",getMemberId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WorkGroupMember == false) return false;
		if(this == obj) return true;
		WorkGroupMember other = (WorkGroupMember)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public Date getAddTimeDate() {
		return dateUtils.longToDate(this.addTime);
	}
	
	
	/**
	 * 获得用户组
	 * @author wqtan
	 */
	@Transient
	public WorkGroup getWorkGroup(){
		WorkGroupDao workGroupDao = (WorkGroupDao) ApplicationContextHolder.getBean("workGroupDao");
		WorkGroup result = null;
		if(ObjectUtils.isNotEmpty(this.workGroupId)){
			result = workGroupDao.getById(this.workGroupId);
		}
		return result;
	}
	
	/** 获得用户 */
	@Transient
	public AuthUser getMember(){
		AuthUser result = null;
		if(ObjectUtils.isNotEmpty(this.memberId)){
			AuthUserDao memberDao = (AuthUserDao)ApplicationContextHolder.getBean("authUserDao");
			result = memberDao.getById(this.memberId);
		}
		return result;
	}
}

