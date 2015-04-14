<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Grade.ALIAS_NAME%>：</th>
      <td class="frmtd"><c:out value='${grade.name}'/></td>
      
      <th class="frmth"><%=Grade.ALIAS_SORT%>：</th>
      <td class="frmtd"><c:out value='${grade.sort}'/></td>
      </tr>
  </table>
</div>
