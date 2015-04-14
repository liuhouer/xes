<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" charset="utf-8" src="${ctx}/scripts/ckeditor/ckeditor.js"></script>

	<input type="hidden" id="id" name="id" value="${productAbout.id}"/>
	<input type="hidden" name="code" id="code" value="${productAbout.productCode }" />
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label>产品:</label></th>	
		 <td class="pb_frmtd">
		 <select id="productCode" name="productCode">
			<c:forEach items="${productList}" var="product">
				<option value="${product.code}" <c:if test="${productAbout.productCode == product.code}">selected="selected"</c:if>>${product.name}</option>
			</c:forEach>
		</select>
		<font color='red'><form:errors path="productCode"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=ProductAbout.ALIAS_TITLE%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="title" id="title" class="input_txt {required:true,byteMax:16,messages:{required:'名称不能为空',byteMax:'最多输入16个字节'}}" maxlength="50" />
		<font color='red'>*<form:errors path="title"/></font>
		</td>
	</tr>	
	<tr class="pb_frmtr" style="display: none;">
		<th class="pb_frmth"><label>排序:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="sortId" id="sortId" class="input_txt" maxlength="50" />
		<font color='red'><form:errors path="sortId"/></font>
		</td>
	</tr>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=ProductAbout.ALIAS_CONTENT%>:</label></th>	
		 <td class="pb_frmtd">
		<textarea name="content" id="content" class="ckeditor " style="width:600px;height:300px;visibility:hidden;">${productAbout.content}</textarea>
		<font color='red'><form:errors path="content"/></font>
		</td>
	</tr>	
