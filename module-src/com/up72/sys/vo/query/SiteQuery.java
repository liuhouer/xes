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
 * @author up72
 * @version 1.0
 * @since 1.0
 */
public class SiteQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** name */
	private java.lang.String name;
	/** folderPath */
	private java.lang.String folderPath;
	/** url */
	private java.lang.String url;
	/** description */
	private java.lang.String description;
	/** pageTemplate */
	private java.lang.Long pageTemplate;
	/** detailTemplate */
	private java.lang.Long detailTemplate;
	/** addTime */
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;
	/** metaKeyword */
	private java.lang.String metaKeyword;
	/** metaDesc */
	private java.lang.String metaDesc;
	/** status */
	private java.lang.Integer status;
	/** code */
	private java.lang.String code;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getFolderPath() {
		return this.folderPath;
	}
	
	public void setFolderPath(java.lang.String value) {
		this.folderPath = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.Long getPageTemplate() {
		return this.pageTemplate;
	}
	
	public void setPageTemplate(java.lang.Long value) {
		this.pageTemplate = value;
	}
	
	public java.lang.Long getDetailTemplate() {
		return this.detailTemplate;
	}
	
	public void setDetailTemplate(java.lang.Long value) {
		this.detailTemplate = value;
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
	
	public java.lang.String getMetaKeyword() {
		return this.metaKeyword;
	}
	
	public void setMetaKeyword(java.lang.String value) {
		this.metaKeyword = value;
	}
	
	public java.lang.String getMetaDesc() {
		return this.metaDesc;
	}
	
	public void setMetaDesc(java.lang.String value) {
		this.metaDesc = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

