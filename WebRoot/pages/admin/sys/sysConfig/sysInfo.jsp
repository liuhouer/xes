<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
<title><%=AuthUser.TABLE_ALIAS%> 维护</title>
<script src="${ctx}/scripts/rest.js" ></script>
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
</up72:override>
<up72:override name="content">
	<div style="height: 100%; width: 100%; overflow-y: hidden;" class="z-overflowPanel" id="SP1">
			<table width="100%" cellspacing="6" border="0" style="border-collapse: separate; border-spacing: 6px;">
				<tbody><tr>
					<td width="50%" valign="top">
					<div class="z-legend"><b>应用信息</b></div>
						<%@ include file="appInfo.jsp" %>
					<p>&nbsp;</p>
					<div class="z-legend"><b>基础软件信息</b></div>
						<%@ include file="baseSoft.jsp" %>
					</td>
					<td width="50%" valign="top">
					<div class="z-legend"><b>数据库信息</b></div>
						<%@ include file="DBinfo.jsp" %>
					<p>&nbsp;</p>
					<div class="z-legend"><b>语言信息</b></div>
							<%@ include file="languageInfo.jsp" %>
					<p>&nbsp;</p>
					<div class="z-legend"><b>时区信息</b></div>
							<%@ include file="timeZoneInfo.jsp" %>
					</td>
				</tr>

			</tbody></table>
			</div>

</up72:override>
<jsp:include page="/admin/adminBase" />