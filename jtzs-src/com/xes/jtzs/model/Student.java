/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.model;

import static com.up72.common.CommonUtils.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;


import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.up72.base.*;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.dao.QuestionDao;
import com.xes.jtzs.service.AreaManager;
import com.xes.jtzs.service.CityManager;
import com.xes.jtzs.service.GradeManager;
import com.xes.jtzs.service.ProvinceManager;
import com.xes.jtzs.service.SchoolManager;
import com.xes.jtzs.service.ScoreManager;
/**
 * 
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "xes_jtzs_student")
public class Student extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "学生";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_STATUS = "状态";//1为正常，0为冻结，默认1
	public static final String ALIAS_PROVINCE_ID = "省份";
	public static final String ALIAS_CITY_ID = "城市";
	public static final String ALIAS_AREA_ID = "区县";
	public static final String ALIAS_NICK_NAME = "昵称";
	public static final String ALIAS_LOGIN_NAME = "登录账户";
	public static final String ALIAS_SCHOOL_ID = "学校";
	public static final String ALIAS_GRADE_ID = "年级";
	public static final String ALIAS_SEX = "性别";//0为女，1为男
	public static final String ALIAS_IMG_PATH = "头像";
	public static final String ALIAS_LAST_LOGIN_TIME = "最后登录时间";
	public static final String ALIAS_ADD_TIME = "注册时间";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_PLATFORM = "所属平台";//0为android，1为IOS
	
	//date formats
	public static final String FORMAT_LAST_LOGIN_TIME = DATE_FORMAT;
	public static final String FORMAT_ADD_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 状态,1为正常，0为冻结，默认1*/
	@Max(127)
	private java.lang.Integer status;
	
	/** 区县ID*/
	
	private java.lang.Long areaId;
	/** 城市ID*/
	
	private java.lang.Long cityId;
	/** 省份ID*/
	
	private java.lang.Long provinceId;
	
	/** 昵称*/
	@Length(max=50)
	private java.lang.String nickName;
	
	/** 登录账户*/
	@Length(max=50)
	private java.lang.String loginName;
	
	/** 学校ID*/
	
	private java.lang.Long schoolId;
	
	/** 年纪ID*/
	
	private java.lang.Long gradeId;
	
	/** 性别,0为女，1为男*/
	@Max(127)
	private Integer sex;
	
	/** 头像*/
	@Length(max=200)
	private java.lang.String imgPath;
	
	/** 最后登录时间*/
	
	private java.lang.Long lastLoginTime;
	
	/** 注册时间*/
	
	private java.lang.Long addTime;
	
	/** 密码*/
	@Length(max=50)
	private java.lang.String password;
	
	/** 所属平台,0为android，1为IOS*/
	@Max(127)
	private Integer platform;
	@Length(max=500)
	private java.lang.String token;
	
	
	//columns END


	public Student(){
	}

	public Student(
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
	
	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	@Column(name = "PROVINCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getProvinceId() {
		return this.provinceId;
	}
	
	public void setProvinceId(java.lang.Long value) {
		this.provinceId = value;
	}
	
	@Column(name = "TOKEN", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getToken() {
		return this.token;
	}
	
	public void setToken(java.lang.String value) {
		this.token = value;
	}
	
	@Column(name = "CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCityId() {
		return this.cityId;
	}
	
	public void setCityId(java.lang.Long value) {
		this.cityId = value;
	}
	
	@Column(name = "AREA_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAreaId() {
		return this.areaId;
	}
	
	public void setAreaId(java.lang.Long value) {
		this.areaId = value;
	}
	
	@Column(name = "NICK_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	@Column(name = "LOGIN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getLoginName() {
		return this.loginName;
	}
	
	public void setLoginName(java.lang.String value) {
		this.loginName = value;
	}
	
	@Column(name = "SCHOOL_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getSchoolId() {
		return this.schoolId;
	}
	
	public void setSchoolId(java.lang.Long value) {
		this.schoolId = value;
	}
	
	@Column(name = "GRADE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getGradeId() {
		return this.gradeId;
	}
	
	public void setGradeId(java.lang.Long value) {
		this.gradeId = value;
	}
	
	@Column(name = "SEX", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Integer getSex() {
		return this.sex;
	}
	
	public void setSex(Integer value) {
		this.sex = value;
	}
	
	@Column(name = "IMG_PATH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	
	@Transient
	public Date getLastLoginTimeDate() {
		return dateUtils.longToDate(this.lastLoginTime);
	}
	@Column(name = "LAST_LOGIN_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getLastLoginTime() {
		return this.lastLoginTime;
	}
	
	public void setLastLoginTime(java.lang.Long value) {
		this.lastLoginTime = value;
	}
	
	@Transient
	public Date getAddTimeDate() {
		return dateUtils.longToDate(this.addTime);
	}
	
	@Transient
	public String  getAddTimeStrs() {
		if(this.addTime!=null){
		Date d = new Date(this.addTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:ss");
			return sdf.format(d);
		}else{
			return null;
		}
	}
	
	@Transient
	public String getLastLoginTimeStrs() {
		if(this.lastLoginTime!=null){
		Date d =  new Date(this.lastLoginTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:ss");
			return sdf.format(d);
		}else{
			return null;
		}
	}
	@Column(name = "ADD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAddTime() {
		return this.addTime;
	}
	
	public void setAddTime(java.lang.Long value) {
		this.addTime = value;
	}
	
	@Column(name = "PASSWORD", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	@Column(name = "PLATFORM", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Integer getPlatform() {
		return this.platform;
	}
	
	public void setPlatform(Integer value) {
		this.platform = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Status",getStatus())
			.append("ProvinceId",getProvinceId())
			.append("CityId",getCityId())
			.append("areaId",getAreaId())
			.append("NickName",getNickName())
			.append("LoginName",getLoginName())
			.append("SchoolId",getSchoolId())
			.append("GradeId",getGradeId())
			.append("Sex",getSex())
			.append("ImgPath",getImgPath())
			.append("LastLoginTime",getLastLoginTime())
			.append("AddTime",getAddTime())
			.append("Password",getPassword())
			.append("Platform",getPlatform())
			.append("Token",getToken())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Student == false) return false;
		if(this == obj) return true;
		Student other = (Student)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
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
	public School getSchool(){
		SchoolManager schoolManager = (SchoolManager)ApplicationContextHolder.getBean("schoolManager");
		School school = null;
		if(null != this.getSchoolId()){
			school = schoolManager.getById(this.getSchoolId());
		}
		if(null == school){
			school = new School();
		}
		return school;
	}
	
	@Transient
	public Grade getGrade(){
		GradeManager gradeManager = (GradeManager)ApplicationContextHolder.getBean("gradeManager");
		Grade grade = null;
		if(null != this.getGradeId()){
			grade = gradeManager.getById(this.getGradeId());
		}
		if(null == grade){
			grade = new Grade();
		}
		return grade;
	}
	
	@Transient
	public String getStatusStr(){
		String result = JTZSConstants.Status.FREEZE.getName();
		if(null != this.status && JTZSConstants.Status.NORMAL.getIndex()==this.status){
			result = JTZSConstants.Status.NORMAL.getName();
		}
		return result;
	}
	
	@Transient
	public String getSexStr(){
		String result = JTZSConstants.Sex.MEN.getName();
		if(null != this.status && JTZSConstants.Sex.WOMEN.getIndex()==this.status){
			result = JTZSConstants.Sex.WOMEN.getName();
		}
		return result;
	}
	
	@Transient
	public Score getScore(){
		ScoreManager scoreManager = (ScoreManager)ApplicationContextHolder.getBean("scoreManager");
		return scoreManager.getScoreByRole(this.id, JTZSConstants.ROLE_STUDENT);
	}
	
	@Transient
	public Long getQuestionCount(){
		QuestionDao questionDao = (QuestionDao)ApplicationContextHolder.getBean("questionDao");
		Long count = questionDao.countByAggregate("select count(id) from Question where isDel=? and isQuit=? and isLock=? and studentId=?",
				new Object[]{JTZSConstants.IsDel.UNDELETE.getIndex(),JTZSConstants.QuestionIsQuit.NORMAL.getIndex(),JTZSConstants.Status.NORMAL.getIndex(),this.id});
		if(null == count){
			count = 0L;
		}
		return count;
	}
	
	@Transient
	public String getNianji(){
		return this.getGrade().getName();
	}
	
	@Transient
	public String getXuexiao(){
		return this.getSchool().getName();
	}
	
	@Transient
	public String getShengfen(){
		return this.getProvince().getName();
	}
	
}

