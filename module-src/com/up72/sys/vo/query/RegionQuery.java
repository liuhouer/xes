/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2011
 */

package com.up72.sys.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.up72.base.BaseQuery;

/**
 * 地区查询辅助bean
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
public class RegionQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** region */
	private java.lang.Integer region;
	/** regionName */
	private java.lang.String regionName;
	/** regionType */
	private java.lang.Boolean regionType;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Integer getRegion() {
		return this.region;
	}
	
	public void setRegion(java.lang.Integer value) {
		this.region = value;
	}
	
	public java.lang.String getRegionName() {
		return this.regionName;
	}
	
	public void setRegionName(java.lang.String value) {
		this.regionName = value;
	}
	
	public java.lang.Boolean getRegionType() {
		return this.regionType;
	}
	
	public void setRegionType(java.lang.Boolean value) {
		this.regionType = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

