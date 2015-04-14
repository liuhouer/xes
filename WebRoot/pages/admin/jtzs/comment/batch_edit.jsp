<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=Comment.ALIAS_CONTENT%>：</label></th>
      <td class="frmtd">        <input id="content" class=" input_txt" maxlength="500" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Comment.ALIAS_STUDENT_ID%>：</label></th>
      <td class="frmtd">        <input id="studentId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Comment.ALIAS_ADD_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=Comment.FORMAT_ADD_TIME%>'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Comment.ALIAS_IS_SATISFIED%>：</label></th>
      <td class="frmtd">        <input id="isSatisfied" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Comment.ALIAS_ANSWER_ID%>：</label></th>
      <td class="frmtd">        <input id="answerId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Comment.ALIAS_IS_DEL%>：</label></th>
      <td class="frmtd">        <input id="isDel" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
  </table>
</div>
