/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
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
public class SystemMessageQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 问题ID */
	private java.lang.Long questionId;
	/** 用户ID */
	private java.lang.Long userId;
	/** 用户角色（老师，学生） */
	private Integer userRole;
	/** 添加时间 */
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;
	private java.lang.Long addTimeStart;
	private java.lang.Long addTimeStop;
	/** 是否阅读（0为阅读，1已阅读） */
	private Integer isRead;
	/** 发送人 */
	private java.lang.String addUser;

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
	
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	
	public Integer getUserRole() {
		return this.userRole;
	}
	
	public void setUserRole(Integer value) {
		this.userRole = value;
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
	
	public Integer getIsRead() {
		return this.isRead;
	}
	
	public void setIsRead(Integer value) {
		this.isRead = value;
	}
	
	public java.lang.String getAddUser() {
		return this.addUser;
	}
	public java.lang.Long getAddTimeStart() {
		return this.addTimeStart;
	}
	
	public void setAddTimeStart(java.lang.Long value) {
		this.addTimeStart = value;
	}	
	
	public java.lang.Long getAddTimeStop() {
		return this.addTimeStop;
	}
	
	public void setAddTimeStop(java.lang.Long value) {
		this.addTimeStop = value;
	}
	public void setAddUser(java.lang.String value) {
		this.addUser = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

