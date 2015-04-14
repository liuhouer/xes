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
		<div class="w100 display pt4">
		    <ul id="myhome" name="tags">
		    	<c:if test="${commentType==0}">
			        <li><a href="${ctx}/jtzs/teacher/myCommentList?teacherId=${teacherId}&commentType=1">满意</a></li>
			        <li class="selectmyhome"><a href="${ctx}/jtzs/teacher/myCommentList?teacherId=${teacherId}&commentType=0">不满意</a></li>
		    	</c:if>
		    	<c:if test="${commentType!=0}">
			        <li class="selectmy2"><a href="${ctx}/jtzs/teacher/myCommentList?teacherId=${teacherId}&commentType=1">满意</a></li>
			        <li><a href="${ctx}/jtzs/teacher/myCommentList?teacherId=${teacherId}&commentType=0">不满意</a></li>
		    	</c:if>
		    </ul>
		</div>    
		<c:if test="${!empty page && fn:length(page.result)>0}">
	        <c:forEach items="${page.result}" var="comment" varStatus="status">
				<%@ include file="./include/commentItem.jsp"%>
	        </c:forEach>
	    </c:if>
	</div>
</up72:override>
<%@include file="pageBase.jsp"%>