<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
  <form:form id="admin_tools_index_addDicWord_form" method="post" action="${ctx}/admin/tools/index/addDicWord">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label>中文主词列表:</label></th>
        <td class="pb_frmtd"><textarea name="words" rows="6" id="words" class="input_txt required"></textarea>
          <span class="edit_comment">注：请将每个中文词独占一行</span></td>
      </tr>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_tools_index_addDicWord_form").validate();	
	});
</script> 
