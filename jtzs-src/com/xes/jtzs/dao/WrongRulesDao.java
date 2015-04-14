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
public class WrongRulesDao extends BaseHibernateDao<WrongRules,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return WrongRules.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(WrongRulesQuery query) {
        StringBuilder sql = new StringBuilder("select t from WrongRules t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getRole())) {
            sql.append(" and  t.role = :role ");
        }
        if(isNotEmpty(query.getWrongNum())) {
            sql.append(" and  t.wrongNum = :wrongNum ");
        }
        if(isNotEmpty(query.getContent())) {
            sql.append(" and  t.content = :content ");
        }
        if(isNotEmpty(query.getIsDelQuestion())) {
            sql.append(" and  t.isDelQuestion = :isDelQuestion ");
        }
        if(isNotEmpty(query.getDelScore())) {
            sql.append(" and  t.delScore = :delScore ");
        }
        if(isNotEmpty(query.getDelScoreStart())) {
        	sql.append(" and  t.delScore >= :delScoreStart ");
        }
        if(isNotEmpty(query.getDelScoreEnd())) {
        	sql.append(" and  t.delScore <= :delScoreEnd ");
        }
        if(isNotEmpty(query.getIsStopLogin())) {
            sql.append(" and  t.isStopLogin = :isStopLogin ");
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
