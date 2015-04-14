/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */

package com.xes.jtzs.service;

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
public class EventSendToUserManager extends BaseManager<EventSendToUser,java.lang.Long>{

	private EventSendToUserDao eventSendToUserDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TeacherDao teacherDao;

	public void setEventSendToUserDao(EventSendToUserDao dao) {
		this.eventSendToUserDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.eventSendToUserDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(EventSendToUserQuery query) {
		return eventSendToUserDao.findPage(query);
	}
	
	/**
	 * 根据eventid获取到指定发送人的信息
	 */
	@Transactional(readOnly = true)
	public List<EventSendToUser>  getSendInfoByEventId(Long id) {
		String hql="from EventSendToUser where eventId = ? ";
		List<EventSendToUser> list = eventSendToUserDao.findList(hql, new Object[]{id});
		return list;
	}
	
	/**
	 * 根据eventid获取到指定发送人的信息
	 */
	@Transactional(readOnly = true)
	public List  getUserList(int role) {
		List  list =null;
		if(role==0){
			String hql="from Student  ";
			  list = studentDao.findList(hql, new Object[]{});
		}else{
			
			String hql="from Teacher ";
			  list = teacherDao.findList(hql, new Object[]{});
		}
		return list;
	}
	
	/**
	 * 判断唯一性
	 */
	@Transactional(readOnly = true)
	public boolean  sendFlag(int userRole,long userId,long eventId) {
		boolean  flag =true;
		long size = 0;
			String hql="select count(*) from EventSendToUser where userRole = ? and userId = ?  and eventId = ?  ";
			     size =  eventSendToUserDao.countByAggregate(hql, new Object[]{userRole,userId,eventId});
			if(size>0){
				flag= false;
			}
		return flag;
	}
	
	
	
}
