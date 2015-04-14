<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<up72:override name="head">
	<script type="text/javascript">
	$(document).ready(function(){
		$("span[stopTime]").each(function(i){
			innerendtime(this.id.replace("times",""),$(this).attr("stopTime"));
		});
		givetime();
		$("img.lazy").lazyload();
		YZYPROFILE = ${jsondata};
	});
</script>
</up72:override>
<up72:override name="content">
	<div class="main" id="jsonPageId">
		<div class="w100 display pt4">
			<ul id="myhome" name="tags">
				<c:if test="${questionStatus==2}">
					<li><a href="${ctx}/jtzs/student/myQuestionList?studentId=${studentId}&questionStatus=0">未解答</a></li>
					<li class="selectmyhome"><a href="${ctx}/jtzs/student/myQuestionList?studentId=${studentId}&questionStatus=2">已解答</a></li>
				</c:if>
		    	<c:if test="${questionStatus!=2}">
		    		<li class="selectmy2"><a href="${ctx}/jtzs/student/myQuestionList?studentId=${studentId}&questionStatus=0">未解答</a></li>
			        <li><a href="${ctx}/jtzs/student/myQuestionList?studentId=${studentId}&questionStatus=2">已解答</a></li>
		    	</c:if>
			</ul>
		</div>

		<c:if test="${!empty page.result && fn:length(page.result)>0}">
		 <c:forEach items="${page.result}" var="question" varStatus="status">
			<div class="display w100 border_top"></div>
			 <%@ include file="./include/questionItem.jsp"%>
		  </c:forEach>
		</c:if>
	</div>
</div>
</up72:override>
<%@include file="pageBase.jsp"%>