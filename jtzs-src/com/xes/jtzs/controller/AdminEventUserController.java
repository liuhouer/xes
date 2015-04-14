/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */


package com.xes.jtzs.controller;

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

import com.up72.base.BaseRestSpringController;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;
import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.model.Event;
import com.xes.jtzs.model.EventUser;
import com.xes.jtzs.model.Score;
import com.xes.jtzs.model.ScoreLog;
import com.xes.jtzs.service.EventManager;
import com.xes.jtzs.service.EventUserManager;
import com.xes.jtzs.service.ScoreLogManager;
import com.xes.jtzs.service.ScoreManager;
import com.xes.jtzs.vo.query.EventUserQuery;
/**
 * 数据提取跳转
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/jtzs/eventUser")
public class AdminEventUserController extends BaseRestSpringController<EventUser,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private EventUserManager eventUserManager;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScoreManager scoreManager;
	@Autowired
	private ScoreLogManager scoreLogManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/eventUser";
	
	private final String ECPASS_ACTION = "redirect:/jtzs/commonData/eventList";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setEventUserManager(EventUserManager manager) {
		this.eventUserManager = manager;
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
	public String index(ModelMap model,EventUserQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Page page = this.eventUserManager.findPage(query);
		
		 this.addShowLabelAttrbite("/admin/jtzs/eventUser", request, response);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/eventUser/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		EventUser eventUser = (EventUser)eventUserManager.getById(id);
		model.addAttribute("eventUser",eventUser);
		return "/admin/jtzs/eventUser/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,EventUser eventUser,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("eventUser",eventUser);
		return "/admin/jtzs/eventUser/new";
	}
	
	/** 保存事件-答题的关系验证信息  */
	@RequestMapping(value="/saveEC",method=RequestMethod.POST)
	public String saveEC(ModelMap model,@Valid EventUser eventUser,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/eventUser/new";
		}
		String ids = paramUtils.getParameter(request, "aa" );
		
//		String [] str =ids.split(",");
//		
//		for(String s:str){
//		}
		
		String answer = ids.substring(0, ids.length()-1);
		long eventId = paramUtils.getLongParameter(request, "eventId", 0L);
		long userId = paramUtils.getLongParameter(request, "userId", 0L);
		Event event = eventManager.getById(eventId);
		
		
		long starttime = event.getStartTime();
		long endtime = event.getEndTime();
		int role = event.getSendTo();
		long time = System.currentTimeMillis();
		String uriflag = "";
//		if(role==JTZSConstants.ROLE_STUDENT){
//			uriflag = "studentId";
//		}else if(role == JTZSConstants.ROLE_TEACHER){
//			uriflag = "teacherId";
//		}
		
		if(time<starttime||time>endtime){//提交时间过期
			Flash.current().success("提交时间已过期!");  
//			return "redirect:/jtzs/commonData/"+eventid+"/eventShow?role="+role;
		}
		boolean flag = eventUserManager.isUnique(eventId, userId, role);
		if(flag!=true){                            //判断唯一性
			Flash.current().success("问题已被处理!"); 
//			return "redirect:/jtzs/commonData/"+eventid+"/eventShow?role="+role;
		}
		
       
        eventUser.setAddTime(time);
        eventUser.setEventId(eventId);
        eventUser.setUserRole(role);
        eventUser.setUserId(userId);
        eventUserManager.save(eventUser);
//        Event event =  eventManager.getById(eventid);
        Score score =  null;//scoreManager.getScoreByRole(userId, userRole);
        if(event!=null){
	        if(answer.equals(event.getAnswer())){ //答对了，加分
	        	
	        	ScoreLog scorelog =  new ScoreLog();
	        	scorelog.setUserId(userId);
	        	scorelog.setUserRole(role);
	        	scorelog.setScore(event.getAddScore());
	        	scorelog.setAddTime(time);
	        	scorelog.setContent("答对了一道题，加"+event.getAddScore()+"分");
	        	scoreLogManager.save(scorelog);
	        	
	        	score.setScore(score.getScore()+event.getAddScore());
	        	scoreManager.saveOrUpdate(score);
			}else{
				
				ScoreLog scorelog =  new ScoreLog();
	        	scorelog.setUserId(userId);
	        	scorelog.setUserRole(role);
	        	scorelog.setScore(event.getDelScore());
	        	scorelog.setAddTime(time);
	        	scorelog.setContent("答错了一道题，减"+event.getDelScore()+"分");
	        	scoreLogManager.save(scorelog);
	        	
				score.setScore(score.getScore()-event.getDelScore());
				scoreManager.saveOrUpdate(score);
			}
        }
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		
		return "redirect:/jtzs/commonData/eventList?"+uriflag+"="+userId;
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid EventUser eventUser,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/eventUser/new";
		}
		
		eventUserManager.save(eventUser);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		EventUser eventUser = (EventUser)eventUserManager.getById(id);
		model.addAttribute("eventUser",eventUser);
		return "/admin/jtzs/eventUser/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		EventUser eventUser = (EventUser)eventUserManager.getById(id);
		model.addAttribute("eventUser",eventUser);
		return "/admin/jtzs/eventUser/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		EventUser eventUser = (EventUser)eventUserManager.getById(id);
		model.addAttribute("eventUser",eventUser);
		return "/admin/jtzs/eventUser/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid EventUser eventUser,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/eventUser/edit";
		}
		
		eventUserManager.update(eventUser);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		eventUserManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			eventUserManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
}

