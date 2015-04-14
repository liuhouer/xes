<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/scripts/rowsel.js"></script>
<script type="text/javascript">
/*
	function chooseWorkGroup(dom, workGroupId){
		if(isNull($("#organizationId").val())){
			alert("请先选择机构");
			return ;
		}
		var url = "${ctx}/admin/auth/role/workGroup?orgId="+$("#organizationId").val()+"&workGroupId="+workGroupId;
		
		showSelectPage(dom,{url:url,title:'选择用户组',selBox:'workGroupBox',selList:"#role_workGroupSel .selrow",selId:"#workGroupId",selName:"#workGroupName"});
	}
	*/
	function chooseOrganization(sel){
		var orgId = $(sel).val();
		if(isNull(orgId)){
			var optHtml = "<option value=''>请选择部门</option>";
			$("#workGroupId").html(optHtml);
			return ;
		}
		$.ajax({
			url : "${ctx}/admin/auth/workGroup/"+orgId+"/workGroupJSON",
			dataType : "json",
			success : function(jsonDatas){
				var optHtml = "<option value=''>请选择部门</option>";
					var nbsp = "";
					var str = "";
					$(jsonDatas).each(function(i,obj){
						for(var j=2;j<obj.deptLevel;j++){
							nbsp += "&nbsp;";
						}
						if(obj.deptLevel >1){
							str = "|-";
						}
						optHtml += "<option value='"+ obj.id+"'>"
						+nbsp+str+obj.name+"</option>";
					});
					$("#workGroupId").html(optHtml);
			},
			error : function(){
				alert("网络错误，请刷新页面重试。");
			}
		});
	}
</script>
<input type="hidden" name="return_url" id="return_url" value="redirect:/admin/auth/role" />
	<input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID" value="${AUTH_PERM_ID}"/>
	<input type="hidden" id="id" name="id" value="${role.id}"/>
	<c:choose>
	<c:when test="${null != role.id && rold.id > 0}">
	<input type="hidden" id="createUserId" name="createUserId" value="${role.createUserId}"/>
	</c:when>
	<c:otherwise>
	<input type="hidden" id="createUserId" name="createUserId" value="${role.createUserId}"/>
	</c:otherwise>
	</c:choose>
	<form:hidden path="productCode" id="productCode" />
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Role.ALIAS_NAME%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="name" id="name" class="{required:true,byteMax:20,remote:'${ctx}/admin/auth/role/validateName?workGroupId=${workGroupId}&organizationId=${organizationId}',messages:{remote:'该角色名称已被占用'}} input_txt" maxlength="20" />
		<span class="required">*</span><font color='red'><form:errors path="name"/></font>
		</td>
	</tr>
	<!-- 角色是否为系统默认角色判断处理开始 -->
	<c:choose>
	<c:when test="${null == role.productCode}">
		<tr class="pb_frmtr">
		    <th class="pb_frmth"><label><%=Role.ALIAS_ORGANIZATION_ID%>:</label></th>	
			 <td class="pb_frmtd">
			 <select id="organizationId" name="organizationId" onchange="chooseOrganization(this);" class="required">
			 	<option value="">请选择机构</option>
			 	<c:forEach items="${organizationList}" var="org">
			 	<option value="${org.id}"<c:if test="${org.id == role.organizationId}"> selected="selected"</c:if>>${org.name}</option>
			 	</c:forEach>
			 </select>
			<span class="required">*</span><font color='red'><form:errors path="organizationId"/></font>
			</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Role.ALIAS_WORK_GROUP_ID%>:</label></th>	
			 <td class="pb_frmtd">
			 <select id="workGroupId" name="workGroupId" class="required">
			 	<option value="">请选择部门</option>
			 	<c:forEach items="${workGroupList}" var="workGroup">
			 	<option value="${workGroup.id}"<c:if test="${workGroup.id == role.workGroupId}"> selected="selected"</c:if>>
			 	<c:if test="${workGroup.deptLevel>2}">
				 	<c:forEach begin="3" end="${workGroup.deptLevel}" step="1">&nbsp;</c:forEach>
				</c:if> 	
			 	<c:if test="${workGroup.deptLevel>1}">|-</c:if>
			 	${workGroup.name}
			 	</option>
			 	</c:forEach>
			 </select>
			<span id="workGroupSpan"></span>
			<span class="required">*</span><font color='red'><form:errors path="workGroupId"/></font>
			</td>
		</tr>	
	</c:when>
	<c:when test="${null != role.productCode }">
	<input type="hidden" name="organizationId" id="organizationId" value="0" />
	<input type="hidden" name="workGroupId" id="workGroupId" value="0" />
	</c:when>
	</c:choose>
	<!-- 角色是否为系统默认角色判断处理结束 -->
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Role.ALIAS_ENABLED%>:</label></th>	
		 <td class="pb_frmtd">
		<form:radiobutton path="enabled" value="1" class="required" checked="checked" />启用
		<form:radiobutton path="enabled" value="0" class="required" />禁用
		</td>
	</tr>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Role.ALIAS_DESCRIPTION%>:</label></th>	
		 <td class="pb_frmtd">
		<form:textarea cols="5" rols="25" path="description" id="description" class="input_txt" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
		<font color='red'><form:errors path="description"/></font>
		</td>
	</tr>