<%@page import="com.up72.auth.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.Enumeration"%>
<%@page import="com.up72.auth.member.model.AuthUser"%>
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
              <td class="func"><a onclick="show('${ctx}/admin/auth/member/new','<%=AuthUser.TABLE_ALIAS%>添加',600)" href="javascript:;" class="sysiconBtn addorder">添加</a></td>
            </tr>
          </table>
        </div></td>
      <td><div class="op_area">
          <table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
              <td class="functop"><h3>默认操作区</h3></td>
            </tr>
            <tr>
              <td class="func">
              <span  onclick="doRestEdit({confirmMsg:'确认删除选中的记录到回收站吗？',url:'${ctx}/admin/auth/member/recycleDelete',batchBox:'items',boxCon:'.pb_main',form:'#admin_auth_member_page_form',method:'delete'})" class="sysiconBtn delete">删除</span>
              <!--span  onclick="doRestEdit({confirmMsg:'确认删除选中的记录吗？',url:'${ctx}/admin/auth/member',batchBox:'items',boxCon:'.pb_main',form:'#admin_auth_member_page_form',method:'delete'})" class="sysiconBtn delete">彻底删除</span-->
             <a href="${ctx}/admin/auth/member/recycle"  class="sysiconBtn">回收站</a>
              <a href="javascript:" onclick="doRestBatchEdit({url:'/pages/admin/auth/member/batch_edit.jsp',batchBox:'items',form:'#admin_auth_member_batchEdit_form'})"  class="sysiconBtn edit">批量编辑</a>
             
              
              </td>
            </tr>
          </table>
        </div></td>
      <td><div class="op_area">
          <table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
              <td class="functop"><h3>导入导出</h3></td>
            </tr>
            <tr>
              <td class="func"><span style="padding-left:4px;" class="sysiconBtn arrow_down" onclick="$('#export_button_rel').toggle();">导出</span> 
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
              <td class="functop"><h3>查询<span id="advance_search_button" rel="#search_button_rel" class="prepend_1 arrow_down lnk" onclick="$('#search_button_rel').toggle();">高级查询</span></h3></td>
            </tr>
            <tr>
              <td class="func">
			 
                  <!--高级查询层-->
                  <div id="search_button_rel" style="display:none; position:absolute;" class="finder_filter_pos">
                    <div class="finder_filter_body">
				  <table>
						<tr>	
							<td class="tdLabel">
							<select id="searchColumn" name="searchColumn" onchange="syncValue(this,'#searchColumnSel')">
								 <option value="userName" <c:if test="${'userName' eq query.searchColumn}"> selected</c:if>><%=AuthUser.ALIAS_USER_NAME%></option>
								 <option value="nickName" <c:if test="${'nickName' eq query.searchColumn}"> selected</c:if>><%=AuthUser.ALIAS_NICK_NAME%></option>
								 <option value="email" <c:if test="${'email' eq query.searchColumn}"> selected</c:if>><%=AuthUser.ALIAS_EMAIL%></option>
								 <option value="mobile" <c:if test="${'mobile' eq query.searchColumn}"> selected</c:if>><%=AuthUser.ALIAS_MOBILE%></option>
                          </select>：</td>		
							<td>
								<input id="searchColumnValue" value="${query.searchColumnValue}" onkeyup="syncValue(this,'#searchColumnInputCreate')" class="keywords" name="searchColumnValue" style="width:160px;">
							</td>
						</tr>	
						<tr>
							<td class="tdLabel">性别</td>
							<td><input type="radio" name="gender" value="0" <c:if test="${query.gender==0}"> checked="checked"</c:if>/>保密&nbsp;&nbsp;<input type="radio" name="gender" value="1" <c:if test="${query.gender==1}"> checked="checked"</c:if>/>男&nbsp;&nbsp;<input type="radio" name="gender" value="2" <c:if test="${query.gender==2}"> checked="checked"</c:if>/>女&nbsp;&nbsp;<input type="radio" name="gender" value="" />所有</td>
						</tr>
						<tr>
							<td class="tdLabel">用户类型</td>
							<td><input type="radio" name="code" value="1"<c:if test="${query.code==1}"> checked="checked"</c:if> />用户&nbsp;&nbsp;<input type="radio" name="code" value="2" <c:if test="${query.code==2}"> checked="checked"</c:if>/>管理员&nbsp;&nbsp;<input type="radio" name="code" value="5" <c:if test="${query.code==5}"> checked="checked"</c:if>/>系统管理员&nbsp;&nbsp;<input type="radio" name="code" value="" />所有</td>
						</tr>
						<tr>
							<td class="tdLabel">手机验证</td>
							<td><input type="radio" name="mobileValidate" value="0" <c:if test="${query.mobileValidate==0}"> checked="checked"</c:if>/>未认证&nbsp;&nbsp;<input type="radio" name="mobileValidate" value="1" <c:if test="${query.mobileValidate==1}"> checked="checked"</c:if>/>已认证&nbsp;&nbsp;<input type="radio" name="mobileValidate" value="" />所有</td>
						</tr>
						<tr>
							<td class="tdLabel">Email是否可见</td>
							<td><input type="radio" name="emailVisible" value="0" <c:if test="${query.emailVisible==0}"> checked="checked"</c:if>/>保密&nbsp;&nbsp;<input type="radio" name="emailVisible" value="1" <c:if test="${query.emailVisible==1}"> checked="checked"</c:if>/>可见&nbsp;&nbsp;<input type="radio" name="emailVisible" value="" />所有</td>
						</tr>
				 </table> 
				 <input type="submit" class="sysiconBtnNoIcon"  value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/auth/member'"/>
                    </div>
                  </div>
                  <!--end高级查询层-->
                  
                  <div class="finder_filter_active">
                    <table cellspacing="0" cellpadding="0" border="0" width="100%">
                      <tr>
                        <td><select id="searchColumnSel" name="searchColumnSel"  onchange="syncValue(this,'#searchColumn')">
								 <option value="userName" <c:if test="${'userName' eq query.searchColumn}"> selected</c:if>><%=AuthUser.ALIAS_USER_NAME%></option>
								 <option value="nickName" <c:if test="${'nickName' eq query.searchColumn}"> selected</c:if>><%=AuthUser.ALIAS_NICK_NAME%></option>
								 <option value="email" <c:if test="${'email' eq query.searchColumn}"> selected</c:if>><%=AuthUser.ALIAS_EMAIL%></option>
								 <option value="mobile" <c:if test="${'mobile' eq query.searchColumn}"> selected</c:if>><%=AuthUser.ALIAS_MOBILE%></option>
                          </select></td>
                        <td><input id="searchColumnInputCreate" name="searchColumnSelValue" value="${query.searchColumnValue}" onkeyup="syncValue(this,'#searchColumnValue')" class="keywords"  style="width:160px;"></td>
                        <td><input type="submit" class="sysiconBtnNoIcon"  value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/auth/member'"/></td>
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