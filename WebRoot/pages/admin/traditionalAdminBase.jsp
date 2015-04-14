<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.bruce.common.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style }/global.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style }/structs_outter.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style }/skins/${skin }/skin_outter.css"/>" />

<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style }/structs_inner.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style }/skins/${skin }/skin_inner.css"/>" />
<%@include file="include/traditional/resource.jsp" %>
<up72:block name="head" />
<!--[if lt IE 7]>
<script src="${ctx}/scripts/ie6png.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, li, img, a');
    </script>
<![endif]-->
<script language=JavaScript>
<!--
function nowclock() {
	var today = new Date();
	var weekday;
	var displayDateStr;
	if(today.getDay()==0) weekday = " 星期日"
	if(today.getDay()==1) weekday = " 星期一"
	if(today.getDay()==2) weekday = " 星期二"
	if(today.getDay()==3) weekday = " 星期三"
	if(today.getDay()==4) weekday = " 星期四"
	if(today.getDay()==5) weekday = " 星期五"
	if(today.getDay()==6) weekday = " 星期六"
	document.fgColor = "000000";

	var hours = today.getHours();
	var minutes = today.getMinutes();
	var seconds = today.getSeconds();

	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var day = today.getDate();

	if (minutes < 10) minutes = "0" + minutes;
	if (seconds < 10) seconds = "0" + seconds;
	if (day < 10) day = "0" + day;
	if (month < 10) month = "0" + month;

	displayDateStr = year + "年" + month + "月" + day + "日" + " " + hours + ":" + minutes + " " + weekday;
	document.getElementById("currentTime").innerHTML = displayDateStr;
	setTimeout("nowclock()", 1000);
}
// -->
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="up72_dowrap">
    <!--top start-->
    <tr>
        <td valign="top" class="up72_dotop">
		<%//@include file="include/default/header.jsp" %>
        <div class="up72_top">
            <jsp:include page="/admin/menu/left" flush="true" />
            <h1 class="up72_logo"><a href="${ctx}/admin/index"><img src="${ctx}/styles/${style }/skins/${skin }/images/logo.png" alt="信息安全等级保护管理系统" title="信息安全等级保护管理系统"></a></h1>
        </div>
        </td>
    </tr>
    <!--top end-->
    <tr>
        <td valign="top" class="up72_domain">
            <!--main start-->      
            <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" class="up72_mtable">
                <tr>
                    <td class="up72_mleft">
                        <!--mainleft start-->
                        <div class="up72_domleft">
                            <%//include file="include/left.jsp" %>
                            <div class="up72_nav">
                              <ul>
                                <li id="leftMenu_${AUTH_PERMISSION_GROUP.id }"><a title="${AUTH_PERMISSION_GROUP.name }" href="javascript:;"><span class="navIco"><img src="${ctx}${AUTH_PERMISSION_GROUP.imgPath }"></span>${AUTH_PERMISSION_GROUP.name }</a>
                                  <ul class="up72_subNav">
                                  	<c:forEach items="${AUTH_PERMISSIONLIST}" var="perm" varStatus="permissionStatus">
                                    <li><a title="${perm.name }" onClick="$.forward('${ctx}${perm.url }',${perm.id });" id="leftSubMenu_${AUTH_PERMISSION_GROUP.id }_${perm.id }" href="javascript:;"><em><c:choose><c:when test="${permissionStatus.index > 10 }">${permissionStatus.index + 1}</c:when><c:otherwise>0${permissionStatus.index + 1}</c:otherwise></c:choose></em>${perm.name }</a></li>
                                    </c:forEach>
                                  </ul>
                                </li>
                              </ul>
                            </div>
                        </div> 
                        <!--mainleft end-->
                    </td>
                    <td class="up72_mright">
                        <!--mainright start-->            
                        <div class="up72_domright">
                            <div class="up72_navBar">
                                <div class="up72_info"><h4>欢迎 , 登录&nbsp;&nbsp;<span class="tnav_time" id="currentTime"></span></h4><a href="${ctx }/admin/index">首页</a> | <a href="${ctx }/admin/auth/member/reset/password">修改密码</a></div>
                                <h2><!-- jsp:include page="/admin/setCurrentProduct" flush="true" / --><!--&raquo;&nbsp;--><span id="navigation_bar"></span></h2>
                            </div>
                            <div id="up72_iframe" class="up72_content">
								<%@ include file="/common/messages.jsp"%>
                                <up72:block name="content" />
                            </div>
                        </div>            
                        <!--mainright end-->
                    </td>
                </tr>
            </table>      
            <!--main end-->      
        </td>
    </tr>
    <tr>
        <td valign="top" class="up72_dofooter">
            <!--foot start-->      
            <%@include file="/pages/admin/include/traditional/footer.jsp" %>      
            <!--foot end-->
        </td>
    </tr>
</table>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	window.parent.$("#navigation_bar").replaceWith("<span id=\"navigation_bar\" class=\"current\">"+$(".navBar").html()+"</span>");
	nowclock();

	var accordion_head = $('.up72_nav > ul > li > a'),
	accordion_body = $('.up72_nav > ul > li > .up72_subNav');
	
	accordion_body.slideUp('normal');
	$("#leftMenu_${AUTH_PERMISSION_GROUP.id} > a").next().stop(true,true).slideToggle('normal');
	accordion_head.removeClass('active');
	$("#leftMenu_${AUTH_PERMISSION_GROUP.id} > a").addClass('active');
	
	$("#leftSubMenu_${AUTH_PERMISSION_GROUP.id}_${AUTH_PERMISSION.id}").addClass("hover");
	
});
</script>
</body>
</html>