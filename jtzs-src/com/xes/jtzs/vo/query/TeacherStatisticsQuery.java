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
public class TeacherStatisticsQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 老师ID */
	private java.lang.Long teacherId;
	/** 20分钟回答量 */
	private java.lang.Integer twentyMinNum;
	/** 30分钟回答量 */
	private java.lang.Integer halfHourNum;
	/** 60分钟回答量 */
	private java.lang.Integer oneHourNum;
	/** 专家作答量 */
	private java.lang.Integer expertNum;
	/** 老师放弃数 */
	private java.lang.Integer quitNum;
	/** 回答总量 */
	private java.lang.Integer answerNum;
	/** 浏览量 */
	private java.lang.Integer showNum;
	/** 满意 */
	private java.lang.Integer satisfy;
	/** 不满意 */
	private java.lang.Integer unsatisfy;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getTeacherId() {
		return this.teacherId;
	}
	
	public void setTeacherId(java.lang.Long value) {
		this.teacherId = value;
	}
	
	public java.lang.Integer getTwentyMinNum() {
		return this.twentyMinNum;
	}
	
	public void setTwentyMinNum(java.lang.Integer value) {
		this.twentyMinNum = value;
	}
	
	public java.lang.Integer getHalfHourNum() {
		return this.halfHourNum;
	}
	
	public void setHalfHourNum(java.lang.Integer value) {
		this.halfHourNum = value;
	}
	
	public java.lang.Integer getOneHourNum() {
		return this.oneHourNum;
	}
	
	public void setOneHourNum(java.lang.Integer value) {
		this.oneHourNum = value;
	}
	
	public java.lang.Integer getExpertNum() {
		return this.expertNum;
	}
	
	public void setExpertNum(java.lang.Integer value) {
		this.expertNum = value;
	}
	
	public java.lang.Integer getQuitNum() {
		return this.quitNum;
	}
	
	public void setQuitNum(java.lang.Integer value) {
		this.quitNum = value;
	}
	
	public java.lang.Integer getAnswerNum() {
		return this.answerNum;
	}
	
	public void setAnswerNum(java.lang.Integer value) {
		this.answerNum = value;
	}
	
	public java.lang.Integer getShowNum() {
		return this.showNum;
	}
	
	public void setShowNum(java.lang.Integer value) {
		this.showNum = value;
	}
	
	public java.lang.Integer getSatisfy() {
		return this.satisfy;
	}
	
	public void setSatisfy(java.lang.Integer value) {
		this.satisfy = value;
	}
	
	public java.lang.Integer getUnsatisfy() {
		return this.unsatisfy;
	}
	
	public void setUnsatisfy(java.lang.Integer value) {
		this.unsatisfy = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

