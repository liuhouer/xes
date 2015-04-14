<%@page import="com.up72.sys.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<h5><%=LogBusiness.TABLE_ALIAS%>信息</h5>
<div class="up72_view skin_view">
  <table width="100%" cellspacing="0" cellpadding="0" border="0" class="up72_view_table">
    <tr>
      <th><%=LogBusiness.ALIAS_USER_GUID%>：</th>
      <td><c:out value='${logBusiness.userGuid}'/></td>
      
      <th><%=LogBusiness.ALIAS_TIME%>：</th>
      <td><c:out value='${logBusiness.timeString}'/></td>
      </tr>
    <tr>
      <th><%=LogBusiness.ALIAS_TYPE%>：</th>
      <td><c:out value='${logBusiness.type}'/></td>
      
      <th><%=LogBusiness.ALIAS_RESULT%>：</th>
      <td><c:out value='${logBusiness.result}'/></td>
      </tr>
    <tr>
      <th><%=LogBusiness.ALIAS_IP%>：</th>
      <td><c:out value='${logBusiness.ip}'/></td>
      
      <th><%=LogBusiness.ALIAS_FUNCTION%>：</th>
      <td><c:out value='${logBusiness.function}'/></td>
      </tr>
    <tr>
      <th><%=LogBusiness.ALIAS_LEVEL%>：</th>
      <td><c:out value='${logBusiness.level}'/></td>
      
      <th><%=LogBusiness.ALIAS_DESCRIPTION%>：</th>
      <td><c:out value='${logBusiness.description}'/></td>
      </tr>
    <tr>
      <th><%=LogBusiness.ALIAS_DELETE_FLAG%>：</th>
      <td><c:out value='${logBusiness.deleteFlag}'/></td>
      </tr>
  </table>
</div>
