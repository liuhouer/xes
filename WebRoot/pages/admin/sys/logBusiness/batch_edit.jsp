<%@page import="com.up72.sys.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit skin_edit">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="up72_edit_table">
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=LogBusiness.ALIAS_USER_GUID%>：</label></th>
      <td class="pb_frmtd">        <input  id="userGuid" class=" txt_ipt ipt_over" maxlength="48" />
         </td>
    </tr>
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=LogBusiness.ALIAS_TIME%>：</label></th>
      <td class="pb_frmtd">
        <input  onclick="WdatePicker({dateFmt:'<%=LogBusiness.FORMAT_TIME%>'})" id="timeString" name="timeString"  maxlength="0" class=" txt_ipt ipt_over" />
         </td>
    </tr>
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=LogBusiness.ALIAS_TYPE%>：</label></th>
      <td class="pb_frmtd">        <input  id="type" class=" txt_ipt ipt_over" maxlength="16" />
         </td>
    </tr>
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=LogBusiness.ALIAS_RESULT%>：</label></th>
      <td class="pb_frmtd">        <input  id="result" class=" txt_ipt ipt_over" maxlength="255" />
         </td>
    </tr>
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=LogBusiness.ALIAS_IP%>：</label></th>
      <td class="pb_frmtd">        <input  id="ip" class=" txt_ipt ipt_over" maxlength="16" />
         </td>
    </tr>
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=LogBusiness.ALIAS_FUNCTION%>：</label></th>
      <td class="pb_frmtd">        <input  id="function" class=" txt_ipt ipt_over" maxlength="48" />
         </td>
    </tr>
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=LogBusiness.ALIAS_LEVEL%>：</label></th>
      <td class="pb_frmtd">        <input  id="level" class=" txt_ipt ipt_over" maxlength="0" />
         </td>
    </tr>
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=LogBusiness.ALIAS_DESCRIPTION%>：</label></th>
      <td class="pb_frmtd">        <input  id="description" class=" txt_ipt ipt_over" maxlength="255" />
         </td>
    </tr>
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=LogBusiness.ALIAS_DELETE_FLAG%>：</label></th>
      <td class="pb_frmtd">        <input  id="deleteFlag" class="digits  txt_ipt ipt_over" maxlength="3" />
         </td>
    </tr>
  </table>
</div>
