/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.up72.auth.model.MemberRole;
import com.up72.auth.vo.query.MemberRoleQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;

/**
 * 权限用户与角色多对多关系Hibernate数据处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class MemberRoleDao extends BaseHibernateDao<MemberRole,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return MemberRole.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(MemberRoleQuery query) {
        //XsqlBuilder 
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from MemberRole t where 1=1 "
			  	+ "/~ and t.memberId = {memberId} ~/"
			  	+ "/~ and t.roleId = {roleId} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from MemberRole t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getMemberId())) {
            sql2.append(" and  t.memberId = :memberId ");
        }
        if(isNotEmpty(query.getRoleId())) {
            sql2.append(" and  t.roleId = :roleId ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}
	

	
	/**
	 * 根据用户ID得到MemberRole
	 * @author bxmen
	 */
	public List<MemberRole> getMemberRole(Long userId){
		String hql = "from MemberRole where memberId=?";
		List<MemberRole> list = super.findList(hql, new Object[]{userId}, 0, null);
		return list;
	}
	
	/**
	 * 修改指定用户拥有的角色
	 * @author bxmen
	 */
	public void updateByMemberId(MemberRole memberRole){
		String hql = "update MemberRole set roleId=? where memberId=?";
		executeHsql(hql, new Object[]{memberRole.getRoleId(), memberRole.getMemberId()});
	}
}
