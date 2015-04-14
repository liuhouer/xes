/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.dao.RolePermissionDao;
import com.up72.auth.model.RolePermission;
import com.up72.auth.vo.query.RolePermissionQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;

/**
 * 权限与角色多对多关系业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class RolePermissionManager extends BaseManager<RolePermission,java.lang.Long>{

	private RolePermissionDao rolePermissionDao;

	public void setRolePermissionDao(RolePermissionDao dao) {
		this.rolePermissionDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.rolePermissionDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(RolePermissionQuery query) {
		return rolePermissionDao.findPage(query);
	}
	
}
