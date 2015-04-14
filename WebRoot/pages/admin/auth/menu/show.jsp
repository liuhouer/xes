<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=Menu.ALIAS_PARENT_ID%>：</th>
      <td><c:out value='${menu.parentId}'/></td>
      <th><%=Menu.ALIAS_PERMISSION_ID%>：</th>
      <td><c:out value='${menu.permissionId}'/></td>
    </tr>
    <tr>
      <th><%=Menu.ALIAS_NAME%>：</th>
      <td><c:out value='${menu.name}'/></td>
      <th><%=Menu.ALIAS_ICON%>：</th>
      <td><c:out value='${menu.icon}'/></td>
    </tr>
    <tr>
      <th><%=Menu.ALIAS_SORT_ID%>：</th>
      <td><c:out value='${menu.sortId}'/></td>
      <th><%=Menu.ALIAS_LEVEL%>：</th>
      <td><c:out value='${menu.level}'/></td>
    </tr>
    <tr>
      <th><%=Menu.ALIAS_ROLE_ID%>：</th>
      <td><c:out value='${menu.roleId}'/></td>
      <th><%=Menu.ALIAS_ADD_TIME%>：</th>
      <td><c:out value='${menu.addTimeString}'/></td>
    </tr>
  </table>
</div>
