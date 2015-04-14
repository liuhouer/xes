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
@Table(name = "xes_jtzs_comment")
public class Comment extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Comment";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CONTENT = "评论内容";
	public static final String ALIAS_STUDENT_ID = "评论学生ID";
	public static final String ALIAS_TEACHER_ID = "老师ID";
	public static final String ALIAS_ADD_TIME = "添加时间";
	public static final String ALIAS_IS_SATISFIED = "是否满意";//0为不满意，1为满意";
	public static final String ALIAS_ANSWER_ID = "答案ID";
	public static final String ALIAS_IS_DEL = "是否删除，0为正常，1为删除";
	
	//date formats
	public static final String FORMAT_ADD_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 评论内容*/
	@Length(max=500)
	private java.lang.String content;
	
	/** 评论学生ID*/
	
	private java.lang.Long studentId;
	/** 老师ID*/
	private java.lang.Long teacherId;
	
	/** 添加时间*/
	
	private java.lang.Long addTime;
	
	/** 师傅满意，0为不满意，1为满意*/
	@Max(127)
	private java.lang.Integer isSatisfied;
	
	/** 答案ID*/
	
	private java.lang.Long answerId;
	
	/** 是否删除，0为正常，1为删除*/
	@Max(127)
	private Integer isDel;
	
	//columns END


	public Comment(){
	}

	public Comment(
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
	
	@Column(name = "STUDENT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getStudentId() {
		return this.studentId;
	}
	
	public void setStudentId(java.lang.Long value) {
		this.studentId = value;
	}
	
	@Column(name = "TEACHER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getTeacherId() {
		return this.teacherId;
	}
	
	public void setTeacherId(java.lang.Long value) {
		this.teacherId = value;
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
	
	@Column(name = "IS_SATISFIED", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getIsSatisfied() {
		return this.isSatisfied;
	}
	
	public void setIsSatisfied(java.lang.Integer value) {
		this.isSatisfied = value;
	}
	
	@Column(name = "ANSWER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAnswerId() {
		return this.answerId;
	}
	
	public void setAnswerId(java.lang.Long value) {
		this.answerId = value;
	}
	
	@Column(name = "IS_DEL", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Integer getIsDel() {
		return this.isDel;
	}
	
	public void setIsDel(Integer value) {
		this.isDel = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Content",getContent())
			.append("StudentId",getStudentId())
			.append("TeacherId",getTeacherId())
			.append("AddTime",getAddTime())
			.append("IsSatisfied",getIsSatisfied())
			.append("AnswerId",getAnswerId())
			.append("IsDel",getIsDel())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Comment == false) return false;
		if(this == obj) return true;
		Comment other = (Comment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public Student getStudent(){
		StudentManager studentManager = (StudentManager)ApplicationContextHolder.getBean("studentManager");
		Student student = null;
		if(null != this.getStudentId()){
			student = studentManager.getById(this.getStudentId());
		}
		if(null == student){
			student = new Student();
		}
		return student;
	}
	
	@Transient
	public Teacher getTeacher(){
		TeacherManager teacherManager = (TeacherManager)ApplicationContextHolder.getBean("teacherManager");
		Teacher teacher = null;
		if(null != this.getTeacherId()){
			teacher = teacherManager.getById(this.getTeacherId());
		}
		if(null == teacher){
			teacher = new Teacher();
		}
		return teacher;
	}
	
	@Transient
	public Answer getAnswer(){
		AnswerManager answerManager = (AnswerManager)ApplicationContextHolder.getBean("answerManager");
		Answer answer = null;
		if(null != this.getAnswerId()){
			answer = answerManager.getById(this.getAnswerId());
		}
		if(null == answer){
			answer = new Answer();
		}
		return answer;
	}
	
	
	@Transient
	public String getAddTimeStr(){
		String rusult = null;
		if(null!= this.addTime){
			rusult = dateUtils.getRelativeTime(addTime);
		}
		if(null == rusult){
			rusult = "";
		}
		return rusult;
	}
	
	@Transient
	public String getSatisfiedStr(){
		String rusult = null;
		if(null!= this.isSatisfied){
			if(this.isSatisfied == JTZSConstants.SatisfiedStatus.SATISFIED.getIndex()){
				rusult = JTZSConstants.SatisfiedStatus.SATISFIED.getName();
			}else{
				rusult = JTZSConstants.SatisfiedStatus.UNSATISFIED.getName();
			}
		}
		if(null == rusult){
			rusult = "";
		}
		return rusult;
	}
}

