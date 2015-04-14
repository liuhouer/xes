/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */


package com.up72.auth.controller;

import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.common.CommonUtils.stringUtil;
import static com.up72.framework.util.ObjectUtils.isNotEmpty;

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
import com.up72.auth.annotation.PermissionGroupAnnotation;
import com.up72.auth.bean.PermissionCodeHelper;
import com.up72.auth.model.Permission;
import com.up72.auth.model.PermissionGroup;
import com.up72.auth.model.Product;
import com.up72.auth.model.ProductAbout;
import com.up72.auth.service.PermissionGroupManager;
import com.up72.auth.service.PermissionManager;
import com.up72.auth.service.ProductAboutManager;
import com.up72.auth.service.ProductManager;
import com.up72.auth.vo.query.ProductQuery;
import com.up72.exception.ServiceException;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;

/**
 * 产品数据提取跳转
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/auth/product")
@PermissionGroupAnnotation(name="产品",description="")
public class AdminProductController extends AuthController<Product,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	@Autowired
	private ProductManager productManager;
	@Autowired
	private ProductAboutManager productAboutManager;
	@Autowired
	private PermissionGroupManager permissionGroupManager;
	@Autowired
	private PermissionManager permissionManager;
	private final String LIST_ACTION = "redirect:/admin/auth/product/indexTree";
		
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
	public String index(ModelMap model,ProductQuery query,HttpServletRequest request,HttpServletResponse response) {
		Page page = this.productManager.findPage(query);
		AuthConstants.PAGE_NUMBER = query.getPageNumber();
		AuthConstants.PAGE_SIZE = query.getPageSize();
		model.addAllAttributes(toModelMap(page, query));
		List<Product> productList = this.productManager.findAll();
		model.addAttribute("productList", productList);
		return "/admin/auth/product/index";//indexTree";
	}
	
	@RequestMapping(value="/indexTree")
	@SuppressWarnings({ "unchecked" })
	public String indexTree(ModelMap model,ProductQuery query,HttpServletRequest request,HttpServletResponse response) {
		Page page = this.productManager.findPage(query);
		AuthConstants.PAGE_NUMBER = query.getPageNumber();
		AuthConstants.PAGE_SIZE = query.getPageSize();
		
		model.addAllAttributes(toModelMap(page, query));
		TreeMap<String,String> orderMap = new TreeMap<String, String>();
		orderMap.put("sortId", "asc");
		List<Product> productList = this.productManager.findList(null, 0, orderMap);
		model.addAttribute("productList",productList);
		model.addAttribute("productList",productList);
		String proId = paramUtils.getParameter(request, "proId", "");
		String perGroupId = paramUtils.getParameter(request, "perGroupId", "");
		model.addAttribute("proId", proId);
		model.addAttribute("perGroupId", perGroupId);
		addPermissionAttribute(model, request);
		return "/admin/auth/product/indexTree";
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
		Product product = productManager.getById(id);
		Long oldSortId = product.getSortId();
		Product nextProduct = productManager.getAnotherProduct(product.getSortId(), type);
		if(null!=nextProduct){
			product.setSortId(nextProduct.getSortId());
			nextProduct.setSortId(oldSortId);
			productManager.update(product);
			productManager.update(nextProduct);
		}else{
			message = ERROR;
		}
		jsonMap.put("message", message);
		return jsonUtil.object2Json(jsonMap);
	}
	/**
	 * 控制面板
	 * @author wqtan
	 * @create 2012-12-28 18:01
	 */
	@RequestMapping(value="/dashboard")
	public String dashboard(ModelMap model,ProductQuery query,HttpServletRequest request,HttpServletResponse response) {
		this.addPermissionAttribute(model, request);
		return "/admin/auth/product/dashboard";
	}
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Product product = (Product)productManager.getById(id);
		model.addAttribute("product",product);
		return "/admin/auth/product/show";
	}

	/** 进入关于产品页面 */
	@RequestMapping(value="/{code}/about")
	public String about(ModelMap model,@PathVariable java.lang.String code,HttpServletRequest request) throws Exception {
		HashMap<String,Object> params =new HashMap<String, Object>();
		params.put("productCode", code);
		System.out.println(code);
		List<ProductAbout> productAboutList = this.productAboutManager.findList(params, 0, null);
		if(productAboutList != null && productAboutList.size()>0){
			model.addAttribute("productAbout",productAboutList.get(0));
			System.out.println(productAboutList.get(0).getContent());
			System.out.println(productAboutList.get(0).getTitle());
		}
		model.addAttribute("code",code);
		return "/admin/auth/product/product_about";
	}
	
	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Product product,HttpServletRequest request,HttpServletResponse response) throws Exception {
		createToken(request);
		model.addAttribute("product",product);
		addPermissionAttribute(model, request);
		return "/admin/auth/product/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String create(ModelMap model,@Valid Product product,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		if(errors.hasErrors()) {
			return "/admin/auth/product/new";
		}
		String status = SUCCESS;
		String message = "操作成功";
		if(validateToken(request)){
			
			try{
				product.setCode(PermissionCodeHelper.createCode(product.getName()));
				productManager.save(product);
				product.setSortId(product.getId());
				productManager.update(product);
			}catch (ServiceException e) {
				status = ERROR;
				message = e.getMessage();
			}catch (Exception e) {
				log.error(e);
				status = ERROR;
				message = "系统错误"+e.getMessage();
			}
			
		}else{
			String referer = request.getHeader("Referer");
			if (isNotEmpty(referer)) {
				model.addAttribute("referer", referer);
				jsonMap.put("referer", referer);
			}
			status="formError";
			

		}
		jsonMap.put(STATUS, status);
		jsonMap.put(MESSAGE, stringUtil.encode(message));
		return jsonUtil.object2Json(jsonMap);
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) throws Exception {
		Product product = (Product)productManager.getById(id);
		model.addAttribute("product",product);
		addPermissionAttribute(model, request);
		return "/admin/auth/product/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Product product = (Product)productManager.getById(id);
		model.addAttribute("product",product);
		return "/admin/auth/product/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Product product = (Product)productManager.getById(id);
		model.addAttribute("product",product);
		model.addAttribute("type", "show");
		return "/admin/auth/product/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Product product,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/auth/product/edit";
		}
		if(null == product.getCode() || product.getCode().trim().equals("")){
			product.setCode(PermissionCodeHelper.createCode(product.getName()));
		}
		productManager.update(product);
		Flash.current().success(UPDATE_SUCCESS);
		
		String returnUrl = paramUtils.getParameter(request,"return_url", "");
		if(null == returnUrl || returnUrl.trim().equals("")){
			returnUrl = LIST_ACTION+"?pageNumber="+AuthConstants.PAGE_NUMBER+"&pageSize="+AuthConstants.PAGE_SIZE;
		}
		return this.getUrl(returnUrl,request);
	}
	@RequestMapping(value="/{id}/updateProduct")
	@ResponseBody
	public Integer updateProduct(ModelMap model,@PathVariable java.lang.Long id,@Valid Product product) {
		Integer result =0;
		if(null == product.getCode() || product.getCode().trim().equals("")){
			product.setCode(PermissionCodeHelper.createCode(product.getName()));
		}
		try{
			productManager.update(product);
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
		productManager.cascadingDeleteByProductId(id);
		HashMap<String,String> jsonMap=new HashMap<String, String> ();
		jsonMap.put("status", "success");
		return jsonUtil.object2Json(jsonMap);
	}
	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			productManager.cascadingDeleteByProductId(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION+"?pageNumber="+AuthConstants.PAGE_NUMBER+"&pageSize="+AuthConstants.PAGE_SIZE;
	}
	/** 选项卡 
	 * 
	 * @throws IOException */
	@RequestMapping(value="/{id}/tabProduct")
	public String tabProduct(ModelMap model,@PathVariable java.lang.Long id,java.lang.Long AUTH_PERM_ID,HttpServletRequest request) throws IOException {
		Product product = productManager.getById(id);
		long productTabId=paramUtils.getLongParameter(request, "productTabId", 0L); 
		model.addAttribute("product", product);
		model.addAttribute("id", id);
		model.addAttribute("code",product.getCode());
		model.addAttribute("AUTH_PERM_ID", AUTH_PERM_ID);
		model.addAttribute("productTabId", productTabId);
		return "/admin/auth/product/tab_product";
	}
	
	/** 选项卡 
	 * 
	 * @throws IOException */
	@RequestMapping(value="/{id}/tabPermission")
	public String tabPermission(ModelMap model,@PathVariable java.lang.Long id,java.lang.Long AUTH_PERM_ID,HttpServletRequest request) throws IOException {
		Permission permission = permissionManager.getById(id);
		model.addAttribute("permission", permission);
		model.addAttribute("id", id);
		model.addAttribute("AUTH_PERM_ID", AUTH_PERM_ID);
		return "/admin/auth/product/tab_permission";
	}
	
	/** 选项卡 
	 * 
	 * @throws IOException */
	@RequestMapping(value="/{id}/tabPermissionGroup")
	public String tabPermissionGroup(ModelMap model,@PathVariable java.lang.Long id,java.lang.Long AUTH_PERM_ID,HttpServletRequest request) throws IOException {
		PermissionGroup permissionGroup = permissionGroupManager.getById(id);
		String code = paramUtils.getParameter(request, "code");
		model.addAttribute("permissionGroup", permissionGroup);
		model.addAttribute("id", id);
		model.addAttribute("code", code);
		model.addAttribute("AUTH_PERM_ID", AUTH_PERM_ID);
		return "/admin/auth/product/tab_permissionGroup";
	}
	/**
	 * 产品下的权限组tab显示
	 * @author wqtan
	 */
	@RequestMapping(value="/{id}/tabPermGroup")
	public String tabPermGroup(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) {
		Product product = productManager.getById(id);
		model.addAttribute("product", product);
		this.addPermissionAttribute(model, request);
		return "/admin/auth/product/tabPermGroup";
	}
	/**
	 * 产品下的角色tab显示
	 * @author wqtan
	 */
	@RequestMapping(value="/{id}/tabRole")
	public String tabRole(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) {
		Product product = productManager.getById(id);
		model.addAttribute("product", product);
		model.addAttribute("type", "role");
		this.addPermissionAttribute(model, request);
		return "/admin/auth/product/tabRole";
	}
	
	@RequestMapping("/showSort")
	public String showSort(ModelMap model,ProductQuery query,HttpServletRequest request){
		query.setPageSize(10);
		Page page = this.productManager.findPage(query);
		
		AuthConstants.PAGE_NUMBER = query.getPageNumber();
		AuthConstants.PAGE_SIZE = query.getPageSize();
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/auth/product/sort";
	}

	@RequestMapping("/sort")
	@ResponseBody
	public String sort(ModelMap model,HttpServletRequest request) throws IOException{
		HashMap<String,Object> jsonMap = new HashMap<String, Object>();
		
		
		
		return jsonUtil.object2Json(jsonMap);
	}
	
	/**
	 * 权限导出
	 */
	@RequestMapping("/exportPermission")
	public void exportPermission(ModelMap modelMap,HttpServletRequest request){
		
	}
	
}

