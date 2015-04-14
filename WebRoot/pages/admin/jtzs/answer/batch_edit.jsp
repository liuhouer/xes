<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=Answer.ALIAS_QUESTION_ID%>：</label></th>
      <td class="frmtd">        <input id="questionId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Answer.ALIAS_TEACHER_ID%>：</label></th>
      <td class="frmtd">        <input id="teacherId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Answer.ALIAS_ANSWER%>：</label></th>
      <td class="frmtd">        <input id="answer" class=" input_txt" maxlength="500" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Answer.ALIAS_IDER%>：</label></th>
      <td class="frmtd">        <input id="ider" class=" input_txt" maxlength="500" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Answer.ALIAS_ANSWER_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=Answer.FORMAT_ANSWER_TIME%>'})" id="answerTimeString" name="answerTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Answer.ALIAS_STATUS%>：</label></th>
      <td class="frmtd">        <input id="status" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
  </table>
</div>
