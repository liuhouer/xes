<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src="${ctx}/scripts/rowsel.js"></script>
<script type="text/javascript">
function chooseProduct(dom, childName, childId){
	var url = "${ctx}/admin/auth/permission/product?productCode="+$("#admin_auth_permission_tab_edit_form").find("input[id='productCode']").val();
	showSelectPage(dom,{url:url,title:'选择产品',selBox:'productBox', selList:"#permission_productSel .selrow",selId:"#productCode",selName:"#productName", callFun:function(){
		$(childName).val("请点击选择产品下对应权限组");
		$(childId).val("");
	}});
}
function choosePermissionGroup(dom){
	if(isNull($("#productCode").val())){
		alert("请先选择所属产品");
		return ;
	}
	var url = "${ctx}/admin/auth/permission/permissionGroup?productCode="+$("#admin_auth_permission_tab_edit_form").find("input[id='productCode']").val()+"&permissionGroupCode="+$("#admin_auth_permission_tab_edit_form").find("input[id='permissionGroupCode']").val();
	showSelectPage(dom,{url:url,title:'选择权限组',selBox:'permissionGroupBox', selList:"#permission_permissionGroupSel .selrow",selId:"#permissionGroupCode",selName:"#permissionGroupName"});
}
</script>

<form:form id="admin_auth_permission_tab_edit_form" method="put" action="${ctx}/admin/auth/permission/${permission.id}" modelAttribute="permission">
  <div class="up72_edit">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <input type="hidden" name="return_url" id="return_url" value="redirect:${ctx}/pages/admin/auth/permissionGroup/tab.jsp?id=${permissionGroupCode}&productCode=${productCode}" />
      <input type="hidden" id="id" name="id" value="${permission.id}"/>
      <input type="hidden" id="code" name="code" value="${permission.code}"/>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=Permission.ALIAS_NAME%>：</label></th>
        <td class="pb_frmtd"><form:input path="name" id="name" class="required input_txt" maxlength="30" />
          <span class="required">*</span><font color='red'>
          <form:errors path="name"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=Permission.ALIAS_PRODUCT_ID%>：</label></th>
        <td class="pb_frmtd"><input type="text" id="productName" readonly="true" value="${permission.product.name}" class="input_txt" onclick="chooseProduct(this, '#permissionGroupName', '#permissionGroupCode')" />
          <form:hidden path="productCode" id="productCode" />
          <span class="required">*</span><font color='red'>
          <form:errors path="productCode"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=Permission.ALIAS_PERMISSION_GROUP_ID%>：</label></th>
        <td class="pb_frmtd"><input type="text" id="permissionGroupName" readonly="true" value="${permission.permissionGroup.name}" class="input_txt" onclick="choosePermissionGroup(this)" />
          <form:hidden path="permissionGroupCode" id="permissionGroupCode" />
          <span class="required">*</span><font color='red'>
          <form:errors path="permissionGroupCode"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=Permission.ALIAS_URL%>：</label></th>
        <td class="pb_frmtd"><form:input path="url" id="url" class="required input_txt" maxlength="100" />
          <span class="required">*</span><font color='red'>
          <form:errors path="url"/>
          </font></td>
      </tr>
      <c:if test="${null!=permission && null!=permission.code && permission.code!=''}">
        <tr class="pb_frmtr">
          <th class="pb_frmth"><label><%=Permission.ALIAS_CODE%>:</label></th>
          <td class="pb_frmtd"><form:input path="code" id="code" readonly="readonly" class="input_txt" maxlength="100" />
            <span class="required">*</span><font color='red'>
            <form:errors path="code"/>
            </font></td>
        </tr>
      </c:if>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=Permission.ALIAS_ENABLED%>：</label></th>
        <td class="pb_frmtd"><form:radiobutton path="enabled" id="enabled" value="1" />
          启用
          <form:radiobutton path="enabled" id="enabled" value="0" />
          不启用 <span class="required">*</span><font color='red'>
          <form:errors path="enabled"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=Permission.ALIAS_TYPE%>：</label></th>
        <td class="pb_frmtd"><div>
            <form:radiobutton path="type" class="required" value="1" />
            菜单权限
            <label style="color:#FF0000">与用户角色相连，决定用户对某功能的菜单显示操作！</label>
          </div>
          <div>
            <form:radiobutton path="type" class="required" value="2" />
            操作权限
            <label style="color:#FF0000">与用户角色相连，决定用户对某功能的增、删、改、查操作！</label>
          </div>
          <div>
            <form:radiobutton path="type" class="required" value="3" />
            TAG权限
            <label style="color:#FF0000">与列表页面相连，于页面设置，决定列表页面所关联业务及操作功能！</label>
          </div>
          <span class="required"></span><font color='red'>
          <form:errors path="type"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <input type="hidden" value="1" name="sort" id="sort" />
        <th class="pb_frmth"><label><%=Permission.ALIAS_DESCRIPTION%>：</label></th>
        <td class="pb_frmtd"><form:textarea rols="8" cols="20" path="description" id="description" class="input_txt" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
          <font color='red'>
          <form:errors path="description"/>
          </font></td>
      </tr>
      <!--
        <th class="pb_frmth"><label><%=Permission.ALIAS_SORT%>：</label></th>
            <td class="pb_frmtd"><form:input path="sort" id="sort" class="required digits input_txt" maxlength="10" /><span class="required">*</span><font color='red'><form:errors path="sort"/></font></td>
        -->
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </div>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_permission_tab_edit_form").validate();	
	});
</script> 
