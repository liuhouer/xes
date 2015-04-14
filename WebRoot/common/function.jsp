<%@page import="com.up72.dao.hibernate.CommonDAOHibernate"%>
<%@page import="com.up72.framework.util.holder.ApplicationContextHolder"%>
<%@page import="com.up72.ucenter.model.Member"%>
<%@page import="com.bruce.common.CommonConstants"%>
<%@page import="com.up72.ucenter.service.MemberManager"%>
<%@ page language="java"
	import="java.util.*,com.up72.base.*,com.up72.util.*,com.up72.ucenter.dao.MemberDao"
	pageEncoding="utf-8"%>
<%@page import="com.bruce.common.CommonUtils"%>
<%@page import="com.up72.ucenter.UCenterUtils"%>
<%@page import="com.up72.ucenter.UCenterConstants"%>
<%@page import="com.bruce.common.json.JsonUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://www.up72.com" prefix="up72"%>
<%@include file="config.jsp"%>
<%!
JsonUtil jsonUtil = UCenterUtils.jsonUtil;
//操作信息提示页
	private static final void $goMessagePage(HttpServletRequest request,
			HttpServletResponse response, String resultCode,
			String resultMessage, String url) throws Exception {
		request.setAttribute("resultCode", resultCode);
		request.setAttribute("resultMessage", resultMessage);
		if (url == null || "".equals(url.trim())) {
			request.setAttribute("url", request.getHeader("Referer"));
		} else {
			request.setAttribute("url", url);
		}
		$forward("/message.jsp", request, response);
	}

	//判断对象不为null或空
	private static final boolean $isNotEmpty(Object pattern) {
		return CommonUtils.objectUtils.isNotEmpty(pattern);
	}

	private static final Long[] $getLongParams(ServletRequest request,
			String name) {
		String[] params = request.getParameterValues(name);
		if (params == null) {
			return null;
		}
		int j = params.length;
		Long[] result = new Long[j];
		for (int i = 0; i < j; i++) {
			result[i] = Long.valueOf(params[i]);
		}
		return result;
	}
	
	private static final Long $getLongParam(ServletRequest request,
			String name, Long defval) {
		String param = request.getParameter(name);
		Long value = defval;
		if (param != null) {
			try {
				value = Long.parseLong(param);
			} catch (NumberFormatException ignore) {
			}
		}
		return value;
	}

	private static final void $forward(String url, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.getRequestDispatcher(url).forward(request, response);
	}

	private static final void $redirect(String url, HttpServletResponse response)
			throws Exception {
		response.sendRedirect(url);
	}

	private static final void $referer(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}
	
	private static final void $redirect(String url,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.sendRedirect(request.getContextPath()+url);
	}

	private static final String $getParam(ServletRequest request, String name,
			String defval) {
		String param = request.getParameter(name);
		return (param != null ? param.trim() : defval);
	}

	private static final int $getParam(ServletRequest request, String name,
			int defval) {
		String param = request.getParameter(name);
		int value = defval;
		if (param != null) {
			try {
				value = Integer.parseInt(param);
			} catch (NumberFormatException ignore) {
			}
		}
		return value;
	}

	private static final Long[] $getParams(ServletRequest request, String name) {
		String[] params = request.getParameterValues(name);
		if (params == null) {
			return null;
		}
		int j = params.length;
		Long[] result = new Long[j];
		for (int i = 0; i < j; i++) {
			result[i] = Long.valueOf(params[i]);
		}
		return result;
	}

	private static final void $jsMessage(JspWriter out, String message,
			String Url) throws Exception {
		out.println("<script>alert('" + message + "')</script>");
		out.println("<script>location.href='" + Url + "';</script>");
	}

	//获取前台登录用户
	private static final Member $getSessionMember(HttpServletRequest request)
			throws Exception {
		boolean isLogin = UCenterUtils.memberUtils.isLogin(request);
		Member currentUser = null;
		if (isLogin) {
			currentUser =  UCenterUtils.memberUtils.getSessionUser(request);
		}
		if (null == currentUser) {
			String userName =  UCenterUtils.memberUtils.getLoginName(request);
			if (userName != null && !("".equals(userName))) {
				MemberDao memberDao = (MemberDao) ApplicationContextHolder
						.getBean("memberDao");
				currentUser = memberDao.findByProperty("userName", userName);
			}
		}
		if (currentUser != null) {
			return (Member) currentUser;
		}
		return currentUser;
		//Member loginMember = (Member) request.getSession().getAttribute(
		//		CommonConstants.SESSION_MEMBER_KEY);
		//return loginMember;
	}

	/**
	 * Cookie获取前台登录用户名
	 **/
	private static final Member $getCookieName(HttpServletRequest request)
			throws Exception {
		String loginName = UCenterUtils.memberUtils.getLoginName(request);
		//.getString(request, "COOKIE_MEMBER_KEY");
		Member loginMember = null;
		if (loginName != null && !("".equals(loginName))) {
			MemberDao memberDao = (MemberDao) ApplicationContextHolder
					.getBean("memberDao");
			loginMember = memberDao.findByProperty("userName", loginName);
		}
		return loginMember;
	}

	/**
	 * Cookie设置前台登录用户名
	 **/
	private static final void $setCookieName(HttpServletResponse response,
			String name) throws Exception {
		UCenterUtils.cookieUtil.setCookie(response, "COOKIE_MEMBER_KEY", name, 60 * 60 * 24);
	}
	
	
	private static Member $getCurrentMember(HttpServletRequest request)throws Exception{
		Long currentMemberId = $getLongParam(request,"memberId",0L);
		MemberManager memberManager = (MemberManager) ApplicationContextHolder.getBean("memberManager");
		Member currentMember = memberManager.getById(currentMemberId);
		if(currentMember == null){
		    //如没有预览用户编号传入，则默认为登陆用户
			currentMember = $getCookieName(request);
		}
		request.setAttribute("currentMember", currentMember);
		return currentMember;
	}%>
<%
	//toolsUtil.jsp end
%>
<%
	//mvcUtil.jsp begin
%>
<%
	/** mvc方法 **/

	final String $MD_E = "edit";//3
	final String $MD_F = "form";//2
	final String $MD_D = "del";//4
	final String $MD_P = "page";//1
	final String $MD_V = "view";//5
	final String $MD_CF = "createFile";//6 createFile创建文件
	final String $MD_P_JSON = "page.json";//7 
	final String $MD_V_JSON = "view.json";//8 

	pageContext.setAttribute("MD_E", $MD_E);
	pageContext.setAttribute("MD_F", $MD_F);
	pageContext.setAttribute("MD_D", $MD_D);
	pageContext.setAttribute("MD_P", $MD_P);
	pageContext.setAttribute("MD_V", $MD_V);
	pageContext.setAttribute("MD_CF", $MD_CF);
	pageContext.setAttribute("MD_P_JSON", $MD_P_JSON);
	pageContext.setAttribute("MD_V_JSON", $MD_V_JSON);
	/** mvc方法 **/
%>
<%!//mvc判断
	private static final Integer $swithMD(HttpServletRequest request) {
		Integer result = 1;
		HashMap<String, Integer> methods = (HashMap<String, Integer>) request
				.getAttribute("$MD_MAP");
		String method = $getParam(request, "method", "page");
		if (methods.containsKey(method)) {
			result = methods.get(method);
		}
		return result;
	}

	private static final String $getMD(HttpServletRequest request) {
		String method = $getParam(request, "method", "index");
		return method;
	}

	private static final boolean $isMD(String md, HttpServletRequest request) {
		if (md.equals($getMD(request))) {
			return true;
		}
		return false;
	}

	private static final boolean $isMDJson(HttpServletRequest request) {
		String method = $getMD(request);
		if (method.endsWith(".json")) {
			return true;
		}
		return false;
	}

	//初始化method方法
	private static final void $initMD(HttpServletRequest request) {
		HashMap<String, Integer> $MD_MAP = new HashMap<String, Integer>();
		$MD_MAP.put("page", 1);
		$MD_MAP.put("form", 2);
		$MD_MAP.put("edit", 3);
		$MD_MAP.put("del", 4);
		$MD_MAP.put("view", 5);
		$MD_MAP.put("createFile", 6);
		$MD_MAP.put("page.json", 7);
		$MD_MAP.put("view.json", 8);
		request.setAttribute("$MD_MAP", $MD_MAP);
	}

	//添加method方法
	private static final void $addMD(HttpServletRequest request,
			String methodKey, Integer methodNum) {
		HashMap<String, Integer> $MD_MAP = null;
		if (!$isNotEmpty(request.getAttribute("$MD_MAP"))) {
			$initMD(request);
			$MD_MAP = (HashMap<String, Integer>) request
					.getAttribute("$MD_MAP");
		}
		$MD_MAP.put(methodKey, methodNum);
	}

	//mvc方法结束%>
<%
	CommonDAOHibernate $d = (CommonDAOHibernate) ApplicationContextHolder
			.getBean("commonDAOHibernate");
%>
<%
	/** init方法 **/
	String $CTX = request.getContextPath();
	pageContext.setAttribute("CTX", $CTX);
	$initMD(request);//mvc初始化
%>
