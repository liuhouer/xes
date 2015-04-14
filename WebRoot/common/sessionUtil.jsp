<%@ page language="java" import="com.bruce.util.*" pageEncoding="utf-8"%>
<%@include file="config.jsp" %>

<%!
	private static final void $setSessionMember(HttpServletRequest request,IMember member) throws Exception{
		SessionUtil.setSessionMember(request ,member);
	}
	
	private static final IMember $getSessionMember(HttpServletRequest request) throws Exception{
		return SessionUtil.getSessionMember(request);
	}

	private static final void $setSessionSite(HttpServletRequest request,Site site) throws Exception{
		SessionUtil.setSessionSite(request ,site);
	}

	private static final Site $getSessionSite(HttpServletRequest request) throws Exception{
		return SessionUtil.getSessionSite(request);
	}
	
%>
