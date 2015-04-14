<%@ page language="java" import="java.util.*,com.bruce.util.*,com.bruce.model.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib uri="http://www.up72.com" prefix="up72" %> 
<%@include file="config.jsp" %>

<%!
//判断对象不为null或空
private static final boolean $isNotEmpty(Object pattern){
	return ObjectUtils.isNotEmpty(pattern);
}
private static final void $forward(String url,HttpServletRequest request,HttpServletResponse response) throws Exception{
	request.getRequestDispatcher(url).forward(request,response);
}
private static final void $redirect(String url,HttpServletResponse response) throws Exception{
	response.sendRedirect(url);
}
private static final void $referer(HttpServletRequest request,HttpServletResponse response) throws Exception{
	String referer = request.getHeader("Referer");
	response.sendRedirect(referer);
} 

private static final String $getParam(ServletRequest request, String name,
	    String defval){
	    String param = request.getParameter(name);
	    return (param != null ? param : defval);
	}

	private static final int $getParam(ServletRequest request, String name,
	    int defval){
	    String param = request.getParameter(name);
	    int value = defval;
	    if (param != null) {
		try { value = Integer.parseInt(param); }
		catch (NumberFormatException ignore) { }
	    }
	    return value;
	}

	private static final Long[] $getParams(ServletRequest request, String name){
			String[] params = request.getParameterValues(name); 
			if(params == null){
				return null;
			}
			int j = params.length;
			Long[] result = new Long[j];
		    for(int i = 0 ; i < j ; i++){
		    	result[i] = Long.valueOf(params[i]);
		    }
		    return result;
	}
	
	private static final String $getDateTime(String pattern){
		return DateUtils.getDateTimeStr(pattern);
	}

	private static final void $jsMessage(JspWriter out ,String  message ,String Url) throws Exception{
		out.println("<script>alert('" + message + "')</script>");
		out.println("<script>location.href='" + Url + "';</script>");
	}

%>
