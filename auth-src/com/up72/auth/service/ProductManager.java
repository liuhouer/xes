/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.AuthUtils;
import com.up72.auth.dao.PermissionDao;
import com.up72.auth.dao.PermissionGroupDao;
import com.up72.auth.dao.ProductAboutDao;
import com.up72.auth.dao.ProductDao;
import com.up72.auth.model.Permission;
import com.up72.auth.model.PermissionGroup;
import com.up72.auth.model.Product;
import com.up72.auth.vo.query.ProductQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;

/**
 * 产品业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class ProductManager extends BaseManager<Product,java.lang.Long>{

	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductAboutDao productAboutDao;
	@Autowired
	private PermissionGroupDao permissionGroupDao;
	@Autowired
	private PermissionDao permissionDao;
	
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.productDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(ProductQuery query) {
		return productDao.findPage(query);
	}
	
	//---------------------------级联删除 产品 开始-------------------------
	/**
	 * 级联删除 产品下的权限，角色 全部删除
	 * @author bxmen 
	 * @summary
	 */
	public void cascadingDeleteByProductId(java.lang.Long productId){
		if(productId!=null && productId>0){
			Product product = this.getById(productId);
			if(null != product){
				this.deletePermisstionGroup(product.getCode());
				productDao.delete(product);
			}
		}
	}
	
	/**
	 * 删除产品下的权限组
	 * @author bxmen
	 * @param productId 
	 */
	protected void deletePermisstionGroup(String productCode) {
		//查询permissionGroup的HQL语句
		String hql = "from PermissionGroup where 1=1 and productCode=?";
		//获取PermissionGroup列表
		List<PermissionGroup> permissionGroupList = permissionGroupDao.findList(hql, new Object[]{productCode}, 0, null);
		
		for (PermissionGroup permissionGroup : permissionGroupList) {
			this.deletePermisstion(permissionGroup.getCode(), productCode);
			permissionGroupDao.deleteById(permissionGroup.getId());
		}
	}
	
	/**
	 * 删除权限组下的权限
	 * @author bxmen
	 */
	protected void deletePermisstion(String permissionGroupCode, String productCode) {
		//查询permission的HQL语句
		String hql = "from Permission where 1=1 and permissionGroupCode=? and productCode=?";
		List<Permission> permissionList = permissionDao.findList(hql, new Object[]{permissionGroupCode, productCode}, 0, null);
		for (Permission permission : permissionList) {
			permissionDao.deleteById(permission.getId());
		}
	}
	//---------------------------级联删除 产品 结束-------------------------
	
	@SuppressWarnings("unchecked")
	public List<Product> getProductListByPermGroup(List<PermissionGroup> permGroupList){
		List<Product> result = null;
		List list = new LinkedList();
		if(null != permGroupList){
			for (int i = 0; i < permGroupList.size(); i++) {
				list.add(permGroupList.get(i).getProductCode());
			}
			Object[] idsObject = AuthUtils.stringUtil.wipeRepeatList(list);
			String productIds = AuthUtils.stringUtil.join(idsObject, ",");
			if(ObjectUtils.isNotEmpty(productIds)){
				StringBuffer hsql = new StringBuffer("from Product p where 1=1");
				hsql.append(" and p.code in(" + productIds + ")");
				
				TreeMap<String,String> orders = new TreeMap<String, String>();
				orders.put("sortId", "asc");
				result = this.productDao.find(hsql.toString(), null, 0, orders);
			}
		}
		return result;
	}
	
	public Product getProductByCode(String code){
		return this.productDao.getProductByCode(code);
	}
	
	/**
	 * 根据productCode数组获得Product集合
	 * @author wgf
	 * @param productCodes
	 * @return
	 */
	public List<Product> getProductByCodes(String[] productCodes){
		List<Product> result = new ArrayList<Product>();
		if(null != productCodes && productCodes.length > 0){
			for (int i = 0; i < productCodes.length; i++) {
				String productCode = productCodes[i];
				Product p = this.getProductByCode(productCode);
				result.add(p);
			}
			
		}
		return result;
	}
	/**导入或者更新
	 * @author wgf
	 * @param productList导入的所有数据集合 code选择的产品code
	 * @param code
	 */
	public void saveProduct(List<Product> productList,String code){
		Product oldProduct = this.getProductByCode(code);
		if(null != oldProduct){
			this.productDao.delete(oldProduct);
		}
		if(null != productList && productList.size() > 0){
			for (int j = 0; j < productList.size(); j++) {
				Product product = productList.get(j);
				if(null != product){
					String productCode = product.getCode();
					if(null != productCode){
						if(productCode.equals(code)){
							product.setId(null);
							this.save(product);
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
	public Product getAnotherProduct(Long sortId,int type){
		Product result = null;
		String hql = "";
		if(type==1){
			hql = "from Product where sortId < ? order by sortId desc";
		}else{
			hql = "from Product where sortId > ? order by sortId asc";
		}
		TreeMap<String,String> orderMap = new TreeMap<String, String>();
		orderMap.put("sortId", "asc");
		List<Product> list = productDao.findList(hql, new Object[]{sortId},0,orderMap);
		if(null!=list && list.size()>0){
			result = list.get(0);
		}
		return result;
	}
}
