/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */


package com.xes.jtzs.controller;

import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.paramUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.up72.base.BaseRestSpringController;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;
import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.model.Grade;
import com.xes.jtzs.model.GradeSubject;
import com.xes.jtzs.model.Subject;
import com.xes.jtzs.service.GradeManager;
import com.xes.jtzs.service.GradeSubjectManager;
import com.xes.jtzs.service.SubjectManager;
import com.xes.jtzs.vo.query.SubjectQuery;
/**
 * 数据提取跳转
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/jtzs/subject")
public class AdminSubjectController extends BaseRestSpringController<Subject,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	@Autowired
	private GradeSubjectManager gradeSubjectManager;
	@Autowired
	private GradeManager gradeManager;
	private SubjectManager subjectManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/subject";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setSubjectManager(SubjectManager manager) {
		this.subjectManager = manager;
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
	public String index(ModelMap model,SubjectQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Page page = this.subjectManager.findPage(query);
		
		 this.addShowLabelAttrbite("/admin/jtzs/subject", request, response);
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/subject/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Subject subject = (Subject)subjectManager.getById(id);
		model.addAttribute("subject",subject);
		return "/admin/jtzs/subject/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Subject subject,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("subject",subject);
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());		
		return "/admin/jtzs/subject/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid Subject subject,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/subject/new";
		}
		
		subjectManager.save(subject);
		subject.setSort(subject.getId());
		subjectManager.update(subject);
		gradeSubjectManager.addGradeSubject(subject);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Subject subject = (Subject)subjectManager.getById(id);
		model.addAttribute("subject",subject);
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());		
		return "/admin/jtzs/subject/edit";
	}
	
	/** 编辑关系 */
	@RequestMapping(value="/{id}/editRela")
	public String editRela(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Subject subject = (Subject)subjectManager.getById(id);
		model.addAttribute("subject",subject);
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());
		ArrayList<GradeSubject> gradeSubjectList = (ArrayList) gradeSubjectManager.getListBySubId(id);
		model.addAttribute("gradeSubjectList",gradeSubjectList);
		return "/admin/jtzs/subject/editRela";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Subject subject = (Subject)subjectManager.getById(id);
		model.addAttribute("subject",subject);
		return "/admin/jtzs/subject/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Subject subject = (Subject)subjectManager.getById(id);
		model.addAttribute("subject",subject);
		return "/admin/jtzs/subject/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Subject subject,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/subject/edit";
		}
		subjectManager.update(subject);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 更新状态*/
	@RequestMapping(value="/{id}/upstatus",method=RequestMethod.POST)
	public String upstatus(ModelMap model,@PathVariable java.lang.Long id,@Valid Subject subject,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/subject/edit";
		}
		long[] gradeId = paramUtils.getLongParameters(request, "gradeId", 0L);
		gradeSubjectManager.clearStatus(id);
		for (int i = 0; i < gradeId.length; i++) {
			if(gradeId[i] > 0){
				gradeSubjectManager.updateStatus(id, gradeId[i]);
			}
		}
//		subjectManager.update(subject);
//		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		subjectManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			subjectManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	
	
	/** 
	 * 判断是否唯一
	 * @author liutongling
	 **/
	@RequestMapping(value="/isUnique")
	@ResponseBody
	public boolean isUnique(@Valid Subject subject) throws Exception {
		boolean b = subjectManager.isUniqueName(subject.getName(),subject.getId());
		System.out.println(b);
		return b;
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

		Subject subject = subjectManager.getById(id);
		if(null != subject){
			if(JTZSConstants.Pubilc.ENABLED.getIndex()==subject.getStatus()){
				subject.setStatus(JTZSConstants.Pubilc.DISABLE.getIndex());
			}else{
				subject.setStatus(JTZSConstants.Pubilc.ENABLED.getIndex());
			}
			subjectManager.update(subject);
			jsonMap.put("valid", subject.getStatus());
			status = SUCCESS;
		}
		jsonMap.put("status", status);
		return jsonUtil.object2Json(jsonMap);
	}
	
}

