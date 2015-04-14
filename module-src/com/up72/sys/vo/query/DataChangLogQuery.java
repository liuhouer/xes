/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.sys.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.up72.base.BaseQuery;
/**
 * 查询辅助bean
 * 
 * @author up72
 * @version 1.0
 * @since 1.0
 */
public class DataChangLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** changTable */
	private java.lang.String changTable;
	/** changId */
	private java.lang.Long changId;
	/** changTime */
	private java.lang.Long changTimeBegin;
	private java.lang.Long changTimeEnd;
	/** changType */
	private java.lang.String changType;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getChangTable() {
		return this.changTable;
	}
	
	public void setChangTable(java.lang.String value) {
		this.changTable = value;
	}
	
	public java.lang.Long getChangId() {
		return this.changId;
	}
	
	public void setChangId(java.lang.Long value) {
		this.changId = value;
	}
	
	public java.lang.Long getChangTimeBegin() {
		return this.changTimeBegin;
	}
	
	public void setChangTimeBegin(java.lang.Long value) {
		this.changTimeBegin = value;
	}	
	
	public java.lang.Long getChangTimeEnd() {
		return this.changTimeEnd;
	}
	
	public void setChangTimeEnd(java.lang.Long value) {
		this.changTimeEnd = value;
	}
	
	public java.lang.String getChangType() {
		return this.changType;
	}
	
	public void setChangType(java.lang.String value) {
		this.changType = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

