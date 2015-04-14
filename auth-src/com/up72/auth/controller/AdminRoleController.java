/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */


package com.up72.auth.controller;

import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.common.CommonUtils.stringUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.up72.auth.AuthConstants;
import com.up72.auth.AuthUtils;
import com.up72.auth.annotation.PermissionGroupAnnotation;
import com.up72.auth.member.model.AuthUser;
import com.up72.auth.model.Organization;
import com.up72.auth.model.Permission;
import com.up72.auth.model.PermissionGroup;
import com.up72.auth.model.Product;
import com.up72.auth.model.Role;
import com.up72.auth.model.WorkGroup;
import com.up72.auth.service.OrganizationManager;
import com.up72.auth.service.PermissionGroupManager;
import com.up72.auth.service.PermissionManager;
import com.up72.auth.service.ProductManager;
import com.up72.auth.service.RoleManager;
import com.up72.auth.service.WorkGroupManager;
import com.up72.auth.vo.query.RoleQuery;
import com.up72.exception.ServiceException;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.web.scope.Flash;

/**
 * 权限角色表数据提取跳转
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/auth/role")
@PermissionGroupAnnotation(name="角色",description="")
public class AdminRoleController extends AuthController<Role,java.lang.Long>{
	protected final static String ASSIGN_SUCCESS = "分配成功";
	 
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private RoleManager roleManager;
	@Autowired
	private OrganizationManager organizationManager;
	@Autowired
	private WorkGroupManager workGroupManager;
	@Autowired
	private ProductManager productManager;
	@Autowired
	private PermissionManager permissionManager;
	@Autowired
	private PermissionGroupManager permissionGroupManager;
	
	private final String LIST_ACTION = "redirect:/admin/auth/role";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setRoleManager(RoleManager manager) {
		this.roleManager = manager;
	}
	
	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
	}
	   
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	public void init(ModelMap model) {
		//model.put("now", new java.sql.Timestamp(System.currentTimeMillis()));
	}
	
	/** 列表 
	 * @throws IOException */
	@RequestMapping
	@SuppressWarnings({ "unchecked" })
	public String index(ModelMap model,RoleQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		AuthUser loginAuthUser = this.getAdminLoginMember(request);
		if(null != loginAuthUser && !AuthConstants.authUser.AUTH_USER_SYSTEM.equals(loginAuthUser.getCode())){
			return "redirect:/admin/auth/role/indexSelf?" + AuthConstants.common.AUTH_PERM_ID + "=" + paramUtils.getParameter(request, AuthConstants.common.AUTH_PERM_ID);
		}else{
			Page page = this.roleManager.findPage(query);
			
			model.addAllAttributes(toModelMap(page, query));
			this.addPermissionAttribute(model, request);
	
			this.addShowLabelAttrbite("/admin/auth/role", model, request, response);
			return "/admin/auth/role/index";
		}
	}
	
	/** 登录用户创建的角色列表 */
	@RequestMapping("/indexSelf")
	@SuppressWarnings({ "unchecked" })
	public String indexSelf(ModelMap model,RoleQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		//获取当前登录的用户
		AuthUser loginAuthUser = this.getAdminLoginMember(request);
		query.setCreateUserId(loginAuthUser.getId());
		
		Page page = this.roleManager.findPage(query);
		
		model.addAllAttributes(toModelMap(page, query));
		this.addPermissionAttribute(model, request);

		this.addShowLabelAttrbite("/admin/auth/role", model, request, response);
		return "/admin/auth/role/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Role role = (Role)roleManager.getById(id);
		model.addAttribute("role",role);
		return "/admin/auth/role/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Role role,HttpServletRequest request,HttpServletResponse response) throws Exception {
		AuthUser loginAuthUser = this.getAdminLoginMember(request);
		Long workGroupId = paramUtils.getLongParameter(request, "workGroupId", 0L);
		Long organizationId = paramUtils.getLongParameter(request, "organizationId", 0L);
		String parentType = paramUtils.getParameter(request, "parentType", "");
		WorkGroup workGroup = roleManager.getWorkGroup(workGroupId);
		Organization organization = organizationManager.getById(organizationId);
		
		List<WorkGroup> workGroupList=null;
		if(parentType.equals("org"))
		{
			if(ObjectUtils.isNotEmpty(organization)){
				role.setOrganizationId(organizationId);
				workGroupList=workGroupManager.getWorkGroupList(organizationId);
			}
		}else if(parentType.equals("wg"))
		{
			
			if(ObjectUtils.isNotEmpty(workGroup)){
				role.setWorkGroupId(workGroupId);
				role.setOrganizationId(workGroup.getOrganizationId());
			}
			workGroupList = workGroupManager.getWorkGroupList(workGroup.getOrganizationId());
		}
		
		
		if(null != loginAuthUser){
			role.setCreateUserId(loginAuthUser.getId());
		}
		model.addAttribute("role",role);
		model.addAttribute("workGroupId",workGroupId);
		model.addAttribute("organizationId",organizationId);
		model.addAttribute("parentType",parentType);
		model.addAttribute("organizationList", this.organizationManager.findAll());
		model.addAttribute("workGroupList", workGroupList);
		this.addPermissionAttribute(model, request);
		return "/admin/auth/role/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String create(ModelMap model,@Valid Role role,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/auth/role/new";
		}
		String status = SUCCESS;
		String message = "操作成功";
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try{
			//--------------- 屏蔽部门（用户组）
			if(role != null && (role.getWorkGroupId() == null || role.getWorkGroupId()==0)){
				role.setWorkGroupId(0L); 
			}
			roleManager.save(role);
		}catch (ServiceException e) {
			status = ERROR;
			message = e.getMessage();
		}catch (Exception e) {
			log.error(e);
			status = ERROR;
			message = "系统错误"+e.getMessage();
		}
		jsonMap.put(STATUS, status);
		jsonMap.put(MESSAGE, stringUtil.encode(message));
			
		return jsonUtil.object2Json(jsonMap);
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) throws Exception {
		Role role = (Role)roleManager.getById(id);
		model.addAttribute("role",role);
		
		model.addAttribute("workGroupId",paramUtils.getLongParameter(request, "workGroupId", 0L));
		model.addAttribute("organizationId",paramUtils.getLongParameter(request, "organizationId", 0L));
		model.addAttribute("organizationList", this.organizationManager.findAll());
		model.addAttribute("workGroupList", this.workGroupManager.getWorkGroupList(role.getOrganizationId()));
		this.addPermissionAttribute(model, request);
		return "/admin/auth/role/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) throws Exception {
		Role role = (Role)roleManager.getById(id);
		model.addAttribute("role",role);
		
		model.addAttribute("workGroupId", paramUtils.getIntParameter(request,"workGroupId",0));
		model.addAttribute("organizationId", paramUtils.getIntParameter(request,"organizationId",0));
		model.addAttribute("organizationList", this.organizationManager.findAll());
		model.addAttribute("workGroupList", this.workGroupManager.getWorkGroupList(role.getOrganizationId()));
		this.addPermissionAttribute(model, request);
		return "/admin/auth/role/tab_edit";
	}
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Role role = (Role)roleManager.getById(id);
		model.addAttribute("role",role);
		return "/admin/auth/role/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Role role,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/auth/role/edit";
		}
		
		//--------------- 屏蔽部门（用户组）
		if(role != null && (role.getWorkGroupId() == null || role.getWorkGroupId()==0)){
			role.setWorkGroupId(0L); 
		}
		
		roleManager.update(role);
		Flash.current().success(UPDATE_SUCCESS);
		
		String returnUrl = paramUtils.getParameter(request,"return_url", "");
		if(null == returnUrl || returnUrl.trim().equals("")){
			returnUrl = LIST_ACTION;
		}
		return this.getUrl(returnUrl, request);
	}
	
	@RequestMapping(value="/{id}/updateRole")
	@ResponseBody
	public Integer updateRole(ModelMap model,@PathVariable java.lang.Long id,java.lang.Long productId,@Valid Role role,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Integer result =0;
		if(errors.hasErrors()) {
			return result;
		}
		//--------------- 屏蔽部门（用户组）
		if(role != null && (role.getWorkGroupId() == null || role.getWorkGroupId()==0)){
			role.setWorkGroupId(0L); 
		}
		try{
			roleManager.update(role);
			result =1;
		}catch(Exception e){
			result =0;
		}
		model.addAttribute("productId",productId);
		return result;
	}
	
	@RequestMapping(value="/tabUpdate")
	public String tabUpdate(ModelMap model,@Valid Role role,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			System.out.println("error ...");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			return "/admin/auth/role/edit";
		}
		
		roleManager.update(role);
		Flash.current().success(UPDATE_SUCCESS);
		
		String returnUrl = paramUtils.getParameter(request,"return_url", "");
		if(null == returnUrl || returnUrl.trim().equals("")){
			returnUrl = LIST_ACTION;
		}
		return this.getUrl(returnUrl, request);
	}
	
	/** 删除 
	 * @throws IOException */
	@RequestMapping(value="/{id}/delete")
	@ResponseBody
	public String delete(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) throws IOException {
		roleManager.removeById(id);
		/*Flash.current().success(DELETE_SUCCESS);
		
		String returnUrl = paramUtils.getParameter(request,"return_url", "");
		if(null == returnUrl || returnUrl.trim().equals("")){
			returnUrl = LIST_ACTION;
		}
		
		return returnUrl;*/
		HashMap<String,String> jsonMap = new HashMap<String, String>();
		jsonMap.put("status", "success");
		return jsonUtil.object2Json(jsonMap);
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items,HttpServletRequest request) {
		for(int i = 0; i < items.length; i++) {
			//roleManager.removeById(items[i]);
			roleManager.cascadingDeleteByRoleId(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		
		String returnUrl = paramUtils.getParameter(request,"return_url", "");
		if(null == returnUrl || returnUrl.trim().equals("")){
			returnUrl = LIST_ACTION;
		}
		return returnUrl;
	}
	
	//======================================================================================================
	
	@RequestMapping(value="/organization")
	public String organization(ModelMap model){
		model.addAttribute("orgList",roleManager.getOrganizationList());
		return "/admin/auth/role/organization";
	}
	
	/**
	 * 选择角色所在用户组
	 */
	@RequestMapping(value="/workGroup")
	public String workGroup(ModelMap model,@RequestParam("orgId") Long organizationId, HttpServletRequest request, HttpServletResponse response){
		Long workGroupId = paramUtils.getLongParameter(request, "workGroupId", AuthConstants.DEFAULT_LONG_VALUE);
		List<WorkGroup> workGroupList = roleManager.getWorkGroupList(organizationId);
		model.addAttribute("workGroupList",workGroupList);
		model.addAttribute("workGroupId", workGroupId);
		return "/admin/auth/role/workGroup";
	}
	
	/*
	 * 获得指定用户组权限列表   >>>>>  屏蔽用户组， 获得机构下的权限列表
	 */
	@RequestMapping(value="/{id}/permission")
	public String permission(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) throws IOException{
		AuthUser loginAuthUser = this.getAdminLoginMember(request);
		//当前登录用户不为null并且不是系统管理员则跳转到另一个方法
		if(null != loginAuthUser){
			if(!AuthConstants.authUser.AUTH_USER_SYSTEM.equals(loginAuthUser.getCode())){
				return "redirect:/admin/auth/role/" + id + "/selfPermission?" + AuthConstants.common.AUTH_PERM_ID + "=" + paramUtils.getParameter(request, AuthConstants.common.AUTH_PERM_ID);
			}else{
				Role role = roleManager.getById(id);
				model.addAttribute("role",role);
				
				//如果角色的产品id不为空或不为0，则说明为默认角色
				if(!ObjectUtils.isEmpty(role.getProductCode()) && !role.getProductCode().trim().equals("")){
					model.addAttribute("productList",new Product[]{role.getProduct()});
				}
				//如果角色的产品id为空，或者为0，则正常处理
				else{
					//获得指定角色所在用户组的产品列表
//					WorkGroup workGroup = role.getWorkGroup();
//					if(null != workGroup){
//						model.addAttribute("productList",workGroup.getProductList());
//					} else {
//						model.addAttribute("productList",productManager.findAll());
//					}
					
					//获得指定角色所在机构的产品列表
					model.addAttribute("productList",roleManager.getProductList(id));
				}
				//获得指定角色的权限列表 转成id的json字符串
				List<Integer> perMenuIds = roleManager.getRolePermissionId(id, AuthConstants.PERMISSION_TYPE_MENU);
				model.addAttribute("permMenuIds",jsonUtil.object2Json(perMenuIds));
				
				List<Integer> permOptionIds = roleManager.getRolePermissionId(id, AuthConstants.PERMISSION_TYPE_OPTION);
				model.addAttribute("permOptionIds",jsonUtil.object2Json(permOptionIds));
				
				List<Integer> permTagIds = roleManager.getRolePermissionId(id, AuthConstants.PERMISSION_TYPE_TAB);
				model.addAttribute("permTagIds",jsonUtil.object2Json(permTagIds));
				
				model.addAttribute("workGroupId", paramUtils.getIntParameter(request,"workGroupId",0));
				model.addAttribute("organizationId", paramUtils.getIntParameter(request,"organizationId",0));
				this.addPermissionAttribute(model, request);
			}
		}
		return "/admin/auth/role/permission";
	}
	
	/*
	 * 获得指定用户组权限列表
	 */
	@RequestMapping(value="/{id}/selfPermission")
	public String selfPermission(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) throws IOException{
		AuthUser loginAuthUser = this.getAdminLoginMember(request);
		List loginUserRoleIdsList = loginAuthUser.getRoleIds();
		
		Role role = roleManager.getById(id);
		model.addAttribute("role",role);
		
		List<Permission> permissionList = null;
		List<PermissionGroup> permissionGroupList = null;
		List<Product> productList = null;
		if(null != loginAuthUser 
				&& null != loginAuthUser.getUserName()
				&& !loginAuthUser.getUserName().trim().toLowerCase().equals("admin")){
			String roleIds = AuthUtils.stringUtil.join(loginUserRoleIdsList, ",");
			//权限列表
			permissionList = permissionManager.getPermissionListByRoleIds(roleIds, AuthConstants.PERMISSION_TYPE_MENU, 0);
			//权限组列表
			permissionGroupList = permissionGroupManager.getPermissionGroupListByPerm(permissionList);
			//产品列表
			productList = productManager.getProductListByPermGroup(permissionGroupList);
		}else{
			TreeMap<String,String> orderMap = new TreeMap<String, String>();
			orderMap.put("sortId", "asc");
			//权限列表
			permissionList = permissionManager.findList(null, 0, orderMap);
			//权限组列表
			permissionGroupList = permissionGroupManager.findList(null, 0, orderMap);
			//产品列表
			productList = roleManager.getProductList(id);
		}
		model.addAttribute("permissionList", permissionList);
		model.addAttribute("permissionGroupList", permissionGroupList);
		model.addAttribute("productList", productList);
//		System.out.println(productList);
		
//		permissionManager.getPermissionListByRole(roleId, permType, size);
//		Role role = loginUserRoleList.get(i);
			
//			//如果角色的产品id不为空或不为0，则说明为默认角色
//			if(!ObjectUtils.isEmpty(role.getProductId()) && role.getProductId() > 0L){
//				productList.add(role.getProduct());
//				model.addAttribute("productList",new Product[]{role.getProduct()});
//			}
//			//如果角色的产品id为空，或者为0，则正常处理
//			else{
//				//获得指定角色所在用户组的产品列表
//				WorkGroup workGroup = role.getWorkGroup();
//				if(null != workGroup){
//					model.addAttribute("productList",workGroup.getProductList());
//				}else{
//					model.addAttribute("productList",productManager.findAll());
//				}
//			}
			//获得指定角色的权限列表 转成id的json字符串
			List<Integer> perMenuIds = roleManager.getRolePermissionId(id, AuthConstants.PERMISSION_TYPE_MENU);
			model.addAttribute("permMenuIds",jsonUtil.object2Json(perMenuIds));
			
			List<Integer> permOptionIds = roleManager.getRolePermissionId(id, AuthConstants.PERMISSION_TYPE_OPTION);
			model.addAttribute("permOptionIds",jsonUtil.object2Json(permOptionIds));
			
			List<Integer> permTagIds = roleManager.getRolePermissionId(id, AuthConstants.PERMISSION_TYPE_TAB);
			model.addAttribute("permTagIds",jsonUtil.object2Json(permTagIds));
//			
			model.addAttribute("workGroupId", paramUtils.getIntParameter(request,"workGroupId",0));
			model.addAttribute("organizationId", paramUtils.getIntParameter(request,"organizationId",0));
			this.addPermissionAttribute(model, request);
		return "/admin/auth/role/selfPermission";
	}
	/**
	 * 为角色分配权限
	 * @author wqtan
	 */
	@RequestMapping(value="/{id}/assignPerm")
	public String assignPermission(ModelMap model, @PathVariable java.lang.Long id, HttpServletRequest request){
		//获得角色
		Role role = roleManager.getById(id);

		//分配菜单权限
		long[] menuPermIds = paramUtils.getLongParameters(request, "menuPermIds", AuthConstants.DEFAULT_LONG_VALUE);;
		roleManager.assignPermission(role, menuPermIds,AuthConstants.PERMISSION_TYPE_MENU);
		
		//分配操作权限
		long[] optionPermIds = paramUtils.getLongParameters(request, "optionPermIds", AuthConstants.DEFAULT_LONG_VALUE);;
		roleManager.assignPermission(role, optionPermIds,AuthConstants.PERMISSION_TYPE_OPTION);
		
		//分配Tag权限
		long[] tagPermIds = paramUtils.getLongParameters(request, "tagPermIds", AuthConstants.DEFAULT_LONG_VALUE);;
		roleManager.assignPermission(role, tagPermIds,AuthConstants.PERMISSION_TYPE_TAB);
	
		Flash.current().success(ASSIGN_SUCCESS);
		
		String returnUrl = paramUtils.getParameter(request,"return_url", "");
		if(null == returnUrl || returnUrl.trim().equals("")){
			returnUrl = LIST_ACTION;
		}
		return this.getUrl(returnUrl, request);
	}
	
	
	/**
	 * 新为角色分配权限
	 * @author wqtan
	 */
	@RequestMapping(value="/{id}/assignPermission")
	@ResponseBody
	public Integer assignPerm(ModelMap model, @PathVariable java.lang.Long id,java.lang.Long productId, HttpServletRequest request){
		Integer result =0;
		try{
			Role role = roleManager.getById(id);

			//分配菜单权限
			long[] menuPermIds = paramUtils.getLongParameters(request, "menuPermIds", AuthConstants.DEFAULT_LONG_VALUE);;
			roleManager.assignPermission(role, menuPermIds,AuthConstants.PERMISSION_TYPE_MENU);
			
			//分配操作权限
			long[] optionPermIds = paramUtils.getLongParameters(request, "optionPermIds", AuthConstants.DEFAULT_LONG_VALUE);;
			roleManager.assignPermission(role, optionPermIds,AuthConstants.PERMISSION_TYPE_OPTION);
			
			//分配Tag权限
			long[] tagPermIds = paramUtils.getLongParameters(request, "tagPermIds", AuthConstants.DEFAULT_LONG_VALUE);;
			roleManager.assignPermission(role, tagPermIds,AuthConstants.PERMISSION_TYPE_TAB);
			result =1;
		}catch(Exception e){
			result =0;
		}
		//获得角色
		
		return result;
	}
	
//----------------------------产品相关角色处理开始------------------------------------
	/** 进入新增 */
	@RequestMapping(value="/proNew")
	public String proNew(ModelMap model,Role role,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long workGroupId = paramUtils.getLongParameter(request, "workGroupId", AuthConstants.DEFAULT_LONG_VALUE);
		WorkGroup workGroup = roleManager.getWorkGroup(workGroupId);
		if(ObjectUtils.isNotEmpty(workGroup)){
			role.setOrganizationId(workGroup.getOrganizationId());
		}
		model.addAttribute("role",role);
		
		model.addAttribute("productId",paramUtils.getIntParameter(request, "productId", 0));
		return "/admin/auth/product/role/new";
	}
	
	/** 进入新增 */
	@RequestMapping(value="/{id}/proEdit")
	public String proEdit(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) {
		Role role = (Role)roleManager.getById(id);
		model.addAttribute("role",role);
		
		model.addAttribute("productId",paramUtils.getIntParameter(request, "productId", 0));
		return "/admin/auth/product/role/edit";
	}
	
	/** 产品角色，选项卡编辑 */
	@RequestMapping(value="/{id}/proTabEdit")
	public String proTabEdit(ModelMap model,@PathVariable java.lang.Long id,java.lang.Long productId,HttpServletRequest request) throws Exception {
		Role role = (Role)roleManager.getById(id);
		model.addAttribute("role",role);
		Long orgId = paramUtils.getLongParameter(request, "orgId", 0l);
		model.addAttribute("orgId", orgId);
		model.addAttribute("productId",productId);
		return "/admin/auth/product/role/tab_edit";
	}
	
	/** 产品角色，选项卡显示 */
	@RequestMapping(value="/{id}/proTabShow")
	public String proTabShow(ModelMap model,@PathVariable java.lang.Long id,java.lang.Long productId,HttpServletRequest request) throws Exception {
		Role role = (Role)roleManager.getById(id);
		Long orgId = paramUtils.getLongParameter(request, "orgId", 0l);
		Long AUTH_PERM_ID = paramUtils.getLongParameter(request, "AUTH_PERM_ID", 0l);
		model.addAttribute("orgId", orgId);
		model.addAttribute("role",role);
		model.addAttribute("productId",productId);
		model.addAttribute("AUTH_PERM_ID", AUTH_PERM_ID);
		return "/admin/auth/product/role/tab_show";
	}
	/** 产品角色，选项卡显示 */
	@RequestMapping(value="/{id}/orgTabShow")
	public String orgTabShow(ModelMap model,@PathVariable java.lang.Long id,java.lang.Long productId,HttpServletRequest request) throws Exception {
		String result="/admin/auth/organization/tab_role_show";
		Role role = (Role)roleManager.getById(id);
		Long orgId = paramUtils.getLongParameter(request, "orgId", 0l);
		Long AUTH_PERM_ID = paramUtils.getLongParameter(request, "AUTH_PERM_ID", 0l);
		String parentType = paramUtils.getParameter(request, "parentType", "");
		if(parentType.equals("org"))
		{
			result="/admin/auth/organization/tab_role_show";
		}else if(parentType.equals("wg"))
		{
			result="/admin/auth/workGroup/tab_role_show";
		}
		model.addAttribute("orgId", orgId);
		model.addAttribute("role",role);
		model.addAttribute("productId",productId);
		model.addAttribute("AUTH_PERM_ID", AUTH_PERM_ID);
		return result;
	}
	/*
	 * 产品角色，获得指定用户组权限列表
	 */
	@RequestMapping(value="/{id}/proPermission")
	public String proPermission(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) throws IOException{
		Role role = roleManager.getById(id);
		model.addAttribute("role",role);
		Long orgId = paramUtils.getLongParameter(request, "orgId", 0l);
		model.addAttribute("orgId", orgId);
		//如果角色的产品id不为空或不为0，则说明为默认角色
		if(!ObjectUtils.isEmpty(role.getProductCode()) && !role.getProductCode().trim().equals("")){
			model.addAttribute("productList",new Product[]{role.getProduct()});
		}
		//如果角色的产品id为空，或者为0，则正常处理
		else{
			//获得指定角色所在用户组的产品列表
			WorkGroup workGroup = role.getWorkGroup();
			//model.addAttribute("productList",workGroup.getProductList());
		}
		
		//获得指定角色的权限列表 转成id的json字符串
		List<Integer> perMenuIds = roleManager.getRolePermissionId(id, AuthConstants.PERMISSION_TYPE_MENU);
		model.addAttribute("permMenuIds",jsonUtil.object2Json(perMenuIds));
		
		List<Integer> permOptionIds = roleManager.getRolePermissionId(id, AuthConstants.PERMISSION_TYPE_OPTION);
		model.addAttribute("permOptionIds",jsonUtil.object2Json(permOptionIds));
		
		List<Integer> permTagIds = roleManager.getRolePermissionId(id, AuthConstants.PERMISSION_TYPE_TAB);
		model.addAttribute("permTagIds",jsonUtil.object2Json(permTagIds));
		
		model.addAttribute("productId", paramUtils.getParameter(request,"productId"));
		this.addPermissionAttribute(model, request);
		return "/admin/auth/product/role/permission";
	}
	//----------------------------产品相关角色处理结束------------------------------------
	
	/**
	 * 验证角色名称
	 * @author bxmen
	 */
	@RequestMapping(value="/validateName")
	@ResponseBody
	public boolean validateName(ModelMap model,Role role,@RequestParam("name") java.lang.String name, @RequestParam("workGroupId") java.lang.Long workGroupId, @RequestParam("organizationId") java.lang.Long organizationId) throws Exception{
		if(!ObjectUtils.isNotEmpty(name.trim())){
			return false;
		}
		role.setName(name);
		role.setOrganizationId(organizationId);
		role.setWorkGroupId(workGroupId);
		return roleManager.isUnique(role, "name,workGroupId,organizationId");
	}
	
	/**
	 * 验证产品下的角色名称
	 * @author bxmen
	 */
	@RequestMapping(value="/validateRoleName")
	@ResponseBody
	public boolean validateRoleName(ModelMap model,Role role,@RequestParam("name") java.lang.String name, @RequestParam("productId") java.lang.Long productId) throws Exception{
		if(!ObjectUtils.isNotEmpty(name.trim())){
			return false;
		}
		role.setName(name);
		Product product = productManager.getById(productId);
		
		role.setProductCode(null==product?"":product.getCode());
		return roleManager.isUnique(role, "name,productId");
	}

	@RequestMapping(value="/newProductRole")
	public String newProductRole(ModelMap model,Role role,HttpServletRequest request){
		AuthUser loginAuthUser = this.getAdminLoginMember(request);
		String productCode = paramUtils.getParameter(request, "productCode");
		if(null != loginAuthUser){
			role.setCreateUserId(loginAuthUser.getId());
		}
		role.setProductCode(productCode);
		model.addAttribute("role", role);
		model.addAttribute("productList", productManager.findAll());
		this.addPermissionAttribute(model, request);
		return "/admin/auth/product/role/new";
	}
	
	@RequestMapping(value="/{id}/editProductRole")
	public String editProductRole(ModelMap model,@RequestParam("id") Long id,HttpServletRequest request){
		Role role = this.roleManager.getById(id);
		model.addAttribute("role", role);
		model.addAttribute("productList", productManager.findAll());
		this.addPermissionAttribute(model, request);
		return "/admin/auth/product/role/edit";
	}
	
	/** 更改启用状态
	 * @throws IOException */
	@RequestMapping(value="/changeState")
	@ResponseBody
	public String changeState(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		Long id= paramUtils.getLongParameter(request, "id", 0L);
		Role role = this.roleManager.getById(id);
		String message = "success";
		try{
			int state = paramUtils.getIntParameter(request, "state",2);
			if (AuthConstants.PUBILC_ENABLED_UNENABLE == state) {
				role.setEnabled(AuthConstants.PUBILC_ENABLED_ENABLE);
			}
			if (AuthConstants.PUBILC_ENABLED_ENABLE == state) {
				role.setEnabled(AuthConstants.PUBILC_ENABLED_UNENABLE);
			}
			this.roleManager.update(role);
		}catch(Exception e){
			e.printStackTrace();
			message = "error";
		}
		result.put("message", message);
		return jsonUtil.object2Json(result);
	}
}

