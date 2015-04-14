<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=EventUser.ALIAS_EVENT_ID%>：</label></th>
      <td class="frmtd">        <input id="eventId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=EventUser.ALIAS_USER_ID%>：</label></th>
      <td class="frmtd">        <input id="userId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=EventUser.ALIAS_USER_ROLE%>：</label></th>
      <td class="frmtd">        <input id="userRole" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=EventUser.ALIAS_ADD_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=EventUser.FORMAT_ADD_TIME%>'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
  </table>
</div>
