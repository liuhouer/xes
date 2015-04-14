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
public class QuestionQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 问题内容 */
	private java.lang.String content;
	/** 问题图片 */
	private java.lang.String imgPath;
	/** 年级ID */
	private java.lang.Long gradeId;
	/** 学科ID */
	private java.lang.Long subjectId;
	/** 知识点 */
	private java.lang.Long knowledgeId;
	private java.lang.Integer isKnowledge;
	/** 提问时间 */
	private java.lang.Long addTimeBegin;
	private java.lang.Long addTimeEnd;
	/** 提问学生ID */
	private java.lang.Long studentId;
	/** 回答老师Id */
	private java.lang.Long answerTeacherId;
	/** 来源类型，0为学生提问，1为专家作答，2为老师放弃 */
	private Integer sourceType;
	/** 0为无人作答，1为正在作答，2为作答完毕 */
	private Integer status;
	/** 不包括的状态 */
	private Integer notStatus;
	/** 所属平台，0为android，1为IOS */
	private Integer platform;
	/** 举报人ID */
	private java.lang.Long reportId;
	/** 举报时间 */
	private java.lang.Long reportTimeBegin;
	private java.lang.Long reportTimeEnd;
	/** 审核状态，0为未处理，1为已处理 */
	private Integer auditState;
	/** 举报结果，0为未违规，1为违规 */
	private java.lang.Integer reportResult;
	/** 举报内容 */
	private java.lang.String reportContent;
	/** 是否删除，0为未删除，1为已删除 */
	private Integer isDel;
	/** 是否放弃作答，0为正常，1为放弃 */
	private Integer isQuit;
	/** 是否冻结，0为正常，1为冻结 */
	private Integer isLock;
	/** 解答积分类型*/
	private Long scoreType;
	
	private long[] gradeIdList;
	
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	
	public java.lang.Long getGradeId() {
		return this.gradeId;
	}
	
	public void setGradeId(java.lang.Long value) {
		this.gradeId = value;
	}
	
	public java.lang.Long getSubjectId() {
		return this.subjectId;
	}
	
	public void setSubjectId(java.lang.Long value) {
		this.subjectId = value;
	}
	
	public java.lang.Long getKnowledgeId() {
		return this.knowledgeId;
	}
	
	public void setKnowledgeId(java.lang.Long value) {
		this.knowledgeId = value;
	}
	public java.lang.Integer getIsKnowledge() {
		return this.isKnowledge;
	}
	
	public void setIsKnowledge(java.lang.Integer value) {
		this.isKnowledge = value;
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
	
	public java.lang.Long getStudentId() {
		return this.studentId;
	}
	
	public void setStudentId(java.lang.Long value) {
		this.studentId = value;
	}
	
	public Integer getSourceType() {
		return this.sourceType;
	}
	
	public void setSourceType(Integer value) {
		this.sourceType = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getPlatform() {
		return this.platform;
	}
	
	public void setPlatform(Integer value) {
		this.platform = value;
	}
	
	public java.lang.Long getReportId() {
		return this.reportId;
	}
	
	public void setReportId(java.lang.Long value) {
		this.reportId = value;
	}
	
	@Transient
	public Date getReportTimeBeginDate(){
		return dateUtils.longToDate(this.reportTimeBegin);
	}
	
	public java.lang.Long getReportTimeBegin() {
		return this.reportTimeBegin;
	}
	
	public void setReportTimeBegin(java.lang.Long value) {
		this.reportTimeBegin = value;
	}
	
	@Transient
	public Date getReportTimeEndDate(){
		return dateUtils.longToDate(this.reportTimeEnd);
	}
	
	
	public java.lang.Long getReportTimeEnd() {
		return this.reportTimeEnd;
	}
	
	public void setReportTimeEnd(java.lang.Long value) {
		this.reportTimeEnd = value;
	}
	
	public Integer getAuditState() {
		return this.auditState;
	}
	
	public void setAuditState(Integer value) {
		this.auditState = value;
	}
	
	public java.lang.Integer getReportResult() {
		return this.reportResult;
	}
	
	public void setReportResult(java.lang.Integer value) {
		this.reportResult = value;
	}
	
	public java.lang.String getReportContent() {
		return this.reportContent;
	}
	
	public void setReportContent(java.lang.String value) {
		this.reportContent = value;
	}
	
	public Integer getIsDel() {
		return this.isDel;
	}
	
	public void setIsDel(Integer value) {
		this.isDel = value;
	}
	
	public Integer getIsQuit() {
		return this.isQuit;
	}
	
	public void setIsQuit(Integer value) {
		this.isQuit = value;
	}
	
	public Integer getIsLock() {
		return this.isLock;
	}
	
	public void setIsLock(Integer value) {
		this.isLock = value;
	}
	

	public Long getScoreType() {
		return scoreType;
	}

	public void setScoreType(Long scoreType) {
		this.scoreType = scoreType;
	}

	public java.lang.Long getAnswerTeacherId() {
		return answerTeacherId;
	}

	public void setAnswerTeacherId(java.lang.Long answerTeacherId) {
		this.answerTeacherId = answerTeacherId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	public long[] getGradeIdList() {
		return gradeIdList;
	}

	public void setGradeIdList(long[] gradeIdList) {
		this.gradeIdList = gradeIdList;
	}

	public Integer getNotStatus() {
		return notStatus;
	}

	public void setNotStatus(Integer notStatus) {
		this.notStatus = notStatus;
	}
	
}

