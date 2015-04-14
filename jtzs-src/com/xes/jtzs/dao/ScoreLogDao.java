/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class ScoreLogDao extends BaseHibernateDao<ScoreLog,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return ScoreLog.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(ScoreLogQuery query) {
        StringBuilder sql = new StringBuilder("select t from ScoreLog t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getScore())) {
            sql.append(" and  t.score = :score ");
        }
        if(isNotEmpty(query.getUserRole())) {
            sql.append(" and  t.userRole = :userRole ");
        }
        if(isNotEmpty(query.getUserId())) {
            sql.append(" and  t.userId = :userId ");
        }
        if(isNotEmpty(query.getStartTime())) {//开始
        	String startTimeString = query.getStartTime();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    		if(startTimeString!=null&&startTimeString.length()>0){
    			Date date;
				try {
					date = sdf.parse(startTimeString);
					Long longtime = date.getTime();
					sql.append(" and  t.addTime >= "+longtime+" ");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
        }
        if(isNotEmpty(query.getEndTime())) {//结束
            String endTimeString = query.getEndTime();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    		if(endTimeString!=null&&endTimeString.length()>0){
    			Date date;
				try {
					date = sdf.parse(endTimeString);
					Long longtime = date.getTime();
					sql.append(" and  t.addTime <= "+longtime+" ");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
        }
        
        
        if(isNotEmpty(query.getAddTimeBegin())) {
            sql.append(" and  t.addTime >= :addTimeBegin ");
        }
        if(isNotEmpty(query.getAddTimeEnd())) {
            sql.append(" and  t.addTime <= :addTimeEnd ");
        }
        if(isNotEmpty(query.getAddTimeStart())) {
            sql.append(" and  t.addTime > :addTimeStart ");
        }
        if(isNotEmpty(query.getAddTimeStop())) {
            sql.append(" and  t.addTime < :addTimeStop ");
        }
        if(isNotEmpty(query.getOperatorId())) {
            sql.append(" and  t.operatorId = :operatorId ");
        }
        if(isNotEmpty(query.getRemark())) {
            sql.append(" and  t.remark = :remark ");
        }
        if(isNotEmpty(query.getContent())) {
            sql.append(" and  t.content = :content ");
        }
        if(isNotEmpty(query.getScoreType())) {
            sql.append(" and  t.scoreType = :scoreType ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

}
