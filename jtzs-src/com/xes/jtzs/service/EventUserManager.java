/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.up72.common.CommonUtils.*;
import com.up72.framework.beanutils.BeanUtils;

import java.util.*;

import com.up72.base.*;
import com.up72.util.*;

import com.up72.framework.util.*;
import com.up72.framework.web.util.*;
import com.up72.framework.page.*;
import com.up72.framework.page.impl.*;

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
public class EventUserManager extends BaseManager<EventUser,java.lang.Long>{

	private EventUserDao eventUserDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TeacherDao teacherDao;

	public void setEventUserDao(EventUserDao dao) {
		this.eventUserDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.eventUserDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(EventUserQuery query) {
		return eventUserDao.findPage(query);
	}
	
	/**
	 * 判断是否唯一
	 * @author 
	 * @param name
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isUnique(Long eventid,Long userid,int role) {
		boolean unique = true;
		Long size = null;
		StringBuilder sb = new StringBuilder(
				"select count(id) from EventUser where eventId=? and userId=? and userRole=?");
			size = eventUserDao.countByAggregate(sb.toString(), new Object[] {
				eventid, userid, role});
		if( null == size || size > 0){
			unique = false;
		}
		return unique;
	}
	
	/**
	 * 判断是否存在用户
	 * @author 
	 * @param name
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isExist(Long userid,int role) {
		boolean exist = false;
		Long size1 = null;
		Long size2 = null;
		if(role==0){
		StringBuilder sb1 = new StringBuilder(
		"select count(*) from  Teacher where    id=?  ");
		size1 = studentDao.countByAggregate(sb1.toString(), new Object[] {
				 userid});
		}else if(role==1){
		StringBuilder sb2 = new StringBuilder(
		"select count(*) from  Teacher where    id=?  ");
		size2 = teacherDao.countByAggregate(sb2.toString(), new Object[] {
			 userid});	
		}
		if(   (size1!=null&&size1>0) || (size2!=null&&size2>0)  ){
			exist = true;
		}
		return exist;
	}
}
