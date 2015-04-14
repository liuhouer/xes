<%@page import="com.xes.jtzs.model.*"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page trimDirectiveWhitespaces="true"%>
<input type="hidden" id="id" name="id" value="${grade.id}" />
<input type="hidden" id="sort" name="sort" value="${grade.sort}" />
<tr class="frmtr">
	<th class="frmth">
		<label><%=Grade.ALIAS_DIVISION%>:</label>
	</th>
	<td class="frmtd">
   		<select id="division" name="division" style="width: 120px">
       		<c:forEach items="${divisionArray}" var="item" varStatus="status">
			 	<option value="${item.index}" <c:if test="${item.index == grade.division}">selected="selected"</c:if> >${item.name}</option>
       		</c:forEach>
		</select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth">
		<label><%=Grade.ALIAS_NAME%>:</label>
	</th>
	<td class="frmtd">
		<form:input path="name" id="name" class=" input_txt" maxlength="50" />
		<font color='red'>*<form:errors path="name" /></font>
	</td>
</tr>
