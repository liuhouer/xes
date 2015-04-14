<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=AnswerShow.ALIAS_QUESTION_ID%>：</th>
      <td class="frmtd"><c:out value='${answerShow.questionId}'/></td>
      
      <th class="frmth"><%=AnswerShow.ALIAS_ANSWER_ID%>：</th>
      <td class="frmtd"><c:out value='${answerShow.answerId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=AnswerShow.ALIAS_STUDENT_ID%>：</th>
      <td class="frmtd"><c:out value='${answerShow.studentId}'/></td>
      
      <th class="frmth"><%=AnswerShow.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${answerShow.addTimeString}'/></td>
      </tr>
  </table>
</div>
