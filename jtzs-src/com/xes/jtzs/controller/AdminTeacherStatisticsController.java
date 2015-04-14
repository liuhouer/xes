/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */


package com.xes.jtzs.controller;

import static com.up72.common.CommonUtils.dateUtils;
import static com.up72.common.CommonUtils.objectUtils;
import static com.up72.common.CommonUtils.paramUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.hssf.util.HSSFColor;
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
import com.up72.common.excel.ColumnConfig;
import com.up72.common.excel.ExcelExportPoiUtil;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;
import com.xes.jtzs.model.Datas;
import com.xes.jtzs.model.TeacherStatistics;
import com.xes.jtzs.service.TeacherStatisticsManager;
import com.xes.jtzs.vo.query.TeacherStatisticsQuery;
/**
 * 数据提取跳转
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/jtzs/teacherStatistics")
public class AdminTeacherStatisticsController extends BaseRestSpringController<TeacherStatistics,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private TeacherStatisticsManager teacherStatisticsManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/teacherStatistics";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setTeacherStatisticsManager(TeacherStatisticsManager manager) {
		this.teacherStatisticsManager = manager;
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
	public String index(ModelMap model,TeacherStatisticsQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String startAddTimeString = paramUtils.getParameter(request, "startAddTimeString", "");
		String stopAddTimeString = paramUtils.getParameter(request, "stopAddTimeString", "");
		String loginName = paramUtils.getParameter(request, "loginName", "");
		Integer level = paramUtils.getIntParameter(request, "level", 0);
		
		long startAddTime = 0;
		Date startAddTimeDate = null;
		if(objectUtils.isNotEmpty(startAddTimeString)){
			startAddTime = dateUtils.stringToLong(startAddTimeString + " 00:00:00");
			startAddTimeDate = dateUtils.longToDate(startAddTime);
		}
		long endAddTime = 0;
		Date stopAddTimeDate = null;
		if(objectUtils.isNotEmpty(stopAddTimeString)){
			endAddTime = dateUtils.stringToLong(stopAddTimeString + " 23:59:59");
			stopAddTimeDate = dateUtils.longToDate(endAddTime);
		}
		Page page = this.teacherStatisticsManager.findStatisticsPage(loginName, level, startAddTime, endAddTime,query.getPageNumber(),query.getPageSize(),query.getSortColumns());
		
		model.addAllAttributes(toModelMap(page, query));
		model.addAttribute("startAddTimeDate", startAddTimeDate);
		model.addAttribute("stopAddTimeDate", stopAddTimeDate);
		model.addAttribute("level", level);
		model.addAttribute("loginName", loginName);
		return "/admin/jtzs/teacherStatistics/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		TeacherStatistics teacherStatistics = (TeacherStatistics)teacherStatisticsManager.getById(id);
		model.addAttribute("teacherStatistics",teacherStatistics);
		return "/admin/jtzs/teacherStatistics/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,TeacherStatistics teacherStatistics,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("teacherStatistics",teacherStatistics);
		return "/admin/jtzs/teacherStatistics/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid TeacherStatistics teacherStatistics,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/teacherStatistics/new";
		}
		
		teacherStatisticsManager.save(teacherStatistics);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		TeacherStatistics teacherStatistics = (TeacherStatistics)teacherStatisticsManager.getById(id);
		model.addAttribute("teacherStatistics",teacherStatistics);
		return "/admin/jtzs/teacherStatistics/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		TeacherStatistics teacherStatistics = (TeacherStatistics)teacherStatisticsManager.getById(id);
		model.addAttribute("teacherStatistics",teacherStatistics);
		return "/admin/jtzs/teacherStatistics/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		TeacherStatistics teacherStatistics = (TeacherStatistics)teacherStatisticsManager.getById(id);
		model.addAttribute("teacherStatistics",teacherStatistics);
		return "/admin/jtzs/teacherStatistics/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid TeacherStatistics teacherStatistics,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/teacherStatistics/edit";
		}
		
		teacherStatisticsManager.update(teacherStatistics);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		teacherStatisticsManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			teacherStatisticsManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 导出数据 */
	@RequestMapping(value="/exportData")
	public String exportData(ModelMap model,TeacherStatisticsQuery query,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Datas> dataList = new ArrayList<Datas>();
		String startAddTimeString = paramUtils.getParameter(request, "startAddTimeString", "");
		String stopAddTimeString = paramUtils.getParameter(request, "stopAddTimeString", "");
		String loginName = paramUtils.getParameter(request, "loginName", "");
		Integer level = paramUtils.getIntParameter(request, "level", 0);
		
		long startAddTime = 0;
		Date startAddTimeDate = null;
		if(objectUtils.isNotEmpty(startAddTimeString)){
			startAddTime = dateUtils.stringToLong(startAddTimeString + " 00:00:00");
			startAddTimeDate = dateUtils.longToDate(startAddTime);
		}
		long endAddTime = 0;
		Date stopAddTimeDate = null;
		if(objectUtils.isNotEmpty(stopAddTimeString)){
			endAddTime = dateUtils.stringToLong(stopAddTimeString + " 23:59:59");
			stopAddTimeDate = dateUtils.longToDate(endAddTime);
		}
		Page page = this.teacherStatisticsManager.findStatisticsPage(loginName, level, startAddTime, endAddTime,query.getPageNumber(),query.getPageSize(),query.getSortColumns());

		int count  = page.getLastPageNumber();
		if(count>=1){
			for(int i=1;i<=count;i++){
			  query.setPageNumber(i);
			  List<Map<String,String>> list = page.getResult();
			  for(int k=0;k<list.size();k++){
				 Map<String,String>  m =   list.get(k);
				 Datas d = new Datas();
				 m.get("satisfy");
				 
				 
				  if(m.get("satisfy")==null||m.get("satisfy").equals("")){
					  d.setSatisfy(0);
				  }else{
					  d.setSatisfy(Integer.parseInt(m.get("satisfy")));
				  }
				  if(m.get("unsatisfy")==null||m.get("unsatisfy").equals("")){
					  d.setUnsatisfy(0);
				  }else{
					  d.setUnsatisfy(Integer.parseInt(m.get("unsatisfy")));
				  }
				  if(m.get("showNum")==null||m.get("showNum").equals("")){
					  d.setShowNum(0);
				  }else{
					  d.setShowNum(Integer.parseInt(m.get("showNum")));
				  }
				  if(m.get("answerNum")==null||m.get("answerNum").equals("")){
					  d.setAnswerNum(0);
				  }else{
					  d.setAnswerNum(Integer.parseInt(m.get("answerNum")));
				  }
				  if(m.get("twentyMinNum")==null||m.get("twentyMinNum").equals("")){
					  d.setTwentyMinNum(0);
				  }else{
					  d.setTwentyMinNum(Integer.parseInt(m.get("twentyMinNum")));
				  }
				  if(m.get("halfHourNum")==null||m.get("halfHourNum").equals("")){
					  d.setHalfHourNum(0);
				  }else{
					  d.setHalfHourNum(Integer.parseInt(m.get("halfHourNum")));
				  }
				  if(m.get("oneHourNum")==null||m.get("oneHourNum").equals("")){
					  d.setOneHourNum(0);
				  }else{
					  d.setOneHourNum(Integer.parseInt(m.get("oneHourNum")));
				  }
				  if(m.get("expertNum")==null||m.get("expertNum").equals("")){
					  d.setExpertNum(0);
				  }else{
					  d.setExpertNum(Integer.parseInt(m.get("expertNum")));
				  }
				  if(m.get("quitNum")==null||m.get("quitNum").equals("")){
					  d.setQuitNum(0);
				  }else{
					  d.setQuitNum(Integer.parseInt(m.get("quitNum")));
				  }
				  if(m.get("level")==null||m.get("level").equals("")){
					  d.setLevel(0);
				  }else{
					  d.setLevel(Integer.parseInt(m.get("level")));
				  }
				  if(m.get("loginName")==null||m.get("loginName").equals("")){
					  d.setLoginName("");
				  }else{
					  d.setLoginName(m.get("loginName"));
				  }
				 
				  dataList.add(d);
			 }
			  
			  
			  
			}
		}
		
		List<ColumnConfig> configList = new ArrayList<ColumnConfig>();
		configList.add(new ColumnConfig("levelStr", "     等级                           "));
		configList.add(new ColumnConfig("loginName", "    登陆账户                    "));
		configList.add(new ColumnConfig("satisfy", "      满意数                             "));
		configList.add(new ColumnConfig("unsatisfy", "    不满意数                          "));
		configList.add(new ColumnConfig("showNum", "      浏览量                             "));
		configList.add(new ColumnConfig("answerNum", "    回答总量                      "));
		configList.add(new ColumnConfig("twentyMinNum", " 20分钟回答量                "));
		configList.add(new ColumnConfig("halfHourNum", "  30分钟回答量              "));
		configList.add(new ColumnConfig("oneHourNum", "   60分钟回答量              "));
		configList.add(new ColumnConfig("expertNum", "    专家作答量                "       ));
		configList.add(new ColumnConfig("quitNum", "      老师放弃数                     "       ));

        long currenttime = System.currentTimeMillis();
        String filePath = "D:/temp/"+"TeacherStatisticsList_"+currenttime+".xls";
        String fileName = "老师作答统计列表导出文件.xls";
		ExcelExportPoiUtil eep = new ExcelExportPoiUtil(filePath, 0,
				configList);

		eep.exportHeader(new HSSFColor.BLACK(),
				new HSSFColor.WHITE());

		eep.export(dataList);
		eep.save();
        
		request.setAttribute("fileName", fileName);
		request.setAttribute("filePath", filePath);
		request.getRequestDispatcher("/fileDownload/commonDownload").forward(request,response);
//		
		model.addAllAttributes(toModelMap(page, query));
		model.addAttribute("startAddTimeDate", startAddTimeDate);
		model.addAttribute("stopAddTimeDate", stopAddTimeDate);
		model.addAttribute("level", level);
		model.addAttribute("loginName", loginName);
		return "/admin/jtzs/teacherStatistics/index";
	}
	
}

