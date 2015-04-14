<%@page import="com.up72.sys.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=SysCategory.ALIAS_GUID%>：</label></th>
      <td class="frmtd">        <input id="guid" class=" input_txt" maxlength="36" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SysCategory.ALIAS_CAT_NAME%>：</label></th>
      <td class="frmtd">        <input id="catName" class=" input_txt" maxlength="48" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SysCategory.ALIAS_PARENT_GUID%>：</label></th>
      <td class="frmtd">        <input id="parentGuid" class=" input_txt" maxlength="36" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SysCategory.ALIAS_CONFIG_SOURCE%>：</label></th>
      <td class="frmtd">        <input id="configSource" class=" input_txt" maxlength="255" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SysCategory.ALIAS_CONFIG_ID%>：</label></th>
      <td class="frmtd">        <input id="configId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SysCategory.ALIAS_CONTENT_MODEL_ID%>：</label></th>
      <td class="frmtd">        <input id="contentModelId" class="required digits  input_txt" maxlength="19" />
        <span class="required">*</span> </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SysCategory.ALIAS_LIST_URL_PATH%>：</label></th>
      <td class="frmtd">        <input id="listUrlPath" class="url  input_txt" maxlength="255" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SysCategory.ALIAS_CONFIG_URL_PATH%>：</label></th>
      <td class="frmtd">        <input id="configUrlPath" class="url digits  input_txt" maxlength="10" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SysCategory.ALIAS_SORT_ID%>：</label></th>
      <td class="frmtd">        <input id="sortId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=SysCategory.ALIAS_ADD_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=SysCategory.FORMAT_ADD_TIME%>'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
  </table>
</div>
