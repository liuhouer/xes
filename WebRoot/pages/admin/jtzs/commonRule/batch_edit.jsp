<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=CommonRule.ALIAS_TITLE%>：</label></th>
      <td class="frmtd">        <input id="title" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=CommonRule.ALIAS_NUM%>：</label></th>
      <td class="frmtd">        <input id="num" class="digits  input_txt" maxlength="5" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=CommonRule.ALIAS_SCORE%>：</label></th>
      <td class="frmtd">        <input id="score" class="digits  input_txt" maxlength="10" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=CommonRule.ALIAS_IS_STOP_LOGIN%>：</label></th>
      <td class="frmtd">        <input id="isStopLogin" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=CommonRule.ALIAS_ADD_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=CommonRule.FORMAT_ADD_TIME%>'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=CommonRule.ALIAS_START_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=CommonRule.FORMAT_START_TIME%>'})" id="startTimeString" name="startTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=CommonRule.ALIAS_STOP_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=CommonRule.FORMAT_STOP_TIME%>'})" id="stopTimeString" name="stopTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=CommonRule.ALIAS_BEGIN_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=CommonRule.FORMAT_BEGIN_TIME%>'})" id="beginTimeString" name="beginTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=CommonRule.ALIAS_END_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=CommonRule.FORMAT_END_TIME%>'})" id="endTimeString" name="endTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=CommonRule.ALIAS_RULE_TYPE%>：</label></th>
      <td class="frmtd">        <input id="ruleType" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=CommonRule.ALIAS_SCORE_TYPE%>：</label></th>
      <td class="frmtd">        <input id="scoreType" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
  </table>
</div>
