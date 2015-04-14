/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.up72.auth.model.RolePermission;
import com.up72.auth.vo.query.RolePermissionQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;

/**
 * 权限与角色多对多关系Hibernate数据处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class RolePermissionDao extends BaseHibernateDao<RolePermission,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return RolePermission.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(RolePermissionQuery query) {
        //XsqlBuilder 
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from RolePermission t where 1=1 "
			  	+ "/~ and t.roleId = {roleId} ~/"
			  	+ "/~ and t.permissionId = {permissionId} ~/"
			  	+ "/~ and t.organizationId = {organizationId} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from RolePermission t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getRoleId())) {
            sql2.append(" and  t.roleId = :roleId ");
        }
        if(isNotEmpty(query.getPermissionId())) {
            sql2.append(" and  t.permissionId = :permissionId ");
        }
        if(isNotEmpty(query.getOrganizationId())) {
            sql2.append(" and  t.organizationId = :organizationId ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}
	
	
	/**
	 * 获得integer类型的list
	 * @author wqtan
	 */
	public List<Long> findIntegerList(final String hsql,final Object[] params){
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Long>>(){
			public List<Long> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hsql);
				if(ObjectUtils.isNotEmpty(params)){
					for(int i=0;i<params.length;i++){
						query.setParameter(i,params[i]);
					}
				}
				return query.list();
			}
		});
	}
		
}
