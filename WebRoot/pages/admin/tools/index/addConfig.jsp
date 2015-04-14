<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
  <form:form id="admin_library_libCategory_edit_form" method="post" action="${ctx}/admin/library/libCategory/" modelAttribute="libCategory" >
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label>父分类:</label></th>
        <td class="pb_frmtd"><c:if test="${parent == null}"> 根分类
            <input id="parentId" name="parentId" value="0" type="hidden">
          </c:if>
          <c:if test="${parent != null}"> ${parent.name}
            <input id="parentId" name="parentId" value="${parent.id}" type="hidden">
          </c:if></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=LibCategory.ALIAS_NAME%>:</label></th>
        <td class="pb_frmtd"><form:input path="name" id="name" class="input_txt" maxlength="45" />
          <font color='red'>
          <form:errors path="name"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=LibCategory.ALIAS_SUMMARY%>:</label></th>
        <td class="pb_frmtd"><form:input path="summary" id="summary" class="input_txt" maxlength="255" />
          <font color='red'>
          <form:errors path="summary"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=LibCategory.ALIAS_IMG_PATH%>:</label></th>
        <td class="pb_frmtd"><input type="text" name="imgPath" id="imgPath" class="input_txt" readonly="readonly" value="${libCategory.imgPath }" />
          <input width="155" height="50" type="file" name="file"
									id="file_upload_img" style="display: none;">
          <font color='red'>
          <form:errors path="imgPath"/>
          </font></td>
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
		$("#admin_library_libCategory_edit_form").validate();	
	});
</script> 
