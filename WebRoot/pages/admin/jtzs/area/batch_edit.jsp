<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=Area.ALIAS_NAME%>：</label></th>
      <td class="frmtd">        <input id="name" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Area.ALIAS_SORT%>：</label></th>
      <td class="frmtd">        <input id="sort" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Area.ALIAS_EN_NAME%>：</label></th>
      <td class="frmtd">        <input id="enName" class=" input_txt" maxlength="200" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Area.ALIAS_STATUS%>：</label></th>
      <td class="frmtd">        <input id="status" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Area.ALIAS_CITY_ID%>：</label></th>
      <td class="frmtd">        <input id="cityId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
  </table>
</div>
