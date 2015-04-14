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
      <th><%=AuthUser.ALIAS_LAST_IP%>：</th>
      <td><c:out value='${member.lastIp}'/></td>
      <th><%=AuthUser.ALIAS_LAST_VISI_TIME%>：</th>
      <td><fmt:formatDate value="${member.lastVisiTimeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <th><%=AuthUser.ALIAS_CODE%>：</th>
      <td><c:choose>
          <c:when test="${member.code == 2}">管理员</c:when>
          <c:when test="${member.code == 5}">系统管理员</c:when>
        </c:choose></td>
      <th><%=AuthUser.ALIAS_EMAIL%>：</th>
      <td><c:out value='${member.email}'/></td>
    </tr>
    <tr>
      <th><%=AuthUser.ALIAS_MOBILE_VALIDATE%>：</th>
      <td><c:choose>
          <c:when test="${member.mobileValidate == 0}">未认证</c:when>
          <c:when test="${member.mobileValidate == 1}">认证</c:when>
        </c:choose></td>
      <th><%=AuthUser.ALIAS_MOBILE%>：</th>
      <td><c:out value='${member.mobile}'/></td>
    </tr>
    <tr>
      <th><%=AuthUser.ALIAS_ANSER%>：</th>
      <td><c:out value='${member.anser}'/></td>
      <th><%=AuthUser.ALIAS_EMAIL_VISIBLE%>：</th>
      <td><c:choose>
          <c:when test="${member.emailVisible == 0}">保密</c:when>
          <c:when test="${member.emailVisible == 1}">可见</c:when>
        </c:choose></td>
    </tr>
    <tr>
      <th><%=AuthUser.ALIAS_ENABLED%>：</th>
      <td><c:choose>
          <c:when test="${member.enabled == 0}">否</c:when>
          <c:when test="${member.enabled == 1}">是</c:when>
        </c:choose></td>
      <th><%=AuthUser.ALIAS_PROBLEM%>：</th>
      <td><c:out value='${member.problem}'/></td>
    </tr>
  </table>
</div>
