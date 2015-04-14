/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */


package com.xes.jtzs.controller;

import static com.up72.common.CommonUtils.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.up72.base.BaseRestSpringController;
import com.up72.common.excel.ColumnConfig;
import com.up72.common.excel.ExcelExportPoiUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.hssf.util.HSSFColor;
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

import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.JTZSConstants.FreeCycle;
import com.xes.jtzs.model.*;
import com.xes.jtzs.service.*;
import com.xes.jtzs.vo.query.*;
/**
 * 数据提取跳转
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/jtzs/teacher")
public class AdminTeacherController extends BaseRestSpringController<Teacher,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	@Autowired
	private GradeManager gradeManager;
	@Autowired
	private SubjectManager subjectManager;
	@Autowired
	private ProvinceManager provinceManager;
	@Autowired
	private StudentManager studentManager;
	private TeacherManager teacherManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/teacher";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setTeacherManager(TeacherManager manager) {
		this.teacherManager = manager;
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
	public String index(ModelMap model,TeacherQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Page page = this.teacherManager.findPage(query);
		this.addShowLabelAttrbite("/admin/jtzs/teacher", request, response);
		model.addAttribute("statusArray",JTZSConstants.Status.values());
		model.addAttribute("provinceList",provinceManager.getShowListBySort());
		model.addAttribute("gradeList",gradeManager.getGradeListBySort());
		model.addAttribute("subjectList",subjectManager.findAll());
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/teacher/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Teacher teacher = (Teacher)teacherManager.getById(id);
		model.addAttribute("teacher",teacher);
		return "/admin/jtzs/teacher/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Teacher teacher,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("teacher",teacher);
		model.addAttribute("statusArray",JTZSConstants.Status.values());
		model.addAttribute("provinceList",provinceManager.getShowListBySort());
		model.addAttribute("sexArray",JTZSConstants.Sex.values());
		model.addAttribute("gradeList",gradeManager.getGradeListBySort());
		model.addAttribute("subjectList",subjectManager.findAll());
		return "/admin/jtzs/teacher/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid Teacher teacher,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/teacher/new";
		}
		
		if(null == teacher.getImgPath()){
			if(teacher.getSex()==JTZSConstants.Sex.WOMEN.getIndex()){
				teacher.setImgPath(JTZSConstants.DEFOULT_IMG_WOMAN);
			}else{
				teacher.setImgPath(JTZSConstants.DEFOULT_IMG_MAN);
			}
		}
		
		long[] expertGradeId = paramUtils.getLongParameters(request, "expertGradeId", 0L);
		String expertGradeIds = "";
		for (int i = 0; i < expertGradeId.length; i++) {
			if(expertGradeId[i] > 0){
				if(i != 0){
					expertGradeIds+=",";
				}
				expertGradeIds+="["+expertGradeId[i]+"]";
			}
		}
		teacher.setExpertGradeIds(expertGradeIds);
		if(null == teacher.getPassword()){
			teacher.setPassword("111111");
		}
		teacher.setPassword(encryptUtil.md5(teacher.getPassword()));
		//空闲时间
		String[] freeCycles = paramUtils.getParameters(request, "freeCycles", "0");
		if(null!=freeCycles){
			StringBuilder freeCycleStr = new StringBuilder();
			for (FreeCycle freeCycle : JTZSConstants.FreeCycle.values()) {
				boolean isFree = false;
				for (String sFreeCycle: freeCycles) {
					if(Teacher.isFree(sFreeCycle, freeCycle.getIndex())){
						isFree = true;
						break;
					}
				}
				if(isFree){
					freeCycleStr.append("1");
				}else{
					freeCycleStr.append("0");
				}
			}
			teacher.setFreeCycle(freeCycleStr.toString());
		}
		String freeStartTimeString = paramUtils.getParameter(request, "freeStartTimeString", "");
		String freeStopTimeString = paramUtils.getParameter(request, "freeStopTimeString", "");
		if(objectUtils.isNotEmpty(freeStartTimeString) && objectUtils.isNotEmpty(freeStopTimeString)){
			long freeStartTime = dateUtils.timeToLong(freeStartTimeString);
			long freeStopTime = dateUtils.timeToLong(freeStopTimeString);
			teacher.setFreeStartTime(freeStartTime);
			teacher.setFreeStopTime(freeStopTime);
		}
		
		String validStartTimeString = paramUtils.getParameter(request, "validStartTimeString", "");
		String validStopTimeString = paramUtils.getParameter(request, "validStopTimeString", "");
		if(objectUtils.isNotEmpty(validStartTimeString) && objectUtils.isNotEmpty(validStopTimeString)){
			Long startTime = DateUtils.stringToLong(validStartTimeString);
			teacher.setValidStartTime(startTime);
			Long endTime = DateUtils.stringToLong(validStopTimeString);
			teacher.setValidStopTime(endTime);
		}
		teacher.setAddTime(new Date().getTime());
		teacherManager.save(teacher);
		Flash.current().success("创建用户成功，默认密码为：111111"); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Teacher teacher = (Teacher)teacherManager.getById(id);
		model.addAttribute("teacher",teacher);
		model.addAttribute("statusArray",JTZSConstants.Status.values());
		model.addAttribute("sexArray",JTZSConstants.Sex.values());
		model.addAttribute("provinceList",provinceManager.getShowListBySort());
		model.addAttribute("gradeList",gradeManager.getGradeListBySort());
		model.addAttribute("subjectList",subjectManager.findAll());
		return "/admin/jtzs/teacher/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Teacher teacher = (Teacher)teacherManager.getById(id);
		model.addAttribute("teacher",teacher);
		return "/admin/jtzs/teacher/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Teacher teacher = (Teacher)teacherManager.getById(id);
		model.addAttribute("teacher",teacher);
		return "/admin/jtzs/teacher/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Teacher teacher,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/teacher/edit";
		}
		Teacher t = teacherManager.getById(id);
		if(null != t){
			long[] expertGradeId = paramUtils.getLongParameters(request, "expertGradeId", 0L);
			String expertGradeIds = "";
			for (int i = 0; i < expertGradeId.length; i++) {
				if(expertGradeId[i] > 0){
					if(i != 0){
						expertGradeIds+=",";
					}
					expertGradeIds+="["+expertGradeId[i]+"]";
				}
			}
			t.setExpertGradeIds(expertGradeIds);
			t.setExpertSubjectId(teacher.getExpertSubjectId());
			t.setImgPath(teacher.getImgPath());
			t.setStatus(teacher.getStatus());
			t.setSex(teacher.getSex());
			t.setNickName(teacher.getNickName());
			t.setRealName(teacher.getRealName());
			t.setProvinceId(teacher.getProvinceId());
			t.setLevel(teacher.getLevel());
			t.setAreaId(teacher.getAreaId());
			
			String[] freeCycles = paramUtils.getParameters(request, "freeCycles", "0");
			StringBuilder freeCycleStr = new StringBuilder();
			if(null != freeCycles){
				for (FreeCycle freeCycle : JTZSConstants.FreeCycle.values()) {
					boolean isFree = false;
					for (String sFreeCycle: freeCycles) {
						if(Teacher.isFree(sFreeCycle, freeCycle.getIndex())){
							isFree = true;
							break;
						}
					}
					if(isFree){
						freeCycleStr.append("1");
					}else{
						freeCycleStr.append("0");
					}
				}
			}
			t.setFreeCycle(freeCycleStr.toString());
			String freeStartTimeString = paramUtils.getParameter(request, "freeStartTimeString", "");
			String freeStopTimeString = paramUtils.getParameter(request, "freeStopTimeString", "");
			if(objectUtils.isNotEmpty(freeStartTimeString) && objectUtils.isNotEmpty(freeStopTimeString)){
				Long startTime = dateUtils.timeToLong(freeStartTimeString);
				Long endTime = dateUtils.timeToLong(freeStopTimeString);
				t.setFreeStartTime(startTime);
				t.setFreeStopTime(endTime);
			}
			String validStartTimeString = paramUtils.getParameter(request, "validStartTimeString", "");
			String validStopTimeString = paramUtils.getParameter(request, "validStopTimeString", "");
			if(objectUtils.isNotEmpty(validStartTimeString) && objectUtils.isNotEmpty(validStopTimeString)){
				Long startTime = DateUtils.stringToLong(validStartTimeString);
				Long endTime = DateUtils.stringToLong(validStopTimeString);
				t.setValidStartTime(startTime);
				t.setValidStopTime(endTime);
			}
			teacherManager.update(t);
			Flash.current().success(UPDATE_SUCCESS);
		}else{
			Flash.current().error("用户不存在");
		}
		
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		teacherManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			teacherManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 修改密码 */
	@RequestMapping(value="/{id}/editPassword")
	public String editPassword(ModelMap model,@PathVariable java.lang.Long id) {
		Teacher teacher = teacherManager.getById(id);
		model.addAttribute("teacher", teacher);
		return "/admin/jtzs/teacher/editPassword";
	}
	
	/** 修改密码 */
	@RequestMapping(value="/{id}/doEditPassword")
	public String editPassword(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request, HttpServletResponse response) {
		Teacher teacher = teacherManager.getById(id);
		if(null!=teacher){
			String password = paramUtils.getParameter(request, "password", "");
			String rePassword = paramUtils.getParameter(request, "rePassword", "");
			if(!"".equals(password)){
				if(password.equals(rePassword)){
					teacher.setPassword(encryptUtil.md5(password));
					Flash.current().success("密码修改成功");
				}else{
					Flash.current().success("两次密码不一致");
				}
			}else{
				Flash.current().success("密码不能为空");
			}
		}else{
			Flash.current().success("用户不存在");
		}
		return LIST_ACTION;
	}
	
	/** 是否合法时间 */
	@RequestMapping(value = "/isRightValidTime")
	@ResponseBody
	public boolean isRightValidTime(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean status = false;
		
		String validStartTimeString = paramUtils.getParameter(request, "validStartTimeString", "");
		String validStopTimeString = paramUtils.getParameter(request, "validStopTimeString", "");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Long validStartTime = null;
		Long validStopTime = null;
		try {
			Date dt1 = df.parse(validStartTimeString);
			validStartTime = dt1.getTime();
		} catch (Exception e) {
			validStartTime = 0L;
		}
		try {
			Date dt2 = df.parse(validStopTimeString);
			validStopTime = dt2.getTime();
		} catch (Exception e) {
			validStopTime = 0L;
		}
		if(validStartTime > 0 && validStopTime > 0 && validStopTime >= validStartTime){
			status = true;
		}
		return status;
	}
	
	/** 
	 * 设置启用禁用
	 * @author liutongling
	 **/
	@RequestMapping(value="/{id}/doValid")
	@ResponseBody
	public String doValid(@PathVariable java.lang.Long id,HttpServletRequest request) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String status = ERROR;

		Teacher teacher = teacherManager.getById(id);
		if(null != teacher){
			if(JTZSConstants.Status.NORMAL.getIndex()==teacher.getStatus()){
				teacher.setStatus(JTZSConstants.Status.FREEZE.getIndex());
			}else{
				teacher.setStatus(JTZSConstants.Status.NORMAL.getIndex());
			}
			teacherManager.update(teacher);
			jsonMap.put("valid", teacher.getStatus());
			status = SUCCESS;
		}
		jsonMap.put("status", status);
		return jsonUtil.object2Json(jsonMap);
	}
	
	/** 
	 * 判断是否唯一
	 * @author liutongling
	 **/
	@RequestMapping(value="/isUnique")
	@ResponseBody
	public boolean isUnique(@Valid Teacher teacher) throws Exception {
		return teacherManager.isUniqueLoginName(teacher.getLoginName(), teacher.getId()) && studentManager.isUniqueLoginName(teacher.getLoginName(), null);
	}
	
	/** 导出数据 */
	@RequestMapping(value="/exportData")
	public String exportData(ModelMap model,TeacherQuery query,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Teacher> dataList = new LinkedList<Teacher>();
		String loginName    = paramUtils.getParameter(request,"loginName") ;
		String realName    = paramUtils.getParameter(request,"realName") ;
		int status       = paramUtils.getIntParameter(request,"status", -1) ;
		long provinceId   = paramUtils.getLongParameter(request,"provinceId", 0) ;
		long expertGradeId      = paramUtils.getLongParameter(request,"expertGradeId", 0) ;
		long expertSubjectId     = paramUtils.getLongParameter(request,"expertSubjectId", 0) ;
        
		query.setLoginName(loginName);
		query.setRealName(realName);
		if(status!=-1){
        query.setStatus(status);
        }
		if(provinceId!=0){
        query.setProvinceId(provinceId);
		}
		if(expertGradeId!=0){
		query.setExpertGradeId(expertGradeId);	
		}
		if(expertSubjectId!=0){
		query.setExpertSubjectId(expertSubjectId);
		}
        Page page = this.teacherManager.findPage(query);
		int count  = page.getLastPageNumber();
		if(count>=1){
			for(int i=1;i<=count;i++){
			  query.setPageNumber(i);
			  List<Teacher> list = page.getResult();
			  dataList.addAll(list);
			}
		}
		
		List<ColumnConfig> configList = new ArrayList<ColumnConfig>();
		configList.add(new ColumnConfig("statusStr", "状态"));
		configList.add(new ColumnConfig("shengfen", "省份"));
		configList.add(new ColumnConfig("levelStr", "级别"));
		configList.add(new ColumnConfig("nickName", "昵称"));
		configList.add(new ColumnConfig("realName", "真实姓名"));
		configList.add(new ColumnConfig("loginName", "账户"));
		configList.add(new ColumnConfig("scnj", "擅长年级"));
		configList.add(new ColumnConfig("scxk", "擅长学科"));
		configList.add(new ColumnConfig("sexStr", "性别"));
		configList.add(new ColumnConfig("imgPath", "头像路径"));
		configList.add(new ColumnConfig("lastLoginTimeStrs", "最后登录时间"));
		configList.add(new ColumnConfig("addTimeStrs", "注册时间"));

        long currenttime = System.currentTimeMillis();
        String filePath = "D:/temp/"+"TeacherList_"+currenttime+".xls";
        String fileName = "老师列表导出文件.xls";
		ExcelExportPoiUtil eep = new ExcelExportPoiUtil(filePath, 0,
				configList);

		eep.exportHeader(new HSSFColor.BLACK(),
				new HSSFColor.WHITE());

		eep.export(dataList);
		eep.save();
		request.setAttribute("fileName", fileName);
		request.setAttribute("filePath", filePath);
		request.getRequestDispatcher("/fileDownload/commonDownload").forward(request,response);
		
		this.addShowLabelAttrbite("/admin/jtzs/teacher", request, response);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/teacher/index";
	}
	
}

