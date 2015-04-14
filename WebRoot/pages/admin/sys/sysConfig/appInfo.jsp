<%@ page language="java"  pageEncoding="UTF-8"%>
<table width="100%" cellspacing="0" cellpadding="0" class="z-datagrid">
	<tbody>
		<tr class="dataTableHead">
			<td width="36%" height="30" align="right" type="Tree">项&nbsp;</td>
			<td width="64%" field="count" type="Data">值</td>
		</tr>
		<tr>
			<td align="right">应用代码：</td>
			<td>${result.projectCode}<c:if test="${ empty result.projectCode}">unknown</c:if></td>
		</tr>
		<tr>
			<td align="right">应用名称：</td>
			<td>${result.projectName}<c:if test="${ empty result.projectName}">unknown</c:if></td>
		</tr>
		<tr>
			<td align="right">应用版本：</td>
			<td>${result.projectVersion}<c:if test="${ empty result.projectVersion}">unknown</c:if></td>
		</tr>
		<tr>
			<td align="right">本次启动时间：</td>
			<td>${result.projectStarTime}<c:if test="${ empty result.projectStarTime}">unknown</c:if></td>
		</tr>
		<tr>
			<td align="right">最后更新时间：</td>
			<td>${result.projectLastUpdateTime}<c:if test="${ empty result.projectLastUpdateTime}">unknown</c:if></td>
		</tr>
		<tr>
			<td align="right">是否是调试模式：</td>
			<td>${result.projectisDebug}<c:if test="${ empty result.projectisDebug}">unknown</c:if></td>
		</tr>
</tbody></table>