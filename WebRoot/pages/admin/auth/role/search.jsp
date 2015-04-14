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
              <td class="func"><a onclick="show('${ctx}/admin/auth/role/new','<%=Role.TABLE_ALIAS%>添加',600)" href="javascript:;" class="sysiconBtn addorder">添加</a></td>
            </tr>
          </table>
        </div></td>
      <td><div class="op_area">
          <table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
              <td class="functop"><h3>默认操作区</h3></td>
            </tr>
            <tr>
              <td class="func"><span  onclick="doRestBatchDelete('${ctx}/admin/auth/role','items',document.forms.admin_auth_role_page_form)" class="sysiconBtn delete">删除</span><a  onclick="showConfirm('${ctx}/pages/admin/auth/role/batch_edit.jsp','批量编辑',500,300,function(){alert('编辑成功')},function(){alert('取消编辑')})"  class="sysiconBtn edit">批量编辑</a></td>
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
							<td class="tdLabel"><%=Role.ALIAS_WORK_GROUP_ID%>：</td>		
							<td>
								<input value="${query.workGroupId}" id="workGroupId" name="workGroupId" maxlength="19"  class="digits "/></td>
							<td class="tdLabel"><%=Role.ALIAS_DESCRIPTION%>：</td>		
							<td>
								<input value="${query.description}" id="description" name="description" maxlength="100"  class=""/></td>
						</tr>	
						<tr>	
							<td class="tdLabel"><%=Role.ALIAS_REMARK%>：</td>		
							<td>
								<input value="${query.remark}" id="remark" name="remark" maxlength="65535"  class=""/></td>
							<td class="tdLabel"><%=Role.ALIAS_ENABLED%>：</td>		
							<td>
								<input value="${query.enabled}" id="enabled" name="enabled" maxlength="3"  class="digits "/></td>
						</tr>	
						<tr>	
							<td class="tdLabel"><%=Role.ALIAS_ORGANIZATION_ID%>：</td>		
							<td>
								<input value="${query.organizationId}" id="organizationId" name="organizationId" maxlength="19"  class="digits "/></td>
						</tr>	
				 </table> -->
				 
				 
				  <table>
						<tr>	
							<td class="tdLabel">
							<select id="searchColumn" name="searchColumn" onchange="syncValue(this,'#searchColumnSel')">
								 <option value="workGroupId" <c:if test="${'workGroupId' eq query.searchColumn}"> selected</c:if>><%=Role.ALIAS_WORK_GROUP_ID%></option>
								 <option value="description" <c:if test="${'description' eq query.searchColumn}"> selected</c:if>><%=Role.ALIAS_DESCRIPTION%></option>
								 <option value="remark" <c:if test="${'remark' eq query.searchColumn}"> selected</c:if>><%=Role.ALIAS_REMARK%></option>
								 <option value="enabled" <c:if test="${'enabled' eq query.searchColumn}"> selected</c:if>><%=Role.ALIAS_ENABLED%></option>
								 <option value="organizationId" <c:if test="${'organizationId' eq query.searchColumn}"> selected</c:if>><%=Role.ALIAS_ORGANIZATION_ID%></option>
                          </select>：</td>		
							<td>
								<input id="searchColumnValue" value="${query.searchColumnValue}" onkeyup="syncValue(this,'#searchColumnSelValue')" class="keywords" name="searchColumnValue" style="width:160px;">
							</td>
						</tr>	
				 </table> 
				 <input type="submit" class="sysiconBtnNoIcon"  value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/auth/role'"/>
                    </div>
                  </div>
                  <!--end高级查询层-->
                  
                  <div class="finder_filter_active">
                    <table cellspacing="0" cellpadding="0" border="0" width="100%">
                      <tr>
                        <td><select id="searchColumnSel" name="searchColumnSel"  onchange="syncValue(this,'#searchColumn')">
								 <option <c:if test="${'workGroupId' eq query.searchColumn}"> selected</c:if> value="workGroupId"><%=Role.ALIAS_WORK_GROUP_ID%></option>
								 <option <c:if test="${'description' eq query.searchColumn}"> selected</c:if> value="description"><%=Role.ALIAS_DESCRIPTION%></option>
								 <option <c:if test="${'remark' eq query.searchColumn}"> selected</c:if> value="remark"><%=Role.ALIAS_REMARK%></option>
								 <option <c:if test="${'enabled' eq query.searchColumn}"> selected</c:if> value="enabled"><%=Role.ALIAS_ENABLED%></option>
								 <option <c:if test="${'organizationId' eq query.searchColumn}"> selected</c:if> value="organizationId"><%=Role.ALIAS_ORGANIZATION_ID%></option>
                          </select></td>
                        <td><input id="searchColumnInputCreate" name="searchColumnSelValue" value="${query.searchColumnValue}" onkeyup="syncValue(this,'#searchColumnValue')" class="keywords"  style="width:160px;"></td>
                        <td><input type="submit" class="sysiconBtnNoIcon"  value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/auth/role'"/></td>
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
</script>