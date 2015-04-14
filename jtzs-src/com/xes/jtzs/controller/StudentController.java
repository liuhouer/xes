/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */


package com.xes.jtzs.controller;

import static com.up72.common.CommonUtils.encryptUtil;
import static com.up72.common.CommonUtils.jsonUtil;

import com.up72.page.Pagination;
import com.up72.page.QueryResult;
import com.up72.util.DateUtils;
import com.up72.util.SessionUtil;
import com.up72.util.SmsUtils;

import static com.up72.common.CommonUtils.paramUtils;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.up72.auth.controller.AuthController;
import com.up72.auth.member.service.AuthUserManager;
import com.up72.common.CommonConstants;
import com.up72.dao.hibernate.CommonDAOHibernate;
import com.up72.framework.page.Page;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.framework.web.scope.Flash;
import com.up72.iphone.util.IPhonePush;
import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.model.Answer;
import com.xes.jtzs.model.Comment;
import com.xes.jtzs.model.CommonRule;
import com.xes.jtzs.model.Event;
import com.xes.jtzs.model.Question;
import com.xes.jtzs.model.Score;
import com.xes.jtzs.model.Student;
import com.xes.jtzs.model.SystemMessage;
import com.xes.jtzs.service.AnswerManager;
import com.xes.jtzs.service.CommentManager;
import com.xes.jtzs.service.CommonRuleManager;
import com.xes.jtzs.service.EventManager;
import com.xes.jtzs.service.QuestionManager;
import com.xes.jtzs.service.ScoreLogManager;
import com.xes.jtzs.service.ScoreManager;
import com.xes.jtzs.service.StudentManager;
import com.xes.jtzs.service.SystemMessageManager;
import com.xes.jtzs.service.TeacherManager;
import com.xes.jtzs.vo.query.StudentQuery;
/**
 * 数据提取跳转
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/jtzs/student")
public class StudentController extends AuthController<Student,java.lang.Long>{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	@Autowired	
	private AuthUserManager authUserManager;
	@Autowired	
	private SystemMessageManager systemMessageManager;
	@Autowired	
	private AnswerManager answerManager;
	@Autowired	
	private CommentManager commentManager;
	@Autowired	
	private CommonRuleManager commonRuleManager;
	@Autowired	
	private TeacherManager teacherManager;
	
	private StudentManager studentManager;
	@Autowired
	private ScoreManager scoreManager;
	@Autowired
	private QuestionManager questionManager;
	@Autowired
	private EventManager eventManager;
	
	private final String LIST_ACTION = "redirect:/jtzs/student";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setStudentManager(StudentManager manager) {
		this.studentManager = manager;
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
	public String index(ModelMap model,StudentQuery query,HttpServletRequest request,HttpServletResponse response) {
		Page page = this.studentManager.findPage(query);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/jtzs/student/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Student student = (Student)studentManager.getById(id);
		model.addAttribute("student",student);
		return "/jtzs/student/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Student student,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("student",student);
		return "/jtzs/student/new";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid Student student,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/jtzs/student/new";
		}
		
		studentManager.save(student);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		Student student = (Student)studentManager.getById(id);
		model.addAttribute("student",student);
		return "/jtzs/student/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.Long id,@Valid Student student,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/jtzs/student/edit";
		}
		
		studentManager.update(student);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		studentManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			studentManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	///////////////////////////////////////////////
	/** 学生注册 */
	@RequestMapping(value="/studentRegist")
	public String studentRegist(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String sysCode = JTZSConstants.ErrorCode.gg500;
		Map<String,Object> data = new HashMap<String,Object>();
		
		String loginName = paramUtils.getParameter(request, "loginName", "");
		String password = paramUtils.getParameter(request, "password", "");
		String code = paramUtils.getParameter(request, "code", "-1");
		try {
			String sessionCode = (String)request.getSession().getAttribute(loginName);
			if(null != sessionCode && sessionCode.equals(code)){
				if(studentManager.isUniqueLoginName(loginName, null) && teacherManager.isUniqueLoginName(loginName, null)){
					if(password.length() < 6 || password.length() > 18){
						sysCode = JTZSConstants.ErrorCode.gg007;
					}else{
						Student student  = new Student();
						student.setPassword(encryptUtil.md5(password));
						student.setAddTime(new Date().getTime());
						student.setStatus(JTZSConstants.Status.NORMAL.getIndex());
						student.setLoginName(loginName);
						if(null == student.getImgPath()){
							if(null!=student.getSex() && student.getSex()==JTZSConstants.Sex.WOMEN.getIndex()){
								student.setImgPath(JTZSConstants.DEFOULT_IMG_WOMAN);
							}else{
								student.setImgPath(JTZSConstants.DEFOULT_IMG_MAN);
							}
						}
						studentManager.save(student);
						data.put("role", JTZSConstants.ROLE_STUDENT);
						data.put("id", student.getId());
						data.put("student", student);
						
						scoreManager.addStudentScore(student.getId(),commonRuleManager.getCacheRuleByType(JTZSConstants.CommonRuleType.StudentFirstRegist.getIndex()));
						rlt = RLT_SUCCESS;
						sysCode = JTZSConstants.ErrorCode.gg000;
					}
				}else{
					sysCode = JTZSConstants.ErrorCode.gg002;
				}
			}else{
				sysCode = JTZSConstants.ErrorCode.gg004;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.put("data", data);
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(sysCode));
		model.put("code", sysCode);
		model.put("profile", JTZSConstants.UNPROFILE);
		return "/jtzs/student/studentRegist";
	}
	
	/** 学生信息 */
	@RequestMapping(value="/getStudentInfo")
	public String getStudentInfo(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long studentId = paramUtils.getLongParameter(request, "studentId", 0L);
		
		Student student = studentManager.getById(studentId);
		if(null != student){
			long eventSize = eventManager.getStudentEventCount(student);
			model.put("eventSize", eventSize);//取得学生的活动条数
			model.put("student", student);
			code = JTZSConstants.ErrorCode.gg000;
			rlt = RLT_SUCCESS;
		}else{
			code = JTZSConstants.ErrorCode.gg008;
		}
		
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/student/getStudentInfo";
	}
	
	/** 修改学生信息 */
	@RequestMapping(value="/modifyStudentInfo")
	public String modifyStudentInfo(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long studentId = paramUtils.getLongParameter(request, "studentId", 0L);
		Student student = studentManager.getById(studentId);
		if(null != student){
			String imgPath = paramUtils.getParameter(request, "imgPath", "");
			String nickName = paramUtils.getParameter(request, "nickName", "");
			Long provinceId = paramUtils.getLongParameter(request, "provinceId", 0L);
			Long cityId = paramUtils.getLongParameter(request, "cityId", 0L);
			Long areaId = paramUtils.getLongParameter(request, "areaId", 0L);
			Long gradeId = paramUtils.getLongParameter(request, "gradeId", 0L);
			Long schoolId = paramUtils.getLongParameter(request, "schoolId", 0L);
			int sex = paramUtils.getIntParameter(request, "sex", JTZSConstants.Sex.MEN.getIndex());
			if(sex == JTZSConstants.Sex.WOMEN.getIndex() &&( "".equals(imgPath) || imgPath.equals(JTZSConstants.DEFOULT_IMG_MAN))){
				student.setImgPath(JTZSConstants.DEFOULT_IMG_WOMAN);
			}else if(sex == JTZSConstants.Sex.MEN.getIndex() &&( "".equals(imgPath) || imgPath.equals(JTZSConstants.DEFOULT_IMG_WOMAN))){
				student.setImgPath(JTZSConstants.DEFOULT_IMG_MAN);
			}else{
				student.setImgPath(imgPath);
			}
			student.setNickName(nickName);
			if(provinceId > 0){
				student.setProvinceId(provinceId);
			}
			if(cityId > 0){
				student.setCityId(cityId);
			}
			if(areaId > 0){
				student.setAreaId(areaId);
			}
			if(gradeId > 0){
				student.setGradeId(gradeId);
			}
			if(schoolId > 0){
				student.setSchoolId(schoolId);
			}
			student.setSex(sex);
			studentManager.update(student);
			model.put("student", student);
			code = JTZSConstants.ErrorCode.gg000;
			rlt = RLT_SUCCESS;
		}else{
			code = JTZSConstants.ErrorCode.gg008;
		}
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/student/modifyStudentInfo";
	}
	
	/** 学生退出 */
	@RequestMapping(value="/studentLogout")
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
		return "/jtzs/student/studentLogout";
	}
	
	/** 学生放弃回答 */
	@RequestMapping(value="/quitQuestion")
	public String quitQuestion(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long questionId = paramUtils.getLongParameter(request, "questionId", 0L);
		Long studentId = paramUtils.getLongParameter(request, "studentId", 0L);
		Question question = questionManager.getById(questionId);
		if(null != question){
			 if(question.getStudentId().equals(studentId)){
				 if(JTZSConstants.QuestionStatus.NOANSWER.getIndex() == question.getStatus()){
					 question.setIsQuit(JTZSConstants.QuestionIsQuit.QUIT.getIndex());
					 questionManager.update(question);
					 code = JTZSConstants.ErrorCode.gg000;
					 rlt = RLT_SUCCESS;
				 }else if(JTZSConstants.QuestionStatus.ENDANSWER.getIndex() == question.getStatus()){
					 code = JTZSConstants.ErrorCode.xs003;
				 }else if(JTZSConstants.QuestionStatus.NOWANSWER.getIndex() == question.getStatus()){
					 code = JTZSConstants.ErrorCode.xs008;
				 }
			 }else{
				 code = JTZSConstants.ErrorCode.xs002;
			 }
		}else{
			code = JTZSConstants.ErrorCode.xs007;
		}
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/student/quitQuestion";
	}
	
	/** 学生修改密码 */
	@RequestMapping(value="/modifyStudentPassword")
	public String modifyStudentPassword(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;

		Long studentId = paramUtils.getLongParameter(request, "studentId", 0L);
		String oldPassword = paramUtils.getParameter(request, "oldPassword", "");
		String newPassword = paramUtils.getParameter(request, "newPassword", "");
		try {
			code = studentManager.resetPwd(oldPassword, newPassword, studentId);
			if(JTZSConstants.ErrorCode.gg000.equals(code)){
				rlt = RLT_SUCCESS;
			}
		} catch (Exception e) {
		}
		
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/student/modifyStudentPassword";
	}
	
	/** 我的问题列表 */
	@RequestMapping(value="/myQuestionList")
	public String myQuestionList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long studentId = paramUtils.getLongParameter(request, "studentId", 0L);
		int questionStatus = paramUtils.getIntParameter(request, "questionStatus",0);
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start =  paramUtils.getIntParameter(request, "start", 0);
		int split = JTZSConstants.publicSplit;
		
		Page page = questionManager.myQuestionList(studentId,questionStatus);
		model.put("page", page);
		model.put("questionStatus", questionStatus);
		model.put("studentId", studentId);
		
		Map<String,Object> jsondata = new HashMap<String,Object>();
		jsondata.put("ajaxUrl","/jtzs/student/jsonMyQuestionList?studentId="+studentId+"&questionStatus="+questionStatus);
		jsondata.put("range",range);
		jsondata.put("split",--split);
		jsondata.put("start",start+range);
		model.put("jsondata",jsonUtil.object2Json(jsondata));
		return "/jtzs/myList";
	}
	
	/** json我的问题 */
	@RequestMapping(value="/jsonMyQuestionList")
	public String jsonMyQuestionList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long studentId = paramUtils.getLongParameter(request, "studentId", 0L);
		int questionStatus = paramUtils.getIntParameter(request, "questionStatus",0);
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start =  paramUtils.getIntParameter(request, "start", 0);
		int split = paramUtils.getIntParameter(request,"split",0);
		if(split==0){
			model.put("is_end",true);
		}else{
			String hql = "from Question where studentId=? and status=? and isDel=? and isLock=? and isQuit=? order by addTime desc";
			Object[] params = new Object[5];
			params[0] = studentId;
			params[1] = questionStatus;
			params[2] = JTZSConstants.IsDel.UNDELETE.getIndex();
			params[3] = JTZSConstants.QuestionIsLock.UNLOCK.getIndex();
			params[4] = JTZSConstants.QuestionIsQuit.NORMAL.getIndex();
			CommonDAOHibernate $d = (CommonDAOHibernate)ApplicationContextHolder.getBean("commonDAOHibernate");
			QueryResult result = $d.findPage(hql.toString(), params, new Pagination(start,range));
			model.put("result",result.getItems());
			model.put("is_end",false);
		}
		model.put("split",--split);
		model.put("start",start+range);
		model.put("range",range);
		model.put("type","my");
		return "/jtzs/jsonPage/jsonMyQuestionList";
	}
	
	/** 提交问题 */
	@RequestMapping(value="/commitQuestion")
	public String commitQuestion(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long studentId = paramUtils.getLongParameter(request, "studentId", 0L);
		Long answerType = paramUtils.getLongParameter(request, "answerType", 0);
		Long subjectId = paramUtils.getLongParameter(request, "subjectId", 0L);
		Long gradeId = paramUtils.getLongParameter(request, "gradeId", 0L);
		String imgPath = paramUtils.getParameter(request, "imgPath", JTZSConstants.DEFOULT_UPLOAD_IMG);
		String content = paramUtils.getParameter(request, "content", "");
		Integer platform = paramUtils.getIntParameter(request, "platform",0);
		
		Question question = new Question();
		question.setImgPath(imgPath);
		question.setContent(content);
		question.setGradeId(gradeId);
		question.setSubjectId(subjectId);
		question.setStudentId(studentId);
		question.setPlatform(platform);
		question.setScoreType(answerType);
		question.setStatus(JTZSConstants.QuestionStatus.NOANSWER.getIndex());
		question.setIsLock(JTZSConstants.QuestionIsLock.UNLOCK.getIndex());
		question.setIsQuit(JTZSConstants.QuestionIsQuit.NORMAL.getIndex());
		question.setIsDel(JTZSConstants.IsDel.UNDELETE.getIndex());
		
		CommonRule commonRule  = commonRuleManager.getCacheRuleById(answerType);
		if(null == commonRule){//如果没有积分规则，取数据库中默认规则
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("isDefault", 1);
			List<CommonRule> list = commonRuleManager.findList(params, 0, null);
			if(null!=list && list.size() == 1){
				commonRule = list.get(0);
			}
		}
		if(commonRuleManager.isValid(commonRule)){
			question.setAddTime(new Date().getTime());
			if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.Expert.getIndex()){
				question.setSourceType(JTZSConstants.QuestionSourceType.EXPERT.getIndex());
			}else{
				question.setSourceType(JTZSConstants.QuestionSourceType.NORMAL.getIndex());
				question.setStopTime(question.getAddTime() + commonRule.getMinute() * 60 * 1000);
			}
			if(scoreManager.addStudentScore(studentId,commonRuleManager.getCacheRuleById(commonRule.getId()))){
				questionManager.save(question);
				rlt = RLT_SUCCESS;
				code = JTZSConstants.ErrorCode.gg000;
			}else{
				code = JTZSConstants.ErrorCode.xs006;
			}
		}
		model.put("data", question);
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/student/commitQuestion";
	}
	
	/** 回答类型 */
	@RequestMapping(value="/queryAnswerType")
	public String queryAnswerType(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		model.put("data", commonRuleManager.queryByType());
		rlt = RLT_SUCCESS;
		code = JTZSConstants.ErrorCode.gg000;
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/student/queryAnswerType";
	}
	
	/** 是否开放答疑时间 */
	@RequestMapping(value="/isOpenTime")
	public String isOpenTime(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		CommonRule commonRule = commonRuleManager.getCacheRuleByType(JTZSConstants.CommonRuleType.SystemOpenTime.getIndex());
		if(commonRuleManager.isValid(commonRule)){
			code = JTZSConstants.ErrorCode.xs005;
			if(null != commonRule.getAddTime()&&null!=commonRule.getEndTime()){
				long time = DateUtils.getDayTimeToLong();
				if(commonRule.getBeginTime() < commonRule.getEndTime()){
					if(commonRule.getBeginTime() <= time && time <= commonRule.getEndTime()){
						rlt = RLT_SUCCESS;
						code = JTZSConstants.ErrorCode.gg000;
					}
				}else{
					if(commonRule.getEndTime() <= time && time <= commonRule.getBeginTime()){
						rlt = RLT_SUCCESS;
						code = JTZSConstants.ErrorCode.gg000;
					}
				}
			}
			if(JTZSConstants.ErrorCode.xs005.equals(code)){
				model.put("msg", JTZSConstants.ErrorCode.getMag(code)+"，答疑时间为"+commonRule.getBeginTimeStr()+"点—"+commonRule.getEndTimeStr()+"点");
			}
		}
		model.put("data", "");
		model.put("rlt", rlt);
		if(!JTZSConstants.ErrorCode.xs005.equals(code)){
			model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		}
		model.put("code", code);
		return "/jtzs/student/isOpenTime";
	}
	
	/** 提交评论 */
	@RequestMapping(value="/commitComment")
	public String commitComment(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long studentId = paramUtils.getLongParameter(request, "studentId", 0L);
		Long questionId = paramUtils.getLongParameter(request, "questionId", 0L);
		Integer satisfied = paramUtils.getIntParameter(request, "satisfied", JTZSConstants.SatisfiedStatus.SATISFIED.getIndex());
		String content = paramUtils.getParameter(request, "content", "");
		
		Answer answer= answerManager.getAnswerByQuestionId(questionId);
		if(null != answer){
			Comment comment = new Comment();
			comment.setAnswerId(answer.getId());
			comment.setContent(content);
			comment.setIsSatisfied(satisfied);
			comment.setStudentId(studentId);
			comment.setIsDel(JTZSConstants.IsDel.UNDELETE.getIndex());
			comment.setAddTime(new Date().getTime());
			comment.setTeacherId(answer.getTeacherId());
			code = commentManager.commitComment(comment);
			if(code.equals(JTZSConstants.ErrorCode.gg000)){
				SystemMessage sm = new SystemMessage();
				sm.setAddTime(new Date().getTime());
				sm.setUserId(answer.getTeacherId());
				sm.setUserRole(JTZSConstants.ROLE_TEACHER);
				sm.setQuestionId(questionId);
				sm.setContent(comment.getStudent().getNickName()+"同学为您解答的问题进行了评价。");
				sm.setAddUserId(authUserManager.getAdminMember().getId());
				sm.setIsRead(JTZSConstants.SystemMessageIsRead.UNREAD.getIndex());
				sm.setStatus(JTZSConstants.Status.FREEZE.getIndex());
				if(teacherManager.isFreeTime(answer.getTeacher())){
					String token = SessionUtil.getToken(request, answer.getTeacherId().toString());
					if(null != token){
						IPhonePush.push(token,sm.getContent(), 1,"push.p12");
						sm.setStatus(JTZSConstants.Status.NORMAL.getIndex());
					}
				}
				systemMessageManager.save(sm);
				rlt = RLT_SUCCESS;
			}
		}else{
			code = JTZSConstants.ErrorCode.ls004;
		}
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/student/commitComment";
	}
	
	/** 学生分享试题获取积分 */
	@RequestMapping(value="/shareQuestionScore")
	public String shareToStudentScore(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		Long studentId = paramUtils.getLongParameter(request, "studentId", 0L);
		scoreManager.addStudentScore(studentId, commonRuleManager.getCacheRuleByType(JTZSConstants.CommonRuleType.StudentShareQuestion.getIndex()));
		code = JTZSConstants.ErrorCode.gg000;
		rlt = RLT_SUCCESS;
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/student/shareQuestionScore";
	}
	
	/** 学生分享软件获取积分 */
	@RequestMapping(value="/shareSoftwareScore")
	public String shareSoftwareScore(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		Long studentId = paramUtils.getLongParameter(request, "studentId", 0L);
		scoreManager.addStudentScore(studentId, commonRuleManager.getCacheRuleByType(JTZSConstants.CommonRuleType.StudentShareSoftware.getIndex()));
		code = JTZSConstants.ErrorCode.gg000;
		rlt = RLT_SUCCESS;
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/student/shareSoftwareScore";
	}
	
	/** 学生推荐给好友获取积分 */
	@RequestMapping(value="/inviteFriendsScore")
	public String inviteFriendsScore(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		Long studentId = paramUtils.getLongParameter(request, "studentId", 0L);
		String phoneNum = paramUtils.getParameter(request, "phoneNum", "");
		if(!"".equals(phoneNum)){
			String[] phones = phoneNum.split(";");
			for (int i = 0; i < phones.length; i++) {
				if(phones[i].matches("\\d+")){
					SmsUtils smsutil =  new SmsUtils();
					String msg = smsutil.sendMsg011(phones[i], "你的好友邀请你加入解题小能手");
					if(!"error".equals(msg)){
						scoreManager.addStudentScore(studentId, commonRuleManager.getCacheRuleByType(JTZSConstants.CommonRuleType.StudentInviteFriends.getIndex()));
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
		return "/jtzs/student/inviteFriendsScore";
	}
	
}

