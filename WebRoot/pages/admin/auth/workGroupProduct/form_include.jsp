<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${organizationProduct.id}"/>

	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=OrganizationProduct.ALIAS_ORGANIZATION_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="organizationId" id="organizationId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span><font color='red'><form:errors path="organizationId"/></font>
		</td>
		<th class="pb_frmth"><label><%=OrganizationProduct.ALIAS_PRODUCT_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="productCode" id="productCode" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span><font color='red'><form:errors path="productCode"/></font>
		</td>
	</tr>	
		