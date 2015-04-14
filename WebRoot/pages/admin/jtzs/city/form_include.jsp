<%@page import="com.xes.jtzs.model.*"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page trimDirectiveWhitespaces="true"%>
<input type="hidden" id="id" name="id" value="${city.id}" />
<input type="hidden" id="sort" name="sort" value="${city.sort}" />
<tr class="frmtr">
	<th class="frmth">
		<label><%=City.ALIAS_PROVINCE_ID%>:</label>
	</th>
	<td class="frmtd" id="selectProvince">
   		<select id="provinceId" name="provinceId" style="width: 120px">
       		<c:forEach items="${provinceList}" var="item" varStatus="status">
			 	<option value="${item.id}" <c:if test="${item.id == city.provinceId}">selected="selected"</c:if> >${item.name}</option>
       		</c:forEach>
		</select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth">
		<label><%=City.ALIAS_NAME%>:</label>
	</th>
	<td class="frmtd">
		<form:input path="name" id="name" class="input_txt" maxlength="50" />
		<font color='red'>*<form:errors path="name" /></font>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth">
		<label><%=City.ALIAS_STATUS%>:</label>
	</th>
	<td class="frmtd">
		<c:forEach items="${statusArray}" var="item">
			${item.name}<input type="radio" id="status" 
				<c:if test="${city.status==item.index || (item.index==1 && city.status==null)}">checked="checked"</c:if> 
			name="status" value="${item.index}"/>
		</c:forEach>
	</td>
</tr>