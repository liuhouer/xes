<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Version.ALIAS_TYPE%>：</th>
      <td class="frmtd"><c:out value='${version.type}'/></td>
      
      <th class="frmth"><%=Version.ALIAS_VERSION%>：</th>
      <td class="frmtd"><c:out value='${version.version}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Version.ALIAS_SIZE%>：</th>
      <td class="frmtd"><c:out value='${version.size}'/></td>
      
      <th class="frmth"><%=Version.ALIAS_APP_URL%>：</th>
      <td class="frmtd"><c:out value='${version.appUrl}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Version.ALIAS_UPDATE_INFO%>：</th>
      <td class="frmtd"><c:out value='${version.updateInfo}'/></td>
      
      <th class="frmth"><%=Version.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${version.addTimeString}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Version.ALIAS_UPDATE_TIME%>：</th>
      <td class="frmtd"><c:out value='${version.updateTimeString}'/></td>
      </tr>
  </table>
</div>
