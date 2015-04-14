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
import com.up72.auth.annotation.PermissionGroupAnnotation;
import com.up72.auth.model.Role;
import com.up72.auth.model.WorkGroup;
import com.up72.auth.service.OrganizationManager;
import com.up72.auth.service.RoleManager;
import com.up72.auth.service.WorkGroupManager;
import com.up72.auth.vo.query.OrganizationQuery;
import com.up72.auth.vo.query.WorkGroupQuery;
import com.up72.exception.ServiceException;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.web.scope.Flash;

/**
 * 权限工作组（部门）数据提取跳转
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/auth/workGroup")
@PermissionGroupAnnotation(name="部门",description="")
public class AdminWorkGroupController extends AuthController<WorkGroup, java.lang.Long> {

	// 默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null;

	private WorkGroupManager workGroupManager;
	@Autowired
	private OrganizationManager organizationManager;	
	@Autowired
	private RoleManager roleManager;
	

	private final String LIST_ACTION = "redirect:/admin/auth/workGroup";

	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 */
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
	public String index(ModelMap model, WorkGroupQuery query,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Page page = this.workGroupManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		
		this.addShowLabelAttrbite("/admin/auth/workGroup", model, request, response);
		return "/admin/auth/workGroup/index";
	}

	/** 显示 */
	@RequestMapping(value = "/{id}")
	public String show(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		WorkGroup workGroup = (WorkGroup) workGroupManager.getById(id);
		model.addAttribute("workGroup", workGroup);
		return "/admin/auth/workGroup/show";
	}

	/** 进入新增 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/new")
	public String _new(ModelMap model, WorkGroup workGroup,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		workGroup.setAddTime(new Date().getTime());
		Long organizationId =paramUtils.getLongParameter(request, "organizationId", 0l);
		Long workGroupId = paramUtils.getLongParameter(request, "workGroupId", 0l);
		model.addAttribute("workGroup", workGroup);
		model.addAttribute("organizationId", organizationId);
		model.addAttribute("workGroupId", workGroupId);
		model.addAttribute("workGroupList",workGroupManager.getWorkGroupList(organizationId));
		model.addAttribute("organizationList",organizationManager.findAll());
		// 全部产品列表
		List productList = workGroupManager.getAllProduct();
		model.addAttribute("productList", productList);
		
		addPermissionAttribute(model, request);
		return "/admin/auth/workGroup/new";
	}

	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String create(ModelMap model, @Valid WorkGroup workGroup, BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (errors.hasErrors()) {
			return "/admin/auth/workGroup/new";
		}
		String status = SUCCESS;
		String message = "操作成功";
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try{
			Integer deptLevel = 1;
			Long parent = paramUtils.getLongParameter(request, "parent",0L);
			if (null != parent && parent > 0) {
				WorkGroup work = workGroupManager.getById(parent);
				deptLevel = work.getDeptLevel()+1;
			}
			workGroup.setParent(parent);
			workGroup.setDeptLevel(deptLevel);
			// 保存用户组
			workGroupManager.save(workGroup);
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{id}/edit")
	public String edit(ModelMap model, @PathVariable
	java.lang.Long id, HttpServletRequest request) throws Exception {
		WorkGroup workGroup = (WorkGroup) workGroupManager.getById(id);
		model.addAttribute("workGroup", workGroup);
		
		model.addAttribute("organizationList",organizationManager.findAll());
		addPermissionAttribute(model, request);

		// 全部产品列表
		List productList = workGroupManager.getAllProduct();
		model.addAttribute("productList", productList);
		// 已经选择的产品列表
		model.addAttribute("productIds", jsonUtil.object2Json(workGroupManager
				.getProductList(id)));
		return "/admin/auth/workGroup/edit";
	}

	/** 选项卡编辑 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{id}/tabEdit")
	public String tabEdit(ModelMap model, @PathVariable
	java.lang.Long id, HttpServletRequest request) throws Exception {
		WorkGroup workGroup = (WorkGroup) workGroupManager.getById(id);
		model.addAttribute("workGroup", workGroup);
		// 全部产品列表
//		List productList = workGroupManager.getAllProduct();
//		model.addAttribute("productList", productList);
//		// 已经选择的产品列表
//		model.addAttribute("productIds", jsonUtil.object2Json(workGroupManager
//				.getProductList(id)));
		//Long organizationId =paramUtils.getLongParameter(request, "organizationId", 0l);
		Long workGroupId = paramUtils.getLongParameter(request, "workGroupId", 0l);
		model.addAttribute("workGroup", workGroup);
		model.addAttribute("organizationId", workGroup.getOrganizationId());
		model.addAttribute("workGroupId", workGroup.getId());
		model.addAttribute("workGroupList",workGroupManager.getWorkGroupList(workGroup.getOrganizationId()));
		model.addAttribute("organizationList",organizationManager.findAll());
		return "/admin/auth/workGroup/tab_edit";
	}

	
	
	/** 选项卡显示 */
	@RequestMapping(value = "/{id}/tabShow")
	public String tabShow(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		WorkGroup workGroup = (WorkGroup) workGroupManager.getById(id);
		model.addAttribute("workGroup", workGroup);
		return "/admin/auth/workGroup/tab_show";
	}
	
	/** 选项卡显示 */
	@RequestMapping(value = "/{id}/view")
	public String view(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		WorkGroup workGroup = (WorkGroup) workGroupManager.getById(id);
		model.addAttribute("workGroup", workGroup);
		return "/admin/auth/workGroup/view";
	}

	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model, @PathVariable Long id, 
			@Valid WorkGroup workGroup, BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (errors.hasErrors()) {
			return "/admin/auth/workGroup/edit";
		}
		String[] productIds = request.getParameterValues("productIds");
		// 更新用户组信息
		workGroupManager.update(workGroup);
		// 分配部门产品
		workGroupManager.assignProduct(workGroup, productIds);
		Flash.current().success(UPDATE_SUCCESS);
		String returnUrl = paramUtils.getParameter(request,"return_url", "");
		if(null == returnUrl || returnUrl.trim().equals("")){
			returnUrl = LIST_ACTION;
		}
		return this.getUrl(returnUrl,request);
	}
	
	@RequestMapping(value="/{id}/updateWorkGroup")
	@ResponseBody
	public Integer updateWorkGroup(ModelMap model, @PathVariable Long id, 
			@Valid WorkGroup workGroup, BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer result = 0;
		try{
			Integer deptLevel = 1;
			if(workGroup.getParent() > 0){
				WorkGroup work = workGroupManager.getById(workGroup.getParent());
				deptLevel = work.getDeptLevel()+1;
			}
			workGroup.setDeptLevel(deptLevel);
			// 更新用户组信息
			workGroupManager.update(workGroup);
			
			result =1;
		}catch(Exception e){
			result =0;
		}
		return result;
	}

	/** 删除 */
	@RequestMapping(value = "/{id}/delete")
	@ResponseBody
	public String delete(ModelMap model, @PathVariable Long id) throws IOException{
		workGroupManager.deleteWorkGroup(id);
		workGroupManager.deleteWorkGroupMemberByGroupId(id);
		
		HashMap<String,String> jsonMap = new HashMap<String, String>();
		jsonMap.put("status", "success");
		return jsonUtil.object2Json(jsonMap);
	}
	
	/**
	 * @author zjliu
	 * @create 2012-12-20
	 *  删除
	 * 
	 */
	@RequestMapping(value = "/{id}/deleteWorkGroup")
	@ResponseBody
	public String deleteWorkGroup(ModelMap model, @PathVariable Long id) throws IOException{
		//workGroupManager.deleteWorkGroup(id);
		//workGroupManager.deleteWorkGroupMemberByGroupId(id);
		List<WorkGroup> workGroupList = workGroupManager.findCategoriesByTree(id);
		for(WorkGroup workGroup : workGroupList){
			workGroupManager.deleteWorkGroup(workGroup.getId());
		}
		HashMap<String,String> jsonMap = new HashMap<String, String>();
		jsonMap.put("status", "success");
		return jsonUtil.object2Json(jsonMap);
	}

	/** 批量删除 */
	@RequestMapping(method = RequestMethod.DELETE)
	public String batchDelete(ModelMap model, @RequestParam("items")
	java.lang.Long[] items, HttpServletRequest request) {
		for (int i = 0; i < items.length; i++) {
			workGroupManager.deleteWorkGroup(items[i]);
			workGroupManager.deleteWorkGroupMemberByGroupId(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);

		String returnUrl = paramUtils.getParameter(request, "return_url", "");
		if (null == returnUrl || returnUrl.trim().equals("")) {
			returnUrl = LIST_ACTION;
		}
		return returnUrl;
	}

	/**
	 * 根据用户组查找指定用户组下的角色列表
	 * 
	 * @author bxmen
	 * @param id
	 *            为 用户组ID
	 */
	@RequestMapping(value = "/{id}/tagRole")
	public String tagRole(ModelMap model, @PathVariable
	Long id, HttpServletRequest request) {
		List<Role> roleList = workGroupManager.getRoleByWorkGroupId(id);
		WorkGroup workGroup = workGroupManager.getById(id);

		model.addAttribute("roleList", roleList);
		model.addAttribute("workGroup", workGroup);
		model.addAttribute("organizationId", paramUtils.getParameter(request,
				"organizationId", ""));
		return "/admin/auth/workGroup/tag_role";
	}

	/**
	 * 获取所有机构
	 * 
	 * @author bxmen
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/allOrganization")
	public String allOrganization(ModelMap model, OrganizationQuery query,
			HttpServletRequest request, HttpServletResponse response) {
		Long organizationId = paramUtils.getLongParameter(request,
				"organizationId", AuthConstants.DEFAULT_LONG_VALUE);
		Page page = workGroupManager.findAllOrganization(query);

		model.addAllAttributes(toModelMap(page, query));
		model.addAttribute("organizationId", organizationId);
		return "/admin/auth/workGroup/organization";
	}

	/**
	 * 验证部门名称
	 * 
	 * @author bxmen
	 */
	@RequestMapping(value = "/validateName")
	@ResponseBody
	public boolean validateName(ModelMap model, WorkGroup workGroup,
			@RequestParam("name")
			java.lang.String name, @RequestParam("organizationId")
			java.lang.Long organizatinId) throws Exception {
		if (!ObjectUtils.isNotEmpty(name.trim())) {
			return false;
		}
		workGroup.setName(name);
		return workGroupManager.isUnique(workGroup, "name,organizationId");
	}

	@RequestMapping(value="/{organizationId}/workGroupJSON")
	public String workGroupJSON(ModelMap model,@PathVariable Long organizationId,HttpServletRequest request){
		model.addAttribute("workGroupList", this.workGroupManager.getWorkGroupList(organizationId));
		return "/admin/auth/workGroup/workGroupJSON";
	}
	
	/**
	 * 获取部门列表
	 * @create zjliu 2012-12-18
	 */
	@RequestMapping(value = "/{id}/workGroupList")
	public String workGroupList(ModelMap model, @PathVariable
			java.lang.Long id, @Valid
					HttpServletRequest request, HttpServletResponse response){
		List<WorkGroup> workGroupList = workGroupManager.findCategoriesByTree(id);
		model.addAttribute("workGroupList", workGroupList);
		return "/admin/auth/workGroup/work_group_list";
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
		params.put("workGroupId", id);
		List<Role> roleList = roleManager.findList(params, 0, null);
		model.addAttribute("roleList", roleList);
		model.addAttribute("orgId", id);
		return "/admin/auth/workGroup/role_list";
	}
	
}
