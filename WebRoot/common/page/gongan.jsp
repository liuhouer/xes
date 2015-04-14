<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<jsp:useBean id="currentPageNumber" type="java.lang.Integer" scope="request"/>
<ul>
<pg:first export="firstPageUrl=pageUrl" unless="current">
  <li><a href="<%= firstPageUrl %>">首页</a></li>
</pg:first>
<pg:prev export="prevPageUrl=pageUrl">
  <li class="next"><a href="<%= prevPageUrl %>">上一页</a></li>
</pg:prev>
<pg:pages><%
  if (pageNumber == currentPageNumber) {
    %> <li class="current"><%= pageNumber %></li> <%
  } else {
    %> <li><a href="<%= pageUrl %>"><%= pageNumber %></a></li> <%
  }
%></pg:pages>
<pg:next export="nextPageUrl=pageUrl">
  <li class="next"><a href="<%= nextPageUrl %>">下一页</a></li>
</pg:next>
<pg:last export="lastPageUrl=pageUrl" unless="current">
  <li><a href="<%= lastPageUrl %>">尾页</a></li>
</pg:last>
</ul>
