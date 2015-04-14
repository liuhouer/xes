/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class CityManager extends BaseManager<City,java.lang.Long>{

	private CityDao cityDao;

	public void setCityDao(CityDao dao) {
		this.cityDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.cityDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(CityQuery query) {
		return cityDao.findPage(query);
	}
	
	/**
	 * 按排序得到对应父级下的所有启用数据
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<City> getShowListBySort(Long provinceId){
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("sort", "asc");
		List<City> resultList = cityDao.findList("FROM Area where status=? and provinceId=?", new Object[]{JTZSConstants.Pubilc.ENABLED.getIndex(),provinceId}, 0, orders);
		if(null == resultList){
			resultList = new ArrayList<City>();
		}
		return resultList;
	}
	
	/**
	 * 按首字母排序得到对应父级下的所有启用数据
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<City> getShowListByInitial(Long provinceId){
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("enName", "asc");
		List<City> resultList = cityDao.findList("FROM City where status=? and provinceId=?", new Object[]{JTZSConstants.Pubilc.ENABLED.getIndex(),provinceId}, 0, orders);
		if(null == resultList){
			resultList = new ArrayList<City>();
		}
		return resultList;
	}
	
	/**
	 * 按字母排序得到所有启用数据
	 * @return String
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	public List<Map<String,Object>> getCityByInitial(Long provinceId,boolean isEncode) throws UnsupportedEncodingException{
		List<City> cityList = getShowListByInitial(provinceId);
		List<Map<String,Object>> resultList =  new ArrayList<Map<String,Object>>();
		
		City city = null;
		Map<String,Object> dataMap = null;
		for (int i = 0; i < cityList.size(); i++) {
			city = cityList.get(i);
			String initial = city.getEnName().substring(0, 1);
			dataMap = new LinkedHashMap<String, Object>();
			dataMap.put("id", city.getId());
			if(isEncode){
				dataMap.put("name", URLEncoder.encode(city.getName(),"UTF-8"));
			}else{
				dataMap.put("name", city.getName());
			}
			dataMap.put("en",  initial);
			
			resultList.add(dataMap);
			
			city = null;
			dataMap = null;
		}
		return resultList;
	}
	
 	/**
	 * 判断是否唯一
	 * @author liutongling
	 * @param name
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isUniqueName(String name,Long id,Long provinceId) {
		boolean unique = true;
		Long size = null;
		if(StringUtils.isNotBlank(name)){
			name = name.trim();
			StringBuilder sb = new StringBuilder("select count(id) from City where name=? and provinceId=?");
			if(null != id){
				sb.append(" and id!=?");
				size = cityDao.countByAggregate(sb.toString(), new Object[]{name,provinceId,id});
			}else{
				size = cityDao.countByAggregate(sb.toString(), new Object[]{name,provinceId});
			}
		}
		if( null == size || size > 0){
			unique = false;
		}
		return unique;
	}
}
