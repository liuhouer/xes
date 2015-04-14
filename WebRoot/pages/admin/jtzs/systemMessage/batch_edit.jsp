<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=SystemMessage.ALIAS_QUESTION_ID%>：</label></th>
      <td class="frmtd">        <input id="questionId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SystemMessage.ALIAS_USER_ID%>：</label></th>
      <td class="frmtd">        <input id="userId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SystemMessage.ALIAS_USER_ROLE%>：</label></th>
      <td class="frmtd">        <input id="userRole" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SystemMessage.ALIAS_ADD_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=SystemMessage.FORMAT_ADD_TIME%>'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SystemMessage.ALIAS_IS_READ%>：</label></th>
      <td class="frmtd">        <input id="isRead" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SystemMessage.ALIAS_ADD_USER%>：</label></th>
      <td class="frmtd">        <input id="addUser" class=" input_txt" maxlength="50" />
         </td>
    </tr>
  </table>
</div>
