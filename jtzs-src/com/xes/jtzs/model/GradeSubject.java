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
@Table(name = "xes_jtzs_grade_subject")
public class GradeSubject extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "GradeSubject";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_SUBJECT_ID = "学科ID";
	public static final String ALIAS_GRADE_ID = "年级ID";
	public static final String ALIAS_STATUS = "状态";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 学科ID*/
	
	private java.lang.Long subjectId;
	
	/** 年级ID*/
	
	private java.lang.Long gradeId;
	
	/** 是否显示*/
	@Max(127)
	private Integer status;
	
	//columns END


	public GradeSubject(){
	}

	public GradeSubject(
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
	
	@Column(name = "SUBJECT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getSubjectId() {
		return this.subjectId;
	}
	
	public void setSubjectId(java.lang.Long value) {
		this.subjectId = value;
	}
	
	@Column(name = "GRADE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getGradeId() {
		return this.gradeId;
	}
	
	public void setGradeId(java.lang.Long value) {
		this.gradeId = value;
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
			.append("SubjectId",getSubjectId())
			.append("GradeId",getGradeId())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GradeSubject == false) return false;
		if(this == obj) return true;
		GradeSubject other = (GradeSubject)obj;
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
	
	@Transient
	public String getStatusStr(){
		String result = JTZSConstants.Pubilc.DISABLE.getName();
		if(null != this.status && JTZSConstants.Pubilc.DISABLE.getIndex()==this.status){
			result = JTZSConstants.Pubilc.ENABLED.getName();
		}
		return result;
	}
}

