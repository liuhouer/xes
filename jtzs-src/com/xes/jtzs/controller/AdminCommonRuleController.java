/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */


package com.xes.jtzs.controller;

import static com.up72.common.CommonUtils.*;

import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.up72.auth.controller.AuthController;
import com.up72.auth.member.service.AuthUserManager;

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

import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;
import com.up72.util.DateUtils;

import com.up72.base.*;
import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.model.*;
import com.xes.jtzs.service.*;
import com.xes.jtzs.vo.query.*;/**
 * 数据提取跳转
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/jtzs/commonRule")
public class AdminCommonRuleController extends AuthController<CommonRule,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	@Autowired
	private AuthUserManager authUserManager;
	
	private CommonRuleManager commonRuleManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/commonRule";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setCommonRuleManager(CommonRuleManager manager) {
		this.commonRuleManager = manager;
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
	public String index(ModelMap model,CommonRuleQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Page page = this.commonRuleManager.findPage(query);
		
		 this.addShowLabelAttrbite("/admin/jtzs/commonRule", request, response);
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/commonRule/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		CommonRule commonRule = (CommonRule)commonRuleManager.getById(id);
		model.addAttribute("commonRule",commonRule);
		return "/admin/jtzs/commonRule/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,CommonRule commonRule,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("commonRule",commonRule);
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());
		return "/admin/jtzs/commonRule/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid CommonRule commonRule,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/commonRule/new";
		}
		com.up72.auth.UserUtils userUtils = new com.up72.auth.UserUtils();
		UserDetails loginUser = authUserManager.getMember(userUtils.getLoginName(request,0));
		if(null!=loginUser){
			commonRule.setEditTime(new Date().getTime());
			commonRule.setEditUserId(loginUser.getId());
			commonRuleManager.save(commonRule);
			Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		}
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		CommonRule commonRule = (CommonRule)commonRuleManager.getById(id);
		model.addAttribute("commonRule",commonRule);
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());
		return "/admin/jtzs/commonRule/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		CommonRule commonRule = (CommonRule)commonRuleManager.getById(id);
		model.addAttribute("commonRule",commonRule);
		return "/admin/jtzs/commonRule/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		CommonRule commonRule = (CommonRule)commonRuleManager.getById(id);
		model.addAttribute("commonRule",commonRule);
		return "/admin/jtzs/commonRule/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid CommonRule commonRule,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/commonRule/edit";
		}
		com.up72.auth.UserUtils userUtils = new com.up72.auth.UserUtils();
		UserDetails loginUser = authUserManager.getMember(userUtils.getLoginName(request,0));
		CommonRule r = commonRuleManager.getById(commonRule.getId());
		if(null!=loginUser && null!=r){
			String validStartTimeString = paramUtils.getParameter(request, "validStartTimeString", "");
			String validStopTimeString = paramUtils.getParameter(request, "validStopTimeString", "");
			String beginTimeString = paramUtils.getParameter(request, "beginTimeString", "");
			String endTimeString = paramUtils.getParameter(request, "endTimeString", "");
			int num = paramUtils.getIntParameter(request, "num", 0);
			int scoreType = paramUtils.getIntParameter(request, "scoreType", 0);
			int score = paramUtils.getIntParameter(request, "score", 0);
//			int minute = paramUtils.getIntParameter(request, "minute", 0);
			
			long validStartTime = 0;
			long validStopTime = 0;
			if(objectUtils.isNotEmpty(validStartTimeString) && objectUtils.isNotEmpty(validStopTimeString)){
				validStartTime = DateUtils.stringToLong(validStartTimeString);
				validStopTime = DateUtils.stringToLong(validStopTimeString);
			}
			if(validStopTime > 0 && validStartTime > 0){
				r.setValidStartTime(validStartTime);
				r.setValidStopTime(validStopTime);
			}else{
				r.setValidStartTime(null);
				r.setValidStopTime(null);
			}
			long beginTime = 0;
			long endTime = 0;
			if(objectUtils.isNotEmpty(beginTimeString) && objectUtils.isNotEmpty(endTimeString)){
				beginTime = dateUtils.timeToLong(beginTimeString);
				endTime = dateUtils.timeToLong(endTimeString);
			}
			if(beginTime > 0 && endTime > 0){
				r.setBeginTime(beginTime);
				r.setEndTime(endTime);
			}
//			r.setMinute(minute);
			r.setScoreType(scoreType);
			r.setScore(score);
			r.setNum(num);
			r.setEditTime(new Date().getTime());
			r.setEditUserId(loginUser.getId());
			commonRuleManager.update(r);
			if(r.getRuleType()== JTZSConstants.CommonRuleType.Expert.getIndex() || r.getRuleType()== JTZSConstants.CommonRuleType.QuestionScore.getIndex()){
				commonRuleManager.setCacheRuleById(r);
			}else{
				commonRuleManager.setCacheRuleByType(r);
			}
			Flash.current().success(UPDATE_SUCCESS);
		}
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
//		commonRuleManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
//		for(int i = 0; i < items.length; i++) {
//			commonRuleManager.removeById(items[i]);
//		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 
	 * 设置启用禁用
	 * @author liutongling
	 **/
	@RequestMapping(value="/{id}/doValid")
	@ResponseBody
	public String doValid(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String status = ERROR;

		CommonRule commonRule = commonRuleManager.getById(id);
		if(null != commonRule){
			if(JTZSConstants.Pubilc.ENABLED.getIndex()==commonRule.getStatus()){
				commonRule.setStatus(JTZSConstants.Pubilc.DISABLE.getIndex());
			}else{
				commonRule.setStatus(JTZSConstants.Pubilc.ENABLED.getIndex());
			}
			commonRuleManager.update(commonRule);
			if(commonRule.getRuleType()== JTZSConstants.CommonRuleType.Expert.getIndex() || commonRule.getRuleType()== JTZSConstants.CommonRuleType.QuestionScore.getIndex()){
				commonRuleManager.setCacheRuleById(commonRule);
			}else{
				commonRuleManager.setCacheRuleByType(commonRule);
			}
			jsonMap.put("valid", commonRule.getStatus());
			status = SUCCESS;
		}
		jsonMap.put("status", status);
		return jsonUtil.object2Json(jsonMap);
	}
	
}

