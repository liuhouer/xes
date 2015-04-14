<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=School.ALIAS_NAME%>：</th>
      <td class="frmtd"><c:out value='${school.name}'/></td>
      
      <th class="frmth"><%=School.ALIAS_AREA_ID%>：</th>
      <td class="frmtd"><c:out value='${school.areaId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=School.ALIAS_SORT%>：</th>
      <td class="frmtd"><c:out value='${school.sort}'/></td>
      
      <th class="frmth"><%=School.ALIAS_EN_NAME%>：</th>
      <td class="frmtd"><c:out value='${school.enName}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=School.ALIAS_STATUS%>：</th>
      <td class="frmtd"><c:out value='${school.status}'/></td>
      </tr>
  </table>
</div>
