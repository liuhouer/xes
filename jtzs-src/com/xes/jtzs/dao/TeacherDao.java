/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.dao;

import com.up72.base.*;
import com.up72.framework.page.*;
import com.xes.jtzs.model.*;
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
public class TeacherDao extends BaseHibernateDao<Teacher,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Teacher.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(TeacherQuery query) {
        StringBuilder sql = new StringBuilder("select t from Teacher t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getProvinceId()) && query.getProvinceId() >0 ) {
            sql.append(" and  t.provinceId = :provinceId ");
        }
        if(isNotEmpty(query.getAreaId()) && query.getAreaId() >0 ) {
            sql.append(" and  t.areaId = :areaId ");
        }
        if(isNotEmpty(query.getLevel()) && query.getLevel() >0 ) {
            sql.append(" and  t.level = :level ");
        }
        if(isNotEmpty(query.getNickName())) {
            sql.append(" /~  and  t.nickName like '%[nickName]%' ~/ ");
        }
        if(isNotEmpty(query.getRealName())) {
        	sql.append(" /~  and  t.realName like '%[realName]%' ~/ ");
        }
        if(isNotEmpty(query.getLoginName())) {
        	sql.append(" /~  and  t.loginName like '%[loginName]%' ~/ ");
        }
        if(isNotEmpty(query.getExpertGradeIds())) {
            sql.append(" and  t.expertGradeIds = :expertGradeIds ");
        }
        if(isNotEmpty(query.getExpertGradeId()) && query.getExpertGradeId() >0 ) {
        	sql.append(" and  t.expertGradeIds like '%["+query.getExpertGradeId()+"]%'");
        }
        if(isNotEmpty(query.getExpertSubjectId())&& query.getExpertSubjectId() >0 ) {
            sql.append(" and  t.expertSubjectId = :expertSubjectId ");
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
        if(isNotEmpty(query.getStatus())) {
            sql.append(" and  t.status = :status ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

}
