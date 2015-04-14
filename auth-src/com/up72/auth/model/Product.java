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

import com.up72.auth.dao.PermissionGroupDao;
import com.up72.auth.dao.RoleDao;
import com.up72.auth.service.ProductAboutManager;
import com.up72.base.BaseEntity;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 产品
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_product")
public class Product extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "产品";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_IMG_PATH = "图标";
	public static final String ALIAS_SORT_ID = "排序";
	public static final String ALIAS_CODE = "标示码";
	
	public static final String ALIAS_STATUS = "类型";
	public static final String ALIAS_DASHBOARD_URL = "产品控制面板";
	public static final String ALIAS_TAG = "分组标签"; 
	
	//date formats

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 名称*/
	@NotBlank @Length(max=100)
	private java.lang.String name;
	
	/** 描述*/
	@Length(max=500)
	private java.lang.String description;
	private String imgPath;
	private Long sortId;
	private String code;
		
	private Integer status; 
	private String dashboardUrl;
	private String tag;
	
	//columns END


	public Product(){
	}

	public Product(
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
		if(obj instanceof Product == false) return false;
		if(this == obj) return true;
		Product other = (Product)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	/**
	 * 获得该产品下的所有权限组
	 */
	@Transient
	public List<PermissionGroup> getPermissionGroupList(){
		List<PermissionGroup> result = null;
		if(ObjectUtils.isNotEmpty(this.id)){
			PermissionGroupDao permissionGroupDao = (PermissionGroupDao) ApplicationContextHolder.getBean("permissionGroupDao");
			result = permissionGroupDao.getPermissionGroupList(this.code);
		}
		return result;
	}
	
	@Transient
	public List<Role> getRoleList(){
		List<Role> result = null; 
		if(ObjectUtils.isNotEmpty(this.id)){
			RoleDao roleDao = (RoleDao) ApplicationContextHolder.getBean("roleDao");
			result = roleDao.findList("from Role where productCode=?", new Object[]{this.code}, 0, null);
		}
		return result;
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
	
	@Column(name = "DASHBOARD_URL", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public String getDashboardUrl() {
		return dashboardUrl;
	}

	public void setDashboardUrl(String dashboardUrl) {
		this.dashboardUrl = dashboardUrl;
	}
	
	/**
	 * 产品是否有关于信息
	 * @return boolean   true有，false没有
	 */
	@Transient
	public boolean getAboutFlag(){
		boolean flag = false;
		ProductAboutManager productAboutManager = (ProductAboutManager)ApplicationContextHolder.getBean("productAboutManager");
		List<ProductAbout> list = productAboutManager.getProductAboutByProductId(this.code);
		if(list != null && list.size()>0){
			flag = true;
		}
		return flag;
	}
}

