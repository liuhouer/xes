/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */


package com.up72.auth.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.up72.auth.model.WorkGroup;
import com.up72.auth.service.WorkGroupMemberManager;
import com.up72.base.BaseRestSpringController;

/**
 * 权限工作组（部门）数据提取跳转
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/ucenter/workGroupMember")
public class WorkGroupMemberController extends BaseRestSpringController<WorkGroup,java.lang.Long>{
	
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private WorkGroupMemberManager workGroupMemberManager;
	
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
	
	
	/**
	 * 根据用户组查找指定用户组下的角色列表
	 * @author bxmen
	 * @param id 为 用户组ID
	 * @throws IOException 
	 */
//	@RequestMapping(value="/{memberId}/isHaveGroup")
//	@ResponseBody
//	public String isHaveGroup(ModelMap model, @PathVariable Long memberId,HttpServletRequest request) throws IOException{
//		HashMap<String,Object> params = new HashMap<String, Object>();
//		params.put("memberId", memberId);
//		List<WorkGroupMember> list = workGroupMemberManager.findList(params,1,null);
//		HashMap<String,String> jsonMap = new HashMap<String,String>();
//		if(null != list && list.size() > 0){
//			WorkGroupMember wgm = (WorkGroupMember)list.get(0);
//			Integer status = wgm.getStatus();
//			if(CommonConstants.workGroupMember.STATUS_PASS.equals(status)){
//				jsonMap.put("result","success");
//			}else{
//				jsonMap.put("result","error");
//				jsonMap.put("message",stringUtil.encode("该用户还未通过部门审核"));
//			}
//		}else{
//			jsonMap.put("result","error");
//			jsonMap.put("message",stringUtil.encode("该用户还未加入任何部门"));
//		}
//		return JsonUtil.object2Json(jsonMap);
//	}
	
}

