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
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="up72_dashboard">
		<tr>
			<td class="dashboard_td">
				<div class="dashboard_info">
					<div class="promptsnews_tit">
						任务计划
					</div>
					<div class="promptsnews_con">
						<ul class="cms_con">
							<li>
								资源 总数：89 未审核：5 未发布：13
							</li>
							<li>
								栏目 总数：40 未发布：3
							</li>
							<li>
								模板 总数：23 未发布：2
							</li>
						</ul>
					</div>
				</div>
			</td>
			<td width="8" class="dashboard_tdw"></td>
			<td class="dashboard_td">
				<div class="dashboard_info">
					<div class="promptsnews_tit">
						运行环境
					</div>
					<div id="runTime" class="promptsnews_con">
						<%@ include file="dbVerify.jsp" %>
						<img />
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="4" height="8" class="dashboard_tdh"></td>
		</tr>
		<tr>
			<td class="dashboard_td">
				<div class="dashboard_info">
					<div class="promptsnews_tit">
						查询
					</div>
					<div class="promptsnews_con">
						<ul class="cms_con">
							<li>
								关键词：
								<input type="text" id="keyWord" class="input_txt"
									name="keyWord" value="" />
							</li>
							<li>
								类&nbsp;&nbsp;&nbsp;型：
								<input type="checkbox" id="type" name="type" value="1" />
								资源
								<input type="checkbox" id="type" name="type" value="1" />
								栏目
								<input type="checkbox" id="type" name="type" value="1" />
								模板
							</li>
							<li><input type="button" value="SEARCH" />
							</li>
							<li>
								热门查询：
								<a href="#">全媒体</a>&nbsp;
								<a href="#">平台</a>&nbsp;
								<a href="#">CMS</a>
							</li>
						</ul>
					</div>
				</div>
			</td>
			<td width="8" class="dashboard_tdw"></td>
			<td class="dashboard_td">
				<div class="dashboard_info">
					<div class="promptsnews_tit">
						平台介绍
					</div>
					<div class="promptsnews_con">
						<ul class="cms_con">
							<li>
								<a href="#">-关于CMS</a>
							</li>
							<li>
								<a href="#">-发布原理</a>
							</li>
							<li>
								<a href="#">-标准发布流程</a>
							</li>
							<li>
								<a href="#">-模版模型匹配规则</a>
							</li>
							<li>
								<a href="#">更多...</a>
							</li>
						</ul>
					</div>
				</div>
			</td>
		</tr>
	</table>
</up72:override>
<jsp:include page="/admin/adminBase" />