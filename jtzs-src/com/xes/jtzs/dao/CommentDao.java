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
public class CommentDao extends BaseHibernateDao<Comment,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Comment.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(CommentQuery query) {
        StringBuilder sql = new StringBuilder("select t from Comment t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getContent())) {
            sql.append(" and  t.content = :content ");
        }
        if(isNotEmpty(query.getStudentId())) {
            sql.append(" and  t.studentId = :studentId ");
        }
        if(isNotEmpty(query.getTeacherId())) {
        	sql.append(" and  t.teacherId = :teacherId ");
        }
        if(isNotEmpty(query.getAddTimeBegin())) {
            sql.append(" and  t.addTime >= :addTimeBegin ");
        }
        if(isNotEmpty(query.getAddTimeEnd())) {
            sql.append(" and  t.addTime <= :addTimeEnd ");
        }
        if(isNotEmpty(query.getIsSatisfied())) {
            sql.append(" and  t.isSatisfied = :isSatisfied ");
        }
        if(isNotEmpty(query.getAnswerId())) {
            sql.append(" and  t.answerId = :answerId ");
        }
        if(isNotEmpty(query.getIsDel())) {
            sql.append(" and  t.isDel = :isDel ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

}
