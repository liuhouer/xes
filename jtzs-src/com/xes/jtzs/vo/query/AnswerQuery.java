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
public class AnswerQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 问题ID */
	private java.lang.Long questionId;
	/** 老师ID */
	private java.lang.Long teacherId;
	/** 答案内容 */
	private java.lang.String content;
	/** 答案图片 */
	private java.lang.String imgPath;
	/** 解题思路 */
	private java.lang.String ider;
	/** 作答时间 */
	private java.lang.Long answerTimeBegin;
	private java.lang.Long answerTimeEnd;
	/** 答题状态 */
	private Integer status;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getQuestionId() {
		return this.questionId;
	}
	
	public void setQuestionId(java.lang.Long value) {
		this.questionId = value;
	}
	
	public java.lang.Long getTeacherId() {
		return this.teacherId;
	}
	
	public void setTeacherId(java.lang.Long value) {
		this.teacherId = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	
	public java.lang.String getIder() {
		return this.ider;
	}
	
	public void setIder(java.lang.String value) {
		this.ider = value;
	}
	
	public java.lang.Long getAnswerTimeBegin() {
		return this.answerTimeBegin;
	}
	
	public void setAnswerTimeBegin(java.lang.Long value) {
		this.answerTimeBegin = value;
	}	
	
	public java.lang.Long getAnswerTimeEnd() {
		return this.answerTimeEnd;
	}
	
	public void setAnswerTimeEnd(java.lang.Long value) {
		this.answerTimeEnd = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

