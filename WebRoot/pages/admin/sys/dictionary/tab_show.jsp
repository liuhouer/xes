<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.sys.model.Dictionary"%>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=Dictionary.ALIAS_NAME%>：</th>
      <td><c:out value='${dictionary.name}'/></td>
    </tr>
    <tr>
      <th><%=Dictionary.ALIAS_REF_CODE%>：</th>
      <td><c:out value='${dictionary.refCode}'/></td>
    </tr>
     <tr>
      <th><%=Dictionary.ALIAS_DICTIONARY_ID%>：</th>
      <td><c:out value='${dictionary.dictionaryByDictionaryId.name}'/></td>
    </tr>
    <tr>
      <th><%=Dictionary.ALIAS_DESCRIPTION%>：</th>
      <td><c:out value='${dictionary.description}'/></td>
    </tr>
    <tr>
      <th><%=Dictionary.ALIAS_SORT%>：</th>
      <td><c:out value='${dictionary.sort}'/></td>
    </tr>
  </table>
  <div class="up72_submit">
  	  <div class="btn btn_sub" title="编辑"><input type="button" onclick="showPro('${ctx}/admin/sys/dictionary/${dictionary.id}/edit');"  value="编辑" /></div>
      <div class="btn btn_sub" title="删除"><input type="button" onclick="deletePro(${dictionary.id});"  value="删除" /></div>
    </div>
</div>
