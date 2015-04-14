<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>



<ul id="jsddm">
     <li> <a href="javascript:;" style="padding:0;"><img src="${ctx}/styles/skins/deepblue/images/logo_png.png" width="46" height="32" /></a>
        <ul>
          <li><a href="${ctx}/admin/deskTop">个人桌面</a></li>
          <li><a href="${ctx}/admin/auth/permission/goSysSet">系统设置</a></li>
          <li> <a href="${ctx}/admin/auth/member/home">个人设置</a> </li>
          <li> <a href="javascript:;">关于系统</a> </li>
          <li> <a href="${ctx}/admin/authUser/logout">退出管理</a> </li>
        </ul>
      </li>
      
	    <li style="_width:130px; _float:left; _padding-right:-50px;">
	    <div id="jiantou">
	    	<a style="background:none;*background:none;" <c:if test="${AUTH_PRODUCT.dashboardUrl != null }">href="${AUTH_PRODUCT.dashboardUrl}"</c:if> <c:if test="${AUTH_PRODUCT.dashboardUrl == null || AUTH_PRODUCT.dashboardUrl eq ''}">href="javascript:;"</c:if> >
	    		<c:if test="${AUTH_PRODUCT != null }">${AUTH_PRODUCT.name}</c:if>
	    		<c:if test="${AUTH_PRODUCT == null }">${pageName}</c:if>
	    	</a>
	        <c:if test="${AUTH_PRODUCT.aboutFlag}">
	            <ul>
	                <li><a href="javascript:;" onclick="show('${ctx}/admin/auth/productAbout/showProductAbout?productId=${AUTH_PRODUCT.code}','关于${AUTH_PRODUCT.name}',800)">关于${AUTH_PRODUCT.name}</a></li>
	            </ul>
	        </c:if>
	        </div><div id="arrow_r"></div>
	    </li>
  
</ul>
<!-- 
<ul id="nav" class="clearfix">   <div id="arrow_r"></div>
	<c:forEach items="${permissionGroupList}" var="pgroup" >
 	<li class="<c:if test="${AUTH_PERMISSION_GROUP.id == pgroup.id}">on_tlink</c:if> nav_top">
    	<a href="${ctx}/admin/permGroup/${pgroup.id}" title="${pgroup.name}" class="top_link"><span class="down">${pgroup.name}</span></a>
    	<ul class="sub">
    		<c:forEach items="${AUTH_PERMISSIONLIST}" var="perm" varStatus="status" >
		    	<li>
				 	<a href="javascript:;" onclick="$.forward('${ctx}${perm.url}',${perm.id});" class="<c:if test="${AUTH_PERMISSION.id == perm.id}">navover</c:if>" title="${perm.name}" >
				 		${perm.name}
				 	</a>
		        </li>
		    </c:forEach>
		    
            <li><a href="#" class="fly">Bristol</a>
                <ul>
                    <li><a href="#">Redland</a></li>
                    <li><a href="#">Hanham</a></li>
                    <li><a href="#">Eastville</a></li>
                </ul>
            </li>
		</ul>
    </li>
    </c:forEach>
</ul>
 -->
<script type="text/javascript">
	var permJson = '${permJson}';
	var html = "";
	$(permJson).each(function(i,group){
		var permissionGroupId = <c:out value="${AUTH_PERMISSION_GROUP.id}" default="100"/>;
		// on_tlink 
		if(group.id == permissionGroupId){
			html += "<li class='curr'>";
		}else{
			html += "<li>";
		}
		html += "<span><a href='${ctx}/admin/permGroup/"+group.id+"' title='"+group.name+"' >"+group.name+"</a></span>";
		
		html += "<ul>";
		$(group.permList).each(function(j,perm){
			html += "<li>";
			html += "<span><a href='javascript:;' onclick=\"$.forward('${ctx}"+perm.url+"',"+perm.id+");\" title='"+perm.name+"' >";
			html += perm.name;
			html += "</a></span>";
			html += "</li>";
		});
		html += "</ul>";
		
		html += "</li>";
	});
	$("#jsddm").append(html);
</script>
<script type="text/javascript">
	var timeout         = 500;
	var closetimer		= 0;
	var ddmenuitem      = 0;
	
	function jsddm_open()
	{	jsddm_canceltimer();
		jsddm_close();
		ddmenuitem = $(this).find('ul').eq(0).css('visibility', 'visible');}
	
	function jsddm_close()
	{	if(ddmenuitem) ddmenuitem.css('visibility', 'hidden');}
	
	function jsddm_timer()
	{	closetimer = window.setTimeout(jsddm_close, timeout);}
	
	function jsddm_canceltimer()
	{	if(closetimer)
		{	window.clearTimeout(closetimer);
			closetimer = null;}}
	
	$(document).ready(function()
	{	$('#jsddm > li').bind('mouseover', jsddm_open);
		$('#jsddm > li').bind('mouseout',  jsddm_timer);});
	
	document.onclick = jsddm_close;
 </script>
