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
public class ScoreDao extends BaseHibernateDao<Score,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Score.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(ScoreQuery query) {
        StringBuilder sql = new StringBuilder("select t from Score t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getUserRole())) {
            sql.append(" and  t.userRole = :userRole ");
        }
        if(isNotEmpty(query.getUserId())) {
            sql.append(" and  t.userId = :userId ");
        }
        if(isNotEmpty(query.getScore())) {
            sql.append(" and  t.score = :score ");
        }
        if(isNotEmpty(query.getUseScore())) {
            sql.append(" and  t.useScore = :useScore ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

}
