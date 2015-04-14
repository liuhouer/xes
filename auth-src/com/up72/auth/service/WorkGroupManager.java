/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.dao.OrganizationDao;
import com.up72.auth.dao.OrganizationProductDao;
import com.up72.auth.dao.ProductDao;
import com.up72.auth.dao.RoleDao;
import com.up72.auth.dao.RolePermissionDao;
import com.up72.auth.dao.WorkGroupDao;
import com.up72.auth.model.Organization;
import com.up72.auth.model.Product;
import com.up72.auth.model.Role;
import com.up72.auth.model.RolePermission;
import com.up72.auth.model.WorkGroup;
import com.up72.auth.vo.query.OrganizationQuery;
import com.up72.auth.vo.query.WorkGroupQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;

/**
 * 权限工作组（部门）业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class WorkGroupManager extends BaseManager<WorkGroup,java.lang.Long>{

	@Autowired
	private WorkGroupDao workGroupDao;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrganizationProductDao organizationProductDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.workGroupDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(WorkGroupQuery query) {
		return workGroupDao.findPage(query);
	}
	
	
	/**
	 * 获取所有机构列表
	 * @author bxmen
	 * @return 
	 * @summary 
	 */
	@SuppressWarnings("unchecked")
	public List getAllOrganization(){
		return organizationDao.findAll();
	}
	
	/**
	 * 获取所有机构分页列表
	 * @author bxmen
	 */
	@SuppressWarnings("unchecked")
	public Page findAllOrganization(OrganizationQuery query){
		return organizationDao.findPage(query);
	}
	/**
	 * 获取所有产品列表
	 * @author bxmen
	 * @return 
	 * @summary 
	 */
	@SuppressWarnings("unchecked")
	public List getAllProduct(){
		return productDao.findAll();
	}
	
	@SuppressWarnings("unchecked")
	public List findList(HashMap<String,Object> params,Integer num,TreeMap<String,String> orderMap){
		return productDao.findList(params, num, orderMap);
	}
	
	/**
	 * 获得用户组所属产品
	 * @author wqtan
	 */
	public List<String> getProductList(Long id){
		String hsql = "select productCode from WorkGroupProduct where workGroupId=?";
		List<String> list = organizationProductDao.findIntegerList(hsql,new Object[]{id});
		return list;
	}
	
	/**
	 * 给指定用户组分配产品
	 * @author wqtan
	 */
	public void assignProduct(WorkGroup workGroup,String[] productIds){
		if(ObjectUtils.isEmpty(productIds))
		//如果产品列表为空，则清空用户组产品
		{
			clearWorkGroupProduct(workGroup);
		}else
		//如果产品列表不为空，则编辑用户组产品列表
		{
			editWorkGroupProduct(workGroup, productIds);
		}
	}
	
	/**
	 * 清空指定用户组产品
	 */
	protected void clearWorkGroupProduct(WorkGroup workGroup){
		String hsql = "delete from WorkGroupProduct where workGroupId=?";
		organizationProductDao.executeHsql(hsql, workGroup.getId());
	}
	
	/**
	 * 编辑用户组产品列表
	 * @author wqtan
	 */
	protected void editWorkGroupProduct(WorkGroup workGroup,String[] productIds){
		List<Product> list = workGroup.getProductList();
		if(ObjectUtils.isEmpty(list)){
			for(int i=0;i<productIds.length;i++){
				createWorkGroupProduct(workGroup, productIds[i]);
			}
		}else{
			editWorkGroupProduct(workGroup, list, productIds);
		}
	}
	
	protected void editWorkGroupProduct(WorkGroup workGroup,List<Product> list,String[] productIds){
		loop1 : for(int i=0;i<productIds.length;i++){
				for(int j=list.size()-1;j>=0;j--){
					if(list.get(j).getCode().equals(productIds[i]))
					//如果数据库产品列表中含有前台传递的产品，那么从数据库的产品列表种移除该产品，并跳出内层循环
					{
						list.remove(j);
						continue loop1;
					}
				}
				//循环执行完毕
				createWorkGroupProduct(workGroup, productIds[i]);
			}
			//对比完毕，删除原数据库中剩余记录
			if(null!=list && list.size()>0){
				for(int i=0;i<list.size();i++){
					organizationProductDao.executeHsql(
							"delete from WorkGroupProduct where workGroupId=? and productCode=?",
							new Object[]{workGroup.getId(),list.get(i).getCode()});
				}
			}
	}
	
	protected void createWorkGroupProduct(WorkGroup workGroup,String productCode){
//		WorkGroupProduct workGroupProduct = new WorkGroupProduct();
//		workGroupProduct.setProductCode(productCode);
//		workGroupProduct.setWorkGroupId(workGroup.getId());
//		organizationProductDao.save(workGroupProduct);
	}
	
	/**
	 * 获取指定用户组下的角色信息
	 * @author bxmen
	 */
	@Transactional(readOnly=true)
	public List<Role> getRoleByWorkGroupId(Long workGroupId){
		return roleDao.getRoleByWorkGroupId(workGroupId);
	}
	
	/**
	 * 获得机构
	 * @author bxmen
	 */
	@Transactional(readOnly=true)
	public Organization getOrganization(Long organizationId){
		return organizationDao.getById(organizationId);
	}

	public void deleteWorkGroupMemberByGroupId(Long id){
		String sql = "delete from auth_work_group_member where work_group_id = "+id;
		workGroupDao.executeSql(sql);
	}
	
	//-------------------------------------级联删除部门（用户组）开始--------------------------------------
	/**
	 * 删除指定的部门（用户组），及子栏目一并删除
	 * @author bxmen
	 * @param workGroupId 
	 * @summary 
	 */
	public void deleteWorkGroup(Long workGroupId){
		if(null!=workGroupId && workGroupId>0){
			//删除部门（用户组）下的角色
			this.deleteRole(workGroupId);
			
			//删除部门（用户组）
			workGroupDao.deleteById(workGroupId);
		}
	}
	
	/**
	 * 删除部门（用户组）下的角色
	 * @author bxmen
	 * @param workGroupId 
	 * @summary
	 */
	protected void deleteRole(Long workGroupId){
		String hsql = "from Role where 1=1 and workGroupId=?";
		List<Role> list = roleDao.findList(hsql, new Object[]{workGroupId}, 0, null);
		if(null==list || list.size() == 0){
			return ;
		}else{
			for(int i=0;i<list.size();i++){
				//删除角色权限表中与己删除角色的相关信息
				this.deleteRolePermission(list.get(i).getId());
				
				//删除角色
				roleDao.delete(list.get(i));
			}
		}
	}
	
	/**
	 * 删除角色权限表中的跟角色相关的记录
	 * @author bxmen
	 * @param roleId 
	 * @summary 
	 */
	protected void deleteRolePermission(Long roleId) {
		String hsql = "from RolePermission where 1=1 and roleId=?";
		List<RolePermission> list = rolePermissionDao.findList(hsql, new Object[]{roleId}, 0, null);
		if(null==list || list.size() == 0){
			return ;
		}else{
			for(int i=0;i<list.size();i++){
				rolePermissionDao.delete(list.get(i));
			}
		}
	}
	//-------------------------------------级联删除部门（用户组）结束--------------------------------------
	
	/**
	 * 获得指定部门下的工作组
	 * 
	 * @author wqtan
	 */
	public List<WorkGroup> getWorkGroupList(Long organizationId){
		List<WorkGroup> result = null;
		if(null != organizationId && organizationId > 0){
			String hql = "from WorkGroup where 1=1 and organizationId=?";
			TreeMap<String,String> orders = new TreeMap<String, String>();
			orders.put("id", "asc");
			result = this.workGroupDao.find(hql, new Object[]{organizationId},0,orders);
		}
		return result;
	}
	
	/**
	 * 获得用户所属部门
	 * @author wqtan
	 * @param memberId
	 * @return List<WorkGroup>
	 */
	public List<WorkGroup> getMemberWorkGroupList(Long memberId){
		return this.workGroupDao.getMemberWorkGroupList(memberId);
	}

	/**
	 * 根据机构获取部门
	 * @author wqtan
	 */
	public List<WorkGroup> getWorkGroupByOrganizationId(Long organizationId){
		List<WorkGroup> list = workGroupDao.getWorkGroupByOrganizationId(organizationId);
		return list;
	}
	
	public List<WorkGroup> findCategoriesByTree(long id){
		List<WorkGroup> result = new LinkedList<WorkGroup>();
		if(id > 0){
			WorkGroup workGroup = this.getById(id);
			
			if(null != workGroup){
				result.add(workGroup);
			}
		}
		List<WorkGroup> childCats = workGroupDao.findCategoriesByParentAndSite(id);
		if(null != childCats && !childCats.isEmpty()){
			for(int i=0;i<childCats.size();i++){
				List<WorkGroup> temp = findCategoriesByTree(childCats.get(i).getId());
				if(null != temp && !temp.isEmpty()){
					result.addAll(temp);
				}
			}
		}
		
		return result;
	}
	
}
