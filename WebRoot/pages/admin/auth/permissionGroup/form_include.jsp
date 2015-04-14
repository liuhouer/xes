<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.up72.auth.AuthConstants"%>
<%@ include file="/common/taglibs.jsp" %>
<%
Integer system = AuthConstants.common.AUTH_SYSTEM;
Integer user = AuthConstants.common.AUTH_USER;
request.setAttribute("system", system);
request.setAttribute("user", user);
%>
	<input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID" value="${AUTH_PERM_ID}"/>
	<input type="hidden" id="id" name="id" value="${permissionGroup.id}"/>
	<input type="hidden" name="status" id="status" value="" disabled="disabled" />
		
	<input type="hidden" name="return_url" id="return_url" value="redirect:/admin/auth/product/indexTree" />
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=PermissionGroup.ALIAS_NAME%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="name" id="name" class="required input_txt {byteMax:16,messages:{byteMax:'最多输入16个字节'}}" maxlength="30" />
		<span class="required">*</span><font color='red'><form:errors path="name"/></font>
		</td>
	</tr>	
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=PermissionGroup.ALIAS_STATUS%>:</label></th>	
		 <td class="pb_frmtd"><input type="radio" name="status" class="required" value="${system }" <c:if test="${permissionGroup.status == system }">checked="checked"</c:if>>系统权限组
		 	<input type="radio" name="status" class="required" value="${user }" <c:if test="${permissionGroup.status == user || permissionGroup.status==null}">checked="checked"</c:if>>用户权限组
			<span class="required">*</span><font color='red'><form:errors path="status"/></font>
		</td>
	</tr>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=PermissionGroup.ALIAS_PRODUCT_ID%>:</label></th>	
		 <td class="pb_frmtd">
		 <c:choose>
		 	<c:when test="${permissionGroup.code!=null && permissionGroup.code!=''}">
				 <select id="productCode" name="productCode" class="required" onchange="getProductStatusEdit()">
				 	<option value="">==请选择==</option>
				 	<c:forEach items="${productList}" var="items" varStatus="true">
				 	<option value="${items.code}" status="${items.status}" <c:if test="${items.code==permissionGroup.productCode }">selected="selected"</c:if>>${items.name}</option>
				 	</c:forEach>
				 </select>
		 	</c:when>
		 	<c:when test="${permissionGroup.code!=null && permissionGroup.code!=''}">
				 <select id="productCode" name="productCode" class="required" onchange="getProductStatusEdit()">
				 	<option value="">==请选择==</option>
				 	<c:forEach items="${productList}" var="items" varStatus="true">
				 	<option value="${items.code}" status="${items.status}" <c:if test="${items.code==permissionGroup.productCode }">selected="selected"</c:if>>${items.name}</option>
				 	</c:forEach>
				 </select>
		 	</c:when>
		 	<c:otherwise>
				 <select id="productCode" name="productCode" class="required" onchange="getProductStatus()">
				 	<option value="">==请选择==</option>
				 	<c:forEach items="${productList}" var="items" varStatus="true">
				 	<option value="${items.code}" status="${items.status}" <c:if test="${items.code==permissionGroup.productCode }">selected="selected"</c:if>>${items.name}</option>
				 	</c:forEach>
				 </select>
		 	</c:otherwise>
		 </c:choose>
		<span class="required">*</span><font color='red'><form:errors path="productCode"/></font>
		</td>
	</tr>	
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=PermissionGroup.ALIAS_IMG_PATH%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="imgPath" id="imgPath" class="input_txt" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
		<a href="javascript:;" onclick="uploadIconPath();">上传</a>
		<a style="display: none;" id="viewImg" href="${permissionGroup.imgPath}" target="_blank">查看</a>
		<script type="text/javascript">
			loadViewImg();
		</script>
		<font color='red'><form:errors path="imgPath"/></font>
		</td>
	</tr>	
	<c:if test="${null!=permissionGroup && null!=permissionGroup.code && permissionGroup.code!=''}">
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=PermissionGroup.ALIAS_CODE%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="code" id="code" readonly="readonly" class="input_txt" maxlength="100" />
		<font color='red'><form:errors path="code"/></font>
		</td>
	</tr>	
	</c:if>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=PermissionGroup.ALIAS_SORT_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="sortId" id="sortId" class="input_txt {byteMax:99,messages:{byteMax:'最多输入99个字节'}}" maxlength="100" />
		<font color='red'><form:errors path="sortId"/></font>
		</td>
	</tr>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=PermissionGroup.ALIAS_DESCRIPTION%>:</label></th>	
		 <td class="pb_frmtd">
		<form:textarea cols="25" rows="5" path="description" id="description" class="input_txt {byteMax:99,messages:{byteMax:'最多输入99个字节'}}" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
		<font color='red'><form:errors path="description"/></font>
		</td>
	</tr>

	<script language="javascript">	
	function getProductStatus(){
		var status = jQuery("#productCode  option:selected").attr("status");
		if(status == ${system}){
			jQuery("input[type='radio'][value='${system}']").attr("checked", "checked");
			jQuery("input[type='radio']").attr("disabled", "disabled");
			jQuery("#status").removeAttr("disabled");
			jQuery("#status").val(${system});
		}else if(status == ${user}){
			jQuery("input[type='radio'][value='${user}']").attr("checked", "checked");
			jQuery("input[type='radio']").removeAttr("disabled");
			jQuery("#status").attr("disabled", "disabled");
			jQuery("#status").val(${user});
		}else{
			jQuery("input[type='radio'][value='${user}']").attr("checked", "checked");
			jQuery("input[type='radio']").removeAttr("disabled");
			jQuery("#status").attr("disabled", "disabled");
			jQuery("#status").val("");
		}
	}
	
	function getProductStatusEdit(){
		var permGroupStatus = ${user};
		<c:if test="${null!=permissionGroup.status}">
			permGroupStatus = ${permissionGroup.status};
		</c:if>
		var selectStatus = jQuery("#productCode  option:selected").attr("status");
		if(selectStatus == ${system}){
			jQuery("input[type='radio'][value='${system}']").attr("checked", "checked");
			jQuery("input[type='radio']").attr("disabled", "disabled");
			jQuery("#status").removeAttr("disabled");
			jQuery("#status").val(${system});
		}else if(selectStatus == ${user}){
			jQuery("input[type='radio'][value='"+permGroupStatus+"']").attr("checked", "checked");
			jQuery("input[type='radio']").removeAttr("disabled");
			jQuery("#status").attr("disabled", "disabled");
			jQuery("#status").val(permGroupStatus);
		}else{
			jQuery("input[type='radio'][value='${user}']").attr("checked", "checked");
			jQuery("input[type='radio']").removeAttr("disabled");
			jQuery("#status").attr("disabled", "disabled");
			jQuery("#status").val("");
		}
	}
	<c:choose>
		<c:when test="${null!=permissionGroup && null!=permissionGroup.code && permissionGroup.code!=''}">
			getProductStatusEdit();
		</c:when>
		<c:otherwise>
			getProductStatus();
		</c:otherwise>
	</c:choose>
	</script>