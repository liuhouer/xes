/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */


package com.xes.jtzs.controller;

import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.common.CommonUtils.userUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

import com.up72.auth.member.service.AuthUserManager;
import com.up72.base.BaseRestSpringController;
import com.up72.base.UserDetails;
import com.up72.common.excel.ColumnConfig;
import com.up72.common.excel.ExcelExportPoiUtil;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;
import com.xes.jtzs.model.Score;
import com.xes.jtzs.service.ScoreLogManager;
import com.xes.jtzs.service.ScoreManager;
import com.xes.jtzs.vo.query.ScoreLogQuery;
import com.xes.jtzs.vo.query.ScoreQuery;
/**
 * 数据提取跳转
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/jtzs/score")
public class AdminScoreController extends BaseRestSpringController<Score,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	@Autowired
	private AuthUserManager authUserManager;
	@Autowired
	private ScoreLogManager scoreLogManager;
	private ScoreManager scoreManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/score";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setScoreManager(ScoreManager manager) {
		this.scoreManager = manager;
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
	public String index(ModelMap model,ScoreQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		if(null==query.getSortColumns()){
			query.setSortColumns("id desc");
		}
		Page page = this.scoreManager.findPage(query);
		
		this.addShowLabelAttrbite("/admin/jtzs/score", request, response);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/score/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Score score = (Score)scoreManager.getById(id);
		model.addAttribute("score",score);
		return "/admin/jtzs/score/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Score score,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("score",score);
		return "/admin/jtzs/score/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid Score score,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/score/new";
		}
		
		scoreManager.save(score);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Score score = (Score)scoreManager.getById(id);
		model.addAttribute("score",score);
		return "/admin/jtzs/score/edit";
	}
	
	/** 添加积分 */
	@RequestMapping(value="/{id}/addScore")
	public String addScore(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Score score = (Score)scoreManager.getById(id);
		model.addAttribute("score",score);
		return "/admin/jtzs/score/addScore";
	}
	
	/** 添加积分 */
	@RequestMapping(value="/{id}/doAddScore")
	public String doAddScore(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		int addScore = paramUtils.getIntParameter(request, "score", 0);
		int scoreType = paramUtils.getIntParameter(request, "scoreType", -1);
		String remark = paramUtils.getParameter(request, "remark", "");
		UserDetails loginUser = authUserManager.getMember(userUtils.getLoginName(request,null));
		if(null!=loginUser){
			Flash.current().success(scoreManager.addScore(id, addScore, scoreType, remark, loginUser));
		}else{
			Flash.current().error("管理员未登录");
		}
		return LIST_ACTION;
	}
	
	
	/** 查询积分日志 */
	@RequestMapping(value="/selectScoreLog")
	public String selectScoreLog(ModelMap model,ScoreLogQuery query,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(null==query.getSortColumns()){
			query.setSortColumns("addTime desc");
		}
		int userRole = paramUtils.getIntParameter(request, "userRole", 0);
		Long userId = paramUtils.getLongParameter(request, "userId", 0);
		int remainScore = paramUtils.getIntParameter(request, "remainScore", 0);
		query.setUserRole(userRole);
		if(userId!=0){
		query.setUserId(userId);
		}
//		query.setPageSize(20);
		Page page = this.scoreLogManager.findPage(query);
		this.addShowLabelAttrbite("/admin/jtzs/score/selectScoreLog", request, response);
		model.addAttribute("page",page);
		model.addAttribute("remainScore",remainScore);
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/score/selectScoreLog";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Score score = (Score)scoreManager.getById(id);
		model.addAttribute("score",score);
		return "/admin/jtzs/score/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Score score = (Score)scoreManager.getById(id);
		model.addAttribute("score",score);
		return "/admin/jtzs/score/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Score score,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/score/edit";
		}
		
		scoreManager.update(score);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		scoreManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			scoreManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	/** 导出数据 */
	@RequestMapping(value="/exportData")
	public String exportData(ModelMap model,ScoreQuery query,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Score> dataList = new LinkedList<Score>();
		Page page = this.scoreManager.findPage(query);
		String  loginName = paramUtils.getParameter(request, "loginName");
		int userRole = paramUtils.getIntParameter(request, "userRole", 0);
        long allscore = paramUtils.getLongParameter(request, "score", -1);
        long useScore = paramUtils.getLongParameter(request, "useScore",-1);

        query.setLoginName(loginName);
        if(allscore!=-1){
        	query.setScore(allscore);
        	
        }
        if(useScore!=-1){
        	query.setUseScore(useScore);
        }
		int count  = page.getLastPageNumber();
		if(count>=1){
			for(int i=1;i<=count;i++){
			  query.setPageNumber(i);
			  List<Score> list = page.getResult();
			  dataList.addAll(list);
			}
		}
		
		List<ColumnConfig> configList = new ArrayList<ColumnConfig>();
		configList.add(new ColumnConfig("userRoleStr", "角色"));
		configList.add(new ColumnConfig("telephone", "账户"));
		configList.add(new ColumnConfig("score", "总积分"));
		configList.add(new ColumnConfig("useScore", "消费积分"));
		configList.add(new ColumnConfig("remainScore", "可用积分"));

        long currenttime = System.currentTimeMillis();
        String filePath = "D:/temp/"+"ScoreList_"+currenttime+".xls";
        String fileName = "积分导出文件.xls";
		ExcelExportPoiUtil eep = new ExcelExportPoiUtil(filePath, 0,
				configList);

		eep.exportHeader(new HSSFColor.BLACK(),
				new HSSFColor.WHITE());

		eep.export(dataList);
		eep.save();
		request.setAttribute("fileName", fileName);
		request.setAttribute("filePath", filePath);
		request.getRequestDispatcher("/fileDownload/commonDownload").forward(request,response);
		
		this.addShowLabelAttrbite("/admin/jtzs/score", request, response);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/score/index";
	}
	


}

