<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
  <form:form id="admin_auth_organization_edit_form" method="put" action="${ctx}/admin/auth/organization/${organization.id}" modelAttribute="organization">
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
		var jsonProducts = "[<c:forEach items="${organization.productList}" var="item" varStatus="status">\"${item.code}\"<c:if test="${!status.last}">,</c:if></c:forEach>]";
		jsonProducts = $.parseJSON(jsonProducts);
		var pids = $("[name='productIds']");
		
		$(pids).each(function(j,pid){
			$(jsonProducts).each(function(i,code){
				if(code == $(pid).val()){
					$(pid).attr("checked","checked");
					return;
				}
			});
		});
		$("#admin_auth_organization_edit_form").validate();	
	});
</script>