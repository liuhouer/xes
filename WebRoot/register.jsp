<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.bruce.util.TokenUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>处理注册页面1</title>
 <meta http-equiv="pragma" content="no-cache">
 <meta http-equiv="cache-control" content="no-cache">
 <meta http-equiv="expires" content="0">    
 <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
 <meta http-equiv="description" content="This is my page">
 <!--
 <link rel="stylesheet" type="text/css" href="styles.css">
 -->

  </head>
  
  <body>
   <%
    TokenUtil tokenGen = TokenUtil.getInstance();
    if (!tokenGen.isTokenValid(request)){
    	out.print("这是重复提交或非法提交!");
    }else{
     	// 处理请求，并执行resetToken方法，将session中的token去除
     	tokenGen.destroyToken(request);
    }
    %>
  </body>
</html>