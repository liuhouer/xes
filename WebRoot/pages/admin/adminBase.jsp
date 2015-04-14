<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.bruce.common.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理系统</title>
<jsp:include page="/pages/admin/include/getOutterStyleSkin.jsp"></jsp:include>
<%@include file="include/default/resource.jsp" %>
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
		<%@include file="include/default/header.jsp" %>
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
                            <div class="showHidden"><a id="hiddenLeftBtn" href="javascript:;" title="隐藏">&laquo;<!--&raquo;--></a></div>
                            <%//include file="include/left.jsp" %>
                            <jsp:include page="/admin/menu/left" flush="true" />
                        </div>            
                        <!--mainleft end-->
                    </td>
                    <td class="up72_mright">
                        <!--mainright start-->            
                        <div class="up72_domright">
                            <div class="up72_navBar">
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
            <%@include file="/pages/admin/include/footer.jsp" %>      
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