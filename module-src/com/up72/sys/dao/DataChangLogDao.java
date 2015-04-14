/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.sys.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.up72.base.BaseHibernateDao;
import com.up72.exception.ServiceException;
import com.up72.framework.page.Page;
import com.up72.sys.model.DataChangLog;
import com.up72.sys.vo.query.DataChangLogQuery;

/**
 * Hibernate数据处理
 * 
 * @author up72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class DataChangLogDao extends BaseHibernateDao<DataChangLog,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return DataChangLog.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(DataChangLogQuery query) {
        StringBuilder sql = new StringBuilder("select t from DataChangLog t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getChangTable())) {
            sql.append(" and  t.changTable = :changTable ");
        }
        if(isNotEmpty(query.getChangId())) {
            sql.append(" and  t.changId = :changId ");
        }
        if(isNotEmpty(query.getChangTimeBegin())) {
            sql.append(" and  t.changTime >= :changTimeBegin ");
        }
        if(isNotEmpty(query.getChangTimeEnd())) {
            sql.append(" and  t.changTime <= :changTimeEnd ");
        }
        if(isNotEmpty(query.getChangType())) {
            sql.append(" and  t.changType = :changType ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	
	/**
	 * 获取频道，栏目列表信息
	 * @author zwguan
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DataChangLog> findDataChangLogList(final long startTime,final long endTime,final String changTable){
		String sql = "select tc.* from (select d.id,d.chang_table,d.chang_Id,d.chang_time,d.chang_type " +
				"from sys_data_chang_log d where d.chang_time > ? and d.chang_time < ? and d.chang_table=? order by d.chang_type asc,d.chang_time asc) tc"
				+ " group by tc.chang_Id" ;
		
		return this.findDataChangLogList(sql, new Object[]{startTime,endTime,changTable});
	}
	
	/**
	 * 添加数据变更日志
	 * @author wqtan
	 * @param changId
	 * @param changObject
	 * @param changType
	 */
	public DataChangLog addDataChangLog(Long changId,Object changObject,String changType){
		try{
			DataChangLog result = new DataChangLog();
			result.setChangId(changId);
			result.setChangTable(changObject.getClass().getSimpleName());
			result.setChangType(changType);
			result.setChangTime(new Date().getTime());
			this.save(result);
			return result;
		}catch (Exception e) {
			throw new ServiceException(e.getMessage(),e);
		}
	}
	
	/**
	 * 获取频道下新闻列表分页信息
	 * @author yjLiang
	 * @return
	 */
	public List<DataChangLog> findCmpOrResourceChangLogList(final long startTime,final long endTime,
			final String changTable,final String tableRealName,final Long channelId,
			final Long pageStart,final Long pageRange){
		String sql="SELECT * FROM"+
		"(" +
		"SELECT sdcl.*" +
		//",ccpager.RESOURCE_IDS,ccpager.COMPOSE " +
		"FROM sys_data_chang_log sdcl,"+tableRealName+" ccpager "+
		"WHERE sdcl.CHANG_TABLE=? " +
		"AND sdcl.CHANG_ID=ccpager.ID "+
		"AND sdcl.CHANG_TIME>? "+
		"AND sdcl.CHANG_TIME<? "+
		"AND ccpager.CHANNEL_ID=? "+
		"AND sdcl.CHANG_TYPE IN('insert','update','delete') "+
		"ORDER BY sdcl.CHANG_ID DESC,sdcl.CHANG_TYPE,sdcl.CHANG_TIME DESC"+
		")"+
		"temp "+
		"GROUP BY CHANG_ID "+
		"LIMIT ?,?"
		;
		return this.findDataChangLogList(sql, new Object[]{changTable,startTime,endTime,channelId,pageStart,pageRange});
	}
	
	/**
	 * 获取cover信息
	 * @author yjLiang
	 * @return
	 */
	public List<DataChangLog> findCoverChangLogList(final long startTime,final long endTime,
			final String changTable,final String tableRealName){
		String sql="SELECT * FROM"+
		"(" +
		"SELECT sdcl.*" +
		//",ccpager.RESOURCE_IDS,ccpager.COMPOSE " +
		"FROM sys_data_chang_log sdcl,"+tableRealName+" ccpager "+
		"WHERE sdcl.CHANG_TABLE=? " +
		"AND sdcl.CHANG_ID=ccpager.ID "+
		"AND sdcl.CHANG_TIME>? "+
		"AND sdcl.CHANG_TIME<? "+
		"AND (ccpager.CHANNEL_ID=0 OR ccpager.CHANNEL_ID IS NULL) "+
		"AND ccpager.IS_DEFAULT=1 "+
		"AND sdcl.CHANG_TYPE IN('insert','update','delete') "+
		"ORDER BY sdcl.CHANG_ID DESC,sdcl.CHANG_TYPE,sdcl.CHANG_TIME DESC"+
		")"+
		"temp "+
		"GROUP BY CHANG_ID "
		;
		return this.findDataChangLogList(sql, new Object[]{changTable,startTime,endTime});
	}
	
	/**
	 * 获取cmsChannel（无父栏目的）信息
	 * @author yjLiang
	 * @return
	 */
	public List<DataChangLog> findChannelNoCatChangLogList(final long startTime,final long endTime,
			final String changTable,final String tableRealName){
		String sql="SELECT * FROM"+
		"(" +
		"SELECT sdcl.*" +
		"FROM sys_data_chang_log sdcl,"+tableRealName+" ccpager "+
		"WHERE sdcl.CHANG_TABLE=? " +
		"AND sdcl.CHANG_ID=ccpager.ID "+
		"AND sdcl.CHANG_TIME>? "+
		"AND sdcl.CHANG_TIME<? "+
		"AND (ccpager.CMS_CATEGORY_ID=0 OR ccpager.CMS_CATEGORY_ID IS NULL) "+
		"AND sdcl.CHANG_TYPE IN('insert','update','delete') "+
		"ORDER BY sdcl.CHANG_ID DESC,sdcl.CHANG_TYPE,sdcl.CHANG_TIME DESC"+
		")"+
		"temp "+
		"GROUP BY CHANG_ID "
		;
		return this.findDataChangLogList(sql, new Object[]{changTable,startTime,endTime});
	}
	/**
	 * 查询某个频道下的资源变化的数量
	 * @param startTime
	 * @param endTime
	 * @param changTable
	 * @param tableRealName
	 * @return
	 */
	public int findChangCountForChannel(final long channelId,final long startTime,final long endTime,
			final String changTable,final String tableRealName){
		int result=0;
		String sql="select count(id) from "+tableRealName+" where channel_ids like '%["+channelId+"]%'"
					+" and id in (select CHANG_ID from sys_data_chang_log where chang_table=? "
									+" and CHANG_TIME>?  and CHANG_TIME < ?);";
		List list = this.findListBySQL(sql, new Object[]{changTable,startTime,endTime},0, null);
		if(null != list && !list.isEmpty()){
			result=Integer.valueOf(list.get(0).toString());
				
		}
		return result;
	}
/**
 * 公用的SQL查询datalog方法
 * @author yjLiang
 * @param sql
 * @param params
 * @return
 */
	public List<DataChangLog> findDataChangLogList(String sql,Object[] params){
		List list = this.findListBySQL(sql, params, 0, null);
		List<DataChangLog> result = new LinkedList<DataChangLog>();
		if(null != list && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Object[] array = (Object[])list.get(i);
				DataChangLog d = new DataChangLog();
				d.setId(Long.parseLong(array[0].toString()));
				d.setChangTable((String)array[1]);
				d.setChangId(Long.parseLong(array[2].toString()));
				d.setChangTime(Long.parseLong(array[3].toString()));
				d.setChangType((String)array[4]);
				result.add(d);
			}
		}
		return result;
	}
	
	
}
