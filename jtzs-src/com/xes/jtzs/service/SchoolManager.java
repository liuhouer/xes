/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.up72.common.CommonUtils.*;
import com.up72.framework.beanutils.BeanUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import com.up72.base.*;
import com.up72.util.*;

import com.up72.framework.util.*;
import com.up72.framework.web.util.*;
import com.up72.framework.page.*;
import com.up72.framework.page.impl.*;

import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.model.*;
import com.xes.jtzs.dao.*;
import com.xes.jtzs.service.*;
import com.xes.jtzs.vo.query.*;/**
 * 业务处理
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class SchoolManager extends BaseManager<School,java.lang.Long>{

	private SchoolDao schoolDao;

	public void setSchoolDao(SchoolDao dao) {
		this.schoolDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.schoolDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(SchoolQuery query) {
		return schoolDao.findPage(query);
	}
	
	/**
	 * 按排序得到对应父级下的所有启用数据
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<School> getShowListByInitial(Long provinceId,Long cityId,Long areaId,Integer division){
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("enName", "asc");
		StringBuilder sb = new StringBuilder("FROM School where status=? ");
		Object[] obj = new Object[2];
		obj[0] = JTZSConstants.Pubilc.ENABLED.getIndex();
		if(null!=division && division>0){
			sb.append(" and divisions like '%[").append(division.toString()).append("]%' ");
		}
		if(null!=areaId && areaId > 0){
			sb.append(" and areaId=? ");
			obj[1] = areaId;
		}else if(null!=cityId && cityId > 0){
			sb.append(" and cityId=? ");
			obj[1] = cityId;
		}else if(null!=provinceId && provinceId > 0){
			sb.append(" and provinceId=? ");
			obj[1] = provinceId;
		}else{
			sb.append(" and 1=? ");
			obj[1] = 1;
		}
		
		
		List<School> resultList = schoolDao.findList(sb.toString(), obj, 0, orders);
		if(null == resultList){
			resultList = new ArrayList<School>();
		}
		return resultList;
	}
	
	/**
	 * 按字母排序得到所有启用数据
	 * @return String
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	public List<Map<String,Object>> getSchoolByInitial(Long provinceId,Long cityId,Long areaId,Integer division,boolean isEncode) throws UnsupportedEncodingException{
		List<School> areaList = getShowListByInitial(provinceId,cityId,areaId,division);
		List<Map<String,Object>> resultList =  new ArrayList<Map<String,Object>>();
		
		School school = null;
		Map<String,Object> dataMap = null;
		for (int i = 0; i < areaList.size(); i++) {
			school = areaList.get(i);
			String initial = school.getEnName().substring(0, 1);
			dataMap = new LinkedHashMap<String, Object>();
			dataMap.put("id", school.getId());
			if(isEncode){
				dataMap.put("name", URLEncoder.encode(school.getName(), "UTF-8"));
			}else{
				dataMap.put("name", school.getName());
			}
			dataMap.put("en",  initial);
			resultList.add(dataMap);
			
			school = null;
			dataMap = null;
		}
		return resultList;
	}
	
}
