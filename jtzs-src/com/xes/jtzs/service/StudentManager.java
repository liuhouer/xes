/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import static com.up72.common.CommonUtils.encryptUtil;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.base.*;
import com.up72.framework.page.*;
import com.up72.util.DateUtils;

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
public class StudentManager extends BaseManager<Student, java.lang.Long> {
	@Autowired
	private CommonRuleManager commonRuleManager;
	@Autowired
	private ScoreManager scoreManager;

	private StudentDao studentDao;

	public void setStudentDao(StudentDao dao) {
		this.studentDao = dao;
	}

	@SuppressWarnings( { "unchecked" })
	public EntityDao getEntityDao() {
		return this.studentDao;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings( { "unchecked" })
	public Page findPage(StudentQuery query) {
		return studentDao.findPage(query);
	}

	/**
	 * 修改密码
	 * 
	 * @author tlliu
	 */
	public String resetPwd(String oldPassword, String newPassword, Long id) throws Exception {
		String result = JTZSConstants.ErrorCode.gg500;
		Student student = this.getById(id);
		if (null != student) {
			if (!encryptUtil.md5(oldPassword).equals(student.getPassword())) {
				result = JTZSConstants.ErrorCode.gg005;
			} else if (StringUtils.isEmpty(newPassword)) {
				result = JTZSConstants.ErrorCode.gg006;
			} else if (newPassword.length() < 6 || newPassword.length() > 18) {
				result = JTZSConstants.ErrorCode.gg007;
			} else {
				try {
					student.setPassword(encryptUtil.md5(newPassword));
					this.update(student);
					result = JTZSConstants.ErrorCode.gg000;
				} catch (Exception e) {
				}
			}
		} else {
			result = JTZSConstants.ErrorCode.gg008;
		}
		return result;
	}

	/**
	 * 用户名是否唯一(存在) true 唯一 false 不唯一
	 * 
	 * @param loginName
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isUniqueLoginName(String loginName, Long id) {
		boolean unique = true;
		Long size = null;
		if (StringUtils.isNotBlank(loginName)) {
			loginName = loginName.trim();
			StringBuilder sb = new StringBuilder("select count(id) from Student where loginName=?");
			if (null != id) {
				sb.append(" and id!=?");
				size = studentDao.countByAggregate(sb.toString(), new Object[] { loginName, id });
			} else {
				size = studentDao.countByAggregate(sb.toString(), new Object[] { loginName });
			}
		}
		if (null == size || size > 0) {
			unique = false;
		}
		return unique;
	}

	/**
	 * 验证密码
	 * 
	 * @author tlliu
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return 验证通过返回用户信息
	 * @throws Exception
	 */
	public String login(Student student, String loginName, String password) {
		String msg = null;
		if (null != student) {
			if (student.getPassword().equals(encryptUtil.md5(password))) {
				if (this.isValid(student)) {
					if (null == student.getLastLoginTime() || DateUtils.getDayStart(new Date().getTime(), 0) > DateUtils.getDayStart(student.getLastLoginTime(), 0)) {
						scoreManager.addStudentScore(student.getId(), commonRuleManager.getCacheRuleByType(JTZSConstants.CommonRuleType.StudentEveryDayLogin.getIndex()));
					}
					student.setLastLoginTime(new Date().getTime());
					studentDao.update(student);
					msg = JTZSConstants.ErrorCode.gg000;
				} else {
					msg = JTZSConstants.ErrorCode.gg009;
				}
			} else {
				msg = JTZSConstants.ErrorCode.gg005;
			}
		} else {
			msg = JTZSConstants.ErrorCode.gg008;
		}
		return msg;
	}

	/**
	 * 获取用户信息
	 * 
	 * @author tlliu
	 * @param userName
	 *            用户名
	 * @return 返回用户信息
	 */
	@Transactional(readOnly = true)
	public Student getByLoginName(String loginName) {
		return studentDao.findByProperty("loginName", loginName);
	}

	/**
	 * 判断帐号是否有效 true 为有效用户
	 * 
	 * @author tlliu
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isValid(Long id) {
		return this.isValid(this.studentDao.getById(id));
	}

	@Transactional(readOnly = true)
	public boolean isValid(Student student) {
		boolean isValid = false;
		if (null != student) {
			if (JTZSConstants.Status.NORMAL.getIndex() == student.getStatus()) {// 是否被冻结
				isValid = true;
			}
		}
		return isValid;
	}

	/**
	 * 判断是否完善了个人信息
	 * 
	 * @author
	 * @param name
	 * @return
	 */
	@Transactional(readOnly = true)
	public String isProfiled(String loginName) {
		String result = null;
		Student student = studentDao.find("from Student where loginName=?", new Object[] {loginName});
		if (student != null) {
			if (student.getNickName() != null && null != student.getProvinceId() &&	null != student.getAreaId() && null != student.getCity() && null != student.getGrade() && null != student.getSchool()){
				result = JTZSConstants.PROFILE;
			}
		}
		if(null == result){
			result = JTZSConstants.UNPROFILE;
		}
		return result;
	}

}
