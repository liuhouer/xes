<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理系统</title>
<%@include file="getOutterStyleSkin.jsp" %>
<%@include file="/pages/admin/include/resource.jsp" %>
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet" />
</head>
<body>
<div class="up72_dowrap">
	<!--top start-->
     	<%@include file="/pages/admin/include/header.jsp" %>
    <!--top end-->
    
    <!--main start-->
    <div class="up72_domain skin_domain">
    <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" class="up72_mtable">
		<tr>
        	<td><div class="up72_mright_t">系统设置</div></td>
        </tr>
        <tr>
            <td class="up72_mright">
                <!--mainright start-->
                <div class="up72_domright">
                    <div id="up72_iframe" class="up72_content">
						<%@ include file="/common/messages.jsp"%>
						<div class="up72_dashboard">
						    <div class="dashboard_info">
						    	<c:forEach items="${permissionMap}" var="item"> 
						        <div class="promptsnews_tit"><c:choose><c:when test="${(item.key == null) || ('' eq item.key)}">基本管理</c:when>
						        	<c:otherwise>${item.key}</c:otherwise>
						        </c:choose>
						        </div>
						        <div class="promptsnews_con">
									<div class="up72_quickbtns">
										<ul>
											<c:forEach items="${item.value}" var="permission">
											<c:choose>
												<c:when test="${(permission.imgPath == null) || ('' eq permission.imgPath)}"><c:set var="imgPath" value="/styles/default/skins/deepblue/images/icon_default.png"/></c:when>
						        				<c:otherwise><c:set var="imgPath" value="${permission.imgPath}"/></c:otherwise>
						        			</c:choose>
									        <li><a href="${ctx}/admin/sysCofig/${permission.id}?url=${permission.url}"  title="${permission.name}"><span class="quickbtns_icon"><img src="${imgPath}" /></span><span class="quickbtns_title">${permission.name}</span></a></li>
									        </c:forEach>
									    </ul>
									</div>
								</div>
								</c:forEach>
							</div>
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