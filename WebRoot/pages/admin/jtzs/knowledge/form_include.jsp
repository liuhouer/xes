<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${knowledge.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=Knowledge.ALIAS_GRADE%>:</label></th>
  <td class="frmtd">    
  	<%-- <form:input path="gradeId" id="gradeId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="gradeId"/>
    </font> --%>
    
    
    <select id="gradeId" name="gradeId" style="width: 120px">
       		<c:forEach items="${gradeList}" var="item" varStatus="status">
			 	<option value="${item.id}" <c:if test="${item.id == knowledge.gradeId}">selected="selected"</c:if> >${item.name}</option>
       		</c:forEach>
	</select>
    </td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Knowledge.ALIAS_SUBJECT%>:</label></th>
  <td class="frmtd">   
    <%-- <form:input path="subjectId" id="subjectId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="subjectId"/>
    </font> --%>
    
    <select id="subjectId" name="subjectId" style="width: 120px">
       		<c:forEach items="${subjectList}" var="item" varStatus="status">
			 	<option value="${item.id}" <c:if test="${item.id == knowledge.subjectId}">selected="selected"</c:if> >${item.name}</option>
       		</c:forEach>
	</select>
    </td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Knowledge.ALIAS_KNOWLEDGE1%>:</label></th>
  <td class="frmtd"><form:input path="knowledge1" id="knowledge1" class="{required:true,messages:{required:'请填写内容'}} input_txt"  />
    <font color='red'>*
    <form:errors path="knowledge1"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Knowledge.ALIAS_KNOWLEDGE2%>:</label></th>
  <td class="frmtd"><form:input path="knowledge2" id="knowledge2" class="input_txt"  />
    <font color='red'><form:errors path="knowledge2"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Knowledge.ALIAS_KNOWLEDGE3%>:</label></th>
  <td class="frmtd"><form:input path="knowledge3" id="knowledge3" class="input_txt"  /> 
    <font color='red'><form:errors path="knowledge3"/>
    </font></td>
  </tr>
<%-- <tr class="frmtr"> 
  <th class="frmth"><label><%=Knowledge.ALIAS_ADD_TIME%>:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${knowledge.addTime}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="addTime" name="addTime"  class=" input_text" />
    <font color='red'>
    <form:errors path="addTime"/>
    </font></td>
  </tr> --%>
