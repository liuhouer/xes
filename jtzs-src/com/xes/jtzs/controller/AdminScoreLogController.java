/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */


package com.xes.jtzs.controller;

import static com.up72.common.CommonUtils.*;

import com.up72.common.CommonConstants;
import com.up72.common.excel.ColumnConfig;
import com.up72.common.excel.ExcelExportPoiUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.up72.base.BaseRestSpringController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.hssf.util.HSSFColor;
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
@RequestMapping("/admin/jtzs/scoreLog")
public class AdminScoreLogController extends BaseRestSpringController<ScoreLog,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private ScoreLogManager scoreLogManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/scoreLog";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setScoreLogManager(ScoreLogManager manager) {
		this.scoreLogManager = manager;
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
	public String index(ModelMap model,ScoreLogQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Page page = this.scoreLogManager.findPage(query);
		
		 this.addShowLabelAttrbite("/admin/jtzs/scoreLog", request, response);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/scoreLog/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		ScoreLog scoreLog = (ScoreLog)scoreLogManager.getById(id);
		model.addAttribute("scoreLog",scoreLog);
		return "/admin/jtzs/scoreLog/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,ScoreLog scoreLog,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("scoreLog",scoreLog);
		return "/admin/jtzs/scoreLog/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid ScoreLog scoreLog,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/scoreLog/new";
		}
		
		scoreLogManager.save(scoreLog);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		ScoreLog scoreLog = (ScoreLog)scoreLogManager.getById(id);
		model.addAttribute("scoreLog",scoreLog);
		return "/admin/jtzs/scoreLog/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		ScoreLog scoreLog = (ScoreLog)scoreLogManager.getById(id);
		model.addAttribute("scoreLog",scoreLog);
		return "/admin/jtzs/scoreLog/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		ScoreLog scoreLog = (ScoreLog)scoreLogManager.getById(id);
		model.addAttribute("scoreLog",scoreLog);
		return "/admin/jtzs/scoreLog/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid ScoreLog scoreLog,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/scoreLog/edit";
		}
		
		scoreLogManager.update(scoreLog);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		scoreLogManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			scoreLogManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 导出数据 */
	@RequestMapping(value="/exportData")
	public String exportData(ModelMap model,ScoreLogQuery query,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<ScoreLog> dataList = new LinkedList<ScoreLog>();
		int userRole = paramUtils.getIntParameter(request, "userRole", 0);
        long userId =  paramUtils.getLongParameter(request, "userId", 0);
        String startTime = paramUtils.getParameter(request, "startTime");
        String endTime = paramUtils.getParameter(request, "endTime");
        if(userId!=0){
        	query.setUserId(userId);
        }
        query.setUserRole(userRole);
        query.setStartTime(startTime);
        query.setEndTime(endTime);
        Page page = this.scoreLogManager.findPage(query);
		int count  = page.getLastPageNumber();
		if(count>=1){
			for(int i=1;i<=count;i++){
			  query.setPageNumber(i);
			  List<ScoreLog> list = page.getResult();
			  dataList.addAll( list);
			}
		}
		
		List<ColumnConfig> configList = new ArrayList<ColumnConfig>();
		configList.add(new ColumnConfig("leixing", "积分类型"));
		configList.add(new ColumnConfig("content", "消费内容"));
		configList.add(new ColumnConfig("score", "积分"));
		configList.add(new ColumnConfig("addTimeStrs", "消费时间"));
		configList.add(new ColumnConfig("remark", "备注"));

        long currenttime = System.currentTimeMillis();
        String filePath = "D:/temp/"+"ScoreLogList_"+currenttime+".xls";
        String fileName = "积分明细导出文件.xls";
		ExcelExportPoiUtil eep = new ExcelExportPoiUtil(filePath, 0,
				configList);

		eep.exportHeader(new HSSFColor.BLACK(),
				new HSSFColor.WHITE());

		eep.export(dataList);
		eep.save();
		request.setAttribute("fileName", fileName);
		request.setAttribute("filePath", filePath);
		request.getRequestDispatcher("/fileDownload/commonDownload").forward(request,response);
		
		this.addShowLabelAttrbite("/admin/jtzs/score/selectScoreLog", request, response);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/score/selectScoreLog";
	}
	
	
}

