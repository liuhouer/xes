<%@page import="com.up72.auth.model.*" %>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<div class="search mainHead">
  <table cellspacing="0" cellpadding="0" border="0" width="100%">
    <tr>
      <td><div class="op_area">
          <table cellspacing="2" cellpadding="0" border="0" width="100%">
            <tr>
              <td class="functop">&nbsp;
			  
			  </td>
            </tr>
            <tr>
              <td class="func"><a onclick="show('${ctx}/admin/auth/permission/new','<%=Permission.TABLE_ALIAS%>添加',600)" href="javascript:;" class="sysiconBtn addorder">添加</a></td>
            </tr>
          </table>
        </div></td>
      <td><div class="op_area">
          <table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
              <td class="functop"><h3>默认操作区</h3></td>
            </tr>
            <tr>
              <td class="func"><span  onclick="doRestBatchDelete('${ctx}/admin/auth/permission','items',document.forms.admin_auth_permission_page_form)" class="sysiconBtn delete">删除</span><a  onclick="showConfirm('${ctx}/pages/admin/auth/permission/batch_edit.jsp','批量编辑',500,300,function(){alert('编辑成功')},function(){alert('取消编辑')})"  class="sysiconBtn edit">批量编辑</a></td>
            </tr>
          </table>
        </div></td>
      <td><div class="op_area">
          <table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
              <td class="functop"><h3>导入导出</h3></td>
            </tr>
            <tr>
              <td class="func"><span style="padding-left:4px;" class="sysiconBtn arrow_down" onclick="$('#export_button_rel').toggle();bindCloseAction(['#export_button_rel']);">导出</span> 
                <!--导出层-->
                <div style="display:none;" id="export_button_rel" class="dropMenu"><span class="menuitem">csv-逗号分隔的文本文件...</span><span class="menuitem">txt-制表符分隔的文本文件...</span><span class="menuitem">xls-Excel文件...</span></div>
                  <div class="up72_submit">
 					 <div title="导入" class="btn btn_subDown" ><input type="button" onclick="exportPermission()" value="导入"></div>
  					 <div title="导出" class="btn btn_subDown" ><input type="button" onclick="" value="导出"></div>
 			     </div>
                <!--end导出层-->
			  </td>
            </tr>
          </table>
        </div></td>
      <td><!--filter-->
        
        <div class="op_area">
          <table>
            <tr>
              <td class="functop"><h3>查询<span id="advance_search_button" rel="#search_button_rel" class="prepend_1 arrow_down lnk" onclick="$('#search_button_rel').toggle();bindCloseAction(['#search_button_rel']);">高级查询</span></h3></td>
            </tr>
            <tr>
              <td class="func">
			 
                  <!--高级查询层-->
                  <div id="search_button_rel" style="display:none; position:absolute;" class="finder_filter_pos">
                    <div class="finder_filter_body">
                    <!--  <table>
						<tr>	
							<td class="tdLabel"><%=Permission.ALIAS_PERMISSION_GROUP_ID%>：</td>		
							<td>
								<input value="${query.permissionGroupCode}" id="permissionGroupCode" name="permissionGroupCode" maxlength="19"  class="digits "/></td>
							<td class="tdLabel"><%=Permission.ALIAS_TYPE%>：</td>		
							<td>
								<input value="${query.type}" id="type" name="type" maxlength="10"  class="digits "/></td>
						</tr>	
						<tr>	
							<td class="tdLabel"><%=Permission.ALIAS_NAME%>：</td>		
							<td>
								<input value="${query.name}" id="name" name="name" maxlength="30"  class=""/></td>
							<td class="tdLabel"><%=Permission.ALIAS_DESCRIPTION%>：</td>		
							<td>
								<input value="${query.description}" id="description" name="description" maxlength="100"  class=""/></td>
						</tr>	
						<tr>	
							<td class="tdLabel"><%=Permission.ALIAS_URL%>：</td>		
							<td>
								<input value="${query.url}" id="url" name="url" maxlength="100"  class="url "/></td>
							<td class="tdLabel"><%=Permission.ALIAS_ENABLED%>：</td>		
							<td>
								<input value="${query.enabled}" id="enabled" name="enabled" maxlength="3"  class="digits "/></td>
						</tr>	
						<tr>	
							<td class="tdLabel"><%=Permission.ALIAS_SORT%>：</td>		
							<td>
								<input value="${query.sort}" id="sort" name="sort" maxlength="10"  class="digits "/></td>
						</tr>	
				 </table> -->
				 
				 
				  <table>
						<tr>	
							<td class="tdLabel">
							<select id="searchColumn" name="searchColumn" onchange="syncValue(this,'#searchColumnSel')">
								 <option value="permissionGroupCode" <c:if test="${'permissionGroupCode' eq query.searchColumn}"> selected</c:if>><%=Permission.ALIAS_PERMISSION_GROUP_ID%></option>
								 <option value="name" <c:if test="${'name' eq query.searchColumn}"> selected</c:if>><%=Permission.ALIAS_NAME%></option>
								 <option value="description" <c:if test="${'description' eq query.searchColumn}"> selected</c:if>><%=Permission.ALIAS_DESCRIPTION%></option>
								 <option value="url" <c:if test="${'url' eq query.searchColumn}"> selected</c:if>><%=Permission.ALIAS_URL%></option>
								 <option value="enabled" <c:if test="${'enabled' eq query.searchColumn}"> selected</c:if>><%=Permission.ALIAS_ENABLED%></option>
								 <option value="sort" <c:if test="${'sort' eq query.searchColumn}"> selected</c:if>><%=Permission.ALIAS_SORT%></option>
								 <option value="type" <c:if test="${'type' eq query.searchColumn}"> selected</c:if>><%=Permission.ALIAS_TYPE%></option>
                          </select>：</td>		
							<td>
								<input id="searchColumnValue" value="${query.searchColumnValue}" onkeyup="syncValue(this,'#searchColumnSelValue')" class="keywords" name="searchColumnValue" style="width:160px;">
							</td>
						</tr>	
				 </table> 
				 <input type="submit" class="sysiconBtnNoIcon"  value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/auth/permission'"/>
                    </div>
                  </div>
                  <!--end高级查询层-->
                  
                  <div class="finder_filter_active">
                    <table cellspacing="0" cellpadding="0" border="0" width="100%">
                      <tr>
                        <td><select id="searchColumnSel" name="searchColumnSel"  onchange="syncValue(this,'#searchColumn')">
								 <option <c:if test="${'permissionGroupCode' eq query.searchColumn}"> selected</c:if> value="permissionGroupCode"><%=Permission.ALIAS_PERMISSION_GROUP_ID%></option>
								 <option <c:if test="${'name' eq query.searchColumn}"> selected</c:if> value="name"><%=Permission.ALIAS_NAME%></option>
								 <option <c:if test="${'description' eq query.searchColumn}"> selected</c:if> value="description"><%=Permission.ALIAS_DESCRIPTION%></option>
								 <option <c:if test="${'url' eq query.searchColumn}"> selected</c:if> value="url"><%=Permission.ALIAS_URL%></option>
								 <option <c:if test="${'enabled' eq query.searchColumn}"> selected</c:if> value="enabled"><%=Permission.ALIAS_ENABLED%></option>
								 <option <c:if test="${'sort' eq query.searchColumn}"> selected</c:if> value="sort"><%=Permission.ALIAS_SORT%></option>
								 <option <c:if test="${'type' eq query.searchColumn}"> selected</c:if> value="type"><%=Permission.ALIAS_TYPE%></option>
                          </select></td>
                        <td><input id="searchColumnInputCreate" name="searchColumnSelValue" value="${query.searchColumnValue}" onkeyup="syncValue(this,'#searchColumnValue')" class="keywords"  style="width:160px;"></td>
                        <td><input type="submit" class="sysiconBtnNoIcon"  value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/auth/permission'"/></td>
                      </tr>
                    </table>
                  </div>
                </td>
            </tr>
          </table>
        </div></td>
    </tr>
  </table>
</div>
<script type="text/javascript">
$("#searchColumnInputCreate").attr("name",$("#searchColumn").val());
function exportPermission(){
	show("${ctx}/admin/auth/permission/exportPerm","导出计划",600)});
}
</script>