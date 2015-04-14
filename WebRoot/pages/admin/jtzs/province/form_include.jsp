<%@page import="com.xes.jtzs.model.*"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page trimDirectiveWhitespaces="true"%>
<input type="hidden" id="id" name="id" value="${province.id}" />
<input type="hidden" id="sort" name="sort" value="${province.sort}" />
<tr class="frmtr">
	<th class="frmth">
		<label><%=Province.ALIAS_NAME%>:</label>
	</th>
	<td class="frmtd">
		<form:input path="name" id="name" class="{required:true,byteMax:50,remote:'${ctx}/admin/jtzs/province/isUnique?id=${province.id}', messages:{remote:'内容不能重复',required:'请填写内容',byteMax:'不能大于50字'}} input_txt" maxlength="50" />
		<font color='red'> <form:errors path="name" /> </font>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth">
		<label><%=Province.ALIAS_STATUS%>:</label>
	</th>
	<td class="frmtd">
		<c:forEach items="${statusArray}" var="item">
			${item.name}<input type="radio" id="status" <c:if test="${province.status==item.index || (item.index==1 && province.status==null)}">checked="checked"</c:if> name="status" value="${item.index}"/>
		</c:forEach>	
	</td>
</tr>
