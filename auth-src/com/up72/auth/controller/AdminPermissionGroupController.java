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
import com.up72.auth.bean.PermissionCodeHelper;
import com.up72.auth.model.PermissionGroup;
import com.up72.auth.model.Product;
import com.up72.auth.service.PermissionGroupManager;
import com.up72.auth.vo.query.PermissionGroupQuery;
import com.up72.exception.ServiceException;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;

/**
 * 权限分组表数据提取跳转
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/auth/permissionGroup")
@PermissionGroupAnnotation(name="权限组",description="")
public class AdminPermissionGroupController extends AuthController<PermissionGroup,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private PermissionGroupManager permissionGroupManager;
	
	private final String LIST_ACTION = "redirect:/admin/auth/permissionGroup";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setPermissionGroupManager(PermissionGroupManager manager) {
		this.permissionGroupManager = manager;
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
	
	/** 列表 */
	@RequestMapping
	@SuppressWarnings({ "unchecked" })
	public String index(ModelMap model,PermissionGroupQuery query,HttpServletRequest request,HttpServletResponse response) {
		Page page = this.permissionGroupManager.findPage(query);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/auth/permissionGroup/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		PermissionGroup permissionGroup = (PermissionGroup)permissionGroupManager.getById(id);
		model.addAttribute("permissionGroup",permissionGroup);
		return "/admin/auth/permissionGroup/show";
	}

	/** 进入新增 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/new")
	public String _new(ModelMap model,PermissionGroup permissionGroup,HttpServletRequest request,HttpServletResponse response) throws Exception {
		List productList = permissionGroupManager.getProduct();
		Long productId = paramUtils.getLongParameter(request, "productId", AuthConstants.DEFAULT_LONG_VALUE);
		Long permissionGroupId = paramUtils.getLongParameter(request, "permissionGroupId", AuthConstants.DEFAULT_LONG_VALUE);
		model.addAttribute("permissionGroup",permissionGroup);
		model.addAttribute("productList",productList);
		model.addAttribute("productId", productId);
		model.addAttribute("permissionGroupId", permissionGroupId);
		addPermissionAttribute(model, request);
		return "/admin/auth/permissionGroup/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String create(ModelMap model,@Valid PermissionGroup permissionGroup,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String status = SUCCESS;
		String message = "操作成功";
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		if(errors.hasErrors()) {
			return  "/admin/auth/permissionGroup/new";
		}
		try{
			permissionGroup.setCode(PermissionCodeHelper.createGroupCode(permissionGroup));
			permissionGroupManager.save(permissionGroup);
			permissionGroup.setSortId(permissionGroup.getId());
			permissionGroupManager.update(permissionGroup);
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
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) throws Exception {
		PermissionGroup permissionGroup = (PermissionGroup)permissionGroupManager.getById(id);
		List productList = permissionGroupManager.getProduct();
		model.addAttribute("permissionGroup",permissionGroup);
		model.addAttribute("productList",productList);
		
		model.addAttribute("productId", paramUtils.getIntParameter(request, "productId", 0));
		addPermissionAttribute(model, request);
		return "/admin/auth/permissionGroup/edit";
	}
	
	/** 选项卡编辑 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) throws Exception {
		PermissionGroup permissionGroup = (PermissionGroup)permissionGroupManager.getById(id);
		List productList = permissionGroupManager.getProduct();
		model.addAttribute("permissionGroup",permissionGroup);
		model.addAttribute("productList",productList);
		
		model.addAttribute("permissionGroupId", paramUtils.getIntParameter(request, "permissionGroupId", 0));
		model.addAttribute("productId", paramUtils.getIntParameter(request, "productId", 0));
		return "/admin/auth/permissionGroup/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		PermissionGroup permissionGroup = (PermissionGroup)permissionGroupManager.getById(id);
		model.addAttribute("permissionGroup",permissionGroup);
		return "/admin/auth/permissionGroup/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid PermissionGroup permissionGroup,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/auth/permissionGroup/edit";
		}
		if(null == permissionGroup.getCode() || permissionGroup.getCode().equals("")){
			permissionGroup.setCode(PermissionCodeHelper.createGroupCode(permissionGroup));
		}
		permissionGroupManager.update(permissionGroup);
		Flash.current().success(UPDATE_SUCCESS);
		
		String returnUrl = paramUtils.getParameter(request,"return_url", "");
		if(null == returnUrl || returnUrl.trim().equals("")){
			returnUrl = LIST_ACTION;
		}
		return this.getUrl(returnUrl, request);
	}
	
	@RequestMapping(value="/{id}/updatePermissionGroup")
	@ResponseBody
	public Integer updatePermissionGroup(ModelMap model,@PathVariable java.lang.Long id,@Valid PermissionGroup permissionGroup,HttpServletRequest request){
		Integer result = 0;
		if(null == permissionGroup.getCode() || permissionGroup.getCode().equals("")){
			permissionGroup.setCode(PermissionCodeHelper.createGroupCode(permissionGroup));
		}
		try{
			permissionGroupManager.update(permissionGroup);
			result =1;
		}catch(Exception e){
			result =0;
		}
		return result;
	}
	
	
	/** 删除 
	 * @throws IOException */
	@RequestMapping(value="/{id}/delete")
	@ResponseBody
	public String delete(ModelMap model,@PathVariable java.lang.Long id) throws IOException {
		permissionGroupManager.cascadingDeleteByPerGroupId(id);
		HashMap<String,String> jsonMap = new HashMap<String, String>();
		jsonMap.put("status", "success");
		return jsonUtil.object2Json(jsonMap);
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items,HttpServletRequest request) {
		for(int i = 0; i < items.length; i++) {
			permissionGroupManager.cascadingDeleteByPerGroupId(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		
		String returnUrl = paramUtils.getParameter(request,"return_url", "");
		if(null == returnUrl || returnUrl.trim().equals("")){
			returnUrl = LIST_ACTION;
		}
		return returnUrl;
	}
	
	/**
	 * 批量编辑    取得产品列表
	 * @author bxmen
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/batchEdit")
	public String batchEdit(ModelMap model){
		List<Product> productList = permissionGroupManager.getProduct();
		
		model.addAttribute("productList", productList);
		return "/admin/auth/permissionGroup/batch_edit";
	}
	
	/**
	 * 批量操作 更新
	 * @author bxmen
	 **/
	@RequestMapping(value="/batchUpdate")
	public String batchUpdate(ModelMap model, @RequestParam java.lang.String batchItems, @RequestParam java.lang.String[] batchNames,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String stringParam = "|name|description|productId|";
		Map<String,String> map = new HashMap<String, String>();
		
		for(int i=0;i<batchNames.length;i++){
			if(stringParam.indexOf("|"+batchNames[i]+"|")!=-1){
				map.put(batchNames[i],"'"+request.getParameter(batchNames[i])+"'");
			}else{
				map.put(batchNames[i],request.getParameter(batchNames[i]));
			}
		}
		
		permissionGroupManager.batchUpdaterPermissionGroup(batchItems,map);
		Flash.current().success(UPDATE_SUCCESS);
		
		String returnUrl = paramUtils.getParameter(request,"return_url", "");
		if(null == returnUrl || returnUrl.trim().equals("")){
			returnUrl = LIST_ACTION;
		}
		return returnUrl;
	}
	
	/**
	 * 权限组下的权限tag显示
	 * @author wqtan
	 */
	@RequestMapping(value="/{id}/tabPermission")
	public String tabPermission(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) {
		PermissionGroup permissionGroup = permissionGroupManager.getById(id);
		
		model.addAttribute("permissionGroup",permissionGroup);
		model.addAttribute("permissionGroupId", paramUtils.getIntParameter(request, "permissionGroupId", 0));
		model.addAttribute("productId", paramUtils.getIntParameter(request, "productId", 0));
		return "/admin/auth/permissionGroup/tabPermission";
	}
	

	@RequestMapping(value="/{productCode}/permGroupJSON")
	public String permGroupJSON(ModelMap model,@PathVariable String productCode,HttpServletRequest request){
		model.addAttribute("permissionGroupList",this.permissionGroupManager.getPermissionGroupList(productCode));
		return "/admin/auth/permissionGroup/permGroupJSON";
	}
	 /**
	    *修改排序 
	    * 
	    **/
		@RequestMapping(value="/{id}/updateSortId")
		@ResponseBody
		public String updateSortId(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request,HttpServletResponse response) throws IOException{
			Integer type = paramUtils.getIntParameter(request, "type", 0);
			HashMap<String,Object> jsonMap = new HashMap<String,Object>();
			String message = SUCCESS;
			PermissionGroup permissionGroup = permissionGroupManager.getById(id);
			Long oldSortId = permissionGroup.getSortId();
			PermissionGroup nextPermissionGroup = permissionGroupManager.getAnotherPermissionGroup(permissionGroup.getSortId(),permissionGroup.getProductCode(), type);
			if(null!=nextPermissionGroup){
				permissionGroup.setSortId(nextPermissionGroup.getSortId());
				nextPermissionGroup.setSortId(oldSortId);
				permissionGroupManager.update(permissionGroup);
				permissionGroupManager.update(nextPermissionGroup);
			}else{
				message = ERROR;
			}
			jsonMap.put("message", message);
			return jsonUtil.object2Json(jsonMap);
		}
}

