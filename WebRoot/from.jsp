<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.bruce.util.TokenUtil"%>
<%@page import="com.bruce.common.CommonConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>

  <title>注册页面1</title>
 		<meta http-equiv="Expires" content="0">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-store">
        <meta http-equiv="Pragma" content="no-cache">
        
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <script type="text/javascript" src="${ctx}/scripts/jquery-1.7.2.js"></script>
  <script type="text/javascript">
  	function submitForm(dom){
  		// alert($(dom).removeAttr("onclick"));
  		$("#form1").submit();
  	}
  </script>
 </head>
 <%
  TokenUtil.getInstance().createToken(request);
  String s = (String)session.getAttribute(CommonConstants.FORM_SUBMIT_TOKEN); 
 %>
 <body>
  <form id="form1" name="form1" method="post" action="register.jsp">
   <table align="center">
   <tr>
    <td colspan="2">
		<%=TokenUtil.getInstance().writeHidden(request, response) %>
    </td>
   </tr>
   	<tr>
   		<td align="right">
   			Token:
   		</td>
   		<td>
   			<%=s %>
   		</td>
   	</tr>
    <tr>
     <td align="right">
      用户名：
     </td>
     <td>
      <input type="text" name="t1" />
     </td>
    </tr>

    <tr>
     <td align="right">
      密码：
     </td>
     <td>
      <input type="password" name="t2" />
     </td>
    </tr>

    <tr>
     <td align="right">
      确认密码：
     </td>
     <td>
      <input type="password" name="t3" />
     </td>
    </tr>

    <tr>
     <td align="right">
      性别：
     </td>
     <td>
      <input type="radio" name="radio" id="radio" value="boy" />
      男
      <input type="radio" name="radio" id="radio2" value="gril" />
      女
     </td>
    </tr>

    <tr>
     <td align="right">
      个人说明：
     </td>
     <td>
      <textarea name="textraea1" rows="15" cols="60"></textarea>
     </td>
    </tr>

    <tr>
     <td align="right">
      <input onclick="submitForm(this);" type="button" name="button" id="button" value="提交" />
     </td>
     <td>
      <input type="reset" name="button2" id="button2" value="重置" />
     </td>
    </tr>
   </table>
  </form>
  
 </body>
</html>