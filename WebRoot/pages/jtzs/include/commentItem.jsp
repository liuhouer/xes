<%@ page contentType="text/html;charset=UTF-8"%>
<div class="con bgeee bore0e0e0">
    <div class="conr display">
        <div class="pleft"><img src="${ctx}/images/defaultM.png" class="lazy" data-original="${ctx}${comment.student.imgPath}" onerror="DefaultSamllHandPic(this)"></div>
        <div class="pright">
            <div class="tops">
                <div class="lef2">
                  <span class="name">${comment.student.nickName}</span><span class="time">${comment.addTimeStr}</span>
                </div>
                <div class="con2"><c:out value="${comment.content}" /></div>
                <div class="rig2 ff00 center">
	                <c:if test="${comment.isSatisfied==1}">
	                	<img src="${ctx}/pages/jtzs/images/hand.png">
	                </c:if>
                	<c:if test="${comment.isSatisfied!=1}">
	                	<img src="${ctx}/pages/jtzs/images/hand2.png">
                 	</c:if>
                </div>
            </div>
        </div>
    </div>    
</div>