<%@page import="com.up72.sys.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${sysCategory.id}"/>
<input type="hidden" id="level" name="level" value="${sysCategory.level}" />  
<input type="hidden" id="pGuid" name="pGuid" value="${sysCategory.parentGuid}" />  
<input type="hidden" id="addTime" name="addTime" value="${sysCategory.addTime}"/>
<tr class="frmtr" style="display: none;"> 
  <th class="frmth"><label><%=SysCategory.ALIAS_GUID%>:</label></th>
  <td class="frmtd">    <form:input path="guid" id="guid" class=" input_text" maxlength="36" />
    <font color='red'>
    <form:errors path="guid"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SysCategory.ALIAS_CAT_NAME%>:</label></th>
  <td class="frmtd">    <form:input path="catName" id="catName" class="required input_text" maxlength="48" />
   	<span class="required">*</span>
    <font color='red'>
    <form:errors path="catName"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SysCategory.ALIAS_PARENT_GUID%>:</label></th>
  <td class="frmtd">  
  	<select id="parentGuid" name="parentGuid">
  	  <option value="">请选择上级分类</option>
  	  <c:forEach items="${categoryList}" var="item">
  	  	<option value='${item.guid}' <c:if test="${item.guid==sysCategory.parentGuid}">selected="selected"</c:if>>
  	  	<c:forEach begin="2" end="${item.level}">&nbsp;&nbsp;</c:forEach>
            <c:if test="${item.level > 1}">|-</c:if>
	  	  	${item.catName}</option>
  	  </c:forEach>
  	</select>
  	<span class="required">*</span>
    <font color='red'>
    <form:errors path="parentGuid"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SysCategory.ALIAS_CONFIG_SOURCE%>:</label></th>
  <td class="frmtd">    <form:input path="configSource" id="configSource" class=" input_text" maxlength="255" />
    <font color='red'>
    <form:errors path="configSource"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SysCategory.ALIAS_CONFIG_ID%>:</label></th>
  <td class="frmtd">    <form:input path="configId" id="configId" class="digits  input_text" maxlength="19" />
    <font color='red'>
    <form:errors path="configId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SysCategory.ALIAS_CONTENT_MODEL_ID%>:</label></th>
  <td class="frmtd">    <form:input path="contentModelId" id="contentModelId" class="required digits  input_text" maxlength="19" />
    <span class="required">*</span><font color='red'>
    <form:errors path="contentModelId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SysCategory.ALIAS_LIST_URL_PATH%>:</label></th>
  <td class="frmtd">    <form:input path="listUrlPath" id="listUrlPath" class=" input_text" maxlength="255" />
    <font color='red'>
    <form:errors path="listUrlPath"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SysCategory.ALIAS_CONFIG_URL_PATH%>:</label></th>
  <td class="frmtd">    <form:input path="configUrlPath" id="configUrlPath" class=" input_text" maxlength="255" />
    <font color='red'>
    <form:errors path="configUrlPath"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SysCategory.ALIAS_SORT_ID%>:</label></th>
  <td class="frmtd">    <form:input path="sortId" id="sortId" class="digits  input_text" maxlength="19" />
    <font color='red'>
    <form:errors path="sortId"/>
    </font></td>
  </tr>
<tr class="frmtr" style="display: none;"> 
  <th class="frmth"><label><%=SysCategory.ALIAS_ADD_TIME%>:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${sysCategory.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="addTimeString" name="addTimeString"  maxlength="20" class=" input_text" />
    <font color='red'>
    <form:errors path="addTime"/>
    </font></td>
  </tr>
