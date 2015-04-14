<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
function editProjectName(){
	if($("#projectName").val()!=''){
		$.ajax({
			type : "post",
			url : "${ctx}/admin/sys/sysConfig/saveProjectName",
			data : $("#iscs_change_project_name_form").serialize(),
			dataType : "json",
			success : function(msg){
				if(msg.status == 'success'){
					alert("系统名称修改成功!", 0, "window.location.reload();");
					return;
				}else{
					alert("系统名称写入失败!");
					return;
				}
			}
		});
	}else{
		alert("项目名称不能为空！");
		return;
	}
}
</script>
<form name="iscs_change_project_name_form" id="iscs_change_project_name_form" method="post">
<div class="up72_edit">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
        <tr class="pb_frmtr" >
          <th class="pb_frmth" style="width:160px;"><label>系统名称：</label></th>
          <td class="pb_frmtd" ><input type="text" id="projectName" class="input_txt" style="width:220px;" name="projectName" value="${projectName}"><input type="button" id="submitButton" name="submitButton" value="完成" onclick="editProjectName()" /></td>
        </tr>
    </table>    
</div>
</form>