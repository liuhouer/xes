/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.dao.MemberRoleDao;
import com.up72.auth.model.MemberRole;
import com.up72.auth.vo.query.MemberRoleQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;

/**
 * 权限用户与角色多对多关系业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class MemberRoleManager extends BaseManager<MemberRole,java.lang.Long>{

	private MemberRoleDao memberRoleDao;

	public void setMemberRoleDao(MemberRoleDao dao) {
		this.memberRoleDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.memberRoleDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(MemberRoleQuery query) {
		return memberRoleDao.findPage(query);
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public MemberRole getMemberRole(Long memberId) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("memberId", memberId);
		List<MemberRole> list = memberRoleDao.findList(params, 0, null);
		MemberRole memberRole = null;
		if(null!=list && list.size()>0){
			memberRole = list.get(0);
		}
		return memberRole;
	}
	
}
