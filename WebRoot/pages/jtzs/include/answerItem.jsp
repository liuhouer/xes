<%@ page contentType="text/html;charset=UTF-8"%>
<div class="con gradual">
    <div class="conr display">
        <div class="cleft"><img src="${ctx}/images/defaultM.png" class="lazy" data-original="${ctx}${answer.teacher.imgPath}" onerror="DefaultSamllHandPic(this)"></div>
        <div class="cright">
            <div class="tops">
                <div class="lef">
                  <span class="name">${answer.teacher.nickName }</span>
                  <span class="time">${answer.answerTimeStr }</span>
                </div>
            </div>
        </div>
	</div>
    <div class="conr display">
        <div class="neir">
            <span class="nr w100">解答：<c:out value="${answer.content }" /></span>
        </div>
        <div class="neir">
            <span class="tu m4"><img src="${ctx}/images/nipic.png" showImg="1" class="lazy" data-original="${ctx}${answer.imgPath}" onerror="ErrNoImg(this)"></span>
        </div>
        <!-- 专家解答 -->
        <c:if test="${question.sourceType==2}">
	        <div class="neir">
	            <div class="solving">解答思路：${answer.ider }</div>
	        </div>
        </c:if>
    </div>    
</div>