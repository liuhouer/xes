<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Area.ALIAS_NAME%>：</th>
      <td class="frmtd"><c:out value='${area.name}'/></td>
      
      <th class="frmth"><%=Area.ALIAS_SORT%>：</th>
      <td class="frmtd"><c:out value='${area.sort}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Area.ALIAS_EN_NAME%>：</th>
      <td class="frmtd"><c:out value='${area.enName}'/></td>
      
      <th class="frmth"><%=Area.ALIAS_STATUS%>：</th>
      <td class="frmtd"><c:out value='${area.status}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Area.ALIAS_CITY_ID%>：</th>
      <td class="frmtd"><c:out value='${area.cityId}'/></td>
      </tr>
  </table>
</div>
