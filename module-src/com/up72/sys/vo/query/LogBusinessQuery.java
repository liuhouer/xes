/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.sys.vo.query;

import static com.up72.common.CommonUtils.dateUtils;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.up72.base.BaseQuery;
/**
 * 业务日志表查询辅助bean
 * 
 * @author sys
 * @version 1.0
 * @since 1.0
 */
public class LogBusinessQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 操作人 */
	private java.lang.String userGuid;
	/** 操作时间 */
	private java.lang.Long time;
	private java.lang.Long timeBegin;
	private java.lang.Long timeEnd;
	/** 操作类型 */
	private java.lang.String type;
	/** 操作结果描述 */
	private java.lang.String result;
	/** 操作类型 */
	private java.lang.String ip;
	/** 操作类型 */
	private java.lang.String function;
	/** 日志级别 */
	private java.lang.Boolean level;
	/** 操作类型 */
	private java.lang.String description;
	/** 删除标记:0-正常数据;1-系统删除数据;2-废弃数据 */
	private Integer deleteFlag;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getUserGuid() {
		return this.userGuid;
	}
	
	public void setUserGuid(java.lang.String value) {
		this.userGuid = value;
	}
	@Transient
	public Date getTimeBeginDate(){
		return dateUtils.longToDate(this.timeBegin);
	}
	@Transient
	public Date getTimeEndDate(){
		return dateUtils.longToDate(this.timeEnd);
	}
	public java.lang.Long getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(java.lang.Long timeBegin) {
		this.timeBegin = timeBegin;
	}

	public java.lang.Long getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(java.lang.Long timeEnd) {
		this.timeEnd = timeEnd;
	}

	public java.lang.Long getTime() {
		return time;
	}

	public void setTime(java.lang.Long time) {
		this.time = time;
	}

	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	public java.lang.String getResult() {
		return this.result;
	}
	
	public void setResult(java.lang.String value) {
		this.result = value;
	}
	
	public java.lang.String getIp() {
		return this.ip;
	}
	
	public void setIp(java.lang.String value) {
		this.ip = value;
	}
	
	public java.lang.String getFunction() {
		return this.function;
	}
	
	public void setFunction(java.lang.String value) {
		this.function = value;
	}
	
	public java.lang.Boolean getLevel() {
		return this.level;
	}
	
	public void setLevel(java.lang.Boolean value) {
		this.level = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	
	public void setDeleteFlag(Integer value) {
		this.deleteFlag = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

