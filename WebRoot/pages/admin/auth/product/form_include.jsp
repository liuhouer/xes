<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.AuthConstants"%>
<%@ include file="/common/taglibs.jsp" %>

	<input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID" value="${AUTH_PERM_ID}"/>
	<input type="hidden" id="id" name="id" value="${product.id}"/>

	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Product.ALIAS_NAME%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="name" id="name" class="required input_txt {byteMax:16,messages:{byteMax:'最多输入16个字节'}}" maxlength="30" />
		<span class="required">*</span><font color='red'><form:errors path="name"/></font>
		</td>
	</tr>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Product.ALIAS_TAG%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="tag" id="tag" class="input_txt {byteMax:29,messages:{byteMax:'最多输入29个字节'}}" maxlength="30" />
		<font color='red'><form:errors path="tag"/></font>
		</td>
	</tr>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Product.ALIAS_STATUS%>:</label></th>	
		 <td class="pb_frmtd"><input type="radio" id="status" name="status" value="<%=AuthConstants.common.AUTH_ALL %>"	
				 <c:if test="${(product.status == null) || (product.status == 0)}">checked</c:if>
			/>全局产品
			<input type="radio" id="status" name="status" value="<%=AuthConstants.common.AUTH_SYSTEM %>"	 
				 <c:if test="${product.status == 1}">checked</c:if>
			/>系统产品
			<input type="radio" id="status" name="status" value="<%=AuthConstants.common.AUTH_USER %>"	 
				 <c:if test="${product.status == 2}">checked</c:if>
			/>用户产品
		<span class="required">*</span><font color='red'><form:errors path="status"/></font>
		</td>
	</tr>
	<c:if test="${null!=product && null!=product.id && product.id>0}">
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Product.ALIAS_CODE%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="code" id="code" readonly="readonly" class="input_txt" maxlength="100" />
		<font color='red'><form:errors path="code"/></font>
		</td>
	</tr>	
	</c:if>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Product.ALIAS_IMG_PATH%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="imgPath" id="imgPath" class="input_txt" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
		<a href="javascript:;" onclick="uploadIconPath();">上传</a>
		<a style="display: none;" id="viewImg" href="${product.imgPath}" target="_blank">查看</a>
		<script type="text/javascript">
			loadViewImg();
		</script>
		<font color='red'><form:errors path="imgPath"/></font>
		</td>
	</tr>	
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label>默认地址:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="dashboardUrl" id="dashboardUrl" class="input_txt {byteMax:99,messages:{byteMax:'最多输入99个字节'}}" maxlength="100" />
		<font color='red'><form:errors path="dashboardUrl"/></font>
		</td>
	</tr>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Product.ALIAS_DESCRIPTION%>:</label></th>	
		 <td class="pb_frmtd">
		<form:textarea cols="25" rows="5" path="description" id="description" class="input_txt {byteMax:99,messages:{byteMax:'最多输入99个字节'}}" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
		<font color='red'><form:errors path="description"/></font>
		</td>
	</tr>	
		