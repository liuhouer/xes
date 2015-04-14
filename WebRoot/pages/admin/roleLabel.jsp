<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.bruce.util.SessionUtil,com.up72.base.UserDetails,com.bruce.common.CommonConstants;"%>
<%@ include file="/common/taglibs.jsp" %>
<span>
欢迎回来：
${loginMember.userName}！&nbsp;&nbsp;<span class="up72_menu_role"><span class="up72_role_text">角色：</span>
<c:choose>
<c:when test="${loginMember.userName == 'admin'}"><span class="role_current">超级管理员</span></c:when>
<c:otherwise>
<span id="role_current" class="role_current" onclick="$('#role_options').slideToggle('fast');">${role.name}</span>
    <div id="role_options" class="role_options" style="display:none;">
        <ul>
			<c:forEach items="${roleList}" var="item" varStatus="status">
        	<li><a href="javascript:;" onclick="setRoleId('${item.id}');">${item.name}</a></li>
			</c:forEach>
        </ul>
    </div>
</c:otherwise>
</c:choose>
</span>
<script language="javascript">
/*
$("#role_current").bind("click",function(){
	$("#role_current").showFlow("#role_options",{
		left : -700
	});
});
*/
function setRoleId(roleId){
 	//var roleId = document.getElementById("menu_roleId").value;
	if(roleId != ""){
		window.location.href="${ctx}/admin/setRoleId/"+ roleId ;
	}
}
function changRoleCurrent(dom){
	var styName = $(dom).attr('class');
	if(styName == "role_current"){
		$(dom).attr('class', 'role_current_open');
	}else{
		$(dom).attr('class', 'role_current');
	}
}
</script>


