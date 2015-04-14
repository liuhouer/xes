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
public class StudentDao extends BaseHibernateDao<Student,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Student.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(StudentQuery query) {
        StringBuilder sql = new StringBuilder("select t from Student t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getStatus())) {
            sql.append(" and  t.status = :status ");
        }
        if(isNotEmpty(query.getProvinceId())) {
            sql.append(" and  t.provinceId = :provinceId ");
        }
        if(isNotEmpty(query.getNickName())) {
            sql.append(" /~  and  t.nickName like '%[nickName]%' ~/ ");
        }
        if(isNotEmpty(query.getLoginName())) {
            sql.append(" /~  and  t.loginName like '%[loginName]%' ~/ ");
        }
        if(isNotEmpty(query.getSchoolId())) {
            sql.append(" and  t.schoolId = :schoolId ");
        }
        if(isNotEmpty(query.getGradeId())) {
            sql.append(" and  t.gradeId = :gradeId ");
        }
        if(isNotEmpty(query.getSex())) {
            sql.append(" and  t.sex = :sex ");
        }
        if(isNotEmpty(query.getImgPath())) {
            sql.append(" and  t.imgPath = :imgPath ");
        }
        if(isNotEmpty(query.getLastLoginTimeBegin())) {
            sql.append(" and  t.lastLoginTime >= :lastLoginTimeBegin ");
        }
        if(isNotEmpty(query.getLastLoginTimeEnd())) {
            sql.append(" and  t.lastLoginTime <= :lastLoginTimeEnd ");
        }
        if(isNotEmpty(query.getAddTimeBegin())) {
            sql.append(" and  t.addTime >= :addTimeBegin ");
        }
        if(isNotEmpty(query.getAddTimeEnd())) {
            sql.append(" and  t.addTime <= :addTimeEnd ");
        }
        if(isNotEmpty(query.getPassword())) {
            sql.append(" and  t.password = :password ");
        }
        if(isNotEmpty(query.getPlatform())) {
            sql.append(" and  t.platform = :platform ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

}
