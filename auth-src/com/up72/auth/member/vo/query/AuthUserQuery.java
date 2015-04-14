/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.member.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.up72.base.BaseQuery;

/**
 * 用户查询辅助bean
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
public class AuthUserQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 登陆名 */
	private java.lang.String userName;
	/** 登陆密码 */
	private java.lang.String password;
	/** 昵称 */
	private java.lang.String nickName;
	/** 图像 */
	private java.lang.String imgPath;
	/** 登陆问题 */
	private java.lang.String loginAnswer;
	/** 用户安全问答 */
	private java.lang.String secques;
	/** 性别（0=保密 1=男 2＝女） */
	private Integer gender;
	/** 用户类型 */
	private Integer code;
	/** 注册IP */
	private java.lang.String regIp;
	/** 注册时间 */
	private java.lang.Long regTime;
	/** 最后访问IP */
	private java.lang.String lastIp;
	/** 最后访问时间 */
	private java.lang.Long lastVisiTime;
	/** 积分 */
	private java.lang.Integer credit;
	/** EMAIL */
	private java.lang.String email;
	/** 手机验证（0为未认证，1为验证） */
	private Integer mobileValidate;
	/** 手机 */
	private java.lang.String mobile;
	/** 国家 */
	private java.lang.String country;
	/** 省 */
	private java.lang.String province;
	/** 市 */
	private java.lang.String city;
	/** 县 */
	private java.lang.String county;
	/** 邮编 */
	private java.lang.String postCode;
	/** 详细地址 */
	private java.lang.String addressInfo;
	/** 生日 */
	private java.lang.Long birthdayTime;
	/** EMAIL地址可见 */
	private Integer emailVisible;
	/** 是否禁用 0为禁用 1为开启 */
	private Integer enabled;
	/** 找回密码问题 */
	private java.lang.String problem;
	/** 找回密码答案 */
	private java.lang.String anser;
	/** 删除标志位 */
	private java.lang.Integer delStatus;
	

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	
	public java.lang.String getLoginAnswer() {
		return this.loginAnswer;
	}
	
	public void setLoginAnswer(java.lang.String value) {
		this.loginAnswer = value;
	}
	
	public java.lang.String getSecques() {
		return this.secques;
	}
	
	public void setSecques(java.lang.String value) {
		this.secques = value;
	}
	
	public Integer getGender() {
		return this.gender;
	}
	
	public void setGender(Integer value) {
		this.gender = value;
	}
	
	public Integer getCode() {
		return this.code;
	}
	
	public void setCode(Integer value) {
		this.code = value;
	}
	
	public java.lang.String getRegIp() {
		return this.regIp;
	}
	
	public void setRegIp(java.lang.String value) {
		this.regIp = value;
	}
	
	public java.lang.Long getRegTime() {
		return this.regTime;
	}
	
	public void setRegTime(java.lang.Long value) {
		this.regTime = value;
	}
	
	public java.lang.String getLastIp() {
		return this.lastIp;
	}
	
	public void setLastIp(java.lang.String value) {
		this.lastIp = value;
	}
	
	public java.lang.Long getLastVisiTime() {
		return this.lastVisiTime;
	}
	
	public void setLastVisiTime(java.lang.Long value) {
		this.lastVisiTime = value;
	}
	
	public java.lang.Integer getCredit() {
		return this.credit;
	}
	
	public void setCredit(java.lang.Integer value) {
		this.credit = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public Integer getMobileValidate() {
		return this.mobileValidate;
	}
	
	public void setMobileValidate(Integer value) {
		this.mobileValidate = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getCountry() {
		return this.country;
	}
	
	public void setCountry(java.lang.String value) {
		this.country = value;
	}
	
	public java.lang.String getProvince() {
		return this.province;
	}
	
	public void setProvince(java.lang.String value) {
		this.province = value;
	}
	
	public java.lang.String getCity() {
		return this.city;
	}
	
	public void setCity(java.lang.String value) {
		this.city = value;
	}
	
	public java.lang.String getCounty() {
		return this.county;
	}
	
	public void setCounty(java.lang.String value) {
		this.county = value;
	}
	
	public java.lang.String getPostCode() {
		return this.postCode;
	}
	
	public void setPostCode(java.lang.String value) {
		this.postCode = value;
	}
	
	public java.lang.String getAddressInfo() {
		return this.addressInfo;
	}
	
	public void setAddressInfo(java.lang.String value) {
		this.addressInfo = value;
	}
	
	public java.lang.Long getBirthdayTime() {
		return this.birthdayTime;
	}
	
	public void setBirthdayTime(java.lang.Long value) {
		this.birthdayTime = value;
	}
	
	public Integer getEmailVisible() {
		return this.emailVisible;
	}
	
	public void setEmailVisible(Integer value) {
		this.emailVisible = value;
	}
	
	public Integer getEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(Integer value) {
		this.enabled = value;
	}
	
	public java.lang.String getProblem() {
		return this.problem;
	}
	
	public void setProblem(java.lang.String value) {
		this.problem = value;
	}
	
	public java.lang.String getAnser() {
		return this.anser;
	}
	
	public void setAnser(java.lang.String value) {
		this.anser = value;
	}
	

	public java.lang.Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(java.lang.Integer delStatus) {
		this.delStatus = delStatus;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

