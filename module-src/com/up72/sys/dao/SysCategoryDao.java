/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.sys.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;
import com.up72.sys.model.SysCategory;
import com.up72.sys.vo.query.SysCategoryQuery;

/**
 * Hibernate数据处理
 * 
 * @author sys
 * @version 1.0
 * @since 1.0
 */
@Repository
public class SysCategoryDao extends BaseHibernateDao<SysCategory,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return SysCategory.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(SysCategoryQuery query) {
        StringBuilder sql = new StringBuilder("select t from SysCategory t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getGuid())) {
            sql.append(" and  t.guid = :guid ");
        }
        if(isNotEmpty(query.getCatName())) {
            sql.append("/~ and  t.catName like '%[catName]%' ~/");
        }
        if(isNotEmpty(query.getParentGuid())) {
            sql.append(" and  t.parentGuid = :parentGuid ");
        }
        if(isNotEmpty(query.getConfigSource())) {
            sql.append(" and  t.configSource = :configSource ");
        }
        if(isNotEmpty(query.getConfigId())) {
            sql.append(" and  t.configId = :configId ");
        }
        if(isNotEmpty(query.getContentModelId())) {
            sql.append(" and  t.contentModelId = :contentModelId ");
        }
        if(isNotEmpty(query.getListUrlPath())) {
            sql.append(" and  t.listUrlPath = :listUrlPath ");
        }
        if(isNotEmpty(query.getConfigUrlPath())) {
            sql.append(" and  t.configUrlPath = :configUrlPath ");
        }
        if(isNotEmpty(query.getSortId())) {
            sql.append(" and  t.sortId = :sortId ");
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
	/**
	 * 根据guid获得SysCategory
	 * @author wgf
	 * @param guid
	 */
	public SysCategory getByGuid(String guid){
		SysCategory result=null;
		String hql = "from SysCategory where guid = ?";
		result=this.find(hql, new Object[]{guid});
		return result;
	}
	
	/**
	 * 根据parentGuid 获取SysCategory
	 * @param parentGuid
	 * @return
	 */
	public List<SysCategory> findsysCategoryByParent(String parentGuid)  {
		List<SysCategory> result = null;
		StringBuffer hql = new StringBuffer("from SysCategory where 1=1");
		if(null != parentGuid && !"".equals(parentGuid) && !"source".equals(parentGuid)){
			hql.append(" and parentGuid = ?");
			result = this.findList(hql.toString(), new Object[]{parentGuid});
		}else{
			hql.append(" and (parentGuid = '' or parentGuid is null)");
			result = this.findList(hql.toString(),new Object[]{});
		}
		return result;
	}
	/**
	 * 获得指定分类节点下的树状list
	 * @author wqtan 
	 * update wgf 
	 */
	public List<SysCategory> findCategoriesByTree(String guid){
		List<SysCategory> result = new LinkedList<SysCategory>();
		
		if(null != guid && !"".equals(guid)){
			SysCategory category = this.getByGuid(guid);
			if(null != category){
				result.add(category);
			}
		}
		List<SysCategory> childCats = this.findsysCategoryByParent(guid);
		
		if(null != childCats && !childCats.isEmpty()){
			for(int i=0;i<childCats.size();i++){
				List<SysCategory> temp = findCategoriesByTree(childCats.get(i).getGuid());
				if(null != temp && !temp.isEmpty()){
					result.addAll(temp);
				}
			}
		}
		
		return result;
	}
	@SuppressWarnings("unchecked")
	public Long findcount(final String hql,final HashMap<String, Object> params) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				StringBuffer hqlBuffer = new StringBuffer();
				hqlBuffer.append(hql);
				List<Object> values = new ArrayList<Object>();
				if(params != null)
				{
					Iterator<String> it = params.keySet().iterator();		
					while(it.hasNext())
					{
						String field = it.next();
						Object value = params.get(field);
						if(value == null)
						{
							hqlBuffer.append(" and " + field);
						}
						else
						{
							hqlBuffer.append(" and " + field + "=?");
							values.add(value);
						}
					}
				}
				return findcount(hqlBuffer.toString(),values.toArray());
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public Long findcount(final String hql,final Object[] params) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql).setCacheable(true).setCacheRegion("frontpages");
				if(params != null && params.length > 0){
					for(int i=0 ; i< params.length ;i++ )
						query.setParameter(i,params[i]);
				}
				return (Long)query.list().get(0);
			}
		});
	}
}
