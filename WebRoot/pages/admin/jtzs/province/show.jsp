<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Province.ALIAS_NAME%>：</th>
      <td class="frmtd"><c:out value='${province.name}'/></td>
      
      <th class="frmth"><%=Province.ALIAS_SORT%>：</th>
      <td class="frmtd"><c:out value='${province.sort}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Province.ALIAS_EN_NAME%>：</th>
      <td class="frmtd"><c:out value='${province.enName}'/></td>
      
      <th class="frmth"><%=Province.ALIAS_STATUS%>：</th>
      <td class="frmtd"><c:out value='${province.status}'/></td>
      </tr>
  </table>
</div>
