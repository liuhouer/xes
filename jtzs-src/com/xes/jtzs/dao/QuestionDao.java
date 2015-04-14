/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.dao;

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
import com.xes.jtzs.vo.query.*;

import static com.up72.framework.util.ObjectUtils.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Hibernate数据处理
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Repository
public class QuestionDao extends BaseHibernateDao<Question,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Question.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(QuestionQuery query) {
        StringBuilder sql = new StringBuilder("select t from Question t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getContent())) {
        	sql.append(" /~  and  t.content like '%[content]%' ~/ ");
        }
        if(isNotEmpty(query.getImgPath())) {
            sql.append(" and  t.imgPath = :imgPath ");
        }
        if(isNotEmpty(query.getGradeId())) {
            sql.append(" and  t.gradeId = :gradeId ");
        }
        if(isNotEmpty(query.getSubjectId())) {
            sql.append(" and  t.subjectId = :subjectId ");
        }
        if(isNotEmpty(query.getKnowledgeId())) {
            sql.append(" and  t.knowledgeId = :knowledgeId ");
        }
        if(isNotEmpty(query.getIsKnowledge())) {
        	if(query.getIsKnowledge() > 0){
        		sql.append(" and  t.knowledgeId >0 ");
        	}else{
        		sql.append(" and  t.knowledgeId =0 ");
        	}
    		sql.append(" and  t.status = ").append(JTZSConstants.QuestionStatus.ENDANSWER.getIndex());
        }
        if(isNotEmpty(query.getAddTimeBegin())) {
            sql.append(" and  t.addTime >= :addTimeBegin ");
        }
        if(isNotEmpty(query.getAddTimeEnd())) {
            sql.append(" and  t.addTime <= :addTimeEnd ");
        }
        if(isNotEmpty(query.getStudentId())) {
            sql.append(" and  t.studentId = :studentId ");
        }
        if(isNotEmpty(query.getAnswerTeacherId())) {
        	sql.append(" and  t.answerTeacherId = :answerTeacherId ");
        }
        if(isNotEmpty(query.getSourceType())) {
            sql.append(" and  t.sourceType = :sourceType ");
        }
        if(isNotEmpty(query.getStatus())) {
            sql.append(" and  t.status = :status ");
        }
        if(isNotEmpty(query.getPlatform())) {
            sql.append(" and  t.platform = :platform ");
        }
        if(isNotEmpty(query.getReportId())) {
            sql.append(" and  t.reportId = :reportId ");
        }
        if(isNotEmpty(query.getReportTimeBegin())) {
            sql.append(" and  t.reportTime >= :reportTimeBegin ");
        }
        if(isNotEmpty(query.getReportTimeEnd())) {
            sql.append(" and  t.reportTime <= :reportTimeEnd ");
        }
        if(isNotEmpty(query.getAuditState())) {
            sql.append(" and  t.auditState = :auditState ");
        }
        if(isNotEmpty(query.getReportResult())) {
            sql.append(" and  t.reportResult = :reportResult ");
        }
        if(isNotEmpty(query.getReportContent())) {
            sql.append(" and  t.reportContent = :reportContent ");
        }
        if(isNotEmpty(query.getIsDel())) {
            sql.append(" and  t.isDel = :isDel ");
        }
        if(isNotEmpty(query.getIsQuit())) {
            sql.append(" and  t.isQuit = :isQuit ");
        }
        if(isNotEmpty(query.getIsLock())) {
            sql.append(" and  t.isLock = :isLock ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page myQuestionList(QuestionQuery query) {
		StringBuilder sql = new StringBuilder("select t from Question t where 1=1 ");
		if(isNotEmpty(query.getStudentId())) {
			sql.append(" and  t.studentId = :studentId ");
		}
        if(isNotEmpty(query.getStatus())) {
        	if(JTZSConstants.QuestionStatus.ENDANSWER.getIndex()==query.getStatus()){
        		sql.append(" and  t.status = :status ");
        	}else{
        		sql.append(" and  t.status!=").append(JTZSConstants.QuestionStatus.ENDANSWER.getIndex());
        	}
        }
		if(isNotEmpty(query.getIsQuit())) {
			sql.append(" and  t.isQuit = :isQuit ");
		}
		if(isNotEmpty(query.getIsDel())) {
			sql.append(" and  t.isDel = :isDel ");
		}
		if(isNotEmpty(query.getIsLock())) {
			sql.append(" and  t.isLock = :isLock ");
		}
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
		return pageQuery(sql.toString(),query);
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page teacherQuestionList(QuestionQuery query) {
		StringBuilder sql = new StringBuilder("select t from Question t where 1=1 ");
		if(null != query.getGradeIdList() && query.getGradeIdList().length > 0){
			sql.append(" and  (");
			for (int i = 0; i < query.getGradeIdList().length; i++) {
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" t.gradeId=").append(query.getGradeIdList()[i]);
			}
			sql.append(" )");
		}
		if(isNotEmpty(query.getSubjectId())) {
			sql.append(" and  t.subjectId = :subjectId ");
		}
		if(isNotEmpty(query.getStatus())) {
			sql.append(" and  t.status = :status ");
		}
		if(isNotEmpty(query.getNotStatus())) {
			sql.append(" and  t.status != :notStatus ");
		}
		sql.append(" and  t.isLock=").append(JTZSConstants.QuestionIsLock.UNLOCK.getIndex());
		sql.append(" and  t.isQuit=").append(JTZSConstants.QuestionIsQuit.NORMAL.getIndex());
		sql.append(" and  t.isDel=").append(JTZSConstants.IsDel.UNDELETE.getIndex());
		sql.append(" and  t.sourceType=").append(JTZSConstants.QuestionSourceType.NORMAL.getIndex());
		if(isNotEmpty(query.getSortColumns())) {
			sql.append(" /~ order by [sortColumns] ~/ ");
		}
		return pageQuery(sql.toString(),query);
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page expertQuestionList(QuestionQuery query) {
		StringBuilder sql = new StringBuilder("select t from Question t where 1=1 ");
		if(null != query.getGradeIdList() && query.getGradeIdList().length > 0){
			sql.append(" and  (");
			for (int i = 0; i < query.getGradeIdList().length; i++) {
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" t.gradeId=").append(query.getGradeIdList()[i]);
			}
			sql.append(" )");
		}
		if(isNotEmpty(query.getSubjectId())) {
			sql.append(" and  t.subjectId = :subjectId ");
		}
		if(isNotEmpty(query.getStatus())) {
			sql.append(" and  t.status = :status ");
		}
		if(isNotEmpty(query.getNotStatus())) {
			sql.append(" and  t.status != :notStatus ");
		}
		sql.append(" and  t.isLock=").append(JTZSConstants.QuestionIsLock.UNLOCK.getIndex());
		sql.append(" and  t.isQuit=").append(JTZSConstants.QuestionIsQuit.NORMAL.getIndex());
		sql.append(" and  t.isDel=").append(JTZSConstants.IsDel.UNDELETE.getIndex());
		if(isNotEmpty(query.getSortColumns())) {
			sql.append(" /~ order by [sortColumns] ~/ ");
		}	
		return pageQuery(sql.toString(),query);
	}

}
