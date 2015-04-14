<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.bruce.common.CommonConstants"%>
<%@page import="com.up72.base.BaseRestSpringController;"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="up72_menu_role" onclick="$(this).showFlow('#siteList',{left:-8,top:5});">
   	<span style="width:85px; padding-left:5px; float:left;" id="nowSiteName">
   	<c:if test="${currentSite != null}">
   		${currentSite.name}
   	</c:if>
   	<c:if test="${currentSite == null}">
   		切换站点
   	</c:if>
   	
   	</span>
   	<span class="role_current"><a href="#">&nbsp;</a></span>
 </div>
<div id="siteList" class="zhan" style="display: none;">
      <ul>
	    <c:forEach items="${siteTree}" var="t_cat">
		 <option value="${t_cat.id}" <c:if test="${t_cat.id == site.parentId}"> selected="selected"</c:if>>
		 	<li>
		 	<c:forEach begin="2" end="${t_cat.level}">&nbsp;&nbsp;</c:forEach>
		 	<c:if test="${t_cat.level > 1}">|-</c:if><a href="javascript:;" onclick="setSiteId('${t_cat.id}','${t_cat.name}');">${t_cat.name}</a>
		   </li>	
		   </option>
		</c:forEach>
	</ul>

</div>
<script type="text/javascript">
	    function setSiteId(siteId,siteName){
	        $.ajax({
				"url" : "${ctx}/admin/cms/site/changeSite",
				"type" : "post",
				"data" : "siteId="+siteId,
				"dataType" : "json",
				"success" : function (data){
				   if(data.status == "success"){
				   		$("#nowSiteName").html(""+siteName);
	    				$("#siteList").hide();
	    				window.location.reload();
				   }else if(data.status == "error"){
				   		alert("设置失败");
				   }
				}
			});
	    }  
</script>