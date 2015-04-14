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
import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.Transient;

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
public class ScoreLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 积分 */
	private java.lang.Integer score;
	private java.lang.Integer userRole;
	/** 用户ID */
	private java.lang.Long userId;
	/** 消费时间 */
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;
	private java.lang.Long addTimeStart;
	private java.lang.Long addTimeStop;
	/** 操作人 */
	private java.lang.Long operatorId;
	/** 备注 */
	private java.lang.String remark;
	/** 消费内容 */
	private java.lang.String content;
	/** 积分类型 */
	private Integer scoreType;
	
	/** 开始时间 */
	private String startTime ;
	

	/** 结束时间 */
	private String endTime ;

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public Long convertTimeStrToLong(String strs) throws ParseException{
		String str = strs;
		Long longtime =null;
		 if(isNotEmpty(str)&&str.length()>0) {
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    			Date date  = sdf.parse(str);
				    longtime = date.getTime();
	     }
		
		return longtime;
	}
	
	@Transient
	public Date getStartTimeDate() throws ParseException {
		return dateUtils.longToDate(convertTimeStrToLong(this.startTime));
	}
	
	@Transient
	public Date getEndTimeDate() throws ParseException {
		return dateUtils.longToDate(convertTimeStrToLong(this.endTime));
	}

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Integer getScore() {
		return this.score;
	}
	
	public void setScore(java.lang.Integer value) {
		this.score = value;
	}
	
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
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
	
	public java.lang.Long getOperatorId() {
		return this.operatorId;
	}
	
	public void setOperatorId(java.lang.Long value) {
		this.operatorId = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public Integer getScoreType() {
		return this.scoreType;
	}
	
	public void setScoreType(Integer value) {
		this.scoreType = value;
	}
	
	public Integer getUserRole() {
		return this.userRole;
	}
	
	public void setUserRole(Integer value) {
		this.userRole = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

