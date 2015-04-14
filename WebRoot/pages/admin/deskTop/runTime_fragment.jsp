<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/taglibs.jsp"%>
<table>
	<tr>
		<td valign="top" align="center">
			<img src="${ctx}/images/computer.jpg" height="110px" width="110px;"><br/>
			<a href="javascript:;" onclick="admin_deskTop_dbVerify();">数据库校验</a><br/>
			<a href="${ctx}/admin/tools/db">数据库备份还原</a>
		</td>
		<td width="20">
			&nbsp;
		</td>
		<td>
			<ul class="cms_con">
				<c:forEach items="${prop}" var="item">
					<li>
						${item.key} : ${item.value}
					</li>
				</c:forEach>
			</ul>
		</td>
	</tr>
</table>
<script>
function admin_deskTop_dbVerify(){
	show("${ctx}/admin/deskTop/dbVerify?enforce=true", "数据库校验", 600 , 300);
	//$.ajax({
	//  	url: "${ctx}/admin/deskTop/dbVerify?enforce=true",
	//  	type: "GET",
	//  	cache: false,
	//  	success: function(html){
	//    	$("#runTime").html(html);
	//  	}
	//});
}
</script>