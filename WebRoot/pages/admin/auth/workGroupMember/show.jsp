<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=WorkGroupMember.ALIAS_WORK_GROUP_ID%>：</th>
      <td><c:out value='${workGroupMember.workGroupId}'/></td>
    </tr>
    <tr>
      <th><%=WorkGroupMember.ALIAS_MEMBER_ID%>：</th>
      <td><c:out value='${workGroupMember.memberId}'/></td>
    </tr>
  </table>
</div>
