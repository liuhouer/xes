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
@Table(name = "xes_jtzs_feedback")
public class Feedback extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Feedback";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_USER_ROLE = "用户角色（老师，学生）";
	public static final String ALIAS_ADD_TIME = "添加时间";
	
	//date formats
	public static final String FORMAT_ADD_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 内容*/
	@Length(max=500)
	private java.lang.String content;
	
	/** 用户ID*/
	
	private java.lang.Long userId;
	
	/** 用户角色（老师，学生）*/
	@Max(127)
	private Integer userRole;
	
	/** 添加时间*/
	
	private java.lang.Long addTime;
	
	//columns END


	public Feedback(){
	}

	public Feedback(
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
	
	@Column(name = "CONTENT", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Column(name = "USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	
	@Column(name = "USER_ROLE", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getUserRole() {
		return this.userRole;
	}
	
	public void setUserRole(Integer value) {
		this.userRole = value;
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
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Content",getContent())
			.append("UserId",getUserId())
			.append("UserRole",getUserRole())
			.append("AddTime",getAddTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Feedback == false) return false;
		if(this == obj) return true;
		Feedback other = (Feedback)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

