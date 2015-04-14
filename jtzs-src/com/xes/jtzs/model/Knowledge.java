/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.model;

import static com.up72.common.CommonUtils.*;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.constraints.*;
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
@Table(name = "xes_jtzs_knowledge")
public class Knowledge extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "知识点";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_GRADE_ID = "年级ID";
	public static final String ALIAS_SUBJECT_ID = "学科ID";
	public static final String ALIAS_GRADE = "年级";
	public static final String ALIAS_SUBJECT = "学科";
	public static final String ALIAS_KNOWLEDGE = "知识点";
	public static final String ALIAS_KNOWLEDGE1 = "知识点(一级)";
	public static final String ALIAS_KNOWLEDGE2 = "知识点(二级)";
	public static final String ALIAS_KNOWLEDGE3 = "知识点(三级)";
	public static final String ALIAS_ADD_TIME = "添加时间";
	
	//date formats
	public static final String FORMAT_ADD_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 年级ID*/
	
	private java.lang.Long gradeId;
	
	/** 学科ID*/
	
	private java.lang.Long subjectId;
	
	/** 知识点1*/
	@Length(max=50)
	private java.lang.String knowledge1;
	
	/** 知识点2*/
	@Length(max=50)
	private java.lang.String knowledge2;
	
	/** 知识点3*/
	@Length(max=50)
	private java.lang.String knowledge3;
	
	/** 添加时间*/
	
	private java.lang.Long addTime;
	
	//columns END


	public Knowledge(){
	}

	public Knowledge(
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
	
	@Column(name = "GRADE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getGradeId() {
		return this.gradeId;
	}
	
	public void setGradeId(java.lang.Long value) {
		this.gradeId = value;
	}
	
	@Column(name = "SUBJECT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getSubjectId() {
		return this.subjectId;
	}
	
	public void setSubjectId(java.lang.Long value) {
		this.subjectId = value;
	}
	
	@Column(name = "KNOWLEDGE1", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getKnowledge1() {
		return this.knowledge1;
	}
	
	public void setKnowledge1(java.lang.String value) {
		this.knowledge1 = value;
	}
	
	@Column(name = "KNOWLEDGE2", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getKnowledge2() {
		return this.knowledge2;
	}
	
	public void setKnowledge2(java.lang.String value) {
		this.knowledge2 = value;
	}
	
	@Column(name = "KNOWLEDGE3", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getKnowledge3() {
		return this.knowledge3;
	}
	
	public void setKnowledge3(java.lang.String value) {
		this.knowledge3 = value;
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
			.append("GradeId",getGradeId())
			.append("SubjectId",getSubjectId())
			.append("Knowledge1",getKnowledge1())
			.append("Knowledge2",getKnowledge2())
			.append("Knowledge3",getKnowledge3())
			.append("AddTime",getAddTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Knowledge == false) return false;
		if(this == obj) return true;
		Knowledge other = (Knowledge)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public Grade getGrade(){
		GradeManager gradeManager = (GradeManager)ApplicationContextHolder.getBean("gradeManager");
		Grade grade = null;
		if(null != this.getGradeId()){
			grade = gradeManager.getById(this.getGradeId());
		}
		if(null == grade){
			grade = new Grade();
		}
		return grade;
	}
	
	
	@Transient
	public Subject getSubject(){
		SubjectManager subjectManager = (SubjectManager)ApplicationContextHolder.getBean("subjectManager");
		Subject subject = null;
		if(null != this.getSubjectId()){
			subject = subjectManager.getById(this.getSubjectId());
		}
		if(null == subject){
			subject = new Subject();
		}
		return subject;
	}
}

