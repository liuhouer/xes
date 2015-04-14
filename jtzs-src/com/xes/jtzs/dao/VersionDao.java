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
public class VersionDao extends BaseHibernateDao<Version,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Version.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(VersionQuery query) {
        StringBuilder sql = new StringBuilder("select t from Version t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getType())) {
            sql.append(" and  t.type = :type ");
        }
        if(isNotEmpty(query.getVersion())) {
            sql.append(" and  t.version = :version ");
        }
        if(isNotEmpty(query.getSize())) {
            sql.append(" and  t.size = :size ");
        }
        if(isNotEmpty(query.getAppUrl())) {
            sql.append(" and  t.appUrl = :appUrl ");
        }
        if(isNotEmpty(query.getUpdateInfo())) {
            sql.append(" and  t.updateInfo = :updateInfo ");
        }
        if(isNotEmpty(query.getAddTimeBegin())) {
            sql.append(" and  t.addTime >= :addTimeBegin ");
        }
        if(isNotEmpty(query.getAddTimeEnd())) {
            sql.append(" and  t.addTime <= :addTimeEnd ");
        }
        if(isNotEmpty(query.getUpdateTimeBegin())) {
            sql.append(" and  t.updateTime >= :updateTimeBegin ");
        }
        if(isNotEmpty(query.getUpdateTimeEnd())) {
            sql.append(" and  t.updateTime <= :updateTimeEnd ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

}
