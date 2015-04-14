<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=WorkGroup.ALIAS_NAME%>：</th>
      <td><c:out value='${workGroup.name}'/></td>
      <th><%=WorkGroup.ALIAS_ADD_TIME%>：</th>
      <td><c:out value='${workGroup.addTime}'/></td>
    </tr>
    <tr>
      <th><%=WorkGroup.ALIAS_REMARK%>：</th>
      <td><c:out value='${workGroup.remark}'/></td>
      <th><%=WorkGroup.ALIAS_ENABLED%>：</th>
      <td><c:out value='${workGroup.enabled}'/></td>
    </tr>
    <tr>
      <th><%=WorkGroup.ALIAS_ORGANIZATION_ID%>：</th>
      <td><c:out value='${workGroup.organizationId}'/></td>
      <th><%=WorkGroup.ALIAS_DEPT_LEVEL%>：</th>
      <td><c:out value='${workGroup.deptLevel}'/></td>
    </tr>
  </table>
</div>
