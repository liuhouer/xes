<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table width="100%" cellspacing="0" cellpadding="0" class="z-datagrid">
		<tbody>
			<tr class="dataTableHead">
				<td width="36%" height="30" align="right" type="Tree">项&nbsp;</td>
				<td width="64%" field="count" type="Data">值</td>
			</tr>
			<tr>
				<td align="right">操作系统名称：</td>
				<td>${result.osName}</td>
			</tr>
			<tr>
				<td align="right">操作系统版本：</td>
				<td>${result.osVersion}</td>
			</tr>
			<tr>
				<td align="right">操作系统补丁：</td>
				<td><c:if test="${ empty result.buding}">unknown</c:if></td>
			</tr>
			<tr>
				<td align="right">JDK厂商：</td>
				<td>${result.jdkVendor}</td>
			</tr>
			<tr>
				<td align="right">JDK版本：</td>
				<td>${result.jdkVersion}</td>
			</tr>
			<tr>
				<td align="right">JDK主目录：</td>
				<td>${result.java_home}</td>
			</tr>
			<tr>
				<td align="right">Servlet容器名称：</td>
				<td><%application.getServerInfo(); %></td>
			</tr>
			<tr>
				<td align="right">启动Servlet容器的用户名：</td>
				<td>--root</td>
			</tr>
			<tr>
				<td align="right">JDK己用内存数/最大可用数：</td>
				<td>--517M/986M</td>
			</tr>
			<tr>
				<td align="right">文件编码：</td>
				<td>--GBK</td>
			</tr>
	</tbody>
	</table>
