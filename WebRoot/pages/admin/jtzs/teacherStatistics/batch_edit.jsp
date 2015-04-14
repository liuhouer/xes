<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=TeacherStatistics.ALIAS_TEACHER_ID%>：</label></th>
      <td class="frmtd">        <input id="teacherId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=TeacherStatistics.ALIAS_TWENTY_MIN_NUM%>：</label></th>
      <td class="frmtd">        <input id="twentyMinNum" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=TeacherStatistics.ALIAS_HALF_HOUR_NUM%>：</label></th>
      <td class="frmtd">        <input id="halfHourNum" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=TeacherStatistics.ALIAS_ONE_HOUR_NUM%>：</label></th>
      <td class="frmtd">        <input id="oneHourNum" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=TeacherStatistics.ALIAS_EXPERT_NUM%>：</label></th>
      <td class="frmtd">        <input id="expertNum" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=TeacherStatistics.ALIAS_QUIT_NUM%>：</label></th>
      <td class="frmtd">        <input id="quitNum" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=TeacherStatistics.ALIAS_ANSWER_NUM%>：</label></th>
      <td class="frmtd">        <input id="answerNum" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=TeacherStatistics.ALIAS_SHOW_NUM%>：</label></th>
      <td class="frmtd">        <input id="showNum" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=TeacherStatistics.ALIAS_SATISFY%>：</label></th>
      <td class="frmtd">        <input id="satisfy" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=TeacherStatistics.ALIAS_UNSATISFY%>：</label></th>
      <td class="frmtd">        <input id="unsatisfy" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
  </table>
</div>
