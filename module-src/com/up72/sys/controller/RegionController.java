/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2011
 */


package com.up72.sys.controller;

import static com.up72.common.CommonUtils.paramUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.up72.sys.model.Region;
import com.up72.sys.service.RegionManager;
import com.up72.sys.vo.query.RegionQuery;

/**
 * 地区数据提取跳转
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/tools/region")
public class RegionController extends BaseRestSpringController<Region,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private RegionManager regionManager;
	
	private final String LIST_ACTION = "redirect:/tools/region";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setRegionManager(RegionManager manager) {
		this.regionManager = manager;
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
	
	/** 列表 */
	@RequestMapping
	@SuppressWarnings({ "unchecked" })
	public String index(ModelMap model,RegionQuery query,HttpServletRequest request,HttpServletResponse response) {
		Page page = this.regionManager.findPage(query);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/tools/region/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Region region = (Region)regionManager.getById(id);
		model.addAttribute("region",region);
		return "/tools/region/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Region region,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("region",region);
		return "/tools/region/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid Region region,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/tools/region/new";
		}
		
		regionManager.save(region);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Region region = (Region)regionManager.getById(id);
		model.addAttribute("region",region);
		return "/tools/region/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Region region,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/tools/region/edit";
		}
		
		regionManager.update(region);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		regionManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			regionManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**
	 * @author wqtan
	 * @return
	 */
	@RequestMapping(value="/json")
	public String json(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		// 父级
		long parent = paramUtils.getLongParameter(request, "region", 0L);
		// 子级
		long id = paramUtils.getLongParameter(request, "id", 0L);
		// 等级
		int level = paramUtils.getIntParameter(request, "idLevel", 0);
		
		// 如果有id，无父级 获得父级列表
		if(id > 0 && parent <= 0){
			model.addAttribute("list",this.regionManager.getParentList(id,level));
		}
		// 如果有id，有父级 获得兄弟列表
		else if(id > 0 && parent > 0){
			model.addAttribute("list",this.regionManager.getBrotherList(parent));
		}
		// 如果无id，获得子级列表
		else{
			model.addAttribute("list",this.regionManager.getChildrenList(parent));
		}
		return "/ucenter/gongan/member/json";
	}
	
	/**
	 * 检验指定的regoin是否是叶子节点
	 * 
	 * @author wqtan
	 */
	@RequestMapping(value="/isLeaf")
	@ResponseBody
	public String isLeaf(HttpServletRequest request,HttpServletResponse response){
		
		Long regionId = paramUtils.getLongParameter(request, "regionId", 0L);
		boolean result = this.regionManager.isLeft(regionId);
		
		return Boolean.toString(result);
	}
}

