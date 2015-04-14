/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.dao.OrganizationProductDao;
import com.up72.auth.model.OrganizationProduct;
import com.up72.auth.vo.query.OrganizationProductQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;

/**
 * 部门与产品业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class OrganizationProductManager extends BaseManager<OrganizationProduct,java.lang.Long>{

	@Autowired
	private OrganizationProductDao organizationProductDao;
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.organizationProductDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(OrganizationProductQuery query) {
		return organizationProductDao.findPage(query);
	}
	
}
