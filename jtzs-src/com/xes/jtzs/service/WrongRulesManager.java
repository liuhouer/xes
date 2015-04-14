/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.up72.base.*;
import com.up72.framework.page.*;

import com.xes.jtzs.JTZSConstants;
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
public class WrongRulesManager extends BaseManager<WrongRules,java.lang.Long>{

	private WrongRulesDao wrongRulesDao;

	public void setWrongRulesDao(WrongRulesDao dao) {
		this.wrongRulesDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.wrongRulesDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(WrongRulesQuery query) {
		return wrongRulesDao.findPage(query);
	}

	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public boolean isUniqueName(Long id, Integer role, Integer wrongNum) {
		boolean unique = true;
		Long size = null;
		if(null != wrongNum && null != role){
			StringBuilder sb = new StringBuilder("select count(id) from WrongRules where role=? and wrongNum=?  ");
			if(null != id){//id不是空，编辑
				sb.append(" and id!=?");
				size = wrongRulesDao.countByAggregate(sb.toString(), new Object[]{role,wrongNum,id});
			}else{//新增
				size = wrongRulesDao.countByAggregate(sb.toString(), new Object[]{role,wrongNum});
			}
		}
		if( null != size && size > 0){
			unique = false;
		}
		return unique;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public WrongRules getStudentWrongRulesByWrongNum(int num){
		WrongRules result = null;
		List<WrongRules> list = wrongRulesDao.findList("from WrongRules where role=? and wrongNum=?", new Object[]{JTZSConstants.ROLE_STUDENT,num});
		if(null!=list && list.size()>0){
			result = list.get(0);
		}
		return result;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public WrongRules getTeacherWrongRulesByWrongNum(int num){
		WrongRules result = null;
		List<WrongRules> list = wrongRulesDao.findList("from WrongRules where role=? and wrongNum=?", new Object[]{JTZSConstants.ROLE_TEACHER,num});
		if(null!=list && list.size()>0){
			result = list.get(0);
		}
		return result;
	}
	
}
