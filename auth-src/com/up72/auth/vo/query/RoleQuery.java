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
 * 权限角色表查询辅助bean
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
public class RoleQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	private java.lang.String name;
	/** 所属权限组 */
	private java.lang.Long workGroupId;
	private java.lang.String productCode;
	/** 描述 */
	private java.lang.String description;
	/** 备注 */
	private java.lang.String remark;
	/** 是否启用 1为启用 */
	private Integer enabled;
	/** 所属机构 */
	private java.lang.Long organizationId;
	/** 创建者 */
	private java.lang.Long createUserId;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	
	public java.lang.Long getWorkGroupId() {
		return workGroupId;
	}

	public void setWorkGroupId(java.lang.Long workGroupId) {
		this.workGroupId = workGroupId;
	}

	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
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
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getProductCode() {
		return productCode;
	}

	public void setProductCode(java.lang.String productId) {
		this.productCode = productId;
	}

	public java.lang.Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(java.lang.Long createUserId) {
		this.createUserId = createUserId;
	}
	
}

