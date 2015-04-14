<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.xes.jtzs.model.Teacher"%>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <form:form id="admin_jtzs_teacher_edit_form" method="post" action="${ctx}/admin/jtzs/teacher/${teacher.id}/doEditPassword">
    <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
		<tr class="frmtr">
			<th class="frmth"><label><%=Teacher.ALIAS_LOGIN_NAME%>:</label></th>
			<td class="frmtd">
		     	<c:out value="${teacher.loginName}"></c:out>
			</td>
		</tr>
		<tr class="frmtr">
			<th class="frmth"><label>新密码:</label></th>
			<td class="frmtd">
				<input name="password" id="password" class="{required:true,byteMax:18, messages:{required:'请填写内容',byteMax:'不能大于18字'}} input_txt" maxlength="50" type="password" />
				<font color='red'>*<form:errors path="password" /> </font>
			</td>
		</tr>
		<tr class="frmtr">
			<th class="frmth"><label>再次输入新密码:</label></th>
			<td class="frmtd">
				<input name="rePassword" id="rePassword" class="{required:true,byteMax:18, messages:{required:'请填写内容',byteMax:'不能大于18字'}} input_txt" maxlength="50" type="password" />
				<font color='red'>*<form:errors path="rePassword" /> </font>
			</td>
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
		$("#admin_jtzs_teacher_edit_form").validate({
			/*  
			// ajax提交方式
			submitHandler:function(form){   
				form.submit();
			},
			*/
			errorPlacement: function(error, element) {
				error.appendTo(element.parent());
			}
		});	
	});
</script> 
