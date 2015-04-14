<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Feedback.ALIAS_CONTENT%>：</th>
      <td class="frmtd"><c:out value='${feedback.content}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Feedback.ALIAS_USER_ID%>：</th>
      <td class="frmtd"><c:out value='${feedback.userId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Feedback.ALIAS_USER_ROLE%>：</th>
      <td class="frmtd"><c:out value='${feedback.userRole}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Feedback.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><fmt:formatDate value="${feedback.addTimeDate}"/></td>
      </tr>
  </table>
</div>
