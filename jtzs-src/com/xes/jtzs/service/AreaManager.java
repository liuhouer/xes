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
public class AreaManager extends BaseManager<Area,java.lang.Long>{

	private AreaDao areaDao;

	public void setAreaDao(AreaDao dao) {
		this.areaDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.areaDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(AreaQuery query) {
		return areaDao.findPage(query);
	}
	
	/**
	 * 按排序得到对应父级下的所有启用数据
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Area> getShowListBySort(Long cityId){
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("sort", "asc");
		List<Area> resultList = areaDao.findList("FROM Area where status=? and cityId=?", new Object[]{JTZSConstants.Pubilc.ENABLED.getIndex(),cityId}, 0, orders);
		if(null == resultList){
			resultList = new ArrayList<Area>();
		}
		return resultList;
	}
	
	/**
	 * 按排序得到对应父级下的所有启用数据
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Area> getShowListByInitial(Long cityId){
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("enName", "asc");
		List<Area> resultList = areaDao.findList("FROM Area where status=? and cityId=?", new Object[]{JTZSConstants.Pubilc.ENABLED.getIndex(),cityId}, 0, orders);
		if(null == resultList){
			resultList = new ArrayList<Area>();
		}
		return resultList;
	}
	
	/**
	 * 按字母排序得到所有启用数据
	 * @return String
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	public List<Map<String,Object>> getAreaByInitial(Long cityId,boolean isEncode) throws UnsupportedEncodingException{
		List<Area> areaList = getShowListByInitial(cityId);
		List<Map<String,Object>> resultList =  new ArrayList<Map<String,Object>>();
		
		Area area = null;
		Map<String,Object> dataMap = null;
		for (int i = 0; i < areaList.size(); i++) {
			area = areaList.get(i);
			String initial = area.getEnName().substring(0, 1);
			dataMap = new LinkedHashMap<String, Object>();
			dataMap.put("id", area.getId());
			if(isEncode){
				dataMap.put("name", URLEncoder.encode(area.getName(),"UTF-8"));
			}else{
				dataMap.put("name", area.getName());
			}
			dataMap.put("en",  initial);
			
			resultList.add(dataMap);
			
			area = null;
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
	public boolean isUniqueName(String name,Long id,Long cityId) {
		boolean unique = true;
		Long size = null;
		if(StringUtils.isNotBlank(name)){
			name = name.trim();
			StringBuilder sb = new StringBuilder("select count(id) from Area where name=? and cityId=?");
			if(null != id){
				sb.append(" and id!=?");
				size = areaDao.countByAggregate(sb.toString(), new Object[]{name,cityId,id});
			}else{
				size = areaDao.countByAggregate(sb.toString(), new Object[]{name,cityId});
			}
		}
		if( null == size || size > 0){
			unique = false;
		}
		return unique;
	}
}
