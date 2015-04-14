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
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import com.up72.auth.dao.OrganizationDao;
import com.up72.auth.dao.ProductDao;
import com.up72.base.BaseEntity;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 机构与产品
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_organization_product")
public class OrganizationProduct extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "机构与产品";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_ORGANIZATION_ID = "所属机构";
	public static final String ALIAS_PRODUCT_ID = "所属产品";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** ID*/
	
	private java.lang.Long id;
	
	/** 所属机构*/
	@NotNull 
	private java.lang.Long organizationId;
	
	/** 所属产品*/
	@NotNull 
	private java.lang.String productCode;
	
	//columns END


	public OrganizationProduct(){
	}

	public OrganizationProduct(
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
	
	@Column(name = "ORGANIZATION_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getOrganizationId() {
		return this.organizationId;
	}
	
	public void setOrganizationId(java.lang.Long value) {
		this.organizationId = value;
	}
	
	@Column(name = "PRODUCT_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.String getProductCode() {
		return this.productCode;
	}
	
	public void setProductCode(java.lang.String value) {
		this.productCode = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OrganizationId",getOrganizationId())
			.append("ProductId",getProductCode())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OrganizationProduct == false) return false;
		if(this == obj) return true;
		OrganizationProduct other = (OrganizationProduct)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	/**
	 * 获得产品记录
	 * @author wqtan
	 */
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
	 * 获得机构记录
	 * @author wqtan
	 */
	@Transient
	public Organization getOrganization(){
		Organization result = null;
		if(ObjectUtils.isNotEmpty(this.organizationId)){
			OrganizationDao organizationDao = (OrganizationDao) ApplicationContextHolder.getBean("organizationDao");
			result = organizationDao.getById(this.organizationId);
		}
		return result;
	}
}

