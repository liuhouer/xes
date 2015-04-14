<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.up72.auth.member.model.AuthUser"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
  <title>系统配置</title>
</up72:override>
<up72:override name="content">
<!-- 当前位置 -->
<div class="up72_dashboard">
  <div class="dashboard_info">
    <div class="promptsnews_tit">主题设置</div>
    <div class="promptsnews_con">
      <jsp:include page="/admin/sys/sysConfig/changeSkin" flush="true"></jsp:include>
    </div>
    <div class="promptsnews_tit">系统名称设置</div>
    <div class="promptsnews_con">
      <jsp:include page="/admin/sys/sysConfig/changeProjectName" flush="true"></jsp:include>
    </div>
  </div>
</div>
</up72:override>
<%@ include file="base.jsp" %>
