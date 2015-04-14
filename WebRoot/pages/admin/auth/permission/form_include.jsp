<%@page import="com.up72.auth.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.up72.auth.AuthConstants"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	Integer system = AuthConstants.common.AUTH_SYSTEM;
	Integer user = AuthConstants.common.AUTH_USER;
	request.setAttribute("system", system);
	request.setAttribute("user", user);
%>
<script type="text/javascript" src="${ctx}/scripts/rowsel.js"></script>
<script type="text/javascript">
function choosePermissionGroup(sel){
	var productCode = $(sel).val();
	if(isNull(productCode)){
		var optHtml = "<option value=''>请选择权限组</option>";
		$("#permissionGroupCode").html(optHtml);
		return ;
	}
	$.ajax({
		url : "${ctx}/admin/auth/permissionGroup/"+productCode+"/permGroupJSON",
		dataType : "json",
		success : function(jsonDatas){
			var optHtml = "<option value=''>请选择权限组</option>";
			$(jsonDatas).each(function(i,obj){
				optHtml += "<option value='"+obj.code+"' status='"+obj.status+"'>"+obj.name+"</option>";
			});
			$("#permissionGroupCode").html(optHtml);
		},
		error : function(){
			alert("网络错误，请刷新页面重试。");
		}
	});
	
}
$(document).ready(function(){
	var productCode = $("#productCode").val();
	if(isNull(productCode)){
		var optHtml = "<option value=''>请选择权限组</option>";
		$("#permissionGroupCode").html(optHtml);
		return ;
	}
	var code = "${permission.permissionGroupCode}";
	$.ajax({
		url : "${ctx}/admin/auth/permissionGroup/"+productCode+"/permGroupJSON",
		dataType : "json",
		success : function(jsonDatas){
			var optHtml = "<option value=''>请选择权限组</option>";
			$(jsonDatas).each(function(i,obj){
				if(obj.code == code){
					optHtml += "<option value='"+obj.code+"' selected='selected' status='"+obj.status+"'>"+obj.name+"</option>";
				}else{
					optHtml += "<option value='"+obj.code+"' status='"+obj.status+"'>"+obj.name+"</option>";
				}
			});
			$("#permissionGroupCode").html(optHtml);
		},
		error : function(){
			alert("网络错误，请刷新页面重试。");
		}
	});
	
});
function checkURI(value){
	if(value!=""){
		//"^((https|http|ftp|rtsp|mms)?://)" 
  		//+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@ 
        //+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184 
        //+ "|" // 允许IP和DOMAIN（域名）
        //"([0-9a-zA-Z_!~*'()-]+\/?)*" // 域名- www. 
        //+ "([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z]\/?" // 二级域名 
        //+ "[a-z]{2,6})" // first level domain- .com or .museum 
        //+ "(:[0-9]{1,4})?" // 端口- :80 
        //+ "((//?)|" // a slash isn't required if there is no file name 
        //+ "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$"; 
		var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
 		+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
        + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
        + "|" // 允许IP和DOMAIN（域名）
        + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
        + "[a-z]{2,6})" // first level domain- .com or .museum
        + "(:[0-9]{1,4})?" // 端口- :80
        + "((/?)|" // a slash isn't required if there is no file name
        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$"; 
        var re=new RegExp(strRegex); 
	}
}
</script>

<input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID"
	value="${AUTH_PERM_ID}" />
<input type="hidden" id="id" name="id" value="${permission.id}" />
<input type="hidden" name="status" id="status" value=""
	disabled="disabled" />
<input type="hidden" value="1" name="sort" id="sort" />

<input type="hidden" name="return_url" id="return_url"
	value="redirect:/admin/auth/product/indexTree" />
<tr class="pb_frmtr">
	<th class="pb_frmth">
		<label><%=Permission.ALIAS_NAME%>:
		</label>
	</th>
	<td class="pb_frmtd">
		<form:input path="name" id="name" class="{required:true,byteMax:16,messages:{required:'请输入名称',byteMax:'最多输入16个字节'}} cname input_txt"
			maxlength="30" />
		<span class="required">*</span><font color='red'><form:errors
				path="name" />
		</font>
	</td>
</tr>

<tr class="pb_frmtr">
	<th class="pb_frmth">
		<label><%=Product.ALIAS_TAG%>:
		</label>
	</th>
	<td class="pb_frmtd">
		<form:input path="tag" id="tag" class="input_txt {byteMax:29,messages:{byteMax:'最多输入29个字节'}} " maxlength="30" />
		<font color='red'><form:errors path="tag" />
		</font>
	</td>
</tr>
<tr class="pb_frmtr">
	<th class="pb_frmth">
		<label><%=Permission.ALIAS_PRODUCT_ID%>：
		</label>
	</th>
	<td class="pb_frmtd">
		<select id="productCode" name="productCode"
			onchange="choosePermissionGroup(this);">
			<option value="">
				请选择产品
			</option>
			<c:forEach items="${productList}" var="pro">
				<option value="${pro.code}"
					<c:if test="${pro.code == permission.productCode}"> selected="selected"</c:if>>
					${pro.name}
				</option>
			</c:forEach>
		</select>
		<span class="required">*</span><font color='red'><form:errors
				path="productCode" />
		</font>
	</td>
</tr>
<tr class="pb_frmtr">
	<th class="pb_frmth">
		<label><%=Permission.ALIAS_PERMISSION_GROUP_ID%>:
		</label>
	</th>
	<td class="pb_frmtd">
		<c:choose>
			<c:when test="${null!=permission && null!=permission.code && permission.code!=''}">
				<select id="permissionGroupCode" name="permissionGroupCode"
					onchange="getPermissionGroupStatusEdit()">
					<option value="">
						请选择权限组
					</option>
					<c:forEach items="${permissionGroupList}" var="permGroup">
						<option value="${permGroup.code}" status="${permGroup.status }" <c:if test="${permGroup.code == permission.permissionGroupCode}"> selected="selected"</c:if>>
							${permGroup.name}</option>
					</c:forEach>
				</select>
			</c:when>
			<c:otherwise>
				<select id="permissionGroupCode" name="permissionGroupCode"
					onchange="getPermissionGroupStatus()">
					<option value="">
						请选择权限组
					</option>
					<c:forEach items="${permissionGroupList}" var="permGroup">
						<option value="${permGroup.code}" status="${permGroup.status }"
							<c:if test="${permGroup.code == permission.permissionGroupCode}"> selected="selected"</c:if>>
							${permGroup.name}
						</option>
					</c:forEach>
				</select>
			</c:otherwise>
		</c:choose>
		<span class="required">*</span><font color='red'><form:errors path="permissionGroupCode" />
		</font>
	</td>
</tr>
<tr class="pb_frmtr">
	<th class="pb_frmth">
		<label><%=Permission.ALIAS_URL%>:
		</label>
	</th>
	<td class="pb_frmtd">
		<form:input path="url" id="url" class="input_txt {required:true,byteMax:99,messages:{required:'请输入链接地址',byteMax:'最多输入99个字节'}}"
			maxlength="100" onblur="checkURI(this.value)" />
		<span class="required">*</span><font color='red'><form:errors
				path="url" />
		</font>
	</td>
</tr>
<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Product.ALIAS_IMG_PATH%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="imgPath" id="imgPath" class="input_txt" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
		<a href="javascript:;" onclick="uploadIconPath();">上传</a>
		<a style="display: none;" id="viewImg" href="${permission.imgPath}" target="_blank">查看</a>
		<script type="text/javascript">
			loadViewImg();
		</script>
		<font color='red'><form:errors path="imgPath"/></font>
		</td>
	</tr>	
<c:if
	test="${null!=permission && null!=permission.code && permission.code!=''}">
	<tr class="pb_frmtr">
		<th class="pb_frmth">
			<label><%=Permission.ALIAS_CODE%>:
			</label>
		</th>
		<td class="pb_frmtd">
			<form:input path="code" readonly="readonly" id="code"
				class="input_txt" maxlength="100" />
			<span class="required">*</span><font color='red'><form:errors
					path="code" />
			</font>
		</td>
	</tr>
</c:if>
<tr class="pb_frmtr">
	<th class="pb_frmth">
		<label><%=Permission.ALIAS_ENABLED%>:
		</label>
	</th>
	<td class="pb_frmtd">
		<form:radiobutton path="enabled" class="required" id="enabled"
			value="1" checked="checked" />
		启用
		<form:radiobutton path="enabled" class="required" id="enabled"
			value="0" />
		禁用
	</td>
</tr>
<tr class="pb_frmtr">
	<th class="pb_frmth">
		<label><%=Permission.ALIAS_STATUS%>:
		</label>
	</th>
	<td class="pb_frmtd"><input type="radio" id="status" name="status"
			value="<%=AuthConstants.common.AUTH_ALL%>"
			<c:if test="${(permission.status == null) || (permission.status == 0)}">checked="checked"</c:if> />
		全局权限
		<input type="radio" id="status" name="status"
			value="<%=AuthConstants.common.AUTH_SYSTEM%>"
			<c:if test="${permission.status == 1}">checked="checked"</c:if> />
		系统权限
		<input type="radio" id="status" name="status"
			value="<%=AuthConstants.common.AUTH_USER%>"
			<c:if test="${permission.status == 2}">checked="checked"</c:if> />
		用户权限
		<span class="required">*</span><font color='red'><form:errors
				path="status" />
		</font>
	</td>
</tr>
<tr class="pb_frmtr">
	<th class="pb_frmth">
		<label><%=Permission.ALIAS_TYPE%>:
		</label>
	</th>
	<td class="pb_frmtd">
		<div>
			<form:radiobutton path="type" class="required" value="1"
				checked="checked" />
			菜单权限
			<label style="color: #FF0000">
				与用户角色相连，决定用户对某功能的菜单显示操作！
			</label>
		</div>
		<div>
			<form:radiobutton path="type" class="required" value="2" />
			操作权限
			<label style="color: #FF0000">
				与用户角色相连，决定用户对某功能的增、删、改、查操作！
			</label>
		</div>
		<div>
			<form:radiobutton path="type" class="required" value="3" />
			TAG权限
			<label style="color: #FF0000">
				与列表页面相连，于页面设置，决定列表页面所关联业务及操作功能！
			</label>
		</div>
		<span class="required"></span><font color='red'><form:errors
				path="type" />
		</font>
	</td>
</tr>
<tr class="pb_frmtr">
	<th class="pb_frmth">
		<label><%=Permission.ALIAS_DESCRIPTION%>:
		</label>
	</th>
	<td class="pb_frmtd">
		<form:textarea rols="8" cols="20" path="description" id="description"
			class="input_txt {byteMax:99,messages:{byteMax:'最多输入99个字节'}}" maxlength="100" />
		<font color='red'><form:errors path="description" />
		</font>
	</td>
</tr>

<script language="javascript">	
	function getPermissionGroupStatus(){
		var status = jQuery("#permissionGroupCode option:selected").attr("status");
		if(status == ${system}){
			jQuery("input[type='radio'][name='status'][value='${system}']").attr("checked", "checked");
			jQuery("input[type='radio'][name='status']").attr("disabled", "disabled");
			jQuery("#status").removeAttr("disabled");
			jQuery("#status").val(${system});
		}else if(status == ${user}){
			jQuery("input[type='radio'][name='status'][value='${user}']").attr("checked", "checked");
			jQuery("input[type='radio'][name='status']").removeAttr("disabled");
			jQuery("#status").attr("disabled", "disabled");
			jQuery("#status").val(${user});
		}else{
			jQuery("input[type='radio'][name='status'][value='${user}']").attr("checked", "checked");
			jQuery("input[type='radio'][name='status']").removeAttr("disabled");
			jQuery("#status").attr("disabled", "disabled");
			jQuery("#status").val(${permission.status});
		}
	}
	
	function getPermissionGroupStatusEdit(){
		var permGroupStatus = ${user};
		<c:if test="${null!=permission.status}">
			permGroupStatus = ${permission.status};
		</c:if>
		var selectStatus = jQuery("#permissionGroupCode option:selected").attr("status");
		if(selectStatus == ${system}){
			jQuery("input[type='radio'][name='status'][value='${system}']").attr("checked", "checked");
			jQuery("input[type='radio'][name='status']").attr("disabled", "disabled");
			jQuery("#status").removeAttr("disabled");
			jQuery("#status").val(${system});
		}else if(selectStatus == ${user}){
			jQuery("input[type='radio'][name='status'][value='"+permGroupStatus+"']").attr("checked", "checked");
			jQuery("input[type='radio'][name='status']").removeAttr("disabled");
			jQuery("#status").attr("disabled", "disabled");
			jQuery("#status").val(permGroupStatus);
		}else{
			jQuery("input[type='radio'][name='status'][value='${user}']").attr("checked", "checked");
			jQuery("input[type='radio'][name='status']").removeAttr("disabled");
			jQuery("#status").attr("disabled", "disabled");
			jQuery("#status").val(${permission.status});
		}
	}
	<c:choose>
		<c:when test="${null!=permissionGroup && null!=permissionGroup.code && permissionGroup.code>0}">
			getPermissionGroupStatusEdit();
		</c:when>
		<c:otherwise>
			getPermissionGroupStatus();
		</c:otherwise>
	</c:choose>
	</script>