<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="kt_Sidebar">
	
	<div class="kt_cnav kt_Sb_sOTmail" id="menu1">
		<div class="kt_Sb_cnav_tit" style="background:#CCCCCC;">
			<a href="javascript:" class="fn_bg opned" title="收起" onclick="toggleMenu('#menu1','.ulDefFolders',this)"></a>
			<a href="javascript:" class="ct">用户管理&nbsp;<span id="spnDefCount" style="display: none;"></span></a>
			<!--
			<a href="#" class="fn_bg Aadd" title="添加">添加</a><a href="#"
				class="fn_bg Amag" title="管理">管理</a>
			-->
		</div>
		<ul class="ulDefFolders">
		
			<li>
				<b class="ico_account ico_act_default"></b><a href="${ctx}/admin/ucenter/member" class="ct">用户列表</a>
			</li>
		
			<li>
				<b class="ico_account ico_act_default"></b><a href="${ctx}/admin/auth/organization" class="ct">机构列表</a>
			</li>
		
			<li>
				<b class="ico_account ico_act_default"></b><a href="${ctx}/admin/auth/workGroup" class="ct">用户组列表</a>
			</li>
		
			<li>
				<b class="ico_account ico_act_default"></b><a href="${ctx}/admin/auth/permissionGroup" class="ct">权限组列表</a>
			</li>
		
			<li>
				<b class="ico_account ico_act_default"></b><a href="${ctx}/admin/auth/role" class="ct">角色列表</a>
			</li>
		
			<li>
				<b class="ico_account ico_act_default"></b><a href="${ctx}/admin/auth/permission" class="ct">权限列表</a>
			</li>
			
			<li>
				<b class="ico_account ico_act_default"></b><a href="${ctx}/admin/auth/product" class="ct">产品列表</a>
			</li>
		</ul>
	</div>
</div>