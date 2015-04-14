/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.up72.auth.model.WorkGroupMember;
import com.up72.auth.vo.query.WorkGroupMemberQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;

/**
 * 工作组(部门)与用户多对多关系表Hibernate数据处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class WorkGroupMemberDao extends BaseHibernateDao<WorkGroupMember,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return WorkGroupMember.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(WorkGroupMemberQuery query) {
        //XsqlBuilder 
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from WorkGroupMember t where 1=1 "
			  	+ "/~ and t.workGroupId = {workGroupId} ~/"
			  	+ "/~ and t.memberId = {memberId} ~/"
			  	+ "/~ and t.isManager = {isManager} ~/"
			  	+ "/~ and t.addTime = {addTime} ~/"
			  	+ "/~ and t.status = {status} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from WorkGroupMember t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getWorkGroupId())) {
            sql2.append(" and  t.workGroupId = :workGroupId ");
        }
        if(isNotEmpty(query.getMemberId())) {
            sql2.append(" and  t.memberId = :memberId ");
        }
        if(isNotEmpty(query.getIsManager())) {
            sql2.append(" and  t.isManager = :isManager ");
        }
        if(isNotEmpty(query.getAddTime())) {
            sql2.append(" and  t.addTime = :addTime ");
        }
        if(isNotEmpty(query.getStatus())) {
            sql2.append(" and  t.status = :status ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}
	
	public List<WorkGroupMember> getWorkGroupIdByMemberId(Long memberId){
		String hql = "select t.workGroupId from WorkGroupMember t where 1=1 and t.memberId=?";
		List<WorkGroupMember> list = super.findList(hql, new Object[]{memberId}, 0, null);
		return list;
	}
	/**
	 * 获得指定用户的用户组
	 * @author bxmen
	 */
	public List<WorkGroupMember> getWorkGroupMember(Long userId){
		String hql = "from WorkGroupMember where memberId=?";
		List<WorkGroupMember> list = super.findList(hql, new Object[]{userId}, 0, null);
		return list;
	}
	
	/**
	 * 修改指定用户的用户组
	 * @author bxmen
	 */
	public void updateByMemberId(WorkGroupMember workGroupMember){
		String hql = "update WorkGroupMember set workGroupId=? where memberId=?";
		executeHsql(hql, new Object[]{workGroupMember.getWorkGroupId(), workGroupMember.getMemberId()});
	}
}
