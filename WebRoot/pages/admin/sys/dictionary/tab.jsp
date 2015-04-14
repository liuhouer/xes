<%@page import="com.up72.sys.model.Dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>
	<div class="flexigrid">
		<div class="tDiv">
			<div class="tDiv2">
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/sys/dictionary/${dictionary.id }/addChild','<%=Dictionary.TABLE_ALIAS%>添加',600);">添加字典</span>
					</div>
				</div>
			</div>
		</div>
	</div>
<div id="productTab_${id }">
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<span class="current"><a href="javascript:;">基本信息</a></span>
		</div>
	</div>
	<div id="pro">
	</div>
<!-- tab end -->
</div>
<script>
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
	$.ajax({
			url : "${ctx}/admin/sys/dictionary/${dictionary.id}/view",
			dataType : "html",
			success : function(html){
				$("#pro").html(html);
			},
			error : function(){
				alert("网络错误，请稍后重试。");
			}
		});
</script>