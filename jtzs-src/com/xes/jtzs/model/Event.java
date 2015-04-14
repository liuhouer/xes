/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.model;

import static com.up72.common.CommonUtils.dateUtils;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import com.up72.auth.UserUtils;
import com.up72.base.BaseEntity;
import com.up72.base.UserDetails;
import com.up72.framework.util.holder.ApplicationContextHolder;
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
@Table(name = "xes_jtzs_event")
public class Event extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "任务管理";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE = "主题";
	public static final String ALIAS_SEND_TO = "接收对象";
	public static final String ALIAS_SEND_TIME = "发送时间";
	public static final String ALIAS_SEND_STATUS = "发送状态";
	public static final String ALIAS_SEND_USER = "操作人";
	public static final String ALIAS_IMG_PATH = "标题图片";
	public static final String ALIAS_SUMMARY = "简介";
	public static final String ALIAS_CONTENT = "信息内容";
	public static final String ALIAS_QUESTION1 = "A问题";
	public static final String ALIAS_QUESTION2 = "B问题";
	public static final String ALIAS_QUESTION3 = "C问题";
	public static final String ALIAS_QUESTION4 = "D问题";
	public static final String ALIAS_QUESTION5 = "E问题";
	public static final String ALIAS_QUESTION6 = "F问题";
	public static final String ALIAS_QUESTION7 = "G问题";
	public static final String ALIAS_ANSWER = "答案";
	public static final String ALIAS_START_TIME = "开始时间";
	public static final String ALIAS_END_TIME = "结束时间";
	public static final String ALIAS_ADD_SCORE = "正确答案分值";
	public static final String ALIAS_DEL_SCORE = "错误答案分值";
	public static final String ALIAS_PROVINCE_ID = "省份";
	
	//date formats
	public static final String FORMAT_SEND_TIME = DATE_FORMAT;
	public static final String FORMAT_START_TIME = DATE_FORMAT;
	public static final String FORMAT_END_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 标题*/
	
	private java.lang.String title;
	
	/** 接受对象*/
	@Max(127)
	private Integer sendTo;
	
	/** 发送时间*/
	
	private java.lang.Long sendTime;
	
	/** 发送状态*/
	@Max(127)
	private Integer sendStatus;
	
	/** 操作人*/
	
	private java.lang.Long sendUser;
	
	/** 标题图片*/
	@Length(max=200)
	private java.lang.String imgPath;
	
	/** 简介*/
	@Length(max=200)
	private java.lang.String summary;
	
	/** 内容*/
	@Length(max=65535)
	private java.lang.String content;
	
	/** 问题1*/
	@Length(max=50)
	private java.lang.String question1;
	
	/** 问题2*/
	@Length(max=50)
	private java.lang.String question2;
	
	/** 问题3*/
	@Length(max=50)
	private java.lang.String question3;
	
	/** 问题4*/
	@Length(max=50)
	private java.lang.String question4;
	
	/** 问题5*/
	@Length(max=50)
	private java.lang.String question5;
	
	/** 问题6*/
	@Length(max=50)
	private java.lang.String question6;
	
	/** 问题7*/
	@Length(max=50)
	private java.lang.String question7;
	
	/** 答案*/
	@Length(max=50)
	private java.lang.String answer;
	
	/** 开始时间*/
	
	private java.lang.Long startTime;
	
	/** 结束时间*/
	
	private java.lang.Long endTime;
	
	/** 正确答案分值*/
	
	private java.lang.Integer addScore;
	
	/** 错误答案分值*/
	
	private java.lang.Integer delScore;
	
	/** 省份ID*/
	
	private java.lang.Long provinceId;
	
    /** 城市ID*/
	
	private java.lang.Long cityId;
	
	/** 登陆人姓名*/
	private String userName;
	
	//columns END


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Event(){
	}

	public Event(
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
	
	@Column(name = "TITLE", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "SEND_TO", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getSendTo() {
		return this.sendTo;
	}
	
	public void setSendTo(Integer value) {
		this.sendTo = value;
	}
	
	@Transient
	public Date getSendTimeDate() {
		return dateUtils.longToDate(this.sendTime);
	}
	@Column(name = "SEND_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getSendTime() {
		return this.sendTime;
	}
	
	public void setSendTime(java.lang.Long value) {
		this.sendTime = value;
	}
	
	@Column(name = "SEND_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getSendStatus() {
		return this.sendStatus;
	}
	
	public void setSendStatus(Integer value) {
		this.sendStatus = value;
	}
	
	@Column(name = "SEND_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getSendUser() {
		return this.sendUser;
	}
	
	public void setSendUser(java.lang.Long value) {
		this.sendUser = value;
	}
	
	@Column(name = "IMG_PATH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	
	@Column(name = "SUMMARY", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getSummary() {
		return this.summary;
	}
	
	public void setSummary(java.lang.String value) {
		this.summary = value;
	}
	
	@Column(name = "CONTENT", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Column(name = "QUESTION1", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getQuestion1() {
		return this.question1;
	}
	
	public void setQuestion1(java.lang.String value) {
		this.question1 = value;
	}
	
	@Column(name = "QUESTION2", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getQuestion2() {
		return this.question2;
	}
	
	public void setQuestion2(java.lang.String value) {
		this.question2 = value;
	}
	
	@Column(name = "QUESTION3", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getQuestion3() {
		return this.question3;
	}
	
	public void setQuestion3(java.lang.String value) {
		this.question3 = value;
	}
	
	@Column(name = "QUESTION4", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getQuestion4() {
		return this.question4;
	}
	
	public void setQuestion4(java.lang.String value) {
		this.question4 = value;
	}
	
	@Column(name = "QUESTION5", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getQuestion5() {
		return this.question5;
	}
	
	public void setQuestion5(java.lang.String value) {
		this.question5 = value;
	}
	
	@Column(name = "QUESTION6", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getQuestion6() {
		return this.question6;
	}
	
	public void setQuestion6(java.lang.String value) {
		this.question6 = value;
	}
	
	@Column(name = "QUESTION7", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getQuestion7() {
		return this.question7;
	}
	
	public void setQuestion7(java.lang.String value) {
		this.question7 = value;
	}
	
	@Column(name = "ANSWER", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getAnswer() {
		return this.answer;
	}
	
	public void setAnswer(java.lang.String value) {
		this.answer = value;
	}
	
	@Transient
	public Date getStartTimeDate() {
		return dateUtils.longToDate(this.startTime);
	}
	@Column(name = "START_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(java.lang.Long value) {
		this.startTime = value;
	}
	
	@Transient
	public Date getEndTimeDate() {
		return dateUtils.longToDate(this.endTime);
	}
	@Column(name = "END_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(java.lang.Long value) {
		this.endTime = value;
	}
	
	@Column(name = "ADD_SCORE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAddScore() {
		return this.addScore;
	}
	
	public void setAddScore(java.lang.Integer value) {
		this.addScore = value;
	}
	
	@Column(name = "DEL_SCORE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDelScore() {
		return this.delScore;
	}
	
	public void setDelScore(java.lang.Integer value) {
		this.delScore = value;
	}
	
	@Column(name = "PROVINCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getProvinceId() {
		return this.provinceId;
	}
	public void setProvinceId(java.lang.Long value) {
		this.provinceId = value;
	}
	
	@Column(name = "CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCityId() {
		return cityId;
	}

	public void setCityId(java.lang.Long cityId) {
		this.cityId = cityId;
	}

	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("SendTo",getSendTo())
			.append("SendTime",getSendTime())
			.append("SendStatus",getSendStatus())
			.append("SendUser",getSendUser())
			.append("ImgPath",getImgPath())
			.append("Summary",getSummary())
			.append("Content",getContent())
			.append("Question1",getQuestion1())
			.append("Question2",getQuestion2())
			.append("Question3",getQuestion3())
			.append("Question4",getQuestion4())
			.append("Question5",getQuestion5())
			.append("Question6",getQuestion6())
			.append("Question7",getQuestion7())
			.append("Answer",getAnswer())
			.append("StartTime",getStartTime())
			.append("EndTime",getEndTime())
			.append("AddScore",getAddScore())
			.append("DelScore",getDelScore())
			.append("ProvinceId",getProvinceId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
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
	public String getUserName(){
		UserUtils userutils = new UserUtils();
		String name = null;
		if(null != this.getSendUser()){
			name = userutils.getNameByid(this.getSendUser());
		}
		return name;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Event == false) return false;
		if(this == obj) return true;
		Event other = (Event)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

