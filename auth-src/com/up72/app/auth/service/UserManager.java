/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.app.auth.service;

import static com.up72.framework.util.ObjectUtils.isEmpty;
import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.AuthConstants;
import com.up72.auth.bean.PermissionTree;
import com.up72.auth.bean.PermissionTreeManager;
import com.up72.auth.dao.MemberRoleDao;
import com.up72.auth.dao.PermissionDao;
import com.up72.auth.dao.PermissionGroupDao;
import com.up72.auth.dao.ProductDao;
import com.up72.auth.dao.RoleDao;
import com.up72.auth.dao.RolePermissionDao;
import com.up72.auth.dao.WorkGroupDao;
import com.up72.auth.dao.WorkGroupMemberDao;
import com.up72.auth.member.dao.AuthUserDao;
import com.up72.auth.member.model.AuthUser;
import com.up72.auth.model.MemberRole;
import com.up72.auth.model.Permission;
import com.up72.auth.model.PermissionGroup;
import com.up72.auth.model.Product;
import com.up72.auth.model.Role;
import com.up72.auth.model.WorkGroup;
import com.up72.auth.model.WorkGroupMember;
import com.up72.base.UserDetails;
import com.up72.common.CommonConstants;
import com.up72.exception.ServiceException;


/**
 * 用户业务实现
 * 
 * @author jhe
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class UserManager implements IUserManager{
	@Autowired
	private RolePermissionDao rolePermissionDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MemberRoleDao memberRoleDao;
	@Autowired
	private WorkGroupMemberDao workGroupMemberDao;
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private PermissionGroupDao permissionGroupDao;
	@Autowired
	private WorkGroupDao workGroupDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private AuthUserDao userDao;
	
	/**
	 * 根据userid得到名字
	 * @param id
	 * @return
	 */
	public String getNameById(Long id ){
		AuthUser user = userDao.getById(id);
		String name  = null;
		if(user!=null){
			name = user.getUserName();
		}
		return name;
	}
	
	


	/**
	 * @update wqtan 修改web.xml配置为过滤admin，修改默认过滤规则为除掉/admin/login以外的所有地址
	 */
	public boolean isAnonymity(String url, UserDetails userDetails){
		if(isEmpty(url)){
			return true;
		}
		// @update 默认过滤所有admin地址，admin开头则全部需要登录
		if(url.indexOf("/admin/login")!=-1){
			return true;
		}
		
		String hql = "from Permission p where p.enabled = ? and p.url = ?";
		Permission permission = permissionDao.find(hql, new Object []{CommonConstants.PUBILC_ENABLED, url});
		if(permission != null){
			return false;
		}else{
			//将url /member/1/edit 转换为 /member/*/edit
			url = url.replaceAll("\\d+", "*");
			permission = permissionDao.find(hql, new Object []{CommonConstants.PUBILC_ENABLED, url});
			return permission !=null ? false : true;
		}
	}

	public boolean isAccessRights(String url, Long roleId, UserDetails userDetails) {
		if(isEmpty(userDetails) || isEmpty(roleId)){
			return false;
		}
		//权限地址为空 或 为系统管理员
		if(isEmpty(url)){
			return true;
		}
		
		//获取角色下的权限
		boolean result = verifyPermission(roleId, url);
		if(!result){
			url = url.replaceAll("\\d+", "*");
			result = verifyPermission(roleId, url);
		}
		
		return result;
	}
	
	/**
	 * 根据查询条件获取权限数量
	 * @param roleId 角色ID
	 * @param url 权限地址
	 * @return true 为有该权限，反之为false
	 */
	private boolean verifyPermission(Long roleId, String url){
		String sql ="select COUNT(p.code) from auth_role_permission as r LEFT join auth_permission as p on r.PERMISSION_ID = p.ID where  p.ENABLED = ? and r.ROLE_ID= ? and p.URL = ? ";
		List<Object[]> rolePermissionList = rolePermissionDao.findListBySQL(sql, new Object[]{CommonConstants.PUBILC_ENABLED, roleId, url}, 0, null);
		int count = Integer.valueOf(String.valueOf(rolePermissionList.get(0)));
		if(count == 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 获得某一用户所在的部门 
	 * @author bxmen
	 * @param userId
	 * @return 
	 * @summary 
	 */
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public WorkGroup getWorkGroupByUserId(Long userId) {
		WorkGroup result = null;
		
		List<WorkGroupMember> list = workGroupMemberDao.getWorkGroupMember(userId);
		if(null!= list && list.size() > 0){
			result = list.get(0).getWorkGroup();
		}
		
		return result;
	}


	@Transactional(readOnly=true)
	public Map<String, PermissionTree> getMenus(Long roleId, String productCode, UserDetails userDetails) {
		//返回列表： 权限ID、名称、URL、权限组ID
		List<Object[]> pList = null;
		PermissionTreeManager tree = new PermissionTreeManager();
		
		TreeMap<String,String> orderMap = new TreeMap<String, String>();
		orderMap.put("p.SORT", "asc");
		
		//查询条件：角色、产品、菜单类型、是否启用 (按照SORT字段升序)
		String sql = "select p.ID,p.NAME,p.URL,p.PERMISSION_GROUP_ID from auth_role_permission as r LEFT join auth_permission as p on r.PERMISSION_ID = p.ID where r.ROLE_ID= ? and p.product_id =? and p.type = ? and p.ENABLED =?  ";
		if(isSystemUser(userDetails)){
			sql = "SELECT p.ID,p.NAME,p.URL,p.PERMISSION_GROUP_ID FROM auth_permission p where  p.product_id =? and p.type = ? and p.ENABLED =?  ";
			pList = rolePermissionDao.findListBySQL(sql, new Object[]{productCode, AuthConstants.PERMISSION_TYPE_MENU, CommonConstants.PUBILC_ENABLED}, 0, orderMap);
		}else{
			if(isEmpty(roleId) || isEmpty(productCode)){
				return tree.getPermissionTreeMap();
			}else{
				pList = rolePermissionDao.findListBySQL(sql, new Object[]{roleId, productCode,AuthConstants.PERMISSION_TYPE_MENU, CommonConstants.PUBILC_ENABLED}, 0, orderMap);
			}
		}
		
		
		//添加菜单树
		for(Object[] object : pList){
			tree.addPermissionTree(object);
		}
		
		return tree.getPermissionTreeMap();
	}
	
	public List<PermissionGroup> getPermissionGroups(Long roleId, String productCode,UserDetails userDetails){
		if(null == userDetails){
			throw new ServiceException("用户为空");
		}
		
		List<PermissionGroup> result = null;
		
		TreeMap<String,String> orders = new TreeMap<String, String>();
		orders.put("sortId","asc");
		if(null != userDetails){
			if(userDetails.getUserName().toLowerCase().equals("admin")){
				StringBuffer hsql = new StringBuffer("from PermissionGroup pg where pg.productCode=?");
				
				Object[] params = new Object[]{productCode};
				
				result = permissionGroupDao.find(hsql.toString(), params, 0, orders);
			}else{
				StringBuffer hsql = new StringBuffer("from PermissionGroup pg where pg.code in(");
				hsql.append("select p.permissionGroupCode from Permission p where p.id in(");
				hsql.append("select rp.permissionId from RolePermission rp where rp.roleId=?)");
				hsql.append(") and productCode=?");
				
				Object[] params = new Object[]{roleId,productCode};
				
				result = permissionGroupDao.find(hsql.toString(), params, 0, orders);
			}
		}
		return result;
	}
	
	public List<Permission> getPermissions(Long roleId,String permGroupCode,UserDetails userDetails){
		List<Permission> result = null;
		TreeMap<String,String> orders = new TreeMap<String, String>();
		orders.put("sortId","asc");
		if(null != userDetails){
			if(userDetails.getUserName().trim().equals("admin")){
				StringBuffer hsql = new StringBuffer("from Permission p where p.permissionGroupCode=? and type=?");
				
				Object[] params = new Object[]{permGroupCode,new Integer(1)};
				
				result = permissionDao.find(hsql.toString(), params, 0, orders);
			}else{
				// 未处理 hsql
				StringBuffer hsql = new StringBuffer("from Permission p where p.permissionGroupCode=? and p.type=1");
				hsql.append(" and p.id in(");
				hsql.append("select rp.permissionId from RolePermission rp where rp.roleId=?");
				hsql.append(")");
				Object[] params = new Object[]{permGroupCode,roleId};
				
				result = permissionDao.find(hsql.toString(), params, 0, orders);
			}
		}
		return result;
	}
	
	public List<Permission> getTabs(Long roleId) {
		return null;
	}

//	/**
//	 * 根据用户所在部门提取角色
//	 * @author bxmen
//	 * @param userId
//	 * @return 
//	 * @summary 根据用户ID得到所在的用户组，然后根据用户组ID得到用户组所拥有的角色
//	 */
//	@SuppressWarnings("unchecked")
//	public Role getMemberRoleByWorkGroup(Long userId) {
//		String workGroupId = "";
//		
//		List<WorkGroupMember> list = workGroupMemberDao.getWorkGroupIdByMemberId(userId);
//		for(int i=0;i<list.size();i++){
//			workGroupId = workGroupId + "," + list.get(i).getWorkGroupId();
//		}
//		
//		workGroupId = workGroupId.substring(1);
//		return null;
//	}
	
	/**
	 * 是否为系统管理员
	 * @param userDetails
	 * @return 返回 true为系统用户
	 */
	private boolean isSystemUser(UserDetails userDetails){
		if(isNotEmpty(userDetails) && AuthConstants.MEMBER_TYPE_SYSTEM.equals(userDetails.getCode())){
			return true;
		}
		
		return false;
	}

	/**
	 * @author wqtan
	 * @date 2012-03-28
	 */
	@Transactional(readOnly=true)
	public List<Product> getProducts(Long roleId, UserDetails userDetails) {
		List<Product> productList = null;
		TreeMap<String,String> orders = new TreeMap<String, String>();
		orders.put("sortId","asc");
		if(null != userDetails){
			//是否为超级管理员
			if(userDetails.getUserName().trim().equals("admin")){
				return productDao.findList("from Product", null, 0, orders);
			}
			if(isEmpty(roleId)){
				return productList;
			}
			// 通过角色找权限，通过权限找权限组，通过权限组找产品 hsql拼接
			StringBuffer hsql = new StringBuffer("from Product pro where 1=1 and pro.code in(");
			hsql.append("select pg.productCode from PermissionGroup pg where pg.code in(");
			hsql.append("select p.permissionGroupCode from Permission p where p.type=1 and p.id in(");
			hsql.append("select rp.permissionId from RolePermission rp where rp.roleId=?");
			hsql.append(")");
			hsql.append(")");
			hsql.append(")");
			productList = this.productDao.find(hsql.toString(), new Object[]{roleId},0,orders);
		}
		return productList;
		
	}

	@Transactional(readOnly=true)
	public List<Role> getRoles(UserDetails userDetails) {
		return roleDao.getUserRoles(userDetails.getId());
	}

	public void addNewRole(Long userId, Long roleId){
		
	}
	
	/**
	 * 用户部门表中插入数据
	 * @author bxmen
	 */
	public void createWorkGroupMember(WorkGroupMember workGroupMember){
		workGroupMemberDao.save(workGroupMember);
	}
	
	/**
	 * 用户角色表中插入数据
	 * @author bxmen
	 */
	public void createMemberRole(MemberRole memberRole){
		memberRoleDao.save(memberRole);
	}

	/**
	 * 修改用户所在用户组（部门）
	 * @author bxmen
	 */
	public void updateWorkGroupMember(WorkGroupMember workGroupMember){
		workGroupMemberDao.updateByMemberId(workGroupMember);
	}
	
	/**
	 * 根据用户得到WorkGroupMember
	 * @author bxmen
	 */
	@Transactional(readOnly=true)
	public WorkGroupMember getWorkGroupMemberByUserId(Long userId){
		WorkGroupMember result = null;
		
		List<WorkGroupMember> list = workGroupMemberDao.getWorkGroupMember(userId);
		if(null!= list && list.size() > 0){
			result = list.get(0);
		}
		
		return result;
	}
	/**
	 * 修改用户所拥有角色
	 * @author bxmen
	 */
	public void updateMemberRole(MemberRole memberRole){
		memberRoleDao.updateByMemberId(memberRole);
	}
	/**
	 * 根据用户得到MemberRole
	 * @author bxmen
	 */
	@Transactional(readOnly=true)
	public MemberRole getMemberRoleByUserId(Long userId){
		MemberRole result = null;
		
		List<MemberRole> memberRoleList = memberRoleDao.getMemberRole(userId);
		if(!isEmpty(memberRoleList)){
			result = memberRoleList.get(0);
		}
		
		return result;
	}

	/**
	 * 根据workGroupId得WorkGroup用户组
	 * @author bxmen
	 * @param workGroupId
	 */
	@Transactional(readOnly=true)
	public WorkGroup getWorkGroup(Long workGroupId){
		return workGroupDao.getById(workGroupId);
	}
	
	/**
	 * 获得全部用户组（部门）
	 * @author bxmen
	 */
	@Transactional(readOnly=true)
	public List<WorkGroup> getAllWorkGroup(){
		return workGroupDao.findAll();
	}
	
	/**
	 * 根据部门ID得该部门下的角色
	 * @author bxmen
	 */
	@Transactional(readOnly=true)
	public List<Role> getRoleByWorkGroupId(Long workGroupId){
		return roleDao.getRoleByWorkGroupId(workGroupId);
	}
	
	/**
	 * 获得指定用户的角色
	 * @author bxmen
	 */
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<Role> getRoleByMemberId(Long userId){
		return roleDao.getUserRoles(userId);
	}
	
	public Permission getFirstProductPermission(Long roleId, String productCode,UserDetails userDetails){
		List<Permission> permissionList = null;
		
		TreeMap<String,String> orders = new TreeMap<String, String>();
		orders.put("sortId","asc");
		
		if(null != userDetails && userDetails.getUserName().toLowerCase().equals("admin")){
			StringBuffer hsql = new StringBuffer("from Permission p where p.permissionGroupCode in (" +
					"select pg.code from PermissionGroup pg where pg.productCode=? order by pg.sortId asc) " +
					"and p.type=1");
			hsql.append(" and url!='#'");
			
			Object[] params = new Object[]{productCode};
			
			permissionList = permissionDao.find(hsql.toString(), params, 1, orders);
		}else if(null != userDetails){
			StringBuffer hsql = new StringBuffer("from Permission p where p.permissionGroupCode in (");
			hsql.append("select pg.code from PermissionGroup pg where pg.productCode=?");
			hsql.append(") and p.type=1");
			hsql.append(" and p.id in(");
			hsql.append("select rp.permissionId from RolePermission rp where rp.roleId=?");
			hsql.append(")");
			hsql.append(" and url!='#'");
			
			Object[] params = new Object[]{productCode,roleId};
			
			permissionList = permissionDao.find(hsql.toString(), params, 1, orders);
		}
		Permission result = null;
		if(null != permissionList && !permissionList.isEmpty()){
			result = permissionList.get(0);
		}
		return result;
	}
	
	public Permission getFirstPermission(Long roleId, String permissionGroupCode,UserDetails userDetails){
		List<Permission> permissionList = null;
		
		TreeMap<String,String> orders = new TreeMap<String, String>();
		orders.put("id","asc");
		
		if(isSystemUser(userDetails)){
			StringBuffer hsql = new StringBuffer("from Permission p where p.permissionGroupCode=? and p.type=1");
			hsql.append(" and url!='#'");
			
			Object[] params = new Object[]{permissionGroupCode};
			
			permissionList = permissionDao.find(hsql.toString(), params, 1, orders);
		}else{
			StringBuffer hsql = new StringBuffer("from Permission p where p.id in (" +
					"select rp.permissionId from RolePermission rp where rp.roleId=?)");
			hsql.append(" and p.permissionGroupCode=? and p.type=1");
			hsql.append(" and url!='#'");
			
			Object[] params = new Object[]{roleId,permissionGroupCode};
			
			permissionList = permissionDao.find(hsql.toString(), params, 1, orders);
		}
		Permission result = null;
		if(null != permissionList && !permissionList.isEmpty()){
			result = permissionList.get(0);
		}
		return result;
	}
}
