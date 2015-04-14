<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div id="permGroupMenu" class="up72_menu">
</div>
<script type="text/javascript">
	var permJson;
	<c:if test="${permJson != null && permJson != ''}">
		permJson = ${permJson};
	</c:if>
	var html = "<ul>";
	$(permJson).each(function(i,group){
		var permissionGroupId = <c:out value="${AUTH_PERMISSION_GROUP.id}" default="100"/>;
		var imgPath = "${ctx}/styles/default/skins/deepblue/images/icon_default.png";
		if(group.imgPath!=''){
			imgPath = group.imgPath;
		}
		if(group.id == permissionGroupId){
			html += "<li class='menu_sel'>";
		}else{
			html += "<li class=''>";
		}
		html += "<a href='${ctx}/admin/permGroup/" + group.id + "' id='topSubMenu_" + group.id + "'><span class=\"navIco\"><img src=${ctx}" + imgPath + "></span><p>" + group.name + "</p></a></li>"
	});
	html += "<li><a href='${ctx}/admin/authUser/logout'><span><img src='${ctx}/styles/traditional/skins/blue/images/system_exit.png'></span><p>退出系统</p></a></li>"
	html += "</ul>";
	$("#permGroupMenu").html(html);
</script>