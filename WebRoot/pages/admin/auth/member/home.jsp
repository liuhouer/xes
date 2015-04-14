<%@page import="com.up72.auth.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp"%>
<up72:override name="head">
	<title><%=AuthUser.TABLE_ALIAS%>维护</title>
</up72:override>

<up72:override name="content">
	<table border="0" cellpadding="0" cellspacing="0" width="100%"
		height="100%" class="up72_mtable">
		<tr>
			<td class="up72_mleft">
		<tr>
			<td class="up72_filetree">
				<!--mainleft start-->
				<div class="up72_domleft">
					<div class="up72_sidebar skin_sidebar">
						<div class="up72_cnav">
							<ul class="up72_folders">
								<li>
									<a href="${ctx}/admin/auth/member/home" class="navover">首页</a>
								</li>
								<li>
									<a href="${ctx}/admin/auth/member/editInfo">个人信息</a>
								</li>
								<li>
									<a href="${ctx}/admin/auth/member/resetPassword">修改密码</a>
								</li>
								<li>
									<a href="${ctx}/admin/authUser/logout">退出管理</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!--mainleft end-->
			</td>
			</td>
			<td class="up72_mright">
				<!--mainright start-->
				<div class="up72_domright">
					<div id="up72_iframe" class="up72_content">
						<div class="up72_edit">
						</div>
					</div>
				</div>
				<!--mainright end-->
			</td>
		</tr>
	</table>
</up72:override>
<%@ include file="/pages/admin/adminNoPermBase.jsp"%>
