<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@ include file="/common/taglibs.jsp" %>
<style>
<!--
.z-legend {
    line-height: 25px;
}
-->
</style>
<div class="up72_show">
   <table width="100%" height="227" align="center" cellspacing="0" cellpadding="2" class="show_table">
	<tbody><tr>
		<td>
			<div class="z-legend"><b>基本信息</b></div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="show_table">
				<tbody><tr>
					<td width="39%" height="30" align="right"><%=AuthUser.ALIAS_USER_NAME%>：</td>
					<td width="61%" id="tdUserName"><c:out value='${authUser.userName}'/></td>
				</tr>
				<tr>
					<td height="30" align="right"><%=AuthUser.ALIAS_NICK_NAME%>：</td>
					<td><c:out value='${authUser.nickName}'/></td>
				</tr>
				<tr id="tr_Password2">
					<td height="30" align="right"><%=AuthUser.ALIAS_PASSWORD%>：</td>
					<td><c:out value='${authUser.password}'/></td>
				</tr>
				<tr>
					<td height="30" align="right"><%=AuthUser.ALIAS_ENABLED%>：</td>
					<td>
						<c:choose>
				          <c:when test="${authUser.enabled == 0}">禁用</c:when>
				          <c:when test="${authUser.enabled == 1}">启用</c:when>
				        </c:choose>
					</td>
				</tr>
				<tr>
					<td height="30" align="right"><%=AuthUser.ALIAS_EMAIL%>：</td>
					<td><c:out value='${authUser.email}'/></td>
				</tr>
				<tr>
					<td height="30" align="right"><%=AuthUser.ALIAS_PROBLEM%>：</td>
					<td><c:out value='${authUser.problem}'/></td>
				</tr>
				<tr>
					<td height="30" align="right"><%=AuthUser.ALIAS_ANSER%>：</td>
					<td><c:out value='${authUser.anser}'/></td>
				</tr>
				<tr>
					<td height="30" align="right">备注：</td>
						<td><c:out value='${authUser.comment}'/></td>
				</tr>
			</tbody></table>
		</td>
		<td width="50%" valign="top" id="tdInfo">
			<div style="width:100%;text-align:center;margin-top:15px;">
				<%=AuthUser.ALIAS_IMG_PATH%>:<a style="width:120px;height:120px;overflow:hidden;" href="javascript:void(0)">
					<img width="120" src="<c:out value='${authUser.imgPath}'/>"  id="LogoFileImg">
				</a>
			</div>
		</td>
	</tr>
</tbody></table>
</div>
<div title="编辑" class="btn btn_sub right" id="displayEdit"><input type="button" value="编辑" name="submitButton" id="submitButton" onclick="showEdit()"></div>