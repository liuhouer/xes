<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../../common/taglibs.jsp" %>
<up72:override name="head">
<script type="text/javascript">
	$(document).ready(function(){
		$("span[stopTime]").each(function(i){
			innerendtime(this.id.replace("times",""),$(this).attr("stopTime"));
		});
		givetime();
		YZYPROFILE = ${jsondata};
		$("img.lazy").lazyload();
	});
</script>
</up72:override>
<up72:override name="content">
	<div class="main" id="jsonPageId">
		<c:if test="${!empty page.result && fn:length(page.result)>0}">
	        <c:forEach items="${page.result}" var="item" varStatus="status">
	        	<c:set value="${item.question}" var="question" />
				<div class="display w100 border_top"></div>
				<%@ include file="./include/questionItem.jsp"%>
			</c:forEach>
		</c:if>
	</div>
</up72:override>
<%@include file="pageBase.jsp"%>