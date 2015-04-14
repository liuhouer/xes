<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Answer.ALIAS_QUESTION_ID%>：</th>
      <td class="frmtd"><c:out value='${answer.questionId}'/></td>
      
      <th class="frmth"><%=Answer.ALIAS_TEACHER_ID%>：</th>
      <td class="frmtd"><c:out value='${answer.teacherId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Answer.ALIAS_ANSWER%>：</th>
      <td class="frmtd"><c:out value='${answer.answer}'/></td>
      
      <th class="frmth"><%=Answer.ALIAS_IDER%>：</th>
      <td class="frmtd"><c:out value='${answer.ider}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Answer.ALIAS_ANSWER_TIME%>：</th>
      <td class="frmtd"><c:out value='${answer.answerTimeString}'/></td>
      
      <th class="frmth"><%=Answer.ALIAS_STATUS%>：</th>
      <td class="frmtd"><c:out value='${answer.status}'/></td>
      </tr>
  </table>
</div>
