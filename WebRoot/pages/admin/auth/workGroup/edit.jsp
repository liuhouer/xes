<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
  <form:form id="admin_auth_workGroup_edit_form" method="put" action="${ctx}/admin/auth/workGroup/${workGroup.id}" modelAttribute="workGroup" >
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
		$("#admin_auth_workGroup_edit_form").validate();	
	});
	
function selProduct(){
	var selproductCodes = '${productIds}';
	if(!isNull(selproductCodes)){
		selproductCodes = eval(selproductCodes);
		var boxes = $("input[name='productIds']");
		if(isNull(boxes)){
			return ;
		}
		
		for(var j=0;j<selproductCodes.length;j++){
			for(var i=0;i<boxes.length;i++){
				var boxPro = $(boxes[i]).val();
				if(boxPro == selproductCodes[j]){
					$(boxes[i]).attr("checked","checked");
				}
			}
		}
	}
}
selProduct();
</script>