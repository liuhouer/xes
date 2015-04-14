/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.model;

import javax.persistence.*;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import java.util.*;

import com.up72.base.*;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.service.AreaManager;
import com.xes.jtzs.service.CityManager;
import com.xes.jtzs.service.ProvinceManager;
/**
 * 
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "xes_jtzs_school")
public class School extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "学校";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "学校名称";
	public static final String ALIAS_AREA_ID = "区县";
	public static final String ALIAS_SORT = "排序";
	public static final String ALIAS_EN_NAME = "学校名称拼音";
	public static final String ALIAS_STATUS = "是否禁用";//(1为显示，0为不显示)
	public static final String ALIAS_DIVISIONS = "年部";
	public static final String ALIAS_CITY_ID = "城市";
	public static final String ALIAS_PROVINCE_ID = "省份";
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 学校名称*/
	@Length(max=100)
	private java.lang.String name;
	
	/** 区县ID*/
	
	private java.lang.Long areaId;
	
	/** 排序*/
	
	private java.lang.Long sort;
	
	/** 学校名称拼音*/
	@Length(max=400)
	private java.lang.String enName;
	
	/** 是否显示(1为显示，0为不显示)*/
	@Max(127)
	private Integer status;
	
	/** 年部*/
	@Length(max=100)
	private String divisions;
	/** 城市ID*/
	private java.lang.Long cityId;
	/** 省份ID*/
	
	private java.lang.Long provinceId;
	//columns END


	public School(){
	}

	public School(
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
	
	@Column(name = "NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "AREA_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAreaId() {
		return this.areaId;
	}
	
	public void setAreaId(java.lang.Long value) {
		this.areaId = value;
	}
	
	@Column(name = "SORT", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getSort() {
		return this.sort;
	}
	
	public void setSort(java.lang.Long value) {
		this.sort = value;
	}
	
	@Column(name = "EN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 400)
	public java.lang.String getEnName() {
		return this.enName;
	}
	
	public void setEnName(java.lang.String value) {
		this.enName = value;
	}
	
	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	@Column(name = "DIVISIONS", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getDivisions() {
		return this.divisions;
	}
	
	public void setDivisions(String value) {
		this.divisions = value;
	}
	
	@Column(name = "CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCityId() {
		return this.cityId;
	}
	
	public void setCityId(java.lang.Long value) {
		this.cityId = value;
	}
	@Column(name = "PROVINCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getProvinceId() {
		return this.provinceId;
	}
	
	public void setProvinceId(java.lang.Long value) {
		this.provinceId = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("AreaId",getAreaId())
			.append("Sort",getSort())
			.append("EnName",getEnName())
			.append("Status",getStatus())
			.append("Divistions",getDivisions())
			.append("CityId",getCityId())
			.append("ProvinceId",getProvinceId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof School == false) return false;
		if(this == obj) return true;
		School other = (School)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public City getCity(){
		CityManager cityManager = (CityManager)ApplicationContextHolder.getBean("cityManager");
		City city = null;
		if(null != this.getCityId()){
			city = cityManager.getById(this.getCityId());
		}
		if(null == city){
			city = new City();
		}
		return city;
	}
	
	@Transient
	public Province getProvince(){
		ProvinceManager provinceManager = (ProvinceManager)ApplicationContextHolder.getBean("provinceManager");
		Province province = null;
		if(null != this.getProvinceId()){
			province = provinceManager.getById(this.getProvinceId());
		}
		if(null == province){
			province = new Province();
		}
		return province;
	}
	
	@Transient
	public Area getArea(){
		AreaManager areaManager = (AreaManager)ApplicationContextHolder.getBean("areaManager");
		Area area = null;
		if(null != this.getAreaId()){
			area = areaManager.getById(this.getAreaId());
		}
		if(null == area){
			area = new Area();
		}
		return area;
	}
	
	@Transient
	public Map<Integer,String> getDivisionMap(){
		Map<Integer,String> result = new LinkedHashMap<Integer,String>();
		if(null != this.getDivisions()){
			String[] temp = this.getDivisions().replaceAll("[\\]\\[]", "").split(",");
			for (int i = 0; i < temp.length; i++) {
				String divis = temp[i];
				if(divis.matches("\\d+")){
					Integer id = Integer.parseInt(divis);
					result.put(Integer.parseInt(divis),JTZSConstants.Division.getName(id));
				}
			}
		};
		return result;
	}
	
	@Transient
	public String getStatusStr(){
		String result = JTZSConstants.Pubilc.DISABLE.getName();
		if(null != this.status && JTZSConstants.Pubilc.ENABLED.getIndex()==this.status){
			result = JTZSConstants.Pubilc.ENABLED.getName();
		}
		return result;
	}
}

