<%@page import="com.bruce.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<form:form id="admin_lucene_searchHistory_tab_edit_form" method="put" action="${ctx}/admin/lucene/searchHistory/${searchHistory.id}" modelAttribute="searchHistory">
  <div class="up72_edit">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <input type="hidden" id="id" name="id" value="${searchHistory.id}"/>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=SearchHistory.ALIAS_USER_ID%>：</label></th>
        <td class="pb_frmtd"><form:input path="userId" id="userId" class="digits input_txt" maxlength="19" />
          <font color='red'>
          <form:errors path="userId"/>
          </font></td>
        <th class="pb_frmth"><label><%=SearchHistory.ALIAS_USER_NAME%>：</label></th>
        <td class="pb_frmtd"><form:input path="userName" id="userName" class="input_txt" maxlength="50" />
          <font color='red'>
          <form:errors path="userName"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=SearchHistory.ALIAS_TYPE%>：</label></th>
        <td class="pb_frmtd"><form:input path="type" id="type" class="digits input_txt" maxlength="3" />
          <font color='red'>
          <form:errors path="type"/>
          </font></td>
        <th class="pb_frmth"><label><%=SearchHistory.ALIAS_KEY_WORDS%>：</label></th>
        <td class="pb_frmtd"><form:input path="keyWords" id="keyWords" class="input_txt" maxlength="200" />
          <font color='red'>
          <form:errors path="keyWords"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=SearchHistory.ALIAS_ADD_TIME%>：</label></th>
        <td class="pb_frmtd"><form:input path="addTime" id="addTime" class="required digits input_txt" maxlength="19" />
          <span class="required">*</span><font color='red'>
          <form:errors path="addTime"/>
          </font></td>
      </tr>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </div>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_lucene_searchHistory_tab_edit_form").validate();	
	});
</script> 
