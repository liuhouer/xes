<%@page import="com.bruce.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理系统</title>
<jsp:include page="/pages/admin/include/getOutterStyleSkin.jsp"></jsp:include>

<script src="<c:url value="/scripts/jquery.js"/>" type="text/javascript"></script>		
<script src="<c:url value="/scripts/main.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/application.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/weebox/bgiframe.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/weebox/weebox.js"/>" type="text/javascript"></script>
<link href="<c:url value="/scripts/weebox/weebox.css"/>" type="text/css" rel="stylesheet" />

<script src="<c:url value="/scripts/validate/jquery.validate.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/validate/messages_cn.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/validate/jquery.metadata.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/validate/validate.extend.js"/>" type="text/javascript"></script>

<script type="text/javascript" src="${ctx}/scripts/uptabs.js"></script>
<script src="<c:url value="/scripts/up72menumouse.js"/>" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/scripts/ckeditor/ckeditor.js"></script>

<up72:block name="head" />
<script type="text/javascript"></script>
</head>

<body>
<div class="up72_dowrap">
    <!--main start-->
    <div class="up72_domain skin_domain">
    <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" class="up72_mtable">
        <tr>
            <td class="up72_mright">
                <!--mainright start-->
                <div class="up72_domright">
                
                	<div class="up72_navbar"><h2>编辑</h2></div>
                    
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
		<%@include file="/pages/admin/include/footer.jsp" %>
    <!--foot end-->
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	window.parent.$("#navigation_bar").replaceWith("<span id=\"navigation_bar\">"+$(".navBar").html()+"</span>");
});
</script>  
</div>
</body>
</html>