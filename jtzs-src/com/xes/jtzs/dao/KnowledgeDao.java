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
public class KnowledgeDao extends BaseHibernateDao<Knowledge,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Knowledge.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(KnowledgeQuery query) {
        StringBuilder sql = new StringBuilder("select t from Knowledge t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getGradeId())) {
            sql.append(" and  t.gradeId = :gradeId ");
        }
        if(isNotEmpty(query.getSubjectId())) {
            sql.append(" and  t.subjectId = :subjectId ");
        }
        if(isNotEmpty(query.getKnowledge1())) {
            sql.append(" and  t.knowledge1 = :knowledge1 ");
        }
        if(isNotEmpty(query.getKnowledge2())) {
            sql.append(" and  t.knowledge2 = :knowledge2 ");
        }
        if(isNotEmpty(query.getKnowledge3())) {
            sql.append(" and  t.knowledge3 = :knowledge3 ");
        }
        if(isNotEmpty(query.getKnowledge())) {
            sql.append(" and  t.knowledge1 like '%"+query.getKnowledge()+"%' or  t.knowledge2 like '%"+query.getKnowledge()+"%' or  t.knowledge3 like '%"+query.getKnowledge()+"%'  ");
        }
        if(isNotEmpty(query.getAddTimeBegin())) {
            sql.append(" and  t.addTime >= :addTimeBegin ");
        }
        if(isNotEmpty(query.getAddTimeEnd())) {
            sql.append(" and  t.addTime <= :addTimeEnd ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

}
