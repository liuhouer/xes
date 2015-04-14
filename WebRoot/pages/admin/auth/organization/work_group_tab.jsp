<%@page import="com.up72.auth.model.WorkGroup"%>
<%@page import="com.up72.auth.model.Organization"%>
<%@page import="com.up72.auth.model.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>
	<div class="flexigrid">
		<div class="tDiv">
			<div class="tDiv2">
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/organization/new','<%=Organization.TABLE_ALIAS%>添加',600);">添加机构</span>
						&nbsp;&nbsp;
					</div>
				</div>
				
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/workGroup/new?organizationId=${workGroup.organizationId }&workGroupId=${workGroup.id }','<%=WorkGroup.TABLE_ALIAS%>添加',600);">添加部门</span>
						&nbsp;&nbsp;
					</div>
				</div>
				
				<div class="fbutton">
					<div>
						<span class="addorder" style="padding-left: 20px;"
							onclick="show('${ctx}/admin/auth/role/new?parentType=wg&workGroupId=${workGroup.id }','<%=Role.TABLE_ALIAS%>添加',600);">添加角色</span>
						&nbsp;&nbsp;
					</div>
				</div>
			</div>
		</div>
	</div>
<div id="workgroupTab_${id}">
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<span id="work_group_tab1" onclick="getClass(this.id,'${ctx}/admin/auth/workGroup/${workGroup.id }/view')"><a href="javascript:;">部门信息</a></span>
			<span id="work_group_tab2" onclick="getClass(this.id,'${ctx}/admin/auth/workGroup/${workGroup.id }/workGroupList')"><a href="javascript:;">部门列表</a></span>
			<span id="work_group_tab3" onclick="getClass(this.id,'${ctx}/admin/auth/workGroup/${workGroup.id }/roleList')"><a href="javascript:;">角色列表</a></span>
		</div>
	</div>
	<div id="pro">
	</div>
<!-- tab end -->
</div>
<script>
$(document).ready(function(){
	$("#work_group_tab1").click();
})
function getClass(classId,url){
	var product = "work_group_tab";
	for(var i=1;i<=3;i++){
		var id = product+i;
		$("#"+id).removeClass("current");
	}
	    $("#"+classId).addClass("current");;
		showPro(url);
}
	function showPro(url){
		$.ajax({
			url : url,
			dataType : "html",
			success : function(html){
				$("#pro").html(html);
			},
			error : function(){
				alert("网络错误，请稍后重试。");
			}
		});
	}
</script>