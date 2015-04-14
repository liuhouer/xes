<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Knowledge.ALIAS_GRADE_ID%>：</th>
      <td class="frmtd"><c:out value='${knowledge.gradeId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Knowledge.ALIAS_SUBJECT_ID%>：</th>
      <td class="frmtd"><c:out value='${knowledge.subjectId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Knowledge.ALIAS_KNOWLEDGE1%>：</th>
      <td class="frmtd"><c:out value='${knowledge.knowledge1}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Knowledge.ALIAS_KNOWLEDGE2%>：</th>
      <td class="frmtd"><c:out value='${knowledge.knowledge2}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Knowledge.ALIAS_KNOWLEDGE3%>：</th>
      <td class="frmtd"><c:out value='${knowledge.knowledge3}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Knowledge.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><fmt:formatDate value="${knowledge.addTimeDate}"/></td>
      </tr>
  </table>
</div>
