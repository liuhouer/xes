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

import com.xes.jtzs.model.*;
import com.xes.jtzs.dao.*;
import com.xes.jtzs.service.*;
import com.xes.jtzs.vo.query.*;
import static com.up72.framework.util.ObjectUtils.*;
import org.springframework.stereotype.Repository;

/**
 * Hibernate数据处理
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Repository
public class AnswerDao extends BaseHibernateDao<Answer,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Answer.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(AnswerQuery query) {
        StringBuilder sql = new StringBuilder("select t from Answer t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getQuestionId())) {
            sql.append(" and  t.questionId = :questionId ");
        }
        if(isNotEmpty(query.getTeacherId())) {
            sql.append(" and  t.teacherId = :teacherId ");
        }
        if(isNotEmpty(query.getContent())) {
            sql.append(" and  t.content = :content ");
        }
        if(isNotEmpty(query.getImgPath())) {
        	sql.append(" and  t.imgPath = :imgPath ");
        }
        if(isNotEmpty(query.getIder())) {
            sql.append(" and  t.ider = :ider ");
        }
        if(isNotEmpty(query.getAnswerTimeBegin())) {
            sql.append(" and  t.answerTime >= :answerTimeBegin ");
        }
        if(isNotEmpty(query.getAnswerTimeEnd())) {
            sql.append(" and  t.answerTime <= :answerTimeEnd ");
        }
        if(isNotEmpty(query.getStatus())) {
            sql.append(" and  t.status = :status ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

}
