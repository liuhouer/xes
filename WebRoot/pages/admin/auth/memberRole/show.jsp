<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=MemberRole.ALIAS_MEMBER_ID%>：</th>
      <td><c:out value='${memberRole.memberId}'/></td>
      <th><%=MemberRole.ALIAS_ROLE_ID%>：</th>
      <td><c:out value='${memberRole.roleId}'/></td>
    </tr>
  </table>
</div>
