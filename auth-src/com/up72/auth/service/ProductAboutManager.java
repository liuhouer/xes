/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.dao.ProductAboutDao;
import com.up72.auth.model.ProductAbout;
import com.up72.auth.vo.query.ProductAboutQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;
/**
 * InnoDB free: 263168 kB业务处理
 * 
 * @author auth
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class ProductAboutManager extends BaseManager<ProductAbout,java.lang.Long>{

	private ProductAboutDao productAboutDao;

	public void setProductAboutDao(ProductAboutDao dao) {
		this.productAboutDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.productAboutDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(ProductAboutQuery query) {
		return productAboutDao.findPage(query);
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<ProductAbout> getProductAboutByProductId(String productCode) {
		List<ProductAbout> list = null;
		String hql = "from ProductAbout pa where pa.productCode = ?";
		Map<String, String> orders = new HashMap<String,String>();
		orders.put("sortId", "asc");
		list = productAboutDao.findList(hql, new Object[]{productCode}, 0, orders);
		return list;
	}
	
	/**导入或者更新
	 * @author wgf
	 * @param productAboutList导入的所有数据集合 code选择的产品code
	 * @param code
	 */
	public void saveProductAbout(List<ProductAbout> productAboutList,String code){
		List<ProductAbout> oldProductAboutList = this.productAboutDao.getProductAboutByProductCode(code);
		if(null != oldProductAboutList && oldProductAboutList.size() > 0){
			for (int i = 0; i < oldProductAboutList.size(); i++) {
				ProductAbout oldProductAbout = oldProductAboutList.get(i);
				if(null != oldProductAbout){
					this.productAboutDao.delete(oldProductAbout);
				}
			}
		}
		
		if(null != productAboutList && productAboutList.size() > 0){
			for (int j = 0; j < productAboutList.size(); j++) {
				ProductAbout productAbout = productAboutList.get(j);
				if(null != productAbout){
					String productAboutCode = productAbout.getProductCode();
					if(null != productAboutCode){
						if(productAboutCode.equals(code)){
							productAbout.setId(null);
							this.save(productAbout);
						}
					}
				}
				
			}
		}
		
		
	}
	/**
	 * flag 1 代表上移 0代表下移
	 * @param sortId
	 * @param type
	 * @return
	 */
	public ProductAbout getAnotherProductAbout(Integer sortId,int type,String productCode ){
		ProductAbout result = null;
		String hql = "from ProductAbout where 1=1 ";
		Object[] params =null;
		if(null!=productCode && !"".equals(productCode))
		{
			hql +=" and productCode = ? ";
			params=new Object[]{productCode,sortId};
		}else{
			params=new Object[]{sortId};
		}
		
		TreeMap<String,String> orderMap = new TreeMap<String, String>();
		if(type==1){
			hql += " and  sortId < ?  ";
			orderMap.put("sortId", "desc");
		}else{
			hql += " and  sortId > ?  ";
			orderMap.put("sortId", "asc");
		}
		
		List<ProductAbout> list = productAboutDao.findList(hql, params,0,orderMap);
		if(null!=list && list.size()>0){
			result = list.get(0);
		}
		return result;
	}
}
