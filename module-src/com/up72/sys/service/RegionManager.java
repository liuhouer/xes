/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2011
 */

package com.up72.sys.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;
import com.up72.sys.dao.RegionDao;
import com.up72.sys.model.Region;
import com.up72.sys.vo.query.RegionQuery;

/**
 * 地区业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class RegionManager extends BaseManager<Region,java.lang.Long>{

	private RegionDao regionDao;

	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.regionDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(RegionQuery query) {
		return regionDao.findPage(query);
	}
	
	/**
	 * 获得子级列表
	 * @param parent
	 * @return 
	 */
	public List<Region> getChildrenList(Long parent){
		List<Region> result = null;
		String hsql = null;
		if(null != parent
				&& parent > 0){
			hsql = "from Region where region="+parent;
		}else{
			hsql = "from Region where region=0 or region is null";
		}
		result = this.regionDao.find(hsql, null,0,null);
		return result;
	}
	
	/**
	 * 获得兄弟列表
	 * @param brother
	 * @return
	 */
	public List<Region> getBrotherList(Long brother){
		List<Region> result = null;
		if(null != brother
				&& brother > 0){
			Region region = this.getById(brother);
			if(null != region){
				region = region.getParent();
				result = getChildrenList(null == region?0:region.getId());
			}
		}
		
		return result;
	}
	
	/**
	 * 获得父级列表
	 * @param id
	 * @return 
	 */
	public List<Region> getParentList(Long id,int level){
		List<Region> result = null;
		
		if(null != id && id > 0){
			result = new LinkedList<Region>();
			
			Region region = this.getById(id);
			if(null != region){
				result.add(region);
				
				int _level = 1;
				while((region = region.getParent())!=null){
					if(level > 0
							&& _level >= level){
						break;
					}
					_level ++;
					result.add(region);
				}
			}
		}
		if(null != result
				&& result.size() > 0){
			Collections.reverse(result);
		}
		return result;
	}
	
	/**
	 * 校验节点是否是第几级的节点，level为null，或0则校验是否是叶子节点
	 * @param regionId
	 * @param level 
	 */
	@Transactional(readOnly=true)
	public boolean isLeft(Long regionId){
		boolean result = false;
		
		if(null !=regionId 
				&& regionId > 0){
			List<Region> list = this.getChildrenList(regionId);
			if(null == list
				|| list.size() <=0 ){
				result = true;
			}
		}
		
		return result;
	}
}
