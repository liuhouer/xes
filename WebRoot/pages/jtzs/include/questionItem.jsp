<%@ page contentType="text/html;charset=UTF-8"%>
<div class="con gradual" id="status=${question.status}_id=${question.id}">
    <div class="conr display">
        <div class="cleft">
        	<img src="${ctx}/images/defaultM.png" class="lazy" data-original="${ctx}${question.student.imgPath}" onerror="DefaultSamllHandPic(this)" />
        </div>
        <div class="cright">
            <div class="tops">
                <div class="lef">
                  <span class="name"><c:out value="${question.student.nickName}" /></span>
                  <span class="time">${question.addTimeStr}</span>
                </div>
                <div class="rig">
                	<c:if test="${type=='tea' || type=='exp' }">
	                	<c:if test="${question.sourceType==0}">
		                	<span class="teacher">学生提问</span>
	                	</c:if>
	                	<c:if test="${question.sourceType==1}">
		                	<span class="teacher">专家作答</span>
	                	</c:if>
	                	<c:if test="${question.sourceType==2}">
		                	<span class="teacher">老师放弃</span>
	                	</c:if>
                	</c:if>
					<c:if test="${question.status!=2}">
	                  	<input name="" class="noanswer m10" type="button" value="未解答">
	                </c:if>
	                <c:if test="${question.status==2}">
            	    	<input name="" class="answer m10" type="button" value="已解答">
	                </c:if>
                </div>
            </div>
        </div>
	</div>
    <div class="conr display">
        <div class="neir">
            <span class="nr w100">
            	问题：<c:out value="${question.content}" />
            </span>
        </div>
        <div class="neir">
            <span class="tu m4">
            	<img src="${ctx}/images/nipic.png" showImg="1" class="lazy" data-original="${ctx}${question.imgPath}" onerror="ErrNoImg(this)" />
            </span>
        </div>
        <div class="neir">
            <div class="bq">
            	<img src="${ctx}/pages/jtzs/images/icon_label.png" />
            	<span class="f333">${question.grade.name }</span>
            	<span class="f666">${question.subject.name }</span>
            </div>
            <c:if test="${question.status!=2 && (type=='tea' || type=='exp' || type=='my')}">
            	<div class="manyi">
            		<img src="${ctx}/pages/jtzs/images/icon_clock.png" />距解题完成还有
            		<span class="ff00 f14" id="times${question.id}" stopTime="${question.stopTime}">00分00秒</span>
            	</div>
            </c:if>
            <c:if test="${type=='my' && question.status==2222}">
               <div class="manyis"><img src="${ctx}/pages/jtzs/images/icon_unclear.png">问题不清晰</div>
            </c:if>
            <c:if test="${question.status==2}">
	            <div class="manyis">
	                <ul>
	                 <li class="bg_blue">
	                 	<span class="w30"><img src="${ctx}/pages/jtzs/images/icon_manyi.png"></span>
	                 	<font>${question.satisfiedCount}</font>
	                 </li>
	                 <li class="bg_blue">
	                 	<span class="w30"><img src="${ctx}/pages/jtzs/images/icon_bumanyi.png"></span>
	                 	<font>${question.unsatisfiedCount}</font>
	                 </li>
	                 <li class="bg_green">
	                 	<span class="w30"><img src="${ctx}/pages/jtzs/images/icon_liulan.png"></span>
	                 	<font>${question.pageViewCount}</font>
	                 </li>
	                </ul>                
	            </div>
            </c:if>
        </div>            
    </div>
</div>