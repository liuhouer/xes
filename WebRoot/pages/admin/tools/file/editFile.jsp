<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
function ajaxSubmit() {
	$.post("${ctx}/admin/tools/file/doUpdateFile", {
		"filePath" : $("#filePath").val(),
		"source" : $("#source").val(),
		"charset" :$("#charsetid option:selected").val()
	}, function(data) {
		if (data.success) {
			alert("保存成功！");
		} else {
			alert("保存失败！");
		}
	}, "json");
}
</script>

<div class="up72_edit">
  <form id="jvForm" onsubmit="ajaxSubmit();return false;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <tr class="pb_frmtr">
        <th class="pb_frmth">文件路径：</th>
        <td class="pb_frmtd"><input disabled="disabled" class="input_txt" id="filePath" name="filePath" value="${filePath}"/></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"> 字符编码：</th>
        <td class="pb_frmtd"><select id="charsetid">
            <c:choose>
              <c:when test="${charset=='GBK'}">
                <option selected="selected" value="GBK">GBK</option>
                <option value="UTF-8">UTF-8</option>
              </c:when>
              <c:otherwise>
                <option value="GBK">GBK</option>
                <option selected="selected" value="UTF-8">UTF-8</option>
              </c:otherwise>
            </c:choose>
          </select></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"> 内容：</th>
        <td class="pb_frmtd"><textarea id="source" name="source" class="input_txt" style="width:99%; height:340px;" wrap="off" maxlength="1232896" onkeydown="if((event.keyCode==115||event.keyCode==83)&&event.ctrlKey){ajaxSubmit();return false;}">
					${source}
					</textarea>
          <span class="edit_comment">注：使用ctrl+s保存编辑内容</span></td>
      </tr>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form>
</div>
