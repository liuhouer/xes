<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Score.ALIAS_ROLE%>：</th>
      <td class="frmtd"><c:out value='${score.role}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Score.ALIAS_LOGIN_ID%>：</th>
      <td class="frmtd"><c:out value='${score.loginId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Score.ALIAS_SCORE%>：</th>
      <td class="frmtd"><c:out value='${score.score}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Score.ALIAS_USE_SCORE%>：</th>
      <td class="frmtd"><c:out value='${score.useScore}'/></td>
      </tr>
  </table>
</div>
