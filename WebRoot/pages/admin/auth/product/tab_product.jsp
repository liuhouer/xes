<%@page import="com.up72.auth.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<jsp:include page="/pages/admin/auth/operation_btns.jsp" />
	
<div id="productTab_${id}">
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<span id="product1" onclick="getClass(this.id,'${ctx}/admin/auth/product/${id }/tabShow?AUTH_PERM_ID=${AUTH_PERM_ID}');"><a href="javascript:;">基本信息</a></span>
			<span id="product2" onclick="getClass(this.id,'${ctx}/admin/auth/product/${id }/tabRole?AUTH_PERM_ID=${AUTH_PERM_ID}');"><a href="javascript:;">角色列表</a></span>
			<span id="product3" onclick="getClass(this.id,'${ctx}/admin/auth/productAbout/${code}/tabIndex');"><a href="javascript:;">关于产品</a></span>
		</div>
	</div>
	<div id="pro">
	</div>
<!-- tab end -->
</div>
<script>
$(document).ready(function(){
	if(${productTabId}==2)
	{
		$("#product2").click();
	}else if(${productTabId}==3)
	{
		$("#product3").click();
	}else
	{
		$("#product1").click();
	}
})
function getClass(classId,url){
	var product = "product";
	for(var i=1;i<=3;i++){
		var id = product+i;
		$("#"+id).removeClass("current");
	}
	    $("#"+classId).addClass("current");;
		showPro(url);
}
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