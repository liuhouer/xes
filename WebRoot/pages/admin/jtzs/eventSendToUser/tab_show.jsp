<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=EventSendToUser.ALIAS_EVENT_ID%>：</th>
      <td class="frmtd"><c:out value='${eventSendToUser.eventId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=EventSendToUser.ALIAS_USER_ID%>：</th>
      <td class="frmtd"><c:out value='${eventSendToUser.userId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=EventSendToUser.ALIAS_USER_ROLE%>：</th>
      <td class="frmtd"><c:out value='${eventSendToUser.userRole}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=EventSendToUser.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><fmt:formatDate value="${eventSendToUser.addTimeDate}"/></td>
      </tr>
  </table>
</div>
