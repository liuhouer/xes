<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
	<input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID" value="${AUTH_PERM_ID}"/>
	<input type="hidden" id="id" name="id" value="${organization.id}"/>
	
 	<input type="hidden" id="parent" name="parent" value="1" />
	<input type="hidden" id="olevel" name="olevel" value="1" />
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Organization.ALIAS_NAME%>:</label></th>	
		 <td class="pb_frmtd">
		 <c:choose>
		 	<c:when test="${organization.id == 0 || organization.id=='' || organization.id==null}">
				<form:input path="name" id="name" class="{required:true,byteMax:19,remote:'${ctx}/admin/auth/organization/validateName',messages:{remote:'该机构名已被占用'}} input_txt" maxlength="19" />
		 	</c:when>
		 	<c:otherwise>
		 		<form:input path="name" id="name" class="{required:true} input_txt" maxlength="19" />
		 	</c:otherwise>
		 </c:choose>
				<span class="required">*</span><font color='red'><form:errors path="name"/></font>
		</td>
	 </tr>
	 <!--
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Organization.ALIAS_PARENT%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="parent" id="parent" class="digits input_txt" maxlength="19" />
		<font color='red'><form:errors path="parent"/></font>
		</td>
	</tr>
	-->	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Organization.ALIAS_DOMAIN%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="domain" id="domain" class="required input_txt" maxlength="125" />
		<span class="required">*</span><font color='red'><form:errors path="domain"/></font>
		</td>
	</tr>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Organization.ALIAS_ENABLED%>:</label></th>	
		 <td class="pb_frmtd">
		<form:radiobutton path="enabled" value="1" label="启用" checked="checked"/>
		<form:radiobutton path="enabled" value="0" label="禁用"/></td>
	</tr>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label>用户职能组:</label></th>	
		<td class="pb_frmtd">
		 <c:forEach items="${productList}" var="items" varStatus="true">
			<span style="padding-right:10px">
				<input type="checkBox" name="productIds" value="${items.code}" />${items.name }
			</span>
		 </c:forEach>
		</td>
	</tr>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Organization.ALIAS_REMARK%>:</label></th>	
		 <td class="pb_frmtd">
		<form:textarea rols="5" cols="25" path="remark" id="remark" class="input_txt" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
		<font color='red'><form:errors path="remark"/></font>
		</td>
	</tr>	
	<!--
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Organization.ALIAS_OLEVEL%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="olevel" id="olevel" class="required digits input_txt" maxlength="10" />
		<span class="required">*</span><font color='red'><form:errors path="olevel"/></font>
		</td>
	</tr>	
	-->