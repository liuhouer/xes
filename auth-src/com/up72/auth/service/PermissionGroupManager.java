/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.AuthUtils;
import com.up72.auth.dao.PermissionDao;
import com.up72.auth.dao.PermissionGroupDao;
import com.up72.auth.dao.ProductDao;
import com.up72.auth.model.Permission;
import com.up72.auth.model.PermissionGroup;
import com.up72.auth.model.Product;
import com.up72.auth.vo.query.PermissionGroupQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;

/**
 * 权限分组表业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class PermissionGroupManager extends BaseManager<PermissionGroup,java.lang.Long>{

	@Autowired
	private PermissionGroupDao permissionGroupDao;
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private ProductDao productDao;

	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.permissionGroupDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(PermissionGroupQuery query) {
		return permissionGroupDao.findPage(query);
	}
	
	@SuppressWarnings("unchecked")
	public List getProduct(){
		return productDao.findAll();
	}

	/**
	 * 级联删除，根据权限组ID删除该组及该组下所有的权限
	 * @author bxmen
	 * @param permissionGroupId 
	 */
	public void cascadingDeleteByPerGroupId(java.lang.Long permissionGroupId){
		if(null != permissionGroupId && permissionGroupId > 0){
			PermissionGroup permissionGroup = this.permissionGroupDao.getById(permissionGroupId);
			if(null!=permissionGroup && null!=permissionGroup.getCode() && !permissionGroup.getCode().trim().equals("")){
				//查询permission的HQL语句
				String pHql = "from Permission where 1=1 and permissionGroupCode=?";
				List<Permission> permissionList = permissionDao.findList(pHql, new Object[]{permissionGroup.getCode()}, 0, null);
				for (Permission permission : permissionList) {
					permissionDao.deleteById(permission.getId());
				}
				permissionGroupDao.deleteById(permissionGroupId);
			}
		}
	}
	
	public void batchUpdaterPermissionGroup(String batchItems,Map<String,String> map){
		StringBuffer hsql = new StringBuffer("UPDATE PermissionGroup");
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String column = it.next();
			String value = map.get(column);
			hsql.append(" set "+column+"="+value);
		}
		hsql.append(" where code in ("+batchItems+")");
		permissionGroupDao.executeHsql(hsql.toString());
	}
	
	public List<PermissionGroup> getPermissionGroupList(Long productId){
		List<PermissionGroup> result = null;
		if(null != productId && productId > 0){
			Product product = this.productDao.getById(productId);
			if(null != product && product.getCode()!=null && !product.getCode().trim().equals("")){
				String hsql = "from PermissionGroup where 1=1 and productCode=?";
				TreeMap<String,String> orders = new TreeMap<String, String>();
				orders.put("code", "asc");
				result = this.permissionGroupDao.find(hsql, new Object[]{product.getCode()}, 0, orders);
			}
		}
		return result;
	}
	
	/**
	 * 通过角色和产品码获取权限组
	 * @author wqtan
	 * @create 2012-11-15 15:24
	 * @param roleId 角色id
	 * @param productCode 产品码
	 * @return List<PermissionGroup>
	 */
	public List<PermissionGroup> getPermissionGroupList(Long roleId,String productCode){
		StringBuffer hsql = new StringBuffer("from PermissionGroup pg where pg.code in(");
		hsql.append("select permissionGroupCode from Permission p where p.id in(");
		hsql.append("select rp.permissionId from RolePermission rp from rp.roleId=?");
		hsql.append(")");
		hsql.append(") and pg.productCode=?");
		
		TreeMap<String,String> orders = new TreeMap<String, String>();
		orders.put("id", "asc");
		
		return this.permissionGroupDao.find(hsql.toString(), new Object[]{roleId,productCode}, 0, orders);
	}
	
	public List<PermissionGroup> getPermissionGroupList(String productCode){
		List<PermissionGroup> result = null;
		if(productCode!=null && !productCode.trim().equals("")){
			String hsql = "from PermissionGroup where 1=1 and productCode = ?";
			TreeMap<String,String> orders = new TreeMap<String, String>();
			orders.put("code", "asc");
			result = this.permissionGroupDao.find(hsql, new Object[]{productCode}, 0, orders);
		}
		return result;
	}
	
	/**
	 * 根据权限IDS获取权限组列表
	 */
	@SuppressWarnings("unchecked")
	public List<PermissionGroup> getPermissionGroupListByPerm(List<Permission> permissionList){
		List<PermissionGroup> result = null;
		List list = new LinkedList();
		if(null != permissionList){
			for (int i = 0; i < permissionList.size(); i++) {
				list.add(permissionList.get(i).getPermissionGroupCode());
			}
			Object[] idsObject = AuthUtils.stringUtil.wipeRepeatList(list);
			String permGroupIds = AuthUtils.stringUtil.join(idsObject, ",");
			if(ObjectUtils.isNotEmpty(permGroupIds)){
				StringBuffer hsql = new StringBuffer("from PermissionGroup pg where 1=1");
				hsql.append(" and pg.code in(" + permGroupIds + ")");
				
				TreeMap<String,String> orders = new TreeMap<String, String>();
				orders.put("sortId", "asc");
				result = this.permissionGroupDao.find(hsql.toString(), null, 0, orders);
			}
		}
		return result;
	}
	
	public PermissionGroup getPermissionGroupByCode(String code){
		return this.permissionGroupDao.getPermissionGroupByCode(code);
	}
	/**导入或者更新
	 * @author wgf
	 * @param permissionGroupList导入的所有数据集合 code选择的产品code
	 * @param code
	 */
	public void savePermissionGroup(List<PermissionGroup> permissionGroupList,String code){
		List<PermissionGroup> oldPermissionGroupList = this.getPermissionGroupList(code);
		if(null != oldPermissionGroupList && oldPermissionGroupList.size() > 0){
			for (int i = 0; i < oldPermissionGroupList.size(); i++) {
				PermissionGroup oldPermissionGroup = oldPermissionGroupList.get(i);
				if(null != oldPermissionGroup){
					this.permissionDao.delete(oldPermissionGroup);
				}
			}
		}
		if(null != permissionGroupList && permissionGroupList.size() > 0){
			for (int j = 0; j < permissionGroupList.size(); j++) {
				PermissionGroup permissionGroup = permissionGroupList.get(j);
				if(null != permissionGroup){
					String permissionGroupProductCode = permissionGroup.getProductCode();
					if(null != permissionGroupProductCode){
						if(permissionGroupProductCode.equals(code)){
							permissionGroup.setId(null);
							this.save(permissionGroup);
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
	public PermissionGroup getAnotherPermissionGroup(Long sortId,String code,int type){
		PermissionGroup result = null;
		String hql = "";
		if(type==1){
			hql = "from PermissionGroup where sortId < ?  and  productCode = ? order by sortId desc";
		}else{
			hql = "from PermissionGroup where sortId > ?  and  productCode = ? order by sortId asc";
		}
		TreeMap<String,String> orderMap = new TreeMap<String, String>();
		orderMap.put("sortId", "asc");
		List<PermissionGroup> list = permissionGroupDao.findList(hql, new Object[]{sortId,code},0,orderMap);
		if(null!=list && list.size()>0){
			result = list.get(0);
		}
		return result;
	}
}
