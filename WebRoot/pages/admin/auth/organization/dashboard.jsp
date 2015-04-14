<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.up72.sys.model.Dictionary"%>
<%@page import="com.up72.auth.model.Organization"%>
<%@page import="com.up72.auth.model.WorkGroup"%>
<%@page import="com.up72.auth.model.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp"%>

	<div class="flexigrid">
		<div class="tDiv">
			<div class="tDiv2">
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/organization/new','<%=Organization.TABLE_ALIAS%>添加',600);">添加机构</span>
						&nbsp;&nbsp;
					</div>
				</div>
				
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/workGroup/new?organizationId=${workGroup.organizationId }&workGroupId=${workGroup.id }','<%=WorkGroup.TABLE_ALIAS%>添加',600);">添加部门</span>
						&nbsp;&nbsp;
					</div>
				</div>
				
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/role/new','<%=Role.TABLE_ALIAS%>添加',600);">添加角色</span>
						&nbsp;&nbsp;
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div>
		
	</div>