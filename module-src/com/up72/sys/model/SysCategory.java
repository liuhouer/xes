/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.sys.model;

import static com.up72.common.CommonUtils.dateUtils;

import java.util.Date;
import java.util.HashMap;

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

import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.sys.dao.SysCategoryDao;
/**
 * 
 * 
 * @author sys
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "sys_category")
public class SysCategory extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "系统分类管理";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_GUID = "分类guid";
	public static final String ALIAS_CAT_NAME = "分类名称";
	public static final String ALIAS_PARENT_GUID = "上级分类";
	public static final String ALIAS_LEVEL = "级别";
	public static final String ALIAS_CONFIG_SOURCE = "配置类型";
	public static final String ALIAS_CONFIG_ID = "配置id";
	public static final String ALIAS_CONTENT_MODEL_ID = "内容模块";
	public static final String ALIAS_LIST_URL_PATH = "列表页地址";
	public static final String ALIAS_CONFIG_URL_PATH = "配置页地址";
	public static final String ALIAS_SORT_ID = "排序";
	public static final String ALIAS_ADD_TIME = "添加时间";
	//date formats
	//public static final String FORMAT_ADD_TIME = DATE_FORMAT;

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** */
	@Length(max=255)
	private java.lang.String guid;
	
	/** */
	@Length(max=48)
	private java.lang.String catName;
	
	/** */
	@Length(max=255)
	private java.lang.String parentGuid;
	
	/** */
	@Length(max=255)
	private java.lang.String configSource;
	
	/** */
	private Integer level;
	private java.lang.Long configId;
	
	/** */
	@NotNull 
	private java.lang.Long contentModelId;
	
	/** */
	@Length(max=255)
	private java.lang.String listUrlPath;
	
	/** */
	
	private java.lang.Integer configUrlPath;
	
	/** */
	
	private java.lang.Long sortId;
	
	/** */
	
	private java.lang.Long addTime;
	
	//columns END


	public SysCategory(){
	}

	public SysCategory(
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
	
	@Column(name = "GUID", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getGuid() {
		return this.guid;
	}
	
	public void setGuid(java.lang.String value) {
		this.guid = value;
	}
	
	@Column(name = "CAT_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 48)
	public java.lang.String getCatName() {
		return this.catName;
	}
	
	public void setCatName(java.lang.String value) {
		this.catName = value;
	}
	
	@Column(name = "PARENT_GUID", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getParentGuid() {
		return this.parentGuid;
	}
	
	public void setParentGuid(java.lang.String value) {
		this.parentGuid = value;
	}
	@Column(name = "LEVEL", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@Column(name = "CONFIG_SOURCE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getConfigSource() {
		return this.configSource;
	}
	
	public void setConfigSource(java.lang.String value) {
		this.configSource = value;
	}
	
	@Column(name = "CONFIG_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getConfigId() {
		return this.configId;
	}
	
	public void setConfigId(java.lang.Long value) {
		this.configId = value;
	}
	
	@Column(name = "CONTENT_MODEL_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getContentModelId() {
		return this.contentModelId;
	}
	
	public void setContentModelId(java.lang.Long value) {
		this.contentModelId = value;
	}
	
	@Column(name = "LIST_URL_PATH", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getListUrlPath() {
		return this.listUrlPath;
	}
	
	public void setListUrlPath(java.lang.String value) {
		this.listUrlPath = value;
	}
	
	@Column(name = "CONFIG_URL_PATH", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getConfigUrlPath() {
		return this.configUrlPath;
	}
	
	public void setConfigUrlPath(java.lang.Integer value) {
		this.configUrlPath = value;
	}
	
	@Column(name = "SORT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getSortId() {
		return this.sortId;
	}
	
	public void setSortId(java.lang.Long value) {
		this.sortId = value;
	}
	
	@Transient
	public Date getAddTimeDate() {
		return dateUtils.longToDate(this.addTime);
	}
	@Column(name = "ADD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAddTime() {
		return this.addTime;
	}
	
	public void setAddTime(java.lang.Long value) {
		this.addTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Guid",getGuid())
			.append("CatName",getCatName())
			.append("ParentGuid",getParentGuid())
			.append("ConfigSource",getConfigSource())
			.append("ConfigId",getConfigId())
			.append("ContentModelId",getContentModelId())
			.append("ListUrlPath",getListUrlPath())
			.append("ConfigUrlPath",getConfigUrlPath())
			.append("SortId",getSortId())
			.append("AddTime",getAddTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SysCategory == false) return false;
		if(this == obj) return true;
		SysCategory other = (SysCategory)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	/**
	 * 统计子分类个数
	 * 
	 * @return int
	 */
	@Transient
	public long getChildCount() {
		SysCategoryDao sysCategoryDao = (SysCategoryDao) ApplicationContextHolder.getBean("sysCategoryDao");
		String hql = "select count(guid) from SysCategory where 1=1";
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (null != this.guid && !"".equals(this.guid)) {
			params.put("parentGuid", this.guid);
		} else {
			params.put("parentGuid", "");
		}
		Long result = sysCategoryDao.findcount(hql, params);
		return result;
	}
	
	@Transient
	public SysCategory getParent() {
		SysCategoryDao sysCategoryDao = (SysCategoryDao)ApplicationContextHolder.getBean("sysCategoryDao");
		if (null != this.parentGuid  && !"".equals(this.parentGuid)) {
			return sysCategoryDao.getByGuid(this.parentGuid);
		} else {
			return null;
		}
	}
}

