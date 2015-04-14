 
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit skin_edit">
<form:form id="admin_sys_logBusiness_edit_form" method="put" action="${ctx}/admin/sys/logBusiness/${logBusiness.id}" modelAttribute="logBusiness">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="up72_edit_table">
    <%@ include file="form_include.jsp" %>
    </table>
    
    <div class="up72_edit_sub">
        <div class="btn btn_sub" title="提交">
            <input id="submitButton" name="submitButton" type="submit" value="提交" />
        </div>
        <div class="btn btn_cel" title="重置">
            <input type="reset" id="reset_button" value="重置" />
        </div>
        <div class="btn btn_cel" title="关闭">
            <input type="button" id="reset_button" value="关闭" onclick="closeBox();" />
        </div>
    </div>
</form:form>
</div>	
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_sys_logBusiness_edit_form").validate({
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