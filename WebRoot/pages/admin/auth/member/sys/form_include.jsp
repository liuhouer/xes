<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/scripts/permtree/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/scripts/permtree/jquery.treeview.js"></script>
<script type="text/javascript" src="${ctx}/scripts/permtree/permtree.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/scripts/permtree/jquery.treeview.css" />
<tbody><tr><td width="75%"><table border="0" cellspacing="0" cellpadding="0" class="edit_table">
	<input type="hidden" id="id" name="id" value="${authUser.id}"/>
	<form:hidden path="delStatus" id="delStatus" />
	<input type="hidden" name="code" id="code" value="5" />
	 <tr class="pb_frmtr">
		<th class="pb_frmth" width="15%"><label><%=AuthUser.ALIAS_USER_NAME%>:</label></th>	
		 <td class="pb_frmtd"  width="85%">
		 <c:choose>
		 	<c:when test="${authUser.id == null || authUser.id == 0}">
		 		<form:input path="userName" id="userName" class="{required:true,zname:true,byteMin:4,byteMax:25,remote:'${ctx}/admin/auth/member/validateName/',messages:{remote:'该用户名已被占用'}} input_txt" maxlength="25"/>
		 	</c:when>
		 	<c:otherwise>
				<input type="text" class="input_txt" disabled="disabled" value="${authUser.userName}" />
		 		<form:hidden path="userName" id="userName" />
		 	</c:otherwise>
		 </c:choose>
		<span class="required">*</span><font color='red'><form:errors path="userName"/></font>
		</td>
	</tr>
<c:if test="${(authUser.id == null) || (authUser.id == 0)}">
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_PASSWORD%>:</label></th>	
		 <td class="pb_frmtd">
		<input type="password" id="password" name="password" value="${authUser.password}" class="{required:true,alnum:true,minlength:6,maxlength:16} input_txt" />
		<span class="required">*</span><font color='red'><form:errors path="password"/></font></td>
	</tr>	
</c:if>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=AuthUser.ALIAS_NICK_NAME%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="nickName" id="nickName" class="{required:true,cname:true,byteMin:4,byteMax:14} input_txt" maxlength="20" />
		<span class="required">*</span><font color='red'><form:errors path="nickName"/></font>
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
<tr class="frmtr">
	<th class="frmth"><label><%=AuthUser.ALIAS_IMG_PATH%>:</label></th>
	<td class="frmtd">
		<div style="width:100%;margin-top:15px;">
			<%=AuthUser.ALIAS_IMG_PATH%>:<a style="width:120px;height:120px;overflow:hidden;" href="javascript:void(0)">
				<img width="120" src="<c:out value='${authUser.imgPath}'/>"  id="logoFileImg">
			</a>
			<div style="margin-top:5px;">
				<input type="hidden" value="${authUser.imgPath}" id="imgPath" name="imgPath"><input type="button" onclick="uploadFile()" value="上传">
			</div>
		</div>
	</td>
</tr>
</table></td>
<td width="25%" valign="top" id="tdInfo">
	<div style="width:100%;margin-top:15px;">
			<!-- 菜单权限开始 -->
		<div style="border:#666666 solid 1px;">
		<div>菜单权限</div>
		<div id="menuCtrl">
		<a title="" href="#">关闭全部</a>
		<a title="" href="#">展开全部</a>
		</div>
		<ul id="menuPermtree" class="filetree">
		<!-- 输出产品列表 start -->
		<input type="hidden" value="${role.id}" name="roleId" id="roleId" />
		<c:forEach items="${productList}" var="product" varStatus="proSta">
			<!-- 输出权限组列表 start -->
			<c:if test="${fn:length(product.permissionGroupList)>0}">
				<li class="expandable">
				<span class="<c:choose><c:when test="${null != product.permissionGroupList && fn:length(product.permissionGroupList)>0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>">
				<input type="checkbox" value="${product.id}" name="menuPermProductBox" />
				<c:out value="${product.name}" />
				</span>
					<ul style="display: none;">
						<c:forEach items="${product.permissionGroupList}" var="permGroup" varStatus="pgSta">
						<!-- 输出权限列表 start -->
						<c:if test="${fn:length(permGroup.menuPermList)>0}">
							<li class="expandable">
							<span class="<c:choose><c:when test="${null != permGroup.permissionList && fn:length(permGroup.permissionList)>0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>">
							<input type="checkbox" value="${permGroup.id}" name="menuPermGroupBox" /><c:out value="${permGroup.name}" />
							</span>
								<ul style="display: none;">
								<c:forEach items="${permGroup.menuPermList}" var="perm" varStatus="permSta">
									<li class="expandable">
									<span class="file">
									<input type="checkbox" value="${perm.id}" name="menuPermIds" /><c:out value="${perm.name}" />
									</span>
									</li>
								</c:forEach>
								</ul>
							</li>
						</c:if>
						<!-- 输出权限列表 end -->
						</c:forEach>
					</ul>
				</li>
			</c:if>
			<!-- 输出权限组列表 end -->
		</c:forEach>
		<!-- 输出产品列表 end -->
		</ul>
		</div>
	</div>
</td>
</tr></tbody>
 <script type="text/javascript" >
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
	   		$("#logoFileImg").attr("src",ctx+filePath);
	   		$("#imgPath").val(filePath);
	   		closeBox();
		});
	}
	function upload_cut_call(imgPath){
		$("#logoFileImg").attr("src",imgPath);
		$("#imgPath").val(imgPath);
		closeBox();
	}
	$(document).ready(function(){
		//菜单权限  menuPermBox  permMenuIds
		$("#menuPermtree").treeview({
			control: "#menuCtrl"
		});
		$("#menuPermtree").permtree({checked:{box:'menuPermIds',array:'${permMenuIds}'}});
	});
</script>