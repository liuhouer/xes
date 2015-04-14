/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2011
 */

package com.up72.sys.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import org.springframework.stereotype.Repository;

import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;
import com.up72.sys.model.Region;
import com.up72.sys.vo.query.RegionQuery;

/**
 * 地区Hibernate数据处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class RegionDao extends BaseHibernateDao<Region,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Region.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(RegionQuery query) {
        StringBuilder sql = new StringBuilder("select t from Region t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getRegion())) {
            sql.append(" and  t.region = :region ");
        }
        if(isNotEmpty(query.getRegionName())) {
            sql.append(" and  t.regionName = :regionName ");
        }
        if(isNotEmpty(query.getRegionType())) {
            sql.append(" and  t.regionType = :regionType ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	
	
}
