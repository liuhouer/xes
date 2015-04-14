<%@page import="com.up72.auth.model.*"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<script type="text/javascript" src="${ctx}/scripts/permtree/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/scripts/permtree/jquery.treeview.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/scripts/permtree/jquery.treeview.css" />
<script type="text/javascript" src="${ctx}/scripts/rowsel.js"></script>
<script language="javascript" type="text/javascript">
/*
function chooseWorkGroup(dom, workGroupId, childName, childId){
	var url="${ctx}/admin/auth/member/workGroup?workGroupId=" + workGroupId;
	showSelectPage(dom,{url:url,title:'选择部门',selBox:'workGroupBox',selList:"#member_workGroupSel .selrow",selId:"#workGroupId",selName:"#workGroupName",callFun:function(){
		$(childName).val("");
		$(childId).val("");
	}});
}
function chooseRole(dom, roleId){
	if(isNull($("#workGroupId").val())){
		alert("请先选择所属部门");
		return ;
	}
	var url = "${ctx}/admin/auth/member/role?workGroupId=" + $("#workGroupId").val() + "&roleId=" + roleId;
	showSelectPage(dom,{url:url,title:'选择用户组',selBox:'roleBox',selList:"#member_roleSel .selrow",selId:"#roleId",selName:"#roleName"});
}
*/
var memberWorkGroupJson = '[';
	<c:forEach items="${memberWorkGroupList}" var="workGroup" varStatus="status">
	memberWorkGroupJson += '{"id":"${workGroup.id}","name":"${workGroup.name}"}<c:if test="${!status.last}">,</c:if>';
	</c:forEach>
	memberWorkGroupJson += ']';
memberWorkGroupJson = $.parseJSON(memberWorkGroupJson);
var memberRoleJson = '[';
	<c:forEach items="${memberRoleList}" var="role" varStatus="status">
	memberRoleJson += '{"id":"${role.id}","name":"${role.name}"}<c:if test="${!status.last}">,</c:if>';
	</c:forEach>
	memberRoleJson += ']';
memberRoleJson = $.parseJSON(memberRoleJson);

function chooseOrg(sel){
	var orgId = $(sel).val();
	if(isNull(orgId)){
		$("#workGroupRoleDiv").html("");
		return ;
	}
	loadWorkgroupRoleTree(orgId);
}

function loadWorkgroupRoleTree(orgId){
	$.ajax({
		url : "${ctx}/admin/auth/organization/"+orgId+"/workGroupRoleTree",
		type : "post",
		success : function(html){
			$("#workGroupRoleDiv").html(html);
			if(memberWorkGroupJson.length > 0){
				$("[name='workGroupIds']").each(function(j,obj){
					for(var i=0;i<memberWorkGroupJson.length;i++){
						if($(obj).val() == memberWorkGroupJson[i].id){
							$(obj).attr("checked","checked");
						}
					}
				});
			}
			if(memberRoleJson.length > 0){
				$("[name='roleIds']").each(function(j,obj){
					for(var i=0;i<memberRoleJson.length;i++){
						if($(obj).val() == memberRoleJson[i].id){
							$(obj).attr("checked","checked");
						}
					}
				});
			}
		}
	});
}
<c:if test="${null!=authUser && authUser.organizationId>0}">
	loadWorkgroupRoleTree(${authUser.organizationId});
</c:if>
</script>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="up72_edit">
  <form name="memberWorkGroupRoleForm" id="memberWorkGroupRoleForm" action="${ctx}/admin/auth/member/updateOrganization">
    <input type="hidden" name="memberId" id="memberId" value="${authUser.id}" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=WorkGroup.ALIAS_ORGANIZATION_ID %></label></th>
        <td class="pb_frmtd"><select class="{required:true,messages:{required:'请选择机构'}}" id="organizationId" name="organizationId" onchange="chooseOrg(this)">
            <option value="">请选择机构</option>
            <c:forEach items="${organizationList}" var="org"> <option value="${org.id}"
              <c:if test="${authUser.organizationId == org.id}"> selected="selected"</c:if>
              >${org.name}
              </option>
            </c:forEach>
          </select></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label>角色选择</label></th>
        <td class="pb_frmtd"><div id="workGroupRoleDiv"></div></td>
      </tr>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
    <div id="workGroupRoleError"> </div>
  </form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#wr_submitButton").bind("click",function(){
			$("#memberWorkGroupRoleForm").submit();
		});
		$("#memberWorkGroupRoleForm").validate({
			submitHandler : function(frm){
				//if(!isSelect("workGroupIds","#memberWorkGroupRoleForm")){
				//	$("#workGroupRoleError").html("请选择用户所属部门");
				//}else 
				
				if(!isSelect("roleIds","#memberWorkGroupRoleForm")){
					$("#workGroupRoleError").html("请选择用户所属角色");
				}else{
					$("#workGroupRoleError").html("");
					$.ajax({
						url : "${ctx}/admin/auth/member/updateOrganization",
						type : "post",
						data : $("#memberWorkGroupRoleForm").serialize(),
						dataType : "json",
						success : function(jsondatas){
							if(jsondatas.status =="success"){
								alert("操作成功");
								window.location.reload();
							}else if(jsondatas.status =="error"){
								alert(decodeURIComponent(jsondatas.message));
							}else{
								alert("系统忙，请稍后重试");
							}
						},
						error : function(){
							alert("系统忙，请稍后重试");
						}
					});
				}
			}
		});	
	});
</script> 
