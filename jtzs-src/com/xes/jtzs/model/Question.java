/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.model;

import static com.up72.common.CommonUtils.*;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import java.util.*;

import com.up72.base.*;
import com.up72.util.*;

import com.up72.framework.util.*;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.framework.web.util.*;
import com.up72.framework.page.*;
import com.up72.framework.page.impl.*;

import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.model.*;
import com.xes.jtzs.dao.*;
import com.xes.jtzs.service.*;
import com.xes.jtzs.vo.query.*;/**
 * 
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "xes_jtzs_question")
public class Question extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "问题";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CONTENT = "问题内容";
	public static final String ALIAS_IMG_PATH = "问题图片";
	public static final String ALIAS_GRADE_ID = "年级";
	public static final String ALIAS_SUBJECT_ID = "学科";
	public static final String ALIAS_KNOWLEDGE_ID = "知识点";
	public static final String ALIAS_ADD_TIME = "提问时间";
	public static final String ALIAS_STOP_TIME = "解答结束时间";
	public static final String ALIAS_SCORE_TYPE = "解答积分类型";
	public static final String ALIAS_STUDENT_ID = "提问学生";
	public static final String ALIAS_ANSWER_TEACHER_ID = "作答老师";
	public static final String ALIAS_SOURCE_TYPE = "来源类型";//0为学生提问，1为专家作答，2为老师放弃
	public static final String ALIAS_STATUS = "作答状态";//0为无人作答，1为正在作答，2为作答完毕
	public static final String ALIAS_PLATFORM = "所属平台";//0为android，1为IOS
	public static final String ALIAS_REPORT_ID = "举报人";
	public static final String ALIAS_REPORT_TIME = "举报时间";
	public static final String ALIAS_AUDIT_STATE = "审核状态";//0为未处理，1为已处理
	public static final String ALIAS_REPORT_RESULT = "审核结果";//0为未违规，1为违规
	public static final String ALIAS_REPORT_CONTENT = "举报内容";
	public static final String ALIAS_IS_DEL = "是否删除";//0为未删除，1为已删除
	public static final String ALIAS_IS_QUIT = "是否放弃作答";//0为正常，1为放弃
	public static final String ALIAS_IS_LOCK = "是否冻结";//0为正常，1为冻结
	
	//date formats
	public static final String FORMAT_ADD_TIME = DATE_FORMAT;
	public static final String FORMAT_REPORT_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 问题内容*/
	@Length(max=500)
	private java.lang.String content;
	
	/** 问题图片*/
	@Length(max=200)
	private java.lang.String imgPath;
	
	/** 年级ID*/
	
	private java.lang.Long gradeId;
	
	/** 学科ID*/
	
	private java.lang.Long subjectId;
	
	/** 知识点*/
	
	private java.lang.Long knowledgeId;
	
	/** 提问时间*/
	
	private java.lang.Long addTime;
	/** 解答结束时间*/
	private java.lang.Long stopTime;
	/** 解答积分类型*/
	private Long scoreType;
	/** 提问学生ID*/
	
	private java.lang.Long studentId;
	private java.lang.Long answerTeacherId;
	
	/** 来源类型，0为学生提问，1为专家作答，2为老师放弃*/
	@Max(127)
	private Integer sourceType;
	
	/** 0为无人作答，1为正在作答，2为作答完毕*/
	@Max(127)
	private Integer status;
	
	/** 所属平台，0为android，1为IOS*/
	@Max(127)
	private Integer platform;
	
	/** 举报人ID*/
	
	private java.lang.Long reportId;
	
	/** 举报时间*/
	
	private java.lang.Long reportTime;
	
	/** 审核状态，0为未处理，1为已处理*/
	@Max(127)
	private Integer auditState;
	
	/** 举报结果，0为未违规，1为违规*/
	@Max(127)
	private java.lang.Integer reportResult;
	
	/** 举报内容*/
	@Length(max=200)
	private java.lang.String reportContent;
	
	/** 是否删除，0为未删除，1为已删除*/
	@Max(127)
	private Integer isDel;
	
	/** 是否放弃作答，0为正常，1为放弃*/
	@Max(127)
	private Integer isQuit;
	
	/** 是否冻结，0为正常，1为冻结*/
	@Max(127)
	private Integer isLock;
	
	//columns END


	public Question(){
	}

	public Question(
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
	
	@Column(name = "CONTENT", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getContent() {
		return this.content==null?this.content:this.content.replace("\r", "\\r").replace("\n", "\\n");
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Column(name = "IMG_PATH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	
	@Column(name = "GRADE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getGradeId() {
		return this.gradeId;
	}
	
	public void setGradeId(java.lang.Long value) {
		this.gradeId = value;
	}
	
	@Column(name = "SUBJECT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getSubjectId() {
		return this.subjectId;
	}
	
	public void setSubjectId(java.lang.Long value) {
		this.subjectId = value;
	}
	
	@Column(name = "KNOWLEDGE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getKnowledgeId() {
		return this.knowledgeId;
	}
	
	public void setKnowledgeId(java.lang.Long value) {
		this.knowledgeId = value;
	}
	
	@Transient
	public Date getAddTimeDate() {
		return dateUtils.longToDate(this.addTime);
	}
	@Column(name = "ADD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAddTime() {
		return this.addTime;
	}
	
	public void setAddTime(java.lang.Long value) {
		this.addTime = value;
	}
	
	@Transient
	public Date getStopTimeDate() {
		return dateUtils.longToDate(this.stopTime);
	}
	@Column(name = "STOP_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getStopTime() {
		return this.stopTime;
	}
	
	public void setStopTime(java.lang.Long value) {
		this.stopTime = value;
	}
	
	@Column(name = "STUDENT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getStudentId() {
		return this.studentId;
	}
	
	public void setStudentId(java.lang.Long value) {
		this.studentId = value;
	}
	
	@Column(name = "ANSWER_TEACHER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAnswerTeacherId() {
		return this.answerTeacherId;
	}
	
	public void setAnswerTeacherId(java.lang.Long value) {
		this.answerTeacherId = value;
	}
	
	@Column(name = "SOURCE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getSourceType() {
		return this.sourceType;
	}
	
	public void setSourceType(Integer value) {
		this.sourceType = value;
	}
	
	
	@Column(name = "SCORE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Long getScoreType() {
		return this.scoreType;
	}
	
	public void setScoreType(Long value) {
		this.scoreType = value;
	}
	
	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	@Column(name = "PLATFORM", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getPlatform() {
		return this.platform;
	}
	
	public void setPlatform(Integer value) {
		this.platform = value;
	}
	
	@Column(name = "REPORT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getReportId() {
		return this.reportId;
	}
	
	public void setReportId(java.lang.Long value) {
		this.reportId = value;
	}
	
	@Transient
	public Date getReportTimeDate() {
		return dateUtils.longToDate(this.reportTime);
	}
	@Column(name = "REPORT_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getReportTime() {
		return this.reportTime;
	}
	
	public void setReportTime(java.lang.Long value) {
		this.reportTime = value;
	}
	
	@Column(name = "AUDIT_STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getAuditState() {
		return this.auditState;
	}
	
	public void setAuditState(Integer value) {
		this.auditState = value;
	}
	
	@Column(name = "REPORT_RESULT", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Integer getReportResult() {
		return this.reportResult;
	}
	
	public void setReportResult(java.lang.Integer value) {
		this.reportResult = value;
	}
	
	@Column(name = "REPORT_CONTENT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getReportContent() {
		return this.reportContent;
	}
	
	public void setReportContent(java.lang.String value) {
		this.reportContent = value;
	}
	
	@Column(name = "IS_DEL", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsDel() {
		return this.isDel;
	}
	
	public void setIsDel(Integer value) {
		this.isDel = value;
	}
	
	@Column(name = "IS_QUIT", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsQuit() {
		return this.isQuit;
	}
	
	public void setIsQuit(Integer value) {
		this.isQuit = value;
	}
	
	@Column(name = "IS_LOCK", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsLock() {
		return this.isLock;
	}
	
	public void setIsLock(Integer value) {
		this.isLock = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Content",getContent())
			.append("ImgPath",getImgPath())
			.append("GradeId",getGradeId())
			.append("SubjectId",getSubjectId())
			.append("KnowledgeId",getKnowledgeId())
			.append("AddTime",getAddTime())
			.append("StudentId",getStudentId())
			.append("SourceType",getSourceType())
			.append("Status",getStatus())
			.append("Platform",getPlatform())
			.append("ReportId",getReportId())
			.append("ReportTime",getReportTime())
			.append("AuditState",getAuditState())
			.append("ReportResult",getReportResult())
			.append("ReportContent",getReportContent())
			.append("AnswerTeacherId",getAnswerTeacherId())
			.append("IsDel",getIsDel())
			.append("IsQuit",getIsQuit())
			.append("IsLock",getIsLock())
			.append("ScoreType",getScoreType())
			.append("StopTime",getStopTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Question == false) return false;
		if(this == obj) return true;
		Question other = (Question)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
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
	public Subject getSubject(){
		SubjectManager subjectManager = (SubjectManager)ApplicationContextHolder.getBean("subjectManager");
		Subject subject = null;
		if(null != this.getSubjectId()){
			subject = subjectManager.getById(this.getSubjectId());
		}
		if(null == subject){
			subject = new Subject();
		}
		return subject;
	}
	
	@Transient
	public Student getStudent(){
		StudentManager studentManager = (StudentManager)ApplicationContextHolder.getBean("studentManager");
		Student student = null;
		if(null != this.getStudentId()){
			student = studentManager.getById(this.getStudentId());
		}
		if(null == student){
			student = new Student();
		}
		return student;
	}
	
	@Transient
	public Teacher getReportUser(){
		TeacherManager teacherManager = (TeacherManager)ApplicationContextHolder.getBean("teacherManager");
		Teacher teacher = null;
		if(null != this.getReportId()){
			teacher = teacherManager.getById(this.getReportId());
		}
		if(null == teacher){
			teacher = new Teacher();
		}
		return teacher;
	}
	
	
	@Transient
	public String getHasKnowledge(){
		String result = "";
		if(null!= this.status && this.status==2){
			if(null != this.knowledgeId && this.knowledgeId > 0){
				result = "是";
			}else{
				result = "否";
			}
		}
		return result;
	}
	
	@Transient
	public Knowledge getKnowledge(){
		Knowledge knowledge = null;
		KnowledgeManager knowledgeManager = (KnowledgeManager)ApplicationContextHolder.getBean("knowledgeManager");
		knowledge = knowledgeManager.getById(this.knowledgeId);
		if(null == knowledge){
			knowledge = new Knowledge();
		}
		return knowledge;
	}
	
	@Transient
	public String getStatusStr(){
		String result = "";
		if(null != this.status){
			result = JTZSConstants.QuestionStatus.getName(this.status);
		}
		return result;
	}
	
	@Transient
	public Teacher getTeacher(){
		Teacher teacher = null;
		if(null!=this.answerTeacherId){
			TeacherManager teacherManager = (TeacherManager)ApplicationContextHolder.getBean("teacherManager");
			teacher = teacherManager.getById(this.answerTeacherId);
		}
		if(null == teacher){
			teacher = new Teacher();
		}
		return teacher;
	}
	
	@Transient
	public String getRestTimeStr(){
		String result = "00:00:00";
		Long stopTime = this.getStopTime();
		if(null != stopTime){
			stopTime = stopTime - new Date().getTime();
			if(stopTime > 0){
				result = dateUtils.longToTime(stopTime);
			}
		}
		return result;
	}
	
	@Transient
	public String getSourceTypeStr(){
		String result = "";
		if(null != this.sourceType){
			result = JTZSConstants.QuestionSourceType.getName(this.sourceType);
		}
		return result;
	}
	
	@Transient
	public Long getSatisfiedCount(){
		Long result = null;
		Answer answer = getAnswer();
		if(null != answer.getId()){
			CommentDao commentDao = (CommentDao)ApplicationContextHolder.getBean("commentDao");
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("isDel", JTZSConstants.IsDel.UNDELETE.getIndex());
			params.put("answerId", answer.getId());
			params.put("isSatisfied", JTZSConstants.SatisfiedStatus.SATISFIED.getIndex());
			result = commentDao.countByAggregate("count(id)", params);
		}
		if(null == result){
			result = 0L;
		}
		return result;
	}
	
	@Transient
	public Answer getAnswer(){
		Answer rusult = null;
		AnswerDao answerDao = (AnswerDao)ApplicationContextHolder.getBean("answerDao");
		Object[] params = new Object[2];
		params[0] = this.getId();
		params[1] = JTZSConstants.AnswerStatus.FINISH.getIndex();
		List<Answer> answerList = answerDao.findList("from Answer where questionId=? and status=?", params);
		if(null!=answerList && answerList.size()==1){
			rusult = answerList.get(0); 
		}
		if(null == rusult){
			rusult = new Answer();
		}
		return rusult;
	}
	
	@Transient
	public Long getUnsatisfiedCount(){
		Long result = null;
		Answer answer = getAnswer();
		if(null != answer.getId()){
			CommentDao commentDao = (CommentDao)ApplicationContextHolder.getBean("commentDao");
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("isDel", JTZSConstants.IsDel.UNDELETE.getIndex());
			params.put("answerId", answer.getId());
			params.put("isSatisfied", JTZSConstants.SatisfiedStatus.UNSATISFIED.getIndex());
			result = commentDao.countByAggregate("count(id)", params);
		}
		if(null == result){
			result = 0L;
		}
		return result;
	}
	
	@Transient
	public Integer getPageViewCount(){
		int rusult = 0;
		return rusult;
	}
	
	
	@Transient
	public String getAddTimeStr(){
		String rusult = null;
		if(null!= this.addTime){
			rusult = dateUtils.getRelativeTime(addTime);
		}
		if(null == rusult){
			rusult = "";
		}
		return rusult;
	}
	
	@Transient
	public String getAuditStateStr(){
		String rusult = null;
		if(null!= this.auditState){
			rusult =  JTZSConstants.AuditState.getName(this.auditState);
		}
		if(null == rusult){
			rusult = "";
		}
		return rusult;
	}
	
	@Transient
	public String getReportResultStr(){
		String rusult = null;
		if(null!= this.reportResult){
			rusult =  JTZSConstants.ReportResult.getName(this.reportResult);
		}
		if(null == rusult){
			rusult = "";
		}
		return rusult;
	}
	
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		DateUtils du = new DateUtils();
		System.out.println(du.getHour(1388090202593L - System.currentTimeMillis()));
	}
	
}

