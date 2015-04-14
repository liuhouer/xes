/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.dao.MemberPermissionDao;
import com.up72.auth.model.MemberPermission;
import com.up72.auth.vo.query.MemberPermissionQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;

/**
 * 用户与权限表业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class MemberPermissionManager extends BaseManager<MemberPermission,java.lang.Long>{

	private MemberPermissionDao memberPermissionDao;

	public void setMemberPermissionDao(MemberPermissionDao dao) {
		this.memberPermissionDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.memberPermissionDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(MemberPermissionQuery query) {
		return memberPermissionDao.findPage(query);
	}
	
}
