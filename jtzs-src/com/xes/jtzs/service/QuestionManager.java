/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.up72.common.CommonUtils.*;
import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import com.up72.framework.beanutils.BeanUtils;

import java.util.*;

import com.up72.base.*;
import com.up72.util.*;

import com.up72.framework.util.*;
import com.up72.framework.web.util.*;
import com.up72.framework.page.*;
import com.up72.framework.page.impl.*;

import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.model.*;
import com.xes.jtzs.dao.*;
import com.xes.jtzs.service.*;
import com.xes.jtzs.vo.query.*;/**
 * 业务处理
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class QuestionManager extends BaseManager<Question,java.lang.Long>{

	private QuestionDao questionDao;

	public void setQuestionDao(QuestionDao dao) {
		this.questionDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.questionDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(QuestionQuery query) {
		return questionDao.findPage(query);
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page teacherQuestionList(QuestionQuery query) {
		return questionDao.teacherQuestionList(query);
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page expertQuestionList(QuestionQuery query) {
		return questionDao.expertQuestionList(query);
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Question queryNowAnswerQuestion(Long teacherId) {
		Question question = null;
		List<Question> list = questionDao.findList("FROM Question WHERE answerTeacherId=? and status=?", new Object[]{teacherId,JTZSConstants.QuestionStatus.NOWANSWER.getIndex()});
		if(null!=list && list.size()>0){
			question = list.get(0);
		}
		return question; 
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page myQuestionList(Long studentId,Integer questionStatus) {
		QuestionQuery query = new QuestionQuery();
		query.setPageNumber(1);
		query.setPageSize(JTZSConstants.publicRange);
		query.setStudentId(studentId);
		query.setStatus(questionStatus);
		query.setIsQuit(JTZSConstants.QuestionIsQuit.NORMAL.getIndex());
		query.setIsDel(JTZSConstants.IsDel.UNDELETE.getIndex());
		query.setIsLock(JTZSConstants.QuestionIsLock.UNLOCK.getIndex());
		query.setSortColumns("addTime desc");
		return questionDao.myQuestionList(query);
	}
	
	/**
	 * 老师举报的未违规问题统计
	 * @param studentId
	 * @param questionStatus
	 * @return
	 */
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public int noviolationQuestionCountByTeacherId(Long teacherId) {
		Long count = questionDao.countByAggregate("select count(id) from Question where reportId=? and auditState=? and reportResult=?", 
				new Object[]{teacherId,JTZSConstants.AuditState.TREATED.getIndex(),JTZSConstants.ReportResult.NOVIOLATION.getIndex()});
		if(null == count){
			count = 0L;
		}
		if(count > 5){
			count = 5L;
		}
		return count.intValue();
	}
	
	/**
	 * 学生违规问题统计
	 * @param studentId
	 * @param questionStatus
	 * @return
	 */
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public int violationQuestionCountByStudentId(Long studentId) {
		Long count = questionDao.countByAggregate("select count(id) from Question where studentId=? and auditState=? and reportResult=?", 
				new Object[]{studentId,JTZSConstants.AuditState.TREATED.getIndex(),JTZSConstants.ReportResult.VIOLATION.getIndex()});
		if(null == count){
			count = 0L;
		}
		if(count > 5){
			count = 5L;
		}
		return count.intValue();
	}
}
