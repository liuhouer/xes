/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.dao;

import static com.up72.common.CommonUtils.stringUtil;
import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.up72.auth.model.Role;
import com.up72.auth.vo.query.RoleQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;

/**
 * 权限角色表Hibernate数据处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class RoleDao extends BaseHibernateDao<Role,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Role.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(RoleQuery query) {
        //XsqlBuilder 
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
//		String sql = "select t from Role t where 1=1 "
//
//				+ "/~ and t.name = {name} ~/"
//			  	+ "/~ and t.workGroupId = {workGroupId} ~/"
//			  	+ "/~ and t.enabled = {enabled} ~/"
//
//			  	+ "/~ and t.organizationId = {organizationId} ~/"
//			  	+ "/~ and t.remark = {remark} ~/"
//			  	+ "/~ and t.createUserId = {createUserId} ~/"
//				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from Role t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getName())) {
            sql2.append(" and  t.name like '%" + query.getName() + "%' ");
        }
        if(isNotEmpty(query.getWorkGroupId())) {
            sql2.append(" and  t.workGroupId = :workGroupId ");
        }
        if(isNotEmpty(query.getOrganizationId())) {
            sql2.append(" and  t.organizationId = :organizationId ");
        }
        if(isNotEmpty(query.getProductCode())) {
            sql2.append(" and  t.productCode= :productCode ");
        }else{
        	sql2.append(" and  (t.productCode='' or t.productCode is null)");
        }
        if(isNotEmpty(query.getEnabled())) {
            sql2.append(" and  t.enabled = :enabled ");
        }
        if(isNotEmpty(query.getCreateUserId())) {
            sql2.append(" and  t.createUserId = :createUserId ");
        }
	
		return pageQuery(sql2.toString(),query);
	}
	
	/**
	 * 根据部门ID获得该部门下的角色
	 * @author bxmen
	 */
	public List<Role> getRoleByWorkGroupId(Long workGroupId){
		String hql = "from Role where 1=1 and workGroupId=?";
		List<Role> roleList = findList(hql, new Object[]{workGroupId}, 0, null);
		return roleList;
	} 
	/**
	 * 根据机构ID获得该机构下的角色
	 * 
	 */
	public List<Role> getRoleByOrganizationId(Long organizationId){
		String hql = "from Role where 1=1 and organizationId=?";
		List<Role> roleList = findList(hql, new Object[]{organizationId}, 0, null);
		roleList = roleList != null&&roleList.size()>0?roleList:null;
		return roleList;
	} 
	/**
	 * 获得该角色下的所有权限ID列表
	 * @author wqtan
	 */
	public List<Integer> getRolePermissionId(final Long roleId,final Integer type){
		
		String rpHsql = "select permissionId from RolePermission where roleId=?";
		List<Integer> rpIds = this.findIntegerList(rpHsql, new Object[]{roleId}); 
		String stringIds = stringUtil.parseListToString(rpIds);
		
		final StringBuffer hsql = new StringBuffer("select id from Permission where id in (");
		hsql.append(stringIds);
		hsql.append(")");
		Object[] params = null;
		if(ObjectUtils.isNotEmpty(type)){
			hsql.append(" and type=?");
			params = new Object[]{type};
		}
		
		return this.findIntegerList(hsql.toString(), params);
	}
	
	@SuppressWarnings ({"unchecked"})
	public List<Integer> findIntegerList(final String hsql,final Object[] params){
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Integer>>(){
			public List<Integer> doInHibernate(Session session)
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
	 * @author bxmen
	 * @param hsql
	 * @param params
	 * @return 
	 * @summary
	 */
	@SuppressWarnings ({"unchecked"})
	public List<Long> findLongList(final String hsql,final Object[] params){
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
	 * @author bxmen
	 * @param hsql
	 * @param params
	 * @return 
	 * @summary
	 */
	@SuppressWarnings ({"unchecked"})
	public List<String> findStringList(final String hsql,final Object[] params){
		return this.getHibernateTemplate().execute(new HibernateCallback<List<String>>(){
			public List<String> doInHibernate(Session session)
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
	 * 获得指定用户的角色ID列表
	 * @author bxmen
	 * @return 角色ID列表
	 */
	public List<String> getUserRoleCodes(Long memberId){
		String memberRoleHql = "select roleCode from MemberRole where memberId = ?";
		List<Long> memberRoleList = this.findLongList(memberRoleHql, new Object[]{memberId});
		String roleCodes = stringUtil.parseListToString(memberRoleList);
		
		String hsql = "select roleCode from Role where roleCode in(" + roleCodes + ") and enabled=1";
		List<String> result = this.findStringList(hsql, null);
		return result;
	}
	/**
	 * 获得指定用户的角色列表
	 * @author wqtan
	 */
	public List<Role> getUserRoles(Long userId){
		List<Role> result = null;
		if(null != userId && userId > 0){
			StringBuffer hsql = new StringBuffer("from Role r where 1=1 and r.enabled=1");
			hsql.append(" and r.id in (");
			hsql.append("select mr.roleId from MemberRole mr where mr.memberId=?");
			hsql.append(")");
			result = this.find(hsql.toString(), new Object[]{userId}, 0, null);
		}
		return result;
	}
	
	/**
	 * 获得指定用户的角色ID列表
	 * @author bxmen
	 * @return 角色ID列表
	 */
	public List<Long> getUserRoleIds(Long userId){
		String memberRoleHql = "select roleId from MemberRole where memberId=?";
		List<Long> memberRoleList = this.findLongList(memberRoleHql, new Object[]{userId});
		String roleIds = stringUtil.parseListToString(memberRoleList);
		
		String hsql = "select id from Role where id in(" + roleIds + ") and enabled=1";
		List<Long> result = this.findLongList(hsql, null);
		return result;
	}
	
	public List<Role> getMemberRoleList(Long memberId){
		List<Role> result = null;
		if(null != memberId && memberId > 0){
			StringBuffer hsql = new StringBuffer("from Role r where 1=1");
			hsql.append(" and r.id in(");
			hsql.append("select mr.roleId from MemberRole mr where mr.memberId=?");
			hsql.append(")");
			result = this.find(hsql.toString(), new Object[]{memberId}, 0, null);
		}
		return result;
	}
}
