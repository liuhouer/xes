/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.model;

import java.util.HashMap;
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

import com.up72.auth.dao.OrganizationProductDao;
import com.up72.auth.dao.WorkGroupDao;
import com.up72.auth.service.OrganizationManager;
import com.up72.auth.service.WorkGroupManager;
import com.up72.base.BaseEntity;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 权限机构
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_organization")
public class Organization extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "权限机构";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_PARENT = "上级机构";
	public static final String ALIAS_DOMAIN = "域名";
	public static final String ALIAS_REMARK = "描述";
	public static final String ALIAS_ENABLED = "是否启用";
	public static final String ALIAS_OLEVEL = "级别";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;

	/** 机构名称 */
	@NotBlank @Length(max=255)
	private java.lang.String name;
	
	/** 上级机构*/
	
	private java.lang.Long parent;
	
	/** 域名*/
	@NotBlank @Length(max=125)
	private java.lang.String domain;
	
	/** 备注*/
	@Length(max=65535)
	private java.lang.String remark;
	
	/** 是否启用*/
	@NotNull @Max(127)
	private Integer enabled;
	
	/** 级别*/
	@NotNull 
	private java.lang.Integer olevel;
	
	//columns END


	public Organization(){
	}

	public Organization(
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
	
	@Column(name = "PARENT", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getParent() {
		return this.parent;
	}
	
	public void setParent(java.lang.Long value) {
		this.parent = value;
	}
	
	@Column(name = "DOMAIN", unique = false, nullable = false, insertable = true, updatable = true, length = 125)
	public java.lang.String getDomain() {
		return this.domain;
	}
	
	public void setDomain(java.lang.String value) {
		this.domain = value;
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
	
	@Column(name = "O_LEVEL", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOlevel() {
		return this.olevel;
	}
	
	public void setOlevel(java.lang.Integer value) {
		this.olevel = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Parent",getParent())
			.append("Domain",getDomain())
			.append("Remark",getRemark())
			.append("Enabled",getEnabled())
			.append("Olevel",getOlevel())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Organization == false) return false;
		if(this == obj) return true;
		Organization other = (Organization)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public List<WorkGroup> getWorkGroupList(){
		WorkGroupManager workGroupManager = (WorkGroupManager)ApplicationContextHolder.getBean("workGroupManager");
		return workGroupManager.getWorkGroupList(this.id);
	}
	
	/**
	 * 获得该机构下的所有角色
	 * 
	 */
	@Transient
	public List<Role> getRoleList() {
		OrganizationManager organizationManager = (OrganizationManager) ApplicationContextHolder.getBean("organizationManager");
		return organizationManager.getRoleByOrganizationId(this.id);
	}
	
	/**
	 * 获得该机构下的所有产品
	 * 
	 * @author wqtan
	 */
	@Transient
	public List<Product> getProductList() {
		List<Product> result = null;
		if (ObjectUtils.isNotEmpty(this.id)) {
			OrganizationProductDao organizationProductDao = (OrganizationProductDao) ApplicationContextHolder
					.getBean("organizationProductDao");
			result = organizationProductDao.getProductList(this.id);
		}
		return result;
	}
	
	/**
	 * 判断是否有部门
	 * create zjliu 2012-12-18 
	 * @return
	 */
	@Transient
	public boolean getIsParent() {
		if (getId() != null && 0 != getId()) {
			WorkGroupDao workGroupDao = (WorkGroupDao) ApplicationContextHolder.getBean("workGroupDao");
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("organizationId", this.id);
			List<WorkGroup> list = workGroupDao.findList(params, 0, null);
			if (list != null && list.size()>0) {
				return true;
			}
		}
		return false;
	}
}

