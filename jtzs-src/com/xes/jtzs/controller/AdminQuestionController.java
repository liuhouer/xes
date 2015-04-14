/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */


package com.xes.jtzs.controller;

import static com.up72.common.CommonUtils.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.up72.auth.member.service.AuthUserManager;
import com.up72.base.BaseRestSpringController;
import com.up72.base.UserDetails;
import com.up72.common.CommonConstants;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;
import com.up72.iphone.util.IPhonePush;
import com.up72.util.DateUtils;
import com.up72.util.SessionUtil;
import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.model.*;
import com.xes.jtzs.service.*;
import com.xes.jtzs.vo.query.*;/**
 * 数据提取跳转
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/jtzs/question")
public class AdminQuestionController extends BaseRestSpringController<Question,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	@Autowired
	private SystemMessageManager systemMessageManager;
	@Autowired
	private ScoreManager scoreManager;
	@Autowired
	private AuthUserManager authUserManager;
	@Autowired
	private TeacherManager teacherManager;
	@Autowired
	private StudentManager studentManager;
	@Autowired
	private GradeManager gradeManager;
	@Autowired
	private WrongRulesManager wrongRulesManager;
	@Autowired
	private SubjectManager subjectManager;
	@Autowired
	private KnowledgeManager knowledgeManager;
	private QuestionManager questionManager;
	
	private final String LIST_ACTION = "redirect:/admin/jtzs/question";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setQuestionManager(QuestionManager manager) {
		this.questionManager = manager;
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
	public String index(ModelMap model,QuestionQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		if(null == query.getSortColumns()){
			query.setSortColumns("sourceType desc,stopTime desc");
		}
		Page page = this.questionManager.findPage(query);
		
		 this.addShowLabelAttrbite("/admin/jtzs/question", request, response);
		model.addAttribute("gradeList",gradeManager.getGradeListBySort());
		model.addAttribute("subjectList",subjectManager.getShowListBySort());
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/question/index";
	}
	
	/** 列表  
	 * @throws IOException */
	@RequestMapping(value="/reportIndex")
	public String reportIndex(ModelMap model,QuestionQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		int reloadTime = paramUtils.getIntParameter(request, "reloadTime", 1);
		int isReload = paramUtils.getIntParameter(request, "isReload", 0);
		
		String reportTimeBeginString = paramUtils.getParameter(request, "reportTimeBeginString", "");
		String reportTimeEndString = paramUtils.getParameter(request, "reportTimeEndString", "");
		if(objectUtils.isNotEmpty(reportTimeBeginString)){
			Long startTime = DateUtils.stringToLong(reportTimeBeginString + ":00");
			query.setReportTimeBegin(startTime);
		}
		if(objectUtils.isNotEmpty(reportTimeEndString)){
			Long endTime = DateUtils.stringToLong(reportTimeEndString + ":59");
			query.setReportTimeEnd(endTime);
		}
		
		if(null == query.getSortColumns()){
			query.setSortColumns("reportTime desc,auditState asc");
		}
		Page page = this.questionManager.findPage(query);
		model.addAttribute("auditStateList", JTZSConstants.AuditState.values());
		model.addAttribute("reloadTime", reloadTime);
		model.addAttribute("isReload", isReload);
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/question/reportIndex";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Question question = (Question)questionManager.getById(id);
		model.addAttribute("question",question);
		return "/admin/jtzs/question/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Question question,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("question",question);
		return "/admin/jtzs/question/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid Question question,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/admin/jtzs/question/new";
		}
		
		questionManager.save(question);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Question question = (Question)questionManager.getById(id);
		model.addAttribute("question",question);
		return "/admin/jtzs/question/edit";
	}
	
	/** 编辑举报 */
	@RequestMapping(value="/{id}/reportEdit")
	public String reportEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Question question = (Question)questionManager.getById(id);
		model.addAttribute("question",question);
		return "/admin/jtzs/question/reportEdit";
	}
	
	/** 审核举报结果 */
	@RequestMapping(value="/reportResultEdit")
	public String reportResultEdit(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long questionId = paramUtils.getLongParameter(request, "questionId", 0);
		Integer reportResult = paramUtils.getIntParameter(request, "reportResult",-1);
		UserDetails loginUser = authUserManager.getMember(userUtils.getLoginName(request,null));
		if(null!=loginUser){
			Question question = (Question)questionManager.getById(questionId);
			if(null!=question){
				if(reportResult==JTZSConstants.ReportResult.NOVIOLATION.getIndex()){
					question.setReportResult(JTZSConstants.ReportResult.NOVIOLATION.getIndex());
					question.setIsLock(JTZSConstants.QuestionIsLock.UNLOCK.getIndex());
					question.setSourceType(JTZSConstants.QuestionSourceType.EXPERT.getIndex());
					question.setAuditState(JTZSConstants.AuditState.TREATED.getIndex());
					questionManager.update(question);
					int wrongNum = questionManager.noviolationQuestionCountByTeacherId(question.getReportId());
					WrongRules wrongRules = wrongRulesManager.getTeacherWrongRulesByWrongNum(wrongNum);
					if(null!=wrongRules){
						if(wrongRules.getIsStopLogin()==JTZSConstants.Status.FREEZE.getIndex()){
							Teacher teacher = teacherManager.getById(question.getReportId());
							teacher.setStatus(JTZSConstants.Status.FREEZE.getIndex());
							teacherManager.update(teacher);
						}
						if(wrongRules.getDelScore()>0){
							Score socre = scoreManager.getScoreByRole(question.getReportId(),JTZSConstants.ROLE_TEACHER);
							Flash.current().success(scoreManager.addScore(socre.getId(),wrongRules.getDelScore(), JTZSConstants.ScoreType.DEL.getIndex(), "违规", loginUser));
						}
						SystemMessage sm = new SystemMessage();
						sm.setAddTime(new Date().getTime());
						sm.setUserId(question.getReportId());
						sm.setUserRole(JTZSConstants.ROLE_TEACHER);
						sm.setQuestionId(questionId);
						sm.setAddUserId(authUserManager.getAdminMember().getId());
						sm.setContent(wrongRules.getContent());
						sm.setIsRead(JTZSConstants.SystemMessageIsRead.UNREAD.getIndex());
						sm.setStatus(JTZSConstants.Status.FREEZE.getIndex());
						String token = SessionUtil.getToken(request, question.getReportId().toString());
						if(null != token){
							IPhonePush.push(token,sm.getContent(), 1,"push.p12");
							sm.setStatus(JTZSConstants.Status.NORMAL.getIndex());
						}
						systemMessageManager.save(sm);
					}
				}else if(reportResult==JTZSConstants.ReportResult.VIOLATION.getIndex()){
					question.setReportResult(JTZSConstants.ReportResult.VIOLATION.getIndex());
					question.setAuditState(JTZSConstants.AuditState.TREATED.getIndex());
					questionManager.update(question);
					int wrongNum = questionManager.violationQuestionCountByStudentId(question.getStudentId());
					WrongRules wrongRules = wrongRulesManager.getStudentWrongRulesByWrongNum(wrongNum);
					if(null != wrongRules){
						if(wrongRules.getIsDelQuestion()==JTZSConstants.IsDel.DELETE.getIndex()){
							question.setIsDel(JTZSConstants.IsDel.DELETE.getIndex());
							questionManager.update(question);
						}
						if(wrongRules.getDelScore()>0){
							Score socre = scoreManager.getScoreByRole(question.getStudentId(),JTZSConstants.ROLE_STUDENT);
							Flash.current().success(scoreManager.addScore(socre.getId(),wrongRules.getDelScore(), JTZSConstants.ScoreType.DEL.getIndex(), "违规", loginUser));
						}
						if(wrongRules.getIsStopLogin()==JTZSConstants.Status.FREEZE.getIndex()){
							Student student = studentManager.getById(question.getStudentId());
							student.setStatus(JTZSConstants.Status.FREEZE.getIndex());
							studentManager.update(student);
						}
						SystemMessage sm = new SystemMessage();
						sm.setAddTime(new Date().getTime());
						sm.setUserId(question.getStudentId());
						sm.setUserRole(JTZSConstants.ROLE_STUDENT);
						sm.setQuestionId(questionId);
						sm.setContent(wrongRules.getContent());
						sm.setAddUserId(authUserManager.getAdminMember().getId());
						sm.setIsRead(JTZSConstants.SystemMessageIsRead.UNREAD.getIndex());
						sm.setStatus(JTZSConstants.Status.FREEZE.getIndex());
						String token = SessionUtil.getToken(request, question.getStudentId().toString());
						if(null != token){
							IPhonePush.push(token,sm.getContent(), 1,"push.p12");
							sm.setStatus(JTZSConstants.Status.NORMAL.getIndex());
						}
						systemMessageManager.save(sm);
					}
				}
			}
		}else{
			Flash.current().error("管理员未登录");
		}
		return "redirect:/admin/jtzs/question/reportIndex";
	}
	
	/** 分配知识点 */
	@RequestMapping(value="/selectKnowledge")
	public String selectKnowledge(ModelMap model,KnowledgeQuery query,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long questionId = paramUtils.getLongParameter(request, "questionId", 0);
		Question question = (Question)questionManager.getById(questionId);
		model.addAttribute("question",question);
		Page page = this.knowledgeManager.findPage(query);
		model.addAttribute("gradeList",gradeManager.getGradeListBySort());
		model.addAttribute("subjectList",subjectManager.getSubjectBySort());
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/jtzs/question/selectKnowledge";
	}
	
	/** 保存知识点 */
	@RequestMapping(value="/addKnowledge")
	@ResponseBody
	public String addKnowledge(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long questionId = paramUtils.getLongParameter(request, "questionId", 0);
		Long items = paramUtils.getLongParameter(request, "items", 0L);
		Question question = (Question)questionManager.getById(questionId);
		if(null!=question && null!=items && items > 0){
			question.setKnowledgeId(items);
			questionManager.update(question);
		}
		return "";
	}
	
	/** 选项卡编辑 */
	@RequestMapping(value="/{id}/tabEdit")
	public String tabEdit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Question question = (Question)questionManager.getById(id);
		model.addAttribute("question",question);
		return "/admin/jtzs/question/tab_edit";
	}
	
	
	/** 选项卡显示 */
	@RequestMapping(value="/{id}/tabShow")
	public String tabShow(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Question question = (Question)questionManager.getById(id);
		model.addAttribute("question",question);
		return "/admin/jtzs/question/tab_show";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Question question,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/admin/jtzs/question/edit";
		}
		
		questionManager.update(question);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		questionManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			questionManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
}

