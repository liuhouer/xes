<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<up72:override name="head">
<script type="text/javascript">
	$(document).ready(function(){
		$("img.lazy").lazyload();
		YZYPROFILE = ${jsondata};
	});
</script>
</up72:override>
<up72:override name="content">
<div class="main">
    <c:forEach items="${eventList.result}" var="event" varStatus="status">
		<%@ include file="./include/eventItem.jsp"%>
    </c:forEach>
</div>
</up72:override>
<%@include file="pageBase.jsp"%>