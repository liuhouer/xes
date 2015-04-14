/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.model;

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

import com.up72.auth.dao.PermissionGroupDao;
import com.up72.auth.dao.ProductDao;
import com.up72.base.BaseEntity;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 权限表（菜单，资源）
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_permission")
public class Permission extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "权限";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PERMISSION_GROUP_ID = "所属权限组";
	public static final String ALIAS_NAME = "权限名称";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_URL = "链接地址";
	public static final String ALIAS_ENABLED = "是否启用";
	public static final String ALIAS_SORT = "排序";
	// 权限类型 1为菜单权限、2操作权限、3tag权限
	public static final String ALIAS_TYPE = "权限类型";
	public static final String ALIAS_PRODUCT_ID = "所属产品";
	public static final String ALIAS_CODE = "标示码";
	public static final String ALIAS_IMG_PATH = "图标";
	public static final String ALIAS_STATUS = "类型";
	public static final String ALIAS_TAG = "分组标签";

	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/** */

	private java.lang.Long id;

	/** */
	@NotNull
	private java.lang.String permissionGroupCode;
	@Deprecated
	private java.lang.Long permissionGroupId;

	/** 权限名称 */
	@NotBlank
	@Length(max = 100)
	private java.lang.String name;

	/** 描述 */
	@Length(max = 500)
	private java.lang.String description;

	/** 链接地址 */
	@NotBlank
	@Length(max = 256)
	private java.lang.String url;
	/** 图标 */
	private java.lang.String imgPath;

	/** 是否启用 1为启用 */
	@NotNull
	@Max(127)
	private Integer enabled;

	/** 排序 */

	private java.lang.Integer sortId;

	/** 权限类型 */
	@NotNull
	private java.lang.Integer type;

	/** */
	private java.lang.String productCode;
	@Deprecated
	private java.lang.Long productId;
	private String code;

	private Integer status;
	private String tag;

	// columns END

	public Permission() {
	}

	public Permission(java.lang.Long id) {
		this.id = id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "increment")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getId() {
		return this.id;
	}

	@Column(name = "NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	@Column(name = "TAG", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public java.lang.String getTag() {
		return this.tag;
	}

	public void setTag(java.lang.String value) {
		this.tag = value;
	}

	@Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	@Column(name = "URL", unique = false, nullable = false, insertable = true, updatable = true, length = 256)
	public java.lang.String getUrl() {
		return this.url;
	}

	public void setUrl(java.lang.String value) {
		this.url = value;
	}

	@Column(name = "ENABLED", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer value) {
		this.enabled = value;
	}

	@Column(name = "SORT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSortId() {
		return this.sortId;
	}

	public void setSortId(java.lang.Integer value) {
		this.sortId = value;
	}

	@Column(name = "TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getType() {
		return this.type;
	}

	public void setType(java.lang.Integer value) {
		this.type = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getId()).append("Name", getName()).append(
						"Description", getDescription())
				.append("Url", getUrl()).append("Enabled", getEnabled())
				.append("Sort", getSortId()).append("Type", getType())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Permission == false)
			return false;
		if (this == obj)
			return true;
		Permission other = (Permission) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	/*
	 * 获取权限组
	 */
	@Transient
	public PermissionGroup getPermissionGroup() {
		if (null == this.permissionGroupCode
				|| this.permissionGroupCode.trim().equals("")) {
			return null;
		} else {
			PermissionGroupDao permissionGroupDao = (PermissionGroupDao) ApplicationContextHolder
					.getBean("permissionGroupDao");
			return permissionGroupDao
					.getPermissionGroupByCode(permissionGroupCode);
		}
	}

	/*
	 * 获取所属机构
	 */
	@Transient
	public Product getProduct() {
		if (null == this.productCode || this.productCode.trim().equals("")) {
			return null;
		} else {
			ProductDao productDao = (ProductDao) ApplicationContextHolder
					.getBean("productDao");
			return productDao.getProductByCode(code);
		}
	}

	@Column(name = "IMG_PATH", unique = false, nullable = false, insertable = true, updatable = true, length = 256)
	public java.lang.String getImgPath() {
		return imgPath;
	}

	public void setImgPath(java.lang.String imgPath) {
		this.imgPath = imgPath;
	}

	@Column(name = "CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "STATUS", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Transient
	public String getPermProductCode() {
		String result = null;
		if (null != permissionGroupCode
				&& !permissionGroupCode.trim().equals("")) {
			PermissionGroupDao permissionGroupDao = (PermissionGroupDao) ApplicationContextHolder
					.getBean("permissionGroupDao");
			PermissionGroup permissionGroup = permissionGroupDao
					.getPermissionGroupByCode(permissionGroupCode);
			if (null != permissionGroup) {
				result = permissionGroup.getProductCode();
			}
		}
		return result;
	}

	@Column(name = "PERMISSION_GROUP_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPermissionGroupCode() {
		return permissionGroupCode;
	}

	public void setPermissionGroupCode(java.lang.String permissionGroupCode) {
		this.permissionGroupCode = permissionGroupCode;
	}

	@Column(name = "PRODUCT_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Column(name = "PERMISSION_GROUP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getPermissionGroupId() {
		return permissionGroupId;
	}

	public void setPermissionGroupId(java.lang.Long permissionGroupId) {
		this.permissionGroupId = permissionGroupId;
	}

	@Column(name = "PRODUCT_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getProductId() {
		return productId;
	}

	public void setProductId(java.lang.Long productId) {
		this.productId = productId;
	}
}
