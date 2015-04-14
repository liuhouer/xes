<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
String file = request.getParameter("file");
%>

<form id="file_zoom_form" name="file_zoom_form">
  <input type="hidden" name="file" value="<%= file%>" />
  <div class="up72_edit">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label>缩放方式:</label></th>
        <td class="pb_frmtd"> 比例
          <input type="radio" checked="checked" id="type1" name="type" value="1" />
          最大值
          <input type="radio" id="type2" name="type" value="2" /></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label>缩放值:</label></th>
        <td class="pb_frmtd"><input type="text" class="input_txt" id="value" name="value" /></td>
      </tr>
      <tr>
        <th class="pb_frmth"><label>是否覆盖原文件:</label></th>
        <td class="pb_frmtd"><input type="checkbox" id="isCover" name="isCover" value="1" /></td>
      </tr>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </div>
</form>
