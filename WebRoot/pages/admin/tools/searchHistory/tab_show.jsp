<%@page import="com.bruce.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=SearchHistory.ALIAS_USER_ID%>：</th>
      <td><c:out value='${searchHistory.userId}'/></td>
      <th><%=SearchHistory.ALIAS_USER_NAME%>：</th>
      <td><c:out value='${searchHistory.userName}'/></td>
    </tr>
    <tr>
      <th><%=SearchHistory.ALIAS_TYPE%>：</th>
      <td><c:out value='${searchHistory.type}'/></td>
      <th><%=SearchHistory.ALIAS_KEY_WORDS%>：</th>
      <td><c:out value='${searchHistory.keyWords}'/></td>
    </tr>
    <tr>
      <th><%=SearchHistory.ALIAS_ADD_TIME%>：</th>
      <td><c:out value='${searchHistory.addTime}'/></td>
    </tr>
  </table>
</div>
