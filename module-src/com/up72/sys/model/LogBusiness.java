/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.sys.model;

import static com.up72.common.CommonUtils.dateUtils;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import com.up72.auth.member.model.AuthUser;
import com.up72.auth.member.service.AuthUserManager;
import com.up72.framework.util.holder.ApplicationContextHolder;
/**
 * 业务日志表
 * 
 * @author sys
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "sys_log_business")
public class LogBusiness extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "业务日志表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_GUID = "操作人";
	public static final String ALIAS_TIME = "操作时间";
	public static final String ALIAS_TYPE = "操作类型";
	public static final String ALIAS_RESULT = "操作结果描述";
	public static final String ALIAS_IP = "操作IP";
	public static final String ALIAS_FUNCTION = "操作模块";
	public static final String ALIAS_LEVEL = "日志级别";
	public static final String ALIAS_DESCRIPTION = "操作说明";
	public static final String ALIAS_DELETE_FLAG = "删除标记:0-正常数据;1-系统删除数据;2-废弃数据";
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 操作人*/
	@Length(max=48)
	private java.lang.String userGuid;
	
	/** 操作时间*/
	
	private java.lang.Long time;
	
	/** 操作类型*/
	@Length(max=16)
	private java.lang.String type;
	
	/** 操作结果描述*/
	@Length(max=255)
	private java.lang.String result;
	
	/** 操作类型*/
	@Length(max=16)
	private java.lang.String ip;
	
	/** 操作类型*/
	@Length(max=48)
	private java.lang.String function;
	
	/** 日志级别*/
	
	private java.lang.Boolean level;
	
	/** 操作类型*/
	@Length(max=255)
	private java.lang.String description;
	
	/** 删除标记:0-正常数据;1-系统删除数据;2-废弃数据*/
	@Max(127)
	private Integer deleteFlag;
	
	//columns END


	public LogBusiness(){
	}

	public LogBusiness(
		java.lang.Long id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getId() {
		return this.id;
	}
	
	@Column(name = "USER_GUID", unique = false, nullable = true, insertable = true, updatable = true, length = 48)
	public java.lang.String getUserGuid() {
		return this.userGuid;
	}
	
	public void setUserGuid(java.lang.String value) {
		this.userGuid = value;
	}
	
	@Column(name = "TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getTime() {
		return this.time;
	}
	
	public void setTime(java.lang.Long value) {
		this.time = value;
	}
	@Transient
	public Date getTimeDate(){
		return dateUtils.longToDate(this.time);
	}
	@Column(name = "TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	@Column(name = "RESULT", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getResult() {
		return this.result;
	}
	
	public void setResult(java.lang.String value) {
		this.result = value;
	}
	
	@Column(name = "IP", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public java.lang.String getIp() {
		return this.ip;
	}
	
	public void setIp(java.lang.String value) {
		this.ip = value;
	}
	
	@Column(name = "FUNCTION", unique = false, nullable = true, insertable = true, updatable = true, length = 48)
	public java.lang.String getFunction() {
		return this.function;
	}
	
	public void setFunction(java.lang.String value) {
		this.function = value;
	}
	
	@Column(name = "LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.lang.Boolean getLevel() {
		return this.level;
	}
	
	public void setLevel(java.lang.Boolean value) {
		this.level = value;
	}
	
	@Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	@Column(name = "DELETE_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	
	public void setDeleteFlag(Integer value) {
		this.deleteFlag = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserGuid",getUserGuid())
			.append("Time",getTime())
			.append("Type",getType())
			.append("Result",getResult())
			.append("Ip",getIp())
			.append("Function",getFunction())
			.append("Level",getLevel())
			.append("Description",getDescription())
			.append("DeleteFlag",getDeleteFlag())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LogBusiness == false) return false;
		if(this == obj) return true;
		LogBusiness other = (LogBusiness)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public AuthUser getAuthUser(){
		AuthUser result = null;
		if(null != this.userGuid && !this.userGuid.trim().equals("")){
			AuthUserManager authUserManager = (AuthUserManager) ApplicationContextHolder.getBean("authUserManager");
			if (null != this.userGuid && !this.userGuid.equals("")) {
				result = authUserManager.getById(Long.parseLong(this.userGuid));
			}
			
		}
		return result;
	}
}

