/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */


package com.up72.sys.controller;

import static com.up72.auth.AuthUtils.userUtils;
import static com.up72.common.CommonUtils.dateUtils;
import static com.up72.common.CommonUtils.paramUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.up72.app.auth.service.UserManager;
import com.up72.auth.AuthConstants;
import com.up72.auth.controller.AuthController;
import com.up72.auth.member.service.AuthUserManager;
import com.up72.base.UserDetails;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;
import com.up72.sys.model.SysLogBusiness;
import com.up72.sys.service.SysLogBusinessManager;
import com.up72.util.SystemLogUtil;
import com.up72.sys.vo.query.SysLogBusinessQuery;
/**
 * 业务日志表数据提取跳转
 * 
 * @author iscs
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/sysLogBusiness")
public class AdminSysLogBusinessController extends AuthController<SysLogBusiness,java.lang.Long>{
	private SystemLogUtil systemLogUtil = new SystemLogUtil();
	
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private SysLogBusinessManager sysLogBusinessManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private AuthUserManager authUserManager;
	
	private final String LIST_ACTION = "redirect:/admin/sysLogBusiness";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setSysLogBusinessManager(SysLogBusinessManager manager) {
		this.sysLogBusinessManager = manager;
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
	public String index(ModelMap model,SysLogBusinessQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		if (query.getSortColumns() == null || "".equals(query.getSortColumns())) {
			query.setSortColumns("time desc");
		}
		
		Integer isShowAdvanced = paramUtils.getIntParameter(request, "isShowAdvanced", 0);
		
		String timeBeginStr = paramUtils.getParameter(request, "timeBeginStr", "");
		String timeEndStr = paramUtils.getParameter(request, "timeEndStr", "");
		if(!timeBeginStr.trim().equals("")){
			timeBeginStr += " 00:00:00";
			query.setTimeBegin(dateUtils.stringToLong(timeBeginStr));
		}
		if(!timeEndStr.trim().equals("")){
			timeEndStr += " 23:59:59";
			query.setTimeEnd(dateUtils.stringToLong(timeEndStr));
		}
		Page page = this.sysLogBusinessManager.findPage(query);
		
		UserDetails loginUser = this.getAdminLoginUser(request); //userUtils.getSessionUser(request, 0);
		if(null == loginUser){
			String userName = userUtils.getLoginName(request, AuthConstants.MEMBER_TYPE_SYSTEM);
			if(null == userName || userName.trim().equals("")){
				userName = userUtils.getLoginName(request, AuthConstants.MEMBER_TYPE_ADMIN);
			}
			if(null != userName || !userName.trim().equals("")){
				loginUser = authUserManager.getMember(userName.trim().toLowerCase());
			}
		}
		this.addShowLabelAttrbite("/admin/sysLogBusiness", request, response);
		model.addAllAttributes(toModelMap(page, query));
		model.addAttribute("isShowAdvanced", isShowAdvanced);
		return "/admin/sysLogBusiness/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,HttpServletRequest request,@PathVariable java.lang.Long id) throws Exception {
		SysLogBusiness sysLogBusiness = (SysLogBusiness)sysLogBusinessManager.getById(id);
		model.addAttribute("sysLogBusiness",sysLogBusiness);
		return "/admin/sysLogBusiness/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,SysLogBusiness sysLogBusiness,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("sysLogBusiness",sysLogBusiness);
		return "/admin/sysLogBusiness/new";
	}
	
	/** 保存新增,标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model, SysLogBusiness sysLogBusiness,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/sysLogBusiness/new";
		}
		
		sysLogBusinessManager.save(sysLogBusiness);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,HttpServletRequest request,@PathVariable java.lang.Long id) throws Exception {
		SysLogBusiness sysLogBusiness = (SysLogBusiness)sysLogBusinessManager.getById(id);
		model.addAttribute("sysLogBusiness",sysLogBusiness);
		return "/admin/sysLogBusiness/edit";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,HttpServletRequest request,@PathVariable java.lang.Long id) throws Exception {
		SysLogBusiness sysLogBusiness = (SysLogBusiness)sysLogBusinessManager.getById(id);
		model.addAttribute("sysLogBusiness",sysLogBusiness);
		return "/admin/sysLogBusiness/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,HttpServletRequest request,@PathVariable java.lang.Long id) throws Exception {
		SysLogBusiness sysLogBusiness = (SysLogBusiness)sysLogBusinessManager.getById(id);
		model.addAttribute("sysLogBusiness",sysLogBusiness);
		return "/admin/sysLogBusiness/tab_show";
	}
	
	/** 保存更新,标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}/doEdit")
	public String update(ModelMap model,HttpServletRequest request,@PathVariable java.lang.Long id, SysLogBusiness sysLogBusiness,BindingResult errors,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/sysLogBusiness/edit";
		}
		
		sysLogBusinessManager.update(sysLogBusiness);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model, HttpServletRequest request, @PathVariable java.lang.Long id) {
		UserDetails userDetails = this.getAdminLoginUser(request);
		if(null == userDetails){
			return "redirect:/admin/login";
		}
		SysLogBusiness log = sysLogBusinessManager.getById(id);
		try {
			sysLogBusinessManager.removeById(id);
			systemLogUtil.delete(userDetails.getUserName(), request, "日志管理", "名称：" + log.getFunction() + log.getType() + " ID：" + id);
		} catch (Exception e) {
			systemLogUtil.deleteError(userDetails.getUserName(), request, "日志管理", "名称：" + log.getFunction() + log.getType() + " ID：" + id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(value="/iscs/delete")
	public String batchDelete(ModelMap model, HttpServletRequest request, @RequestParam("items") java.lang.Long[] items) {
		UserDetails userDetails = this.getAdminLoginUser(request);
		if(null == userDetails){
			return "redirect:/admin/login";
		}
		for(int i = 0; i < items.length; i++) {
			sysLogBusinessManager.removeById(items[i]);
		}
		systemLogUtil.delete(userDetails.getUserName(), request, "日志管理", "批量删除日志");
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 清空日志 */
	@RequestMapping(value="/clearLog")
	public String clearLog(ModelMap model, HttpServletRequest request) {
		UserDetails userDetails = this.getAdminLoginUser(request);
		if(null == userDetails){
			return "redirect:/admin/login";
		}
		int clearNum = sysLogBusinessManager.clearLog();
		systemLogUtil.delete(userDetails.getUserName(), request, "日志管理", "清空日志，共清理" + clearNum + "条日志");
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	
	@RequestMapping(value="/export")
	public String export(HttpServletRequest request, HttpServletResponse response){
		String result = LIST_ACTION;
		final int fileCount = 10000;
		String userGuid = paramUtils.getParameter(request, "userGuid", "");
		String type = paramUtils.getParameter(request, "type", "");
		String timeBeginStr = paramUtils.getParameter(request, "timeBeginStr", "");
		String timeEndStr = paramUtils.getParameter(request, "timeEndStr", "");
		Long startTime = 0L;
		Long endTime = 0L;
		if(!timeBeginStr.trim().equals("")){
			timeBeginStr += " 00:00:00";
			startTime = dateUtils.stringToLong(timeBeginStr);
		}
		if(!timeEndStr.trim().equals("")){
			timeEndStr += " 23:59:59";
			endTime = dateUtils.stringToLong(timeEndStr);
		}

		HashMap<String,Object> params = new HashMap<String, Object>();
		if(!userGuid.trim().equals("")){
			params.put("userGuid", userGuid);
		}
		if(!type.trim().equals("")){
			params.put("type", type);
		}
		if(startTime > 0){
			params.put(" time > " + startTime, null);
		}
		if(endTime > 0){
			params.put(" time < " + endTime, null);
		}
		
		TreeMap<String,String> orders = null;//new TreeMap<String, String>();

		String file = sysLogBusinessManager.exportSysLogBusiness(params, orders, 50, fileCount);
		if(!file.trim().equals("")){
			response.setContentType("application/x-msdownload");
			String fileName = "业务日志导出-" + dateUtils.DateToString(new Date());
			
			try {
				fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {}
			response.setHeader("Content-Disposition", "attachment; filename="+fileName+file.substring(file.lastIndexOf(".")));
			
			try{
				FileInputStream fis = new FileInputStream(file);
				
				int flag = 0;
				byte[] buff = new byte[1024*4];
	
				ServletOutputStream sos = response.getOutputStream();
				while((flag = fis.read(buff,0, buff.length))!=-1){
					sos.write(buff, 0, flag);
				}
				
				fis.close();
				sos.close();
			}catch (Exception e) {
				try {
					response.getWriter().print("下载出现错误！");
				} catch (IOException e1) {
				}
			}finally{
				File deleteFile = new File(file);
				deleteFile.delete();
			}
		}
		return result;
	}
}
