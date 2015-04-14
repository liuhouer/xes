<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<jsp:useBean id="currentPageNumber" type="java.lang.Integer" scope="request"/>
<pg:first export="firstPageUrl=pageUrl" unless="current">
<a href="<%= firstPageUrl %>">首页&nbsp;&nbsp;&nbsp;&nbsp;</a>
</pg:first>
<pg:prev export="prevPageUrl=pageUrl">
<a href="<%= prevPageUrl %>">上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>
</pg:prev>
<pg:pages><%
  if (pageNumber == currentPageNumber) {
    %> [<%= pageNumber %>]&nbsp;&nbsp;&nbsp;&nbsp;<%
  } else {
    %> <a href="<%= pageUrl %>">[<%= pageNumber %>]</a>&nbsp;&nbsp;&nbsp;&nbsp; <%
  }
%></pg:pages>
<pg:next export="nextPageUrl=pageUrl">
<a href="<%= nextPageUrl %>">下一页&nbsp;&nbsp;&nbsp;&nbsp;</a>
</pg:next>
<pg:last export="lastPageUrl=pageUrl" unless="current">
<a href="<%= lastPageUrl %>">尾页&nbsp;&nbsp;&nbsp;&nbsp;</a>
</pg:last>

