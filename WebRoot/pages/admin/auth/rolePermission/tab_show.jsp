<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=RolePermission.ALIAS_ROLE_ID%>：</th>
      <td><c:out value='${rolePermission.roleId}'/></td>
      <th><%=RolePermission.ALIAS_PERMISSION_ID%>：</th>
      <td><c:out value='${rolePermission.permissionId}'/></td>
    </tr>
    <tr>
      <th><%=RolePermission.ALIAS_ORGANIZATION_ID%>：</th>
      <td><c:out value='${rolePermission.organizationId}'/></td>
    </tr>
  </table>
</div>
