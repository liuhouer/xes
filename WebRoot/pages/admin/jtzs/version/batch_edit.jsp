<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=Version.ALIAS_TYPE%>：</label></th>
      <td class="frmtd">        <input id="type" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Version.ALIAS_VERSION%>：</label></th>
      <td class="frmtd">        <input id="version" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Version.ALIAS_SIZE%>：</label></th>
      <td class="frmtd">        <input id="size" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Version.ALIAS_APP_URL%>：</label></th>
      <td class="frmtd">        <input id="appUrl" class="url  input_txt" maxlength="200" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Version.ALIAS_UPDATE_INFO%>：</label></th>
      <td class="frmtd">        <input id="updateInfo" class=" input_txt" maxlength="500" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Version.ALIAS_ADD_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=Version.FORMAT_ADD_TIME%>'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Version.ALIAS_UPDATE_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=Version.FORMAT_UPDATE_TIME%>'})" id="updateTimeString" name="updateTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
  </table>
</div>
