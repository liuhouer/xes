<%@page import="com.up72.sys.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=DataChangLog.ALIAS_CHANG_TABLE%>：</th>
      <td><c:out value='${dataChangLog.changTable}'/></td>
      <th><%=DataChangLog.ALIAS_CHANG_ID%>：</th>
      <td><c:out value='${dataChangLog.changId}'/></td>
    </tr>
    <tr>
      <th><%=DataChangLog.ALIAS_CHANG_TIME%>：</th>
      <td><c:out value='${dataChangLog.changTimeString}'/></td>
      <th><%=DataChangLog.ALIAS_CHANG_TYPE%>：</th>
      <td><c:out value='${dataChangLog.changType}'/></td>
    </tr>
  </table>
</div>
