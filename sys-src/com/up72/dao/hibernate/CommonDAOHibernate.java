package com.up72.dao.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.up72.page.Pagination;
import com.up72.page.QueryResult;

public class CommonDAOHibernate extends HibernateDaoSupport {

	public static Logger logger = Logger.getLogger(sun.reflect.Reflection
			.getCallerClass(1));

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
	public CommonDAOHibernate() {
	}

	/**
	 * 清除缓存对象
	 */
	public void evict(Object entity) {
		super.getHibernateTemplate().evict(entity);
	}

	/**
	 * 同步数据库
	 */
	public void flush() {
		super.getHibernateTemplate().flush();
	}

	/**
	 * 通过hql批量执行
	 */
	@SuppressWarnings("deprecation")
	public int executeHql(final String hql) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						int result = session.createQuery(hql).executeUpdate();
						return result;
					}
				});
	}

	/**
	 * 通过hql批量执行
	 */
	@SuppressWarnings("deprecation")
	public int executeHql(final String hql, final Object[] params) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						if (params != null && params.length > 0) {
							for (int i = 0; i < params.length; i++)
								query.setParameter(i, params[i]);
						}

						int result = query.executeUpdate();
						return result;
					}
				});
	}

	/**
	 * 通过hql批量执行
	 */
	@SuppressWarnings("deprecation")
	public int executeHql(final String hql, final Object param) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						query.setParameter(0, param);
						int result = query.executeUpdate();
						return result;
					}
				});
	}

	/**
	 * 批处理sql语句
	 */
	@SuppressWarnings("deprecation")
	public int executeSql(final String sql) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Connection conn = null;
						PreparedStatement stmt = null;
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
	 * 保存并更新对象
	 */
	public void saveOrUpdate(Object object) {
		getHibernateTemplate().saveOrUpdate(object);
		getHibernateTemplate().flush();
	}

	/**
	 * 更新对象
	 * 
	 * @param object
	 */
	public void update(Object object) {
		getHibernateTemplate().update(object);

	}

	/**
	 * 合并不同session中同一对象并更新对象
	 * 
	 * @param object
	 */
	public void merge(Object object) {
		getHibernateTemplate().merge(object);

	}

	/**
	 * 保存对象
	 * 
	 * @param object
	 */
	public void save(Object object) {
		getHibernateTemplate().save(object);
		// getHibernateTemplate().flush();
	}

	/**
	 * 得到单个对象
	 * 
	 * @param clz
	 * @param id
	 * @return
	 */
	public Object get(Class clz, Serializable id) {
		Object object = getHibernateTemplate().get(clz, id);
		return object;
	}

	/**
	 * 得到单个对象
	 * 
	 * @param clz
	 * @param id
	 * @return
	 */
	public Object load(Class clz, Serializable id) {
		Object object = getHibernateTemplate().load(clz, id);
		if (object == null) {
			throw new ObjectRetrievalFailureException(clz, id);
		}
		return object;
	}

	/**
	 * 删除对象
	 * 
	 * @param object
	 */
	public void delete(Object object) {
		getHibernateTemplate().delete(object);
	}

	/**
	 * 删除对象
	 * 
	 * @param object
	 */
	public void delete(Class clz, Serializable id) {
		Object object = getHibernateTemplate().get(clz, id);
		if (object != null) {
			getHibernateTemplate().delete(object);
		}
	}

	/**
	 * 批量删除对象,返回删除个数
	 * 
	 * @param clz
	 * @param ids
	 * @return
	 */
	public Integer delete(Class clz, Long[] ids) {
		Integer result = 0;
		for (Long id : ids) {
			Object object = getHibernateTemplate().get(clz, id);
			if (object != null) {
				getHibernateTemplate().delete(object);
				result += 1;
			}
		}
		return result;
	}

	/**
	 * 得到全部对象集合
	 */
	@SuppressWarnings("unchecked")
	public List loadAll(Class clz) {
		return this.getHibernateTemplate().loadAll(clz);
	}

	/** 函数聚合查询begin* */

	@SuppressWarnings( { "unchecked", "deprecation" })
	public Long findcount(final String hql, final Object[] params) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql).setCacheable(true)
						.setCacheRegion("frontpages");
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++)
						query.setParameter(i, params[i]);
				}
				return query.list().get(0);
			}
		});
	}

	@SuppressWarnings( { "unchecked", "deprecation" })
	public Long findcount(final String hql, final HashMap<String, Object> params) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer hqlBuffer = new StringBuffer();
				hqlBuffer.append(hql);
				List<Object> values = new ArrayList<Object>();
				if (params != null) {
					Iterator<String> it = params.keySet().iterator();
					while (it.hasNext()) {
						String field = it.next();
						Object value = params.get(field);
						if (value == null) {
							hqlBuffer.append(" and " + field);
						} else {
							hqlBuffer.append(" and " + field + "=?");
							values.add(value);
						}
					}
				}
				return findcount(hqlBuffer.toString(), values.toArray());
			}
		});
	}

	/**
	 * 取得资源总数
	 * 
	 * @param param
	 *            查询参数 (如： sum(price)或 count(id))
	 * @param params
	 *            Map 查询参数，属性=值对
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public Long findcount(Class clz, String param,
			HashMap<String, Object> params) {
		StringBuffer hqlBuffer = new StringBuffer();
		if (param != null && !"".equals(param)) {
			hqlBuffer.append("select " + param + " from " + clz.getSimpleName()
					+ " where 1=1");
		} else {
			hqlBuffer.append("select count(id) from " + clz.getSimpleName()
					+ " where 1=1");
		}

		List<Object> values = new ArrayList<Object>();
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String field = it.next();
				Object value = params.get(field);
				if (value == null) {
					hqlBuffer.append(" and " + field);
				} else {
					hqlBuffer.append(" and " + field + "=?");
					values.add(value);
				}
			}
		}
		return findcount(hqlBuffer.toString(), values.toArray());
	}

	public Long findcount(final String hql) {
		return findcount(hql, new HashMap<String, Object>());

	}

	/** 函数聚合查询begin* */

	/** 列表查询begin* */
	public List find(String hql) {
		return getHibernateTemplate().find(hql);
	}

	public List find(String hql, Object param) {
		return getHibernateTemplate().find(hql, param);
	}

	public List find(String hql, Object[] params) {
		return getHibernateTemplate().find(hql, params);
	}

	public List findList(final String hql, final Integer dataNum) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql).setFirstResult(0)
						.setMaxResults(dataNum);
				return query.list();
			}
		});
	}

	public List findList(String hql, Object param, Integer dataNum) {
		return findList(hql, new Object[] { param }, dataNum, null, null, null);
	}

	public List findList(String hql, Object param, Integer dataNum, Map orders) {
		return findList(hql, new Object[] { param }, dataNum, orders, null,
				null);
	}

	public List findList(String hql, Object param, Integer dataNum, Map orders,
			Boolean isQueryCache, String cacheRegion) {
		return findList(hql, new Object[] { param }, dataNum, orders,
				isQueryCache, cacheRegion);
	}

	public List findList(String hql, Object[] params, Integer dataNum) {
		return findList(hql, params, dataNum, null, null, null);
	}

	public List findList(String hql, Object[] params, Integer dataNum,
			Map orders) {
		return findList(hql, params, dataNum, orders, null, null);
	}

	public Object findObject(String hql, HashMap<String, Object> params,
			Map<String, String> orderMap) {
		List<Object> list = findList(hql, params, 1, orderMap, null, null);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings( { "unchecked", "deprecation" })
	public List findList(final String hql, final Object[] params,
			final Integer dataNum, final Map orders,
			final Boolean isQueryCache, final String cacheRegion) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				List result = null;
				String resultHql = "";

				StringBuffer stringBuffer = new StringBuffer(hql);
				if (orders != null) {
					Set set = orders.entrySet();
					Iterator iterator = set.iterator();
					Map.Entry entry = null;
					stringBuffer.append(" order by ");
					while (iterator.hasNext()) {
						entry = (Map.Entry) iterator.next();
						stringBuffer.append((String) entry.getKey())
								.append(" ").append((String) entry.getValue())
								.append(" ,");
					}
					resultHql = stringBuffer.substring(0,
							stringBuffer.length() - 1).toString();
				} else {
					// stringBuffer.append(" order by id desc");
					resultHql = stringBuffer.toString();
				}
				Query query = session.createQuery(resultHql).setFirstResult(0);
				if (isQueryCache != null) {
					query.setCacheable(isQueryCache)
							.setCacheRegion(cacheRegion);
				}
				if (params != null) {
					for (int i = 0; i < params.length; i++)
						query.setParameter(i, params[i]);
				}
				if (dataNum != null && dataNum != 0) {
					query.setMaxResults(dataNum);
				}

				result = query.list();

				return result;
			}
		});
	}

	public List findList(String hql, HashMap<String, Object> params) {
		return findList(hql, params, null, null, null, null);
	}

	public List findList(String hql, HashMap<String, Object> params,
			Integer dataNum) {
		return findList(hql, params, dataNum, null, null, null);
	}

	public List findList(String hql, HashMap<String, Object> params,
			Integer dataNum, Map<String, String> orderMap) {
		return findList(hql, params, dataNum, orderMap, null, null);
	}

	/**
	 * 查找到的对象集合
	 * 
	 * @param hql
	 * @param params
	 *            Map 查询参数，属性=值对
	 * @param num
	 *            返回结果个数
	 * @param orderMap
	 *            排序参数 属性=值对
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findList(String hql, HashMap<String, Object> params,
			Integer dataNum, Map<String, String> orderMap,
			Boolean isQueryCache, String cacheRegion) {

		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(hql);

		List<Object> values = new ArrayList<Object>();
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String field = it.next();
				Object value = params.get(field);
				if (value == null) {
					hqlBuffer.append(" and " + field);
				} else {
					hqlBuffer.append(" and " + field + "=?");
					values.add(value);
				}
			}
		}
		return findList(hqlBuffer.toString(), values.toArray(), dataNum,
				orderMap, isQueryCache, cacheRegion);
	}

	/** 列表查询end* */

	/** 分页查询begin* */

	/** Object传参begin* */
	public QueryResult findPage(String hql, Object param, Pagination pagination) {
		return findPage(hql, new Object[] { param }, pagination, null, null,
				null);
	}

	public QueryResult findPage(String hql, Object param,
			Pagination pagination, Map orders) {
		return findPage(hql, new Object[] { param }, pagination, orders, null,
				null);
	}

	public QueryResult findPage(String hql, Object param,
			Pagination pagination, Map orders, Boolean isQueryCache,
			String cacheRegion) {
		return findPage(hql, new Object[] { param }, pagination, orders,
				isQueryCache, cacheRegion);
	}

	/** Object[]传参begin* */
	public QueryResult findPage(String hql, Object[] params,
			Pagination pagination) {
		return findPage(hql, params, pagination, null, null, null);
	}

	public QueryResult findPage(String hql, Object[] params,
			Pagination pagination, Map orders) {
		return findPage(hql, params, pagination, orders, null, null);
	}

	/**
	 * 通过传递参数进行分页查询
	 * 
	 * @param hql
	 * @param params
	 * @param pagination
	 * @param orders
	 * @param isQueryCache
	 * @param cacheRegion
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public QueryResult findPage(final String hql, final Object[] params,
			final Pagination pagination, final Map orders,
			final Boolean isQueryCache, final String cacheRegion) {
		return (QueryResult) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						List result = new ArrayList();
						String hqlCount = "select count(*) "
								+ hql.substring(hql.indexOf("from"));

						String resultHql = "";
						// xxh 含distinct hql语句的统计处理begin
						String cs = null;
						if (hql.indexOf("select") >= 0
								&& hql.indexOf("from") > 0
								&& hql.indexOf("from") > hql.indexOf("select"))
							cs = hql.substring(hql.indexOf("select")
									+ "select".length(), hql.indexOf("from"));
						if (cs != null) {
							String c = cs.trim();
							hqlCount = "select count(" + c + ") "
									+ hql.substring(hql.indexOf("from"));
						} else {
							hqlCount = "select count(*) "
									+ hql.substring(hql.indexOf("from"));
						}
						// xxh 含distinct hql语句的统计处理end

						StringBuffer stringBuffer = new StringBuffer(hql);
						if (orders != null) {
							Set set = orders.entrySet();
							Iterator iterator = set.iterator();
							Map.Entry entry = null;
							stringBuffer.append(" order by ");
							while (iterator.hasNext()) {
								entry = (Map.Entry) iterator.next();
								stringBuffer.append((String) entry.getKey())
										.append(" ").append(
												(String) entry.getValue())
										.append(" ,");
							}
							resultHql = stringBuffer.substring(0,
									stringBuffer.length() - 1).toString();
						} else {
							// stringBuffer.append(" order by id desc");
							resultHql = stringBuffer.toString();
						}
						Query query = session.createQuery(resultHql);
						if (isQueryCache != null) {
							query = query.setCacheable(isQueryCache)
									.setCacheRegion(cacheRegion);
						}
						if (params != null) {
							for (int i = 0; i < params.length; i++)
								query.setParameter(i, params[i]);
						}
						query.setFirstResult(pagination.getStart());
						query.setMaxResults(pagination.getRange());

						// 当数据集合为0时从数据库提取，否则从外部提取
						if (pagination.getTotalRecord() == 0) {
							Long totalRecord = (Long) findcount(hqlCount,
									params);
							pagination.setTotalRecord(totalRecord.intValue());
						}

						return new QueryResult(pagination, query.list());
					}
				});
	}

	public QueryResult findPage(String hql, HashMap<String, Object> params,
			Pagination pagination) {
		return findPage(hql, params, pagination, null, null, null);
	}

	public QueryResult findPage(String hql, HashMap<String, Object> params,
			Pagination pagination, Map<String, String> orders) {
		return findPage(hql, params, pagination, orders, null, null);
	}

	/**
	 * 查找到的分页对象集合
	 * 
	 * @param hql
	 * @param params
	 *            Map 查询参数，属性=值对
	 * @param orderMap
	 * @param pagination
	 *            分页对象
	 * @return
	 */
	public QueryResult findPage(String hql, HashMap<String, Object> params,
			Pagination pagination, Map<String, String> orderMap,
			Boolean isQueryCache, String cacheRegion) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(hql);

		List<Object> values = new ArrayList<Object>();
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String field = it.next();
				Object value = params.get(field);
				if (value == null) {
					hqlBuffer.append(" and " + field);
				} else {
					hqlBuffer.append(" and " + field + "=?");
					values.add(value);
				}
			}
		}
		QueryResult result = findPage(hqlBuffer.toString(), values.toArray(),
				pagination, orderMap, isQueryCache, cacheRegion);
		return result;
	}

	/** 分页查询end * */

	public int executeHsql(final String hql) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						int result = session.createQuery(hql).executeUpdate();
						return result;
					}
				});
	}

	@SuppressWarnings( { "unchecked", "deprecation" })
	public Long findCounts(final String hql, final Object[] params) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql).setCacheable(true)
						.setCacheRegion("frontpages");
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++)
						query.setParameter(i, params[i]);
				}
				if (query.list() == null || query.list().size() == 0) {
					return 0L;
				} else {
					return query.list().get(0);
				}

			}
		});
	}

	public Long findCounts(final String hql) {
		return findCounts(hql, null);

	}
}
