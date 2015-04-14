<%@page import="com.xes.jtzs.model.*"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page trimDirectiveWhitespaces="true"%>
<input type="hidden" id="id" name="id" value="${subject.id}" />
<input type="hidden" id="sort" name="sort" value="${subject.sort}" />

<tr class="frmtr">
	<th class="frmth"><label><%=Subject.ALIAS_NAME%>:</label></th>
	<td class="frmtd">
		<form:input path="name" id="name" class="{required:true,byteMax:50,remote:'${ctx}/admin/jtzs/subject/isUnique?id=${subject.id}', messages:{remote:'内容不能重复',required:'请填写内容',byteMax:'不能大于50字'}}  input_txt" maxlength="50" />
		<font color='red'>*<form:errors path="name" /> </font>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Subject.ALIAS_STATUS%>:</label></th>
	<td class="frmtd">
		<c:forEach items="${statusArray}" var="item">
			${item.name}<input type="radio" id="status" <c:if test="${subject.status==item.index || (item.index==1 && subject.status==null)}">checked="checked"</c:if> name="status" value="${item.index}"/>
		</c:forEach>
	</td>
</tr>