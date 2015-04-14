package com.up72.sys.model;

/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.sys.dao.DictionaryDao;


/**
 * 数据字典
 * 
 * @author huikang
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "sys_dictionary")
public class Dictionary implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "数据字典";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_REF_CODE = "引用值";
	public static final String ALIAS_DICTIONARY_KEY = "标识码";
	public static final String ALIAS_SORT = "排序";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_DICTIONARY_ID = "父级所属";
	public static final String ALIAS_VALUE = "字典值";
	public static final String ALIAS_LEVEL = "级别";

	// date formats

	// columns START
	/** */
	private java.lang.Long id;

	/** */
	private java.lang.String name;

	/** */
	private java.lang.String refCode;

	/** */
	private java.lang.Integer sort;
	
	/** */
	private java.lang.String dictionaryKey;

	/** */
	private java.lang.String description;

	/** */
	private java.lang.Long dictionaryId;

	/** */
	private java.lang.String value;

	private java.lang.String path;
	
	private java.lang.Integer level;
	@Deprecated
	private String code;
	@Deprecated
	private Integer sortId;

	// columns END

	public Dictionary() {
	}

	public Dictionary(java.lang.Long id) {
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

	@Column(name = "NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	@Column(name = "REF_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getRefCode() {
		return this.refCode;  
	}

	public void setRefCode(java.lang.String value) {
		this.refCode = value;
	}

	@Column(name = "SORT", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSort() {
		return this.sort;
	}

	public void setSort(java.lang.Integer value) {
		this.sort = value;
	}
	@Column(name = "DICTIONARY_KEY", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getDictionaryKey() {
		return this.dictionaryKey;
	}
	
	public void setDictionaryKey(java.lang.String value) {
		this.dictionaryKey = value;
	}
	
	@Column(name = "LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getLevel() {
		return this.level;
	}

	public void setLevel(java.lang.Integer value) {
		this.level = value;
	}

	@Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	@Column(name = "DICTIONARY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getDictionaryId() {
		return this.dictionaryId;
	}

	public void setDictionaryId(java.lang.Long value) {
		this.dictionaryId = value;
	}

	@Column(name = "VALUE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getValue() {
		return this.value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}

	@Column(name = "PATH", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getPath() {
		return this.path;
	}

	public void setPath(java.lang.String path) {
		this.path = path;
	}


	/** 充血实现begin */

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getId()).append("Name", getName()).append(
						"RefCode", getRefCode()).append("Sort", getSort())
				.append("Description", getDescription()).append("DictionaryId",
						getDictionaryId()).append("Value", getValue())
				.toString();
	}
	@Transient
	public List<Dictionary> getChilds(Long id){
		List<Dictionary> result = null;
		if(id != null && id > 0){
			DictionaryDao dictionaryDao = (DictionaryDao) ApplicationContextHolder.getBean("dictionaryDao");
			result = dictionaryDao.find("from Dictionary where dictionaryId="+id);
		}
		return result;
	}
	/**
	 * 获取子级
	 * @param id
	 * @return
	 */
	@Transient
	public List<Dictionary> getChildDictionary(){
		List<Dictionary> result = null;
		if(this.id != null && this.id > 0){
			DictionaryDao dictionaryDao = (DictionaryDao) ApplicationContextHolder.getBean("dictionaryDao");
			result = dictionaryDao.find("from Dictionary where dictionaryId = " + this.id);
		}
		return result;
	}
	/**
	 * 判断该节点是否是父节点
	 * 
	 * @return
	 */
	@Transient
	public boolean getIsParent() {
		if (getId() != null && 0 != getId()) {
			DictionaryDao dictionaryDao = (DictionaryDao) ApplicationContextHolder.getBean("dictionaryDao");
			Long count = dictionaryDao.findcount("select count(id) from Dictionary where dictionaryId = "
							+ this.getId());
			if (count > 0) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断该节点是否是父节点
	 * 
	 * @return
	 */
	@Transient
	public boolean isParent() {
		if (getId() != null && 0 != getId()) {
			DictionaryDao dictionaryDao = (DictionaryDao) ApplicationContextHolder.getBean("dictionaryDao");
			Long count = dictionaryDao.findcount("select count(id) from Dictionary where dictionaryId = "
					+ this.getId());
			if (count > 0) {
				return true;
			}
		}
		return false;
	}
	@Transient
	public List<Dictionary> getDictionaryList() {
		if (this.getId() != null) {
			DictionaryDao dictionaryDao = (DictionaryDao) ApplicationContextHolder.getBean("dictionaryDao");
			return dictionaryDao.find(
					"from Dictionary where dictionaryId = ? order by sort asc",
					this.getId());
		}
		return new ArrayList<Dictionary>();
	}
	@Transient
	public Dictionary getDictionaryById(Long id){
		Dictionary result = null;
		DictionaryDao dictionaryDao = (DictionaryDao) ApplicationContextHolder.getBean("dictionaryDao");
		if(id > 0){
			result = dictionaryDao.get(id);
		}
		return result;
	}
	
	@Transient
	public Dictionary getDictionaryByDictionaryId() {
		Dictionary result = null;
		if (this.dictionaryId != null && this.dictionaryId > 0) {
			DictionaryDao dictionaryDao = (DictionaryDao) ApplicationContextHolder.getBean("dictionaryDao");
			result = (Dictionary) dictionaryDao.get(this.dictionaryId);
		}
		return result;
	}

	/*@Column(name = "SORT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	@Column(name = "CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}*/
}
