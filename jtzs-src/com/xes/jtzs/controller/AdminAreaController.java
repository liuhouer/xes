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
@RequestMapping("/admin/jtzs/area")
public class AdminAreaController extends BaseRestSpringController<Area,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	@Autowired
	private CityManager cityManager;
	@Autowired
	private ProvinceManager provinceManager;
	private AreaManager areaManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/area";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setAreaManager(AreaManager manager) {
		this.areaManager = manager;
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
	public String index(ModelMap model,AreaQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Page page = this.areaManager.findPage(query);
		
		this.addShowLabelAttrbite("/admin/jtzs/area", request, response);
		model.addAttribute("provinceList",provinceManager.getShowListByInitial());
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/area/index";
	}
	

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Area area,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("area",area);
		model.addAttribute("provinceList",provinceManager.getShowListByInitial());
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());
		return "/admin/jtzs/area/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid Area area,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/area/new";
		}
		if(null != area.getName()){
			if(area.getName().length() > 50){
				Flash.current().error("内容不能超过50字");
			}else{
				area.setStatus(JTZSConstants.Pubilc.ENABLED.getIndex());
				area.setEnName(CommonUtils.pinyinUtil.paraseStringToPinyin(area.getName()));
				areaManager.save(area);
				area.setSort(area.getId());
				areaManager.update(area);
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
		Area area = (Area)areaManager.getById(id);
		model.addAttribute("area",area);
		model.addAttribute("provinceList",provinceManager.getShowListByInitial());
		model.addAttribute("statusArray",JTZSConstants.Pubilc.values());
		return "/admin/jtzs/area/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Area area,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/area/edit";
		}
		if(null != area.getName()){
			if(area.getName().length() > 50){
				Flash.current().error("内容不能超过50字");
			}else{
				area.setEnName(CommonUtils.pinyinUtil.paraseStringToPinyin(area.getName()));
				areaManager.update(area);
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
	public boolean isUnique(@Valid Area area) throws Exception {
		return areaManager.isUniqueName(area.getName(),area.getId(),area.getCityId());
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

		Area area = areaManager.getById(id);
		if(null != area){
			if(JTZSConstants.Pubilc.ENABLED.getIndex()==area.getStatus()){
				area.setStatus(JTZSConstants.Pubilc.DISABLE.getIndex());
			}else{
				area.setStatus(JTZSConstants.Pubilc.ENABLED.getIndex());
			}
			areaManager.update(area);
			jsonMap.put("valid", area.getStatus());
			status = SUCCESS;
		}
		jsonMap.put("status", status);
		return jsonUtil.object2Json(jsonMap);
	}
	
	@RequestMapping(value="/queryJsonAreaList")
	@ResponseBody
	public String queryJsonAreaList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Long cityId = paramUtils.getLongParameter(request, "cityId", 0L);
		jsonMap.put("areaList", areaManager.getAreaByInitial(cityId,true));
		return jsonUtil.object2Json(jsonMap);
	}
}

