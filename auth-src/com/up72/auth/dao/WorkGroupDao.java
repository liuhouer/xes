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

import com.up72.auth.model.WorkGroup;
import com.up72.auth.vo.query.WorkGroupQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;

/**
 * 权限工作组（部门）Hibernate数据处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class WorkGroupDao extends BaseHibernateDao<WorkGroup,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return WorkGroup.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(WorkGroupQuery query) {
        //XsqlBuilder 
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from WorkGroup t where 1=1 "
			  	+ "/~ and t.name = {name} ~/"
			  	+ "/~ and t.addTime = {addTime} ~/"
			  	+ "/~ and t.remark = {remark} ~/"
			  	+ "/~ and t.enabled = {enabled} ~/"
			  	+ "/~ and t.organizationId = {organizationId} ~/"
			  	+ "/~ and t.deptLevel = {deptLevel} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from WorkGroup t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getName())) {
            sql2.append(" and  t.name = :name ");
        }
        if(isNotEmpty(query.getAddTime())) {
            sql2.append(" and  t.addTime = :addTime ");
        }
        if(isNotEmpty(query.getRemark())) {
            sql2.append(" and  t.remark = :remark ");
        }
        if(isNotEmpty(query.getEnabled())) {
            sql2.append(" and  t.enabled = :enabled ");
        }
        if(isNotEmpty(query.getOrganizationId())) {
            sql2.append(" and  t.organizationId = :organizationId ");
        }
        if(isNotEmpty(query.getDeptLevel())) {
            sql2.append(" and  t.deptLevel = :deptLevel ");
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
	
	/**
	 * 根据 organizationId 获取 WorkGroup 列表
	 * @author bxmen
	 */
	public List<WorkGroup> getWorkGroupByOrganizationId(Long organizationId){
		String hql = "from WorkGroup where organizationId=?";
		return this.findList(hql, new Object[]{organizationId}, 0, null);
	}
	
	public List<WorkGroup> getMemberWorkGroupList(Long memberId){
		List<WorkGroup> result = null;
		if(null != memberId && memberId > 0){
			StringBuffer hsql = new StringBuffer("from WorkGroup w where 1=1");
			hsql.append("and w.id in(");
			hsql.append("select wm.workGroupId from WorkGroupMember wm where wm.memberId=?");
			hsql.append(")");
			result = this.find(hsql.toString(), new Object[]{memberId}, 0, null);
		}
		return result;
	}

	public List<WorkGroup> findCategoriesByParentAndSite(long parentId) {
		List<WorkGroup> result = null;
		StringBuffer hql = new StringBuffer("from WorkGroup where 1=1");
		if(parentId != 0){
			hql.append(" and parent = ? ");
			result = super.find(hql.toString(), new Object[]{parentId},0,null);
		}else{
			hql.append(" and (parent = 0 or parent is null) ");
			result = super.find(hql.toString(),null,0,null);
		}
		return result;
	}
}
