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

import java.text.SimpleDateFormat;
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
public class TeacherManager extends BaseManager<Teacher,java.lang.Long>{

	private TeacherDao teacherDao;

	public void setTeacherDao(TeacherDao dao) {
		this.teacherDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.teacherDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(TeacherQuery query) {
		return teacherDao.findPage(query);
	}
	
	/**
	 * 修改密码
	 * @author tlliu
	 */
	public String resetPwd(String oldPassword, String newPassword, Long id) throws Exception {
		String result = JTZSConstants.ErrorCode.gg500;
		Teacher teacher = this.getById(id);
		if(null != teacher){
			if (!encryptUtil.md5(oldPassword).equals(teacher.getPassword())) {
				result = JTZSConstants.ErrorCode.gg005;
			} else if(StringUtils.isEmpty(newPassword)){
				result = JTZSConstants.ErrorCode.gg006;
			} else if(newPassword.length()<6 ||newPassword.length()>18){
				result = JTZSConstants.ErrorCode.gg007;
			} else {
				try {
					teacher.setPassword(encryptUtil.md5(newPassword));
					this.update(teacher);
					result = JTZSConstants.ErrorCode.gg000;
				} catch (Exception e) {
				}
			}
		}else{
			result = JTZSConstants.ErrorCode.gg008;
		}
		return result;
	}
	
	/**
	 * 用户名是否唯一(存在) true 唯一 false 不唯一
	 * @param userName
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isUniqueLoginName(String loginName,Long id) {
		boolean unique = true;
		Long size = null;
		if(StringUtils.isNotBlank(loginName)){
			loginName = loginName.trim();
			StringBuilder sb = new StringBuilder("select count(id) from Teacher where loginName=?");
			if(null != id){
				sb.append(" and id!=?");
				size = teacherDao.countByAggregate(sb.toString(), new Object[]{loginName,id});
			}else{
				size = teacherDao.countByAggregate(sb.toString(), new Object[]{loginName});
			}
		}
		if( null == size || size > 0){
			unique = false;
		}
		return unique;
	}
	
	/**
	 * 验证密码
	 * @author tlliu
	 * @param loginName 用户名
	 * @param password 密码
	 * @return 验证通过返回用户信息
	 * @throws Exception
	 */
	public String login(Teacher teacher,String loginName, String password){
		String msg = null;
		if(null != teacher){
			if(teacher.getPassword().equals(encryptUtil.md5(password))){
				if(this.isValid(teacher)){
					if(this.isValidTime(teacher)){
						teacher.setLastLoginTime(new Date().getTime());
						teacherDao.update(teacher);
						msg = JTZSConstants.ErrorCode.gg000;
					}else{
						msg = JTZSConstants.ErrorCode.ls005;
					}
				}else{
					msg = JTZSConstants.ErrorCode.gg009;
				}
			}else{
				msg = JTZSConstants.ErrorCode.gg005;
			}
		}else{
			msg = JTZSConstants.ErrorCode.gg008;
		}
		return msg;
	}
	
	/**
	 * 获取用户信息
	 * @author tlliu
	 * @param loginName 用户名
	 * @return 返回用户信息
	 */
	@Transactional(readOnly = true)
	public Teacher getByLoginName(String loginName) {
		return teacherDao.findByProperty("loginName", loginName);
	}
	
	/**
	 * 判断帐号是否有效  true 为有效用户
	 * @author tlliu
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isValid(Long id){
		return this.isValid(this.teacherDao.getById(id));
	}
	
	@Transactional(readOnly = true)
	public boolean isValid(Teacher teacher){
		boolean isValid = false;
		if(null != teacher){
			if(JTZSConstants.Status.NORMAL.getIndex() == teacher.getStatus()){//是否被冻结
				isValid = true;
			}
		}
		return isValid;
	}
	
	@Transactional(readOnly = true)
	public boolean isValidTime(Teacher teacher){
		boolean isValid = false;
		if(null != teacher){
			if((null!=teacher.getValidStartTime() && teacher.getValidStartTime() < new Date().getTime()) && 
					(null!=teacher.getValidStopTime() && teacher.getValidStopTime() > new Date().getTime())){
				isValid = true;
			}
		}
		return isValid;
	}
	
	private static SimpleDateFormat dd = new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * 判断老师空闲时间
	 * @param teacher
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isFreeTime(Teacher teacher){
		boolean isFreeTime = false;
		if(null != teacher){
			Calendar c = Calendar.getInstance();
			long time = dateUtils.timeToLong(dd.format(c.getTime()));
			int dd = c.get(GregorianCalendar.DAY_OF_WEEK);
			if(null==teacher.getFreeStartTime() && null==teacher.getFreeStopTime()){
				isFreeTime = isFreeDay(teacher, dd);
			}else if(null!=teacher.getFreeStartTime() && null!=teacher.getFreeStopTime()){
				if(teacher.getFreeStartTime() < teacher.getFreeStopTime()){
					if(teacher.getFreeStartTime() < time && time < teacher.getFreeStopTime()){
						isFreeTime = isFreeDay(teacher, dd);
					}
				}else{
					if(teacher.getFreeStopTime() < time && time < teacher.getFreeStartTime()){
						isFreeTime = isFreeDay(teacher, dd);
					}
				}
				
			}
			
		}
		return isFreeTime;
	}
	
	private boolean isFreeDay(Teacher teacher,int dd){
		boolean result = false;
		switch (dd) {
			case 1:
				result = teacher.getFreeSunday();
				break;
			case 2:
				result = teacher.getFreeMonday();
				break;
			case 3:
				result = teacher.getFreeTuesday();
				break;
			case 4:
				result = teacher.getFreeWednesday();
				break;
			case 5:
				result = teacher.getFreeThursday();
				break;
			case 6:
				result = teacher.getFreeFriday();
				break;
			case 7:
				result = teacher.getFreeSaturday();
				break;
		}
		return result;
	}
}
