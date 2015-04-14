<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=CommonRule.ALIAS_TITLE%>：</th>
      <td class="frmtd"><c:out value='${commonRule.title}'/></td>
      
      <th class="frmth"><%=CommonRule.ALIAS_NUM%>：</th>
      <td class="frmtd"><c:out value='${commonRule.num}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=CommonRule.ALIAS_SCORE%>：</th>
      <td class="frmtd"><c:out value='${commonRule.score}'/></td>
      
      <th class="frmth"><%=CommonRule.ALIAS_IS_STOP_LOGIN%>：</th>
      <td class="frmtd"><c:out value='${commonRule.isStopLogin}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=CommonRule.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${commonRule.addTimeString}'/></td>
      
      <th class="frmth"><%=CommonRule.ALIAS_START_TIME%>：</th>
      <td class="frmtd"><c:out value='${commonRule.startTimeString}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=CommonRule.ALIAS_STOP_TIME%>：</th>
      <td class="frmtd"><c:out value='${commonRule.stopTimeString}'/></td>
      
      <th class="frmth"><%=CommonRule.ALIAS_BEGIN_TIME%>：</th>
      <td class="frmtd"><c:out value='${commonRule.beginTimeString}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=CommonRule.ALIAS_END_TIME%>：</th>
      <td class="frmtd"><c:out value='${commonRule.endTimeString}'/></td>
      
      <th class="frmth"><%=CommonRule.ALIAS_RULE_TYPE%>：</th>
      <td class="frmtd"><c:out value='${commonRule.ruleType}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=CommonRule.ALIAS_SCORE_TYPE%>：</th>
      <td class="frmtd"><c:out value='${commonRule.scoreType}'/></td>
      </tr>
  </table>
</div>
