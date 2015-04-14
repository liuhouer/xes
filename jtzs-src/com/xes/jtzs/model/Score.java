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
@Table(name = "xes_jtzs_score")
public class Score extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "积分";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_ROLE = "角色";
	public static final String ALIAS_USER_ID = "账户";
	public static final String ALIAS_SCORE = "总积分";
	public static final String ALIAS_USE_SCORE = "消费积分";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 角色*/
	@Max(127)
	private java.lang.Integer userRole;
	
	/** 账户ID*/
	
	private java.lang.Long userId;
	
	/** 积分*/
	
	private java.lang.Long score;
	
	/** 消费积分*/
	
	private java.lang.Long useScore;
	
	//columns END


	public Score(){
	}

	public Score(
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
	
	@Column(name = "SCORE", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getScore() {
		return this.score==null? 0: this.score;
	}
	
	public void setScore(java.lang.Long value) {
		this.score = value;
	}
	
	@Column(name = "USE_SCORE", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getUseScore() {
		return this.useScore==null? 0: this.useScore;
	}
	
	public void setUseScore(java.lang.Long value) {
		this.useScore = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserRole",getUserRole())
			.append("UserId",getUserId())
			.append("Score",getScore())
			.append("UseScore",getUseScore())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Score == false) return false;
		if(this == obj) return true;
		Score other = (Score)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public String getUserRoleStr(){
		if(null != this.userRole){
			if(this.userRole.equals(JTZSConstants.ROLE_STUDENT)){
				return  "学生";
			}else if(this.userRole.equals(JTZSConstants.ROLE_TEACHER)){
				return   "教师";
			}
		}
		return "";
	}
	
	
	@Transient
	public String getTelephone(){
		if(this.userRole!=null){
		if(this.userRole.equals(JTZSConstants.ROLE_STUDENT)){
			return this.getStudent().getLoginName();
		}else if(this.userRole.equals(JTZSConstants.ROLE_TEACHER)){
			return this.getTeacher().getLoginName();
		}
		}
		return "";
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
	public Long getRemainScore(){
		Long remainScore = 0L;
		if(null!= this.score && null!=this.useScore){
			remainScore = this.score -this.useScore; 
		}
		if(remainScore < 0){
			remainScore = 0L;
		}
		return remainScore;
	}
	
}

