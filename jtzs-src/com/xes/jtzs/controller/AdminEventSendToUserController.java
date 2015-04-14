/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */


package com.xes.jtzs.controller;

import static com.up72.common.CommonUtils.*;
import com.up72.common.CommonConstants;
import java.util.Map;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.up72.base.BaseRestSpringController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;

import java.util.*;

import com.up72.base.*;
import com.up72.util.*;

import com.up72.framework.util.*;
import com.up72.framework.web.util.*;
import com.up72.framework.page.*;
import com.up72.framework.page.impl.*;

import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.model.*;
import com.xes.jtzs.dao.*;
import com.xes.jtzs.service.*;
import com.xes.jtzs.vo.query.*;/**
 * 数据提取跳转
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/jtzs/eventSendToUser")
public class AdminEventSendToUserController extends BaseRestSpringController<EventSendToUser,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private EventSendToUserManager eventSendToUserManager;
	@Autowired
	private StudentManager studentManager;
	@Autowired
	private TeacherManager teacherManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/eventSendToUser";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setEventSendToUserManager(EventSendToUserManager manager) {
		this.eventSendToUserManager = manager;
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
	public String index(ModelMap model,EventSendToUserQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Page page = this.eventSendToUserManager.findPage(query);
		
		this.addShowLabelAttrbite("/admin/jtzs/eventSendToUser", request, response);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/eventSendToUser/index";
	}
	
	/** 列表  
	  * @throws IOException */
	@RequestMapping(value="/list")
	public String list(ModelMap model,EventSendToUserQuery querys,HttpServletRequest request,HttpServletResponse response) throws IOException {
		long eventId = paramUtils.getLongParameter(request, "eventId" ,0L);
		String nickName = paramUtils.getParameter(request, "nickName" );
		String loginName = paramUtils.getParameter(request, "loginName");
		int userRole = paramUtils.getIntParameter(request, "userRole", 0);
		
	
		Page page =null; 
		int pageNumber = paramUtils.getIntParameter(request, "pageNumber", 1);
		int pageSize = paramUtils.getIntParameter(request, "pageSize", 20);
		if(userRole == JTZSConstants.ROLE_STUDENT){
			StudentQuery stuQuery = new StudentQuery();
			stuQuery.setPageNumber(pageNumber);
			stuQuery.setPageSize(pageSize);
			stuQuery.setNickName(nickName);
			stuQuery.setLoginName(loginName);
			stuQuery.setUserRole(userRole);
			page = studentManager.findPage(stuQuery);
			model.addAttribute("querys",stuQuery);
		}else if(userRole == JTZSConstants.ROLE_TEACHER){
			
			TeacherQuery teaQuery = new TeacherQuery();
			teaQuery.setPageNumber(pageNumber);
			teaQuery.setPageSize(pageSize);
			teaQuery.setNickName(nickName);
			teaQuery.setLoginName(loginName);
			teaQuery.setUserRole(userRole);
			page = teacherManager.findPage(teaQuery);
			model.addAttribute("querys",teaQuery);
		}
		model.addAttribute("page", page);
		
		
		
		
		
		
		
		if(eventId!=0){
		querys.setEventId(eventId);
		}
		querys.setUserRole(userRole);
		Page page2 = this.eventSendToUserManager.findPage(querys);
		List selList= page2.getResult();
//		query.setEventId(value)
		model.addAttribute("selList",selList);
		model.addAttribute("eventId",eventId);
		return "/admin/jtzs/eventSendToUser/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		EventSendToUser eventSendToUser = (EventSendToUser)eventSendToUserManager.getById(id);
		model.addAttribute("eventSendToUser",eventSendToUser);
		return "/admin/jtzs/eventSendToUser/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,EventSendToUser eventSendToUser,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("eventSendToUser",eventSendToUser);
		return "/admin/jtzs/eventSendToUser/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid EventSendToUser eventSendToUser,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/eventSendToUser/new";
		}
		
		eventSendToUserManager.save(eventSendToUser);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		List selList = eventSendToUserManager.getSendInfoByEventId(id);

		int pageNumber = paramUtils.getIntParameter(request, "pageNumber", 1);
		int pageSize = paramUtils.getIntParameter(request, "pageSize", 20);
		int role = paramUtils.getIntParameter(request, "role", 0);
		List userList = eventSendToUserManager.getUserList(role);
		Page page =null; 
		if(role == JTZSConstants.ROLE_STUDENT){
			StudentQuery stuQuery = new StudentQuery();
			stuQuery.setPageNumber(pageNumber);
			stuQuery.setPageSize(pageSize);
			page = studentManager.findPage(stuQuery);
			model.addAttribute("page", page);
			model.addAttribute("stuQuery", stuQuery);
		}else if(role == JTZSConstants.ROLE_TEACHER){
			TeacherQuery teaQuery = new TeacherQuery();
			teaQuery.setPageNumber(pageNumber);
			teaQuery.setPageSize(pageSize);
			page = teacherManager.findPage(teaQuery);
			model.addAttribute("page", page);
			model.addAttribute("teaQuery",teaQuery);
		}
		model.addAttribute("selList",selList);
		model.addAttribute("userList",userList);
		model.addAttribute("eventId",id);
		model.addAttribute("role",role);
		
		return "/admin/jtzs/eventSendToUser/index";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		EventSendToUser eventSendToUser = (EventSendToUser)eventSendToUserManager.getById(id);
		model.addAttribute("eventSendToUser",eventSendToUser);
		return "/admin/jtzs/eventSendToUser/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		EventSendToUser eventSendToUser = (EventSendToUser)eventSendToUserManager.getById(id);
		model.addAttribute("eventSendToUser",eventSendToUser);
		return "/admin/jtzs/eventSendToUser/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid EventSendToUser eventSendToUser,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/eventSendToUser/edit";
		}
		
		eventSendToUserManager.update(eventSendToUser);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 保存发送关系 */
	@RequestMapping(value="/saves")
	public String save(HttpServletRequest request) throws Exception {
		String ids   = paramUtils.getParameter(request, "idss");;
		int userRole = paramUtils.getIntParameter(request, "role", 0);
		long eventId = paramUtils.getLongParameter(request, "eventId", 0L);
		if(eventId!=0){
		String [] str = ids.split(",");
		 for(int i=0;i<str.length;i++){
			 EventSendToUser e = new EventSendToUser();
			 if(eventSendToUserManager.sendFlag(userRole, Long.valueOf(str[i]).longValue(), eventId)){
			 e.setEventId(eventId);
			 e.setUserId(Long.valueOf(str[i]).longValue());
			 e.setUserRole(userRole);
			 e.setAddTime(System.currentTimeMillis());
			 eventSendToUserManager.saveOrUpdate(e);
			 }
		 }
		}
		Flash.current().success(SAVE_SUCCESS);
		return "redirect:/admin/jtzs/eventSendToUser/list?eventId="+eventId;
	}
	
	
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		eventSendToUserManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 单个移除 */
	@RequestMapping(value="/{id}/remove")
	public String remove(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request,HttpServletResponse response) {
		long eventId = paramUtils.getLongParameter(request, "eventId" ,0L);
		eventSendToUserManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return "redirect:/admin/jtzs/eventSendToUser/list?eventId="+eventId;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			eventSendToUserManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
}

