<%@page import="com.up72.auth.model.*" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<form:form id="admin_auth_member_tab_edit_form" method="put" action="${ctx}/admin/auth/member/${authUser.id}" modelAttribute="authUser">
  	<input type="hidden" id="id" name="id" value="${authUser.id}"/>
    <input type="hidden" id="code" name="code" value="${authUser.code}"/>
    <input type="hidden" id="delStatus" name="delStatus" value="${authUser.delStatus}"/>
    <input type="hidden" id="lastIp" name="lastIp" value="${authUser.lastIp}"/>
    <input type="hidden" id="lastVisiTime" name="lastVisiTime" value="${authUser.lastVisiTime}"/>
    <input type="hidden" id="emailValidate" name="emailValidate" value="${authUser.emailValidate}"/>
    <input type="hidden" id="organizationId" name="organizationId" value="${authUser.organizationId}"/>
    <input type="hidden" id="mobileValidate" name="mobileValidate" value="${authUser.mobileValidate}"/>
    <input type="hidden" id="mobile" name="mobile" value="${authUser.mobile}"/>
    <input type="hidden" id="secques" name="secques" value="${authUser.secques}"/>
    <input type="hidden" id="loginAnswer" name="loginAnswer" value="${authUser.loginAnswer}"/>
    <input type="hidden" id="skin" name="skin" value="${authUser.skin}"/>
    <input type="hidden" id="style" name="style" value="${authUser.style}"/>
  <div class="up72_edit">
    <table width="100%" height="227" align="center" cellspacing="0" cellpadding="2" class="edit_table">
	<tbody><tr>
		<td>
			<div class="z-legend"><b>基本信息</b></div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="show_table">
				<tbody><tr>
					<td width="39%" height="30" align="right"><%=AuthUser.ALIAS_USER_NAME%>：</td>
					<td width="61%" id="tdUserName"><input type="text" class="input_txt" id="userName" name="userName" readonly="readonly" value="${authUser.userName}" />
					<span class="required">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right"><%=AuthUser.ALIAS_NICK_NAME%>：</td>
					<td><input type="text" name="nickName" id="nickName" value="${authUser.nickName}" class="input_txt" maxlength="20" />
					<span class="required">*</span>
					</td>
				</tr>
				<tr id="tr_Password2">
					<td height="30" align="right"><%=AuthUser.ALIAS_PASSWORD%>：</td>
					<td><input type="text" class="input_txt"  id="password" name="password" readonly="readonly" value="${authUser.password}" />
					<span class="required">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right"><%=AuthUser.ALIAS_ENABLED%>：</td>
					<td>
						<input type="radio" name="enabled" id="enabled" <c:if test="${authUser.enabled == 1}">checked="checked"</c:if> class="required digits:" value="1" />
      					    启用
         				 <input type="radio" name="enabled" id="enabled" <c:if test="${authUser.enabled == 0}">checked="checked"</c:if> class="required digits:" value="0" />
       					   禁用
					</td>
				</tr>
				<tr>
					<td height="30" align="right"><%=AuthUser.ALIAS_EMAIL%>：</td>
					<td><form:input path="email" id="email" class="{required:true,email:true} input_txt" />
					 <span class="required">*</span><font color='red'>
         			 <form:errors path="email"/>
         			 </font>
					</td>
				</tr>
				<tr>
					<td height="30" align="right"><%=AuthUser.ALIAS_PROBLEM%>：</td>
					<td><form:input path="problem" id="problem" class="{minlength:4,maxlength:50,cname:true} input_txt" />
					<span class="required">*</span>
					 <font color='red'>
        			  <form:errors path="problem"/>
         			 </font>
					</td>
				</tr>
				<tr>
					<td height="30" align="right"><%=AuthUser.ALIAS_ANSER%>：</td>
					<td><form:input path="anser" id="anser" class="{minlength:4,maxlength:50,cname:true} input_txt" />
					<span class="required">*</span>
					<font color='red'>
          			<form:errors path="anser"/>
         			 </font>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">备注：</td>
					<td><form:input path="comment" id="comment" class="{required:true} input_txt" /><span class="required">*</span></td>
				</tr>
			</tbody></table>
		</td>
		<td width="50%" valign="top" id="tdInfo">
			<div style="width:100%;text-align:center;margin-top:15px;">
				<%=AuthUser.ALIAS_IMG_PATH%>:<a style="width:120px;height:120px;overflow:hidden;" href="javascript:void(0)">
					<img width="120" src="<c:out value='${authUser.imgPath}'/>"  id="logoFileImg">
				</a>
				<div style="margin-top:5px;">
					<input type="hidden" value="${authUser.imgPath}" id="imgPath" name="imgPath">
					<input type="button" onclick="uploadFile()" value="上传">
				</div>
			</div>
		</td>
	</tr>
</tbody></table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </div>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_member_tab_edit_form").validate();	
	});
	function uploadFile(){
   		showCommonUpload({
	   		width:450,
	   		height:150,
	   		sizeLimit: 1024*1024*5,
	   		callBack:"window.parent.uploadFileCall(event, ID, fileObj, response, data)",
	   		fileExt:"*.jpg;*.gif;*.png;*.jpeg"
   		});
   	}

   function uploadFileCall(event, ID, fileObj, response, data){
		closeBox();
		confirm("是否对图片进行裁剪处理？",function(){
			var url = ctx+"/admin/cutImage?imgPath="+response.savePath+"&imgType=png&sw=100&sh=100&width=100&height=100&call=window.parent.upload_cut_call";
			show("iframe#"+url,"裁剪处理",730,400);
			
		},function(){
			var filePath = response.savePath;
	   		$("#logoFileImg").attr("src",filePath);
	   		$("#imgPath").val(filePath);
	   		closeBox();
		});
	}
	function upload_cut_call(imgPath){
		$("#logoFileImg").attr("src",imgPath);
		$("#imgPath").val(imgPath);
		closeBox();
	}
</script> 
