/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */


package com.up72.sys.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.up72.base.BaseRestSpringController;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;
import com.up72.sys.model.DataChangLog;
import com.up72.sys.service.DataChangLogManager;
import com.up72.sys.vo.query.DataChangLogQuery;
/**
 * 数据提取跳转
 * 
 * @author up72
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/sys/dataChangLog")
public class AdminDataChangLogController extends BaseRestSpringController<DataChangLog,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private DataChangLogManager dataChangLogManager;
	
	private final String LIST_ACTION = "redirect:/admin/sys/dataChangLog";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setDataChangLogManager(DataChangLogManager manager) {
		this.dataChangLogManager = manager;
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
	public String index(ModelMap model,DataChangLogQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Page page = this.dataChangLogManager.findPage(query);
		
		model.addAllAttributes(toModelMap(page, query));
		
		this.addShowLabelAttrbite("/admin/sys/dataChangLog",model,request,response);
		return "/admin/sys/dataChangLog/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		DataChangLog dataChangLog = (DataChangLog)dataChangLogManager.getById(id);
		model.addAttribute("dataChangLog",dataChangLog);
		return "/admin/sys/dataChangLog/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,DataChangLog dataChangLog,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("dataChangLog",dataChangLog);
		return "/admin/sys/dataChangLog/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid DataChangLog dataChangLog,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/sys/dataChangLog/new";
		}
		
		dataChangLogManager.save(dataChangLog);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		DataChangLog dataChangLog = (DataChangLog)dataChangLogManager.getById(id);
		model.addAttribute("dataChangLog",dataChangLog);
		return "/admin/sys/dataChangLog/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		DataChangLog dataChangLog = (DataChangLog)dataChangLogManager.getById(id);
		model.addAttribute("dataChangLog",dataChangLog);
		return "/admin/sys/dataChangLog/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		DataChangLog dataChangLog = (DataChangLog)dataChangLogManager.getById(id);
		model.addAttribute("dataChangLog",dataChangLog);
		return "/admin/sys/dataChangLog/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid DataChangLog dataChangLog,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/sys/dataChangLog/edit";
		}
		
		dataChangLogManager.update(dataChangLog);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		dataChangLogManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			dataChangLogManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
}

