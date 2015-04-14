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
 * 权限机构查询辅助bean
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
public class OrganizationQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	private java.lang.String name;
	/** 上级机构 */
	private java.lang.Long parent;
	/** 域名 */
	private java.lang.String domain;
	/** 备注 */
	private java.lang.String remark;
	/** 是否启用 */
	private Integer enabled;
	/** 级别 */
	private java.lang.Integer olevel;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.Long getParent() {
		return this.parent;
	}
	
	public void setParent(java.lang.Long value) {
		this.parent = value;
	}
	
	public java.lang.String getDomain() {
		return this.domain;
	}
	
	public void setDomain(java.lang.String value) {
		this.domain = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public Integer getEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(Integer value) {
		this.enabled = value;
	}
	
	public java.lang.Integer getOlevel() {
		return this.olevel;
	}
	
	public void setOlevel(java.lang.Integer value) {
		this.olevel = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

}

