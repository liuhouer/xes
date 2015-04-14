/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.auth.model;

import static com.up72.common.CommonUtils.dateUtils;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import com.up72.auth.service.MenuManager;
import com.up72.base.BaseEntity;
import com.up72.framework.util.holder.ApplicationContextHolder;
/**
 * 
 * 
 * @author up72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_menu")
public class Menu extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "菜单";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_PARENT_ID = "父级菜单";
	public static final String ALIAS_PERMISSION_ID = "权限";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_ICON = "图标";
	public static final String ALIAS_SORT_ID = "排序";
	public static final String ALIAS_LEVEL = "级别";
	public static final String ALIAS_ROLE_ID = "角色";
	public static final String ALIAS_ADD_TIME = "添加时间";
	
	//date formats
	public static final String FORMAT_ADD_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** */
	
	private java.lang.Long parentId;
	
	/** */
	
	private java.lang.Long permissionId;
	
	/** */
	@Length(max=256)
	private java.lang.String name;
	
	/** */
	@Length(max=256)
	private java.lang.String icon;
	
	/** */
	
	private java.lang.Integer sortId;
	
	/** */
	
	private java.lang.Integer level;
	
	/** */
	
	private java.lang.Long roleId;
	
	/** */
	
	private java.lang.Long addTime;
	
	//columns END


	public Menu(){
	}

	public Menu(
		java.lang.Long id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getId() {
		return this.id;
	}
	
	@Column(name = "PARENT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.Long value) {
		this.parentId = value;
	}
	
	@Column(name = "PERMISSION_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getPermissionId() {
		return this.permissionId;
	}
	
	public void setPermissionId(java.lang.Long value) {
		this.permissionId = value;
	}
	
	@Column(name = "NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "ICON", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getIcon() {
		return this.icon;
	}
	
	public void setIcon(java.lang.String value) {
		this.icon = value;
	}
	
	@Column(name = "SORT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSortId() {
		return this.sortId;
	}
	
	public void setSortId(java.lang.Integer value) {
		this.sortId = value;
	}
	
	@Column(name = "LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getLevel() {
		return this.level;
	}
	
	public void setLevel(java.lang.Integer value) {
		this.level = value;
	}
	
	@Column(name = "ROLE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(java.lang.Long value) {
		this.roleId = value;
	}
	
	@Transient
	public Date getAddTimeDate() {
		return dateUtils.longToDate(this.addTime);
	}
	@Column(name = "ADD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAddTime() {
		return this.addTime;
	}
	
	public void setAddTime(java.lang.Long value) {
		this.addTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ParentId",getParentId())
			.append("PermissionId",getPermissionId())
			.append("Name",getName())
			.append("Icon",getIcon())
			.append("SortId",getSortId())
			.append("Level",getLevel())
			.append("RoleId",getRoleId())
			.append("AddTime",getAddTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Menu == false) return false;
		if(this == obj) return true;
		Menu other = (Menu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Transient
	public List getChildListById(){
		List result = null;
		MenuManager menuManager = (MenuManager)ApplicationContextHolder.getBean("menuManager");
		/*
		MenuManager menuManager = (MenuManager)ApplicationContextHolder.getBean("menuManager");
		HashMap<String,Object> params = new HashMap<String, Object>();
		params.put("parentId", this.id);
		TreeMap<String, String> orderMap = new TreeMap<String, String>();
		orderMap.put("desc", "sortId");
		result = menuManager.findList(params, null, orderMap);
		*/
		return result;
	}
}

