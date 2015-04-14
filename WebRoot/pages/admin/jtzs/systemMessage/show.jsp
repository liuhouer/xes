<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=SystemMessage.ALIAS_QUESTION_ID%>：</th>
      <td class="frmtd"><c:out value='${systemMessage.questionId}'/></td>
      
      <th class="frmth"><%=SystemMessage.ALIAS_USER_ID%>：</th>
      <td class="frmtd"><c:out value='${systemMessage.userId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=SystemMessage.ALIAS_USER_ROLE%>：</th>
      <td class="frmtd"><c:out value='${systemMessage.userRole}'/></td>
      
      <th class="frmth"><%=SystemMessage.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${systemMessage.addTimeString}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=SystemMessage.ALIAS_IS_READ%>：</th>
      <td class="frmtd"><c:out value='${systemMessage.isRead}'/></td>
      
      <th class="frmth"><%=SystemMessage.ALIAS_ADD_USER%>：</th>
      <td class="frmtd"><c:out value='${systemMessage.addUser}'/></td>
      </tr>
  </table>
</div>
