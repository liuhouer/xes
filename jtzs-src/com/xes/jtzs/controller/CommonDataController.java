package com.xes.jtzs.controller;

import static com.up72.common.CommonUtils.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.up72.base.BaseRestSpringController;
import com.up72.dao.hibernate.CommonDAOHibernate;
import com.up72.framework.page.Page;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.page.Pagination;
import com.up72.page.QueryResult;
import com.up72.util.SessionUtil;
import com.up72.util.SmsUtils;
import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.model.Answer;
import com.xes.jtzs.model.AnswerShow;
import com.xes.jtzs.model.Event;
import com.xes.jtzs.model.EventUser;
import com.xes.jtzs.model.Feedback;
import com.xes.jtzs.model.Question;
import com.xes.jtzs.model.Score;
import com.xes.jtzs.model.ScoreLog;
import com.xes.jtzs.model.Student;
import com.xes.jtzs.model.Teacher;
import com.xes.jtzs.service.AnswerShowManager;
import com.xes.jtzs.service.AreaManager;
import com.xes.jtzs.service.CityManager;
import com.xes.jtzs.service.CommentManager;
import com.xes.jtzs.service.EventManager;
import com.xes.jtzs.service.EventUserManager;
import com.xes.jtzs.service.GradeManager;
import com.xes.jtzs.service.ProvinceManager;
import com.xes.jtzs.service.QuestionManager;
import com.xes.jtzs.service.SchoolManager;
import com.xes.jtzs.service.ScoreLogManager;
import com.xes.jtzs.service.ScoreManager;
import com.xes.jtzs.service.StudentManager;
import com.xes.jtzs.service.SubjectManager;
import com.xes.jtzs.service.SystemMessageManager;
import com.xes.jtzs.service.TeacherManager;
import com.xes.jtzs.service.TeacherStatisticsManager;
import com.xes.jtzs.vo.query.CommentQuery;
import com.xes.jtzs.vo.query.EventQuery;
import com.xes.jtzs.vo.query.QuestionQuery;
import com.xes.jtzs.vo.query.ScoreLogQuery;
import com.xes.jtzs.vo.query.SystemMessageQuery;

@Controller
@RequestMapping("/jtzs/commonData")
public class CommonDataController extends BaseRestSpringController<Object,java.lang.Long>{
	@Autowired	
	private SystemMessageManager systemMessageManager;
	@Autowired	
	private EventUserManager eventUserManager;
	@Autowired	
	private AnswerShowManager answerShowManager;
	@Autowired	
	private CommentManager commentManager;
	@Autowired	
	private QuestionManager questionManager;
	@Autowired
	private ProvinceManager provinceManager;
	@Autowired
	private CityManager cityManager;
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private GradeManager gradeManager;
	@Autowired
	private SchoolManager schoolManager;
	@Autowired
	private TeacherManager teacherManager;
	@Autowired
	private TeacherStatisticsManager teacherStatisticsManager;
	@Autowired
	private StudentManager studentManager;
	@Autowired
	private SubjectManager subjectManager;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScoreLogManager scoreLogManager;
	
	@Autowired
	private ScoreManager scoreManager;
	
	
	
	/** 获得省份列表 */
	@RequestMapping(value="/queryProvinceData")
	public String queryProvinceData(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		List data = null;
		try {
			data = provinceManager.getProvinceListByInitial(false);
			rlt = RLT_SUCCESS;
			code = JTZSConstants.ErrorCode.gg000;
		} catch (Exception e) {
		}
		
		model.put("data",data);
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/commonData/queryProvinceData";
	}
	
	/** 获得城市列表 */
	@RequestMapping(value="/queryCityData")
	public String queryCityData(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long provinceId = paramUtils.getLongParameter(request, "provinceId", 0);
		List data = null;
		try {
			data = cityManager.getCityByInitial(provinceId,false);
			rlt = RLT_SUCCESS;
			code = JTZSConstants.ErrorCode.gg000;
		} catch (Exception e) {
		}
		
		model.put("data",data);
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/commonData/queryCityData";
	}
	
	/** 获得区县列表 */
	@RequestMapping(value="/queryAreaData")
	public String queryAreaData(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long cityId = paramUtils.getLongParameter(request, "cityId", 0);
		List data = null;
		try {
			data = areaManager.getAreaByInitial(cityId,false);
			rlt = RLT_SUCCESS;
			code = JTZSConstants.ErrorCode.gg000;
		} catch (Exception e) {
		}
		
		model.put("data",data);
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/commonData/queryAreaData";
	}
	
	/** 获得年级列表 */
	@RequestMapping(value="/queryGradeData")
	public String queryGradeData(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		List data = null;
		try {
			data = gradeManager.getGradeBySort();
			rlt = RLT_SUCCESS;
			code = JTZSConstants.ErrorCode.gg000;
		} catch (Exception e) {
		}
		model.put("data",data);
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/commonData/queryGradeData";
	}
	
	/** 获得学校列表 */
	@RequestMapping(value="/querySchoolData")
	public String querySchoolData(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		Long provinceId = paramUtils.getLongParameter(request, "provinceId", 0);
		Long cityId = paramUtils.getLongParameter(request, "cityId", 0);
		Long areaId = paramUtils.getLongParameter(request, "areaId", 0);
		Integer division = paramUtils.getIntParameter(request, "division", 0);
		List data = null;
		try {
			data = schoolManager.getSchoolByInitial(provinceId, cityId, areaId,division,false);
			rlt = RLT_SUCCESS;
			code = JTZSConstants.ErrorCode.gg000;
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("data",data);
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/commonData/querySchoolData";
	}
	
	/** 学科列表 */
	@RequestMapping(value="/querySubjectData")
	public String querySubjectData(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		
		List data = null;
		try {
			data = subjectManager.getSubjectBySort();
			rlt = RLT_SUCCESS;
			code = JTZSConstants.ErrorCode.gg000;
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("data",data);
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/commonData/querySubjectData";
	}
	
	/** 对应学科下的年级列表 */
	@RequestMapping(value="/queryGradeDataBySubject")
	public String queryGradeDataBySubject(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		Long subjectId = paramUtils.getLongParameter(request, "subjectId", 0);
		List data = null;
		try {
			data = gradeManager.getGradeBySubject(subjectId);
			rlt = RLT_SUCCESS;
			code = JTZSConstants.ErrorCode.gg000;
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("data",data);
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/commonData/queryGradeData";
	}
	
	
	/** 
	 * 统一登录
	 * @author liutongling 
	 * @return 
	 */
	@RequestMapping(value="/doLogin")
	public String doLogin(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		Map<String,Object> data = new HashMap<String,Object>();

		String loginName = paramUtils.getParameter(request, "loginName", "");
		String password = paramUtils.getParameter(request, "password", "");
		try {
			if(StringUtils.isNotBlank(loginName)){
				Student student = studentManager.getByLoginName(loginName);
				if(null == student){
					Teacher teacher = teacherManager.getByLoginName(loginName);
					if(null == teacher){
						code = JTZSConstants.ErrorCode.gg008;
					}else{
						code = teacherManager.login(teacher, loginName, password);
						if(JTZSConstants.ErrorCode.gg000.equals(code)){
							if(JTZSConstants.ROLE_EXPERT.equals(teacher.getLevel())){
								data.put("role", JTZSConstants.ROLE_EXPERT);
							}else{
								data.put("role", JTZSConstants.ROLE_TEACHER);
							}
							data.put("id", teacher.getId());
							data.put("teacher", teacher);
							data.put("question", questionManager.queryNowAnswerQuestion(teacher.getId()));
							rlt = RLT_SUCCESS;
						}
					}
				}else{
					code = studentManager.login(student, loginName, password);
					if(JTZSConstants.ErrorCode.gg000.equals(code)){
						data.put("role", JTZSConstants.ROLE_STUDENT);
						data.put("id", student.getId());
						data.put("student", student);
						model.put("profile",studentManager.isProfiled(loginName));
						rlt = RLT_SUCCESS;
					}
				}
			}else{
				code = JTZSConstants.ErrorCode.gg001;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.put("data", data);
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/commonData/doLogin";
	}
	
	/** 获得验证码 */
	@RequestMapping(value="/getVerificationCode")
	public String getVerificationCode(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		String codetype = paramUtils.getParameter(request, "codeType", "");
		String loginName = paramUtils.getParameter(request, "loginName", "");
		try {
			if(codetype.equals(JTZSConstants.ZHUCE)){
				if(teacherManager.isUniqueLoginName(loginName, null) && studentManager.isUniqueLoginName(loginName,null)){
					String randomCode = SmsUtils.geneNums();
					//TODO
					randomCode = "000000";
					SmsUtils smsutil =  new SmsUtils();
					smsutil.sendMsg010(loginName, "您正在注册解题助手，手机验证码："+randomCode+"，30分钟内有效。");
					HttpSession session = request.getSession();
					session.setAttribute(loginName,randomCode);
					session.setMaxInactiveInterval(30*60); //session 过期时间
					rlt = RLT_SUCCESS;
					code = JTZSConstants.ErrorCode.gg000;
				}else{
					code = JTZSConstants.ErrorCode.gg002;
				}
			}else if(codetype.equals(JTZSConstants.XIUGAIMIMA)){
				if(teacherManager.isUniqueLoginName(loginName, null) && studentManager.isUniqueLoginName(loginName,null)){
					code = JTZSConstants.ErrorCode.gg003;
				}else{
					String randomCode = SmsUtils.geneNums();
					randomCode = "000000";
					SmsUtils smsutil =  new SmsUtils();
					smsutil.sendMsg010(loginName, "您正在修改解题助手密码，手机验证码："+randomCode+"，30分钟内有效。");
					HttpSession session = request.getSession();
					session.setAttribute(loginName,randomCode);
					session.setMaxInactiveInterval(30*60); //session 过期时间
					rlt = RLT_SUCCESS;
					code = JTZSConstants.ErrorCode.gg000;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/commonData/getVerificationCode";
	}
	
	/** 修改密码 */
	@RequestMapping(value="/modifyPassword")
	public String modifyPassword(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String sysCode = JTZSConstants.ErrorCode.gg500;
		
		String loginName = paramUtils.getParameter(request, "loginName", "");
		String newPassword = paramUtils.getParameter(request, "newPassword", "");
		String code = paramUtils.getParameter(request, "code", "-1");
		try {
			String sessionCode = (String)request.getSession().getAttribute(loginName);
			if(null != sessionCode && sessionCode.equals(code)){
				Teacher teacher = teacherManager.getByLoginName(loginName);
				if(null == teacher){
					Student student = studentManager.getByLoginName(loginName);
					if(null!=student){
						student.setPassword(encryptUtil.md5(newPassword));
						studentManager.update(student);
						rlt = RLT_SUCCESS;
						sysCode = JTZSConstants.ErrorCode.gg000;
					}else{
						sysCode = JTZSConstants.ErrorCode.gg008;
					}
				}else{
					teacher.setPassword(encryptUtil.md5(newPassword));
					teacherManager.update(teacher);
					rlt = RLT_SUCCESS;
					sysCode = JTZSConstants.ErrorCode.gg000;
				}
			}else{
				sysCode = JTZSConstants.ErrorCode.gg004;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(sysCode));
		model.put("code", sysCode);
		return "/jtzs/commonData/modifyPassword";
	}
	
	/** 活动列表 */
	@RequestMapping(value="/eventList")
	public String eventList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long provinceId = paramUtils.getLongParameter(request, "provinceId", 0L);
		Long cityId = paramUtils.getLongParameter(request, "cityId", 0L);
		Long userId = paramUtils.getLongParameter(request, "userId", 0L);
		String role = paramUtils.getParameter(request, "role" );
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start = paramUtils.getIntParameter(request, "start", 0);
		int split = JTZSConstants.publicSplit;
		
		Page eventList = null;
		if(provinceId!=0&&role!=null&&role!=""){
			boolean mat = role.matches("\\d+");//返回true为纯数字,否则就不是纯数字
			EventQuery equery = new EventQuery();
			if(mat==true){
				equery.setProvinceId(provinceId);
				equery.setSendTo(Integer.parseInt(role));
				equery.setSendStatus(JTZSConstants.ROLE_TEACHER);//已发送
				equery.setPageSize(range);
				equery.setPageNumber(start);
				equery.setSortColumns("sendTime desc");
			    eventList = eventManager.findPage(equery);
			}
			
		}else{
			EventQuery equery = new EventQuery();
			equery.setSendStatus(JTZSConstants.ROLE_TEACHER);//已发送
			equery.setPageSize(range);
			equery.setPageNumber(start);
			equery.setSortColumns("sendTime desc");
			eventList = eventManager.findPage(equery);
		}
		model.addAttribute("eventList",eventList);
		
		Map<String,Object> jsondata = new HashMap<String,Object>();
		jsondata.put("ajaxUrl","/jtzs/commenData/jsonEventList?provinceId="+provinceId+"&role="+role+"&cityId="+cityId+"&userId="+userId);
		jsondata.put("range",range);
		jsondata.put("split",--split);
		jsondata.put("start",start+range);
		model.put("jsondata",jsonUtil.object2Json(jsondata));
		model.put("type","commont");
		return "/jtzs/eventList";
	}
	
	/** 活动详情 */
	@RequestMapping(value="/{id}/eventShow")
	public String eventShow(ModelMap model,HttpServletRequest request,HttpServletResponse response,@PathVariable java.lang.Long id) throws Exception {
		Event event = eventManager.getById(id);
		model.addAttribute("event", event);
		return "/jtzs/eventShow";
	}
	
	/** 积分列表 */
	@RequestMapping(value="/queryScoreLog")
	public String queryScoreLog(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String msg = JTZSConstants.ErrorCode.gg500;
		
		Long userId = paramUtils.getLongParameter(request, "userId", 0L);
		int userRole = paramUtils.getIntParameter(request, "userRole",0);
		Long startTime = paramUtils.getLongParameter(request, "startTime",0);
		Long stopTime = paramUtils.getLongParameter(request, "stopTime",0);
		Score  score = scoreManager.getScoreByRole(userId, userRole);
		if(null != score){
			ScoreLogQuery slquery = new ScoreLogQuery();
			slquery.setUserId(userId);
			slquery.setUserRole(userRole);
			slquery.setPageSize(20);
			if(startTime >0){
				slquery.setAddTimeStart(startTime);
			}
			if(stopTime > 0){
				slquery.setAddTimeStop(stopTime);
			}
			slquery.setSortColumns("addTime desc");
			Page page = scoreLogManager.findPage(slquery);
			if(page.getTotalCount() > 20 && startTime > 0){
				model.put("isReload", true);
			}else{
				model.put("isReload", false);
			}
			
			model.put("score", score.getScore());
			model.put("useScore", score.getUseScore());
			model.put("leftScore", score.getRemainScore());//可用积分
			model.put("logList", page);
			msg = JTZSConstants.ErrorCode.gg000;
			rlt = RLT_SUCCESS;
		}else{
			msg = JTZSConstants.ErrorCode.gg008;
		}
		
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", msg);
		return "/jtzs/commonData/queryScoreLog";
	}
	
	/** 
	 * 最新问题（html）
	 *  
	 */
	@RequestMapping(value="/newQuestionList")
	public String newQuestionList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long gradeId = paramUtils.getLongParameter(request, "gradeId", 0L);
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start =  paramUtils.getIntParameter(request, "start", 0);
		int split = JTZSConstants.publicSplit;
		QuestionQuery query = new QuestionQuery();
		if(gradeId > 0){
			query.setGradeId(gradeId);
		}
		query.setIsLock(JTZSConstants.QuestionIsLock.UNLOCK.getIndex());
		query.setIsQuit(JTZSConstants.QuestionIsQuit.NORMAL.getIndex());
		query.setIsDel(JTZSConstants.IsDel.UNDELETE.getIndex());
		query.setSortColumns("addTime desc");
		query.setPageSize(range);
		query.setPageNumber(start);
		Page page = questionManager.findPage(query);
		model.put("page", page);
		
		Map<String,Object> jsondata = new HashMap<String,Object>();
		jsondata.put("ajaxUrl","/jtzs/commonData/jsonNewQuestionList?gradeId="+gradeId);
		jsondata.put("range",range);
		jsondata.put("split",--split);
		jsondata.put("start",start+range);
		model.put("type","new");
		model.put("jsondata",jsonUtil.object2Json(jsondata));
		return "/jtzs/newList";
	}
	
	/** 最新问题 */
	@RequestMapping(value="/jsonNewQuestionList")
	public String jsonNewQuestionList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long gradeId = paramUtils.getLongParameter(request, "gradeId", 0L);
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start = paramUtils.getIntParameter(request, "start", 0);
		int split = paramUtils.getIntParameter(request,"split",0);
		
		if(split==0){
			model.put("is_end",true);
		}else{
			String hql = "from Question where isLock=? and isQuit=? and isDel=? ";
			Object[] params = new Object[3];
			if(gradeId > 0){
				hql += " and gradeId=?";
				params = new Object[4];
			}
			hql+= " order by addTime desc";
			params[0] = JTZSConstants.QuestionIsLock.UNLOCK.getIndex();
			params[1] = JTZSConstants.QuestionIsQuit.NORMAL.getIndex();
			params[2] = JTZSConstants.IsDel.UNDELETE.getIndex();
			if(gradeId > 0){
				params[3] = gradeId;
			}
			
			CommonDAOHibernate $d = (CommonDAOHibernate)ApplicationContextHolder.getBean("commonDAOHibernate");
			QueryResult result = $d.findPage(hql.toString(), params, new Pagination(start,range));
			model.put("result",result.getItems());
			model.put("is_end",false);
		}
		model.put("split",--split);
		model.put("start",start+range);
		model.put("range",range);
		model.put("type","new");
		return "/jtzs/jsonPage/jsonNewQuestionList";
	}
	
	/** 问题详情 */
	@RequestMapping(value="/questionShow")
	public String questionShow(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long questionId = paramUtils.getLongParameter(request, "questionId", 0L);
		Long studentId = paramUtils.getLongParameter(request, "studentId", 0L);
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start = paramUtils.getIntParameter(request, "start", 0);
		int split = JTZSConstants.publicSplit;
		Map<String,Object> jsondata = new HashMap<String,Object>();
		Question question = questionManager.getById(questionId);
		if(null != question && JTZSConstants.QuestionStatus.ENDANSWER.getIndex() == question.getStatus()){
			Answer answer = question.getAnswer();
			model.put("answer", answer);
			CommentQuery query = new CommentQuery();
			query.setAnswerId(answer.getId());
			query.setIsDel(JTZSConstants.IsDel.UNDELETE.getIndex());
			query.setSortColumns("addTime desc");
			query.setPageSize(range);
			query.setPageNumber(start);
			Page page = commentManager.findPage(query);
			model.put("commentPage", page);
			
			if(studentId > 0){
				HashMap<String,Object> params = new HashMap<String,Object>();
				params.put("studentId", studentId);
				params.put("questionId", questionId);
				
				List<AnswerShow> list = answerShowManager.findList(params, 0, null);
				if(null==list || list.isEmpty()){
					AnswerShow answerShow = new AnswerShow();
					answerShow.setAddTime(new Date().getTime());
					answerShow.setStudentId(studentId);
					answerShow.setQuestionId(questionId);
					answerShowManager.save(answerShow);
					teacherStatisticsManager.addShow(answer.getTeacherId());
				}
			}
			jsondata.put("ajaxUrl","/jtzs/commonData/jsonQuestionShowCommentList?answerId="+answer.getId());
			--split;
		}
		
		jsondata.put("range",range);
		jsondata.put("split",split);
		jsondata.put("start",start+range);
		model.put("jsondata",jsonUtil.object2Json(jsondata));
		model.put("type","show");
		
		model.put("studentId", studentId);
		model.put("question", question);
		return "/jtzs/questionShow";
	}
	
	/** 问题的评论 */
	@RequestMapping(value="/jsonQuestionShowCommentList")
	public String jsonMyCommentList(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long answerId = paramUtils.getLongParameter(request, "answerId", 0L);
		int range = paramUtils.getIntParameter(request, "range", JTZSConstants.publicRange);//单页数据
		int start =  paramUtils.getIntParameter(request, "start", 0);
		int split = paramUtils.getIntParameter(request,"split",0);
		if(split==0){
			model.put("is_end",true);
		}else{
			String hql = "from Comment where answerId=? and isDel=? order by addTime desc";
			Object[] params = new Object[2];
			params[0] = answerId;
			params[1] = JTZSConstants.IsDel.UNDELETE.getIndex();
			CommonDAOHibernate $d = (CommonDAOHibernate)ApplicationContextHolder.getBean("commonDAOHibernate");
			QueryResult result = $d.findPage(hql.toString(), params, new Pagination(start,range));
			model.put("result",result.getItems());
			model.put("is_end",false);
			--split;
		}
		model.put("split",split);
		model.put("start",start+range);
		model.put("range",range);
		model.put("type","show");
		return "/jtzs/jsonPage/jsonQuestionShowCommentList";
	}
	
	/** 保存事件-答题的关系验证信息  */
	@RequestMapping(value="/saveAnswer")
	public String saveAnswer(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		EventUser eventUser = new EventUser();
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		String ids = paramUtils.getParameter(request, "id" );
		String answer =null;
		long eventId = paramUtils.getLongParameter(request, "eventId", 0L);
		long userId = paramUtils.getLongParameter(request, "userId", 0L);
		if(ids!=null&&ids!=""){
			answer = ids.substring(0, ids.length()-1);
		}
		Event event = eventManager.getById(eventId);
		
		long starttime =0 ;
		long endtime = 0;
		int role = 0;
		long time = System.currentTimeMillis();
		if(event!=null){
			starttime = event.getStartTime();
			endtime = event.getEndTime();
			role = event.getSendTo();
		}
		//保存eventuser表  begin..........
		if(starttime!=0||endtime!=0){
			if (time < starttime || time > endtime) {// 提交时间过期
				code = JTZSConstants.ErrorCode.gg011;
			}else { 
				boolean flag = eventUserManager.isUnique(eventId, userId, role);
				if (!flag) { // 判断唯一性
					code = JTZSConstants.ErrorCode.gg012;
				} else { 
					if(eventUserManager.isExist(userId, role)){
						eventUser.setAddTime(time);
						eventUser.setEventId(eventId);
						eventUser.setUserRole(role);
						eventUser.setUserId(userId);
						eventUserManager.save(eventUser);
					}
					//保存eventuser表  end..........
					Score score =  scoreManager.getScoreByRole(userId, role);
			        if(event!=null){
				        if(answer.equals(event.getAnswer())){ //答对了，加分
				        	
				        	    ScoreLog scorelog =  new ScoreLog();
				        	    scorelog.setUserId(userId);
				        	    scorelog.setUserRole(role);
				        	    scorelog.setScore(event.getAddScore());
				        	    scorelog.setAddTime(time);
				        	    scorelog.setScoreType(JTZSConstants.ScoreType.ADD.getIndex());
				        	    scorelog.setContent("答对了一道题，加"+event.getAddScore()+"分");
				        	    scoreLogManager.save(scorelog);
				        	
				        	
				        	    if(score.getId()!=null){//更新
				        	    	score.setScore(score.getScore()+event.getAddScore());
				        	    	scoreManager.saveOrUpdate(score);
				        	    }else{//新增
					            	
					            	score.setScore(Long.parseLong(event.getAddScore()+""));
					            	scoreManager.saveOrUpdate(score);
				        	    }
						}else{//搭错了
							    ScoreLog scorelog =  new ScoreLog();
				        	    scorelog.setUserId(userId);
				        	    scorelog.setUserRole(role);
				        	    scorelog.setScore(event.getDelScore());
				        	    scorelog.setAddTime(time);
				        	    scorelog.setScoreType(JTZSConstants.ScoreType.DEL.getIndex());
				        	    scorelog.setContent("答错了一道题，加"+event.getDelScore()+"分");
				        	    scoreLogManager.save(scorelog);
							
				        	    if(score.getId()!=null){//更新
				        	    	score.setScore(score.getScore()+event.getDelScore());
				        	    	scoreManager.saveOrUpdate(score);
				        	
				        	    }else{//新增
					        	
				        	    	score.setScore(Long.parseLong(event.getDelScore()+""));
				        	    	scoreManager.saveOrUpdate(score);
				        	    }
						}
				        code = JTZSConstants.ErrorCode.gg000;
				        rlt = RLT_SUCCESS;
			        }
				}
			}
		}
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/commonData/saveAnswer";
	}
	
	/** 反抗问题 */
	@RequestMapping(value="/feedback")
	public String feedback(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String code = JTZSConstants.ErrorCode.gg500;
		int userRule = paramUtils.getIntParameter(request, "userRule", 0);
		Long userId = paramUtils.getLongParameter(request, "userId", 0L);
		String content = paramUtils.getParameter(request, "content", "");
		if(!"".equals(content)){
			if(content.length()<500){
				Feedback feedback = new Feedback();
				feedback.setAddTime(new Date().getTime());
				feedback.setContent(content);
				feedback.setUserId(userId);
				feedback.setUserRole(userRule);
				rlt = RLT_SUCCESS;
				code = JTZSConstants.ErrorCode.gg000;
			}else{
				code = JTZSConstants.ErrorCode.gg015;
			}
		}else{
			code = JTZSConstants.ErrorCode.gg014;
		}
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", JTZSConstants.ErrorCode.getMag(code));
		model.put("code", code);
		return "/jtzs/commonData/feedback";
	}
	
	/** 系统短消息列表 */
	@RequestMapping(value="/querySystemMsg")
	public String querySystemMsg(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String msg = JTZSConstants.ErrorCode.gg500;
		Long userId = paramUtils.getLongParameter(request, "userId", 0L);
		int userRole = paramUtils.getIntParameter(request, "userRole",0);
		Long startTime = paramUtils.getLongParameter(request, "startTime",0);
		Long stopTime = paramUtils.getLongParameter(request, "stopTime",0);
		SystemMessageQuery query = new SystemMessageQuery();
		query.setUserId(userId);
		query.setUserRole(userRole);
		query.setPageSize(20);
		if(startTime >0){
			query.setAddTimeStart(startTime);
		}
		if(stopTime > 0){
			query.setAddTimeStop(stopTime);
		}
		query.setSortColumns("addTime desc");
		Page page = systemMessageManager.findPage(query);
		if(page.getTotalCount() > 20 && startTime > 0){
			model.put("isReload", true);
		}else{
			model.put("isReload", false);
		}
		
		model.put("systemMsgList", page.getResult());
		msg = JTZSConstants.ErrorCode.gg000;
		rlt = RLT_SUCCESS;
		
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", msg);
		return "/jtzs/commonData/querySystemMsg";
	}
	
	
	/** 系统推送 */
	@RequestMapping(value="/queryPush")
	public String queryPush(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rlt = RLT_ERROR;
		String msg = JTZSConstants.ErrorCode.gg500;
		String token = paramUtils.getParameter(request, "token","");
		int role = paramUtils.getIntParameter(request, "role",-1);
		Long userId = paramUtils.getLongParameter(request, "userId",0L);
		if(role >= 0 && userId > 0 && !"".equals(token) ){
			SessionUtil.setToken(request, token, userId.toString());
		}
		msg = JTZSConstants.ErrorCode.gg000;
		rlt = RLT_SUCCESS;
		model.put("data", "");
		model.put("rlt", rlt);
		model.put("msg", msg);
		return "/jtzs/commonData/queryPush";
	}
	
}
