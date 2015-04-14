/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;
import com.up72.sys.dao.DataChangLogDao;
import com.up72.sys.model.DataChangLog;
import com.up72.sys.vo.query.DataChangLogQuery;
/**
 * 业务处理
 * 
 * @author up72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class DataChangLogManager extends BaseManager<DataChangLog,java.lang.Long>{

	private DataChangLogDao dataChangLogDao;

	public void setDataChangLogDao(DataChangLogDao dao) {
		this.dataChangLogDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.dataChangLogDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(DataChangLogQuery query) {
		return dataChangLogDao.findPage(query);
	}

	public List<DataChangLog> findDataChangLogList(long startTime,long endTime,String changTable){
		return this.dataChangLogDao.findDataChangLogList(startTime, endTime, changTable);
	}
	public int findChangCountForChannel(long channelId,long startTime,long endTime,
			String changTable,String tableRealName ){
		return this.dataChangLogDao.findChangCountForChannel(channelId,startTime, endTime, changTable,tableRealName);
	}
	
	/**
	 * 添加数据变更日志
	 * @author wqtan
	 * @param changId
	 * @param changObject
	 * @param changType
	 */
	public DataChangLog addDataChangLog(Long changId,Object changObject,String changType){
		return this.dataChangLogDao.addDataChangLog(changId, changObject, changType);
	}
}
