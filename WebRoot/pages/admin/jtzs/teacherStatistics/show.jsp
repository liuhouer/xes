<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=TeacherStatistics.ALIAS_TEACHER_ID%>：</th>
      <td class="frmtd"><c:out value='${teacherStatistics.teacherId}'/></td>
      
      <th class="frmth"><%=TeacherStatistics.ALIAS_TWENTY_MIN_NUM%>：</th>
      <td class="frmtd"><c:out value='${teacherStatistics.twentyMinNum}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=TeacherStatistics.ALIAS_HALF_HOUR_NUM%>：</th>
      <td class="frmtd"><c:out value='${teacherStatistics.halfHourNum}'/></td>
      
      <th class="frmth"><%=TeacherStatistics.ALIAS_ONE_HOUR_NUM%>：</th>
      <td class="frmtd"><c:out value='${teacherStatistics.oneHourNum}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=TeacherStatistics.ALIAS_EXPERT_NUM%>：</th>
      <td class="frmtd"><c:out value='${teacherStatistics.expertNum}'/></td>
      
      <th class="frmth"><%=TeacherStatistics.ALIAS_QUIT_NUM%>：</th>
      <td class="frmtd"><c:out value='${teacherStatistics.quitNum}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=TeacherStatistics.ALIAS_ANSWER_NUM%>：</th>
      <td class="frmtd"><c:out value='${teacherStatistics.answerNum}'/></td>
      
      <th class="frmth"><%=TeacherStatistics.ALIAS_SHOW_NUM%>：</th>
      <td class="frmtd"><c:out value='${teacherStatistics.showNum}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=TeacherStatistics.ALIAS_SATISFY%>：</th>
      <td class="frmtd"><c:out value='${teacherStatistics.satisfy}'/></td>
      
      <th class="frmth"><%=TeacherStatistics.ALIAS_UNSATISFY%>：</th>
      <td class="frmtd"><c:out value='${teacherStatistics.unsatisfy}'/></td>
      </tr>
  </table>
</div>
