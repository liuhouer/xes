/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.auth.vo.query;

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
public class MenuQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** parentId */
	private java.lang.Long parentId;
	/** permissionId */
	private java.lang.Long permissionId;
	/** name */
	private java.lang.String name;
	/** icon */
	private java.lang.String icon;
	/** sortId */
	private java.lang.Integer sortId;
	/** level */
	private java.lang.Integer level;
	/** roleId */
	private java.lang.Long roleId;
	/** addTime */
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.Long value) {
		this.parentId = value;
	}
	
	public java.lang.Long getPermissionId() {
		return this.permissionId;
	}
	
	public void setPermissionId(java.lang.Long value) {
		this.permissionId = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getIcon() {
		return this.icon;
	}
	
	public void setIcon(java.lang.String value) {
		this.icon = value;
	}
	
	public java.lang.Integer getSortId() {
		return this.sortId;
	}
	
	public void setSortId(java.lang.Integer value) {
		this.sortId = value;
	}
	
	public java.lang.Integer getLevel() {
		return this.level;
	}
	
	public void setLevel(java.lang.Integer value) {
		this.level = value;
	}
	
	public java.lang.Long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(java.lang.Long value) {
		this.roleId = value;
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

