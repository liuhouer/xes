<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Comment.ALIAS_CONTENT%>：</th>
      <td class="frmtd"><c:out value='${comment.content}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Comment.ALIAS_STUDENT_ID%>：</th>
      <td class="frmtd"><c:out value='${comment.studentId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Comment.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><fmt:formatDate value="${comment.addTimeDate}"/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Comment.ALIAS_IS_SATISFIED%>：</th>
      <td class="frmtd"><c:out value='${comment.isSatisfied}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Comment.ALIAS_ANSWER_ID%>：</th>
      <td class="frmtd"><c:out value='${comment.answerId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Comment.ALIAS_IS_DEL%>：</th>
      <td class="frmtd"><c:out value='${comment.isDel}'/></td>
      </tr>
  </table>
</div>
