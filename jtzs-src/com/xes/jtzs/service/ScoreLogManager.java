/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import javax.persistence.Transient;
import com.up72.base.*;
import com.up72.framework.page.*;
import com.xes.jtzs.model.*;
import com.xes.jtzs.dao.*;
import com.xes.jtzs.vo.query.*;/**
 * 业务处理
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class ScoreLogManager extends BaseManager<ScoreLog,java.lang.Long>{

	private ScoreLogDao scoreLogDao;

	public void setScoreLogDao(ScoreLogDao dao) {
		this.scoreLogDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.scoreLogDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(ScoreLogQuery query) {
		return scoreLogDao.findPage(query);
	}
	
	@Transient
	public List<ScoreLog> getLogList(long userid,int role){
		String hql =" from ScoreLog where userId =? and userRole=?  ";
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("addTime", "desc");
		List<ScoreLog> list = scoreLogDao.findList(hql, new Object[]{userid,role}, 0, orders);
		return list;
	}
}
