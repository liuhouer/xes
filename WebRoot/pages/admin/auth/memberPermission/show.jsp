<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=MemberPermission.ALIAS_MEMBER_ID%>：</th>
      <td><c:out value='${memberPermission.memberId}'/></td>
      <th><%=MemberPermission.ALIAS_PERMISSION_ID%>：</th>
      <td><c:out value='${memberPermission.permissionId}'/></td>
    </tr>
    <tr>
      <th><%=MemberPermission.ALIAS_STATE%>：</th>
      <td><c:out value='${memberPermission.state}'/></td>
    </tr>
  </table>
</div>
