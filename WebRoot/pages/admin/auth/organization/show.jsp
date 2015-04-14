<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=Organization.ALIAS_PARENT%>：</th>
      <td><c:out value='${organization.parent}'/></td>
      <th><%=Organization.ALIAS_DOMAIN%>：</th>
      <td><c:out value='${organization.domain}'/></td>
    </tr>
    <tr>
      <th><%=Organization.ALIAS_REMARK%>：</th>
      <td><c:out value='${organization.remark}'/></td>
      <th><%=Organization.ALIAS_ENABLED%>：</th>
      <td><c:out value='${organization.enabled}'/></td>
    </tr>
    <tr>
      <th><%=Organization.ALIAS_OLEVEL%>：</th>
      <td><c:out value='${organization.olevel}'/></td>
    </tr>
  </table>
</div>
