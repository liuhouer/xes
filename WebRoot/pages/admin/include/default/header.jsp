<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.bruce.common.CommonConstants"%>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="perm"%> 
<div class="up72_top">
    <div class="up72_tnav">
        <div class="up72_config"></div>
        <div class="up72_info"><h4>欢迎 , 登录&nbsp;&nbsp;<span class="tnav_time" id="currentTime"></span></h4><a href="${ctx }/admin/index" title="首页" class="tnav_config">首页</a>|<perm:tag selector="" jsPermPath="${ctx }/admin/smis/sysConfig" type="1"><a href="${ctx }/admin/sys/sysConfig" title="系统设置" class="tnav_config tnav_exit">系统设置</a>|</perm:tag><a href="#" class="tnav_config tnav_exit">修改密码</a>|<a href="${ctx}/admin/authUser/logout" class="tnav_exit">退出管理</a></div>
    </div>
    <h1 class="up72_logo"><a href="${ctx}/admin/index" title="信息安全等级保护管理系统">信息安全等级保护管理系统</a></h1>
</div>
