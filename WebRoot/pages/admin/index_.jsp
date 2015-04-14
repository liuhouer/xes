<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理系统</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/global.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/structs_outter.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/skins/deepblue/skin_outter.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/structs_inner.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/skins/deepblue/skin_inner.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/scripts/weebox/weebox.css"/>"/>

<script src="<c:url value="/scripts/jquery.js"/>" type="text/javascript"></script>		
<script src="<c:url value="/scripts/main.js"/>" type="text/javascript"></script>	
<script src="<c:url value="/scripts/weebox/bgiframe.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/weebox/weebox.js"/>" type="text/javascript"></script>
<script src="<c:url value="${ctx}/scripts/uptabs.js"/>" type="text/javascript" ></script>
<script src="<c:url value="/scripts/up72menumouse.js"/>" type="text/javascript"></script>

</head>

<body>
<div class="up72_dowrap">
	<!--top start-->
	<div class="up72_dotop skin_dotop">
        <div class="up72_top">
            <div class="up72_tnav">
                <div class="up72_navl"></div>
                <div class="up72_ext"><!-- <a href="#" target="_blank">反馈意见</a>&nbsp;|&nbsp;<a href="#">设置</a>&nbsp;|&nbsp;<a href="#" target="_blank">帮助</a>&nbsp;|&nbsp; --><jsp:include page="/admin/menu/roleLabel" flush="true" />&nbsp;&nbsp;|<a href="${ctx}/admin/member/logout" class="up72_tnav_exit">退出管理</a></div>
            </div>
            <h1 class="logo"><a href="${ctx}/" target="_blank">运营基础平台</a></h1>
        </div>
    </div>
    <!--top end-->
    
    <!--main start-->
    <div class="up72_domain skin_domain">
    <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" class="up72_mtable">
        <tr>
            <td class="up72_mleft">
                <!--mainleft start-->
                <div class="up72_domleft">
                    <div class="up72_sidebar skin_sidebar">
                        <div class="up72_cnav">
                            <ul class="up72_folders">
								<jsp:include page="/admin/menu/productLabel" flush="true" />
                            </ul>
                     	</div>
                        <div class="up72_bline"></div>
                        
                    </div>
                </div>
                <!--mainleft end-->
            </td>
            <td class="up72_mright">
                <!--mainright start-->
                <div class="up72_domright">
                
                	<div class="up72_navbar"><!--<span class="navbar_back"><a href="javascript:;" onclick="window.history.go(-1);"><< 返回</a></span>--><h2>当前位置：<jsp:include page="/admin/setCurrentProduct" flush="true" /><span id="navigation_bar"></span></h2></div>
                    
                    <div class="up72_content">
						<div id="up72_iframe" class="up72_iframe">
							<jsp:include page="/admin/menu/permissionLabel" flush="true" />
						</div>
                    </div>
                    
                </div>
                <!--mainright end-->
            </td>
        </tr>
    </table>
    </div>
    <!--main end-->
    
    <!--foot start-->
		<%@include file="/pages/admin/include/footer.jsp" %>
    <!--foot end-->
</div>
</body>
</html>