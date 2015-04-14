/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
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
@Table(name = "xes_jtzs_wrong_rules")
public class WrongRules extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "模板管理";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ROLE = "角色对象";
	public static final String ALIAS_WRONG_NUM = "违规次数";
	public static final String ALIAS_CONTENT = "发送内容";
	public static final String ALIAS_IS_DEL_QUESTION = "问题是否删除，0为不删除，1为删除";
	public static final String ALIAS_DEL_SCORE = "扣除积分";
	public static final String ALIAS_IS_STOP_LOGIN = "是否冻结帐号，0为不冻结，1为冻结";
	public static final String ALIAS_ADD_TIME = "添加时间";
	
	//date formats
	public static final String FORMAT_ADD_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 角色对象*/
	@Max(127)
	private Integer role;
	
	/** 违规次数*/
	@Max(32767)
	private Integer wrongNum;
	
	/** 发送内容*/
	@Length(max=200)
	private java.lang.String content;
	
	/** 问题是否删除，0为不删除，1为删除*/
	@Max(127)
	private Integer isDelQuestion;
	
	/** 扣除积分*/
	
	private java.lang.Integer delScore;
	
	/** 是否冻结帐号，0为不冻结，1为冻结*/
	@Max(127)
	private Integer isStopLogin;
	
	/** 添加时间*/
	
	private java.lang.Long addTime;
	
	//columns END


	public WrongRules(){
	}

	public WrongRules(
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
	
	@Column(name = "ROLE", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getRole() {
		return this.role;
	}
	
	public void setRole(Integer value) {
		this.role = value;
	}
	
	@Column(name = "WRONG_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	public Integer getWrongNum() {
		return this.wrongNum;
	}
	
	public void setWrongNum(Integer value) {
		this.wrongNum = value;
	}
	
	@Column(name = "CONTENT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Column(name = "IS_DEL_QUESTION", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsDelQuestion() {
		return this.isDelQuestion;
	}
	
	public void setIsDelQuestion(Integer value) {
		this.isDelQuestion = value;
	}
	
	@Column(name = "DEL_SCORE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDelScore() {
		return this.delScore;
	}
	
	public void setDelScore(java.lang.Integer value) {
		this.delScore = value;
	}
	
	@Column(name = "IS_STOP_LOGIN", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsStopLogin() {
		return this.isStopLogin;
	}
	
	public void setIsStopLogin(Integer value) {
		this.isStopLogin = value;
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
			.append("Role",getRole())
			.append("WrongNum",getWrongNum())
			.append("Content",getContent())
			.append("IsDelQuestion",getIsDelQuestion())
			.append("DelScore",getDelScore())
			.append("IsStopLogin",getIsStopLogin())
			.append("AddTime",getAddTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WrongRules == false) return false;
		if(this == obj) return true;
		WrongRules other = (WrongRules)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

