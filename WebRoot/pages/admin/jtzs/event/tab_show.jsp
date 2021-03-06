<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_TITLE%>：</th>
      <td class="frmtd"><c:out value='${event.title}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_SEND_TO%>：</th>
      <td class="frmtd"><c:out value='${event.sendTo}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_SEND_TIME%>：</th>
      <td class="frmtd"><fmt:formatDate value="${event.sendTimeDate}"/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_SEND_STATUS%>：</th>
      <td class="frmtd"><c:out value='${event.sendStatus}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_SEND_USER%>：</th>
      <td class="frmtd"><c:out value='${event.sendUser}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_IMG_PATH%>：</th>
      <td class="frmtd"><c:out value='${event.imgPath}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_SUMMARY%>：</th>
      <td class="frmtd"><c:out value='${event.summary}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_CONTENT%>：</th>
      <td class="frmtd"><c:out value='${event.content}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_QUESTION1%>：</th>
      <td class="frmtd"><c:out value='${event.question1}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_QUESTION2%>：</th>
      <td class="frmtd"><c:out value='${event.question2}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_QUESTION3%>：</th>
      <td class="frmtd"><c:out value='${event.question3}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_QUESTION4%>：</th>
      <td class="frmtd"><c:out value='${event.question4}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_QUESTION5%>：</th>
      <td class="frmtd"><c:out value='${event.question5}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_QUESTION6%>：</th>
      <td class="frmtd"><c:out value='${event.question6}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_QUESTION7%>：</th>
      <td class="frmtd"><c:out value='${event.question7}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_ANSWER%>：</th>
      <td class="frmtd"><c:out value='${event.answer}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_START_TIME%>：</th>
      <td class="frmtd"><fmt:formatDate value="${event.startTimeDate}"/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_END_TIME%>：</th>
      <td class="frmtd"><fmt:formatDate value="${event.endTimeDate}"/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_ADD_SCORE%>：</th>
      <td class="frmtd"><c:out value='${event.addScore}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_DEL_SCORE%>：</th>
      <td class="frmtd"><c:out value='${event.delScore}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Event.ALIAS_PROVINCE_ID%>：</th>
      <td class="frmtd"><c:out value='${event.provinceId}'/></td>
      </tr>
  </table>
</div>
