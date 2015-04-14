/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.sys.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.up72.base.BaseQuery;
/**
 * 查询辅助bean
 * 
 * @author sys
 * @version 1.0
 * @since 1.0
 */
public class SysCategoryQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** guid */
	private java.lang.String guid;
	/** catName */
	private java.lang.String catName;
	/** parentGuid */
	private java.lang.String parentGuid;
	/** configSource */
	private java.lang.String configSource;
	/** configId */
	private java.lang.Long configId;
	/** contentModelId */
	private java.lang.Long contentModelId;
	/** listUrlPath */
	private java.lang.String listUrlPath;
	/** configUrlPath */
	private java.lang.Integer configUrlPath;
	/** sortId */
	private java.lang.Long sortId;
	/** addTime */
	private java.lang.Long addTime;
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getGuid() {
		return this.guid;
	}
	
	public void setGuid(java.lang.String value) {
		this.guid = value;
	}
	
	public java.lang.String getCatName() {
		return this.catName;
	}
	
	public void setCatName(java.lang.String value) {
		this.catName = value;
	}
	
	public java.lang.String getParentGuid() {
		return this.parentGuid;
	}
	
	public void setParentGuid(java.lang.String value) {
		this.parentGuid = value;
	}
	
	public java.lang.String getConfigSource() {
		return this.configSource;
	}
	
	public void setConfigSource(java.lang.String value) {
		this.configSource = value;
	}
	
	public java.lang.Long getConfigId() {
		return this.configId;
	}
	
	public void setConfigId(java.lang.Long value) {
		this.configId = value;
	}
	
	public java.lang.Long getContentModelId() {
		return this.contentModelId;
	}
	
	public void setContentModelId(java.lang.Long value) {
		this.contentModelId = value;
	}
	
	public java.lang.String getListUrlPath() {
		return this.listUrlPath;
	}
	
	public void setListUrlPath(java.lang.String value) {
		this.listUrlPath = value;
	}
	
	public java.lang.Integer getConfigUrlPath() {
		return this.configUrlPath;
	}
	
	public void setConfigUrlPath(java.lang.Integer value) {
		this.configUrlPath = value;
	}
	
	public java.lang.Long getSortId() {
		return this.sortId;
	}
	
	public void setSortId(java.lang.Long value) {
		this.sortId = value;
	}
	
	public java.lang.Long getAddTime() {
		return addTime;
	}

	public void setAddTime(java.lang.Long addTime) {
		this.addTime = addTime;
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

