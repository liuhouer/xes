<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=Knowledge.ALIAS_GRADE_ID%>：</label></th>
      <td class="frmtd">        <input id="gradeId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Knowledge.ALIAS_SUBJECT_ID%>：</label></th>
      <td class="frmtd">        <input id="subjectId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Knowledge.ALIAS_KNOWLEDGE1%>：</label></th>
      <td class="frmtd">        <input id="knowledge1" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Knowledge.ALIAS_KNOWLEDGE2%>：</label></th>
      <td class="frmtd">        <input id="knowledge2" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Knowledge.ALIAS_KNOWLEDGE3%>：</label></th>
      <td class="frmtd">        <input id="knowledge3" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Knowledge.ALIAS_ADD_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=Knowledge.FORMAT_ADD_TIME%>'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
  </table>
</div>
