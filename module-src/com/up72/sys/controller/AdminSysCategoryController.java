/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */


package com.up72.sys.controller;

import static com.up72.common.CommonUtils.paramUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

import com.up72.base.BaseRestSpringController;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;
import com.up72.sys.dao.SysCategoryDao;
import com.up72.sys.model.SysCategory;
import com.up72.sys.service.SysCategoryManager;
import com.up72.sys.vo.query.SysCategoryQuery;
/**
 * 数据提取跳转
 * 
 * @author sys
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/sys/sysCategory")
public class AdminSysCategoryController extends BaseRestSpringController<SysCategory,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private SysCategoryManager sysCategoryManager;
	@Autowired
	private SysCategoryDao sysCategoryDao;
	
	private final String LIST_ACTION = "redirect:/admin/sys/sysCategory";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setSysCategoryManager(SysCategoryManager manager) {
		this.sysCategoryManager = manager;
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
	
	@RequestMapping(value="/indexForm")
	@SuppressWarnings({ "unchecked" })
	public String indexForm(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Long id=paramUtils.getLongParameter(request, "id", 0L);
		SysCategory sysCategory = new SysCategory();
		if (id != 0) {
			 sysCategory = sysCategoryManager.getById(id);
			 model.addAttribute("sysCategory", sysCategory);
		}
		List<SysCategory> categoryList = this.sysCategoryDao.findCategoriesByTree("");
		model.addAttribute("categoryList", categoryList);
		return "/admin/sys/sysCategory/indexForm";
	}
	
	/** 列表 */
	@RequestMapping(value="/list")
	@SuppressWarnings({ "unchecked" })
	public String list(ModelMap model,SysCategoryQuery query,HttpServletRequest request,HttpServletResponse response) {
		Page page = this.sysCategoryManager.findPage(query);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/sys/sysCategory/index";
	}
	
	@RequestMapping(value="/indexTree")
	public String indexTree(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		List<SysCategory> categoryList = this.sysCategoryDao.findCategoriesByTree("");
		model.addAttribute("categoryList", categoryList);
		return "/admin/sys/sysCategory/indexTree";
	}
	@RequestMapping("/catTree")
	public String catTree(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String guid = paramUtils.getParameter(request, "root","");
		List<SysCategory> categoryList = this.sysCategoryManager.getCategoriesByParent(guid);
		model.addAttribute("categoryList", categoryList);
		return "/admin/sys/sysCategory/catTreeJson";
	}
	/** 显示 */
	/*@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		SysCategory sysCategory = (SysCategory)sysCategoryManager.getById(id);
		model.addAttribute("sysCategory",sysCategory);
		return "/admin/sys/sysCategory/show";
	}*/

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,SysCategory sysCategory,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("sysCategory",sysCategory);
		List<SysCategory> categoryList = this.sysCategoryDao.findCategoriesByTree("");
		model.addAttribute("categoryList", categoryList);
		return "/admin/sys/sysCategory/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid SysCategory sysCategory,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/sys/sysCategory/new";
		}
		String parentGuid = sysCategory.getParentGuid();
		SysCategory parentSysCategory=null;
		if(!"".equals(parentGuid)){
			parentGuid = parentGuid.replaceAll(",", "");
			parentSysCategory=this.sysCategoryDao.getByGuid(parentGuid);
		}
		//设置级别
		if(null != parentSysCategory){
			sysCategory.setLevel(parentSysCategory.getLevel() + 1);
		}else{
			sysCategory.setLevel(1);
		}
		sysCategory.setParentGuid(parentGuid);
		sysCategory.setGuid(UUID.randomUUID().toString());
		sysCategory.setAddTime(System.currentTimeMillis());
		sysCategoryManager.save(sysCategory);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return "redirect:/admin/sys/sysCategory/indexTree";
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		SysCategory sysCategory = (SysCategory)sysCategoryManager.getById(id);
		model.addAttribute("sysCategory",sysCategory);
		List<SysCategory> categoryList = this.sysCategoryDao.findCategoriesByTree("");
		model.addAttribute("categoryList", categoryList);
		return "/admin/sys/sysCategory/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		SysCategory sysCategory = (SysCategory)sysCategoryManager.getById(id);
		model.addAttribute("sysCategory",sysCategory);
		return "/admin/sys/sysCategory/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		SysCategory sysCategory = (SysCategory)sysCategoryManager.getById(id);
		model.addAttribute("sysCategory",sysCategory);
		return "/admin/sys/sysCategory/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}/update")
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid SysCategory sysCategory,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		/*if(errors.hasErrors()) {
			return "/admin/sys/sysCategory/edit";
		}
		*/
		String parentGuid = sysCategory.getParentGuid();
		SysCategory parentSysCategory=null;
		if(!"".equals(parentGuid)){
			parentGuid = parentGuid.replaceAll(",", "");
			parentSysCategory=this.sysCategoryDao.getByGuid(parentGuid);
		}else{
			sysCategory.setLevel(1);
		}
		//设置级别
		if(null != parentSysCategory){
			sysCategory.setLevel(parentSysCategory.getLevel() + 1);
		}
		sysCategory.setParentGuid(parentGuid);
		sysCategoryManager.update(sysCategory);
		Flash.current().success(UPDATE_SUCCESS);
		return "redirect:/admin/sys/sysCategory/indexForm";
	}
	@RequestMapping("/doDelete")
	public String doDelete(ModelMap model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String[] guids = paramUtils.getParameters(request, "category_ids", "");
		sysCategoryManager.deleteCategories(guids);
		Flash.current().success(DELETE_SUCCESS);
		return "redirect:/admin/sys/sysCategory/indexTree";
	}
	
	@RequestMapping("/pageDelete")
	public String pageDelete(ModelMap model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String[] guids = paramUtils.getParameters(request, "category_ids", "");
		sysCategoryManager.deleteCategories(guids);
		Flash.current().success(DELETE_SUCCESS);
		return "redirect:/admin/sys/sysCategory/indexTree";
	}
	
	/**
	 * 搜索列表
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/search")
	@SuppressWarnings( { "unchecked" })
	public String search(ModelMap model,SysCategoryQuery query, HttpServletRequest request)
			throws IOException {
		List<SysCategory> categoryList = this.sysCategoryDao.findCategoriesByTree("");
		model.addAttribute("categoryList", categoryList);

		String parentGuid = paramUtils.getParameter(request, "parentGuid","");
		String catName = paramUtils.getParameter(request, "catName", "");
		catName = catName.trim();
		if (!"".equals(catName)) {
			query.setCatName(catName);
			model.addAttribute("catName",catName);
		}
		if (!"".equals(parentGuid)) {
			query.setParentGuid(parentGuid);
			model.addAttribute("parentGuid",parentGuid);
		}
		Page page = this.sysCategoryManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/sys/sysCategory/page";
	}
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		sysCategoryManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			sysCategoryManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
}

