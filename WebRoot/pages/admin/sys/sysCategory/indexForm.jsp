<%@page import="com.up72.sys.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/pages/admin/include/resource.jsp" %>
<%@include file="/pages/admin/include/getInnerStyleSkin.jsp" %>
<script src="<c:url value="/scripts/jquery.js"/>" type="text/javascript"></script>		
<script src="<c:url value="/scripts/main.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/application.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/weebox/bgiframe.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/weebox/weebox.js"/>" type="text/javascript"></script>
<link href="<c:url value="/scripts/weebox/weebox.css"/>" type="text/css" rel="stylesheet" />
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 

<style>
.edit_tablet{font-size:12px; float:left;}
.frmtht{background: none repeat scroll 0 0 #F6F9FC;border-right: 1px solid #EAF2FA;border-bottom: 1px solid #EAF2FA;font-weight: normal;padding: 5px;text-align: right;vertical-align: top;width: 120px; line-height: 26px;}
.frmtdt{border-bottom: 1px solid #EAF2FA;line-height: 23px;padding: 4px;vertical-align: top;}
.edit_submitt{margin-left:13%; padding: 1%;float: left;}
</style>

  <form id="admin_sys_sysCategory_page_form" method="post" target="resource_frame"name="admin_sys_sysCategory_page_form" action="${ctx}/admin/sys/sysCategory/${sysCategory.id}/update">
  	<input type="hidden" id="id" name="id" value="${sysCategory.id}"/>
  	<input type="hidden" id="guid" name="guid" value="${sysCategory.guid}"/>
  	<input type="hidden" id="addTime" name="addTime" value="${sysCategory.addTime}"/>
  	<input type="hidden" id="pGuid" name="pGuid" value="${sysCategory.parentGuid}" />  
  	<input type="hidden" id="level" name="level" value="${sysCategory.level}" />
    <table id="admin_sys_sysCategory_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_tablet">
      <tbody>
         <tr class="frmtr"> 
			  <th class="frmtht"><label><%=SysCategory.ALIAS_CAT_NAME%>:</label></th>
			  <td class="frmtdt"><input type="text" name="catName" id="catName"  value='<c:if test="${!empty  sysCategory}">${sysCategory.catName}</c:if>' class=" input_text" maxlength="48" /></td>
		 </tr>
		 <tr class="frmtr"> 
			  <th class="frmtht"><label><%=SysCategory.ALIAS_PARENT_GUID%>:</label></th>
			  <td class="frmtdt">
			  	<select id="parentGuid" name="parentGuid">
  	  				<option value="">请选择上级分类</option>
  	 				 <c:forEach items="${categoryList}" var="item">
  	  					<option value='${item.guid}' <c:if test="${item.guid==sysCategory.parentGuid}">selected="selected"</c:if>>
  	  					<c:forEach begin="2" end="${item.level}">&nbsp;&nbsp;</c:forEach>
           				 <c:if test="${item.level > 1}">|-</c:if>
	  	  					${item.catName}</option>
  	  				</c:forEach>
  				</select>
  			</td>
		 </tr>
		 <tr class="frmtr"> 
			  <th class="frmtht"><label><%=SysCategory.ALIAS_CONFIG_SOURCE%>:</label></th>
			  <td class="frmtdt"><input type="text" name="configSource" id="configSource" value='<c:if test="${!empty  sysCategory}">${sysCategory.configSource}</c:if>' class=" input_text" maxlength="255" /></td>
		 </tr>
		 <tr class="frmtr"> 
			  <th class="frmtht"><label><%=SysCategory.ALIAS_CONFIG_ID%>:</label></th>
			  <td class="frmtdt"><input type="text" name="configId" id="configId" value='<c:if test="${!empty  sysCategory}">${sysCategory.configId}</c:if>' class="digits  input_text" maxlength="19" /></td>
		 </tr>
		 <tr class="frmtr"> 
			  <th class="frmtht"><label><%=SysCategory.ALIAS_CONTENT_MODEL_ID%>:</label></th>
			  <td class="frmtdt"><input type="text" name="contentModelId" id="contentModelId" value='<c:if test="${!empty  sysCategory}">${sysCategory.contentModelId}</c:if>' class="required digits  input_text" maxlength="19" /></td>
		 </tr>
		 <tr class="frmtr"> 
			  <th class="frmtht"><label><%=SysCategory.ALIAS_LIST_URL_PATH%>:</label></th>
			  <td class="frmtdt"><input type="text" name="listUrlPath" id="listUrlPath" value='<c:if test="${!empty  sysCategory}">${sysCategory.listUrlPath}</c:if>' class="url  input_text" maxlength="255" /></td>
		 </tr>
		 <tr class="frmtr"> 
			  <th class="frmtht"><label><%=SysCategory.ALIAS_CONFIG_URL_PATH%>:</label></th>
			  <td class="frmtdt"><input type="text" name="configUrlPath" id="configUrlPath" value='<c:if test="${!empty  sysCategory}">${sysCategory.configUrlPath}</c:if>' class="url digits  input_text" maxlength="10" /></td>
		 </tr>
		 <tr class="frmtr"> 
			  <th class="frmtht"><label><%=SysCategory.ALIAS_SORT_ID%>:</label></th>
			  <td class="frmtdt"><input type="text" name="sortId" id="sortId" value='<c:if test="${!empty  sysCategory}">${sysCategory.sortId}</c:if>' class="digits  input_text" maxlength="19" /></td>
		 </tr>
      </tbody>
    </table>
    <c:if test="${!empty  sysCategory}">
     <div class="edit_submitt">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton"  value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onClick="closeBox();" /></div>
    </div>
   </c:if>
  </form>
 <script type="text/javascript">
	<%--$(document).ready(function(){
		$("#admin_sys_sysCategory_page_form").validate({
			// ajax提交方式
			submitHandler:function(form){   
				$.ajax({
					"url" : "${ctx}/admin/sys/sysCategory/${sysCategory.id}/update",
					"type" : "POST",
					"data" : $("#admin_sys_sysCategory_page_form").serialize(),
					"dataType" : "json",
					"success" : function (data){
					
					}
				});
			},
			errorPlacement: function(error, element) {
				error.appendTo(element.parent());
			}
		});
	});
	
 
 	
 --%>
 
 	$("#parentGuid").change( function() {
		var selectVal=$(this).val();
		var thisId=$("#guid").val();
		var pId=$("#pGuid").val();
		if(selectVal==thisId){
			alert("不能选择自身为上级分类");
			$("option[value="+pId+"]").attr("selected","selected");
		}
	});
</script>