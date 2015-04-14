<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="perm"%> 
<script type="text/javascript" src="${ctx}/scripts/uptabs.js"></script>

<%
String id = request.getParameter("id");
%>
<div id="TabDemo_${member.id}">
	<div class="up72_tabs">
	<c:choose>
		<c:when test="${member.code == 1}">
			<!-- 会员标签 -->
			<!-- tab start -->
			<div class="tabs_con"> 
				<perm:tag selector="span[rel]" replace="${ctx},${member.id}">
				<span rel="${ctx}/admin/auth/member/${member.id}/tabShow"><a href="javascript:;">基本信息</a></span>
				</perm:tag>
				<perm:tag selector="span[rel]" replace="${ctx},${member.id}">
				<span rel="${ctx}/admin/auth/member/${member.id}/tabEdit"><a href="javascript:;">信息编辑</a></span>
				</perm:tag>
				<perm:tag selector="span[rel]" replace="${ctx},${member.id}">
				<span rel="${ctx}/admin/auth/member/${member.id}/tabBuddy"><a href="javascript:;">TA的好友</a></span>
				</perm:tag>
				<!--<span rel="${ctx}/admin/auth/member/${member.id}/tabEdit">他的积分</span>-->
				<perm:tag selector="span[rel]" replace="${ctx},${member.id}">
				<span rel="${ctx}/admin/auth/member/${member.id}/tagMessage"><a href="javascript:;">TA的消息</a></span>
				</perm:tag>
				<!--<span rel="${ctx}/admin/auth/member/${member.id}/tabEdit">他的文章</span>-->
				<perm:tag selector="span[rel]" replace="${ctx},${member.id}">
				<span rel="${ctx}/admin/auth/member/${member.id}/tabVisitHistory"><a href="javascript:;">最近访客</a></span>
				</perm:tag>
				
				<!--<span rel="${ctx}/admin/auth/member/${member.id}/tabEdit">他的评论</span>-->
				<perm:tag selector="span[rel]" replace="${ctx},${member.id}">
				<span rel="${ctx}/admin/auth/member/${member.id}/tabEmail"><a href="javascript:;">发 邮 件</a></span>
				</perm:tag>
				<perm:tag selector="span[rel]" replace="${ctx},${member.id}">
				<span rel="${ctx}/admin/auth/member/${member.id}/tabNote"><a href="javascript:;">发 短 信</a></span>
				</perm:tag>
				<perm:tag selector="span[rel]" replace="${ctx},${member.id}">
				<span rel="${ctx}/admin/auth/member/${member.id}/tabSendMessage"><a href="javascript:;">发 消 息</a></span>
				</perm:tag>
			</div>
			<!-- tab end -->
		</c:when>
		<c:otherwise>
			<!-- 管理员标签 -->
			<!-- tab start -->
			<div class="tabs_con"> 
				<perm:tag selector="span[rel]" replace="${ctx},${member.id}">
				<span rel="${ctx}/admin/auth/member/${member.id}/tabShow"><a href="javascript:;">基本信息</a></span>
				</perm:tag>
				<perm:tag selector="span[rel]" replace="${ctx},${member.id}">
				<span rel="${ctx}/admin/auth/member/${member.id}/tabEdit"><a href="javascript:;">信息编辑</a></span>
				</perm:tag>
				<perm:tag selector="span[rel]" replace="${ctx},${member.id}">
				<span rel="${ctx}/admin/auth/member/${member.id}/tagWorkGroupMember"><a href="javascript:;">TA的机构</a></span>
				</perm:tag>
			</div>
			<!-- tab end -->
		</c:otherwise>
	</c:choose>
	</div>	
</div>
<script type="text/javascript">
	$("#TabDemo_${member.id}").find(".tabs_con span").up72Tabs(
		"#TabDemo_${member.id}",
		{
			selCss : "current",
			showCss : "tabs"
		}
	);
</script>