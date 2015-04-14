<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=GradeSubject.ALIAS_SUBJECT_ID%>：</label></th>
      <td class="frmtd">        <input id="subjectId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=GradeSubject.ALIAS_GRADE_ID%>：</label></th>
      <td class="frmtd">        <input id="gradeId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=GradeSubject.ALIAS_STATUS%>：</label></th>
      <td class="frmtd">        <input id="status" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
  </table>
</div>
