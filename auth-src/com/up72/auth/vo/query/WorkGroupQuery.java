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
 * 权限工作组（部门）查询辅助bean
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
public class WorkGroupQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 部门名称 */
	private java.lang.String name;
	/** 创建时间 */
	private java.lang.Long addTime;
	/** 备注 */
	private java.lang.String remark;
	/** 是否启用 */
	private Integer enabled;
	/** 所属机构 */
	private java.lang.Long organizationId;
	/** 级别 */
	private java.lang.Integer deptLevel;

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
	
	public java.lang.Long getAddTime() {
		return this.addTime;
	}
	
	public void setAddTime(java.lang.Long value) {
		this.addTime = value;
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
	
	public java.lang.Long getOrganizationId() {
		return this.organizationId;
	}
	
	public void setOrganizationId(java.lang.Long value) {
		this.organizationId = value;
	}
	
	public java.lang.Integer getDeptLevel() {
		return this.deptLevel;
	}
	
	public void setDeptLevel(java.lang.Integer value) {
		this.deptLevel = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

