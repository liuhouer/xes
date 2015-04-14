<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=WrongRules.ALIAS_ROLE%>：</label></th>
      <td class="frmtd">        <input id="role" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=WrongRules.ALIAS_WRONG_NUM%>：</label></th>
      <td class="frmtd">        <input id="wrongNum" class="digits  input_txt" maxlength="5" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=WrongRules.ALIAS_CONTENT%>：</label></th>
      <td class="frmtd">        <input id="content" class=" input_txt" maxlength="200" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=WrongRules.ALIAS_IS_DEL_QUESTION%>：</label></th>
      <td class="frmtd">        <input id="isDelQuestion" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=WrongRules.ALIAS_DEL_SCORE%>：</label></th>
      <td class="frmtd">        <input id="delScore" class="digits  input_txt" maxlength="10" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=WrongRules.ALIAS_IS_STOP_LOGIN%>：</label></th>
      <td class="frmtd">        <input id="isStopLogin" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=WrongRules.ALIAS_ADD_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=WrongRules.FORMAT_ADD_TIME%>'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
  </table>
</div>
