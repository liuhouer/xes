<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="wap_top">${channel.title }</div>
<c:choose>
<c:when test="${type eq 'list' && pager.compose eq '(0:0:10000:5000),(0:5000:5000:5000),(5000:5000:5000:5000)'}">
	<jsp:include page="c_3_100_50_50.jsp"></jsp:include>
</c:when>
<c:when test="${type eq 'list' && pager.compose eq '(0:0:5000:5000),(5000:0:5000:5000),(0:5000:10000:5000)'}">
	<jsp:include page="c_3_50_50_100.jsp"></jsp:include>
</c:when>
<c:when test="${type eq 'list' && pager.compose eq '(0:0:4000:5000),(4000:0:6000:5000),(0:5000:6000:5000),(6000:5000:4000:5000)'}">
	<jsp:include page="c_4_60_40_40_60.jsp"></jsp:include>
</c:when>
<c:when test="${type eq 'list' && pager.compose eq '(0:0:6000:5000),(6000:0:4000:5000),(0:5000:4000:5000),(4000:5000:6000:5000)'}">
	<jsp:include page="c_4_40_60_60_40.jsp"></jsp:include>
</c:when>
<c:when test="${type eq 'list' && pager.compose eq '(0:0:3333:5000),(3333:0:3333:5000),(6666:0:3333:5000),(0:5000:5000:5000),(5000:5000:5000:5000)'}">
	<jsp:include page="c_5_30_30_30_50_50.jsp"></jsp:include>
</c:when>

<c:when test="${type eq 'list' && pager.compose eq '(0:0:5000:5000),(5000:0:5000:5000),(0:5000:3333:5000),(3333:5000:3333:5000),(6666:5000:3333:5000)'}">
	<jsp:include page="c_5_50_50_30_30_30.jsp"></jsp:include>
</c:when>
</c:choose>
 <div class="page"><a href="javascript:lastNum();" ><img src="${ctx }/pages/admin/qmt/preview/compose/images/br-back.png">上一页</a><a href="javascript:nextNum();"  class="pages"><img src="${ctx }/pages/admin/qmt/preview/compose/images/br-forward.png">下一页</a></div>
</div>
</div>
