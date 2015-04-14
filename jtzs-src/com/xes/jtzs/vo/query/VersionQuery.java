/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */

package com.xes.jtzs.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import static com.up72.common.CommonUtils.*;
import java.io.Serializable;

import java.util.*;

import com.up72.base.*;
import com.up72.util.*;

import com.up72.framework.util.*;
import com.up72.framework.web.util.*;
import com.up72.framework.page.*;
import com.up72.framework.page.impl.*;

import com.xes.jtzs.model.*;
import com.xes.jtzs.dao.*;
import com.xes.jtzs.service.*;
import com.xes.jtzs.vo.query.*;/**
 * 查询辅助bean
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
public class VersionQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 系统类型 1 ios 0 android */
	private Integer type;
	/** 版本号 */
	private java.lang.String version;
	/** 大小 */
	private Integer size;
	/** 升级包地址 */
	private java.lang.String appUrl;
	/** 更新日志 */
	private java.lang.String updateInfo;
	/** 创建时间 */
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;
	/** 更新时间 */
	private java.lang.Long updateTimeBegin;
	private java.lang.Long updateTimeEnd;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer value) {
		this.type = value;
	}
	
	public java.lang.String getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.String value) {
		this.version = value;
	}
	
	public Integer getSize() {
		return this.size;
	}
	
	public void setSize(Integer value) {
		this.size = value;
	}
	
	public java.lang.String getAppUrl() {
		return this.appUrl;
	}
	
	public void setAppUrl(java.lang.String value) {
		this.appUrl = value;
	}
	
	public java.lang.String getUpdateInfo() {
		return this.updateInfo;
	}
	
	public void setUpdateInfo(java.lang.String value) {
		this.updateInfo = value;
	}
	
	public java.lang.Long getAddTimeBegin() {
		return this.addTimeBegin;
	}
	
	public void setAddTimeBegin(java.lang.Long value) {
		this.addTimeBegin = value;
	}	
	
	public java.lang.Long getAddTimeEnd() {
		return this.addTimeEnd;
	}
	
	public void setAddTimeEnd(java.lang.Long value) {
		this.addTimeEnd = value;
	}
	
	public java.lang.Long getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}
	
	public void setUpdateTimeBegin(java.lang.Long value) {
		this.updateTimeBegin = value;
	}	
	
	public java.lang.Long getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}
	
	public void setUpdateTimeEnd(java.lang.Long value) {
		this.updateTimeEnd = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

