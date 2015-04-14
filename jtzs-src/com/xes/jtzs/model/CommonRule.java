/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.model;

import static com.up72.common.CommonUtils.*;
import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.up72.auth.member.model.AuthUser;
import com.up72.auth.member.service.AuthUserManager;
import com.up72.base.*;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.xes.jtzs.JTZSConstants;

/**
 * 
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "xes_jtzs_common_rule")
public class CommonRule extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "全局设置";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE = "内容";
	public static final String ALIAS_NUM = "数量";
	public static final String ALIAS_SCORE = "积分";
	public static final String ALIAS_SCORE_TYPE = "积分种类";
	public static final String ALIAS_IS_STOP_LOGIN = "是否冻结帐号";//，0为不冻结，1为冻结
	public static final String ALIAS_VALID_START_TIME = "有效开始时间";
	public static final String ALIAS_VALID_STOP_TIME = "有效结束时间";
	public static final String ALIAS_BEGIN_TIME = "开始时间";
	public static final String ALIAS_END_TIME = "结束时间";
	public static final String ALIAS_RULE_TYPE = "规则类型";
	public static final String ALIAS_ADD_TIME = "添加时间";
	public static final String ALIAS_EDIT_TIME = "编辑时间";
	public static final String ALIAS_MINUTE = "分钟";
	public static final String ALIAS_EDIT_USER_ID = "操作人";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_IS_DEFAULT = "是否默认";
	
	//date formats
	public static final String FORMAT_ADD_TIME = DATE_FORMAT;
	public static final String FORMAT_VALID_START_TIME = DATE_FORMAT;
	public static final String FORMAT_VALID_STOP_TIME = DATE_FORMAT;
	public static final String FORMAT_BEGIN_TIME = DATE_FORMAT;
	public static final String FORMAT_END_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 标题*/
	@Length(max=50)
	private java.lang.String title;
	
	/** 数量*/
	@Max(32767)
	private Integer num;
	
	/** 扣除积分*/
	
	private java.lang.Integer score;
	/** 分钟*/
	private java.lang.Integer minute;
	
	/** 是否冻结帐号，0为不冻结，1为冻结*/
	@Max(127)
	private Integer isStopLogin;
	/** 有效开始时间*/
	
	private java.lang.Long validStartTime;
	
	/** 有效结束时间*/
	
	private java.lang.Long validStopTime;
	
	/** 开始时间*/
	
	private java.lang.Long beginTime;
	
	/** 结束时间*/
	
	private java.lang.Long endTime;
	
	/** 规则类型*/
	@Max(127)
	private Integer ruleType;
	
	/** 积分种类*/
	@Max(127)
	private Integer scoreType;
	@Max(127)
	private Integer status;
	@Max(127)
	private Integer isDefault;
	
	/** 添加时间*/
	
	private java.lang.Long addTime;
	/** 编辑时间*/
	
	private java.lang.Long editTime;
	
	/** 操作人 */
	private java.lang.Long editUserId;
	
	//columns END


	public CommonRule(){
	}

	public CommonRule(
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
	
	@Column(name = "TITLE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String value) {
		this.title = value;
	}
	
	@Column(name = "NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	public Integer getNum() {
		return this.num;
	}
	
	public void setNum(Integer value) {
		this.num = value;
	}
	
	@Column(name = "SCORE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getScore() {
		return this.score;
	}
	
	public void setScore(java.lang.Integer value) {
		this.score = value;
	}
	
	@Column(name = "MINUTE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMinute() {
		return this.minute;
	}
	
	public void setMinute(java.lang.Integer value) {
		this.minute = value;
	}
	
	@Column(name = "IS_STOP_LOGIN", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsStopLogin() {
		return this.isStopLogin;
	}
	
	public void setIsStopLogin(Integer value) {
		this.isStopLogin = value;
	}
	
	@Transient
	public Date getValidStartTimeDate() {
		return dateUtils.longToDate(this.validStartTime);
	}
	@Column(name = "VALID_START_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getValidStartTime() {
		return this.validStartTime;
	}
	
	public void setValidStartTime(java.lang.Long value) {
		this.validStartTime = value;
	}
	
	@Transient
	public Date getValidStopTimeDate() {
		return dateUtils.longToDate(this.validStopTime);
	}
	@Column(name = "VALID_STOP_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getValidStopTime() {
		return this.validStopTime;
	}
	
	public void setValidStopTime(java.lang.Long value) {
		this.validStopTime = value;
	}
	
	@Transient
	public Date getBeginTimeDate() {
		return dateUtils.longToDate(this.beginTime);
	}
	
	@Transient
	public String getBeginTimeStr(){
		return null != this.beginTime ? dateUtils.longToTime(this.beginTime).substring(0, 5):"";
	}
	
	@Column(name = "BEGIN_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getBeginTime() {
		return this.beginTime;
	}
	
	public void setBeginTime(java.lang.Long value) {
		this.beginTime = value;
	}
	
	@Transient
	public Date getEndTimeDate() {
		return dateUtils.longToDate(this.endTime);
	}
	
	@Transient
	public String getEndTimeStr() {
		return null != this.endTime ? dateUtils.longToTime(this.endTime).substring(0, 5):"";
	}
	@Column(name = "END_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(java.lang.Long value) {
		this.endTime = value;
	}
	
	@Column(name = "RULE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getRuleType() {
		return this.ruleType;
	}
	
	public void setRuleType(Integer value) {
		this.ruleType = value;
	}
	
	@Column(name = "SCORE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Integer getScoreType() {
		return this.scoreType;
	}
	
	public void setScoreType(Integer value) {
		this.scoreType = value;
	}
	
	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	@Column(name = "IS_DEFAULT", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Integer getIsDefault() {
		return this.isDefault;
	}
	
	public void setIsDefault(Integer value) {
		this.isDefault = value;
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
	public Date getEditTimeDate() {
		return dateUtils.longToDate(this.editTime);
	}
	@Column(name = "EDIT_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getEditTime() {
		return this.editTime;
	}
	
	public void setEditTime(java.lang.Long value) {
		this.editTime = value;
	}
	
	@Column(name = "EDIT_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getEditUserId() {
		return this.editUserId;
	}
	
	public void setEditUserId(java.lang.Long value) {
		this.editUserId = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("Num",getNum())
			.append("Score",getScore())
			.append("IsStopLogin",getIsStopLogin())
			.append("AddTime",getAddTime())
			.append("ValidStartTime",getValidStartTime())
			.append("ValidStopTime",getValidStopTime())
			.append("BeginTime",getBeginTime())
			.append("EndTime",getEndTime())
			.append("RuleType",getRuleType())
			.append("ScoreType",getScoreType())
			.append("EditUserId",getEditUserId())
			.append("EditTime",getEditTime())
			.append("Status",getStatus())
			.append("Minute",getMinute())
			.append("IsDefault",getIsDefault())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CommonRule == false) return false;
		if(this == obj) return true;
		CommonRule other = (CommonRule)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public AuthUser getEditUser(){
		AuthUserManager authUserManager = (AuthUserManager)ApplicationContextHolder.getBean("authUserManager");
		AuthUser user = null;
		if(null != this.getEditUserId()){
			user = authUserManager.getById(this.getEditUserId());
		}
		if(null == user){
			user = new AuthUser();
		}
		return user;
	}
	
	@Transient
	public String getStatusStr(){
		String result = JTZSConstants.Pubilc.DISABLE.getName();
		if(null != this.status && JTZSConstants.Pubilc.ENABLED.getIndex()==this.status){
			result = JTZSConstants.Pubilc.ENABLED.getName();
		}
		return result;
	}
	

	@Transient
	public String getRuleTypeStr(){
		String result = null;
		if(null!=this.ruleType){
			result = JTZSConstants.CommonRuleType.getName(this.ruleType);
		}
		if(null == result){
			result = "";
		}
		return result;
	}
}

