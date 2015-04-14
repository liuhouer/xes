<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Question.ALIAS_CONTENT%>：</th>
      <td class="frmtd"><c:out value='${question.content}'/></td>
      
      <th class="frmth"><%=Question.ALIAS_IMG_PATH%>：</th>
      <td class="frmtd"><c:out value='${question.imgPath}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Question.ALIAS_GRADE_ID%>：</th>
      <td class="frmtd"><c:out value='${question.gradeId}'/></td>
      
      <th class="frmth"><%=Question.ALIAS_SUBJECT_ID%>：</th>
      <td class="frmtd"><c:out value='${question.subjectId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Question.ALIAS_KNOWLEDGE_ID%>：</th>
      <td class="frmtd"><c:out value='${question.knowledgeId}'/></td>
      
      <th class="frmth"><%=Question.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${question.addTimeString}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Question.ALIAS_STUDENT_ID%>：</th>
      <td class="frmtd"><c:out value='${question.studentId}'/></td>
      
      <th class="frmth"><%=Question.ALIAS_SOURCE_TYPE%>：</th>
      <td class="frmtd"><c:out value='${question.sourceType}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Question.ALIAS_STATUS%>：</th>
      <td class="frmtd"><c:out value='${question.status}'/></td>
      
      <th class="frmth"><%=Question.ALIAS_PLATFORM%>：</th>
      <td class="frmtd"><c:out value='${question.platform}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Question.ALIAS_REPORT_ID%>：</th>
      <td class="frmtd"><c:out value='${question.reportId}'/></td>
      
      <th class="frmth"><%=Question.ALIAS_REPORT_TIME%>：</th>
      <td class="frmtd"><c:out value='${question.reportTimeString}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Question.ALIAS_AUDIT_STATE%>：</th>
      <td class="frmtd"><c:out value='${question.auditState}'/></td>
      
      <th class="frmth"><%=Question.ALIAS_REPORT_RESULT%>：</th>
      <td class="frmtd"><c:out value='${question.reportResult}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Question.ALIAS_REPORT_CONTENT%>：</th>
      <td class="frmtd"><c:out value='${question.reportContent}'/></td>
      
      <th class="frmth"><%=Question.ALIAS_IS_DEL%>：</th>
      <td class="frmtd"><c:out value='${question.isDel}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Question.ALIAS_IS_QUIT%>：</th>
      <td class="frmtd"><c:out value='${question.isQuit}'/></td>
      
      <th class="frmth"><%=Question.ALIAS_IS_LOCK%>：</th>
      <td class="frmtd"><c:out value='${question.isLock}'/></td>
      </tr>
  </table>
</div>
