/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */


package com.up72.auth.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.up72.auth.AuthConstants;
import com.up72.auth.model.Menu;
import com.up72.auth.service.MenuManager;
import com.up72.auth.vo.query.MenuQuery;
import com.up72.base.BaseRestSpringController;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;
/**
 * 数据提取跳转
 * 
 * @author up72
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/auth/menu")
public class MenuController extends BaseRestSpringController<Menu,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private MenuManager menuManager;
	
	private final String LIST_ACTION = "redirect:/auth/menu";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setMenuManager(MenuManager manager) {
		this.menuManager = manager;
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
	public String index(ModelMap model,MenuQuery query,HttpServletRequest request,HttpServletResponse response) {
		Page page = this.menuManager.findPage(query);
		
		AuthConstants.PAGE_NUMBER = query.getPageNumber();
		AuthConstants.PAGE_SIZE = query.getPageSize();
		
		model.addAllAttributes(toModelMap(page, query));
		List<Menu> menuList = this.menuManager.findAll();
		model.addAttribute("menuList",menuList);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/auth/menu/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Menu menu = (Menu)menuManager.getById(id);
		model.addAttribute("menu",menu);
		return "/auth/menu/show";
	}
	/** 主页 */
	@RequestMapping(value="/indexTree")
	@SuppressWarnings({ "unchecked" })
	public String indexTree(ModelMap model,MenuQuery query,HttpServletRequest request,HttpServletResponse response) {
		Page page = this.menuManager.findPage(query);
		
		AuthConstants.PAGE_NUMBER = query.getPageNumber();
		AuthConstants.PAGE_SIZE = query.getPageSize();
		
		model.addAllAttributes(toModelMap(page, query));
		List<Menu> menuList = this.menuManager.findAll();
		model.addAttribute("menuList",menuList);
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/auth/menu/indexTree";
	}
	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Menu menu,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("menu",menu);
		return "/auth/menu/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid Menu menu,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/auth/menu/new";
		}
		
		menuManager.save(menu);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Menu menu = (Menu)menuManager.getById(id);
		model.addAttribute("menu",menu);
		return "/auth/menu/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Menu menu,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/auth/menu/edit";
		}
		
		menuManager.update(menu);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		menuManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			menuManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
}

