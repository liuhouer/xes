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
import com.xes.jtzs.vo.query.*;
/**
 * 业务处理
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class CommonRuleManager extends BaseManager<CommonRule,java.lang.Long>{
	private static Map<Integer,CommonRule> ruleTyepMap = new HashMap<Integer,CommonRule>();
	private static Map<Long,CommonRule> ruleIdMap = new HashMap<Long,CommonRule>();
	
	private CommonRuleDao commonRuleDao;

	public void setCommonRuleDao(CommonRuleDao dao) {
		this.commonRuleDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.commonRuleDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(CommonRuleQuery query) {
		return commonRuleDao.findPage(query);
	}
	
	/**
	 * 通过id获得缓存中有效的规则 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public CommonRule getCacheRuleById(Long id) {
		CommonRule commonRule = ruleIdMap.get(id);
		if(null == commonRule){
			commonRule = commonRuleDao.getById(id);
			if(null != commonRule){
				setCacheRuleById(commonRule);
			}
		}
		return commonRule;
	}
	
	/**
	 * 验证规则是否有效
	 * @author liutongling
	 * @param commonRule
	 * @return
	 */
	@Transactional(readOnly=true)
	public boolean isValid(CommonRule commonRule){
		return commonRuleDao.isValid(commonRule);
	}
	
	/**
	 * 通过id存储缓存中的规则 
	 * @param commonRule
	 * @return
	 */
	@Transactional(readOnly=true)
	public void setCacheRuleById(CommonRule commonRule) {
		ruleIdMap.put(commonRule.getId(), commonRule);
	}
	
	/**
	 * 通过id删除缓存中的规则 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public void delCacheRuleById(Long id) {
		ruleIdMap.remove(id);
	}
	
	/**
	 * 通过类型获得缓存中的规则 
	 * @param type
	 * @return
	 */
	@Transactional(readOnly=true)
	public CommonRule getCacheRuleByType(Integer type) {
		CommonRule commonRule = ruleTyepMap.get(type);
		if(null == commonRule){
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("ruleType", type);
			params.put("( ruleType!= "+ JTZSConstants.CommonRuleType.QuestionScore.getIndex()+" or ruleType!= "+JTZSConstants.CommonRuleType.Expert.getIndex()+")", null);
			commonRule = commonRuleDao.findByProperties(params);
			if(null!=commonRule){
				setCacheRuleByType(commonRule);
			}
		}
		return commonRule;
	}
	
	/**
	 * 通过类型存储缓存中的规则 
	 * @param commonRule
	 * @return
	 */
	@Transactional(readOnly=true)
	public void setCacheRuleByType(CommonRule commonRule) {
		ruleTyepMap.put(commonRule.getRuleType(), commonRule);
	}
	
	/**
	 * 获得提问扣积分规则（xx分钟扣xx积分，专家解答）
	 * @return
	 */
	public List<CommonRule> queryByType(){
		List<CommonRule> result = new ArrayList<CommonRule>();
		StringBuilder hql = new StringBuilder("from CommonRule where status=? and (ruleType=? or ruleType=?)");
		Object[] params = new Object[3];
		params[0] = JTZSConstants.Pubilc.ENABLED.getIndex();
		params[1] = JTZSConstants.CommonRuleType.QuestionScore.getIndex();
		params[2] = JTZSConstants.CommonRuleType.Expert.getIndex();
		List<CommonRule> list = commonRuleDao.findList(hql.toString(), params);
		for (int i = 0; i < list.size(); i++) {
			CommonRule cr = list.get(i);
			if(null!=cr.getValidStartTime()){
				if(null!=cr.getValidStopTime()){
					Long time = new Date().getTime();
					if(cr.getValidStartTime() < time && time < cr.getValidStopTime()){
						result.add(cr);
					}
				}
			}else if(null==cr.getValidStopTime()){
					result.add(cr);
			}
		}
		return result;
	}
	
}
