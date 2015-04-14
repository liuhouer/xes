<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=City.ALIAS_NAME%>：</th>
      <td class="frmtd"><c:out value='${city.name}'/></td>
      
      <th class="frmth"><%=City.ALIAS_SORT%>：</th>
      <td class="frmtd"><c:out value='${city.sort}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=City.ALIAS_EN_NAME%>：</th>
      <td class="frmtd"><c:out value='${city.enName}'/></td>
      
      <th class="frmth"><%=City.ALIAS_PROVINCE_ID%>：</th>
      <td class="frmtd"><c:out value='${city.provinceId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=City.ALIAS_STATUS%>：</th>
      <td class="frmtd"><c:out value='${city.status}'/></td>
      </tr>
  </table>
</div>
