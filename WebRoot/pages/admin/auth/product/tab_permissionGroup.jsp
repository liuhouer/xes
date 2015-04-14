<%@page import="com.up72.auth.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
	
<jsp:include page="/pages/admin/auth/operation_btns.jsp" />

<div id="permissionGroupTab_${id }">
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<span id="permissionGroup" class ="current" onclick="showPro('${ctx}/admin/auth/permissionGroup/${id }/tabShow?AUTH_PERM_ID=${AUTH_PERM_ID}');"><a href="javascript:;">基本信息</a></span>
		</div>
	</div>
	<div id="pro">
	</div>
<!-- tab end -->
</div>
<script>
	$(document).ready(function(){
		$("#permissionGroup").click();
	})
	function showPro(url){
		$.ajax({
			url : url,
			dataType : "html",
			success : function(html){
				$("#pro").html(html);
				
			},
			error : function(){
				alert("网络错误，请稍后重试。");
			}
		});
	}
</script>