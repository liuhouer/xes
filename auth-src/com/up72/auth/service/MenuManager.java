/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.auth.service;

import java.util.List;
import java.util.TreeMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.dao.MenuDao;
import com.up72.auth.model.Menu;
import com.up72.auth.vo.query.MenuQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;
/**
 * 业务处理
 * 
 * @author up72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class MenuManager extends BaseManager<Menu,java.lang.Long>{

	private MenuDao menuDao;

	public void setMenuDao(MenuDao dao) {
		this.menuDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.menuDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(MenuQuery query) {
		return menuDao.findPage(query);
	}
	
	public List<Menu> getChildMenu(Long id){
		StringBuffer hsql = new StringBuffer("from com.up72.auth.model.Menu where 1=1");
		if(null==id || id.equals(0)){
			hsql.append(" and (parentId=0 or parentId is null)");
		}else{
			hsql.append(" and parentId="+id.intValue());
		}
		TreeMap<String,String> orders = new TreeMap<String, String>();
		orders.put("sortId", "asc");
		
		return this.menuDao.find(hsql.toString(), (Object[])null, 0, orders);
	}
}
