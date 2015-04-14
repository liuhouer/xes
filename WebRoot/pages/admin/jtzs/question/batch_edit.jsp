<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_CONTENT%>：</label></th>
      <td class="frmtd">        <input id="content" class=" input_txt" maxlength="500" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_IMG_PATH%>：</label></th>
      <td class="frmtd">        <input id="imgPath" class=" input_txt" maxlength="200" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_GRADE_ID%>：</label></th>
      <td class="frmtd">        <input id="gradeId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_SUBJECT_ID%>：</label></th>
      <td class="frmtd">        <input id="subjectId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_KNOWLEDGE_ID%>：</label></th>
      <td class="frmtd">        <input id="knowledgeId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_ADD_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=Question.FORMAT_ADD_TIME%>'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_STUDENT_ID%>：</label></th>
      <td class="frmtd">        <input id="studentId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_SOURCE_TYPE%>：</label></th>
      <td class="frmtd">        <input id="sourceType" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_STATUS%>：</label></th>
      <td class="frmtd">        <input id="status" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_PLATFORM%>：</label></th>
      <td class="frmtd">        <input id="platform" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_REPORT_ID%>：</label></th>
      <td class="frmtd">        <input id="reportId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_REPORT_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=Question.FORMAT_REPORT_TIME%>'})" id="reportTimeString" name="reportTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_AUDIT_STATE%>：</label></th>
      <td class="frmtd">        <input id="auditState" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_REPORT_RESULT%>：</label></th>
      <td class="frmtd">        <input id="reportResult" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_REPORT_CONTENT%>：</label></th>
      <td class="frmtd">        <input id="reportContent" class=" input_txt" maxlength="200" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_IS_DEL%>：</label></th>
      <td class="frmtd">        <input id="isDel" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_IS_QUIT%>：</label></th>
      <td class="frmtd">        <input id="isQuit" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Question.ALIAS_IS_LOCK%>：</label></th>
      <td class="frmtd">        <input id="isLock" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
  </table>
</div>
