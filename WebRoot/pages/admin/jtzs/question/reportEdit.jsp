<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.xes.jtzs.model.Question"%>
<%@page import="com.xes.jtzs.JTZSConstants"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page trimDirectiveWhitespaces="true"%>
<div class="up72_edit">
	<form:form id="admin_jtzs_question_edit_form" method="put" action="${ctx}/admin/jtzs/question/reportResultEdit">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
			<input type="hidden" id="questionId" name="questionId" value="${question.id}" />

			<tr class="frmtr">
				<th class="frmth"><label>是否违规:</label></th>
				<td class="frmtd">
					否&nbsp;<input type="radio" id="reportResult" name="reportResult" value="<%=JTZSConstants.ReportResult.NOVIOLATION.getIndex() %>">&nbsp;&nbsp;&nbsp;&nbsp;
					是&nbsp;<input type="radio" id="reportResult" name="reportResult" value="<%=JTZSConstants.ReportResult.VIOLATION.getIndex() %>">
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
		$("#admin_jtzs_question_edit_form").validate({
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