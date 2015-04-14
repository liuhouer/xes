<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=ScoreLog.ALIAS_SCORE%>：</th>
      <td class="frmtd"><c:out value='${scoreLog.score}'/></td>
      
      <th class="frmth"><%=ScoreLog.ALIAS_SCORE_ID%>：</th>
      <td class="frmtd"><c:out value='${scoreLog.scoreId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=ScoreLog.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${scoreLog.addTimeString}'/></td>
      
      <th class="frmth"><%=ScoreLog.ALIAS_OPERATOR_ID%>：</th>
      <td class="frmtd"><c:out value='${scoreLog.operatorId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=ScoreLog.ALIAS_REMARK%>：</th>
      <td class="frmtd"><c:out value='${scoreLog.remark}'/></td>
      
      <th class="frmth"><%=ScoreLog.ALIAS_CONTENT%>：</th>
      <td class="frmtd"><c:out value='${scoreLog.content}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=ScoreLog.ALIAS_SCORE_TYPE%>：</th>
      <td class="frmtd"><c:out value='${scoreLog.scoreType}'/></td>
      </tr>
  </table>
</div>
