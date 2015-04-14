/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */

package com.xes.jtzs.model;

import static com.up72.common.CommonUtils.*;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import java.util.*;

import com.up72.base.*;
import com.up72.util.*;

import com.up72.framework.util.*;
import com.up72.framework.web.util.*;
import com.up72.framework.page.*;
import com.up72.framework.page.impl.*;

import com.xes.jtzs.model.*;
import com.xes.jtzs.dao.*;
import com.xes.jtzs.service.*;
import com.xes.jtzs.vo.query.*;/**
 * 
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "xes_jtzs_version")
public class Version extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Version";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TYPE = "系统类型 1 ios 0 android";
	public static final String ALIAS_VERSION = "版本号";
	public static final String ALIAS_SIZE = "大小";
	public static final String ALIAS_APP_URL = "升级包地址";
	public static final String ALIAS_UPDATE_INFO = "更新日志";
	public static final String ALIAS_ADD_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	
	//date formats
	public static final String FORMAT_ADD_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 系统类型 1 ios 0 android*/
	@Max(127)
	private Integer type;
	
	/** 版本号*/
	@Length(max=50)
	private java.lang.String version;
	
	/** 大小*/
	@Max(127)
	private Integer size;
	
	/** 升级包地址*/
	@Length(max=200)
	private java.lang.String appUrl;
	
	/** 更新日志*/
	@Length(max=500)
	private java.lang.String updateInfo;
	
	/** 创建时间*/
	
	private java.lang.Long addTime;
	
	/** 更新时间*/
	
	private java.lang.Long updateTime;
	
	//columns END


	public Version(){
	}

	public Version(
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
	
	@Column(name = "TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer value) {
		this.type = value;
	}
	
	@Column(name = "VERSION", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.String value) {
		this.version = value;
	}
	
	@Column(name = "SIZE", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getSize() {
		return this.size;
	}
	
	public void setSize(Integer value) {
		this.size = value;
	}
	
	@Column(name = "APP_URL", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getAppUrl() {
		return this.appUrl;
	}
	
	public void setAppUrl(java.lang.String value) {
		this.appUrl = value;
	}
	
	@Column(name = "UPDATE_INFO", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getUpdateInfo() {
		return this.updateInfo;
	}
	
	public void setUpdateInfo(java.lang.String value) {
		this.updateInfo = value;
	}
	
	@Transient
	public Date getAddTimeDate() {
		return dateUtils.longToDate(this.addTime);
	}
	@Column(name = "ADD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAddTime() {
		return this.addTime;
	}
	
	public void setAddTime(java.lang.Long value) {
		this.addTime = value;
	}
	
	@Transient
	public Date getUpdateTimeDate() {
		return dateUtils.longToDate(this.updateTime);
	}
	@Column(name = "UPDATE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.Long value) {
		this.updateTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Type",getType())
			.append("Version",getVersion())
			.append("Size",getSize())
			.append("AppUrl",getAppUrl())
			.append("UpdateInfo",getUpdateInfo())
			.append("AddTime",getAddTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Version == false) return false;
		if(this == obj) return true;
		Version other = (Version)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

