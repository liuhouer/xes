<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=Role.ALIAS_WORK_GROUP_ID%>：</th>
      <td><c:out value='${role.workGroupId}'/></td>
      <th><%=Role.ALIAS_DESCRIPTION%>：</th>
      <td><c:out value='${role.description}'/></td>
    </tr>
    <tr>
      <th><%=Role.ALIAS_REMARK%>：</th>
      <td><c:out value='${role.remark}'/></td>
      <th><%=Role.ALIAS_ENABLED%>：</th>
      <td><c:out value='${role.enabled}'/></td>
    </tr>
    <tr>
      <th><%=Role.ALIAS_ORGANIZATION_ID%>：</th>
      <td><c:out value='${role.organizationId}'/></td>
    </tr>
  </table>
</div>
