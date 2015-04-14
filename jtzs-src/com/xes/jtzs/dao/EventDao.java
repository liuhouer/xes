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
public class EventDao extends BaseHibernateDao<Event,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Event.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(EventQuery query) {
        StringBuilder sql = new StringBuilder("select t from Event t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getTitle())) {
            sql.append(" and  t.title like '%"+query.getTitle()+"%' ");
        }
        if(isNotEmpty(query.getSendTo())) {
            sql.append(" and  t.sendTo = :sendTo ");
        }
        if(isNotEmpty(query.getStartTime())) {//有效期开始
        	String startTimeString = query.getStartTime();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    		if(startTimeString!=null&&startTimeString.length()>0){
    			Date date;
				try {
					date = sdf.parse(startTimeString);
					Long longtime = date.getTime();
					sql.append(" and  t.startTime >= "+longtime+" ");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
        }
        if(isNotEmpty(query.getEndTime())) {//有效期结束
            String endTimeString = query.getEndTime();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    		if(endTimeString!=null&&endTimeString.length()>0){
    			Date date;
				try {
					date = sdf.parse(endTimeString);
					Long longtime = date.getTime();
					sql.append(" and  t.endTime <= "+longtime+" ");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
        }
        if(isNotEmpty(query.getSendTimeBegin())) {
            sql.append(" and  t.sendTime >= :sendTimeBegin ");
        }
        if(isNotEmpty(query.getSendTimeEnd())) {
            sql.append(" and  t.sendTime <= :sendTimeEnd ");
        }
        if(isNotEmpty(query.getSendStatus())) {
            sql.append(" and  t.sendStatus = :sendStatus ");
        }
        if(isNotEmpty(query.getSendUser())) {
            sql.append(" and  t.sendUser = :sendUser ");
        }
        if(isNotEmpty(query.getImgPath())) {
            sql.append(" and  t.imgPath = :imgPath ");
        }
        if(isNotEmpty(query.getSummary())) {
            sql.append(" and  t.summary = :summary ");
        }
        if(isNotEmpty(query.getContent())) {
            sql.append(" and  t.content = :content ");
        }
        if(isNotEmpty(query.getQuestion1())) {
            sql.append(" and  t.question1 = :question1 ");
        }
        if(isNotEmpty(query.getQuestion2())) {
            sql.append(" and  t.question2 = :question2 ");
        }
        if(isNotEmpty(query.getQuestion3())) {
            sql.append(" and  t.question3 = :question3 ");
        }
        if(isNotEmpty(query.getQuestion4())) {
            sql.append(" and  t.question4 = :question4 ");
        }
        if(isNotEmpty(query.getQuestion5())) {
            sql.append(" and  t.question5 = :question5 ");
        }
        if(isNotEmpty(query.getQuestion6())) {
            sql.append(" and  t.question6 = :question6 ");
        }
        if(isNotEmpty(query.getQuestion7())) {
            sql.append(" and  t.question7 = :question7 ");
        }
        if(isNotEmpty(query.getAnswer())) {
            sql.append(" and  t.answer = :answer ");
        }
        if(isNotEmpty(query.getStartTimeBegin())) {
            sql.append(" and  t.startTime >= :startTimeBegin ");
        }
        if(isNotEmpty(query.getStartTimeEnd())) {
            sql.append(" and  t.startTime <= :startTimeEnd ");
        }
        if(isNotEmpty(query.getEndTimeBegin())) {
            sql.append(" and  t.endTime >= :endTimeBegin ");
        }
        if(isNotEmpty(query.getEndTimeEnd())) {
            sql.append(" and  t.endTime <= :endTimeEnd ");
        }
        if(isNotEmpty(query.getAddScore())) {
            sql.append(" and  t.addScore = :addScore ");
        }
        if(isNotEmpty(query.getDelScore())) {
            sql.append(" and  t.delScore = :delScore ");
        }
        if(isNotEmpty(query.getProvinceId())) {
            sql.append(" and  t.provinceId = :provinceId ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

}
