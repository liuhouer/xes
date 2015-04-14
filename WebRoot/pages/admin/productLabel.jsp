<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<c:forEach items="${productList}" var="item" varStatus="status">
<li><a href="${ctx}/admin/goProduct/${item.id}" class="" title="${item.name }" ><span class="nav_ico${status.index}"></span>${item.name }</a></li>
</c:forEach><!-- <a href="#">更多▼</a> class="navover" -->
 