<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="<c:out value='${dictionaryValue.id}' default='0' />"/>
	<input type="hidden" id="parentId" name="parentId" value="<c:out value='${parentDictionaryValue.id}' default='0' />"/>
	<input type="hidden" id="dictionaryId" name="dictionaryId" value="${dictionary.id}"/>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label>所属字典:</label></th>	
		 <td class="pb_frmtd">
	 	 <c:choose>
		 	<c:when test="${dictionary.id == '' || dictionary.id == 0 || dictionary.id == null}">
				<form:input path="dictionary.name" id="dictionary.name" class="input_txt" maxlength="100" />
		 	</c:when>
		 	<c:otherwise>
				<input type="text" disabled="disabled" class="input_txt" value="${dictionary.name }" />
		 		<form:hidden path="dictionary.name" id="dictionary.name" />
		 	</c:otherwise>
		 </c:choose>
		<font color='red'><form:errors path="dictionary.name"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label>字典值名称:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="code" id="code" class="input_txt" maxlength="30" />
		<span class="required">*</span><font color='red'><form:errors path="code"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label>字典值:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="value" id="value" class="input_txt" maxlength="100" />
		<span class="required">*</span><font color='red'><form:errors path="value"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label>字典值描述:</label></th>	
		 <td class="pb_frmtd">
		<form:textarea rols="5" cols="25" path="description" id="description" class="input_txt" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
		<font color='red'><form:errors path="description"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label>字典值排序:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="sortId" id="sortId" class="digits input_txt" maxlength="19" />
		<font color='red'><form:errors path="sortId"/></font>
		</td>
	</tr>	
		