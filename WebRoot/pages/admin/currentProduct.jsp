<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<a href="${ctx}/admin/goProduct/${currentProduct.id}" class="">${currentProduct.name }</a>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
  	$(".up72_folders li a").menuMouse({selCss:"navover",overCss:"#",index:"${ctx}/admin/goProduct/${currentProduct.id}"});
})
</script> 