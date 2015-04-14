/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.model;

import static com.up72.common.CommonUtils.dateUtils;

import java.util.Date;
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

import com.up72.auth.dao.OrganizationDao;
import com.up72.auth.dao.WorkGroupDao;
import com.up72.auth.service.WorkGroupManager;
import com.up72.base.BaseEntity;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 权限工作组（部门）
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_work_group")
public class WorkGroup extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "部门";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "部门名称";
	public static final String ALIAS_ADD_TIME = "创建时间";
	public static final String ALIAS_REMARK = "描述";
	public static final String ALIAS_ENABLED = "是否启用";
	public static final String ALIAS_ORGANIZATION_ID = "所属机构";
	public static final String ALIAS_DEPT_LEVEL = "级别";
	public static final String ALIAS_PARENT = "上级部门";
	public static final String ALIAS_PRODUCT_LIST = "用户组职能";
	public static final String ALIAS_MANAGER = "部门管理员";

	// date formats

	// columns START
	/** */

	private java.lang.Long id;

	/** 部门名称 */
	@NotBlank
	@Length(max = 125)
	private java.lang.String name;

	/** 上级部门 */
	private java.lang.Long parent;
	/** 创建时间 */
	@NotNull
	private java.lang.Long addTime;

	/** 备注 */
	@Length(max = 65535)
	private java.lang.String remark;

	/** 是否启用 */
	@NotNull
	@Max(127)
	private Integer enabled;

	/** 所属机构 */
	private java.lang.Long organizationId;

	/** 级别 */
	private java.lang.Integer deptLevel;

	// columns END

	public WorkGroup() {
	}

	public WorkGroup(java.lang.Long id) {
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "increment")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getId() {
		return this.id;
	}

	@Column(name = "NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 125)
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	@Column(name = "PARENT", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getParent() {
		return this.parent;
	}

	public void setParent(java.lang.Long value) {
		this.parent = value;
	}

	@Transient
	public Date getAddTimeDate() {
		return dateUtils.longToDate(this.addTime);
	}

	@Column(name = "ADD_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAddTime() {
		return this.addTime;
	}

	public void setAddTime(java.lang.Long value) {
		this.addTime = value;
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

	@Column(name = "DEPT_LEVEL", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDeptLevel() {
		return this.deptLevel;
	}

	public void setDeptLevel(java.lang.Integer value) {
		this.deptLevel = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getId()).append("Name", getName()).append(
						"AddTime", getAddTime()).append("Remark", getRemark())
				.append("Enabled", getEnabled()).append("OrganizationId",
						getOrganizationId())
				.append("DeptLevel", getDeptLevel()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WorkGroup == false)
			return false;
		if (this == obj)
			return true;
		WorkGroup other = (WorkGroup) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@Transient
	public Organization getOrganization() {
		if (this.organizationId == null || this.organizationId == 0) {
			return null;
		} else {
			OrganizationDao organizationDao = (OrganizationDao) ApplicationContextHolder
					.getBean("organizationDao");
			return organizationDao.getById(this.organizationId);
		}
	}

	/**
	 * 获得该部门下的所有角色
	 * 
	 * @author wqtan
	 */
	@Transient
	public List<Role> getRoleList() {
		WorkGroupManager workGroupManager = (WorkGroupManager) ApplicationContextHolder.getBean("workGroupManager");
		return workGroupManager.getRoleByWorkGroupId(this.id);
	}

	/**
	 * 获得该部门下的所有产品
	 * 
	 * @author wqtan
	 */
	@Transient
	public List<Product> getProductList() {
		List<Product> result = null;
		if (ObjectUtils.isNotEmpty(this.id)) {
//			OrganizationProductDao organizationProductDao = (OrganizationProductDao) ApplicationContextHolder
//					.getBean("workGroupProductDao");
//			result = organizationProductDao.getProductList(this.id);
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
			params.put("parent", this.id);
			List<WorkGroup> list = workGroupDao.findList(params, 0, null);
			if (list != null && list.size()>0) {
				return true;
			}
		}
		return false;
	}

}
