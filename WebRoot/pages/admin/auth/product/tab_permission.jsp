<%@page import="com.up72.auth.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
	
<jsp:include page="/pages/admin/auth/operation_btns.jsp" />

<div id="permissionTab_${id }">
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<span id="permission" class="current" onclick="showPro('${ctx}/admin/auth/permission/${id}/tabShow');"><a href="javascript:;">基本信息</a></span>
		</div>
	</div>
	<div id="pro">
	</div>
<!-- tab end -->
</div>
<script>
	$(document).ready(function(){
		$("#permission").click();
	});
	
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