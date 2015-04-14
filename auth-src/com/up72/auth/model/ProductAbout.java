/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.auth.model;


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

import com.up72.auth.dao.ProductDao;
import com.up72.base.BaseEntity;
import com.up72.framework.util.holder.ApplicationContextHolder;
/**
 * InnoDB free: 263168 kB
 * 
 * @author auth
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_product_about")
public class ProductAbout extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "产品关于";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PRODUCT_CODE = "所属产品";
	public static final String ALIAS_TITLE = "名称";
	public static final String ALIAS_CONTENT = "页面内容";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** */
	
	private java.lang.String productCode;
	
	/** 名称*/
	@Length(max=50)
	private java.lang.String title;
	
	/** 页面内容*/
	@Length(max=65535)
	private java.lang.String content;
	
	private Integer sortId;
	
	//columns END


	public ProductAbout(){
	}

	public ProductAbout(
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
	
	@Column(name = "PRODUCT_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.String getProductCode() {
		return this.productCode;
	}
	
	public void setProductCode(java.lang.String value) {
		this.productCode = value;
	}
	
	@Column(name = "TITLE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "CONTENT", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Column(name = "SORT_ID", unique = true, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ProductCode",getProductCode())
			.append("Title",getTitle())
			.append("Content",getContent())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ProductAbout == false) return false;
		if(this == obj) return true;
		ProductAbout other = (ProductAbout)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public Product getProduct(){
		Product product = null;
		if(this.getProductCode() != null && !"".equals(this.getProductCode())){
			ProductDao productDao = (ProductDao) ApplicationContextHolder.getBean("productDao");
			product = productDao.getProductByCode(this.getProductCode());
		}
		return product;
	}
}

