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

import com.up72.base.BaseRestSpringController;
import com.up72.common.CommonUtils;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;


import com.xes.jtzs.JTZSConstants;
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
@RequestMapping("/admin/jtzs/city")
public class AdminCityController extends BaseRestSpringController<City,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private CityManager cityManager;
	@Autowired
	private ProvinceManager provinceManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/city";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setCityManager(CityManager manager) {
		this.cityManager = manager;
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
	public String index(ModelMap model,CityQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Page page = this.cityManager.findPage(query);
		
		this.addShowLabelAttrbite("/admin/jtzs/city", request, response);
		model.addAttribute("provinceList",provinceManager.getShowListBySort());
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/city/index";
	}
	
	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,City city,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("city",city);
		model.addAttribute("provinceList",provinceManager.getShowListBySort());
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());
		return "/admin/jtzs/city/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid City city,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/city/new";
		}
		if(null != city.getName()){
			if(city.getName().length() > 50){
				Flash.current().error("内容不能超过50字");
			}else{
				city.setStatus(JTZSConstants.Pubilc.ENABLED.getIndex());
				city.setEnName(CommonUtils.pinyinUtil.paraseStringToPinyin(city.getName()));
				cityManager.save(city);
				city.setSort(city.getId());
				cityManager.update(city);
				Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
			}
		}else{
			Flash.current().error("内容不能为空");
		}
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		City city = (City)cityManager.getById(id);
		model.addAttribute("city",city);
		model.addAttribute("provinceList",provinceManager.getShowListBySort());
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());
		return "/admin/jtzs/city/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid City city,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/city/edit";
		}
		if(null != city.getName()){
			if(city.getName().length() > 50){
				Flash.current().error("内容不能超过50字");
			}else{
				city.setEnName(CommonUtils.pinyinUtil.paraseStringToPinyin(city.getName()));
				cityManager.update(city);
				Flash.current().success(UPDATE_SUCCESS);
			}
		}else{
			Flash.current().error("内容不能为空");
		}
		return LIST_ACTION;
	}
	
	/** 
	 * 判断是否唯一
	 * @author liutongling
	 **/
	@RequestMapping(value="/isUnique")
	@ResponseBody
	public boolean isUnique(@Valid City city) throws Exception {
		return cityManager.isUniqueName(city.getName(),city.getId(),city.getProvinceId());
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

		City city = cityManager.getById(id);
		if(null != city){
			if(JTZSConstants.Pubilc.ENABLED.getIndex()==city.getStatus()){
				city.setStatus(JTZSConstants.Pubilc.DISABLE.getIndex());
			}else{
				city.setStatus(JTZSConstants.Pubilc.ENABLED.getIndex());
			}
			cityManager.update(city);
			jsonMap.put("valid", city.getStatus());
			status = SUCCESS;
		}
		jsonMap.put("status", status);
		return jsonUtil.object2Json(jsonMap);
	}
	
	@RequestMapping(value="/queryJsonCityList")
	@ResponseBody
	public String queryJsonCityList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Long provinceId = paramUtils.getLongParameter(request, "provinceId", 0L);
		jsonMap.put("cityList", cityManager.getCityByInitial(provinceId,true));
		return jsonUtil.object2Json(jsonMap);
	}
	
}

