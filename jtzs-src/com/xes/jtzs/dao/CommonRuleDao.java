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
public class CommonRuleDao extends BaseHibernateDao<CommonRule,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return CommonRule.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(CommonRuleQuery query) {
        StringBuilder sql = new StringBuilder("select t from CommonRule t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getTitle())) {
            sql.append(" and  t.title = :title ");
        }
        if(isNotEmpty(query.getNum())) {
            sql.append(" and  t.num = :num ");
        }
        if(isNotEmpty(query.getScore())) {
            sql.append(" and  t.score = :score ");
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
        if(isNotEmpty(query.getValidStartTimeBegin())) {
            sql.append(" and  t.validStartTime >= :validStartTimeBegin ");
        }
        if(isNotEmpty(query.getValidStartTimeEnd())) {
            sql.append(" and  t.validStartTime <= :validStartTimeEnd ");
        }
        if(isNotEmpty(query.getValidStopTimeBegin())) {
            sql.append(" and  t.validStopTime >= :validStopTimeBegin ");
        }
        if(isNotEmpty(query.getValidStopTimeEnd())) {
            sql.append(" and  t.validStopTime <= :validStopTimeEnd ");
        }
        if(isNotEmpty(query.getBeginTimeBegin())) {
            sql.append(" and  t.beginTime >= :beginTimeBegin ");
        }
        if(isNotEmpty(query.getBeginTimeEnd())) {
            sql.append(" and  t.beginTime <= :beginTimeEnd ");
        }
        if(isNotEmpty(query.getEndTimeBegin())) {
            sql.append(" and  t.endTime >= :endTimeBegin ");
        }
        if(isNotEmpty(query.getEndTimeEnd())) {
            sql.append(" and  t.endTime <= :endTimeEnd ");
        }
        if(isNotEmpty(query.getRuleType())) {
            sql.append(" and  t.ruleType = :ruleType ");
        }
        if(isNotEmpty(query.getStatus())) {
        	sql.append(" and  t.status = :status ");
        }
        if(isNotEmpty(query.getScoreType())) {
            sql.append(" and  t.scoreType = :scoreType ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	
	/**
	 * 验证规则是否有效
	 * @author liutongling
	 * @param commonRule
	 * @return
	 */
	@Transactional(readOnly=true)
	public boolean isValid(CommonRule commonRule){
		boolean result = true;
		if(null != commonRule){
			if(commonRule.getStatus() == JTZSConstants.Pubilc.DISABLE.getIndex()){
				result = false;
			}else if(null != commonRule.getValidStartTime()){
				long time = new Date().getTime();
				if(null != commonRule.getValidStopTime() && (commonRule.getValidStartTime() > time || time > commonRule.getValidStopTime())){
					result = false;
				}
			}else if(null != commonRule.getValidStartTime()){
				result = false;
			}
		}else{
			result = false;
		} 
		return result;
	}

}
