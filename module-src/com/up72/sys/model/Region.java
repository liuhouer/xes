/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2011
 */

package com.up72.sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.up72.base.BaseEntity;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.sys.dao.RegionDao;


/**
 * 地区
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "sys_region")
public class Region extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "地区";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_REGION = "父级";
	public static final String ALIAS_REGION_NAME = "名称";
	public static final String ALIAS_REGION_TYPE = "级别";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** */
	@NotNull 
	private java.lang.Integer region;
	
	/** */
	@NotBlank @Length(max=120)
	private java.lang.String regionName;
	
	/** */
	@NotNull 
	private java.lang.Integer regionType;
	
	//columns END


	public Region(){
	}

	public Region(
		java.lang.Long id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getId() {
		return this.id;
	}
	
	@Column(name = "region", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRegion() {
		return this.region;
	}
	
	public void setRegion(java.lang.Integer value) {
		this.region = value;
	}
	
	@Column(name = "region_name", unique = false, nullable = false, insertable = true, updatable = true, length = 120)
	public java.lang.String getRegionName() {
		return this.regionName;
	}
	
	public void setRegionName(java.lang.String value) {
		this.regionName = value;
	}
	
	@Column(name = "region_type", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	public java.lang.Integer getRegionType() {
		return this.regionType;
	}
	
	public void setRegionType(java.lang.Integer value) {
		this.regionType = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Region",getRegion())
			.append("RegionName",getRegionName())
			.append("RegionType",getRegionType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Region == false) return false;
		if(this == obj) return true;
		Region other = (Region)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public Region getParent(){
		Region result = null;
		if(this.region != null
				&& this.region > 0){
			RegionDao regionDao = (RegionDao) ApplicationContextHolder.getBean("regionDao");
			result = regionDao.getById(new Long(this.region));
		}
		return result;
	}
}

