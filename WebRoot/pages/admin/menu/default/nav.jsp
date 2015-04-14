<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<c:forEach items="${productList}" var="item" varStatus="status" end="16">
<a href="${ctx}/admin/goProduct/${item.id}" class="space<c:if test="${item.id == AUTH_PRODUCT.id}"> up72_tnav_on</c:if> dock-item" title="${item.name }" ><span class="nav_ico${status.index}">${item.name }</span><img src="<c:choose><c:when test="${null!=item.imgPath && item.imgPath!=''}">${ctx}${item.imgPath}</c:when><c:otherwise>${ctx}/styles/default/skins/deepblue/images/icon_default.png</c:otherwise></c:choose>" /></a>
<c:if test="${!status.last}">
</c:if>
</c:forEach>
<!--<c:if test="${null!=productList && fn:length(productList)>4}">
<a id="up72ProductMoreBtn" href="javascript:;" class="space_m_more space_m_more_on" style="position:absolute; z-index:999;">更多▼</a>
<div id="up72ProductMore" class="product-more">
<c:forEach items="${productList}" var="item" varStatus="status" begin="4">
<a href="${ctx}/admin/goProduct/${item.id}" class="" title="${item.name }" ><span class="nav_ico${status.index}"></span>${item.name }</a><br />
</c:forEach>
</div>
</c:if>-->
<script type="text/javascript">
	$("#up72ProductMoreBtn").hover(function(){
		$("#up72ProductMoreBtn").showFlow("#up72ProductMore",{
			left : 7,
			top : -2
		});
	},function(){
		
	});
	
	if(isNull($(".up72_tnav_on").attr("tagName"))){
		$(".space_m_more").addClass("up72_tnav_on");
	}
</script>
 