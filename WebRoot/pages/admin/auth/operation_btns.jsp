<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="flexigrid">
		<div class="tDiv">
			<div class="tDiv2">
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/product/new?AUTH_PERM_ID=${AUTH_PERM_ID}','<%=Product.TABLE_ALIAS%>添加',600);">添加产品</span>
					</div>
				</div>
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/permissionGroup/new?productCode=${code }&AUTH_PERM_ID=${AUTH_PERM_ID}','<%=PermissionGroup.TABLE_ALIAS%>添加',600);">添加权限组</span>
					</div>
				</div>
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/permission/new?productCode=${code }&AUTH_PERM_ID=${AUTH_PERM_ID}','<%=Permission.TABLE_ALIAS%>添加',600);">添加权限</span>
					</div>
				</div>
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/role/newProductRole?productCode=${code }&AUTH_PERM_ID=${AUTH_PERM_ID}','<%=Role.TABLE_ALIAS%>添加',600);">添加角色</span>
					</div>
				</div>
			</div>
		</div>
	</div>