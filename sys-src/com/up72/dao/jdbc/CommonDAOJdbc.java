package com.up72.dao.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.up72.page.Pagination;
import com.up72.page.QueryResult;

public class CommonDAOJdbc extends JdbcTemplate
{

public void setDataSource(DataSource dataSource)
{
	super.setDataSource(dataSource);
}

public List<Map> findList(String sql, int startRow, int rowsCount) throws DataAccessException{
	return findList(sql, startRow, rowsCount,null);
}
/**
* 普通分页查询<br>
* <b>如果结果结合比较大应该调用setFetchsize() 和setMaxRow两个方法来控制一下，否则会内存溢出</b>
* @see #setFetchSize(int)
* @see #setMaxRows(int)
* @param sql
*            查询的sql语句
* @param startRow
*            起始行
* @param rowsCount
*            获取的行数
* @return
* @throws DataAccessException
*/
@SuppressWarnings("unchecked")
public List<Map> findList(String sql, int startRow, int rowsCount,Class clz) throws DataAccessException{
	RowMapper rowMapper = getColumnMapRowMapper();
	if(clz != null){
		rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(clz);
	}
	return query(sql, startRow, rowsCount, rowMapper);
}

/**
* 自定义行包装器查询<br>
* <b>如果结果结合比较大应该调用setFetchsize() 和setMaxRow两个方法来控制一下，否则会内存溢出</b>
* @see #setFetchSize(int)
* @see #setMaxRows(int)
* @param sql
*            查询的sql语句
* @param startRow
*            起始行
* @param rowsCount
*            获取的行数
* @param rowMapper
*            行包装器
* @return
* @throws DataAccessException
*/
@SuppressWarnings("unchecked")
public List<Map> query(String sql, int startRow, int rowsCount, RowMapper rowMapper) throws DataAccessException{
	return (List) query(sql, new SplitPageResultSetExtractor(rowMapper, startRow,rowsCount));
}
/**Object传参begin**/
	public QueryResult findPage(String sql,Object param,Pagination pagination,Class clz) {
	return findPage(sql, new Object[]{param},pagination,null,clz);
}

public QueryResult findPage(String sql,Object param,Pagination pagination, Map orders,Class clz){
	return findPage(sql, new Object[]{param},pagination,orders,clz);
}
/**Object[]传参begin**/
public QueryResult findPage(String sql,Object[] params,Pagination pagination,Class clz) {
	return findPage(sql, params,pagination,null,clz);
}

/**
 * 分页查询
 * @param sql
 * @param params
 * @param pagination
 * @param orders
 * @return
 * @throws DataAccessException
 */

@SuppressWarnings("unchecked")
public QueryResult findPage(String sql,Object[] params, Pagination pagination,Map orders,Class clz) throws DataAccessException{
	String sqlCount = "select count(*) " + sql.substring(sql.indexOf("from"));
	
	String resultSql = "";
	//xxh 含distinct sql语句的统计处理begin
	String cs = null;
	if(sql.indexOf("select") >= 0 && 
			sql.indexOf("from") > 0 && 
			sql.indexOf("from") > sql.indexOf("select"))
		cs = sql.substring(sql.indexOf("select") +  "select".length(), sql.indexOf("from"));
	if(cs != null)
	{
		String c = cs.trim();
		sqlCount = "select count(" + c + ") " + sql.substring(sql.indexOf("from"));
	}else{
		sqlCount = "select count(*) " + sql.substring(sql.indexOf("from"));
	}
	//xxh 含distinct sql语句的统计处理end

	StringBuffer stringBuffer = new StringBuffer(sql);
	if(orders != null){
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
		stringBuffer.append(" order by id desc");
		resultSql = stringBuffer.toString();
	}
	RowMapper rowMapper = getColumnMapRowMapper();
	
	pagination.setTotalRecord(queryForInt(sqlCount,params));
	if(clz !=null){
		rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(clz);
	}
	return new QueryResult(pagination,query(resultSql,pagination.getStart(),pagination.getRange(),rowMapper));
}

public QueryResult findPage(String sql,HashMap<String, Object> params,Pagination pagination,Class clz) {
	return findPage(sql, params,pagination,null,clz);
}

/**
 * 查找到的分页对象集合
 * @param sql
 * @param params Map 查询参数，属性=值对
 * @param orderMap
 * @param pagination 分页对象
 * @return
 */
public QueryResult findPage(String sql, HashMap<String, Object> params, Pagination pagination, Map<String ,String> orderMap,Class clz) {
	StringBuffer hsql = new StringBuffer();
	hsql.append(sql);
	
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
				hsql.append(" and " + field);
			}
			else
			{
				hsql.append(" and " + field + "=?");
				values.add(value);
			}
		}
	}
	QueryResult result = findPage(hsql.toString(), values.toArray(), pagination, orderMap, clz);
	return result;
}
}


