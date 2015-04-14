/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import org.springframework.stereotype.Repository;

import com.up72.auth.model.Organization;
import com.up72.auth.vo.query.OrganizationQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;

/**
 * 权限机构Hibernate数据处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class OrganizationDao extends BaseHibernateDao<Organization,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Organization.class;
	}
	
	/**
	 * @author bxmen
	 * @param query
	 * @return Page
	 * @summary 
	 */
	@SuppressWarnings({ "unchecked" })
	public Page findPage(OrganizationQuery query) {
        StringBuilder sql = new StringBuilder("select t from Organization t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getParent())) {
            sql.append(" and  t.parent = :parent ");
        }
        if(isNotEmpty(query.getName())) {
            sql.append(" and  t.name like '%" + query.getName() + "%' ");
        }
        if(isNotEmpty(query.getDomain())) {
            sql.append(" and  t.domain = :domain ");
        }
        if(isNotEmpty(query.getRemark())) {
            sql.append(" and  t.remark = :remark ");
        }
        if(isNotEmpty(query.getEnabled())) {
            sql.append(" and  t.enabled = :enabled ");
        }
        if(isNotEmpty(query.getOlevel())) {
            sql.append(" and  t.olevel = :olevel ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

}
