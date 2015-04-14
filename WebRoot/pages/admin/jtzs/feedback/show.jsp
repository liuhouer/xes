<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Feedback.ALIAS_CONTENT%>：</th>
      <td class="frmtd"><c:out value='${feedback.content}'/></td>
      
      <th class="frmth"><%=Feedback.ALIAS_USER_ID%>：</th>
      <td class="frmtd"><c:out value='${feedback.userId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Feedback.ALIAS_USER_ROLE%>：</th>
      <td class="frmtd"><c:out value='${feedback.userRole}'/></td>
      
      <th class="frmth"><%=Feedback.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${feedback.addTimeString}'/></td>
      </tr>
  </table>
</div>
