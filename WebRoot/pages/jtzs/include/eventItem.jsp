<%@ page contentType="text/html;charset=UTF-8"%>
<a href="${ctx}/jtzs/commonData/${event.id}/eventShow">
<div class="con">
    <div class="dates center ffff f125"><fmt:formatDate value="${event.sendTimeDate}" pattern="yyyy-MM-dd HH:mm"/></div>
    <div class="activity m2">
    	<div class="pic"><img src="${event.imgPath }">
        	<div class="hotnews_img_title"><c:out value="${event.title}" /></div>
        </div>
    	<div class="read">
    		<a>阅读全文</a>
    		<a><img src="${ctx}/pages/jtzs/images/icon.png"></div>
    </div>
</div>
</a>