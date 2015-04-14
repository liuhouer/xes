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
public class KnowledgeQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 年级ID */
	private java.lang.Long gradeId;
	/** 学科ID */
	private java.lang.Long subjectId;
	/** 知识点1 */
	private java.lang.String knowledge1;
	/** 知识点2 */
	private java.lang.String knowledge2;
	/** 知识点3 */
	private java.lang.String knowledge3;
	/** 添加时间 */
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;

	/** 知识点chaxun */
	private java.lang.String knowledge;
	public java.lang.String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(java.lang.String knowledge) {
		this.knowledge = knowledge;
	}

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getGradeId() {
		return this.gradeId;
	}
	
	public void setGradeId(java.lang.Long value) {
		this.gradeId = value;
	}
	
	public java.lang.Long getSubjectId() {
		return this.subjectId;
	}
	
	public void setSubjectId(java.lang.Long value) {
		this.subjectId = value;
	}
	
	public java.lang.String getKnowledge1() {
		return this.knowledge1;
	}
	
	public void setKnowledge1(java.lang.String value) {
		this.knowledge1 = value;
	}
	
	public java.lang.String getKnowledge2() {
		return this.knowledge2;
	}
	
	public void setKnowledge2(java.lang.String value) {
		this.knowledge2 = value;
	}
	
	public java.lang.String getKnowledge3() {
		return this.knowledge3;
	}
	
	public void setKnowledge3(java.lang.String value) {
		this.knowledge3 = value;
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
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

