<%@page import="com.xes.jtzs.model.*" %>

<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_edit">
  <form:form id="admin_jtzs_scoreLog_tab_edit_form" method="put" action="${ctx}/admin/jtzs/scoreLog/${scoreLog.id}" modelAttribute="scoreLog">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <%@ include file="form_include.jsp" %>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_jtzs_scoreLog_tab_edit_form").validate({
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
