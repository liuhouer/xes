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
public class TeacherStatisticsDao extends BaseHibernateDao<TeacherStatistics,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return TeacherStatistics.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(TeacherStatisticsQuery query) {
        StringBuilder sql = new StringBuilder("select t from TeacherStatistics t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getTeacherId())) {
            sql.append(" and  t.teacherId = :teacherId ");
        }
        if(isNotEmpty(query.getTwentyMinNum())) {
            sql.append(" and  t.twentyMinNum = :twentyMinNum ");
        }
        if(isNotEmpty(query.getHalfHourNum())) {
            sql.append(" and  t.halfHourNum = :halfHourNum ");
        }
        if(isNotEmpty(query.getOneHourNum())) {
            sql.append(" and  t.oneHourNum = :oneHourNum ");
        }
        if(isNotEmpty(query.getExpertNum())) {
            sql.append(" and  t.expertNum = :expertNum ");
        }
        if(isNotEmpty(query.getQuitNum())) {
            sql.append(" and  t.quitNum = :quitNum ");
        }
        if(isNotEmpty(query.getAnswerNum())) {
            sql.append(" and  t.answerNum = :answerNum ");
        }
        if(isNotEmpty(query.getShowNum())) {
            sql.append(" and  t.showNum = :showNum ");
        }
        if(isNotEmpty(query.getSatisfy())) {
            sql.append(" and  t.satisfy = :satisfy ");
        }
        if(isNotEmpty(query.getUnsatisfy())) {
            sql.append(" and  t.unsatisfy = :unsatisfy ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
}
