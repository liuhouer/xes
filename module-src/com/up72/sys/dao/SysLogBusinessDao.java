/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.sys.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import org.springframework.stereotype.Repository;

import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;
import com.up72.sys.model.SysLogBusiness;
import com.up72.sys.vo.query.SysLogBusinessQuery;

/**
 * 业务日志表Hibernate数据处理
 * 
 * @author iscs
 * @version 1.0
 * @since 1.0
 */
@Repository
public class SysLogBusinessDao extends
		BaseHibernateDao<SysLogBusiness, java.lang.Long> {

	@SuppressWarnings( { "unchecked" })
	public Class getEntityClass() {
		return SysLogBusiness.class;
	}

	@SuppressWarnings( { "unchecked" })
	public Page findPage(SysLogBusinessQuery query) {
		StringBuilder sql = new StringBuilder(
				"select t from SysLogBusiness t where 1=1 and (t.deleteFlag <> 1 or t.deleteFlag is null) ");
		if (isNotEmpty(query.getId())) {
			sql.append(" and  t.id = :id ");
		}
		if (isNotEmpty(query.getUserGuid())) {
			sql.append(" and  t.userGuid = :userGuid ");
		}
		if (isNotEmpty(query.getTimeBegin())) {
			sql.append(" and  t.time >= :timeBegin ");
		}
		if (isNotEmpty(query.getTimeEnd())) {
			sql.append(" and  t.time <= :timeEnd ");
		}
		if (isNotEmpty(query.getType())) {
			sql.append(" and  t.type = :type ");
		}
		if (isNotEmpty(query.getResult())) {
			sql.append(" and  t.result = :result ");
		}
		if (isNotEmpty(query.getIp())) {
			sql.append(" and  t.ip = :ip ");
		}
		if (isNotEmpty(query.getFunction())) {
			sql.append(" and  t.function = :function ");
		}
		if (isNotEmpty(query.getLevel())) {
			sql.append(" and  t.level = :level ");
		}
		if (isNotEmpty(query.getDescription())) {
			sql.append(" and  t.description = :description ");
		}
		if (isNotEmpty(query.getSortColumns())) {
			sql.append(" /~ order by [sortColumns] ~/ ");
		}
		return pageQuery(sql.toString(), query);
	}

	public int clearLog() {
		String hql = "delete from SysLogBusiness";
		return this.executeHsql(hql);
	}
}
