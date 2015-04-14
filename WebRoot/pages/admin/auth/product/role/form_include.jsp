<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
	function chooseWorkGroup(dom, workGroupId){
		if(isNull($("#organizationId").val())){
			alert("请先选择机构");
			return ;
		}
		var url = "${ctx}/admin/auth/role/workGroup?orgId="+$("#organizationId").val()+"&workGroupId="+workGroupId;
		
		showSelectPage(dom,{url:url,title:'选择用户组',selBox:'workGroupBox',selList:"#role_workGroupSel .selrow",selId:"#workGroupId",selName:"#workGroupName"});
	}

 	function changProductId(sel){
 		var productCode = $(sel).val();
 		if(isNull(productCode) || productCode==""){
 			return;
 		}
 		$(sel).find("option").each(function(i,opt){
 			if($(opt).attr("selected")){
 				$("#productId").val($(opt).attr("productId"));
 				return;
 			}
 		});
 	}
 </script>
	<input type="hidden" id="id" name="id" value="${role.id}"/>
	<input type="hidden" id="remark" name="remark" value="${role.remark}"/>
	<input type="hidden" id="createUserId" name="createUserId" value="${role.createUserId}"/>
	<input type="hidden" name="return_url" id="return_url" value="redirect:/admin/auth/product/indexTree?AUTH_PERM_ID=${AUTH_PERM_ID}" />
	
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Role.ALIAS_PRODUCT_ID%>:</label></th>	
		 <td class="pb_frmtd">
		 <input type="hidden" id="productId" name="productId" value="${role.product.id}"/>
		 <select id="productCode" name="productCode" onchange="changProductId(this)" class="{required:true,messages:{required:'请选择产品'}}">
		 	<option value="">请选择角色所属产品</option>
		 	<c:forEach items="${productList}" var="product">
		 	<option value="${product.code}" productId="${product.id}"  <c:if test="${product.code == role.productCode}"> selected="selected"</c:if>>${product.name}</option>
		 	</c:forEach>
		 </select>
		<span class="required">*</span><font color='red'><form:errors path="productCode"/></font>
		</td>
	</tr>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Role.ALIAS_NAME%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="name" id="name" class="{required:true,byteMax:16,messages:{required:'请输入角色名',byteMax:'最多输入16个字节'}}"  maxlength="20" /> <!-- 验证输入的角色名称是否已经存在 （有bug暂时屏蔽） {required:true,byteMax:20,remote:'${ctx}/admin/auth/role/validateRoleName?productCode=${productCode}',messages:{remote:'该角色名称已被占用'}} -->
		<span class="required">*</span><font color='red'><form:errors path="name"/></font>
		</td>
	</tr>
	<!-- 角色是否为系统默认角色判断处理开始 -->
	<input type="hidden" name="organizationId" id="organizationId" value="0" />
	<input type="hidden" name="workGroupId" id="workGroupId" value="0" />
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
		<form:textarea cols="5" rols="25" path="description" id="description" class="input_txt {byteMax:99,messages:{byteMax:'最多输入99个字节'}}" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
		<font color='red'><form:errors path="description"/></font>
		</td>
	</tr>