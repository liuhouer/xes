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
public class AreaDao extends BaseHibernateDao<Area,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Area.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(AreaQuery query) {
        StringBuilder sql = new StringBuilder("select t from Area t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getName())) {
        	sql.append(" /~  and  t.name like '%[name]%' ~/ ");
        }
        if(isNotEmpty(query.getSort())) {
            sql.append(" and  t.sort = :sort ");
        }
        if(isNotEmpty(query.getEnName())) {
            sql.append(" and  t.enName = :enName ");
        }
        if(isNotEmpty(query.getStatus())) {
            sql.append(" and  t.status = :status ");
        }
        if(isNotEmpty(query.getCityId()) && query.getCityId()>0) {
            sql.append(" and  t.cityId = :cityId ");
        }
        if(isNotEmpty(query.getProvinceId()) && query.getProvinceId()>0) {
            sql.append(" and  t.provinceId = :provinceId ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

}
