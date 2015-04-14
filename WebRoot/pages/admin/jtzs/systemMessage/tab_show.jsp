<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=SystemMessage.ALIAS_QUESTION_ID%>：</th>
      <td class="frmtd"><c:out value='${systemMessage.questionId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=SystemMessage.ALIAS_USER_ID%>：</th>
      <td class="frmtd"><c:out value='${systemMessage.userId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=SystemMessage.ALIAS_USER_ROLE%>：</th>
      <td class="frmtd"><c:out value='${systemMessage.userRole}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=SystemMessage.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><fmt:formatDate value="${systemMessage.addTimeDate}"/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=SystemMessage.ALIAS_IS_READ%>：</th>
      <td class="frmtd"><c:out value='${systemMessage.isRead}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=SystemMessage.ALIAS_ADD_USER%>：</th>
      <td class="frmtd"><c:out value='${systemMessage.addUser}'/></td>
      </tr>
  </table>
</div>
