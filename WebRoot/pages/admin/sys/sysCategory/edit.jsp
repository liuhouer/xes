<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_edit">
  <form:form id="admin_sys_sysCategory_edit_form" method="put"  modelAttribute="sysCategory">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <%@ include file="form_include.jsp" %>
    </table>
    <div class="edit_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_sys_sysCategory_edit_form").validate({
			
			// ajax提交方式
			submitHandler:function(form){   
				$.ajax({
		  			url : "${ctx}/admin/sys/sysCategory/${sysCategory.id}/update",
		  			type : "post",
		  			"data" : $("#admin_sys_sysCategory_edit_form").serialize(),
		  			success : function(response){
		  				closeBox();
		  				alert("更新成功",3,function(){
  							window.location.reload();
  						});
		  			} 
  				});
			},
			
			errorPlacement: function(error, element) {
				error.appendTo(element.parent());
			}
		});	
	
	$("#parentGuid").change( function() {
		var selectVal=$(this).val();
		var thisId=$("#guid").val();
		var pId=$("#pGuid").val();
		if(selectVal==thisId){
			alert("不能选择自身为上级分类");
			$("option[value="+pId+"]").attr("selected","selected");
		}
	});
});


</script>