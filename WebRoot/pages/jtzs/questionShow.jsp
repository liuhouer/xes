<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../../common/taglibs.jsp" %>
<up72:override name="head">
<script type="text/javascript" >
	$(document).ready(function(){
		$("span[stopTime]").each(function(i){
			innerendtime(this.id.replace("times",""),$(this).attr("stopTime"));
		});
		YZYPROFILE = ${jsondata};
		givetime();
		$("img.lazy").lazyload();
	});
</script>
</up72:override>
<up72:override name="content">
	<div class="main">
		<%@ include file="./include/questionItem.jsp"%>
		<div class="display w100 border_top lfloat m10"></div>
		<c:if test="${question.status==0 && question.isQuit==0 && question.studentId == studentId}">
            <div class="w100 auto center display bf0f0f0 lh3">
                <div class="neir">
                    <span class="nr w100 bold">正在安排适合的老师为您解答，请耐心等候</span>
                </div>
            </div>
			<div class="display w100 border_bottom lfloat"></div>
			<div class="w92 auto">
			    <div class="neir m10">
			    	<input id="${question.id}" class="giveup_bg pointer" type="button" value="放弃问题">
			    </div>
			    <div class="giveup_bgb f125 display">&nbsp;</div>
			</div> 
		</c:if>
		<c:if test="${question.status==1 && question.isQuit==0 && question.studentId == studentId}">
			<div class="w100 auto center display bf0f0f0 lh3">
			    <div class="neir">
			        <span class="nr w100 bold">老师正在为您作答，请耐心等候</span>
			    </div>
			</div>
			<div class="display w100 border_bottom lfloat"></div>
		</c:if>
		
		<c:if test="${!empty answer && answer.id > 0}">
			<%@ include file="./include/answerItem.jsp"%>
			<div id="jsonPageId">
				<c:if test="${!empty commentPage && fn:length(commentPage.result)>0}">
				 	<c:forEach items="${commentPage.result}" var="comment" varStatus="status">
						<%@ include file="./include/commentItem.jsp"%>
					</c:forEach>
			    </c:if>
		    </div>
		</c:if>
	</div>
</up72:override>
<%@include file="pageBase.jsp"%>