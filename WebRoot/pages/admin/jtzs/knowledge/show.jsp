<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Knowledge.ALIAS_GRADE_ID%>：</th>
      <td class="frmtd"><c:out value='${knowledge.gradeId}'/></td>
      
      <th class="frmth"><%=Knowledge.ALIAS_SUBJECT_ID%>：</th>
      <td class="frmtd"><c:out value='${knowledge.subjectId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Knowledge.ALIAS_KNOWLEDGE1%>：</th>
      <td class="frmtd"><c:out value='${knowledge.knowledge1}'/></td>
      
      <th class="frmth"><%=Knowledge.ALIAS_KNOWLEDGE2%>：</th>
      <td class="frmtd"><c:out value='${knowledge.knowledge2}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Knowledge.ALIAS_KNOWLEDGE3%>：</th>
      <td class="frmtd"><c:out value='${knowledge.knowledge3}'/></td>
      
      <th class="frmth"><%=Knowledge.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${knowledge.addTimeString}'/></td>
      </tr>
  </table>
</div>
