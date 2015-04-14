<%@page import="com.xes.jtzs.JTZSConstants.IsDel"%>
<%@page import="org.hamcrest.core.Is"%>
<%@page import="com.xes.jtzs.model.*" %>
<%@page import="com.xes.jtzs.JTZSConstants" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${wrongRules.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label>发送对象：</label></th>
  <td class="frmtd">    <%-- <form:input path="role" id="role" class="digits  input_txt" maxlength="3" /> --%>
    <select id="role" name="role" style="width: 120px" class="{required:true,messages:{required:'请选择对象'}}">
                <option value="" >请选择</option>
			 	<option value="<%=JTZSConstants.ROLE_STUDENT %>" <c:if test="${wrongRules.role == 0}">selected="selected"</c:if>>学生</option>
			 	<option value="<%=JTZSConstants.ROLE_TEACHER %>" <c:if test="${wrongRules.role == 1}">selected="selected"</c:if>>老师</option>
	</select>
    <font color='red'>*<form:errors path="role"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label>误报/违纪次数：</label></th>
  <td class="frmtd">
     <select id="wrongNum" name="wrongNum" style="width: 120px">
                <option value="" >请选择</option>
			 	<option value="1" <c:if test="${wrongRules.wrongNum == 1}">selected="selected"</c:if>>1</option>
			 	<option value="2" <c:if test="${wrongRules.wrongNum == 2}">selected="selected"</c:if>>2</option>
			 	<option value="3" <c:if test="${wrongRules.wrongNum == 3}">selected="selected"</c:if>>3</option>
			 	<option value="4" <c:if test="${wrongRules.wrongNum == 4}">selected="selected"</c:if>>4</option>
			 	<option value="5" <c:if test="${wrongRules.wrongNum == 5}">selected="selected"</c:if>>5</option>
	</select>
    <font color='red'>*<form:errors path="wrongNum"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label>发送内容:</label></th>
  <td class="frmtd">    <form:textarea path="content" id="content" class=" {required:true,messages:{required:'请填写内容'}}, input_txt" maxlength="200" />
    <font color='red'>*<form:errors path="content"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label>问题是否删除:</label></th>
  <td class="frmtd">
    <select id="isDelQuestion" name="isDelQuestion" style="width: 120px">
			 	<option value="<%=JTZSConstants.IsDel.DELETE.getIndex() %>" <c:if test="${wrongRules.isDelQuestion == 1}">selected="selected"</c:if>>是</option>
			 	<option value="<%=JTZSConstants.IsDel.UNDELETE.getIndex() %>" <c:if test="${wrongRules.isDelQuestion == 0}">selected="selected"</c:if>>否</option>
	</select>
    
    <font color='red'>*<form:errors path="isDelQuestion"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label>扣除积分:</label></th>
  <td class="frmtd"><form:input path="delScore" id="delScore" class="digits required input_txt" maxlength="5" />
    <font color='red'>*
    <form:errors path="delScore"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label>是否冻结账号:</label></th>
  <td class="frmtd">
    <select id="isStopLogin" name="isStopLogin" style="width: 120px">
			 	<option value="<%=JTZSConstants.Status.NORMAL.getIndex() %>" <c:if test="${wrongRules.isStopLogin == 1}">selected="selected"</c:if>>是</option>
			 	<option value="<%=JTZSConstants.Status.FREEZE.getIndex() %>" <c:if test="${wrongRules.isStopLogin == 0}">selected="selected"</c:if>>否</option>
	</select>
    <font color='red'>*
    <form:errors path="isStopLogin"/>
    </font></td>
  </tr>

