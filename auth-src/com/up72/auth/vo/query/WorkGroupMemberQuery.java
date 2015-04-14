/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.up72.base.BaseQuery;

/**
 * 工作组(部门)与用户多对多关系表查询辅助bean
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
public class WorkGroupMemberQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 所在部门 */
	private java.lang.Long workGroupId;
	/** 用户 */
	private java.lang.Long memberId;
	
	private java.lang.Integer isManager;
	
	private java.lang.Long addTime;
	
	private java.lang.Integer status;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getWorkGroupId() {
		return this.workGroupId;
	}
	
	public void setWorkGroupId(java.lang.Long value) {
		this.workGroupId = value;
	}
	
	public java.lang.Long getMemberId() {
		return this.memberId;
	}
	
	public java.lang.Integer getIsManager() {
		return isManager;
	}

	public void setIsManager(java.lang.Integer isManager) {
		this.isManager = isManager;
	}

	public void setMemberId(java.lang.Long value) {
		this.memberId = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	public java.lang.Long getAddTime() {
		return addTime;
	}

	public void setAddTime(java.lang.Long addTime) {
		this.addTime = addTime;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	
}

