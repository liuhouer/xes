/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.auth.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import org.springframework.stereotype.Repository;

import com.up72.auth.model.Menu;
import com.up72.auth.vo.query.MenuQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;

/**
 * Hibernate数据处理
 * 
 * @author up72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class MenuDao extends BaseHibernateDao<Menu,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Menu.class;
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(MenuQuery query) {
        StringBuilder sql = new StringBuilder("select t from Menu t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getParentId())) {
            sql.append(" and  t.parentId = :parentId ");
        }
        if(isNotEmpty(query.getPermissionId())) {
            sql.append(" and  t.permissionId = :permissionId ");
        }
        if(isNotEmpty(query.getName())) {
            sql.append(" and  t.name = :name ");
        }
        if(isNotEmpty(query.getIcon())) {
            sql.append(" and  t.icon = :icon ");
        }
        if(isNotEmpty(query.getSortId())) {
            sql.append(" and  t.sortId = :sortId ");
        }
        if(isNotEmpty(query.getLevel())) {
            sql.append(" and  t.level = :level ");
        }
        if(isNotEmpty(query.getRoleId())) {
            sql.append(" and  t.roleId = :roleId ");
        }
        if(isNotEmpty(query.getAddTimeBegin())) {
            sql.append(" and  t.addTime >= :addTimeBegin ");
        }
        if(isNotEmpty(query.getAddTimeEnd())) {
            sql.append(" and  t.addTime <= :addTimeEnd ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}


	
	

}
