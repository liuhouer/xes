<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理系统</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style }/global.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style }/structs_outter.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style }/skins/${skin }/skin_outter.css"/>" />

<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style }/structs_inner.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style }/skins/${skin }/skin_inner.css"/>" />
<%@include file="include/system/resource.jsp" %>
<up72:block name="head" />
</head>
<body>
<div class="up72_dowrap">
	<!--top start-->
     	<%@include file="include/system/header.jsp" %>
    <!--top end-->
    
    <!--main start-->
    <div class="up72_domain skin_domain">
    <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" class="up72_mtable">
		<tr>
        	<td class="up72_mright_t">${AUTH_PERMISSION.name}</td>
        </tr>
        <tr>
            <!--<td class="up72_mleft">-->
                <!--mainleft start-->
                <%//@include file="include/left.jsp" %>
                <!--mainleft end-->
            <!--</td>-->
            <td class="up72_mright">
                <!--mainright start-->
                <div class="up72_domright">
                    <div id="up72_iframe" class="up72_content">
						<%@ include file="/common/messages.jsp"%>
						<up72:block name="content" />
                    </div>
                </div>
                <!--mainright end-->
            </td>
        </tr>
    </table>
    </div>
    <!--main end-->
    
    <!--foot start-->
		<%@include file="/pages/admin/include/system/footer.jsp" %>
    <!--foot end-->
</div>
</body>
</html>