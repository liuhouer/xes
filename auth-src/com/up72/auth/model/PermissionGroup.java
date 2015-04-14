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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.up72.auth.AuthConstants;
import com.up72.auth.dao.PermissionDao;
import com.up72.auth.dao.ProductDao;
import com.up72.base.BaseEntity;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 权限分组表
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_permission_group")
public class PermissionGroup extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "权限分组表";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_PRODUCT_ID = "所属产品";
	public static final String ALIAS_IMG_PATH = "图标";
	public static final String ALIAS_SORT_ID = "排序";
	public static final String ALIAS_CODE = "标示码";
	public static final String ALIAS_STATUS = "类型";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** ID*/
	
	private java.lang.Long id;
	
	/** 权限组名称*/
	@NotBlank @Length(max=30)
	private java.lang.String name;
	
	/** 描述*/
	@Length(max=100)
	private java.lang.String description;
	
	/** 所属产品*/
	private String productCode;
	@Deprecated
	private java.lang.Long productId;
	private String imgPath;
	private Long sortId;
	private String code;
	private Integer status;
	
	//columns END


	public PermissionGroup(){
	}

	public PermissionGroup(
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
	
	@Column(name = "NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Description",getDescription())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PermissionGroup == false) return false;
		if(this == obj) return true;
		PermissionGroup other = (PermissionGroup)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	@Column(name = "IMG_PATH", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	@Column(name = "SORT_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}
	
	/**
	 * 获得所属产品 
	 */
	@Transient
	public Product getProduct(){
		if(null == this.productCode || this.productCode.trim().equals("")){
			return null;
		}else{
			ProductDao productDao = (ProductDao)ApplicationContextHolder.getBean("productDao");
			return productDao.getProductByCode(this.productCode);
		}
	}
	
	/**
	 * 获得该权限组下的所有[菜单]权限
	 * @author wqtan
	 */
	@Transient
	public List<Permission> getPermissionList(){
		List<Permission> result = null;
		if(ObjectUtils.isNotEmpty(this.code)){
			PermissionDao permissionDao = (PermissionDao) ApplicationContextHolder.getBean("permissionDao"); 
			result = permissionDao.getPermissionList(this.code,null);
		}
		return result;
	}
	
	/**
	 * 获得该权限组下的所有[菜单]权限
	 * @author wqtan
	 */
	@Transient
	public List<Permission> getMenuPermList(){
		List<Permission> result = null;
		if(ObjectUtils.isNotEmpty(this.code)){
			PermissionDao permissionDao = (PermissionDao) ApplicationContextHolder.getBean("permissionDao"); 
			result = permissionDao.getPermissionList(this.code,AuthConstants.PERMISSION_TYPE_MENU);
		}
		return result;
	}
	/**
	 * 获得该权限组下的所有[操作]权限
	 * @author wqtan
	 */
	@Transient
	public List<Permission> getOptionPermList(){
		List<Permission> result = null;
		if(ObjectUtils.isNotEmpty(this.code)){
			PermissionDao permissionDao = (PermissionDao) ApplicationContextHolder.getBean("permissionDao"); 
			result = permissionDao.getPermissionList(this.code,AuthConstants.PERMISSION_TYPE_OPTION);
		}
		return result;
	}
	/**
	 * 获得该权限组下的所有[选项卡]权限
	 * @author wqtan
	 */
	@Transient
	public List<Permission> getTagPermList(){
		List<Permission> result = null;
		if(ObjectUtils.isNotEmpty(this.code)){
			PermissionDao permissionDao = (PermissionDao) ApplicationContextHolder.getBean("permissionDao"); 
			result = permissionDao.getPermissionList(this.code,AuthConstants.PERMISSION_TYPE_TAB);
		}
		return result;
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

	@Column(name = "PRODUCT_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	

	@Column(name = "PRODUCT_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getProductId() {
		return productId;
	}

	public void setProductId(java.lang.Long productId) {
		this.productId = productId;
	}
}

