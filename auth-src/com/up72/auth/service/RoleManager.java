/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.service;

import static com.up72.common.CommonUtils.stringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.AuthConstants;
import com.up72.auth.dao.OrganizationDao;
import com.up72.auth.dao.PermissionDao;
import com.up72.auth.dao.ProductDao;
import com.up72.auth.dao.RoleDao;
import com.up72.auth.dao.RolePermissionDao;
import com.up72.auth.dao.WorkGroupDao;
import com.up72.auth.model.Organization;
import com.up72.auth.model.Permission;
import com.up72.auth.model.Product;
import com.up72.auth.model.Role;
import com.up72.auth.model.RolePermission;
import com.up72.auth.model.WorkGroup;
import com.up72.auth.vo.query.RoleQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;

/**
 * 权限角色表业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class RoleManager extends BaseManager<Role,java.lang.Long>{
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private WorkGroupDao workGroupDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private ProductDao productDao;

	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.roleDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(RoleQuery query) {
		return roleDao.findPage(query);
	}
	
	/**
	 * 获得机构列表
	 */
	public List<Organization> getOrganizationList(){
		List<Organization> result = organizationDao.findList("from Organization", null, 0, null);
		return result;
	}
	
	/**
	 * 获得指定机构下的用户组列表
	 */
	public List<WorkGroup> getWorkGroupList(Long organizationId){
		List<WorkGroup> result = workGroupDao.findList("from WorkGroup where organizationId=?",
				new Object[]{organizationId}, 0, null);
		return result;
	}
	
	/**
	 * 获得该角色下的所有权限ID列表
	 * @author wqtan
	 */
	public List<Integer> getRolePermissionId(Long roleId,Integer type){
		return roleDao.getRolePermissionId(roleId, type);
	}
	
	/**
	 * 为指定的角色分配权限
	 * @author wqtan
	 */
	public void assignPermission(Role role,long[] permissionIds,Integer type){
		if(ObjectUtils.isEmpty(permissionIds) || permissionIds[0] == AuthConstants.DEFAULT_LONG_VALUE)
		//如果没有勾选任何权限，则将权限致空
		{
			clearWorkGroupProduct(role,type);
		}
		//如果勾选了权限，则编辑权限
		else
		{
			editWorkGroupProduct(role, type, permissionIds);
		}
	}
	
	/*
	 * 清空指定角色的指定权限
	 */
	protected void clearWorkGroupProduct(Role role,Integer type){
		if(ObjectUtils.isEmpty(type)){
			String hsql = "delete from RolePermission where roleId=?";
			rolePermissionDao.executeHsql(hsql,new Object[]{role.getId()});
		}else{
			clearWorkGroupProductWidthType(role,type);
		}
	}
	
	protected void clearWorkGroupProductWidthType(Role role,Integer type) {
		//获得指定角色的权限ids
		String rolePermHsql = "select permissionId from RolePermission where roleId=?";
		List<Long> rolePermList = rolePermissionDao.findIntegerList(rolePermHsql, new Object[]{role.getId()});
		String rolePermIds = stringUtil.parseListToString(rolePermList);
		//获得指定type的权限ids
		String permTypeids = null;
		if(ObjectUtils.isNotEmpty(rolePermIds) && !rolePermIds.trim().equals("")){
			String permTypeHsql = "select id from Permission where id in("+rolePermIds+") and type=?";
			List<Long> permTypeList = permissionDao.findIntegerList(permTypeHsql, new Object[]{type});
			permTypeids = stringUtil.parseListToString(permTypeList);
		}
		//删除指定角色的指定权限
		if(ObjectUtils.isNotEmpty(permTypeids) && !permTypeids.trim().equals("")){
			String hsql = "delete from RolePermission where permissionId in("+permTypeids+") and roleId=?";
			rolePermissionDao.executeHsql(hsql,new Object[]{role.getId()});
		}
	}
	
	/*
	 * 为角色添加新的权限
	 */
	protected void createRolePermission(Role role,long permissionId){
		RolePermission rolePermission = new RolePermission();
		rolePermission.setOrganizationId(role.getOrganizationId());
		rolePermission.setPermissionId(permissionId);
		rolePermission.setRoleId(role.getId());
		rolePermissionDao.save(rolePermission);
	}
	
	/*
	 *  编辑角色权限
	 */
	protected void editWorkGroupProduct(Role role,Integer type,long[] permissionIds){
		List<Permission> list = role.getPermissionList(type);
		if(ObjectUtils.isEmpty(list)){
			for(int i=0;i<permissionIds.length;i++){
				createRolePermission(role, permissionIds[i]);
			}
		}else{
			editWorkGroupProduct(role, type, list, permissionIds);
		}
	}
	
	/*
	 * 编辑权限
	 */
	protected void editWorkGroupProduct(Role role,Integer type,List<Permission> list,long[] permissionIds){
	
		loop1 : for(int i=0;i<permissionIds.length;i++){
			for(int j=list.size()-1;j>=0;j--){
				if(list.get(j).getId().equals(permissionIds[i]))
				//如果数据库产品列表中含有前台传递的产品，那么从数据库的产品列表种移除该产品，并跳出内层循环
				{
					list.remove(j);
					continue loop1;
				}
			}
			//循环执行完毕
			createRolePermission(role, permissionIds[i]);
		}
		
		//对比完毕，删除原数据库中剩余记录
		if(null!=list && list.size()>0){
			for(int i=0;i<list.size();i++){
				rolePermissionDao.executeHsql(
						"delete from RolePermission where roleId=? and permissionId=?",
						new Object[]{role.getId(),list.get(i).getId()});
			}
		}
	}
	
	/**
	 * 根据用户组ID查询用户组
	 */
	public WorkGroup getWorkGroup(Long workGroupId){
		return workGroupDao.getById(workGroupId);
	}
	
	/**
	 * 级联删除 删除角色 如果该角色下已经分配权限，则权限也删除
	 * @author bxmen
	 * @param roleId 
	 * @summary 
	 */
	public void cascadingDeleteByRoleId(Long roleId){
		//查询该角色下是否存在权限
		String pHql = "from RolePermission where 1=1 and roleId=?";
		
		List<RolePermission> rolePermissionList = rolePermissionDao.findList(pHql, new Object[]{roleId}, 0, null);

		//循环删除权限
		for (RolePermission rolePermission : rolePermissionList) {
			rolePermissionDao.deleteById(rolePermission.getId());
		}
		roleDao.deleteById(roleId);
	}
	
	public List<Role> getMemberRoleList(Long memberId){
		return roleDao.getMemberRoleList(memberId);
	}
	
	/**
	 * 获得指定角色所有产品
	 * @author wqtan
	 * @return List<Product>
	 */
	public List<Product> getProductList(long roleId){
		List<Product> result = new ArrayList<Product>();
		if(roleId > 0){
			Role role = this.roleDao.getById(roleId);
			if(null != role){
				Long organizationId = role.getOrganizationId();
				if(null != organizationId && organizationId > 0){
					StringBuilder hsql = new StringBuilder("from Product p where 1=1 and code in (");
					hsql.append("select productCode from OrganizationProduct op where op.organizationId="+organizationId);
					hsql.append(")");
					
					TreeMap<String,String> orders = new TreeMap<String, String>();
					orders.put("sortId", "asc");
						
					result = this.productDao.find(hsql.toString(), null, 0, orders);
				}
			}
		}
		return result;
	}
}
