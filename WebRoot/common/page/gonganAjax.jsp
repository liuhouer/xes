<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<jsp:useBean id="currentPageNumber" type="java.lang.Integer" scope="request"/>
<ul>
<pg:first export="firstPageUrl=pageUrl" unless="current">
  <li><a href="javascript:;" onclick="gongan_library_appraise_ajax('<%= firstPageUrl %>');">首页</a></li>
</pg:first>
<pg:prev export="prevPageUrl=pageUrl">
  <li><a href="javascript:;" onclick="gongan_library_appraise_ajax('<%= prevPageUrl %>');">上一页</a></li>
</pg:prev>
<pg:pages><%
  if (pageNumber == currentPageNumber) {
    %> <li class="current"><%= pageNumber %></li> <%
  } else {
    %> <li><a href="javascript:;" onclick="gongan_library_appraise_ajax('<%= pageUrl %>');"><%= pageNumber %></a></li> <%
  }
%></pg:pages>
<pg:next export="nextPageUrl=pageUrl">
  <li><a href="javascript:;" onclick="gongan_library_appraise_ajax('<%= nextPageUrl %>');">下一页</a></li>
</pg:next>
<pg:last export="lastPageUrl=pageUrl" unless="current">
  <li><a href="javascript:;" onclick="gongan_library_appraise_ajax('<%= lastPageUrl %>');">尾页</a></li>
</pg:last>
</ul>
