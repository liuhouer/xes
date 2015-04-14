/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */


package com.xes.jtzs.controller;

import static com.up72.common.CommonUtils.*;

import com.up72.common.CommonConstants;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/admin/jtzs/grade")
public class AdminGradeController extends BaseRestSpringController<Grade,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	@Autowired
	private GradeSubjectManager gradeSubjectManager;
	private GradeManager gradeManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/grade";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setGradeManager(GradeManager manager) {
		this.gradeManager = manager;
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
	public String index(ModelMap model,GradeQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Page page = this.gradeManager.findPage(query);
		
		 this.addShowLabelAttrbite("/admin/jtzs/grade", request, response);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/grade/index";
	}
	
	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Grade grade,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("grade",grade);
		model.addAttribute("divisionArray",JTZSConstants.Division.values());
		return "/admin/jtzs/grade/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid Grade grade,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/grade/new";
		}
		
		gradeManager.save(grade);
		grade.setSort(grade.getId());
		gradeManager.update(grade);
		gradeSubjectManager.addGradeSubject(grade);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Grade grade = (Grade)gradeManager.getById(id);
		model.addAttribute("grade",grade);
		model.addAttribute("divisionArray",JTZSConstants.Division.values());
		return "/admin/jtzs/grade/edit";
	}
	/** 编辑关系 */
	@RequestMapping(value="/{id}/editRela")
	public String editRela(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Grade grade = (Grade)gradeManager.getById(id);
		model.addAttribute("grade",grade);
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());
		ArrayList<GradeSubject> gradeSubjectList = (ArrayList) gradeSubjectManager.getListByGradeId(id);
		model.addAttribute("gradeSubjectList",gradeSubjectList);
		return "/admin/jtzs/grade/editRela";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Grade grade,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/grade/edit";
		}
		
		gradeManager.update(grade);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/** 更新状态*/
	@RequestMapping(value="/{id}/upstatus",method=RequestMethod.POST)
	public String upstatus(ModelMap model,@PathVariable java.lang.Long id,@Valid Grade grade,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/grade/edit";
		}
		long[] subjectId = paramUtils.getLongParameters(request, "subjectId", 0L);
		gradeSubjectManager.clearStatusByGrade(id);
		for (int i = 0; i < subjectId.length; i++) {
			if(subjectId[i] > 0){
				gradeSubjectManager.updateStatus(subjectId[i],id);
			}
		}
//		subjectManager.update(grade);
//		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 
	 * 判断是否唯一
	 * @author liutongling
	 **/
	@RequestMapping(value="/isUnique")
	@ResponseBody
	public boolean isUnique(@Valid Grade grade) throws Exception {
		return gradeManager.isUniqueName(grade.getName(),grade.getId(),grade.getDivision());
	}
}

