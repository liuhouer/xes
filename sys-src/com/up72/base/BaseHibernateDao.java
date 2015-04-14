package com.up72.base;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;
import static com.up72.framework.util.SqlRemoveUtils.removeFetchKeyword;
import static com.up72.framework.util.SqlRemoveUtils.removeOrders;
import static com.up72.framework.util.SqlRemoveUtils.removeSelect;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javacommon.xsqlbuilder.SafeSqlProcesser;
import javacommon.xsqlbuilder.SafeSqlProcesserFactory;
import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;
import javacommon.xsqlbuilder.safesql.DirectReturnSafeSqlProcesser;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.Dialect;
import org.hibernate.impl.SessionFactoryImpl;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.up72.framework.page.Page;
import com.up72.framework.page.PageRequest;
import com.up72.page.Pagination;
import com.up72.page.QueryResult;
import com.up72.util.StringFormat;

/**
 * @author up72
 */
public abstract class BaseHibernateDao<E,PK extends Serializable> extends HibernateDaoSupport implements EntityDao<E,PK>{
	/**
	 * Logger for subclass
	 */
	protected Log log = LogFactory.getLog(getClass());
	
	public long queryForLong(final String queryString) {
		return queryForLong(queryString,new Object[]{});
	}
	
	public long queryForLong(final String queryString,Object value) {
		return queryForLong(queryString,new Object[]{value});
	}
	
	public long queryForLong(final String queryString,Object[] values) {
		return DataAccessUtils.longResult(getHibernateTemplate().find(queryString, values));
	}
	
	/**
	 * 得到全部数据,但执行分页
	 * @param pageRequest
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Page findAll(final PageRequest pageRequest) {
		return (Page)getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				
				StringBuffer queryString = new StringBuffer(" FROM ").append(getEntityClass().getSimpleName());
				String countQueryString = "SELECT count(*) " + queryString.toString();
				if(StringUtils.hasText(pageRequest.getSortColumns())) {
					queryString.append(" ORDER BY "+pageRequest.getSortColumns());
				}
				
				Query query = session.createQuery(queryString.toString());
				Query countQuery = session.createQuery(countQueryString);
				return PageQueryUtils.executeQueryForPage(pageRequest, query, countQuery);
			}
		});
	}
	
	/**
	 * 取得集合对象
	 * @param hql HQL语句
	 * @param params Map 查询参数，属性=值对
	 * @param dataNum 返回结果个数
	 * @param orders  排序参数 属性=值对
	 * @return 集合对象
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<E> findList(final String hql,final Object[] params,final int dataNum,final Map orders) {
		return (List<E>) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
		List<E> result = new ArrayList();
		String resultSql = "";

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
	        resultSql = stringBuffer.substring(0, stringBuffer.length()-1).toString();
		}else{
			stringBuffer.append(" order by 'id' desc");
			resultSql = stringBuffer.toString();
		}
		Query query= session.createQuery(resultSql);
		if(params != null && params.length > 0){
			for(int i=0 ; i< params.length ;i++ )
				query.setParameter(i,params[i]);
		}
		if( dataNum == 0 ){
			result = query.setFirstResult(0).list();
		}
		else{
			result = query.setFirstResult(0).setMaxResults(dataNum).list();
		}
		return result;
		}});
		}
	
	
	public List<E> findList(final String hql,final Object[] params,final int dataNum){
		return findList(hql, params, dataNum, null);
	}
	
	public List<E> findList(final String hql,final Object[] params){
		return findList(hql, params, 0, null);
	}
	
	/**
	 * 取得集合对象
	 * @param hql HQL语句
	 * @param params Map 查询参数，属性=值对
	 * @param dataNum 返回结果个数
	 * @param orders  排序参数 属性=值对
	 * @return 集合对象
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<E> find(final String hql,final Object[] params,final int dataNum,final Map orders) {
		return (List<E>) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
		List<E> result = new ArrayList();
		String resultSql = "";

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
	        resultSql = stringBuffer.substring(0, stringBuffer.length()-1).toString();
		}else{
			stringBuffer.append(" order by 'id' desc");
			resultSql = stringBuffer.toString();
		}
		Query query= session.createQuery(resultSql);
		if(params != null && params.length > 0){
			for(int i=0 ; i< params.length ;i++ )
				query.setParameter(i,params[i]);
		}
		if( dataNum == 0 ){
			result = query.setFirstResult(0).list();
		}
		else{
			result = query.setFirstResult(0).setMaxResults(dataNum).list();
		}

		return result;
		}});
		}
	
	/**
	 * 获取查询对象
	 * @param hql
	 * @param params Map 查询参数，属性=值对
	 * @return 返回查询对象
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public E find(final String hql,final Object[] params) {
		return (E) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
		List<E> result = new ArrayList();
		
		Query query= session.createQuery(hql);
		if(params != null && params.length > 0){
			for(int i=0 ; i< params.length ;i++ )
				query.setParameter(i,params[i]);
		}
		result = query.setFirstResult(0).list();
		if(isNotEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}

		}});
	}

	
	/**
	 * 取得集合对象
	 * @param sql sql语句
	 * @param params Map 查询参数，属性=值对
	 * @param dataNum 返回结果个数
	 * @param orders  排序参数 属性=值对
	 * @return 集合对象
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Object[]> findListBySQL(final String sql,final Object[] params,final int dataNum,final Map orders) {
		return   (List<Object[]>)getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
		List<E> result = new ArrayList();
		String resultSql = "";

		StringBuffer stringBuffer = new StringBuffer(sql);
		if(orders != null && !orders.isEmpty()){
			Set set = orders.entrySet();
	        Iterator iterator = set.iterator();
	        Map.Entry entry = null;
	        stringBuffer.append(" order by ");
	        while (iterator.hasNext()) {
	            entry = (Map.Entry) iterator.next();
	            stringBuffer.append((String)entry.getKey()).append(" ").append((String)entry.getValue()).append(" ,");
	        }
	        resultSql = stringBuffer.substring(0, stringBuffer.length()-1).toString();
		}else{
			//stringBuffer.append(" order by 'id' desc");
			resultSql = stringBuffer.toString();
		}
		Query query= session.createSQLQuery(resultSql);
		if(params != null && params.length > 0){
			for(int i=0 ; i< params.length ;i++ )
				query.setParameter(i,params[i]);
		}
		if( dataNum == 0 ){
			result = query.setFirstResult(0).list();
		}
		else{
			result = query.setFirstResult(0).setMaxResults(dataNum).list();
		}

		return result;
		}});
		}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Page pageQuery(final String sql,final PageRequest pageRequest) {
		final String countQuery = "select count(*) " + removeSelect(removeFetchKeyword((sql)));
		return pageQuery(sql,countQuery,pageRequest);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public Page pageQuery(final String sql,String countQuery,final PageRequest pageRequest) {
		Map otherFilters = new HashMap(1);
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		
		XsqlBuilder builder = getXsqlBuilder();
		
		//混合使用otherFilters与pageRequest.getFilters()为一个filters使用
		XsqlFilterResult queryXsqlResult = builder.generateHql(sql,pageRequest);
		XsqlFilterResult countQueryXsqlResult = builder.generateHql(countQuery,pageRequest);
		
		return PageQueryUtils.pageQuery(getHibernateTemplate(),pageRequest,queryXsqlResult,countQueryXsqlResult);
	}
	
	protected XsqlBuilder getXsqlBuilder() {
		SessionFactoryImpl sf = (SessionFactoryImpl)(getSessionFactory());
		Dialect dialect = sf.getDialect();
		
		//or SafeSqlProcesserFactory.getMysql();
		SafeSqlProcesser safeSqlProcesser = SafeSqlProcesserFactory.getFromCacheByHibernateDialect(dialect); 
		XsqlBuilder builder = new XsqlBuilder(safeSqlProcesser);
		
		if(builder.getSafeSqlProcesser().getClass() == DirectReturnSafeSqlProcesser.class) {
			System.err.println(BaseHibernateDao.class.getSimpleName()+".getXsqlBuilder(): 故意报错,你未开启Sql安全过滤,单引号等转义字符在拼接sql时需要转义,不然会导致Sql注入攻击的安全问题，请修改源码使用new XsqlBuilder(SafeSqlProcesserFactory.getDataBaseName())开启安全过滤");
		}
		return builder;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	static class PageQueryUtils {
		private static Page pageQuery(HibernateTemplate template,final PageRequest pageRequest, final XsqlFilterResult queryXsqlResult, final XsqlFilterResult countQueryXsqlResult) {
			return (Page)template.execute(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = setQueryParameters(session.createQuery(queryXsqlResult.getXsql()),pageRequest);
					Query countQuery = setQueryParameters(session.createQuery(removeOrders(countQueryXsqlResult.getXsql())),pageRequest);
					
					return executeQueryForPage(pageRequest, query, countQuery);
				}
			});
		}
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		private static Object executeQueryForPage(final PageRequest pageRequest,Query query, Query countQuery) {
			Page page = new Page(pageRequest,((Number)countQuery.uniqueResult()).intValue());
			if(page.getTotalCount() <= 0) {
				page.setResult(new ArrayList(0));
			}else {
				page.setResult(query.setFirstResult(page.getFirstResult()).setMaxResults(page.getPageSize()).list());
			}
			return page;
		}
	
		public static Query setQueryParameters(Query q,Object params) {
			q.setProperties(params);
			return q;
		}
		
		public static Query setQueryParameters(Query q,Map params) {
			q.setProperties(params);
			return q;
		}
	}
	 
	public Serializable save(E entity) {
		return (Long)getHibernateTemplate().save(entity);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<E> findAll() {
		return getHibernateTemplate().loadAll(getEntityClass());
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public E getById(PK id) {
		return (E)getHibernateTemplate().get(getEntityClass(),id);
	}

	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public void delete(Serializable entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public void deleteById(PK id) {
		Object entity = getById(id);
		if(entity != null) {
			getHibernateTemplate().delete(entity);
		}
	}

	public void deleteById(PK id,Boolean ObjectRetrievalFailure) {
		Object entity = getById(id);
		if(ObjectRetrievalFailure){
			if(entity == null) {
				throw new ObjectRetrievalFailureException(getEntityClass(),id);
			}
			getHibernateTemplate().delete(entity);
		}else{
			if(entity != null) {
				getHibernateTemplate().delete(entity);
			}
		}
		
	}
	
	public Integer deleteById(PK[] ids) {
    	Integer result = 0;
    	for(PK id : ids){
    		deleteById(id);
            	result += 1;
            }
    	return result;
	}
	
	public void update(E entity) {
		getHibernateTemplate().update(entity);
	}

	public void saveOrUpdate(E entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void refresh(Object entity) {
		getHibernateTemplate().refresh(entity);
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void evict(Object entity) {
		getHibernateTemplate().evict(entity);
	}

	public void saveAll(Collection<E> entities) {
		for(Iterator<E> it = entities.iterator(); it.hasNext();) {
			save(it.next());
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void deleteAll(Collection entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
    public E findByProperty(final String propertyName, final Object value){
    	
        return (E)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createCriteria(getEntityClass())
					.add(Restrictions.eq(propertyName,value))
					.uniqueResult();
			}
        });
    }

	/**
	 * 查找到的对象集合,并返回第一条记录
	 * @param params  Map 查询参数，属性=值对
	 * @param orderMap 排序参数 属性=值对
	 * @return E 
	 * @author baoxin.men create 2012-09-05 
	 */
	@SuppressWarnings("unchecked")
	public E findByProperties(HashMap<String, Object> params)  {
		StringBuffer hsql = new StringBuffer();
		hsql.append("from "+ getEntityClass().getSimpleName() +" where 1=1");
		
		List<Object> values = new ArrayList<Object>();
		if(params != null && !params.isEmpty()){
			Iterator<String> it = params.keySet().iterator();		
			while(it.hasNext()){
				String field = it.next();
				Object value = params.get(field);
				if(value == null){
					hsql.append(" and " + field);
				}else{
					hsql.append(" and " + field + "=?");
					values.add(value);
				}
			}
		}
		List<E> list = findList(hsql.toString(), values.toArray(), 0, null);
		if(list.size()>0){
			return (E) list.get(0);
		}
		return null;
	}
	
	
    @SuppressWarnings({ "unchecked", "deprecation" })
    public List<E> findAllBy(final String propertyName, final Object value) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createCriteria(getEntityClass())
					.add(Restrictions.eq(propertyName,value))
					.list();
			}
        });
    }

    /**
	 * 判断对象某些属性的值在数据库中是否唯一.
	 *
	 * @param uniquePropertyNames 在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
	 */
	public boolean isUnique(E entity, String uniquePropertyNames) {
		Assert.hasText(uniquePropertyNames);
		Criteria criteria = getSession().createCriteria(getEntityClass()).setProjection(Projections.rowCount());
		String[] nameList = uniquePropertyNames.split(",");
		try {
			// 循环加入唯一列
			for (int i = 0; i < nameList.length; i++) {
				criteria.add(Restrictions.eq(nameList[i], PropertyUtils.getProperty(entity, nameList[i])));
			}

			// 以下代码为了如果是update的情况,排除entity自身.

			String idName = getSessionFactory().getClassMetadata(entity.getClass()).getIdentifierPropertyName();
			if(idName != null) {
				// 取得entity的主键值
				Serializable id =  (Serializable)PropertyUtils.getProperty(entity, idName);
	
				// 如果id!=null,说明对象已存在,该操作为update,加入排除自身的判断
				if (id != null)
					criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
			}
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return ((Number) criteria.uniqueResult()).intValue() == 0;
	}
	
	
	
	/**
	 * 取得资源总数
	 * @param param  查询参数 (如： “sum(price)”或  “count(id))”（默认）
	 * @param params  Map 查询参数，属性=值对
	 * @return
	 * @throws DAOException
	 */
	public Long countByAggregate(String param , HashMap<String, Object> params) {
		StringBuffer hsql = new StringBuffer();
		if(param !=null && !"".equals(param)){
			hsql.append("select "+ param +" from " +  getEntityClass().getSimpleName() + " where 1=1");
		}else{
			hsql.append("select count(id) from " +  getEntityClass().getSimpleName() + " where 1=1");
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
					hsql.append(" and " + field);
				}
				else
				{
					hsql.append(" and " + field + "=?");
					values.add(value);
				}
			}
		}
		return countByAggregate(hsql.toString(), values.toArray());
	}
	
	public Long countByAggregate(final String hql){
		return countByAggregate(hql, new Object[]{});
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Long countByAggregate(final String hql,final Object[] params) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				if(params != null && params.length > 0){
					for(int i=0 ; i< params.length ;i++ )
						query.setParameter(i,params[i]);
				}
				return query.iterate().next();
			}
		});
	}
	
	@SuppressWarnings({ "unchecked" })
    public abstract Class getEntityClass();
	
	
	
	/**
	 * 通过hql查询对象
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public int executeHsql(final String hql){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				int result = session.createQuery(hql).executeUpdate();
				return result;
			}
		});
	}
	
	/**
	 * 通过hql批量执行
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public int executeHsql(final String hql,final Object[] params){
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
	/**
	 * 通过hql批量执行
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public int executeHsql(final String hql,final Object param){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				query.setParameter(0,param);
				int result = query.executeUpdate();
				return result;
			}
		});
	}
	
    /** (non-Javadoc)
	 * 批处理sql语句
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public int executeSql(final String sql){
		return (Integer)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Connection conn = null;
				PreparedStatement stmt =null;
				int result = 0;
				try {
					conn = (Connection) session.connection();
					stmt = conn.prepareStatement(sql);
					result = stmt.executeUpdate();
				} catch (Exception e) {
					logger.error("sql操作失败 : " + e.getMessage());
				} finally {
					try {
						stmt.close();
						conn.close();
					} catch (SQLException e) {
						logger.error("批处理失败 : " + e.getMessage());
					}
					releaseSession(session);
				}
				return result;
			}
		});
	}
	
	/**
	 * 查找到的对象集合
	 * @param params  Map 查询参数，属性=值对
	 * @param num 返回结果个数
	 * @param orderMap 排序参数 属性=值对
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<E> findList(HashMap<String, Object> params , Integer num , TreeMap<String,String> orderMap)  {
		
		StringBuffer hsql = new StringBuffer();
		hsql.append("from "+ getEntityClass().getSimpleName() +" where 1=1");
		
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
					hsql.append(" and " + field);
				}
				else
				{
					hsql.append(" and " + field + "=?");
					values.add(value);
				}
			}
		}
		return findList(hsql.toString(), values.toArray(), num, orderMap);
	}
	
	
	/**
	 * 查找到的分页对象集合
	 * @param params Map 查询参数，属性=值对
	 * @param orderMap
	 * @param pagination 分页对象
	 * @return
	 */
	public QueryResult findPage(HashMap<String, Object> params, TreeMap<String ,String> orderMap ,Pagination pagination) {
		StringBuffer hsql = new StringBuffer();
		hsql.append("from "+ getEntityClass().getSimpleName() +" where 1=1");
		
		StringFormat sf = new StringFormat();
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
					hsql.append(" and " + field);
				}
				else
				{
					hsql.append(" and " + field + "=?");
					sf.add(value);
					values.add(value);
				}
			}
		}
		QueryResult result = findPage(hsql.toString(), values.toArray(), orderMap, pagination);
		sf.setFormatStr(hsql.toString());
		result.setHql(sf.toString());
		return result;
	}
	
	/**
	 * 查找到的分页对象集合
	 * @param hql 	HQL语句
	 * @param params Map 查询参数，属性=值对
	 * @param orders Map 查询参数，属性=值对
	 * @param pagination 分页对象
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public QueryResult findPage(final String hql,final Object[] params,final Map orders,final Pagination pagination) {
		return (QueryResult) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
		List items = new ArrayList();
		String hqlCount = "select count(*) " + hql.substring(hql.indexOf("from"));
		
		//xxh 含distinct hql语句的统计处理开始
		String cs = null;
		if(hql.indexOf("select") >= 0 && 
				hql.indexOf("from") > 0 && 
				hql.indexOf("from") > hql.indexOf("select"))
			cs = hql.substring(hql.indexOf("select") +  "select".length(), hql.indexOf("from"));
		if(cs == null || cs.trim().equals(""))
		{
			hqlCount = "select count(*) " + hql.substring(hql.indexOf("from"));
		}
		else
		{
			String c = cs.trim();
			hqlCount = "select count(" + c + ") " + hql.substring(hql.indexOf("from"));
		}
		//xxh 含distinct hql语句的统计处理结束
		
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
			stringBuffer.append("  order by id desc");
			resultHql = stringBuffer.toString();
		}
		Query query= session.createQuery(resultHql);
		if(params != null && params.length > 0){
			for(int i=0 ; i< params.length ;i++ )
				query.setParameter(i,params[i]);
		}
		query.setFirstResult(pagination.getStart());
		query.setMaxResults(pagination.getRange());
		
		items = query.list();
		
		//当数据集合为0时从数据库提取，否则从外部提取
		if(pagination.getTotalRecord() == 0){
			Long totalRecord = (Long)countByAggregate(hqlCount,params);
			pagination.setTotalRecord(totalRecord.intValue());
		}
		
		return new QueryResult(pagination,items);
		}
		});
	}
	
	protected String parseToSQLStringComma(int[] strArray) {
        if (strArray == null || strArray.length == 0)
            return "";
        String myStr = "";
        for (int i = 0; i < strArray.length - 1; i++) {
            myStr += "'" + strArray[i] + "',";
        }
        myStr += "'" + strArray[strArray.length - 1] + "'";
        return myStr;
    }
	
	@SuppressWarnings("unchecked")
	public long uniqueByHql(final String hsql,final Object[] params){
		Object queryResult = this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hsql);
				if(null != params && params.length > 0){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				Object unique = query.uniqueResult();
				return unique;
			}
		});
		
		long result = 0L;
		if(queryResult instanceof Long){
			result = null == queryResult?0L:((Long)queryResult).longValue();
		}else if(queryResult instanceof Integer){
			result = null == queryResult?0L:((Integer)queryResult).intValue();
		}
		return result;
	}
	
	/**
	 * 查找到的对象集合
	 * @param params  Map 查询参数，属性=值对
	 * @param num 返回结果个数
	 * @param orderMap 排序参数 属性=值对
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<E> listQuery(HashMap<String, Object> params , Integer num , TreeMap<String,String> orderMap)  {
		
		StringBuffer hsql = new StringBuffer();
		hsql.append("from "+ getEntityClass().getSimpleName() +" where 1=1");
		
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
					hsql.append(" and " + field);
				}
				else
				{
					hsql.append(" and " + field + "=?");
					values.add(value);
				}
			}
		}
		return find(hsql.toString(), values.toArray(), num, orderMap);
	}
}
