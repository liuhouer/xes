/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.dao;

import static com.up72.common.CommonUtils.stringUtil;
import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.up72.auth.model.Permission;
import com.up72.auth.vo.query.PermissionQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;

/**
 * 权限表（菜单，资源）Hibernate数据处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class PermissionDao extends BaseHibernateDao<Permission,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Permission.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(PermissionQuery query) {
        //XsqlBuilder 
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from Permission t where 1=1 "
			  	+ "/~ and t.permissionGroupId = {permissionGroupId} ~/"
			  	+ "/~ and t.name = {name} ~/"
			  	+ "/~ and t.description = {description} ~/"
			  	+ "/~ and t.url = {url} ~/"
			  	+ "/~ and t.enabled = {enabled} ~/"
			  	+ "/~ and t.sort = {sort} ~/"
			  	+ "/~ and t.type = {type} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from Permission t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getPermissionGroupCode())) {
            sql2.append(" and  t.permissionGroupId = :permissionGroupId ");
        }
        if(isNotEmpty(query.getName())) {
            sql2.append(" and  t.name = :name ");
        }
        if(isNotEmpty(query.getDescription())) {
            sql2.append(" and  t.description = :description ");
        }
        if(isNotEmpty(query.getUrl())) {
            sql2.append(" and  t.url = :url ");
        }
        if(isNotEmpty(query.getEnabled())) {
            sql2.append(" and  t.enabled = :enabled ");
        }
        if(isNotEmpty(query.getSort())) {
            sql2.append(" and  t.sort = :sort ");
        }
        if(isNotEmpty(query.getType())) {
            sql2.append(" and  t.type = :type ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}
	
	/**
	 * 获得指定权限组的权限列表
	 * @author wqtan
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Permission> getPermissionList(String permGroupCode,Integer type){
		StringBuffer hsql = new StringBuffer("from Permission p where p.permissionGroupCode=?");
		Object[] params = null;
		if(ObjectUtils.isNotEmpty(type)){
			hsql.append(" and p.type=?");
			params = new Object[]{permGroupCode,type};
		}else{
			params = new Object[]{permGroupCode};
		}

		TreeMap<String,String> orders = new TreeMap<String, String>();
		orders.put("sortId", "asc");
		return this.findList(hsql.toString(), params, 0, orders);
	}
	
	/**
	 * 获得指定权限组的权限列表
	 * @author wqtan
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Permission> getPermissionListByGroupId(Long permGroupId,Integer type){
		StringBuffer hsql = new StringBuffer("from Permission p where p.permissionGroupId=?");
		Object[] params = null;
		if(ObjectUtils.isNotEmpty(type)){
			hsql.append(" and p.type=?");
			params = new Object[]{permGroupId,type};
		}else{
			params = new Object[]{permGroupId};
		}
		return this.findList(hsql.toString(), params, 0, null);
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Permission> getRolePermissionList(Long roleId,Integer type){
		List<Permission> result = null;
		//获得指定角色的权限id列表
		String rolePermHsql = "select permissionId from RolePermission where roleId=?";
		List<Long> rolePermList = this.findIntegerList(rolePermHsql, new Object[]{roleId});
		String rolePermIds = stringUtil.parseListToString(rolePermList);
		//获得指定权限类型的权限列表
		if(null != rolePermIds && !rolePermIds.trim().equals("")){
			String hsql = "from Permission where id in ("+rolePermIds+")";
			Object[] params = null;
			if(ObjectUtils.isNotEmpty(type) && type > 0){
				hsql +=  "and type=?";
				params = new Object[]{type};
			}
			result = this.findList(hsql, params, 0, null);
		}
		return result;
	}
	
	/*@SuppressWarnings({ "unchecked" })
	public List<Permission> getPermissionList(List<Long> roleIdList,Integer type,String permUri){
		List<Permission> result = null;
		if(null == roleIdList || roleIdList.isEmpty()){
			return result;
		}else{
			//获得指定角色的权限id列表
			String rolePermHsql = "select permissionId from RolePermission where roleId in ("+
				stringUtil.parseListToString(roleIdList)+")";
			List<Long> rolePermList = this.findIntegerList(rolePermHsql, null);
			String rolePermIds = stringUtil.parseListToString(rolePermList);
			//获得指定权限类型的权限列表
			if(null != rolePermIds && !rolePermIds.trim().equals("")){
				String hsql = "from Permission where id in ("+rolePermIds+")";
				List<Object> params = new LinkedList<Object>();
				if(ObjectUtils.isNotEmpty(type) && type > 0){
					hsql +=  "and type=?";
					params.add(type);
				}
				if(null != permUri && !permUri.trim().equals("")){
					hsql +=  "and url=?";
					params.add(permUri);
				}
				result = this.findList(hsql, params.toArray(),1 , null);
			}
		}
		return result;
	}*/
	@SuppressWarnings({ "unchecked" })
	public List<Permission> getPermissionList(List<String> roleCodeList,Integer type,String permUri){
		List<Permission> result = null;
		if(null == roleCodeList || roleCodeList.isEmpty()){
			return result;
		}else{
			//获得指定角色的权限id列表
			String rolePermHsql = "select permissionId from RolePermission where roleCode in ("+
				stringUtil.parseListToString(roleCodeList)+")";
			List<Long> rolePermList = this.findIntegerList(rolePermHsql, null);
			String rolePermIds = stringUtil.parseListToString(rolePermList);
			//获得指定权限类型的权限列表
			if(null != rolePermIds && !rolePermIds.trim().equals("")){
				String hsql = "from Permission where id in ("+rolePermIds+")";
				List<Object> params = new LinkedList<Object>();
				if(ObjectUtils.isNotEmpty(type) && type > 0){
					hsql +=  " and type=?";
					params.add(type);
				}
				if(null != permUri && !permUri.trim().equals("")){
					hsql +=  " and url=?";
					params.add(permUri);
				}
				result = this.findList(hsql, params.toArray(),1 , null);
			}
		}
		return result;
	}
	/**
	 * 获得integer类型的list
	 * @author wqtan
	 */
	@SuppressWarnings ({"unchecked"})
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
	
	/**
	 * 根据code获得指定权限
	 * @author wqtan
	 */
	public Permission getPermissionByCode(String code){
		if(null != code && !code.trim().equals("")){
			return this.findByProperty("code", code);
		}else{
			return null;
		}
	}
	/**
	 * 根据productCode获得指定权限
	 * @author wgf
	 */
	public List<Permission> getPermissionByProductCode(String productCode){
		if(null != productCode && !productCode.trim().equals("")){
			String hql = "from Permission where productCode = ?";
			return this.findList(hql, new Object[]{productCode});
		}else{
			return null;
		}
	}
	
	/**
	 * 根据 链接地址 获得指定的权限
	 * @author bxmen
	 */
	public Permission getPermissionByURL(String url){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("url", url);
		return this.findByProperties(params);
	}
	
	
	public Permission getPermissionByURL(String url, Integer type){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("url", url);
		params.put("type", type);
		return this.findByProperties(params);
	}
}
