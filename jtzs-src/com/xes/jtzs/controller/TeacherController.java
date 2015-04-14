/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */


package com.xes.jtzs.controller;

import static com.up72.common.CommonUtils.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.up72.auth.controller.AuthController;
import com.up72.auth.member.service.AuthUserManager;

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

import com.up72.dao.hibernate.CommonDAOHibernate;
import com.up72.framework.page.Page;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.framework.web.scope.Flash;
import com.up72.iphone.util.IPhonePush;
import com.up72.page.Pagination;
import com.up72.page.QueryResult;
import com.up72.util.SessionUtil;
import com.up72.util.SmsUtils;


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
@RequestMapping("/jtzs/teacher")
public class TeacherController extends AuthController<Teacher,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	@Autowired	
	private CommentManager commentManager;
	@Autowired	
	private TeacherStatisticsManager teacherStatisticsManager;
	@Autowired	
	private CommonRuleManager commonRuleManager;
	@Autowired	
	private AnswerManager answerManager;
	@Autowired	
	private QuestionManager questionManager;
	private TeacherManager teacherManager;
	@Autowired
	private ScoreManager scoreManager;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private AuthUserManager authUserManager;
	@Autowired
	private SystemMessageManager systemMessageManager;
	private final String LIST_ACTION = "redirect:/jtzs/teacher";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setTeacherManager(TeacherManager manager) {
		this.teacherManager = manager;
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
	public String index(ModelMap model,TeacherQuery query,HttpServletRequest request,HttpServletResponse response) {
		Page page = this.teacherManager.findPage(query);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/jtzs/teacher/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Teacher teacher = (Teacher)teacherManager.getById(id);
		model.addAttribute("teacher",teacher);
		return "/jtzs/teacher/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Teacher teacher,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("teacher",teacher);
		return "/jtzs/teacher/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid Teacher teacher,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/jtzs/teacher/new";
		}
		
		teacherManager.save(teacher);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Teacher teacher = (Teacher)teacherManager.getById(id);
		model.addAttribute("teacher",teacher);
		return "/jtzs/teacher/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Teacher teacher,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/jtzs/teacher/edit";
		}
		
		teacherManager.update(teacher);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		teacherManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			teacherManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	///////////////////////////////////////////////
	/** 老师退出 */
	@RequestMapping(value="/teacherLogout")
	public String studentLogout(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		clearSessionLogin(request);
		code = JTZSConstants.ErrorCode.gg000;
		rlt = RLT_SUCCESS;
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/teacher/teacherLogout";
	}
	

	
	/** 老师信息 */
	@RequestMapping(value="/getTeacherInfo")
	public String getTeacherInfo(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long teacherId = paramUtils.getLongParameter(request, "teacherId", 0L);
		Teacher teacher = teacherManager.getById(teacherId);
		if(null != teacher){
			long eventSize  = eventManager.getTeacherEventCount(teacher);
			model.put("eventSize", eventSize);//取得老师的活动条数
			model.put("teacher", teacher);
			code = JTZSConstants.ErrorCode.gg000;
			rlt = RLT_SUCCESS;
		}else{
			code = JTZSConstants.ErrorCode.gg008;
		}
		
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/teacher/getTeacherInfo";
	}
	
	/** 修改老师信息 老师信息不能前端修改 */
	@Deprecated
	@RequestMapping(value="/modifyTeacherInfo")
	public String modifyTeacherInfo(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long teacherId = paramUtils.getLongParameter(request, "id", 0L);
		Teacher teacher = teacherManager.getById(teacherId);
		if(null != teacher){
			String imgPath = paramUtils.getParameter(request, "imgPath", "");
			String nickName = paramUtils.getParameter(request, "nickName", "");
			Long provinceId = paramUtils.getLongParameter(request, "provinceId", 0L);
			Long expertSubjectId = paramUtils.getLongParameter(request, "expertSubjectId", 0L);
			long[] expertGradeIds = paramUtils.getLongParameters(request, "expertGradeIds", 0L);
			int sex = paramUtils.getIntParameter(request, "sex", 1);
			StringBuilder expertGradeIdStr = new StringBuilder();
			for (int i = 0; i < expertGradeIds.length; i++) {
				if(i!=0){
					expertGradeIdStr.append(",");
				}
				expertGradeIdStr.append("[").append(expertGradeIds[i]).append("]");
				
			}
			
			teacher.setImgPath(imgPath);
			teacher.setNickName(nickName);
			teacher.setProvinceId(provinceId);
			teacher.setExpertSubjectId(expertSubjectId);
			teacher.setExpertGradeIds(expertGradeIdStr.toString());
			teacher.setSex(sex);
			teacherManager.update(teacher);
			code = JTZSConstants.ErrorCode.gg000;
			rlt = RLT_SUCCESS;
		}else{
			code = JTZSConstants.ErrorCode.gg008;
		}
		
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/teacher/modifyTeacherInfo";
	}
	
	/** 老师修改密码 */
	@RequestMapping(value="/modifyTeacherPassword")
	public String modifyStudentPassword(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long teacherId = paramUtils.getLongParameter(request, "teacherId", 0L);
		String oldPassword = paramUtils.getParameter(request, "oldPassword", "");
		String newPassword = paramUtils.getParameter(request, "newPassword", "");
		try {
			code = teacherManager.resetPwd(oldPassword, newPassword, teacherId);
			if(JTZSConstants.ErrorCode.gg000.equals(code)){
				rlt = RLT_SUCCESS;
			}
		} catch (Exception e) {
		}
		
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/teacher/modifyTeacherPassword";
	}
	
	/** 普通教师列表（html） */
	@RequestMapping(value="/teacherQuestionList")
	public String teacherQuestionList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long expertSubjectId = paramUtils.getLongParameter(request, "expertSubjectId", 0L);
		long[] expertGradeIds = paramUtils.getLongParameters(request, "expertGradeIds", 0L);
		Integer questionStatus = paramUtils.getIntParameter(request, "questionStatus", JTZSConstants.QuestionStatus.NOANSWER.getIndex());
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start =  paramUtils.getIntParameter(request, "start", 0);
		int split = JTZSConstants.publicSplit;
		
		QuestionQuery query = new QuestionQuery();
		if(0 != expertSubjectId){
			query.setSubjectId(expertSubjectId);
		}
		StringBuilder sb = new StringBuilder();
		if(null != expertGradeIds && expertGradeIds[0]!=0){
			for (int i = 0; i < expertGradeIds.length; i++) {
				sb.append("&egIds=").append(expertGradeIds[0]);
			}
			query.setGradeIdList(expertGradeIds);
		}
		if(questionStatus != -1){
			query.setStatus(questionStatus);
		}else{
			query.setNotStatus(JTZSConstants.QuestionStatus.NOWANSWER.getIndex());
		}
		query.setPageSize(range);
		query.setPageNumber(start);
		query.setSortColumns("addTime desc");
		model.put("page", questionManager.teacherQuestionList(query));
		
		Map<String,Object> jsondata = new HashMap<String,Object>();
		jsondata.put("ajaxUrl","/jtzs/teacher/jsonTeacherQuestionList?esId="+expertSubjectId+"&qs="+questionStatus+"&egIds="+sb.toString());
		jsondata.put("range",range);
		jsondata.put("split",--split);
		jsondata.put("start",start+range);
		model.put("jsondata",jsonUtil.object2Json(jsondata));
		model.put("type","tea");
		return "/jtzs/teacherList";
	}
	
	/** 普通教师列表 */
	@RequestMapping(value="/jsonTeacherQuestionList")
	public String jsonTeacherQuestionList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long expertSubjectId = paramUtils.getLongParameter(request, "esId", 0L);
		long[] expertGradeIds = paramUtils.getLongParameters(request, "egIds", 0L);
		Integer questionStatus = paramUtils.getIntParameter(request, "qs",JTZSConstants.QuestionStatus.NOANSWER.getIndex());
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start = paramUtils.getIntParameter(request, "start", 0);
		int split = paramUtils.getIntParameter(request,"split",0);
	
		if(split==0){
			model.put("is_end",true);
		}else{
			StringBuilder hql = new StringBuilder("from Question where isLock=? and isQuit=? and isDel=? ");
			Object[] params = new Object[3];
			params[0] = JTZSConstants.QuestionIsLock.UNLOCK.getIndex();
			params[1] = JTZSConstants.QuestionIsQuit.NORMAL.getIndex();
			params[2] = JTZSConstants.IsDel.UNDELETE.getIndex();
			if(expertSubjectId > 0){
				hql.append(" and subjectId=").append(expertSubjectId);
			}
			if(questionStatus != -1){
				hql.append(" and status=").append(questionStatus);
			}
			if(null != expertGradeIds && expertGradeIds[0]!=0){
				hql.append(" and (");
				for (int i = 0; i < expertGradeIds.length; i++) {
					if(i!=0){
						hql.append(" or ");
					}
					hql.append(" gradeId=").append(expertGradeIds[i]);
				}
				hql.append(" )");
			}
			hql.append(" order by addTime desc");
			
			CommonDAOHibernate $d = (CommonDAOHibernate)ApplicationContextHolder.getBean("commonDAOHibernate");
			QueryResult result = $d.findPage(hql.toString(), params, new Pagination(start,range));
			model.put("result",result.getItems());
			model.put("is_end",false);
		}
		model.put("split",--split);
		model.put("start",start+range);
		model.put("range",range);
		model.put("type","tea");
		return "/jtzs/jsonPage/jsonTeacherQuestionList";
	}
	
	/** 专家问题列表（html） */
	@RequestMapping(value="/expertQuestionList")
	public String expertQuestionList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long expertSubjectId = paramUtils.getLongParameter(request, "expertSubjectId", 0L);
		long[] expertGradeIds = paramUtils.getLongParameters(request, "expertGradeIds", 0L);
		Integer questionStatus = paramUtils.getIntParameter(request, "questionStatus", JTZSConstants.QuestionStatus.NOANSWER.getIndex());
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start =  paramUtils.getIntParameter(request, "start", 0);
		int split = JTZSConstants.publicSplit;
		QuestionQuery query = new QuestionQuery();
		if(0 != expertSubjectId){
			query.setSubjectId(expertSubjectId);
		}
		StringBuilder sb = new StringBuilder();
		if(null != expertGradeIds && expertGradeIds[0]!=0){
			for (int i = 0; i < expertGradeIds.length; i++) {
				sb.append("&egIds=").append(expertGradeIds[0]);
			}
			query.setGradeIdList(expertGradeIds);
		}
		if(questionStatus != -1){
			query.setStatus(questionStatus);
		}else{
			query.setNotStatus(JTZSConstants.QuestionStatus.NOWANSWER.getIndex());
		}
		query.setPageSize(range);
		query.setPageNumber(start);
		query.setSortColumns("addTime desc");
		model.put("page", questionManager.expertQuestionList(query));
		
		Map<String,Object> jsondata = new HashMap<String,Object>();
		jsondata.put("ajaxUrl","/jtzs/teacher/jsonExpertQuestionList?esId="+expertSubjectId+"&qs="+questionStatus+"&egIds="+sb.toString());
		jsondata.put("range",range);
		jsondata.put("split",--split);
		jsondata.put("offset",start+range);
		model.put("jsondata",jsonUtil.object2Json(jsondata));
		model.put("type","exp");
		return "/jtzs/expertList";
	}
	
	/** 专家问题列表 */
	@RequestMapping(value="/jsonExpertQuestionList")
	public String jsonExpertQuestionList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long expertSubjectId = paramUtils.getLongParameter(request, "esId", 0L);
		long[] expertGradeIds = paramUtils.getLongParameters(request, "egIds", 0L);
		Integer questionStatus = paramUtils.getIntParameter(request, "qs", JTZSConstants.QuestionStatus.NOANSWER.getIndex());
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start = paramUtils.getIntParameter(request, "start", 0);
		int split = paramUtils.getIntParameter(request,"split",0);
		if(split==0){
			model.put("is_end",true);
		}else{
			StringBuilder hql = new StringBuilder("from Question where isLock=? and isQuit=? and isDel=? ");
			Object[] params = new Object[3];
			params[0] = JTZSConstants.QuestionIsLock.UNLOCK.getIndex();
			params[1] = JTZSConstants.QuestionIsQuit.NORMAL.getIndex();
			params[2] = JTZSConstants.IsDel.UNDELETE.getIndex();
			if(expertSubjectId > 0){
				hql.append(" and subjectId=").append(expertSubjectId);
			}
			if(questionStatus != -1){
				hql.append(" and status=").append(questionStatus);
			}
			if(null != expertGradeIds && expertGradeIds[0]!=0){
				hql.append(" and (");
				for (int i = 0; i < expertGradeIds.length; i++) {
					if(i!=0){
						hql.append(" or ");
					}
					hql.append(" gradeId=").append(expertGradeIds[i]);
				}
				hql.append(" )");
			}
			hql.append(" order by addTime desc");
			CommonDAOHibernate $d = (CommonDAOHibernate)ApplicationContextHolder.getBean("commonDAOHibernate");
			QueryResult result = $d.findPage(hql.toString(), params, new Pagination(start,range));
			model.put("result",result.getItems());
			model.put("is_end",false);
		}
		model.put("split",--split);
		model.put("start",start+range);
		model.put("range",range);
		model.put("type","exp");
		return "/jtzs/jsonPage/jsonExpertQuestionList";
	}
	
	/** 我解答的问题（html） */
	@RequestMapping(value="/myAnswerList")
	public String myAnswerList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long teacherId = paramUtils.getLongParameter(request, "teacherId", 0L);
		Integer answerType = paramUtils.getIntParameter(request, "answerType", JTZSConstants.AnswerStatus.FINISH.getIndex());
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start =  paramUtils.getIntParameter(request, "start", 0);
		int split = JTZSConstants.publicSplit;
		
		AnswerQuery query = new AnswerQuery();
		if(teacherId > 0){
			query.setTeacherId(teacherId);
		}
		if(answerType == JTZSConstants.AnswerStatus.FINISH.getIndex()){
			query.setStatus(answerType);
		}else{
			query.setStatus(JTZSConstants.AnswerStatus.QUIT.getIndex());
		}
		query.setPageSize(range);
		query.setPageNumber(start);
		query.setSortColumns("answerTime desc");
		model.put("page", answerManager.findPage(query));
		
		Map<String,Object> jsondata = new HashMap<String,Object>();
		jsondata.put("ajaxUrl","/jtzs/teacher/jsonMyAnswerList?teacherId="+teacherId+"&answerType="+answerType);
		jsondata.put("range",range);
		jsondata.put("split",--split);
		jsondata.put("start",start+range);
		model.put("jsondata",jsonUtil.object2Json(jsondata));
		model.put("type","answer");
		return "/jtzs/myAnswerList";
	}
	
	/** 我回答的问题 */
	@RequestMapping(value="/jsonMyAnswerList")
	public String jsonMyAnswerList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long teacherId = paramUtils.getLongParameter(request, "teacherId", 0L);
		Integer answerType = paramUtils.getIntParameter(request, "answerType", JTZSConstants.AnswerStatus.FINISH.getIndex());
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start = paramUtils.getIntParameter(request, "start", 0);
		int split = paramUtils.getIntParameter(request,"split",0);
		
		if(split==0){
			model.put("is_end",true);
		}else{
			String hql = "from Answer where teacherId=? and status=? order by answerTime desc";
			Object[] params = new Object[2];
			params[0] = teacherId;
			params[1] = answerType;
			CommonDAOHibernate $d = (CommonDAOHibernate)ApplicationContextHolder.getBean("commonDAOHibernate");
			QueryResult result = $d.findPage(hql.toString(), params, new Pagination(start,range));
			model.put("result",result.getItems());
			model.put("is_end",false);
			--split;
		}
		model.put("split",split);
		model.put("start",start+range);
		model.put("range",range);
		model.put("type","answer");
		return "/jtzs/jsonPage/jsonMyAnswerList";
	}
	
	/** 我解答的问题的评论列表（html） */
	@RequestMapping(value="/myCommentList")
	public String myCommentList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long teacherId = paramUtils.getLongParameter(request, "teacherId", 0L);
		Integer commentType = paramUtils.getIntParameter(request, "commentType", JTZSConstants.SatisfiedStatus.SATISFIED.getIndex());
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start = paramUtils.getIntParameter(request, "start", 0);
		int split = JTZSConstants.publicSplit;
		
		CommentQuery query = new CommentQuery();
		if(teacherId > 0){
			query.setTeacherId(teacherId);
		}
		if(commentType == JTZSConstants.SatisfiedStatus.SATISFIED.getIndex()){
			query.setIsSatisfied(commentType);
		}else{
			query.setIsSatisfied(JTZSConstants.SatisfiedStatus.UNSATISFIED.getIndex());
		}
		query.setIsDel(JTZSConstants.IsDel.UNDELETE.getIndex());
		query.setSortColumns("addTime desc");
		query.setPageSize(range);
		query.setPageNumber(start);
		model.put("page", commentManager.findPage(query));
		model.put("commentType", commentType);
		model.put("teacherId", teacherId);
		
		Map<String,Object> jsondata = new HashMap<String,Object>();
		jsondata.put("ajaxUrl","/jtzs/teacher/jsonMyCommentList?teacherId="+teacherId+"&commentType="+commentType);
		jsondata.put("range",range);
		jsondata.put("split",--split);
		jsondata.put("start",start+range);
		model.put("jsondata",jsonUtil.object2Json(jsondata));
		model.put("type","commont");
		return "/jtzs/myCommentList";
	}
	
	/** 我解答的问题 */
	@RequestMapping(value="/jsonMyCommentList")
	public String jsonMyCommentList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long teacherId = paramUtils.getLongParameter(request, "teacherId", 0L);
		Integer commentType = paramUtils.getIntParameter(request, "commentType", JTZSConstants.SatisfiedStatus.SATISFIED.getIndex());
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start =  paramUtils.getIntParameter(request, "start", 0);
		int split = paramUtils.getIntParameter(request,"split",0);
		if(split==0){
			model.put("is_end",true);
		}else{
			String hql = "from Comment where teacherId=? and isSatisfied=? and isDel=? order by addTime desc";
			Object[] params = new Object[3];
			params[0] = teacherId;
			params[1] = commentType;
			params[2] = JTZSConstants.IsDel.UNDELETE.getIndex();
			CommonDAOHibernate $d = (CommonDAOHibernate)ApplicationContextHolder.getBean("commonDAOHibernate");
			QueryResult result = $d.findPage(hql.toString(), params, new Pagination(start,range));
			model.put("result",result.getItems());
			model.put("is_end",false);
			--split;
		}
		model.put("split",split);
		model.put("start",start+range);
		model.put("range",range);
		model.put("type","comment");
		return "/jtzs/jsonPage/jsonMyCommentList";
	}
	
	/** 抢答问题 */
	@RequestMapping(value="/getAnswerChance")
	public String getAnswerChance(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long teacherId = paramUtils.getLongParameter(request, "teacherId", 0L);
		Long questionId = paramUtils.getLongParameter(request, "questionId", 0L);
		
		Question question = questionManager.getById(questionId);
		if(null!=question){
			if(JTZSConstants.QuestionStatus.ENDANSWER.getIndex() == question.getStatus()){
				code = JTZSConstants.ErrorCode.ls002;
			}else if(JTZSConstants.QuestionStatus.NOWANSWER.getIndex() == question.getStatus()){
				code = JTZSConstants.ErrorCode.ls003;
			}else{
				question.setStatus(JTZSConstants.QuestionStatus.NOWANSWER.getIndex());
				question.setAnswerTeacherId(teacherId);
				questionManager.update(question);
				code = JTZSConstants.ErrorCode.gg000;
				rlt = RLT_SUCCESS;
				model.put("question", question);
			}
		}
		
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/teacher/getAnswerChance";
	}
	
	/** 举报问题 */
	@RequestMapping(value="/reportQuestion")
	public String reportQuestion(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long teacherId = paramUtils.getLongParameter(request, "teacherId", 0L);
		Long questionId = paramUtils.getLongParameter(request, "questionId", 0L);
		String content = paramUtils.getParameter(request, "content", "");
		
		Question question = questionManager.getById(questionId);
		if(null!=question){
			question.setIsLock(JTZSConstants.QuestionIsLock.LOCK.getIndex());
			question.setReportId(teacherId);
			question.setReportTime(new Date().getTime());
			question.setReportContent(content);
			question.setAuditState(JTZSConstants.AuditState.UNTREATED.getIndex());
			questionManager.update(question);
			rlt = RLT_SUCCESS;
			code = JTZSConstants.ErrorCode.gg000;
		}
		
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/teacher/reportQuestion";
	}
	
	/** 问题不清晰 */
	@RequestMapping(value="/questionNotClear")
	public String questionNotClear(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long teacherId = paramUtils.getLongParameter(request, "teacherId", 0L);
		Long questionId = paramUtils.getLongParameter(request, "questionId", 0L);
		String content = paramUtils.getParameter(request, "content", "");
		
		Question question = questionManager.getById(questionId);
		if(null!=question){
			question.setIsLock(JTZSConstants.QuestionIsLock.LOCK.getIndex());
			question.setReportId(teacherId);
			question.setReportTime(new Date().getTime());
			question.setReportContent(content);
			question.setAuditState(JTZSConstants.AuditState.UNTREATED.getIndex());
			questionManager.update(question);
			rlt = RLT_SUCCESS;
			code = JTZSConstants.ErrorCode.gg000;
		}
		
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/teacher/questionNotClear";
	}
	
	/** 放弃回答 */
	@RequestMapping(value="/quitAnswer")
	public String quitAnswer(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long teacherId = paramUtils.getLongParameter(request, "teacherId", 0L);
		Long questionId = paramUtils.getLongParameter(request, "questionId", 0L);
		
		Question question = questionManager.getById(questionId);
		if(null!=question){
			question.setStatus(JTZSConstants.QuestionStatus.NOANSWER.getIndex());
			question.setSourceType(JTZSConstants.QuestionSourceType.QUIT.getIndex());
			questionManager.update(question);
			
			Answer answer = new Answer();
			answer.setQuestionId(questionId);
			answer.setTeacherId(teacherId);
			answer.setAnswerTime(new Date().getTime());
			answer.setStatus(JTZSConstants.AnswerStatus.QUIT.getIndex());
			answerManager.save(answer);
			scoreManager.addTeacherScore(teacherId, commonRuleManager.getCacheRuleByType(JTZSConstants.CommonRuleType.TeacherQuitAnswer.getIndex()));
			teacherStatisticsManager.addQuitAnswer(teacherId);
			rlt = RLT_SUCCESS;
			code = JTZSConstants.ErrorCode.gg000;
		}
		
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/teacher/quitAnswer";
	}
	
	
	/** 提交答案 */
	@RequestMapping(value="/commitAnswer")
	public String commitAnswer(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long teacherId = paramUtils.getLongParameter(request, "teacherId", 0L);
		Long questionId = paramUtils.getLongParameter(request, "questionId", 0L);
		String content = paramUtils.getParameter(request, "content", "");
		String ider = paramUtils.getParameter(request, "ider", "");
		String imgPath = paramUtils.getParameter(request, "imgPath", JTZSConstants.DEFOULT_UPLOAD_IMG);
		
		Question question = questionManager.getById(questionId);
		if(null!=question){
			question.setStatus(JTZSConstants.QuestionStatus.ENDANSWER.getIndex());
			question.setKnowledgeId(0L);
			questionManager.update(question);
			
			Answer answer = new Answer();
			answer.setQuestionId(questionId);
			answer.setTeacherId(teacherId);
			answer.setAnswerTime(new Date().getTime());
			answer.setIder(ider);
			answer.setContent(content);
			answer.setImgPath(imgPath);
			answer.setStatus(JTZSConstants.AnswerStatus.FINISH.getIndex());
			answerManager.save(answer);
			scoreManager.addTeacherScore(teacherId, commonRuleManager.getCacheRuleById(question.getScoreType()));
			if(JTZSConstants.CommonRuleType.Expert.getIndex()==question.getScoreType()){
				teacherStatisticsManager.addExpertAnswer(teacherId);
			}else{
				teacherStatisticsManager.addAnswer(teacherId,commonRuleManager.getCacheRuleById(question.getScoreType()));
			}
			SystemMessage sm = new SystemMessage();
			sm.setAddTime(new Date().getTime());
			sm.setUserId(question.getStudentId());
			sm.setUserRole(JTZSConstants.ROLE_STUDENT);
			sm.setQuestionId(questionId);
			sm.setContent("您提出的问题已由"+answer.getTeacher().getNickName()+"解答完毕，请给辛苦的老师一个评价吧。");
			sm.setAddUserId(authUserManager.getAdminMember().getId());
			sm.setIsRead(JTZSConstants.SystemMessageIsRead.UNREAD.getIndex());
			sm.setStatus(JTZSConstants.Status.FREEZE.getIndex());
			String token = SessionUtil.getToken(request, answer.getTeacherId().toString());
			if(null != token){
				IPhonePush.push(token,sm.getContent(), 1,"push.p12");
				sm.setStatus(JTZSConstants.Status.NORMAL.getIndex());
			}
			systemMessageManager.save(sm);
			rlt = RLT_SUCCESS;
			code = JTZSConstants.ErrorCode.gg000;
		}else{
			code = JTZSConstants.ErrorCode.gg010;
		}
		
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/teacher/commitAnswer";
	}
	
	/** 老师分享给学生获得积分 */
	@RequestMapping(value="/shareToStudentScore")
	public String shareToStudentScore(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		Long teacherId = paramUtils.getLongParameter(request, "teacherId", 0L);
		long[] phoneNum = paramUtils.getLongParameters(request, "phoneNum", 0L);
		if(null != phoneNum){
			for (int i = 0; i < phoneNum.length; i++) {
				if(phoneNum[i]>0){
					System.out.println(phoneNum[i]);
					SmsUtils smsutil =  new SmsUtils();
					String msg = smsutil.sendMsg011(phoneNum[i]+"", "你的好友邀请你加入解题小能手");
					if(!"error".equals(msg)){
						scoreManager.addTeacherScore(teacherId, commonRuleManager.getCacheRuleByType(JTZSConstants.CommonRuleType.TeacherShare.getIndex()));
					}
				}
			}
		}
		code = JTZSConstants.ErrorCode.gg000;
		rlt = RLT_SUCCESS;
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/teacher/shareToStudentScore";
	}
	
}

