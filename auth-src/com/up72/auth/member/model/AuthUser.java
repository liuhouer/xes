/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.member.model;

import static com.up72.common.CommonUtils.dateUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import com.up72.app.auth.service.UserManager;
import com.up72.auth.dao.WorkGroupMemberDao;
import com.up72.auth.model.Role;
import com.up72.auth.model.WorkGroup;
import com.up72.auth.model.WorkGroupMember;
import com.up72.base.BaseEntity;
import com.up72.base.UserDetails;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 用户
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "auth_user")
public class AuthUser extends BaseEntity implements java.io.Serializable,UserDetails{
	private static final long serialVersionUID = 5454155825314635342L;

	//alias
	public static final String TABLE_ALIAS = "后台管理员";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_NAME = "登录帐号";
	public static final String ALIAS_PASSWORD = "登录密码";
	public static final String ALIAS_NICK_NAME = "昵称";
	public static final String ALIAS_IMG_PATH = "图像";
	public static final String ALIAS_CODE = "用户类型";	//（2后台用户 5系统管理员用户）
	public static final String ALIAS_LAST_IP = "最后访问IP";
	public static final String ALIAS_LAST_VISI_TIME = "最后登录时间";
	public static final String ALIAS_EMAIL = "电子邮箱";
	public static final String ALIAS_MOBILE = "手机";
	
	public static final String ALIAS_MOBILE_VALIDATE = "Email是否验证";		//（0=未验证，1=验证）
	public static final String ALIAS_ENABLED = "状态";	// 0为禁用 1为开启
	public static final String ALIAS_DEL_STATUS = "删除状态（1为删除、默认为0）";
	
	public static final String ALIAS_LOGIN_ANSWER = "登录问题";
	public static final String ALIAS_SECQUES = "用户安全问答";
	public static final String ALIAS_PROBLEM = "密码提示问题";
	public static final String ALIAS_ANSER = "密码提示答案";
	public static final String ALIAS_COMMENT = "备注";
	public static final String ALIAS_ADD_TIME = "添加时间";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 登陆名*/
	private java.lang.String userName;
	
	/** 登陆密码*/
	private java.lang.String password;
	
	/** 昵称*/
	private java.lang.String nickName;
	
	/** 图像*/
	private java.lang.String imgPath;
	
	/** 用户类型 ,1为一般用户，2后台用户 5系统管理员用户*/
	private Integer code;
	
	/** 最后访问IP*/
	private java.lang.String lastIp;
	
	/** 最后访问时间*/
	private java.lang.Long lastVisiTime;
	
	/** EMAIL*/
	private java.lang.String email;
	
	/** 手机验证（0为未认证，1为验证）*/
	private Integer mobileValidate;
	
	/** 手机*/
	private java.lang.String mobile;
	
	/** EMAIL地址可见*/
	private Integer emailValidate;

	/** 登陆问题*/
	private java.lang.String loginAnswer;
	
	/** 用户安全问答*/
	private java.lang.String secques;
	
	/** 找回密码问题*/
	private java.lang.String problem;
	
	/** 找回密码答案*/
	private java.lang.String anser;
	
	/** 是否禁用 0为禁用 1为开启*/
	private Integer enabled;
	
	/** 删除状态（1为删除、默认为0）*/
	private Integer delStatus;
	
	/** 所在机构*/
	private java.lang.Long organizationId;
	/** 风格 */
	private String style;
	/** 皮肤 */
	private String skin;
	/** 备注*/
	private String comment;
	/** 添加时间 */
	private Long addTime;
	//columns END


	public AuthUser(){
	}

	public AuthUser(
		java.lang.Long id
	){
		this.id = id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getId() {
		return this.id;
	}
	
	@Column(name = "USER_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	@Column(name = "PASSWORD", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	@Column(name = "NICK_NAME", unique = false, insertable = true, updatable = true, length = 50)
	public java.lang.String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	@Column(name = "IMG_PATH", unique = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	
	@Column(name = "CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getCode() {
		return this.code;
	}
	
	public void setCode(Integer value) {
		this.code = value;
	}
	
	@Column(name = "LAST_IP", unique = false, nullable = false, insertable = true, updatable = true, length = 15)
	public java.lang.String getLastIp() {
		return this.lastIp;
	}
	
	public void setLastIp(java.lang.String value) {
		this.lastIp = value;
	}
	
	@Transient
	public Date getLastVisiTimeDate() {
		return dateUtils.longToDate(this.lastVisiTime);
	}
	@Column(name = "LAST_VISI_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getLastVisiTime() {
		return this.lastVisiTime;
	}
	
	public void setLastVisiTime(java.lang.Long value) {
		this.lastVisiTime = value;
	}
	
	@Column(name = "EMAIL", unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	@Column(name = "MOBILE_VALIDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getMobileValidate() {
		return this.mobileValidate;
	}
	
	public void setMobileValidate(Integer value) {
		this.mobileValidate = value;
	}
	
	@Column(name = "MOBILE", unique = false, nullable = true, insertable = true, updatable = true, length = 15)
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	@Column(name = "EMAIL_VALIDATE", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getEmailValidate() {
		return this.emailValidate;
	}
	
	public void setEmailValidate(Integer value) {
		this.emailValidate = value;
	}
	
	@Column(name = "ENABLED", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(Integer value) {
		this.enabled = value;
	}

	@Column(name = "LOGIN_ANSWER", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getLoginAnswer() {
		return this.loginAnswer;
	}
	
	public void setLoginAnswer(java.lang.String value) {
		this.loginAnswer = value;
	}
	
	@Column(name = "SECQUES", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getSecques() {
		return this.secques;
	}
	
	public void setSecques(java.lang.String value) {
		this.secques = value;
	}
	
	@Column(name = "PROBLEM", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getProblem() {
		return this.problem;
	}
	
	public void setProblem(java.lang.String value) {
		this.problem = value;
	}
	
	@Column(name = "ANSER", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getAnser() {
		return this.anser;
	}

	@Column(name = "DEL_STATUS", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	
	@Column(name = "ORGANIZATION_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(java.lang.Long organizationId) {
		this.organizationId = organizationId;
	}
	
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	@Column(name = "COMMENT", unique = false, nullable = true, insertable = true, updatable = true)
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Transient
	public Date getAddTimeDate() {
		return dateUtils.longToDate(this.addTime);
	}
	@Column(name = "ADD_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAddTime() {
		return this.addTime;
	}
	
	public void setAddTime(java.lang.Long value) {
		this.addTime = value;
	}
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserName",getUserName())
			.append("Password",getPassword())
			.append("NickName",getNickName())
			.append("ImgPath",getImgPath())
			.append("LoginAnswer",getLoginAnswer())
			.append("Secques",getSecques())
			.append("Code",getCode())
			.append("LastIp",getLastIp())
			.append("LastVisiTime",getLastVisiTime())
			.append("AddTime",getAddTime())
			.append("Email",getEmail())
			.append("MobileValidate",getMobileValidate())
			.append("Mobile",getMobile())
			.append("EmailVisible",getEmailValidate())
			.append("Enabled",getEnabled())
			.append("Problem",getProblem())
			.append("Anser",getAnser())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AuthUser == false) return false;
		if(this == obj) return true;
		AuthUser other = (AuthUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
//======================权限处理开始=============================================
	/**
	 * 获得用户在角色列表
	 */
	@Transient
	public List<Role> getRoles() {
		UserManager userManager = (UserManager)ApplicationContextHolder.getBean("userManager");
		return userManager.getRoles(this);
	}
	
	/**
	 * 获得用户的角色，当角色单一的时候，非单一取第一个
	 * @author wqtan
	 * @return Role
	 */
	@Transient
	public Role getRole(){
		Role result = null;
		List<Role> rolelist = this.getRoles();
		if(null != rolelist
				&& rolelist.size() > 0){
			result =rolelist.get(0);
		}
		return result;
	}

	@Transient
	@SuppressWarnings("unchecked")
	public List getRoleIds(){
		List result = new LinkedList();
		List<Role> roleList = this.getRoles();
		for (int i = 0; i < roleList.size(); i++) {
			result.add(roleList.get(i).getId());
		}
		return result;
	}
	
	@Transient
	public WorkGroup getWorkGroup(){
		WorkGroupMemberDao workGroupMemberDao = (WorkGroupMemberDao) ApplicationContextHolder.getBean("workGroupMemberDao");
		List<WorkGroupMember> list = workGroupMemberDao.getWorkGroupMember(this.id);
		
		WorkGroup result = null;
		if(null!=list && list.size() > 0){
			result = list.get(0).getWorkGroup();
		}
		return result;
	}
	
	@Transient
	public List<WorkGroupMember> getWorkGroupList() {
		WorkGroupMemberDao workGroupMemberDao = (WorkGroupMemberDao) ApplicationContextHolder.getBean("workGroupMemberDao");
		List<WorkGroupMember> list = workGroupMemberDao.getWorkGroupMember(this.id);
		return list;
	}

	public void setAnser(java.lang.String anser) {
		this.anser = anser;
	}

	
//======================权限处理结束=============================================

}

