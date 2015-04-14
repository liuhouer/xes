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
public class EventManager extends BaseManager<Event,java.lang.Long>{

	private EventDao eventDao;

	public void setEventDao(EventDao dao) {
		this.eventDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.eventDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(EventQuery query) {
		return eventDao.findPage(query);
	}
	
	/**
	 * 老师活动数
	 */
	@Transactional(readOnly = true)
	public long getTeacherEventCount(Teacher teacher) {
		String hql="select count(*) from Event where sendStatus=? and sendTo=? and (provinceId=0 or provinceId=?) and (cityId=? or cityId=0)";	
		Long size = null;
		size = eventDao.countByAggregate(hql, new Object[]{JTZSConstants.SendStatus.YIFASONG.getIndex(),
				JTZSConstants.ROLE_TEACHER,teacher.getProvinceId(),teacher.getCityId()});
		if(null==size){
			size = 0L;
		}
		return size;
	}
	
	/**
	 * 学生活动数
	 */
	@Transactional(readOnly = true)
	public long getStudentEventCount(Student student) {
		String hql="select count(*) from Event where sendStatus=? and sendTo=? and (provinceId=0 or provinceId=?) and (cityId=? or cityId=0)";	
		Long size = null;
		size = eventDao.countByAggregate(hql, new Object[]{JTZSConstants.SendStatus.YIFASONG.getIndex(),
				JTZSConstants.ROLE_STUDENT,student.getProvinceId(),student.getCityId()});
		if(null==size){
			size = 0L;
		}
		return size;
	}
}
