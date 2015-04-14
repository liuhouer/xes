/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.vo.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

import com.up72.base.*;
/**
 * 查询辅助bean
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
public class TeacherQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 省份ID */
	private java.lang.Long provinceId;
	/** 地区ID */
	private java.lang.Long areaId;
	/** 老师级别，1为普通老师，2为专家 */
	private Integer level;
	/** 昵称 */
	private java.lang.String nickName;
	/** 真实姓名 */
	private java.lang.String realName;
	/** 登录帐号 */
	private java.lang.String loginName;
	/** 擅长年级 */
	private java.lang.String expertGradeIds;
	/** 擅长年级 */
	private java.lang.Long expertGradeId;
	/** 擅长学科 */
	private java.lang.Long expertSubjectId;
	/** 性别,0为女，1为男 */
	private Integer sex;
	/** 头像 */
	private java.lang.String imgPath;
	/** 最后登录时间 */
	private java.lang.Long lastLoginTimeBegin;
	private java.lang.Long lastLoginTimeEnd;
	/** 添加时间 */
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;
	/** 登录密码 */
	private java.lang.String password;
	/** 状态,0为正常，1为冻结 */
	private Integer status;
	
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
	
	public java.lang.Long getProvinceId() {
		return this.provinceId;
	}
	
	public void setProvinceId(java.lang.Long value) {
		this.provinceId = value;
	}
	
	public java.lang.Long getAreaId() {
		return this.areaId;
	}
	
	public void setAreaId(java.lang.Long value) {
		this.areaId = value;
	}
	
	public Integer getLevel() {
		return this.level;
	}
	
	public void setLevel(Integer value) {
		this.level = value;
	}
	
	public java.lang.String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	public java.lang.String getRealName() {
		return this.realName;
	}
	
	public void setRealName(java.lang.String value) {
		this.realName = value;
	}
	
	public java.lang.String getLoginName() {
		return this.loginName;
	}
	
	public void setLoginName(java.lang.String value) {
		this.loginName = value;
	}
	
	public java.lang.String getExpertGradeIds() {
		return this.expertGradeIds;
	}
	
	public void setExpertGradeIds(java.lang.String value) {
		this.expertGradeIds = value;
	}
	
	public java.lang.Long getExpertGradeId() {
		return this.expertGradeId;
	}
	
	public void setExpertGradeId(java.lang.Long value) {
		this.expertGradeId = value;
	}
	
	public java.lang.Long getExpertSubjectId() {
		return this.expertSubjectId;
	}
	
	public void setExpertSubjectId(java.lang.Long value) {
		this.expertSubjectId = value;
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
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

