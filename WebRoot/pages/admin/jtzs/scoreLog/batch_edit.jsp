<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=ScoreLog.ALIAS_SCORE%>：</label></th>
      <td class="frmtd">        <input id="score" class="digits  input_txt" maxlength="10" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=ScoreLog.ALIAS_SCORE_ID%>：</label></th>
      <td class="frmtd">        <input id="scoreId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=ScoreLog.ALIAS_ADD_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=ScoreLog.FORMAT_ADD_TIME%>'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=ScoreLog.ALIAS_OPERATOR_ID%>：</label></th>
      <td class="frmtd">        <input id="operatorId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=ScoreLog.ALIAS_REMARK%>：</label></th>
      <td class="frmtd">        <input id="remark" class=" input_txt" maxlength="200" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=ScoreLog.ALIAS_CONTENT%>：</label></th>
      <td class="frmtd">        <input id="content" class=" input_txt" maxlength="200" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=ScoreLog.ALIAS_SCORE_TYPE%>：</label></th>
      <td class="frmtd">        <input id="scoreType" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
  </table>
</div>
