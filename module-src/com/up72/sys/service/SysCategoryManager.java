/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;
import com.up72.sys.dao.SysCategoryDao;
import com.up72.sys.model.SysCategory;
import com.up72.sys.vo.query.SysCategoryQuery;
/**
 * 业务处理
 * 
 * @author sys
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class SysCategoryManager extends BaseManager<SysCategory,java.lang.Long>{

	private SysCategoryDao sysCategoryDao;

	public void setSysCategoryDao(SysCategoryDao dao) {
		this.sysCategoryDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.sysCategoryDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(SysCategoryQuery query) {
		return sysCategoryDao.findPage(query);
	}
	
	public List<SysCategory> getCategoriesByParent(String parentId) {
		return sysCategoryDao.findsysCategoryByParent(parentId);
	}
	
	/**
	 * @author wqtan
	 * @author 2012.4.7zwguan
	 */
	public void deleteCategories(String[] guids) {
		
			if(null != guids && guids.length > 0){
				for(int i=0;i<guids.length;i++){
					
					this.deleteCategory(guids[i]);
					
				}
			}
	}
	
	public void deleteCategory(String guids) {
		if(null != guids && !"".equals(guids)){
			List<SysCategory> list = sysCategoryDao.findCategoriesByTree(guids);
			if(null != list && !list.isEmpty()){
				for(int i=list.size()-1;i>=0;i--){
					this.removeById(list.get(i).getId());
					list.remove(i);
				  }
				
			
			}
		}
	}
}
