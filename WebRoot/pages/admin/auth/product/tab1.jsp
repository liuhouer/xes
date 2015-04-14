<%@page import="com.up72.auth.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>
	<div class="flexigrid">
		<div class="tDiv">
			<div class="tDiv2">
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/product/new?AUTH_PERM_ID=${id}','<%=Product.TABLE_ALIAS%>添加',600);">添加产品</span>
					</div>
				</div>
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/permissionGroup/new?AUTH_PERM_ID=${id}','<%=PermissionGroup.TABLE_ALIAS%>添加',600);">添加权限组</span>
					</div>
				</div>
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/permission/new?AUTH_PERM_ID=${id}','<%=Permission.TABLE_ALIAS%>添加',600);">添加权限</span>
					</div>
				</div>
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/role/newProductRole?AUTH_PERM_ID=${id}','<%=Role.TABLE_ALIAS%>添加',600);">添加角色</span>
					</div>
				</div>
				<!-- 
        <div class="fbutton">
          <div><span class="addorder" style="padding-left: 20px;" onclick="exportPermission();">导出权限</span></div>
        </div>
        <div class="fbutton">
          <div><span class="addorder" style="padding-left: 20px;" onclick="showImportPermission();">导入权限</span></div>
        </div>
         -->
			</div>
		</div>
	</div>
<div id="productTab_${id }">
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<c:if test="${type ==1}">
				<span onclick="showPro('${ctx}/admin/auth/product/${id }/tabRole?AUTH_PERM_ID=${AUTH_PERM_ID}');"><a href="javascript:;">角色</a></span> 
				<span onclick="showPro('${ctx}/admin/auth/product/${id}/edit?AUTH_PERM_ID=${AUTH_PERM_ID}')"><a href="javascript:;">编辑</a></span>
				<span onclick="showPro('${ctx}/admin/auth/product/${id }/tabShow?AUTH_PERM_ID=${AUTH_PERM_ID}')"><a href="javascript:;">基本信息</a></span>
			</c:if>
			<c:if test="${type ==2}">
				<span onclick="showPro('${ctx}/admin/auth/permissionGroup/${id }/edit?AUTH_PERM_ID=${AUTH_PERM_ID}');"><a href="javascript:;">编辑</a></span>
				<span onclick="showPro('${ctx}/admin/auth/permissionGroup/${id }/tabShow?AUTH_PERM_ID=${AUTH_PERM_ID}');"><a href="javascript:;">基本信息</a></span>
			</c:if>
			<c:if test="${type ==3}">
				<span onclick="showPro('${ctx}/admin/auth/permission/${id}/edit?AUTH_PERM_ID=${AUTH_PERM_ID}');"><a href="javascript:;">编辑</a></span>
				<span onclick="showPro('${ctx}/admin/auth/permission/${id}/tabShow');"><a href="javascript:;">基本信息</a></span>
			</c:if>
			
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
</script>