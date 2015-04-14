/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */


package com.xes.jtzs.controller;

import static com.up72.auth.AuthUtils.userUtils;
import static com.up72.common.CommonUtils.paramUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


import com.up72.auth.member.dao.AuthUserDao;
import com.up72.base.BaseRestSpringController;
import com.up72.base.UserDetails;
import com.up72.framework.page.Page;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.framework.web.scope.Flash;
import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.model.Event;
import com.xes.jtzs.service.EventManager;
import com.xes.jtzs.service.ProvinceManager;
import com.xes.jtzs.vo.query.EventQuery;
/**
 * 数据提取跳转
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/jtzs/event")
public class AdminEventController extends BaseRestSpringController<Event,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private EventManager eventManager;
	
	@Autowired
	private ProvinceManager provinceManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/event";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setEventManager(EventManager manager) {
		this.eventManager = manager;
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
	public String index(ModelMap model,EventQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Page page = this.eventManager.findPage(query);
		
		this.addShowLabelAttrbite("/admin/jtzs/event", request, response);
		model.addAttribute("provinceList",provinceManager.getShowListByInitial());
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/event/index";
	}
	
	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Event event,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("provinceList",provinceManager.getShowListByInitial());
		model.addAttribute("event",event);
		return "/admin/jtzs/event/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid Event event,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/event/new";
		}
		//设置操作人为登陆用户
		UserDetails currentUser = null;
		       // 先从cookie中查找
		if (null == currentUser) {
			String userName = userUtils.getLoginName(request, null);
		if (userName != null && !("".equals(userName))) {
			AuthUserDao memberDao = (AuthUserDao) ApplicationContextHolder
					.getBean("authUserDao");
			currentUser = memberDao.findByProperty("userName", userName);
			event.setSendUser(currentUser.getId());
		}
		}
		String imgPath = request.getParameter("imgPath");
		if(imgPath==null||imgPath==""){
			event.setImgPath("/pages/jtzs/images/img.jpg");
		}
		
		
		//设置操作人为登陆用户--end
		String sendTo1 = request.getParameter("sendTo1");
		String sendTo2 = request.getParameter("sendTo2");
		String sendTo3 = request.getParameter("sendTo3");
		String startTimeString = request.getParameter("startTimeString");
		String endTimeString = request.getParameter("endTimeString");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(sendTo1!=""&&sendTo2!=""&&sendTo1!=null&&sendTo2!=null){
			event.setSendTo(null);
		}else{
			if(sendTo1!=null&&sendTo1!=null){
				event.setSendTo(JTZSConstants.ROLE_STUDENT);
			}else if(sendTo2!=null&&sendTo2!=null){
				event.setSendTo(JTZSConstants.ROLE_TEACHER);
			}else if(sendTo3!=null&&sendTo3!=null){
				event.setSendTo(JTZSConstants.ROLE_SOMEONE);
			}
		}
		if(startTimeString!=null&&startTimeString.length()>0){
			Date date = sdf.parse(startTimeString);
			Long longtime = date.getTime();
			event.setStartTime(longtime);
		}
		if(endTimeString!=null&&endTimeString.length()>0){
			Date date = sdf.parse(endTimeString);
			Long longtime = date.getTime();
			event.setEndTime(longtime);
		}
		event.setSendStatus(JTZSConstants.SendStatus.WEIFASONG.getIndex());//未发送
		eventManager.save(event);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Event event = (Event)eventManager.getById(id);
		model.addAttribute("provinceList",provinceManager.getShowListByInitial());
		model.addAttribute("event",event);
		return "/admin/jtzs/event/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Event event = (Event)eventManager.getById(id);
		model.addAttribute("event",event);
		return "/admin/jtzs/event/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Event event = (Event)eventManager.getById(id);
		model.addAttribute("event",event);
		return "/admin/jtzs/event/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Event event,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/event/edit";
		}
		String sendTo1 = request.getParameter("sendTo1");
		String sendTo2 = request.getParameter("sendTo2");
		String sendTo3 = request.getParameter("sendTo3");
		String startTimeString = request.getParameter("startTimeString");
		String endTimeString = request.getParameter("endTimeString");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(sendTo1!=""&&sendTo2!=""&&sendTo1!=null&&sendTo2!=null){
			event.setSendTo(null);
		}else{
			if(sendTo1!=null&&sendTo1!=null){
				event.setSendTo(JTZSConstants.ROLE_STUDENT);
			}else if(sendTo2!=null&&sendTo2!=null){
				event.setSendTo(JTZSConstants.ROLE_TEACHER);
			}else if(sendTo3!=null&&sendTo3!=null){
				event.setSendTo(JTZSConstants.ROLE_SOMEONE);
			}
		}
		if(startTimeString!=null&&startTimeString.length()>0){
			Date date = sdf.parse(startTimeString);
			Long longtime = date.getTime();
			event.setStartTime(longtime);
		}
		if(endTimeString!=null&&endTimeString.length()>0){
			Date date = sdf.parse(endTimeString);
			Long longtime = date.getTime();
			event.setEndTime(longtime);
		}
		String imgPath = request.getParameter("imgPath");
		if(imgPath==null||imgPath==""){
			event.setImgPath("/pages/jtzs/images/img.jpg");
		}
		event.setSendStatus(JTZSConstants.SendStatus.WEIFASONG.getIndex());//未发送
		eventManager.saveOrUpdate(event);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		eventManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	/** 单个删除 */
	@RequestMapping(value="/{id}/remove")
	public String remove(ModelMap model,@PathVariable java.lang.Long id) {
		eventManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	/** 单个发送 */
	@RequestMapping(value="/{id}/send")
	public String send(ModelMap model,@PathVariable java.lang.Long id) {
		Event event = (Event)eventManager.getById(id);
		event.setSendStatus(JTZSConstants.SendStatus.YIFASONG.getIndex());//已发送
		long time = System.currentTimeMillis();
		event.setSendTime(time);//设置发送时间
		eventManager.saveOrUpdate(event);
		Flash.current().success(SEND_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 单个冻结 */
	@RequestMapping(value="/{id}/freeze")
	public String freeze(ModelMap model,@PathVariable java.lang.Long id) {
		Event event = (Event)eventManager.getById(id);
		event.setSendStatus(JTZSConstants.SendStatus.DONGJIE.getIndex());//已冻结
		eventManager.saveOrUpdate(event);
		Flash.current().success(FREEZE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			eventManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 预览 */
	@RequestMapping(value="/preview")
	public String preview(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		 Long eventId = paramUtils.getLongParameter(request, "eventId", 0l);
		 Event event = eventManager.getById(eventId);
	     model.addAttribute("event", event);
		return "/admin/preview/compose/preview";
	}
}

