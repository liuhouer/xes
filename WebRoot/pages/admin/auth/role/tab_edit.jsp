<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/scripts/rowsel.js"></script>
<script type="text/javascript">
/*
	function chooseWorkGroup(dom){
		if(isNull($("#organizationId").val())){
			alert("请先选择机构");
			return ;
		}
		var url = "${ctx}/admin/auth/role/workGroup?orgId="+$("#organizationId").val()+"&id="+$("#admin_auth_role_tab_edit_form").find("input[id='id']").val();
		
		showSelectPage(dom,{url:url,title:'选择用户组',selBox:'workGroupBox',selList:"#role_workGroupSel .selrow",selId:"#workGroupId",selName:"#workGroupName"});
	}
	*/
	function chooseOrganization(sel){
		var orgId = $(sel).val();
		if(isNull(orgId)){
			var optHtml = "<option value=''>请选择部门</option>";
			$("#workGroupId").html(optHtml);
			return ;
		}
		$.ajax({
			url : "${ctx}/admin/auth/workGroup/"+orgId+"/workGroupJSON",
			dataType : "json",
			success : function(jsonDatas){
				var optHtml = "<option value=''>请选择部门</option>";
				$(jsonDatas).each(function(i,obj){
					optHtml += "<option value='"+obj.id+"'>"+obj.name+"</option>";
				});
				$("#workGroupId").html(optHtml);
			},
			error : function(){
				alert("网络错误，请刷新页面重试。");
			}
		});
	}
</script>
<style type="text/css">
.overcss {
	color: #FF0000;
	background: #999999;
}
.clickcss {
	background: #006600;
	color: #000099;
}
</style>
<form:form id="admin_auth_role_edit_form" method="put" action="${ctx}/admin/auth/role/${role.id}" modelAttribute="role">

<input type="hidden" id="return_url" name="return_url" value="/admin/auth/organization/indexTree" />
<input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID" value="${AUTH_PERM_ID}" />
<div class="up72_edit">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
    <input type="hidden" id="id" name="id" value="${role.id}"/>
    <form:hidden path="productCode" id="productCode" />
    <!-- 角色是否为系统默认角色判断处理开始 -->
    <c:choose>
      <c:when test="${role.productCode == null}">
        <tr class="pb_frmtr">
          <th class="pb_frmth">&nbsp;</th>
          <td class="pb_frmtd"><c:if test="${role.productCode != null}"> <font color="#FF0000">(该角色为系统角色不能编辑)</font> </c:if></td>
        </tr>
        <tr class="pb_frmtr">
          <th class="pb_frmth"><label><%=Role.ALIAS_ORGANIZATION_ID%>:</label></th>
          <td class="pb_frmtd"><select id="organizationId" name="organizationId" onchange="chooseOrganization(this);">
              <option value="">请选择机构</option>
              <c:forEach items="${organizationList}" var="org">
                <option value="${org.id}"
                <c:if test="${org.id == role.organizationId}"> selected="selected"</c:if>
                >${org.name} </option>
              </c:forEach>
            </select>
            <span class="required">*</span><font color='red'>
            <form:errors path="organizationId"/>
            </font></td>
        </tr>
        <tr class="pb_frmtr">
          <th class="pb_frmth"><label><%=Role.ALIAS_WORK_GROUP_ID%>:</label></th>
          <td class="pb_frmtd"><select id="workGroupId" name="workGroupId">
              <option value="">请选择部门</option>
              <c:forEach items="${workGroupList}" var="workGroup">
                <option value="${workGroup.id}"
                <c:if test="${workGroup.id == role.workGroupId}"> selected="selected"</c:if>
                >${workGroup.name} </option>
              </c:forEach>
            </select>
            <span id="workGroupSpan"></span> <span class="required">*</span><font color='red'>
            <form:errors path="workGroupId"/>
            </font></td>
        </tr>
      </c:when>
      <c:when test="${role.productCode != null }">
        <input type="hidden" name="organizationId" id="organizationId" value="0" />
        <input type="hidden" name="workGroupId" id="workGroupId" value="0" />
      </c:when>
    </c:choose>
    <!-- 角色是否为系统默认角色判断处理结束 -->
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=Role.ALIAS_NAME%>:</label></th>
      <td class="pb_frmtd"><c:choose>
          <c:when test="${role.productCode == null}">
            <form:input path="name" id="name" class="required input_txt" maxlength="255" />
          </c:when>
          <c:when test="${role.productCode != null}">
            <input type="text" name="name" id="name" disabled="disabled" value="${role.name}"/>
          </c:when>
        </c:choose>
        <span class="required">*</span><font color='red'>
        <form:errors path="name"/>
        </font></td>
    </tr>
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=Role.ALIAS_ENABLED%>:</label></th>
      <td class="pb_frmtd"><c:choose>
          <c:when test="${role.productCode == null }">
            <form:radiobutton path="enabled" value="1" class="required" checked="checked" />
            启用
            <form:radiobutton path="enabled" value="0" class="required" />
            禁用 </c:when>
          <c:when test="${role.productCode != null}">
            <input type="radio" name="enabled" value="1" disabled="disabled"
            <c:if test="${role.enabled == 1}"> checked="checked"</c:if>
            />
            启用
            <input type="radio" name="enabled" value="0" disabled="disabled"
            <c:if test="${role.enabled == 0}"> checked="checked"</c:if>
            />
            不启用 </c:when>
        </c:choose></td>
    </tr>
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=Role.ALIAS_DESCRIPTION%>：</label></th>
      <td class="pb_frmtd"><c:choose>
          <c:when test="${role.productCode == null}">
            <form:textarea rows="5" cols="25" path="description" id="description" class="input_txt" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
          </c:when>
          <c:when test="${role.productCode != null}">
            <textarea rows="5" cols="25" name="description" id="description" disabled="disabled">${role.description}</textarea>
          </c:when>
        </c:choose>
        <font color='red'>
        <form:errors path="description"/>
        </font></td>
    </tr>
  </table>
  <c:if test="${role.productCode == null}">
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </c:if>
</div>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_role_tab_edit_form").validate();	
	});
</script> 
