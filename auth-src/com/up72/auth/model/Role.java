/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.up72.auth.dao.OrganizationDao;
import com.up72.auth.dao.PermissionDao;
import com.up72.auth.dao.ProductDao;
import com.up72.auth.dao.WorkGroupDao;
import com.up72.base.BaseEntity;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 权限角色表
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_role")
public class Role extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "权限角色";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = " 角色名";
	public static final String ALIAS_WORK_GROUP_ID = "所属用户组";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_ENABLED = "是否启用";
	public static final String ALIAS_ORGANIZATION_ID = "所属机构";
	public static final String ALIAS_PRODUCT_ID = "所属产品";
	public static final String ALIAS_CREATE_USER_ID = "创建者";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 所属权限组*/
	private java.lang.Long workGroupId;
	@NotBlank
	private java.lang.String name;
	/** 描述*/
	@Length(max=100)
	private java.lang.String description;
	
	/** 备注*/
	@Length(max=65535)
	private java.lang.String remark;
	
	/** 是否启用 1为启用*/
	@NotNull @Max(127)
	private Integer enabled;
	
	/** 所属机构*/
	@NotNull 
	private java.lang.Long organizationId;
	
	/** 角色所属产品，用于初始化产品默认角色 */
	@NotNull
	private java.lang.String productCode;
	
	private java.lang.Long createUserId;
	
	//columns END

	public Role(){
	}

	public Role(
		java.lang.Long id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getId() {
		return this.id;
	}

	@Column(name = "NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	@Column(name = "WORK_GROUP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getWorkGroupId() {
		return this.workGroupId;
	}
	
	public void setWorkGroupId(java.lang.Long value) {
		this.workGroupId = value;
	}
	
	@Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	@Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	@Column(name = "ENABLED", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(Integer value) {
		this.enabled = value;
	}
	
	@Column(name = "ORGANIZATION_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getOrganizationId() {
		return this.organizationId;
	}
	
	public void setOrganizationId(java.lang.Long value) {
		this.organizationId = value;
	}


	@Column(name = "PRODUCT_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.String getProductCode() {
		return productCode;
	}

	public void setProductCode(java.lang.String productId) {
		this.productCode = productId;
	}
	
	@Column(name = "CREATE_USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(java.lang.Long createUserId) {
		this.createUserId = createUserId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("workGroupId",getWorkGroupId())
			.append("Description",getDescription())
			.append("Remark",getRemark())
			.append("Enabled",getEnabled())
			.append("OrganizationId",getOrganizationId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Role == false) return false;
		if(this == obj) return true;
		Role other = (Role)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public Organization getOrganization(){
		if(this.organizationId == null || this.organizationId==0){
			return null;
		}else{
			OrganizationDao organizationDao = (OrganizationDao) ApplicationContextHolder.getBean("organizationDao");
			return organizationDao.getById(this.organizationId);
		}
	}
	
	@Transient
	public WorkGroup getWorkGroup(){
		WorkGroup  result = null;
		if(ObjectUtils.isNotEmpty(this.workGroupId)){
			WorkGroupDao workGroupDao = (WorkGroupDao) ApplicationContextHolder.getBean("workGroupDao");
			result = workGroupDao.getById(this.workGroupId); 
			//result = workGroupDao.getById(this.organizationId);
		}
		return result;
	}
	
	@Transient
	public Product getProduct(){
		Product result = null;
		if(ObjectUtils.isNotEmpty(this.productCode)){
			ProductDao productDao = (ProductDao) ApplicationContextHolder.getBean("productDao");
			result = productDao.getProductByCode(this.productCode);
		}
		return result;
	}
	
	/**
	 * 获得该角色下的权限列表
	 */
	@Transient
	public List<Permission> getPermissionList(Integer type){
		List<Permission> result = null;
		if(ObjectUtils.isNotEmpty(this.id)){
			PermissionDao permissionDao = (PermissionDao) ApplicationContextHolder.getBean("permissionDao");
			result = permissionDao.getRolePermissionList(this.id,type);
		}
		return result;	
	}
	
}

