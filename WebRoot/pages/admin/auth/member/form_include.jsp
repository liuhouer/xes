<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@ include file="/common/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${AuthUser.id}"/>
	<form:hidden path="lastIp" id="lastIp" />
	<form:hidden path="lastVisiTime" id="lastVisiTime" />
	<form:hidden path="delStatus" id="delStatus" />
	<form:hidden path="imgPath" id="imgPath" />
	<input type="hidden" name="code" id="code" value="5" />
	
<script language="javascript" type="text/javascript">
function chooseWorkGroup(dom, workGroupId){
	var url="${ctx}/admin/auth/member/workGroup?workGroupId=" + workGroupId;
	showSelectPage(dom,{url:url,title:'选择用户组',selBox:'workGroupBox'});
}
function chooseRole(dom, roleId){
	if(isNull($("#workGroupId").val())){
		alert("请先选择所属用户组");
		return ;
	}
	var url = "${ctx}/admin/auth/member/role?workGroupId=" + $("#workGroupId").val() + "&roleId=" + roleId;
	showSelectPage(dom,{url:url,title:'选择用户组',selBox:'roleBox'});
}
</script>
	
	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_USER_NAME%>:</label></th>	
		 <td class="pb_frmtd">
		 <c:choose>
		 	<c:when test="${AuthUser.id == '' || AuthUser.id == 0 || AuthUser.id == null}">
		 		<form:input path="userName" id="userName" class="{required:true,zname:true,byteMin:4,byteMax:25,remote:'${ctx}/auth/member/validateName/',messages:{remote:'该用户名已被占用'}} input_txt" maxlength="25"/>
		 	</c:when>
		 	<c:otherwise>
				<input type="text" class="input_txt" disabled="disabled" value="${authUser.userName}" />
		 		<form:hidden path="userName" id="userName" />
		 	</c:otherwise>
		 </c:choose>
		<span class="required">*</span><font color='red'><form:errors path="userName"/></font>
		</td>
	</tr>
	
	
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_PASSWORD%>:</label></th>	
		 <td class="pb_frmtd">
	<c:choose>
		<c:when test="${(authUser.id != null) && (authUser.id > 0)}">
		<input type="text" class="input_txt" disabled="disabled" value="${authUser.password}" />
		<form:hidden path="password" id="password" />
		</c:when>
		<c:otherwise>
		<form:input path="password" id="password" class="{required:true,alnum:true,minlength:6,maxlength:16} input_txt" />
		</c:otherwise>
	</c:choose>	
		<span class="required">*</span><font color='red'><form:errors path="password"/></font>
		</td>
	</tr>	
	
	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_NICK_NAME%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="nickName" id="nickName" class="{cname:true,byteMin:4,byteMax:14} input_txt" maxlength="20" />
		<span class="required"></span><font color='red'><form:errors path="nickName"/></font>
		</td>
	</tr>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_EMAIL%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="email" id="email" class="{required:true,email:true} input_txt" maxlength="25" />
		<span class="required">*</span><font color='red'><form:errors path="email"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_LOGIN_ANSWER%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="loginAnswer" id="loginAnswer" class="{minlength:4,maxlength:50,cname:true} input_txt" maxlength="20" />
		<font color='red'><form:errors path="loginAnswer"/></font>
		</td>
	</tr>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_SECQUES%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="secques" id="secques" class="{minlength:4,maxlength:50,cname:true} input_txt" maxlength="20" />
		<font color='red'><form:errors path="secques"/></font>
		</td>
	</tr>	
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_CODE%>:</label></th>	
		 <td class="pb_frmtd">
		<form:radiobutton path="code" label="管理员" value="2" class="required digits:" />	
		<form:radiobutton path="code" label="系统管理员" value="5" class="required digits:" />	
		<span class="required"></span><font color='red'><form:errors path="code"/></font>
		</td>
	</tr>	
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_MOBILE%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="mobile" id="mobile" class="mobile input_txt" maxlength="15" />
		<font color='red'><form:errors path="mobile"/></font>
		</td>
	</tr>	
	 
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_PROBLEM%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="problem" id="problem" class="{minlength:4,maxlength:50,cname:true} input_txt" maxlength="20" />
		<font color='red'><form:errors path="problem"/></font>
		</td>
	</tr>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_ANSER%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="anser" id="anser" class="{minlength:4,maxlength:50,cname:true} input_txt" maxlength="20" />
		<font color='red'><form:errors path="anser"/></font>
		</td>
	</tr>	
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_ENABLED%>:</label></th>	
		 <td class="pb_frmtd">
		<form:radiobutton path="enabled" label="可用" value="1" class="required digits:" checked="checked" />
		<form:radiobutton path="enabled" label="禁用" value="0" class="required digits:" />
		<font color='red'><form:errors path="enabled"/></font>
		</td>
	</tr>
	<tr class="pb_frmtr">
	 <th class="pb_frmth"><label><%=AuthUser.ALIAS_MOBILE_VALIDATE%>:</label></th>	
		 <td class="pb_frmtd">
		<form:radiobutton path="mobileValidate" label="否" value="0" class="required digits:" checked="checked" />
		<form:radiobutton path="mobileValidate" label="是" value="1" class="required digits:" />
		<font color='red'><form:errors path="mobileValidate"/></font>
		</td>
	</tr>