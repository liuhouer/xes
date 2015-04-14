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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.up72.auth.annotation.PermissionGroupAnnotation;
import com.up72.auth.dao.OrganizationDao;
import com.up72.auth.dao.WorkGroupDao;
import com.up72.auth.model.Organization;
import com.up72.auth.model.Product;
import com.up72.auth.model.Role;
import com.up72.auth.model.WorkGroup;
import com.up72.auth.service.OrganizationManager;
import com.up72.auth.service.RoleManager;
import com.up72.auth.service.WorkGroupManager;
import com.up72.auth.vo.query.OrganizationQuery;
import com.up72.exception.ServiceException;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.framework.web.scope.Flash;

/**
 * 权限机构数据提取跳转
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/auth/organization")
@PermissionGroupAnnotation(name = "机构", description = "")
public class AdminOrganizationController extends
		AuthController<Organization, java.lang.Long> {
	// 默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null;

	private OrganizationManager organizationManager;
	
	private WorkGroupManager workGroupManager;
	
	private RoleManager roleManager;

	private final String LIST_ACTION = "redirect:/admin/auth/organization/indexTree";

	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 */
	public void setOrganizationManager(OrganizationManager manager) {
		this.organizationManager = manager;
	}

	public void setWorkGroupManager(WorkGroupManager manager) {
		this.workGroupManager = manager;
	}
	
	public void setRoleManager(RoleManager manager) {
		this.roleManager = manager;
	}
	
	/** binder用于bean属性的设置 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	public void init(ModelMap model) {
		// model.put("now", new java.sql.Timestamp(System.currentTimeMillis()));
	}

	/** 列表 */
	@RequestMapping
	@SuppressWarnings( { "unchecked" })
	public String index(ModelMap model, OrganizationQuery query,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		AuthConstants.PAGE_NUMBER = query.getPageNumber();
		AuthConstants.PAGE_SIZE = query.getPageSize();

		Page page = this.organizationManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));

		this.addShowLabelAttrbite("/admin/auth/organization", model, request,
				response);
		return "/admin/auth/organization/index";
	}

	/** 列表 */
	@RequestMapping(value = "/indexTree")
	@SuppressWarnings( { "unchecked" })
	public String indexTree(ModelMap model, OrganizationQuery query,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		AuthConstants.PAGE_NUMBER = query.getPageNumber();
		AuthConstants.PAGE_SIZE = query.getPageSize();

		Page page = this.organizationManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));

		List<Organization> organizationList = this.organizationManager
				.findAll();
		model.addAttribute("organizationList", organizationList);
		addPermissionAttribute(model, request);

		this.addShowLabelAttrbite("/admin/auth/organization", model, request,
				response);
		return "/admin/auth/organization/indexTree";
	}
	

	/**
	 * 权限dashboard
	 */
	@RequestMapping(value = "/dashboard")
	public String dashboard(ModelMap model, OrganizationQuery query,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		
		return "/admin/auth/organization/dashboard";
	}

	/** 显示 */
	@RequestMapping(value = "/{id}")
	public String show(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		Organization organization = (Organization) organizationManager
				.getById(id);
		model.addAttribute("organization", organization);
		return "/admin/auth/organization/show";
	}

	/** 进入新增 */
	@RequestMapping(value = "/new")
	@SuppressWarnings("unchecked")
	public String _new(ModelMap model, Organization organization,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		model.addAttribute("organization", organization);
		// 全部产品列表
		List<Product> productList = organizationManager.getAllProduct();
		model.addAttribute("productList", productList);

		addPermissionAttribute(model, request);
		return "/admin/auth/organization/new";
	}

	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String create(ModelMap model, @Valid Organization organization, BindingResult errors,HttpServletRequest request, HttpServletResponse response)throws Exception {
		if (errors.hasErrors()) {
			return "/admin/auth/organization/new";
		}
		String status = SUCCESS;
		String message = "操作成功";
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try{
			organizationManager.save(organization);
			// 分配机构产品
			String[] productIds = request.getParameterValues("productIds");
			organizationManager.assignProduct(organization, productIds);
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
	@RequestMapping(value = "/{id}/edit")
	@SuppressWarnings("unchecked")
	public String edit(ModelMap model, @PathVariable
	java.lang.Long id, HttpServletRequest request) throws Exception {
		Organization organization = (Organization) organizationManager
				.getById(id);
		model.addAttribute("organization", organization);
		addPermissionAttribute(model, request);
		// 全部产品列表
		List<Product> productList = organizationManager.getAllProduct();
		model.addAttribute("productList", productList);
		return "/admin/auth/organization/edit";
	}

	/** 选项卡编辑 */
	@RequestMapping(value = "/{id}/tabEdit")
	public String tabEdit(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		Organization organization = (Organization) organizationManager
				.getById(id);
		model.addAttribute("organization", organization);
		// 全部产品列表
		List<Product> productList = organizationManager.getAllProduct();
		model.addAttribute("productList", productList);
		return "/admin/auth/organization/tab_edit";
	}

	/***
	 * @create zjliu 2012-12-18
	 * 选项卡
	 */
	@RequestMapping(value = "/tab")
	public String tab(ModelMap model,HttpServletRequest request){
		String ids = paramUtils.getParameter(request, "id","");
		String[] idChar = ids.split("_");
		if("o".equals(idChar[0])){
			Organization organization = this.organizationManager.getById(Long.parseLong(idChar[1]));
			model.addAttribute("organization",organization);
			return "/admin/auth/organization/organization_tab";
		}else{
			WorkGroup workGroup = workGroupManager.getById(Long.parseLong(idChar[1]));
			model.addAttribute("workGroup",workGroup);
			return "/admin/auth/organization/work_group_tab";
		}
		
	}
	
	/** 选项卡显示 */
	@RequestMapping(value = "/{id}/tabShow")
	public String tabShow(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		Organization organization = (Organization) organizationManager
				.getById(id);
		model.addAttribute("organization", organization);
		return "/admin/auth/organization/tab_show";
	}

	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(ModelMap model, @PathVariable
	java.lang.Long id, @Valid
	Organization organization, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (errors.hasErrors()) {
			return "/admin/auth/organization/edit";
		}
		organizationManager.update(organization);

		// 分配机构产品
		String[] productIds = request.getParameterValues("productIds");
		organizationManager.assignProduct(organization, productIds);

		Flash.current().success(UPDATE_SUCCESS);

		String returnUrl = paramUtils.getParameter(request, "return_url", "");
		if (null == returnUrl || returnUrl.trim().equals("")) {
			returnUrl = LIST_ACTION + "?pageNumber="
					+ AuthConstants.PAGE_NUMBER + "&pageSize="
					+ AuthConstants.PAGE_SIZE;
		}
		return this.getUrl(returnUrl, request);
	}
	/**
	 * 更新机构
	 * @create zjliu 2012-12-18
	 */
	@RequestMapping(value = "/{id}/updateOrganization")
	@ResponseBody
	public Integer updateOrganization(ModelMap model, @PathVariable
			java.lang.Long id, @Valid
			Organization organization, BindingResult errors,
					HttpServletRequest request, HttpServletResponse response){
		Integer result =0;
		try{
			organizationManager.update(organization);
			// 分配机构产品
			String[] productIds = request.getParameterValues("productIds");
			organizationManager.assignProduct(organization, productIds);
			result =1;
		}catch(Exception e){
			result =0;
		}
		return result;
	}

	/**
	 * 获取部门列表
	 * @create zjliu 2012-12-18
	 */
	@RequestMapping(value = "/{id}/workGroupList")
	public String workGroupList(ModelMap model, @PathVariable
			java.lang.Long id, @Valid
					HttpServletRequest request, HttpServletResponse response){
//		HashMap<String, Object> params = new HashMap<String, Object>();
//		params.put("organizationId", id);
		List<WorkGroup> workGroupList = workGroupManager.getWorkGroupList(id);
		model.addAttribute("workGroupList", workGroupList);
		return "/admin/auth/organization/work_group_list";
	}
	
	/**
	 * 获取角色列表
	 * @create zjliu 2012-12-18
	 */
	@RequestMapping(value = "/{id}/roleList")
	public String roleList(ModelMap model, @PathVariable
			java.lang.Long id, @Valid
					HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("organizationId", id);
		List<Role> roleList = roleManager.findList(params, 0, null);
		model.addAttribute("roleList", roleList);
		model.addAttribute("orgId", id);
		model.addAttribute("parentType","org");
		return "/admin/auth/organization/role_list";
	}
	
	
	
	/** 删除 */
	@RequestMapping(value = "/{id}/delete")
	@ResponseBody
	public String delete(ModelMap model, @PathVariable
	java.lang.Long id) throws IOException {
		organizationManager.deleteOrganization(id);
		HashMap<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("status", "success");
		return jsonUtil.object2Json(jsonMap);
	}

	/** 删除 */
	@RequestMapping(value = "/{id}/deleteOrganization")
	@ResponseBody
	public String deleteOrganization(ModelMap model, @PathVariable
	java.lang.Long id) throws IOException {
		organizationManager.deleteOrganization(id);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("organizationId", id);
		//List<WorkGroup> workGroupList = workGroupManager.findList(params, 0, null);
		List<WorkGroup> workGroupList = workGroupManager.getWorkGroupList(id);
		for(WorkGroup workGroup : workGroupList){
			workGroupManager.deleteWorkGroup(workGroup.getId());
		}
		HashMap<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("status", "success");
		return jsonUtil.object2Json(jsonMap);
	}
	
	/** 批量删除 */
	@RequestMapping(method = RequestMethod.DELETE)
	public String batchDelete(ModelMap model, @RequestParam("items")
	java.lang.Long[] items) {
		for (int i = 0; i < items.length; i++) {
			organizationManager.deleteOrganization(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION + "?pageNumber=" + AuthConstants.PAGE_NUMBER
				+ "&pageSize=" + AuthConstants.PAGE_SIZE;
	}

	/**
	 * 根据机构ID获取用户组列表
	 * 
	 * @author bxmen
	 */
	@RequestMapping(value = "/{id}/tabWorkGroup")
	public String tabWorkGroup(ModelMap model, @PathVariable
	Long id) {

		List<WorkGroup> workGroupList = organizationManager
				.getWorkGroupByOrganizationId(id);
		Organization organization = organizationManager.getById(id);

		model.addAttribute("organization", organization);
		model.addAttribute("organizationId", organization.getId());
		model.addAttribute("workGroupList", workGroupList);
		return "/admin/auth/organization/tab_work_group";
	}

	/**
	 * 验证机构名称
	 * 
	 * @author bxmen
	 */
	@RequestMapping(value = "/validateName")
	@ResponseBody
	public boolean validateName(ModelMap model, Organization organization,
			@RequestParam("name")
			java.lang.String name) throws Exception {
		if (!ObjectUtils.isNotEmpty(name.trim())) {
			return false;
		}
		organization.setName(name);
		return organizationManager.isUnique(organization, "name");
	}

	/**
	 * 机构角色树
	 * 
	 * @author wqtan
	 */
	@RequestMapping(value = "/{orgId}/workGroupRoleTree")
	public String workGroupRoleTree(ModelMap model, @PathVariable
	Long orgId) {
		// List<WorkGroup> workGroupList =
		// this.organizationManager.getWorkGroupByOrganizationId(orgId);
		Organization organization = this.organizationManager.getById(orgId);
		model.addAttribute("organization", organization);
		return "/admin/auth/organization/workGroupRoleTree";
	}
	
	/**
	 * 获取树形json树
	 * @author zjliu
	 * @create 2012-12-14 15:21
	 */
	@RequestMapping(value="/nodes")
	public String _getNodesJson(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String ids = paramUtils.getParameter(request, "id","");
		OrganizationDao organizationDao = (OrganizationDao)ApplicationContextHolder.getBean("organizationDao");
		WorkGroupDao workGroupDao = (WorkGroupDao)ApplicationContextHolder.getBean("workGroupDao");
		if(ids == null || "".equals(ids)){
			List<Organization> list = organizationDao.findAll();
			model.addAttribute("itemList",list);
			model.addAttribute("type","o");
		}else{
			String[] idChar = ids.split("_");
			if(idChar[0].equals("w")){
				HashMap<String, Object> params = new  HashMap<String, Object>();
				params.put("parent", Long.parseLong(idChar[1]) );
				List<WorkGroup> list =workGroupDao.findList(params, 0, null);
				model.addAttribute("itemList",list);
				model.addAttribute("type","w");
			}else if(idChar[0].equals("o")){
				HashMap<String, Object> params = new  HashMap<String, Object>();
				params.put("parent", 0L );
				params.put("organizationId", Long.parseLong(idChar[1]));
				List<WorkGroup> list =workGroupDao.findList(params, 0, null);
				model.addAttribute("itemList",list);
				model.addAttribute("type","w");
			}
		}
		return "/admin/auth/organization/nodes_json";
	}
	/***
	 * @author zjliu
	 * @create 2012-12-14 15:21
	 */
	@RequestMapping(value = "/{id}/workGroupView")
	public String workGroupView(ModelMap model, @PathVariable
			Long id){
		WorkGroup workGroup = (WorkGroup) workGroupManager.getById(id);
		model.addAttribute("workGroup", workGroup);
		return "/admin/auth/organization/work_group_view";
	}
	/***
	 * @author zjliu
	 * @create 2012-12-14 15:21
	 */
	@RequestMapping(value = "/{id}/workGroupTabEdit")
	public String workGroupTabEdit(ModelMap model, @PathVariable
			Long id){
		WorkGroup workGroup = (WorkGroup) workGroupManager.getById(id);
		model.addAttribute("workGroup", workGroup);
		return "/admin/auth/organization/work_group_tab_edit";
	}
}
