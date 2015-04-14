<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=Student.ALIAS_STATUS%>：</label></th>
      <td class="frmtd">        <input id="status" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Student.ALIAS_PROVINCE_ID%>：</label></th>
      <td class="frmtd">        <input id="provinceId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Student.ALIAS_NICK_NAME%>：</label></th>
      <td class="frmtd">        <input id="nickName" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Student.ALIAS_LOGIN_NAME%>：</label></th>
      <td class="frmtd">        <input id="loginName" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Student.ALIAS_SCHOOL_ID%>：</label></th>
      <td class="frmtd">        <input id="schoolId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Student.ALIAS_GRADE_ID%>：</label></th>
      <td class="frmtd">        <input id="gradeId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Student.ALIAS_SEX%>：</label></th>
      <td class="frmtd">        <input id="sex" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Student.ALIAS_IMG_PATH%>：</label></th>
      <td class="frmtd">        <input id="imgPath" class=" input_txt" maxlength="200" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Student.ALIAS_LAST_LOGIN_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=Student.FORMAT_LAST_LOGIN_TIME%>'})" id="lastLoginTimeString" name="lastLoginTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Student.ALIAS_ADD_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=Student.FORMAT_ADD_TIME%>'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Student.ALIAS_PASSWORD%>：</label></th>
      <td class="frmtd">        <input id="password" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Student.ALIAS_PLATFORM%>：</label></th>
      <td class="frmtd">        <input id="platform" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
  </table>
</div>
