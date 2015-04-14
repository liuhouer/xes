/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.model;

import static com.up72.common.CommonUtils.*;

import java.text.SimpleDateFormat;
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
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.framework.web.util.*;
import com.up72.framework.page.*;
import com.up72.framework.page.impl.*;

import com.xes.jtzs.JTZSConstants;
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
@Table(name = "xes_jtzs_score_log")
public class ScoreLog extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ScoreLog";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_SCORE = "积分";
	public static final String ALIAS_USER_ID = "用户";
	public static final String ALIAS_USER_ROLE = "角色";
	public static final String ALIAS_ADD_TIME = "消费时间";
	public static final String ALIAS_OPERATOR_ID = "操作人";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_CONTENT = "消费内容";
	public static final String ALIAS_SCORE_TYPE = "积分类型";
	
	//date formats
	public static final String FORMAT_ADD_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 积分*/
	
	private java.lang.Integer score;
	
	/** 用户ID*/
	
	private java.lang.Long userId;
	
	private java.lang.Integer userRole;
	
	/** 消费时间*/
	
	private java.lang.Long addTime;
	
	/** 操作人*/
	
	private java.lang.Long operatorId;
	
	/** 备注*/
	@Length(max=200)
	private java.lang.String remark;
	
	/** 消费内容*/
	@Length(max=200)
	private java.lang.String content;
	
	/** 积分类型*/
	@Max(127)
	private Integer scoreType;
	
	@Transient
	public String  getAddTimeStrs() {
		if(this.addTime!=null){
		Date d = new Date(this.addTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:ss");
			return sdf.format(d);
		}else{
			return null;
		}
	}
	
	@Transient
	public String  getScoreTypeStrs() {
		if(this.scoreType!=null){
		if(this.scoreType.equals(JTZSConstants.ScoreType.ADD.getIndex())){
			return "获得";
		}else if(this.scoreType.equals(JTZSConstants.ScoreType.DEL.getIndex())){
			return "消耗";
		}
		}
		return "";
	}
	
	
	public ScoreLog(){
	}

	public ScoreLog(
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
	
	@Column(name = "SCORE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getScore() {
		return this.score;
	}
	
	public void setScore(java.lang.Integer value) {
		this.score = value;
	}
	
	@Column(name = "USER_ROLE", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.Integer getUserRole() {
		return this.userRole;
	}
	
	public void setUserRole(java.lang.Integer value) {
		this.userRole = value;
	}
	
	@Column(name = "USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
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
	
	@Column(name = "OPERATOR_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getOperatorId() {
		return this.operatorId;
	}
	
	public void setOperatorId(java.lang.Long value) {
		this.operatorId = value;
	}
	
	@Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	@Column(name = "CONTENT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Column(name = "SCORE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getScoreType() {
		return this.scoreType;
	}
	
	public void setScoreType(Integer value) {
		this.scoreType = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Score",getScore())
			.append("UserRole",getUserRole())
			.append("UserId",getUserId())
			.append("AddTime",getAddTime())
			.append("OperatorId",getOperatorId())
			.append("Remark",getRemark())
			.append("Content",getContent())
			.append("ScoreType",getScoreType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ScoreLog == false) return false;
		if(this == obj) return true;
		ScoreLog other = (ScoreLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public String getUserRoleStr(){
		String userRoleStr = null;
		if(null != this.userRole){
			if(this.userRole == JTZSConstants.ROLE_STUDENT){
				userRoleStr = "学生";
			}else{
				userRoleStr = "教师";
			}
		}
		if(null == userRoleStr){
			userRoleStr = "";
		}
		return userRoleStr;
	}
	
	@Transient
	public Student getStudent(){
		StudentManager studentManager = (StudentManager)ApplicationContextHolder.getBean("studentManager");
		Student student = null;
		if(null != this.userId){
			student = studentManager.getById(this.userId);
		}
		if(null == student){
			student = new Student();
		}
		return student;
	}
	
	@Transient
	public Teacher getTeacher(){
		Teacher teacher = null;
		if(null != this.userId){
			TeacherManager teacherManager = (TeacherManager)ApplicationContextHolder.getBean("teacherManager");
			teacher = teacherManager.getById(userId);
		}
		if(null == teacher){
			teacher = new Teacher();
		}
		return teacher;
	}

	@Transient
	public String getLeixing() {
		if(this.scoreType.equals(JTZSConstants.ScoreType.ADD.getIndex())){
			return "获得";
		}else if(this.scoreType.equals(JTZSConstants.ScoreType.DEL.getIndex())){
			return "消耗";
		}
		return "";
	}


	
}

