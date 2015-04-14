/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
import com.up72.auth.model.OrganizationProduct;
import com.up72.auth.model.Permission;
import com.up72.auth.model.Product;
import com.up72.auth.model.Role;
import com.up72.auth.model.RolePermission;
import com.up72.auth.model.WorkGroup;
import com.up72.auth.vo.query.OrganizationQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;

/**
 * 权限机构业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class OrganizationManager extends
		BaseManager<Organization, java.lang.Long> {

	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private WorkGroupDao workGroupDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;

	@Autowired
	private OrganizationProductDao organizationProductDao;

	@Autowired
	private ProductDao productDao;

	@SuppressWarnings( { "unchecked" })
	public EntityDao getEntityDao() {
		return this.organizationDao;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings( { "unchecked" })
	public Page findPage(OrganizationQuery query) {
		return organizationDao.findPage(query);
	}

	/**
	 * 根据 机构ID 获取 用户组
	 * 
	 * @author bxmen
	 */
	@Transactional(readOnly = true)
	public List<WorkGroup> getWorkGroupByOrganizationId(Long organizationId) {
		return workGroupDao.getWorkGroupByOrganizationId(organizationId);
	}

	// -----------------------级联删除机构下与机构有关联信息
	// 开始-----------------------------------
	/**
	 * 删除指定的机构，删除该机构的同时还会删除机构下的部门，及部门下的角色，以及用户与部门的关联
	 * 
	 * @param id
	 */
	public void deleteOrganization(Long id) {
		if (null != id && id > 0) {
			// 删除部门
			this.deleteWorkGroup(id);
			// 删除机构
			organizationDao.deleteById(id);
		}
	}

	/**
	 * 删除机构下的部门（用户组）
	 * 
	 * @author bxmen
	 * @param organizationId
	 * @summary
	 */
	protected void deleteWorkGroup(Long organizationId) {
		String hsql = "from WorkGroup where 1=1 and organizationId=?";

		List<WorkGroup> list = workGroupDao.findList(hsql,
				new Object[] { organizationId }, 0, null);
		if (null == list || list.size() == 0) {
			return;
		} else {
			for (int i = 0; i < list.size(); i++) {
				// 删除部门（用户组）下的角色
				this.deleteRole(list.get(i).getId());

				// 删除部门（用户组）
				workGroupDao.delete(list.get(i));
			}
		}
	}

	/**
	 * 删除部门（用户组）下的角色
	 * 
	 * @author bxmen
	 * @param workGroupId
	 * @summary
	 */
	protected void deleteRole(Long workGroupId) {
		String hsql = "from Role where 1=1 and workGroupId=?";
		List<Role> list = roleDao.findList(hsql, new Object[] { workGroupId },
				0, null);
		if (null == list || list.size() == 0) {
			return;
		} else {
			for (int i = 0; i < list.size(); i++) {
				// 删除角色权限表中与己删除角色的相关信息
				this.deleteRolePermission(list.get(i).getId());

				// 删除角色
				roleDao.delete(list.get(i));
			}
		}
	}

	/**
	 * 删除角色权限表中的跟角色相关的记录
	 * 
	 * @author bxmen
	 * @param roleId
	 * @summary
	 */
	protected void deleteRolePermission(Long roleId) {
		String hsql = "from RolePermission where 1=1 and roleId=?";
		List<RolePermission> list = rolePermissionDao.findList(hsql,
				new Object[] { roleId }, 0, null);
		if (null == list || list.size() == 0) {
			return;
		} else {
			for (int i = 0; i < list.size(); i++) {
				rolePermissionDao.delete(list.get(i));
			}
		}
	}

	// -----------------------级联删除机构下与机构有关联信息
	// 结束-----------------------------------

	public Organization getMemberOrganization(Long memberId) {
		Organization result = null;
		if (null != memberId && memberId > 0) {
			StringBuffer hsql = new StringBuffer(
					"from Organization o where 1=1");
			hsql.append(" and o.id in(");
			hsql.append("select w.organizationId from WorkGroup w");
			hsql
					.append(" where w.id in (select wm.workGroupId from WorkGroupMember wm where wm.memberId=?)");
			hsql.append("))");
			List<Organization> list = this.organizationDao.find(
					hsql.toString(), new Object[] { memberId }, 0, null);
			result = null == list || list.isEmpty() ? null : list.get(0);
		}
		return result;
	}

	/**
	 * 获取指定机构下的角色信息
	 * 
	 */
	@Transactional(readOnly = true)
	public List<Role> getRoleByOrganizationId(Long organizationId) {
		return roleDao.getRoleByOrganizationId(organizationId);
	}

	/**
	 * 获取所有产品列表
	 * 
	 * @return
	 * @summary
	 */
	@SuppressWarnings("unchecked")
	public List getAllProduct() {
		return productDao.findAll();
	}

	/**
	 * 给指定机构分配产品
	 * 
	 * @author wqtan
	 */
	public void assignProduct(Organization organization, String[] productIds) {
		String[] productIdsSub=null;
		if (ObjectUtils.isEmpty(productIds))
		// 如果产品列表为空，则清空机构产品
		{
			clearOrganizationProduct(organization);
		} else
		// 如果产品列表不为空，则编辑机构产品列表
		{
			editOrganizationProduct(organization, productIds);
		}

		//updateRoleByProduct(organization, productIds);
	}

	/**
	 * 修改所选的产品下角色 也属于该机构(但是需要复制出单独角色放到该机构)
	 */
	protected void updateRoleByProduct(Organization organization,
			String[] productIds) {
		if (productIds != null && productIds.length > 0) {
			for (int i = 0; i < productIds.length; i++) {
				String hql = "from Role r where r.productCode=?";
				List<Role> roleList = (List<Role>) roleDao.findList(hql,
						new Object[] { productIds[i] });
				if (roleList != null && roleList.size() > 0) {
					for (Role role : roleList) {
						Role tmpRole=new Role();
						BeanUtils.copyProperties(role, tmpRole);
						tmpRole.setId(null);
						tmpRole.setOrganizationId(organization.getId());
						tmpRole.setProductCode("0");
						roleDao.save(tmpRole);
					}
				}
			}
		}
	}
	
	protected void updateRoleByProduct(Organization organization,
			String productCode) {
		if ( null != productCode && !("").equals(productCode)) {
			String hql = "from Role r where r.productCode=?";
			List<Role> roleList = (List<Role>) roleDao.findList(hql,
					new Object[] {  productCode  });
			if (roleList != null && roleList.size() > 0) {
				for (Role role : roleList) {
					Role tmpRole=new Role();
					BeanUtils.copyProperties(role, tmpRole);
					tmpRole.setId(null);
					tmpRole.setOrganizationId(organization.getId());
					tmpRole.setProductCode("0");
					roleDao.save(tmpRole);
					
					List<Permission> permissionsList = role.getPermissionList(0);
					if(!ObjectUtils.isEmpty(permissionsList)){
						for(Permission perm:permissionsList){
							createRolePermission(tmpRole, perm.getId());
						}
					}
					
				}
			}
		}
	}

	/**
	 * 清空指定机构产品
	 */
	protected void clearOrganizationProduct(Organization organization) {
		String hsql = "delete from OrganizationProduct where organizationId=?";
		organizationProductDao.executeHsql(hsql, organization.getId());
	}

	/**
	 * 编辑机构产品列表
	 * 
	 * @author wqtan
	 */
	protected void editOrganizationProduct(Organization organization,
			String[] productIds) {
		List<Product> list = organization.getProductList();
		if (ObjectUtils.isEmpty(list)) {
			for (int i = 0; i < productIds.length; i++) {
				createOrganizationProduct(organization, productIds[i]);
				//把产品的角色复制到机构下
				updateRoleByProduct(organization,productIds[i]);
			}
		} else {
			editOrganizationProduct(organization, list, productIds);
		}
	}

	protected void editOrganizationProduct(Organization organization,
			List<Product> list, String[] productIds) {
		loop1: for (int i = 0; i < productIds.length; i++) {
			for (int j = list.size() - 1; j >= 0; j--) {
				if (list.get(j).getCode().equals(productIds[i]))
				// 如果数据库产品列表中含有前台传递的产品，那么从数据库的产品列表种移除该产品，并跳出内层循环
				{
					list.remove(j);
					continue loop1;
				}
			}
			// 循环执行完毕
			createOrganizationProduct(organization, productIds[i]);
			updateRoleByProduct(organization,productIds[i]);
		}
		// 对比完毕，删除原数据库中剩余记录
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				organizationProductDao
						.executeHsql(
								"delete from OrganizationProduct where organizationId=? and productCode=?",
								new Object[] { organization.getId(),
										list.get(i).getCode() });
			}
		}
	}

	protected void createOrganizationProduct(Organization organization,
			String productCode) {
		OrganizationProduct organizationProduct = new OrganizationProduct();
		organizationProduct.setProductCode(productCode);
		organizationProduct.setOrganizationId(organization.getId());
		organizationProductDao.save(organizationProduct);
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
}
