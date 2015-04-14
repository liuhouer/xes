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
import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Transient;

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
public class EventQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 标题 */
	private java.lang.String title;
	/** 接受对象 */
	private Integer sendTo;
	/** 发送时间 */
	private java.lang.Long sendTimeBegin;
	private java.lang.Long sendTimeEnd;
	/** 发送状态 */
	private Integer sendStatus;
	/** 操作人 */
	private java.lang.Long sendUser;
	/** 标题图片 */
	private java.lang.String imgPath;
	/** 简介 */
	private java.lang.String summary;
	/** 内容 */
	private java.lang.String content;
	/** 问题1 */
	private java.lang.String question1;
	/** 问题2 */
	private java.lang.String question2;
	/** 问题3 */
	private java.lang.String question3;
	/** 问题4 */
	private java.lang.String question4;
	/** 问题5 */
	private java.lang.String question5;
	/** 问题6 */
	private java.lang.String question6;
	/** 问题7 */
	private java.lang.String question7;
	/** 答案 */
	private java.lang.String answer;
	/** 开始时间 */
	private java.lang.Long startTimeBegin;
	private java.lang.Long startTimeEnd;
	/** 结束时间 */
	private java.lang.Long endTimeBegin;
	private java.lang.Long endTimeEnd;
	/** 正确答案分值 */
	private java.lang.Integer addScore;
	/** 错误答案分值 */
	private java.lang.Integer delScore;
	/** 省份ID */
	private java.lang.Long provinceId;
	
	/** 开始时间 */
	private String startTime ;
	

	/** 结束时间 */
	private String endTime ;
	
	/** 城市ID*/
	
	private java.lang.Long cityId;
	
	public Long convertTimeStrToLong(String strs) throws ParseException{
		String str = strs;
		Long longtime =null;
		 if(isNotEmpty(str)&&str.length()>0) {
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    			Date date  = sdf.parse(str);
				    longtime = date.getTime();
	     }
		
		return longtime;
	}
	
	@Transient
	public Date getStartTimeDate() throws ParseException {
		return dateUtils.longToDate(convertTimeStrToLong(this.startTime));
	}
	
	@Transient
	public Date getEndTimeDate() throws ParseException {
		return dateUtils.longToDate(convertTimeStrToLong(this.endTime));
	}

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public Integer getSendTo() {
		return this.sendTo;
	}
	
	public void setSendTo(Integer value) {
		this.sendTo = value;
	}
	
	public java.lang.Long getSendTimeBegin() {
		return this.sendTimeBegin;
	}
	
	public void setSendTimeBegin(java.lang.Long value) {
		this.sendTimeBegin = value;
	}	
	
	public java.lang.Long getSendTimeEnd() {
		return this.sendTimeEnd;
	}
	
	public void setSendTimeEnd(java.lang.Long value) {
		this.sendTimeEnd = value;
	}
	
	public Integer getSendStatus() {
		return this.sendStatus;
	}
	
	public void setSendStatus(Integer value) {
		this.sendStatus = value;
	}
	
	public java.lang.Long getSendUser() {
		return this.sendUser;
	}
	
	public void setSendUser(java.lang.Long value) {
		this.sendUser = value;
	}
	
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	
	public java.lang.String getSummary() {
		return this.summary;
	}
	
	public void setSummary(java.lang.String value) {
		this.summary = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getQuestion1() {
		return this.question1;
	}
	
	public void setQuestion1(java.lang.String value) {
		this.question1 = value;
	}
	
	public java.lang.String getQuestion2() {
		return this.question2;
	}
	
	public void setQuestion2(java.lang.String value) {
		this.question2 = value;
	}
	
	public java.lang.String getQuestion3() {
		return this.question3;
	}
	
	public void setQuestion3(java.lang.String value) {
		this.question3 = value;
	}
	
	public java.lang.String getQuestion4() {
		return this.question4;
	}
	
	public void setQuestion4(java.lang.String value) {
		this.question4 = value;
	}
	
	public java.lang.String getQuestion5() {
		return this.question5;
	}
	
	public void setQuestion5(java.lang.String value) {
		this.question5 = value;
	}
	
	public java.lang.String getQuestion6() {
		return this.question6;
	}
	
	public void setQuestion6(java.lang.String value) {
		this.question6 = value;
	}
	
	public java.lang.String getQuestion7() {
		return this.question7;
	}
	
	public void setQuestion7(java.lang.String value) {
		this.question7 = value;
	}
	
	public java.lang.String getAnswer() {
		return this.answer;
	}
	
	public void setAnswer(java.lang.String value) {
		this.answer = value;
	}
	
	public java.lang.Long getStartTimeBegin() {
		return this.startTimeBegin;
	}
	
	public void setStartTimeBegin(java.lang.Long value) {
		this.startTimeBegin = value;
	}	
	
	public java.lang.Long getStartTimeEnd() {
		return this.startTimeEnd;
	}
	
	public void setStartTimeEnd(java.lang.Long value) {
		this.startTimeEnd = value;
	}
	
	public java.lang.Long getEndTimeBegin() {
		return this.endTimeBegin;
	}
	
	public void setEndTimeBegin(java.lang.Long value) {
		this.endTimeBegin = value;
	}	
	
	public java.lang.Long getEndTimeEnd() {
		return this.endTimeEnd;
	}
	
	public void setEndTimeEnd(java.lang.Long value) {
		this.endTimeEnd = value;
	}
	
	public java.lang.Integer getAddScore() {
		return this.addScore;
	}
	
	public void setAddScore(java.lang.Integer value) {
		this.addScore = value;
	}
	
	public java.lang.Integer getDelScore() {
		return this.delScore;
	}
	
	public void setDelScore(java.lang.Integer value) {
		this.delScore = value;
	}
	
	public java.lang.Long getProvinceId() {
		return this.provinceId;
	}
	
	public void setProvinceId(java.lang.Long value) {
		this.provinceId = value;
	}
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	public java.lang.Long getCityId() {
		return cityId;
	}

	public void setCityId(java.lang.Long cityId) {
		this.cityId = cityId;
	}

}

