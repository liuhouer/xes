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
public class ProvinceManager extends BaseManager<Province,java.lang.Long>{

	private ProvinceDao provinceDao;

	public void setProvinceDao(ProvinceDao dao) {
		this.provinceDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.provinceDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(ProvinceQuery query) {
		return provinceDao.findPage(query);
	}
	
	/**
	 * 按排序得到所有启用数据
	 * @author liutongling
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Province> getShowListBySort(){
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("sort", "asc");
		List<Province> resultList = provinceDao.findList("FROM Province where status=?", new Object[]{JTZSConstants.Pubilc.ENABLED.getIndex()}, 0, orders);
		if(null == resultList){
			resultList = new ArrayList<Province>();
		}
		return resultList;
	}
	
	/**
	 * 按字母排序得到所有启用数据
	 * @author liutongling
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Province> getShowListByInitial(){
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("enName", "asc");
		List<Province> resultList = provinceDao.findList("FROM Province where status=?", new Object[]{JTZSConstants.Pubilc.ENABLED.getIndex()}, 0, orders);
		if(null == resultList){
			resultList = new ArrayList<Province>();
		}
		return resultList;
	}
	
	/**
	 * 按字母排序得到所有启用数据
	 * @return String
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	public List<Map<String,Object>> getProvinceListByInitial(boolean isEncode) throws UnsupportedEncodingException{
		List<Province> provinceList = getShowListByInitial();
		List<Map<String,Object>> resultList =  new ArrayList<Map<String,Object>>();
		
		Province province = null;
		Map<String,Object> dataMap = null;
		for (int i = 0; i < provinceList.size(); i++) {
			province = provinceList.get(i);
			String initial = province.getEnName().substring(0, 1);
			dataMap = new LinkedHashMap<String, Object>();
			dataMap.put("id", province.getId());
			if(isEncode){
				dataMap.put("name", URLEncoder.encode(province.getName(),"UTF-8"));
			}else{
				dataMap.put("name", province.getName());
			}
			dataMap.put("en",  initial);
			
			resultList.add(dataMap);
			
			province = null;
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
	public boolean isUniqueName(String name,Long id) {
		boolean unique = true;
		Long size = null;
		if(StringUtils.isNotBlank(name)){
			name = name.trim();
			StringBuilder sb = new StringBuilder("select count(id) from Province where name=?");
			if(null != id){
				sb.append(" and id!=?");
				size = provinceDao.countByAggregate(sb.toString(), new Object[]{name,id});
			}else{
				size = provinceDao.countByAggregate(sb.toString(), new Object[]{name});
			}
		}
		if( null == size || size > 0){
			unique = false;
		}
		return unique;
	}
	
}
