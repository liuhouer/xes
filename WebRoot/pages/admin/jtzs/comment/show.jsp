<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Comment.ALIAS_CONTENT%>：</th>
      <td class="frmtd"><c:out value='${comment.content}'/></td>
      
      <th class="frmth"><%=Comment.ALIAS_STUDENT_ID%>：</th>
      <td class="frmtd"><c:out value='${comment.studentId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Comment.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${comment.addTimeString}'/></td>
      
      <th class="frmth"><%=Comment.ALIAS_IS_SATISFIED%>：</th>
      <td class="frmtd"><c:out value='${comment.isSatisfied}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Comment.ALIAS_ANSWER_ID%>：</th>
      <td class="frmtd"><c:out value='${comment.answerId}'/></td>
      
      <th class="frmth"><%=Comment.ALIAS_IS_DEL%>：</th>
      <td class="frmtd"><c:out value='${comment.isDel}'/></td>
      </tr>
  </table>
</div>
