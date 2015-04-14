<%@ page contentType="text/html;charset=UTF-8"%>



<div class="up72_dotop skin_dotop"><!--<c:if test="${null == AUTH_PRODUCT}"> style="height:80px;"</c:if>-->
  <div class="up72_top">
    <div class="up72_tnav">
      <div>
        <jsp:include page="/admin/menu/left" flush="true" />
      </div>
    </div>
    <div class="up72_tbg"> &nbsp; </div>
    <c:if test="${null != AUTH_PRODUCT}"> 
      <!--menu-->
      <div class="up72_subnav" style="display: none;">
        <h2><a href="#${AUTH_PRODUCT.id}">${AUTH_PRODUCT.name}</a><span class="px18 bold"><b class="hei">·</b>${AUTH_PERMISSION_GROUP.name}</span></h2>
      </div>
    </c:if>
  </div>
</div>
