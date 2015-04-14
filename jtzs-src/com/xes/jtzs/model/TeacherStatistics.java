/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.model;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.up72.base.*;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.xes.jtzs.service.*;
/**
 * 
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "xes_jtzs_teacher_statistics")
public class TeacherStatistics extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "老师作答统计";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TEACHER_ID = "老师";
	public static final String ALIAS_COMMENT_ID = "老师";
	public static final String ALIAS_TWENTY_MIN_NUM = "20分钟回答量";
	public static final String ALIAS_HALF_HOUR_NUM = "30分钟回答量";
	public static final String ALIAS_ONE_HOUR_NUM = "60分钟回答量";
	public static final String ALIAS_EXPERT_NUM = "专家作答量";
	public static final String ALIAS_QUIT_NUM = "老师放弃数";
	public static final String ALIAS_ANSWER_NUM = "回答总量";
	public static final String ALIAS_SHOW_NUM = "浏览量";
	public static final String ALIAS_SATISFY = "满意";
	public static final String ALIAS_UNSATISFY = "不满意";
	public static final String ALIAS_ADD_TIME = "添加时间";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 老师ID*/
	
	private java.lang.Long teacherId;
	/** 评论ID*/
	private java.lang.Long commentId;
	
	/** 20分钟回答量*/
	
	private java.lang.Integer twentyMinNum;
	
	/** 30分钟回答量*/
	
	private java.lang.Integer halfHourNum;
	
	/** 60分钟回答量*/
	
	private java.lang.Integer oneHourNum;
	
	/** 专家作答量*/
	
	private java.lang.Integer expertNum;
	
	/** 老师放弃数*/
	
	private java.lang.Integer quitNum;
	
	/** 回答总量*/
	
	private java.lang.Integer answerNum;
	
	/** 浏览量*/
	
	private java.lang.Integer showNum;
	
	/** 满意*/
	
	private java.lang.Integer satisfy;
	
	/** 不满意*/
	
	private java.lang.Integer unsatisfy;
	
	/** 添加时间*/
	
	private java.lang.Long addTime;
	
	//columns END


	public TeacherStatistics(){
	}

	public TeacherStatistics(
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
	
	@Column(name = "TEACHER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getTeacherId() {
		return this.teacherId;
	}
	
	public void setTeacherId(java.lang.Long value) {
		this.teacherId = value;
	}
	
	@Column(name = "COMMENT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCommentId() {
		return this.commentId;
	}
	
	public void setCommentId(java.lang.Long value) {
		this.commentId = value;
	}
	
	@Column(name = "TWENTY_MIN_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getTwentyMinNum() {
		return this.twentyMinNum;
	}
	
	public void setTwentyMinNum(java.lang.Integer value) {
		this.twentyMinNum = value;
	}
	
	@Column(name = "HALF_HOUR_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getHalfHourNum() {
		return this.halfHourNum;
	}
	
	public void setHalfHourNum(java.lang.Integer value) {
		this.halfHourNum = value;
	}
	
	@Column(name = "ONE_HOUR_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getOneHourNum() {
		return this.oneHourNum;
	}
	
	public void setOneHourNum(java.lang.Integer value) {
		this.oneHourNum = value;
	}
	
	@Column(name = "EXPERT_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getExpertNum() {
		return this.expertNum;
	}
	
	public void setExpertNum(java.lang.Integer value) {
		this.expertNum = value;
	}
	
	@Column(name = "QUIT_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getQuitNum() {
		return this.quitNum;
	}
	
	public void setQuitNum(java.lang.Integer value) {
		this.quitNum = value;
	}
	
	@Column(name = "ANSWER_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getAnswerNum() {
		return this.answerNum;
	}
	
	public void setAnswerNum(java.lang.Integer value) {
		this.answerNum = value;
	}
	
	@Column(name = "SHOW_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getShowNum() {
		return this.showNum;
	}
	
	public void setShowNum(java.lang.Integer value) {
		this.showNum = value;
	}
	
	@Column(name = "SATISFY", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getSatisfy() {
		return this.satisfy;
	}
	
	public void setSatisfy(java.lang.Integer value) {
		this.satisfy = value;
	}
	
	@Column(name = "UNSATISFY", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getUnsatisfy() {
		return this.unsatisfy;
	}
	
	public void setUnsatisfy(java.lang.Integer value) {
		this.unsatisfy = value;
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
			.append("TeacherId",getTeacherId())
			.append("CommentId",getCommentId())
			.append("TwentyMinNum",getTwentyMinNum())
			.append("HalfHourNum",getHalfHourNum())
			.append("OneHourNum",getOneHourNum())
			.append("ExpertNum",getExpertNum())
			.append("QuitNum",getQuitNum())
			.append("AnswerNum",getAnswerNum())
			.append("ShowNum",getShowNum())
			.append("Satisfy",getSatisfy())
			.append("Unsatisfy",getUnsatisfy())
			.append("AddTime",getAddTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TeacherStatistics == false) return false;
		if(this == obj) return true;
		TeacherStatistics other = (TeacherStatistics)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public Teacher getTeacher(){
		Teacher teacher = null;
		TeacherManager teacherManager = (TeacherManager)ApplicationContextHolder.getBean("teacherManager");
		if(this.teacherId!=null){
			teacher =  teacherManager.getById(this.teacherId);
		}else{
			teacher = new Teacher();
		}
		return teacher;
	}
	
}

