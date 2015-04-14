/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.controller;

import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.common.CommonUtils.stringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
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
import com.up72.auth.member.model.AuthUser;
import com.up72.auth.model.Permission;
import com.up72.auth.model.PermissionGroup;
import com.up72.auth.model.Product;
import com.up72.auth.service.PermissionGroupManager;
import com.up72.auth.service.PermissionManager;
import com.up72.auth.service.ProductManager;
import com.up72.auth.vo.query.OrganizationQuery;
import com.up72.auth.vo.query.PermissionGroupQuery;
import com.up72.auth.vo.query.PermissionQuery;
import com.up72.auth.vo.query.ProductQuery;
import com.up72.common.CommonConstants;
import com.up72.common.xml.DatasXmlVo;
import com.up72.exception.ServiceException;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;

/**
 * 权限表（菜单，资源）数据提取跳转
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/auth/permission")
@PermissionGroupAnnotation(name = "权限", description = "")
public class AdminPermissionController extends
		AuthController<Permission, java.lang.Long> {
	// 默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null;
	@Autowired
	private PermissionManager permissionManager;
	@Autowired
	private ProductManager productManager;
	@Autowired
	private PermissionGroupManager permissionGroupManager;

	private final String LIST_ACTION = "redirect:/admin/auth/permission";

	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 */
	public void setPermissionManager(PermissionManager manager) {
		this.permissionManager = manager;
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
	public String index(ModelMap model, PermissionQuery query,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Page page = this.permissionManager.findPage(query);

		model.addAllAttributes(toModelMap(page, query));

		this.addShowLabelAttrbite("/admin/auth/permission", model, request,
				response);
		return "/admin/auth/permission/index";
	}

	/** 显示 */
	@RequestMapping(value = "/{id}")
	public String show(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		Permission permission = (Permission) permissionManager.getById(id);
		model.addAttribute("permission", permission);
		return "/admin/auth/permission/show";
	}

	/** 进入新增 */
	@RequestMapping(value = "/new")
	public String _new(ModelMap model, Permission permission,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		String productId = paramUtils.getParameter(request, "productId");
		String productCode = paramUtils.getParameter(request, "productCode");
		String permissionGroupCode= paramUtils.getParameter(request, "permissionGroupCode");
//		String permissionGroupId = paramUtils.getParameter(request,"permissionGroupId");
		permission.setProductCode(productCode);
		permission.setPermissionGroupCode(permissionGroupCode);
		model.addAttribute("permission", permission);
		addPermissionAttribute(model, request);
		model.addAttribute("productList", productManager.findAll());
		return "/admin/auth/permission/new";
	}

	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String create(ModelMap model, @Valid
	Permission permission, BindingResult errors, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (errors.hasErrors()) {
			return "/admin/auth/permission/new";
		}
		String status = SUCCESS;
		String message = "操作成功";
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try{
			permission.setCode(PermissionCodeHelper.createPermissionCode(permission));
			permissionManager.save(permission);
			permission.setSortId(permission.getId().intValue());
			permissionManager.update(permission);
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
	public String edit(ModelMap model, @PathVariable
	java.lang.Long id, HttpServletRequest request) throws Exception {
		Permission permission = (Permission) permissionManager.getById(id);
		model.addAttribute("permission", permission);
		addPermissionAttribute(model, request);
		model.addAttribute("productList", productManager.findAll());
		if (permission != null) {
			model.addAttribute("permissionGroupList", permissionGroupManager
					.getPermissionGroupList(permission.getPermProductCode()));
		}
		return "/admin/auth/permission/edit";
	}

	/** 选项卡编辑 */
	@RequestMapping(value = "/{id}/tabEdit")
	public String tabEdit(ModelMap model, @PathVariable
	java.lang.Long id, HttpServletRequest request) throws Exception {
		Permission permission = (Permission) permissionManager.getById(id);
		model.addAttribute("permission", permission);

		model.addAttribute("permissionGroupId", paramUtils.getIntParameter(
				request, "permissionGroupId", 0));
		model.addAttribute("productId", paramUtils.getIntParameter(request,
				"productId", 0));
		return "/admin/auth/permission/tab_edit";
	}

	/** 选项卡显示 */
	@RequestMapping(value = "/{id}/tabShow")
	public String tabShow(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		Permission permission = (Permission) permissionManager.getById(id);
		model.addAttribute("permission", permission);
		model.addAttribute("code", permission.getProductCode());
		return "/admin/auth/permission/tab_show";
	}

	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(ModelMap model, @PathVariable
	java.lang.Long id, @Valid
	Permission permission, BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (errors.hasErrors()) {
			return "/admin/auth/permission/edit";
		}
		if (null == permission.getCode()
				|| permission.getCode().trim().equals("")) {
			permission.setCode(PermissionCodeHelper
					.createPermissionCode(permission));
		}
		permission.setProductCode(permission.getPermProductCode());
		permissionManager.update(permission);
		Flash.current().success(UPDATE_SUCCESS);

		String returnUrl = paramUtils.getParameter(request, "return_url", "");
		if (null == returnUrl || returnUrl.trim().equals("")) {
			returnUrl = LIST_ACTION;
		}
		return getUrl(returnUrl, request);
	}

	@RequestMapping(value = "/{id}/updatePermission")
	@ResponseBody
	public Integer updatePermission(ModelMap model, @PathVariable
	java.lang.Long id, @Valid
	Permission permission, HttpServletRequest request,
			HttpServletResponse response){
		Integer result = 0;
		if (null == permission.getCode()
				|| permission.getCode().trim().equals("")) {
			permission.setCode(PermissionCodeHelper
					.createPermissionCode(permission));
		}
		permission.setProductCode(permission.getPermProductCode());
		
		try{
			permissionManager.update(permission);
			result =1;
		}catch(Exception e){
			result =0;
		}
		return result;
	}
	
	
	/**
	 * 删除
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/{id}/delete")
	@ResponseBody
	public String delete(ModelMap model, @PathVariable
	java.lang.Long id, HttpServletRequest request) throws IOException {
		permissionManager.removeById(id);
		HashMap<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("status", "success");
		return jsonUtil.object2Json(jsonMap);
	}

	/** 批量删除 */
	@RequestMapping(method = RequestMethod.DELETE)
	public String batchDelete(ModelMap model, @RequestParam("items")
	java.lang.Long[] items, HttpServletRequest request) {
		for (int i = 0; i < items.length; i++) {
			permissionManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);

		String returnUrl = paramUtils.getParameter(request, "return_url", "");
		if (null == returnUrl || returnUrl.trim().equals("")) {
			returnUrl = LIST_ACTION;
		}
		return returnUrl;
	}

	/**
	 * 根据传递的用户，查询用户具有的菜单权限并跳转输出页面
	 */
	@RequestMapping(value = "/userPermission")
	public String userPermission(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		return "/admin/auth/permission/userPermission";
	}

	/**
	 * 选择机构
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/organization")
	public String organization(ModelMap model, OrganizationQuery query,
			HttpServletRequest request, HttpServletResponse response) {
		Page page = permissionManager.findOrganization(query);
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/auth/permission/organization";
	}

	/**
	 * 选择权限组
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/permissionGroup")
	public String permissionGroup(ModelMap model, PermissionGroupQuery query,
			HttpServletRequest request, HttpServletResponse response) {
		// Long productId = paramUtils.getLongParameter(request, "productId",
		// AuthConstants.DEFAULT_LONG_VALUE);
		Long permissionGroupId = paramUtils.getLongParameter(request,
				"permissionGroupId", AuthConstants.DEFAULT_LONG_VALUE);
		// query.setProductId(productId);
		// System.out.println(query.getProductId());
		Page page = permissionManager.findPermissionGroup(query);
		model.addAllAttributes(toModelMap(page, query));
		model.addAttribute("permissionGroupId", permissionGroupId);
		return "/admin/auth/permission/permissionGroup";
	}

	/**
	 * 选择产品
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/product")
	public String product(ModelMap model, ProductQuery query,
			HttpServletRequest request, HttpServletResponse response) {
		Page page = permissionManager.findProduct(query);
		Long productId = paramUtils.getLongParameter(request, "productId",
				AuthConstants.DEFAULT_LONG_VALUE);
		model.addAttribute("productId", productId);
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/auth/permission/product";
	}
	/**
	 * 跳转到权限导入导出
	 */
	@RequestMapping("/exportAndImportPerm")
	public String exportAndImportPerm(ModelMap modelMap,HttpServletRequest request){
		
		return "/admin/auth/permission/exportAndImportPerm";
	}
	/**
	 * 获取导出数据
	 */
	@RequestMapping("/getData")
	public String getData(ModelMap model,HttpServletRequest request){
		String[] productCodes=paramUtils.getParameters(request, "items", "");
		List<Product> productList = this.productManager.getProductByCodes(productCodes);
		model.addAttribute("productList", productList);
		return "/admin/auth/permission/exportData";
	}
	/**
	 * 导出权限
	 */
	@RequestMapping(value = "/exportPerm")
	public String exportPerm(ModelMap model,ProductQuery query,HttpServletRequest request,HttpServletResponse response) {
		List<Product> productList = this.productManager.findAll();
		model.addAttribute("productList",productList);
		return "/admin/auth/permission/export";
	}

	/**
	 * 导出权限处理
	 * @throws IOException 
	 */
	@RequestMapping(value = "/doExportPerm")
	@ResponseBody
	public String doExportPerm(ModelMap model, Permission query,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] productCodes=paramUtils.getParameters(request, "items", "");
		Long now=new Date().getTime();
		String xmlSavePath=CommonConstants.ROOTPATH+"export/";
		 File file = new File(xmlSavePath);
		 if (!file.exists()) {
		     file.mkdir();
		  }
		String xmlPath=xmlSavePath+"Product_"+now+".xml";
		String message = this.permissionManager.exportPerm(productCodes, xmlPath);
		HashMap<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("message", message);
		jsonMap.put("xmlPath", xmlPath);
		return jsonUtil.object2Json(jsonMap);
	}
	/**
	 * 导出权限处理
	 * @throws IOException 
	 */
	@RequestMapping(value = "/downPerm")
	@ResponseBody
	public String downPerm(ModelMap model,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String file=paramUtils.getParameter(request, "filePath");
		Long now=new Date().getTime();
		String fileName="product_"+now+"";
		if(!file.trim().equals("")){
			response.setContentType("application/x-msdownload");
			try {
				fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {}
			response.setHeader("Content-Disposition", "attachment; filename="+fileName+file.substring(file.lastIndexOf(".")));
			
			try{
				FileInputStream fis = new FileInputStream(file);
				
				int flag = 0;
				byte[] buff = new byte[1024*4];
	
				ServletOutputStream sos = response.getOutputStream();
				while((flag = fis.read(buff,0, buff.length))!=-1){
					sos.write(buff, 0, flag);
				}
				
				fis.close();
				sos.close();
			}catch (Exception e) {
				try {
					response.getWriter().print("下载出现错误！");
				} catch (IOException e1) {
				}
			}finally{
				File deleteFile = new File(file);
				deleteFile.delete();
			}
		}
		return "/admin/auth/permission/exportAndImportPerm";
	}

	/**
	 * 导出权限返回
	 */
	@RequestMapping(value = "/goBack")
	public String goBack(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		String[] productCodes=paramUtils.getParameters(request, "items", "");
		StringBuffer str = new StringBuffer();
		if(!"".equals(productCodes) && productCodes.length > 0){
			for (int i = 0; i < productCodes.length; i++) {
				str.append(productCodes[i]);
				if(i < productCodes.length-1){
					str.append(",");
				}
			}
			String codes = str.toString();
			model.addAttribute("codes", codes);
		}
		List<Product> list = this.productManager.findAll();
		model.addAttribute("productList", list);
		return "/admin/auth/permission/export";
	}
	/**
	 * 导入权限
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/importPerm")
	public String importPerm(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		String filePath=paramUtils.getParameter(request, "filePath");
		
		if(!"".equals(filePath.trim()) && null!=filePath){
			DatasXmlVo dxv = this.permissionManager.importPermXML(filePath);
			LinkedHashMap<String, List> recodeMap = dxv.getRecodeMap();
			List<Product> productList = recodeMap.get(Product.class.getName());
			model.addAttribute("productList", productList);
			model.addAttribute("filePath", filePath);
		}
		return "/admin/auth/permission/import";
	}
	
	/**
	 * 导入权限处理
	 */
	@RequestMapping(value = "/doImportPerm")
	public String doImportPerm(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		String[] productCodes=paramUtils.getParameters(request, "items", "");//选中的记录
		String filePath=paramUtils.getParameter(request, "filePath");
		if(!"".equals(filePath.trim()) && null!=filePath){
			DatasXmlVo dxv = this.permissionManager.importPermXML(filePath);
			this.permissionManager.doImportPerm(productCodes, dxv);
			if(!"".equals(filePath.trim()) && null!=filePath){
				String xmlSavePath=CommonConstants.ROOTPATH+filePath;
				File file = new File(xmlSavePath);
				if (file.exists()) {
					file.delete();
				}
			}
		}
		List<Product> productList = this.productManager.getProductByCodes(productCodes);
		model.addAttribute("productList", productList);
		return "/admin/auth/permission/successImport";
	}
	/**
	 * 导入权限返回
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/goBackImport")
	public String goBackImport(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		String filePath=paramUtils.getParameter(request, "filePath");
		if(!"".equals(filePath.trim()) && null!=filePath){
			String xmlSavePath=CommonConstants.ROOTPATH+filePath;
			File file = new File(xmlSavePath);
			if (file.exists()) {
				file.delete();
			}
		}
	 return "redirect:/admin/auth/permission/exportAndImportPerm";
	}

	/**
	 * 重置权限码
	 */
	@RequestMapping(value = "/doResetCode")
	@ResponseBody
	public String doResetCode() throws IOException {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		String status = SUCCESS;
		String message = "操作成功";

		try {
			permissionManager.resetPermissionCode();
		} catch (ServiceException e) {
			message = e.getMessage();
			status = ERROR;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			message = "系统异常，请稍后重试";
			status = ERROR;
		}

		jsonMap.put(STATUS, status);
		jsonMap.put(MESSAGE, message);
		return jsonUtil.object2Json(jsonMap);
	}

	/***************************************************************************
	 * 系统管理权限遍历
	 */
	@RequestMapping(value = "/{productCode}/goSysSet")
	@SuppressWarnings("unchecked")
	public String goSysSet(ModelMap model,@PathVariable String productCode, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long roleId = this.getSessionRoleId(request);
		AuthUser loginUser = this.getLoginMember(request);
		
		List<Permission> permissionList = null;
		if(null!=loginUser && loginUser.getUserName().trim().equals("admin")){
			HashMap<String,Object> params = new HashMap<String, Object>();
			params.put("type", AuthConstants.PERMISSION_TYPE_MENU);
			params.put("productCode", productCode);
			permissionList = this.permissionManager.findList(params, 0, null);
		}else{
			permissionList = this.permissionManager.getPermissionListByRole(roleId, productCode, AuthConstants.PERMISSION_TYPE_MENU, 0);
		}
		
		Map<String, List<Permission>> permissionMap = new HashMap<String, List<Permission>>();
		if(null!=permissionList && !permissionList.isEmpty()){
			for (int i = 0; i < permissionList.size(); i++) {
				Permission permission = permissionList.get(i);
				PermissionGroup group = permission.getPermissionGroup();
				if(null!=group){
					List<Permission> list = permissionMap.get(group.getName());
					if(null == list){
						list = new LinkedList<Permission>();
					}
					list.add(permission);
					permissionMap.put(group.getName(), list);
				}
			}
		}
		model.addAttribute("permissionMap", permissionMap);
		
		//String styles = getStyle(request);
		String result = "/admin/deskTop/sysSet";
//		if(null!=styles && !styles.trim().equals("")){
//			result = "/admin/include/" + styles + "/sysSet";
//		}
		return result;
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
			Permission permission = permissionManager.getById(id);
			Integer oldSortId = permission.getSortId();
			Permission nextPermission = permissionManager.getAnotherPermssion(permission.getSortId(),permission.getProductCode(),permission.getPermissionGroupCode(), type);
			if(null!=nextPermission){
				permission.setSortId(nextPermission.getSortId());
				nextPermission.setSortId(oldSortId);
				permissionManager.update(permission);
				permissionManager.update(nextPermission);
			}else{
				message = ERROR;
			}
			jsonMap.put("message", message);
			return jsonUtil.object2Json(jsonMap);
		}
}
