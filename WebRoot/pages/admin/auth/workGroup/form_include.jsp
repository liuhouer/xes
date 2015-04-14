<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
	<script>
		function onChangeWorkGroup(){
			var organizationId= $("#organizationId").val();
			if(isNull(organizationId)){
				var optHtml = "<option value=''>请选择部门</option>";
				$("#parent").html(optHtml);
				return ;
			}
			$.ajax({
				url : "${ctx}/admin/auth/workGroup/"+organizationId+"/workGroupJSON",
				dataType : "json",
				success : function (jsonDatas){
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
					$("#parent").html(optHtml);
				},
				error :  function(){
					alert("网络错误，请刷新页面重试。");
				}
			});
		}
	</script>
	<input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID" value="${AUTH_PERM_ID}"/>
	<input type="hidden" id="id" name="id" value="${workGroup.id}"/>
	<!-- <input type="hidden" id="parent" name="parent" value="<c:out value="${workGroup.parent}" default="0" />"/> -->
	<form:hidden path="addTime" id="addTime" />
	<input type="hidden" name="return_url" id="return_url" value="redirect:/admin/auth/organization/indexTree" />
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=WorkGroup.ALIAS_ORGANIZATION_ID%>:</label></th>	
		 <td class="pb_frmtd">
			 <select id="organizationId" name="organizationId" onchange="onChangeWorkGroup();" class="{required:true}">
			 	<option value="">请选择机构</option>
			 	<c:forEach items="${organizationList}" var="org">
			 	<option value="${org.id}"<c:if test="${org.id == organizationId}"> selected="selected"</c:if>>${org.name}</option>
			 	</c:forEach>
			 </select>
		<span class="required">*</span><font color='red'><form:errors path="organizationId"/></font>
		</td>
	 </tr>
	 <tr class="pb_frmtr">
	 	<th class="pb_frmth"><label><%=WorkGroup.ALIAS_PARENT%>:</label></th>	
	 		<td class="pb_frmtd">
	 			<select id="parent" name="parent">
	 				<option value="0">请选择部门</option>
	 				<c:forEach items="${workGroupList}" var="item">
				 	<option value="${item.id}"<c:if test="${item.id == workGroupId}"> selected="selected"</c:if>>
				 		<c:forEach begin="2" end="${item.deptLevel}">&nbsp;</c:forEach><c:if test="${item.deptLevel >1}">|-</c:if>${item.name}
				 	</option>
			 	</c:forEach>
	 			</select>
	 		</td>
	 	</tr>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=WorkGroup.ALIAS_NAME%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="name" id="name" class="{required:true,byteMax:20,remote:'${ctx}/admin/auth/workGroup/validateName?organizationId=${organization.id}',messages:{remote:'该部门名称已被占用'}} input_txt" maxlength="20" />
		<span class="required">*</span><font color='red'><form:errors path="name"/></font>
		</td>
	 </tr>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=WorkGroup.ALIAS_ENABLED%>:</label></th>	
		 <td class="pb_frmtd">
		 <form:radiobutton path="enabled" value="1" label="启用" checked="checked" />
		 <form:radiobutton path="enabled" value="0" label="禁用"/>
		<span class="required"></span><font color='red'><form:errors path="enabled"/></font>
		</td>
	</tr>	
	<!-- <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=WorkGroup.ALIAS_PRODUCT_LIST%>:</label></th>	
		<td class="pb_frmtd">
		 <c:forEach items="${productList}" var="items" varStatus="true">
			<span style="padding-right:10px">
				<input type="checkBox" name="productIds" value="${items.code}" />${items.name }
			</span>
		 </c:forEach>
		</td>
	</tr> -->
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=WorkGroup.ALIAS_REMARK%>:</label></th>	
		 <td class="pb_frmtd">
		<form:textarea rows="5" path="remark" id="remark" class="input_txt" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
		<font color='red'><form:errors path="remark"/></font>
		</td>
	</tr>
