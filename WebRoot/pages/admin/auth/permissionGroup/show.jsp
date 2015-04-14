<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=PermissionGroup.ALIAS_NAME%>：</th>
      <td><c:out value='${permissionGroup.name}'/></td>
      <th><%=PermissionGroup.ALIAS_DESCRIPTION%>：</th>
      <td><c:out value='${permissionGroup.description}'/></td>
    </tr>
    <tr>
      <th><%=PermissionGroup.ALIAS_PMS_LEVEL%>：</th>
      <td><c:out value='${permissionGroup.pmsLevel}'/></td>
      <th><%=PermissionGroup.ALIAS_PARENT%>：</th>
      <td><c:out value='${permissionGroup.parent}'/></td>
    </tr>
  </table>
</div>
