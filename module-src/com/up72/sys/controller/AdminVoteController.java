/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */


package com.up72.sys.controller;

import static com.up72.common.CommonUtils.dateUtils;
import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.util.SystemUtils.systemLogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletOutputStream;
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

import com.up72.auth.controller.AuthController;
import com.up72.auth.member.service.AuthUserManager;
import com.up72.base.UserDetails;
import com.up72.common.CommonConstants;
import com.up72.framework.web.scope.Flash;
import com.up72.sys.dao.LogBusinessDao;
import com.up72.sys.model.LogBusiness;
import com.up72.sys.service.LogBusinessManager;
import com.up72.sys.vo.query.LogBusinessQuery;
/**
 * 业务日志表数据提取跳转
 * 
 * @author sys
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/admin/sys/vote")
public class AdminVoteController extends AuthController<LogBusiness,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	@Autowired
	private AuthUserManager authUserManager;
	@Autowired
	private LogBusinessDao logBusinessDao;
	private LogBusinessManager logBusinessManager;
	
	private final String LIST_ACTION = "redirect:/admin/sys/logBusiness";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setLogBusinessManager(LogBusinessManager manager) {
		this.logBusinessManager = manager;
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
	public String index(ModelMap model,LogBusinessQuery query,HttpServletRequest request,HttpServletResponse response) {
		/*String timeBeginStr = paramUtils.getParameter(request, "timeBeginStr", "");
		String timeEndStr = paramUtils.getParameter(request, "timeEndStr", "");
		if(!timeBeginStr.trim().equals("")){
			timeBeginStr += " 00:00:00";
			query.setTimeBegin(dateUtils.stringToLong(timeBeginStr));
		}
		if(!timeEndStr.trim().equals("")){
			timeEndStr += " 23:59:59";
			query.setTimeEnd(dateUtils.stringToLong(timeEndStr));
		}
		UserDetails loginUser = this.getAdminLoginUser(request); //userUtils.getSessionUser(request, 0);
		if(null == loginUser){
			String userName = userUtils.getLoginName(request, AuthConstants.MEMBER_TYPE_SYSTEM);
			if(null == userName || userName.trim().equals("")){
				userName = userUtils.getLoginName(request, AuthConstants.MEMBER_TYPE_ADMIN);
			}
			if(null != userName || !userName.trim().equals("")){
				loginUser = authUserManager.getMember(userName);
			}
		}
		if(null != loginUser){
			HashMap<String, Object> params = new HashMap<String, Object>();
			//params.put("organizationGuid", loginUser.getOrganization().getOrganizationGuid());
			params.put("code", AuthConstants.MEMBER_TYPE_ADMIN);
			List<AuthUser> authUserList = authUserManager.findList(params, 0, null);
			model.addAttribute("authUserList", authUserList);
		}
		query.setSortColumns("time desc");
		Page page = this.logBusinessManager.findPage(query);
		model.addAllAttributes(toModelMap(page, query));*/
		return "/admin/sys/vote/home";
	}
	
	/** 显示 */
	@RequestMapping(value="/voteItem")
	public String voteItem(ModelMap model) throws Exception {
		
		return "/admin/sys/vote/voteItem";
	}
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		LogBusiness logBusiness = (LogBusiness)logBusinessManager.getById(id);
		model.addAttribute("logBusiness",logBusiness);
		return "/admin/sys//show";
	}
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		LogBusiness logBusiness = (LogBusiness)logBusinessManager.getById(id);
		model.addAttribute("logBusiness",logBusiness);
		return "/admin/sys/logBusiness/tab_edit";
	}
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		LogBusiness logBusiness = (LogBusiness)logBusinessManager.getById(id);
		model.addAttribute("dataChangLog",logBusiness);
		return "/admin/sys/logBusiness/tab_show";
	}
	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,LogBusiness logBusiness,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("logBusiness",logBusiness);
		return "/admin/sys/logBusiness/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid LogBusiness logBusiness,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/sys/logBusiness/new";
		}
		
		logBusinessManager.save(logBusiness);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		LogBusiness logBusiness = (LogBusiness)logBusinessManager.getById(id);
		model.addAttribute("logBusiness",logBusiness);
		return "/admin/sys/logBusiness/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid LogBusiness logBusiness,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/sys/logBusiness/edit";
		}
		
		logBusinessManager.update(logBusiness);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		logBusinessManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	/**
	 * 清空日志
	 * @author wgf
	 * @throws IOException 
	 */
	
	@RequestMapping(value="/deleteAll")
	public String deleteAll(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		HashMap<String,Object> params = new HashMap<String, Object>();
		TreeMap<String, String> orderMap = new TreeMap<String, String>();
		orderMap.put("time", "desc");
		List<LogBusiness> logBusinessList = this.logBusinessManager.findList(params, 0, orderMap);
		UserDetails authUser=this.getAdminLoginUser(request); //userUtils.getSessionUser(request, 0);//登录用户
		if(null == authUser){
			return "redirect:/admin/login";
		}
		String userGuid=authUser.getId().toString();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			this.logBusinessDao.deleteAll(logBusinessList);
			systemLogUtil.delete(userGuid, request, "清空日志", "清空日志");
			map.put("message", "success");
		}catch(Exception e){
			map.put("message", "error");
			systemLogUtil.deleteError(userGuid, request, "清空日志失败", "清空日志失败");
		}
		String fileName = "业务日志-" + dateUtils.DateToString(new Date());
		String path = CommonConstants.ROOTPATH + "export/"+fileName+".txt";
		 try {
				logBusinessManager.write(logBusinessList,path);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Flash.current().success("清空日志成功！");
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items,HttpServletRequest request,HttpServletResponse response) {
		UserDetails authUser=this.getAdminLoginUser(request); //userUtils.getSessionUser(request, 0);//登录用户
		if(null == authUser){
			return "redirect:/admin/login";
		}
		String userGuid=authUser.getId().toString();
		for(int i = 0; i < items.length; i++) {
			LogBusiness LogBusiness = this.logBusinessManager.getById(items[i]);
			logBusinessManager.removeById(items[i]);
			systemLogUtil.delete(userGuid, request, "删除日志", LogBusiness.getDescription() + " ID:" + items[i]);
		}
		
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	/**
	 * 导出到excel
	 * @author bxmen
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/export")
	public String export(HttpServletRequest request, HttpServletResponse response){
		final int fileCount = 10000;
		String userGuid = paramUtils.getParameter(request, "userGuid", "");
		String type = paramUtils.getParameter(request, "type", "");
		String file = "";
		HashMap<String,Object> params = new HashMap<String, Object>();
		if(!userGuid.trim().equals("")){
			params.put("userGuid", userGuid);
		}
		if(!type.trim().equals("")){
			params.put("type", type);
		}
		
		TreeMap<String,String> orders = null;//new TreeMap<String, String>();
		String[] items = paramUtils.getParameters(request, "items", "");
		List<LogBusiness> logBusinessList = new ArrayList<LogBusiness>();
		if (null != items && items.length > 0) {
			for (int i = 0; i < items.length; i++) {
				LogBusiness l = this.logBusinessManager.getById(Long.parseLong(items[i]));
				logBusinessList.add(l);
			}
			 file = logBusinessManager.exportSysLogBusiness(logBusinessList);
		}else{
			 file = logBusinessManager.exportAllSysLogBusiness(params, orders, 50, fileCount);
		}
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
		return null;
	}
	/**
	 * 导出为TXT
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/exportTxt")
	public String exportTxt(HttpServletRequest request, HttpServletResponse response){
		String[] items = paramUtils.getParameters(request, "items", "");
		List<LogBusiness> logBusinessList = new ArrayList<LogBusiness>();
		if (null != items && items.length > 0) {
			for (int i = 0; i < items.length; i++) {
				LogBusiness l = this.logBusinessManager.getById(Long.parseLong(items[i]));
				logBusinessList.add(l);
			}
		}else{
			String userGuid = paramUtils.getParameter(request, "userGuid", "");
			String type = paramUtils.getParameter(request, "type", "");
			HashMap<String,Object> params = new HashMap<String, Object>();
			if(!userGuid.trim().equals("")){
				params.put("userGuid", userGuid);
			}
			if(!type.trim().equals("")){
				params.put("type", type);
			}
			TreeMap<String, String> orderMap = new TreeMap<String, String>();
			orderMap.put("time", "desc");
			logBusinessList = this.logBusinessManager.findList(params, 0, orderMap);
		}
		String path = CommonConstants.ROOTPATH + "export/日志.txt";
		 try {
				logBusinessManager.write(logBusinessList,path);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!path.trim().equals("")){
				response.setContentType("application/x-msdownload");
				String fileName = "业务日志导出-" + dateUtils.DateToString(new Date());
				
				try {
					fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
				} catch (UnsupportedEncodingException e) {}
				response.setHeader("Content-Disposition", "attachment; filename="+fileName+path.substring(path.lastIndexOf(".")));
				
				try{
					FileInputStream fis = new FileInputStream(path);
					
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
					File deleteFile = new File(path);
					deleteFile.delete();
				}
			}
		return null;
		
	}
}

