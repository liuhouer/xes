<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<table width="100%" cellspacing="0" cellpadding="0" class="z-datagrid">
			<tbody>
				<tr class="dataTableHead">
					<td width="38%" height="30" align="right" type="Tree">项&nbsp;</td>
					<td width="62%" field="count" type="Data">值</td>
				</tr>
				<tr>
					<td align="right">数据库类型：</td>
					<td>${result.DBname}<c:if test="${ empty result.DBname}">unknown</c:if></td>
				</tr>
				<tr>
					<td align="right">数据库服务器地址：</td>
					<td><%request.getServerName();%></td>
				</tr>
				<tr>
					<td align="right">数据库服务器端口：</td>
					<td><%request.getServerPort(); %></td>
				</tr>
				<tr>
					<td align="right">数据库名称：</td>
					<td>--zcms2_final_demo</td>
				</tr>
				<tr>
					<td align="right">用户名：</td>
					<td>${result.DBuser}</td>
				</tr>
			</tbody>
		</table>