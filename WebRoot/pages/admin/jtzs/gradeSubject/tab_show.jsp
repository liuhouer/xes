<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=GradeSubject.ALIAS_SUBJECT_ID%>：</th>
      <td class="frmtd"><c:out value='${gradeSubject.subjectId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=GradeSubject.ALIAS_GRADE_ID%>：</th>
      <td class="frmtd"><c:out value='${gradeSubject.gradeId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=GradeSubject.ALIAS_STATUS%>：</th>
      <td class="frmtd"><c:out value='${gradeSubject.status}'/></td>
      </tr>
  </table>
</div>
