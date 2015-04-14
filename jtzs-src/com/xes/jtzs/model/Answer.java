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
@Table(name = "xes_jtzs_answer")
public class Answer extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "答案";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_QUESTION_ID = "问题";
	public static final String ALIAS_TEACHER_ID = "回答老师";
	public static final String ALIAS_CONTENT = "答案";
	public static final String ALIAS_IMG_PATH = "答案图片";
	public static final String ALIAS_IDER = "解题思路";
	public static final String ALIAS_ANSWER_TIME = "作答时间";
	public static final String ALIAS_STATUS = "答题状态";
	
	//date formats
	public static final String FORMAT_ANSWER_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 问题ID*/
	
	private java.lang.Long questionId;
	
	/** 老师ID*/
	
	private java.lang.Long teacherId;
	
	/** 问题内容*/
	@Length(max=500)
	private java.lang.String content;
	
	/** 问题图片*/
	@Length(max=200)
	private java.lang.String imgPath;
	
	/** 解题思路*/
	@Length(max=500)
	private java.lang.String ider;
	
	/** 作答时间*/
	
	private java.lang.Long answerTime;
	
	/** 答题状态*/
	@Max(127)
	private Integer status;
	
	//columns END


	public Answer(){
	}

	public Answer(
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
	
	@Column(name = "QUESTION_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getQuestionId() {
		return this.questionId;
	}
	
	public void setQuestionId(java.lang.Long value) {
		this.questionId = value;
	}
	
	@Column(name = "TEACHER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getTeacherId() {
		return this.teacherId;
	}
	
	public void setTeacherId(java.lang.Long value) {
		this.teacherId = value;
	}
	
	@Column(name = "CONTENT", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getContent() {
		return this.content==null?this.content:this.content.replace("\r", "\\r").replace("\n", "\\n");
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Column(name = "IMG_PATH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	
	@Column(name = "IDER", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getIder() {
		return this.ider==null?this.ider:this.ider.replace("\r", "\\r").replace("\n", "\\n");
	}
	
	public void setIder(java.lang.String value) {
		this.ider = value;
	}
	
	@Transient
	public Date getAnswerTimeDate() {
		return dateUtils.longToDate(this.answerTime);
	}
	@Column(name = "ANSWER_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAnswerTime() {
		return this.answerTime;
	}
	
	public void setAnswerTime(java.lang.Long value) {
		this.answerTime = value;
	}
	
	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("QuestionId",getQuestionId())
			.append("TeacherId",getTeacherId())
			.append("Content",getContent())
			.append("ImgPath",getImgPath())
			.append("Ider",getIder())
			.append("AnswerTime",getAnswerTime())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Answer == false) return false;
		if(this == obj) return true;
		Answer other = (Answer)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public String getAnswerTimeStr(){
		String rusult = null;
		if(null!= this.answerTime){
			rusult = dateUtils.getRelativeTime(answerTime);
		}
		if(null == rusult){
			rusult = "";
		}
		return rusult;
	}
	
	@Transient
	public Teacher getTeacher(){
		Teacher teacher = null;
		if(null != this.teacherId){
			TeacherManager teacherManager = (TeacherManager)ApplicationContextHolder.getBean("teacherManager");
			teacher = teacherManager.getById(teacherId);
		}
		if(null == teacher){
			teacher = new Teacher();
		}
		return teacher;
	}
	
	@Transient
	public Question getQuestion(){
		Question question = null;
		if(null != this.teacherId){
			QuestionManager questionManager = (QuestionManager)ApplicationContextHolder.getBean("questionManager");
			question = questionManager.getById(this.questionId);
		}
		if(null == question){
			question = new Question();
		}
		return question;
	}
}

