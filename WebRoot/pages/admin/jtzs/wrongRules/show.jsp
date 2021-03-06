<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=WrongRules.ALIAS_ROLE%>：</th>
      <td class="frmtd"><c:out value='${wrongRules.role}'/></td>
      
      <th class="frmth"><%=WrongRules.ALIAS_WRONG_NUM%>：</th>
      <td class="frmtd"><c:out value='${wrongRules.wrongNum}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=WrongRules.ALIAS_CONTENT%>：</th>
      <td class="frmtd"><c:out value='${wrongRules.content}'/></td>
      
      <th class="frmth"><%=WrongRules.ALIAS_IS_DEL_QUESTION%>：</th>
      <td class="frmtd"><c:out value='${wrongRules.isDelQuestion}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=WrongRules.ALIAS_DEL_SCORE%>：</th>
      <td class="frmtd"><c:out value='${wrongRules.delScore}'/></td>
      
      <th class="frmth"><%=WrongRules.ALIAS_IS_STOP_LOGIN%>：</th>
      <td class="frmtd"><c:out value='${wrongRules.isStopLogin}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=WrongRules.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${wrongRules.addTimeString}'/></td>
      </tr>
  </table>
</div>
