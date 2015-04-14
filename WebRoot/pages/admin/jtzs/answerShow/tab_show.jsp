<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=AnswerShow.ALIAS_QUESTION_ID%>：</th>
      <td class="frmtd"><c:out value='${answerShow.questionId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=AnswerShow.ALIAS_ANSWER_ID%>：</th>
      <td class="frmtd"><c:out value='${answerShow.answerId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=AnswerShow.ALIAS_STUDENT_ID%>：</th>
      <td class="frmtd"><c:out value='${answerShow.studentId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=AnswerShow.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><fmt:formatDate value="${answerShow.addTimeDate}"/></td>
      </tr>
  </table>
</div>
