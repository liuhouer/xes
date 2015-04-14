<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<table width="100%" cellspacing="0" cellpadding="0" class="z-datagrid">
		<tbody>
			<tr class="dataTableHead">
				<td width="38%" height="30" align="right" type="Tree">项&nbsp;</td>
				<td width="62%" field="count" type="Data">值</td>
			</tr>
			<tr>
				<td align="right">系统支持的语言：</td>
				<td>
				<table>
					<tbody>
						<tr>
							<td>中文(简体)(zh-cn)</td>
						</tr>
						<tr>
							<td>中文(繁體)(zh-tw)</td>
						</tr>
						<tr>
							<td>English(en)</td>
						</tr>
				</tbody>
				</table>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">当前语言：</td>
				<td>${result.userLanguage}</td>
			</tr>
		</tbody>
	</table>