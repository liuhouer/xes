/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */


package com.up72.auth.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.up72.auth.annotation.PermissionGroupAnnotation;
import com.up72.auth.model.WorkGroupMember;
import com.up72.auth.service.WorkGroupManager;
import com.up72.auth.service.WorkGroupMemberManager;
import com.up72.auth.vo.query.WorkGroupMemberQuery;
import com.up72.base.BaseRestSpringController;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;

/**
 * 工作组(部门)与用户多对多关系表数据提取跳转
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/auth/workGroupMember")
@PermissionGroupAnnotation(name="用户部门",description="")
public class AdminWorkGroupMemberController extends BaseRestSpringController<WorkGroupMember,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private WorkGroupMemberManager workGroupMemberManager;
	
	@Autowired
	private WorkGroupManager workGroupManager;
	
	private final String LIST_ACTION = "redirect:/admin/auth/workGroupMember";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setWorkGroupMemberManager(WorkGroupMemberManager manager) {
		this.workGroupMemberManager = manager;
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
	public String index(ModelMap model,WorkGroupMemberQuery query,HttpServletRequest request,HttpServletResponse response) {
		List workGroupList = workGroupManager.getEntityDao().findAll();
		Page page = this.workGroupMemberManager.findPage(query);
		model.addAttribute("workGroupId", query.getWorkGroupId());
		model.addAllAttributes(toModelMap(page, query));
		model.addAttribute("workGroupList",workGroupList);
		return "/admin/auth/workGroupMember/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		WorkGroupMember workGroupMember = (WorkGroupMember)workGroupMemberManager.getById(id);
		model.addAttribute("workGroupMember",workGroupMember);
		return "/admin/auth/workGroupMember/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,WorkGroupMember workGroupMember,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("workGroupMember",workGroupMember);
		return "/admin/auth/workGroupMember/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid WorkGroupMember workGroupMember,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/auth/workGroupMember/new";
		}
		
		workGroupMemberManager.save(workGroupMember);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		WorkGroupMember workGroupMember = (WorkGroupMember)workGroupMemberManager.getById(id);
		model.addAttribute("workGroupMember",workGroupMember);
		return "/admin/auth/workGroupMember/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		WorkGroupMember workGroupMember = (WorkGroupMember)workGroupMemberManager.getById(id);
		model.addAttribute("workGroupMember",workGroupMember);
		return "/admin/auth/workGroupMember/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		WorkGroupMember workGroupMember = (WorkGroupMember)workGroupMemberManager.getById(id);
		model.addAttribute("workGroupMember",workGroupMember);
		return "/admin/auth/workGroupMember/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid WorkGroupMember workGroupMember,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/auth/workGroupMember/edit";
		}
		
		workGroupMemberManager.update(workGroupMember);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		workGroupMemberManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			workGroupMemberManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
//	@RequestMapping(value="/{id}/setManager",method=RequestMethod.POST)
//	@ResponseBody
//	public String setManager(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request,HttpServletResponse response){
//		WorkGroupMember workGroupMember = workGroupMemberManager.getById(id);
//		if(workGroupMember.getIsManager() == CommonConstants.workGroupMember.IS_MANAGER_TRUE){
//			workGroupMember.setIsManager(CommonConstants.workGroupMember.IS_MANAGER_FALSE);
//		}else{
//			workGroupMember.setIsManager(CommonConstants.workGroupMember.IS_MANAGER_TRUE);
//			workGroupMember.setStatus(CommonConstants.STATUS_TRUE);
//		}
//		workGroupMemberManager.update(workGroupMember);
//		return "";
//	}
	
	
}

