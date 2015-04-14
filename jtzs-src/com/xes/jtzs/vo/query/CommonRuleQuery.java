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
public class CommonRuleQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 标题 */
	private Integer title;
	/** 数量 */
	private Integer num;
	/** 扣除积分 */
	private java.lang.Integer score;
	/** 是否冻结帐号，0为不冻结，1为冻结 */
	private Integer isStopLogin;
	/** 添加时间 */
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;
	/** 有效开始时间 */
	private java.lang.Long validStartTimeBegin;
	private java.lang.Long validStartTimeEnd;
	/** 有效结束时间 */
	private java.lang.Long validStopTimeBegin;
	private java.lang.Long validStopTimeEnd;
	/** 开始时间 */
	private java.lang.Long beginTimeBegin;
	private java.lang.Long beginTimeEnd;
	/** 结束时间 */
	private java.lang.Long endTimeBegin;
	private java.lang.Long endTimeEnd;
	/** 规则类型 */
	private Integer ruleType;
	/** 状态 */
	private Integer status;
	/** 积分种类 */
	private Integer scoreType;
	/** 编辑时间 */
	private Long editTime;
	/** 编辑人 */
	private Long editUserId;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public Integer getTitle() {
		return this.title;
	}
	
	public void setTitle(Integer value) {
		this.title = value;
	}
	
	public Integer getNum() {
		return this.num;
	}
	
	public void setNum(Integer value) {
		this.num = value;
	}
	
	public java.lang.Integer getScore() {
		return this.score;
	}
	
	public void setScore(java.lang.Integer value) {
		this.score = value;
	}
	
	public Integer getIsStopLogin() {
		return this.isStopLogin;
	}
	
	public void setIsStopLogin(Integer value) {
		this.isStopLogin = value;
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
	
	public java.lang.Long getValidStartTimeBegin() {
		return this.validStartTimeBegin;
	}
	
	public void setValidStartTimeBegin(java.lang.Long value) {
		this.validStartTimeBegin = value;
	}	
	
	public java.lang.Long getValidStartTimeEnd() {
		return this.validStartTimeEnd;
	}
	
	public void setValidStartTimeEnd(java.lang.Long value) {
		this.validStartTimeEnd = value;
	}
	
	public java.lang.Long getValidStopTimeBegin() {
		return this.validStopTimeBegin;
	}
	
	public void setValidStopTimeBegin(java.lang.Long value) {
		this.validStopTimeBegin = value;
	}	
	
	public java.lang.Long getValidStopTimeEnd() {
		return this.validStopTimeEnd;
	}
	
	public void setValidStopTimeEnd(java.lang.Long value) {
		this.validStopTimeEnd = value;
	}
	
	public java.lang.Long getBeginTimeBegin() {
		return this.beginTimeBegin;
	}
	
	public void setBeginTimeBegin(java.lang.Long value) {
		this.beginTimeBegin = value;
	}	
	
	public java.lang.Long getBeginTimeEnd() {
		return this.beginTimeEnd;
	}
	
	public void setBeginTimeEnd(java.lang.Long value) {
		this.beginTimeEnd = value;
	}
	
	public java.lang.Long getEndTimeBegin() {
		return this.endTimeBegin;
	}
	
	public void setEndTimeBegin(java.lang.Long value) {
		this.endTimeBegin = value;
	}	
	
	public java.lang.Long getEndTimeEnd() {
		return this.endTimeEnd;
	}
	
	public void setEndTimeEnd(java.lang.Long value) {
		this.endTimeEnd = value;
	}
	
	public Integer getRuleType() {
		return this.ruleType;
	}
	
	public void setRuleType(Integer value) {
		this.ruleType = value;
	}
	
	public Integer getScoreType() {
		return this.scoreType;
	}
	
	public void setScoreType(Integer value) {
		this.scoreType = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public java.lang.Long getEditTime() {
		return this.editTime;
	}
	
	public void setEditTime(java.lang.Long value) {
		this.editTime = value;
	}
	
	public java.lang.Long getEditUserId() {
		return this.editUserId;
	}
	
	public void setEditUserId(java.lang.Long value) {
		this.editUserId = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

