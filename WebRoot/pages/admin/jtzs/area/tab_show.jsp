<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Area.ALIAS_NAME%>：</th>
      <td class="frmtd"><c:out value='${area.name}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Area.ALIAS_SORT%>：</th>
      <td class="frmtd"><c:out value='${area.sort}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Area.ALIAS_EN_NAME%>：</th>
      <td class="frmtd"><c:out value='${area.enName}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Area.ALIAS_STATUS%>：</th>
      <td class="frmtd"><c:out value='${area.status}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Area.ALIAS_CITY_ID%>：</th>
      <td class="frmtd"><c:out value='${area.cityId}'/></td>
      </tr>
  </table>
</div>
