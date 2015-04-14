/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import org.apache.commons.lang.StringUtils;
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
public class SubjectManager extends BaseManager<Subject,java.lang.Long>{

	private SubjectDao subjectDao;

	public void setSubjectDao(SubjectDao dao) {
		this.subjectDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.subjectDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(SubjectQuery query) {
		return subjectDao.findPage(query);
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
		if(StringUtils.isNotEmpty(name)){
			StringBuilder sb = new StringBuilder("select count(id) from Subject where name=?");
			if(null != id){
				sb.append(" and id!=?");
				size = subjectDao.countByAggregate(sb.toString(), new Object[]{name,id});
			}else{
				size = subjectDao.countByAggregate(sb.toString(), new Object[]{name});
			}
		}
		if( null == size || size > 0){
			unique = false;
		}
		return unique;
	}
	
	/**
	 * 按排序得到所有启用数据
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Subject> getShowListBySort(){
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("sort", "asc");
		Object[] obj = new Object[1];
		obj[0] = JTZSConstants.Pubilc.ENABLED.getIndex();
		List<Subject> resultList = subjectDao.findList("FROM Subject where status=? ",obj, 0, orders);
		if(null == resultList){
			resultList = new ArrayList<Subject>();
		}
		return resultList;
	}
	
	/**
	 * 按字母排序得到所有启用数据
	 * @return String
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	public List<Map<String,Object>> getSubjectBySort() throws UnsupportedEncodingException{
		List<Subject> subjectList = getShowListBySort();
		List<Map<String,Object>> resultList =  new ArrayList<Map<String,Object>>();
		
		Subject subject = null;
		Map<String,Object> dataMap = null;
		for (int i = 0; i < subjectList.size(); i++) {
			subject = subjectList.get(i);
			dataMap = new LinkedHashMap<String, Object>();
			dataMap.put("id", subject.getId());
			dataMap.put("name", subject.getName());
			resultList.add(dataMap);
			
			subject = null;
			dataMap = null;
		}
		return resultList;
	}
	
}
