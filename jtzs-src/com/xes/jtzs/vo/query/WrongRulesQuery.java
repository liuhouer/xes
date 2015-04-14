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
import com.xes.jtzs.vo.query.*;

/**
 * 查询辅助bean
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public class WrongRulesQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;

	/** id */
	private java.lang.Long id;
	/** 角色对象 */
	private Integer role;
	/** 违规次数 */
	private Integer wrongNum;
	/** 发送内容 */
	private java.lang.String content;
	/** 问题是否删除，0为不删除，1为删除 */
	private Integer isDelQuestion;
	/** 扣除积分 */
	private java.lang.Integer delScore;
	private java.lang.Integer delScoreStart;
	private java.lang.Integer delScoreEnd;
	/** 是否冻结帐号，0为不冻结，1为冻结 */
	private Integer isStopLogin;
	/** 添加时间 */
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;

	public java.lang.Long getId() {
		return this.id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}

	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer value) {
		this.role = value;
	}

	public Integer getWrongNum() {
		return this.wrongNum;
	}

	public void setWrongNum(Integer value) {
		this.wrongNum = value;
	}

	public java.lang.String getContent() {
		return this.content;
	}

	public void setContent(java.lang.String value) {
		this.content = value;
	}

	public Integer getIsDelQuestion() {
		return this.isDelQuestion;
	}

	public void setIsDelQuestion(Integer value) {
		this.isDelQuestion = value;
	}

	public java.lang.Integer getDelScore() {
		return this.delScore;
	}

	public void setDelScore(java.lang.Integer value) {
		this.delScore = value;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public java.lang.Integer getDelScoreStart() {
		return delScoreStart;
	}

	public void setDelScoreStart(java.lang.Integer delScoreStart) {
		this.delScoreStart = delScoreStart;
	}

	public java.lang.Integer getDelScoreEnd() {
		return delScoreEnd;
	}

	public void setDelScoreEnd(java.lang.Integer delScoreEnd) {
		this.delScoreEnd = delScoreEnd;
	}

}
