<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=AuthUser.ALIAS_USER_NAME%>：</th>
      <td><c:out value='${member.userName}'/></td>
      <th><%=AuthUser.ALIAS_PASSWORD%>：</th>
      <td><c:out value='${member.password}'/></td>
    </tr>
    <tr>
      <th><%=AuthUser.ALIAS_NICK_NAME%>：</th>
      <td><c:out value='${member.nickName}'/></td>
      <th><%=AuthUser.ALIAS_IMG_PATH%>：</th>
      <td><c:out value='${member.imgPath}'/></td>
    </tr>
    <tr>
      <th><%=AuthUser.ALIAS_LOGIN_ANSWER%>：</th>
      <td><c:out value='${member.loginAnswer}'/></td>
      <th><%=AuthUser.ALIAS_SECQUES%>：</th>
      <td><c:out value='${member.secques}'/></td>
    </tr>
    <tr>
      <th><%=AuthUser.ALIAS_EMAIL%>：</th>
      <td><c:out value='${member.email}'/></td>
      <th><%=AuthUser.ALIAS_CODE%>：</th>
      <td><c:out value='${member.code}'/></td>
    </tr>
    <tr>
      <th><%=AuthUser.ALIAS_LAST_IP%>：</th>
      <td><c:out value='${member.lastIp}'/></td>
      <th><%=AuthUser.ALIAS_LAST_VISI_TIME%>：</th>
      <td><c:out value='${member.lastVisiTime}'/></td>
    </tr>
    <tr>
      <th><%=AuthUser.ALIAS_MOBILE_VALIDATE%>：</th>
      <td><c:out value='${member.mobileValidate}'/></td>
      <th><%=AuthUser.ALIAS_MOBILE%>：</th>
      <td><c:out value='${member.mobile}'/></td>
    </tr>
    <tr>
      <th><%=AuthUser.ALIAS_EMAIL_VISIBLE%>：</th>
      <td><c:out value='${member.emailVisible}'/></td>
      <th><%=AuthUser.ALIAS_ENABLED%>：</th>
      <td><c:out value='${member.enabled}'/></td>
    </tr>
    <tr>
      <th><%=AuthUser.ALIAS_ANSER%>：</th>
      <td><c:out value='${member.anser}'/></td>
      <th><%=AuthUser.ALIAS_PROBLEM%>：</th>
      <td><c:out value='${member.problem}'/></td>
    </tr>
  </table>
</div>
