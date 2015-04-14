<%@page import="com.bruce.model.*" %>
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
              <td class="func"><a onclick="show('${ctx}/admin/lucene/searchHistory/new/','<%=SearchHistory.TABLE_ALIAS%>添加',600)" href="javascript:;" class="sysiconBtn addorder">添加</a></td>
            </tr>
          </table>
        </div></td>
      <td><div class="op_area">
          <table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
              <td class="functop"><h3>默认操作区</h3></td>
            </tr>
            <tr>
              <td class="func"><span  onclick="doRestBatchDelete('${ctx}/admin/lucene/searchHistory/','items',document.forms.admin_lucene_searchHistory_page_form)" class="sysiconBtn delete">删除</span><a  onclick="showConfirm('${ctx}/pages/admin/lucene/searchHistory/batch_edit.jsp','批量编辑',500,300,function(){alert('编辑成功')},function(){alert('取消编辑')})"  class="sysiconBtn edit">批量编辑</a></td>
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
							<td class="tdLabel"><%=SearchHistory.ALIAS_USER_ID%>：</td>		
							<td>
								<input value="${query.userId}" id="userId" name="userId" maxlength="19"  class="digits "/></td>
							<td class="tdLabel"><%=SearchHistory.ALIAS_USER_NAME%>：</td>		
							<td>
								<input value="${query.userName}" id="userName" name="userName" maxlength="50"  class=""/></td>
						</tr>	
						<tr>	
							<td class="tdLabel"><%=SearchHistory.ALIAS_TYPE%>：</td>		
							<td>
								<input value="${query.type}" id="type" name="type" maxlength="3"  class="digits "/></td>
							<td class="tdLabel"><%=SearchHistory.ALIAS_KEY_WORDS%>：</td>		
							<td>
								<input value="${query.keyWords}" id="keyWords" name="keyWords" maxlength="200"  class=""/></td>
						</tr>	
						<tr>	
							<td class="tdLabel"><%=SearchHistory.ALIAS_ADD_TIME%>：</td>		
							<td>
								<input value="${query.addTime}" id="addTime" name="addTime" maxlength="19"  class="digits "/></td>
						</tr>	
				 </table> -->
				 
				 
				  <table>
						<tr>	
							<td class="tdLabel">
							<select id="searchColumn" name="searchColumn" onchange="syncValue(this,'#searchColumnSel')">
								 <option value="userId" <c:if test="${'userId' eq query.searchColumn}"> selected</c:if>><%=SearchHistory.ALIAS_USER_ID%></option>
								 <option value="userName" <c:if test="${'userName' eq query.searchColumn}"> selected</c:if>><%=SearchHistory.ALIAS_USER_NAME%></option>
								 <option value="type" <c:if test="${'type' eq query.searchColumn}"> selected</c:if>><%=SearchHistory.ALIAS_TYPE%></option>
								 <option value="keyWords" <c:if test="${'keyWords' eq query.searchColumn}"> selected</c:if>><%=SearchHistory.ALIAS_KEY_WORDS%></option>
								 <option value="addTime" <c:if test="${'addTime' eq query.searchColumn}"> selected</c:if>><%=SearchHistory.ALIAS_ADD_TIME%></option>
                          </select>：</td>		
							<td>
								<input id="searchColumnValue" value="${query.searchColumnValue}" onkeyup="syncValue(this,'#searchColumnSelValue')" class="keywords" name="searchColumnValue" style="width:160px;">
							</td>
						</tr>	
				 </table> 
				 <input type="submit" class="sysiconBtnNoIcon"  value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/lucene/searchHistory'"/>
                    </div>
                  </div>
                  <!--end高级查询层-->
                  
                  <div class="finder_filter_active">
                    <table cellspacing="0" cellpadding="0" border="0" width="100%">
                      <tr>
                        <td><select id="searchColumnSel" name="searchColumnSel"  onchange="syncValue(this,'#searchColumn')">
								 <option <c:if test="${'userId' eq query.searchColumn}"> selected</c:if> value="userId"><%=SearchHistory.ALIAS_USER_ID%></option>
								 <option <c:if test="${'userName' eq query.searchColumn}"> selected</c:if> value="userName"><%=SearchHistory.ALIAS_USER_NAME%></option>
								 <option <c:if test="${'type' eq query.searchColumn}"> selected</c:if> value="type"><%=SearchHistory.ALIAS_TYPE%></option>
								 <option <c:if test="${'keyWords' eq query.searchColumn}"> selected</c:if> value="keyWords"><%=SearchHistory.ALIAS_KEY_WORDS%></option>
								 <option <c:if test="${'addTime' eq query.searchColumn}"> selected</c:if> value="addTime"><%=SearchHistory.ALIAS_ADD_TIME%></option>
                          </select></td>
                        <td><input id="searchColumnInputCreate" name="searchColumnSelValue" value="${query.searchColumnValue}" onkeyup="syncValue(this,'#searchColumnValue')" class="keywords"  style="width:160px;"></td>
                        <td><input type="submit" class="sysiconBtnNoIcon"  value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/lucene/searchHistory/'"/></td>
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