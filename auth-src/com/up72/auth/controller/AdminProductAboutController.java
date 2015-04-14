/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */


package com.up72.auth.controller;


import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.paramUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.up72.auth.dao.ProductDao;
import com.up72.auth.model.Product;
import com.up72.auth.model.ProductAbout;
import com.up72.auth.service.ProductAboutManager;
import com.up72.auth.service.ProductManager;
import com.up72.auth.vo.query.ProductAboutQuery;
import com.up72.framework.page.Page;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.framework.web.scope.Flash;

/**
 * InnoDB free: 263168 kB数据提取跳转
 * 
 * @author auth
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/auth/productAbout")
public class AdminProductAboutController extends AuthController<ProductAbout,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private ProductAboutManager productAboutManager;
	
	@Autowired
	private ProductManager productManager;
	
	private final String LIST_ACTION = "redirect:/admin/auth/productAbout";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setProductAboutManager(ProductAboutManager manager) {
		this.productAboutManager = manager;
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
	public String index(ModelMap model,ProductAboutQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		query.setSortColumns("sortId asc");
		Page page = this.productAboutManager.findPage(query);
		ProductDao productDao = (ProductDao)ApplicationContextHolder.getBean("productDao");
		List<Product> productList = productDao.findAll();
		model.addAttribute("productList",productList);
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/auth/productAbout/index";
	}
	/**
	    *修改排序 
	    * 
	    **/
		@RequestMapping(value="/{id}/updateSortId")
		@ResponseBody
	public String updateSortId(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer type = paramUtils.getIntParameter(request, "type", 0);
		String productCode= paramUtils.getParameter(request, "productCode", "");
		HashMap<String,Object> jsonMap = new HashMap<String,Object>();
		String message = SUCCESS;
		ProductAbout productAbout = productAboutManager.getById(id);
		Integer oldSortId = productAbout.getSortId();
		ProductAbout nextProductAbout = productAboutManager.getAnotherProductAbout(productAbout.getSortId(), type,productCode);
		if(null!=nextProductAbout){
			productAbout.setSortId(nextProductAbout.getSortId());
			nextProductAbout.setSortId(oldSortId);
			productAboutManager.update(productAbout);
			productAboutManager.update(nextProductAbout);
		}else{
			message = ERROR;
		}
		jsonMap.put("message", message);
		return jsonUtil.object2Json(jsonMap);
	}
	/** 列表  
	  * @throws IOException */
	@RequestMapping(value="/{code}/tabIndex")
	public String tabIndex(ModelMap model,HttpServletRequest request,HttpServletResponse response,@PathVariable java.lang.String code) throws IOException {
		HashMap<String, Object> aboutParams = new HashMap<String, Object>();
		aboutParams.put("productCode", code);
		TreeMap<String,String> orderMap = new TreeMap<String, String>();
		orderMap.put("sortId", "asc");
		List<ProductAbout> aboutList = this.productAboutManager.findList(aboutParams, 0, orderMap);
		ProductDao productDao = (ProductDao)ApplicationContextHolder.getBean("productDao");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		List<Product> productList = productDao.findList(params, 0, null);
		model.addAttribute("productList",productList);
		model.addAttribute("aboutList",aboutList);
		model.addAttribute("code",code);
		model.addAttribute("type", "tabIndex");
		return "/admin/auth/productAbout/tab_index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		ProductAbout productAbout = (ProductAbout)productAboutManager.getById(id);
		model.addAttribute("productAbout",productAbout);
		return "/admin/auth/productAbout/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,ProductAbout productAbout,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("productAbout",productAbout);
		
		List<Product> productList = productManager.findAll();
		model.addAttribute("productList",productList);
		
		model.addAttribute("productCode",paramUtils.getParameter(request, "productCode", ""));
		return "/admin/auth/productAbout/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid ProductAbout productAbout,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/auth/productAbout/new";
		}
		
		productAboutManager.save(productAbout);
		/*添加排序字段*/
		String productAboutId = "";
		if (null != productAbout) {
			productAboutId = String.valueOf(productAbout.getId());
			int sortId = Integer.parseInt(productAboutId);
			productAbout.setSortId(sortId);
			productAboutManager.update(productAbout);
		}
		
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) throws Exception {
		ProductAbout productAbout = (ProductAbout)productAboutManager.getById(id);
		model.addAttribute("productAbout",productAbout);
		
		List<Product> productList = productManager.findAll();
		model.addAttribute("productList",productList);
		
		model.addAttribute("productCode",paramUtils.getParameter(request, "productCode", ""));
		return "/admin/auth/productAbout/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		ProductAbout productAbout = (ProductAbout)productAboutManager.getById(id);
		ProductDao productDao = (ProductDao)ApplicationContextHolder.getBean("productDao");
		List<Product> productList = productDao.findAll();
		model.addAttribute("productAbout",productAbout);
		model.addAttribute("productList",productList);
		return "/admin/auth/productAbout/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		ProductAbout productAbout = (ProductAbout)productAboutManager.getById(id);
		model.addAttribute("productAbout",productAbout);
		return "/admin/auth/productAbout/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid ProductAbout productAbout,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/auth/productAbout/edit";
		}
		
		productAboutManager.update(productAbout);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	@RequestMapping(value="/{code}/updateProductAbout")
	@ResponseBody
	public Integer updateProductAbout(ModelMap model,@PathVariable java.lang.String code,@Valid ProductAbout productAbout,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Integer result =0;
		try{
			productAboutManager.saveOrUpdate(productAbout);
			result =1;
		}catch(Exception e){
			result =0;
		}
		return result;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		productAboutManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			productAboutManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	@RequestMapping(value="/deleteAbout")
	@ResponseBody
	public Integer deleteAbout(ModelMap model,@RequestParam("items") java.lang.Long[] items){
		Integer result = 0;
		for(int i = 0; i < items.length; i++) {
			productAboutManager.removeById(items[i]);
			result =1;
		}
		return result;
	}
	
	/** 选项卡显示 */
	@RequestMapping(value="/showProductAbout")
	public String showProductAbout(ModelMap model,HttpServletRequest request) throws Exception {
		String productId = paramUtils.getParameter(request, "productId");
		List<ProductAbout> productAboutList = productAboutManager.getProductAboutByProductId(productId);
		model.addAttribute("productAboutList",productAboutList);
		return "/admin/auth/productAbout/about";
	}
	
}

