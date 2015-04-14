<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=Permission.ALIAS_NAME%>：</th>
      <td><c:out value='${permission.name}'/></td>
      <th><%=Permission.ALIAS_PERMISSION_GROUP_ID%>：</th>
      <td><c:out value='${permission.permissionGroup.name}'/></td>
    </tr>
    <tr>
      <th><%=Permission.ALIAS_TYPE%>：</th>
      <td><c:choose>
          <c:when test="${permission.type == 0}">菜单</c:when>
          <c:when test="${permission.type == 1}">操作</c:when>
          <c:when test="${permission.type == 2}">选项卡</c:when>
        </c:choose></td>
      <th><%=Permission.ALIAS_DESCRIPTION%>：</th>
      <td><c:out value='${permission.description}'/></td>
    </tr>
    <tr>
      <th><%=Permission.ALIAS_URL%>：</th>
      <td><c:out value='${permission.url}'/></td>
      <th><%=Permission.ALIAS_ENABLED%>：</th>
      <td><c:choose>
          <c:when test="${permission.enabled == 0}">未启用</c:when>
          <c:when test="${permission.enabled == 1}">启用</c:when>
        </c:choose></td>
    </tr>
    <tr>
      <th><%=Permission.ALIAS_ORGANIZATION_ID%>：</th>
      <td><c:out value='${permission.organization.domain}'/></td>
      <th><%=Permission.ALIAS_SORT%>：</th>
      <td><c:out value='${permission.sort}'/></td>
    </tr>
  </table>
</div>
