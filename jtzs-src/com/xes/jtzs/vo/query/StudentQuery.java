/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import static com.up72.common.CommonUtils.*;
import java.io.Serializable;

import java.util.*;

import javax.persistence.Transient;

import com.up72.base.*;
import com.up72.util.*;

import com.up72.framework.util.*;
import com.up72.framework.util.holder.ApplicationContextHolder;
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
public class StudentQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 状态,1为正常，0为冻结，默认1 */
	private java.lang.Integer status;
	/** 城市ID */
	private java.lang.Long provinceId;
	/** 昵称 */
	private java.lang.String nickName;
	/** 登录账户 */
	private java.lang.String loginName;
	/** 学校ID */
	private java.lang.Long schoolId;
	/** 年纪ID */
	private java.lang.Long gradeId;
	/** 性别,0为女，1为男 */
	private Integer sex;
	/** 头像 */
	private java.lang.String imgPath;
	/** 最后登录时间 */
	private java.lang.Long lastLoginTimeBegin;
	private java.lang.Long lastLoginTimeEnd;
	/** 注册时间 */
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;
	/** 密码 */
	private java.lang.String password;
	/** 所属平台,0为android，1为IOS */
	private Integer platform;

	
	private Integer userRole;
	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Long getProvinceId() {
		return this.provinceId;
	}
	
	public void setProvinceId(java.lang.Long value) {
		this.provinceId = value;
	}
	
	public java.lang.String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	public java.lang.String getLoginName() {
		return this.loginName;
	}
	
	public void setLoginName(java.lang.String value) {
		this.loginName = value;
	}
	
	public java.lang.Long getSchoolId() {
		return this.schoolId;
	}
	
	public void setSchoolId(java.lang.Long value) {
		this.schoolId = value;
	}
	
	public java.lang.Long getGradeId() {
		return this.gradeId;
	}
	
	public void setGradeId(java.lang.Long value) {
		this.gradeId = value;
	}
	
	public Integer getSex() {
		return this.sex;
	}
	
	public void setSex(Integer value) {
		this.sex = value;
	}
	
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	
	public java.lang.Long getLastLoginTimeBegin() {
		return this.lastLoginTimeBegin;
	}
	
	public void setLastLoginTimeBegin(java.lang.Long value) {
		this.lastLoginTimeBegin = value;
	}	
	
	public java.lang.Long getLastLoginTimeEnd() {
		return this.lastLoginTimeEnd;
	}
	
	public void setLastLoginTimeEnd(java.lang.Long value) {
		this.lastLoginTimeEnd = value;
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
	
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public Integer getPlatform() {
		return this.platform;
	}
	
	public void setPlatform(Integer value) {
		this.platform = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}

