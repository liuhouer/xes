/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
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
public class SystemMessageDao extends BaseHibernateDao<SystemMessage,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return SystemMessage.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(SystemMessageQuery query) {
        StringBuilder sql = new StringBuilder("select t from SystemMessage t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getQuestionId())) {
            sql.append(" and  t.questionId = :questionId ");
        }
        if(isNotEmpty(query.getUserId())) {
            sql.append(" and  t.userId = :userId ");
        }
        if(isNotEmpty(query.getUserRole())) {
            sql.append(" and  t.userRole = :userRole ");
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
        if(isNotEmpty(query.getIsRead())) {
            sql.append(" and  t.isRead = :isRead ");
        }
        if(isNotEmpty(query.getAddUser())) {
            sql.append(" and  t.addUser = :addUser ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

}
