/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import static com.up72.common.CommonUtils.*;
import java.io.Serializable;

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
 * 查询辅助bean
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
public class CommentQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 评论内容 */
	private java.lang.String content;
	/** 评论学生ID */
	private java.lang.Long studentId;
	/** 老师ID */
	private java.lang.Long teacherId;
	/** 添加时间 */
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;
	/** 师傅满意，0为不满意，1为满意 */
	private java.lang.Integer isSatisfied;
	/** 答案ID */
	private java.lang.Long answerId;
	/** 是否删除，0为正常，1为删除 */
	private Integer isDel;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.Long getStudentId() {
		return this.studentId;
	}
	
	public void setStudentId(java.lang.Long value) {
		this.studentId = value;
	}
	
	public java.lang.Long getTeacherId() {
		return this.teacherId;
	}
	
	public void setTeacherId(java.lang.Long value) {
		this.teacherId = value;
	}
	
	public java.lang.Long getAddTimeBegin() {
		return this.addTimeBegin;
	}
	
	public void setAddTimeBegin(java.lang.Long value) {
		this.addTimeBegin = value;
	}	
	
	public java.lang.Long getAddTimeEnd() {
		return this.addTimeEnd;
	}
	
	public void setAddTimeEnd(java.lang.Long value) {
		this.addTimeEnd = value;
	}
	
	public java.lang.Integer getIsSatisfied() {
		return this.isSatisfied;
	}
	
	public void setIsSatisfied(java.lang.Integer value) {
		this.isSatisfied = value;
	}
	
	public java.lang.Long getAnswerId() {
		return this.answerId;
	}
	
	public void setAnswerId(java.lang.Long value) {
		this.answerId = value;
	}
	
	public Integer getIsDel() {
		return this.isDel;
	}
	
	public void setIsDel(Integer value) {
		this.isDel = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

