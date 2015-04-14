<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
  <form:form id="admin_sys_dictionary_edit_form" method="post" action="${ctx}/admin/sys/dictionary/doEdit" modelAttribute="dictionary">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <%@ include file="form_include.jsp" %>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="button" id="submit_btn" name="submit_btn" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="goBack();" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_sys_dictionary_edit_form").validate({
			submitHandler:function(){
				$("#submit_btn").unbind("click");
				saveAndSubmit();
			}
		});	
		$("#submit_btn").bind("click",function(){
			$("#admin_sys_dictionary_edit_form").submit();
		});
	});
	function saveAndSubmit(){
		var id = $("#id").val();
		$.ajax({
			url : "${ctx}/admin/sys/dictionary/doEdit",
			type : "post",
			data : $("#admin_sys_dictionary_edit_form").serialize(),
			dataType : "text",
			success : function (result){
				if(result =="1"){
					showPro("${ctx}/admin/sys/dictionary/"+id+"/view");
				}
			}
		});
	}
	function goBack(){
		var id = $("#id").val();
		$.ajax({
			  type: "POST",
			  url: "${ctx}/admin/sys/dictionary/tab",
			  data: "id=" + id,
			  dataType: "html",
			  cache: false,
			  success: function(html){
			    	$("#editDiv").html(html);
			  }
			});
	}
</script>
