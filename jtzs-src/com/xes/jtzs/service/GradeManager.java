/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
public class GradeManager extends BaseManager<Grade,java.lang.Long>{

	private GradeDao gradeDao;

	public void setGradeDao(GradeDao dao) {
		this.gradeDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.gradeDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(GradeQuery query) {
		return gradeDao.findPage(query);
	}
	/**
	 * 按排序得到所有数据
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Grade> getGradeListBySort(){
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("sort", "asc");
		List<Grade> resultList = gradeDao.findList("FROM Grade", null, 0, orders);
		if(null == resultList){
			resultList = new ArrayList<Grade>();
		}
		return resultList;
	}
	
	/**
	 * 按排序得到所有数据
	 * @return String
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	public List<Map<String,Object>> getGradeBySort() throws UnsupportedEncodingException{
		List<Grade> gradeList = getGradeListBySort();
		List<Map<String,Object>> resultList =  new ArrayList<Map<String,Object>>();
		
		Grade grade = null;
		Map<String,Object> dataMap = null;
		for (int i = 0; i < gradeList.size(); i++) {
			grade = gradeList.get(i);
			dataMap = new LinkedHashMap<String, Object>();
			dataMap.put("id", grade.getId());
			dataMap.put("name", grade.getName());
			dataMap.put("division", grade.getDivision());
			resultList.add(dataMap);
			
			grade = null;
			dataMap = null;
		}
		return resultList;
	}
	
	
	/**
	 * 对应学科下的年级
	 */
	public List<Map<String,Object>> getGradeBySubject(Long subjectId) throws UnsupportedEncodingException{
		List<Map<String,Object>> resultList =  new ArrayList<Map<String,Object>>();
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("sort", "asc");
		List<Grade> gradeList = gradeDao.findList("FROM Grade where id in(select gradeId from GradeSubject where status=? and subjectId=?)", new Object[]{JTZSConstants.Pubilc.ENABLED.getIndex(),subjectId}, 0, orders);
		if(null != gradeList){
			Grade grade = null;
			Map<String,Object> dataMap = null;
			for (int i = 0; i < gradeList.size(); i++) {
				grade = gradeList.get(i);
				dataMap = new LinkedHashMap<String, Object>();
				dataMap.put("id", grade.getId());
				dataMap.put("name", grade.getName());
				dataMap.put("division", grade.getDivision());
				resultList.add(dataMap);
				
				grade = null;
				dataMap = null;
			}
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
	public boolean isUniqueName(String name,Long id,Integer division) {
		boolean unique = true;
		Long size = null;
		if(StringUtils.isNotBlank(name)){
			name = name.trim();
			StringBuilder sb = new StringBuilder("select count(id) from Grade where name=? and division=?");
			if(null != id){
				sb.append(" and id!=?");
				size = gradeDao.countByAggregate(sb.toString(), new Object[]{name,division,id});
			}else{
				size = gradeDao.countByAggregate(sb.toString(), new Object[]{name,division});
			}
		}
		if( null == size || size > 0){
			unique = false;
		}
		return unique;
	}

}
