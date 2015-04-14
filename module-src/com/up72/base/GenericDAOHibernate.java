package com.up72.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.up72.page.Pagination;
import com.up72.page.QueryResult;

public class GenericDAOHibernate <T> extends HibernateDaoSupport {
		protected Class<T> clz;
	
	protected String parseToSQLStringComma(long[] strArray) {
        if (strArray == null || strArray.length == 0)
            return "";
        String myStr = "";
        for (int i = 0; i < strArray.length - 1; i++) {
            myStr += "'" + strArray[i] + "',";
        }
        myStr += "'" + strArray[strArray.length - 1] + "'";
        return myStr;
    }
	
	/**
	 * Construct method
	 */
	@SuppressWarnings("unchecked")
	public GenericDAOHibernate() {
		clz = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	 /* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#evict(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#evict(java.lang.Object)
	 */
	public void evict(Object entity){
			super.getHibernateTemplate().evict(entity);
	}
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#flush()
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#flush()
	 */
	public void flush(){
			super.getHibernateTemplate().flush();
	}

	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#executeHql(java.lang.String)
	 */
   /* (non-Javadoc)
 * @see com.up72.dao.IG#executeHql(java.lang.String)
 */
@SuppressWarnings("deprecation")
	public int executeHql(final String hql){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				int result = session.createQuery(hql).executeUpdate();
				return result;
			}
		});
	}
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#executeHql(java.lang.String, java.lang.Object[])
	 */
   /* (non-Javadoc)
 * @see com.up72.dao.IG#executeHql(java.lang.String, java.lang.Object[])
 */
@SuppressWarnings("deprecation")
	public int executeHql(final String hql,final Object[] params){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				if(params != null && params.length > 0){
					for(int i=0 ; i< params.length ;i++ )
						query.setParameter(i,params[i]);
				}
				
				int result = query.executeUpdate();
				return result;
			}
		});
	}
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#executeHql(java.lang.String, java.lang.Object)
	 */
   /* (non-Javadoc)
 * @see com.up72.dao.IG#executeHql(java.lang.String, java.lang.Object)
 */
@SuppressWarnings("deprecation")
	public int executeHql(final String hql,final Object param){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				query.setParameter(0,param);
				int result = query.executeUpdate();
				return result;
			}
		});
	}
   /* (non-Javadoc)
 * @see com.up72.dao.hibernate.IDao#executeSql(java.lang.String)
 */

	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#saveOrUpdate(java.lang.Object)
	 */
   /* (non-Javadoc)
 * @see com.up72.dao.IG#saveOrUpdate(java.lang.Object)
 */
	public void saveOrUpdate(Object object) {
		getHibernateTemplate().saveOrUpdate(object);
	    getHibernateTemplate().flush();  	
    }	
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#update(java.lang.Object)
	 */
   /* (non-Javadoc)
 * @see com.up72.dao.IG#update(java.lang.Object)
 */
	public void update(Object object) {
		getHibernateTemplate().update(object);
	}	    
   
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#merge(java.lang.Object)
	 */
   /* (non-Javadoc)
 * @see com.up72.dao.IG#merge(java.lang.Object)
 */
	public void merge(Object object) {
		getHibernateTemplate().merge(object);
   	
	}
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#save(java.lang.Object)
	 */
   /* (non-Javadoc)
 * @see com.up72.dao.IG#save(T)
 */
	public void save(T object) {
		getHibernateTemplate().save(object);
   }
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#get(java.io.Serializable)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#get(java.io.Serializable)
	 */
	public T get(Serializable id) {
		T object = (T)getHibernateTemplate().get(clz, id);
		return object;
	}
	
	public List<T> loadAll() {
		List<T> list = getHibernateTemplate().loadAll(clz);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#load(java.io.Serializable)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#load(java.io.Serializable)
	 */
	public Object load(Serializable id) {
		Object object = getHibernateTemplate().load(clz, id);
		if (object == null) {
           throw new ObjectRetrievalFailureException(clz, id);
       }
   	return object;
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#delete(java.lang.Object)
	 */
   /* (non-Javadoc)
 * @see com.up72.dao.IG#delete(java.lang.Object)
 */
public void delete(Object object) {
   	getHibernateTemplate().delete(object);
   }
   
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#delete(java.io.Serializable)
	 */
   /* (non-Javadoc)
 * @see com.up72.dao.IG#delete(java.io.Serializable)
 */
public void delete(Serializable id) {
   	Object object = getHibernateTemplate().get(clz, id);
   	if (object != null) {
       	getHibernateTemplate().delete(object);
       }
   }
   
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#delete(java.lang.Long[])
	 */
   /* (non-Javadoc)
 * @see com.up72.dao.IG#delete(java.lang.Long[])
 */
public Long delete(Long[] ids) {
	   long result = 0;
		for(Long id : ids){
		   	Object object = getHibernateTemplate().get(clz, id);
		   	if (object != null) {
		       	getHibernateTemplate().delete(object);
		       	result += 1;
		       }
		}
   	return result;
   }

	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#loadAll(java.lang.Class)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#loadAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public List loadAll(Class clz) { 
		return this.getHibernateTemplate().loadAll(clz);
	}
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#find(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#find(java.lang.String)
	 */
	public List<T> find(String hql) {
				return getHibernateTemplate().find(hql);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#find(java.lang.String, java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#find(java.lang.String, java.lang.Object)
	 */
	public List<T> find(String hql,Object param) {
		return getHibernateTemplate().find(hql,param);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#find(java.lang.String, java.lang.Object[])
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#find(java.lang.String, java.lang.Object[])
	 */
	public List<T> find(String hql,Object[] params) {
		return getHibernateTemplate().find(hql, params);
	}
	

	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findList(java.lang.String, java.lang.Integer)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findList(java.lang.String, java.lang.Integer)
	 */
	public List<T> findList(final String hql,final Integer dataNum) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql).setFirstResult(0).setMaxResults(dataNum);
				return query.list();
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findList(java.lang.String, java.lang.Object, java.lang.Integer)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findList(java.lang.String, java.lang.Object, java.lang.Integer)
	 */
	public List<T> findList(String hql,Object param, Integer dataNum) {
		return findList(hql, new Object[]{param}, dataNum, null, null, null);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findList(java.lang.String, java.lang.Object, java.lang.Integer, java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findList(java.lang.String, java.lang.Object, java.lang.Integer, java.util.Map)
	 */
	public List<T> findList(String hql,Object param, Integer dataNum, Map orders) {
		return findList(hql, new Object[]{param}, dataNum, orders, null, null);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findList(java.lang.String, java.lang.Object, java.lang.Integer, java.util.Map, java.lang.Boolean, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findList(java.lang.String, java.lang.Object, java.lang.Integer, java.util.Map, java.lang.Boolean, java.lang.String)
	 */
	public List<T> findList(String hql, Object param, Integer dataNum,
			Map orders, Boolean isQueryCache, String cacheRegion) {
		return findList(hql, new Object[]{param}, dataNum, orders, isQueryCache, cacheRegion);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findList(java.lang.String, java.lang.Object[], java.lang.Integer)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findList(java.lang.String, java.lang.Object[], java.lang.Integer)
	 */
	public List<T> findList(String hql, Object[] params, Integer dataNum){
		return findList(hql, params, dataNum, null, null, null);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findList(java.lang.String, java.lang.Object[], java.lang.Integer, java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findList(java.lang.String, java.lang.Object[], java.lang.Integer, java.util.Map)
	 */
	public List<T> findList(String hql, Object[] params, Integer dataNum, Map orders){
		return findList(hql, params, dataNum, orders, null, null);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findList(java.lang.String, java.lang.Object[], java.lang.Integer, java.util.Map, java.lang.Boolean, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findList(java.lang.String, java.lang.Object[], java.lang.Integer, java.util.Map, java.lang.Boolean, java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> findList(final String hql, final Object[] params, final Integer dataNum, final Map orders, 
			final Boolean isQueryCache, final String cacheRegion) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
		List result = null;
		String resultHql = "";
		
		StringBuffer stringBuffer = new StringBuffer(hql);
		if(orders != null && !orders.isEmpty()){
			Set set = orders.entrySet();
	        Iterator iterator = set.iterator();
	        Map.Entry entry = null;
	        stringBuffer.append(" order by ");
	        while (iterator.hasNext()) {
	            entry = (Map.Entry) iterator.next();
	            stringBuffer.append((String)entry.getKey()).append(" ").append((String)entry.getValue()).append(" ,");
	        }
	        resultHql = stringBuffer.substring(0, stringBuffer.length()-1).toString();
		}else{
//			stringBuffer.append(" order by id desc");
			resultHql = stringBuffer.toString();
		}
		Query query = session.createQuery(resultHql).setFirstResult(0);
		if(isQueryCache != null){
			query.setCacheable(isQueryCache).setCacheRegion(cacheRegion);
		}
		if(params != null && params.length > 0){
			for(int i=0 ; i< params.length ;i++ )
				query.setParameter(i,params[i]);
		}
		if( dataNum != null && dataNum != 0){
			query.setMaxResults(dataNum);
		}

		result = query.list();

		return result;
		}
			});
		}	

	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findList(java.lang.String, java.util.HashMap)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findList(java.lang.String, java.util.HashMap)
	 */
	public List<T> findList(String hql, HashMap<String, Object> params)  {
		return findList(hql, params, null, null, null, null);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findList(java.lang.String, java.util.HashMap, java.lang.Integer)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findList(java.lang.String, java.util.HashMap, java.lang.Integer)
	 */
	public List<T> findList(String hql, HashMap<String, Object> params , Integer dataNum)  {
		return findList(hql, params, dataNum, null, null, null);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findList(java.lang.String, java.util.HashMap, java.lang.Integer, java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findList(java.lang.String, java.util.HashMap, java.lang.Integer, java.util.Map)
	 */
	public List<T> findList(String hql, HashMap<String, Object> params , Integer dataNum , Map<String,String> orderMap)  {
		return findList(hql, params, dataNum, orderMap,null, null);
	}
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findList(java.lang.String, java.util.HashMap, java.lang.Integer, java.util.Map, java.lang.Boolean, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findList(java.lang.String, java.util.HashMap, java.lang.Integer, java.util.Map, java.lang.Boolean, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findList(String hql, HashMap<String, Object> params , Integer dataNum , Map<String,String> orderMap,
			Boolean isQueryCache, String cacheRegion)  {
		
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
		return findList(hqlBuffer.toString(), values.toArray(), dataNum, orderMap , isQueryCache , cacheRegion);
	}
	

	public List<T> findList(HashMap<String, Object> params, int num, TreeMap<String ,String> orderMap){
		String hql = "from " + clz.getSimpleName() + " where 1 = 1";
		return findList(hql, params, num, orderMap);
	}
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findcount(java.lang.String, java.lang.Object[])
	 */
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findcount(java.lang.String, java.lang.Object[])
	 */
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Long findcount(final String hql,final HashMap<String, Object> params) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				StringBuffer hqlBuffer = new StringBuffer();
				hqlBuffer.append(hql);
				List<Object> values = new ArrayList<Object>();
				if(params != null && !params.isEmpty())
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
	
	@SuppressWarnings({ "unchecked", "deprecation" })
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
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findcount(java.lang.Class, java.lang.String, java.util.HashMap)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findcount(java.lang.Class, java.lang.String, java.util.HashMap)
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Long findcount(Class clz,String param , HashMap<String, Object> params) {
		StringBuffer hqlBuffer = new StringBuffer();
		if(param !=null && !"".equals(param)){
			hqlBuffer.append("select "+ param +" from " + clz.getSimpleName() + " where 1=1");
		}else{
			hqlBuffer.append("select count(id) from " + clz.getSimpleName() + " where 1=1");
		}
		
		List<Object> values = new ArrayList<Object>();
		if(params != null && !params.isEmpty())
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
		return findcount(hqlBuffer.toString(), values.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findcount(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findcount(java.lang.String)
	 */
	public Long findcount(final String hql) {
		return findcount(hql,new HashMap<String, Object>());

	}
	/** 函数聚合查询begin**/
	
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findPage(java.lang.String, java.lang.Object, com.up72.web.util.Pagination)
	 */
	
	
	
	public QueryResult findPage(HashMap<String, Object> params,Pagination pagination, TreeMap<String ,String> orderMap){
		String hql = "from " + clz.getSimpleName() + " where 1 = 1 ";
		return findPage(hql, params,pagination , orderMap);
	}
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findPage(java.lang.String, java.lang.Object, com.up72.web.util.Pagination)
	 */
	public QueryResult findPage(String hql,Object param,Pagination pagination) { 
		return findPage(hql, new Object[]{param},pagination,null,null,null);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findPage(java.lang.String, java.lang.Object, com.up72.web.util.Pagination, java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findPage(java.lang.String, java.lang.Object, com.up72.web.util.Pagination, java.util.Map)
	 */
	public QueryResult findPage(String hql,Object param,Pagination pagination, Map orders){
		return findPage(hql, new Object[]{param},pagination,orders,null,null);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findPage(java.lang.String, java.lang.Object, com.up72.web.util.Pagination, java.util.Map, java.lang.Boolean, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findPage(java.lang.String, java.lang.Object, com.up72.web.util.Pagination, java.util.Map, java.lang.Boolean, java.lang.String)
	 */
	public QueryResult findPage(String hql,Object param,Pagination pagination, Map orders,Boolean isQueryCache,String cacheRegion){
		return findPage(hql, new Object[]{param},pagination,orders,isQueryCache,cacheRegion);
	}
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findPage(java.lang.String, java.lang.Object[], com.up72.web.util.Pagination)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findPage(java.lang.String, java.lang.Object[], com.up72.web.util.Pagination)
	 */
	public QueryResult findPage(String hql,Object[] params,Pagination pagination) {
		return findPage(hql, params,pagination,null,null,null);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findPage(java.lang.String, java.lang.Object[], com.up72.web.util.Pagination, java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findPage(java.lang.String, java.lang.Object[], com.up72.web.util.Pagination, java.util.Map)
	 */
	public QueryResult findPage(String hql,Object[] params,Pagination pagination,Map orders) {
		return findPage(hql, params,pagination,orders,null,null);
	}


	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findPage(java.lang.String, java.lang.Object[], com.up72.web.util.Pagination, java.util.Map, java.lang.Boolean, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findPage(java.lang.String, java.lang.Object[], com.up72.web.util.Pagination, java.util.Map, java.lang.Boolean, java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public QueryResult findPage(final String hql,final Object[] params,final Pagination pagination,
			final Map orders,final Boolean isQueryCache,final String cacheRegion){
		return (QueryResult) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
		List result = new ArrayList();
		String hqlCount = "select count(*) " + hql.substring(hql.indexOf("from"));
		
		String resultHql = "";
		//xxh 含distinct hql语句的统计处理begin
		String cs = null;
		if(hql.indexOf("select") >= 0 && 
				hql.indexOf("from") > 0 && 
				hql.indexOf("from") > hql.indexOf("select"))
			cs = hql.substring(hql.indexOf("select") +  "select".length(), hql.indexOf("from"));
		if(cs != null)
		{
			String c = cs.trim();
			hqlCount = "select count(" + c + ") " + hql.substring(hql.indexOf("from"));
		}else{
			hqlCount = "select count(*) " + hql.substring(hql.indexOf("from"));
		}
		//xxh 含distinct hql语句的统计处理end

		StringBuffer stringBuffer = new StringBuffer(hql);
		if(orders != null && !orders.isEmpty()){
			Set set = orders.entrySet();
	        Iterator iterator = set.iterator();
	        Map.Entry entry = null;
	        stringBuffer.append(" order by ");
	        while (iterator.hasNext()) {
	            entry = (Map.Entry) iterator.next();
	            stringBuffer.append((String)entry.getKey()).append(" ").append((String)entry.getValue()).append(" ,");
	        }
	        resultHql = stringBuffer.substring(0, stringBuffer.length()-1).toString();
		}else{
//			stringBuffer.append(" order by id desc");
			resultHql = stringBuffer.toString();
		}
		Query query = session.createQuery(resultHql);
		if(isQueryCache != null){
			query= query.setCacheable(isQueryCache).setCacheRegion(cacheRegion);
		}
		if(params != null && params.length > 0){
			for(int i=0 ; i< params.length ;i++ )
				query.setParameter(i,params[i]);
		}
		query.setFirstResult(pagination.getStart());
		query.setMaxResults(pagination.getRange());
		

		//当数据集合为0时从数据库提取，否则从外部提取
		if(pagination.getTotalRecord() == 0){
			Long totalRecord = (Long)findcount(hqlCount,params);
			pagination.setTotalRecord(totalRecord.intValue());
		}

		
		return new QueryResult(pagination,query.list());
			}
		});
	}
	
	
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findPage(java.lang.String, java.util.HashMap, com.up72.web.util.Pagination)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findPage(java.lang.String, java.util.HashMap, com.up72.web.util.Pagination)
	 */
	public QueryResult findPage(String hql,HashMap<String, Object> params,Pagination pagination) {
		return findPage(hql, params,pagination,null,null,null);
	}
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findPage(java.lang.String, java.util.HashMap, com.up72.web.util.Pagination, java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findPage(java.lang.String, java.util.HashMap, com.up72.web.util.Pagination, java.util.Map)
	 */
	public QueryResult findPage(String hql,HashMap<String, Object> params,Pagination pagination,Map<String ,String> orders) {
		return findPage(hql, params,pagination,orders,null,null);
	}
	/* (non-Javadoc)
	 * @see com.up72.dao.hibernate.IDao#findPage(java.lang.String, java.util.HashMap, com.up72.web.util.Pagination, java.util.Map, java.lang.Boolean, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.up72.dao.IG#findPage(java.lang.String, java.util.HashMap, com.up72.web.util.Pagination, java.util.Map, java.lang.Boolean, java.lang.String)
	 */
	public QueryResult findPage(String hql, HashMap<String, Object> params, Pagination pagination, Map<String ,String> orderMap, 
			Boolean isQueryCache,String cacheRegion) {
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
		QueryResult result = findPage(hqlBuffer.toString(), values.toArray(), pagination, orderMap ,isQueryCache ,cacheRegion);
		return result;
	}
	
	/** 分页查询end **/
}
