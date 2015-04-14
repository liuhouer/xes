<%@page import="com.xes.jtzs.model.*"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page trimDirectiveWhitespaces="true"%>
<input type="hidden" id="id" name="id" value="${student.id}" />
<tr class="frmtr">
	<th class="frmth"><label><%=Student.ALIAS_PROVINCE_ID%>:</label></th>
	<td class="frmtd">
     	<select id="provinceId" name="provinceId" style="width: 120px">
       		<c:forEach items="${provinceList}" var="item" varStatus="status">
			 	<option value="${item.id}" <c:if test="${item.id == student.provinceId}">selected="selected"</c:if> >${item.name}</option>
       		</c:forEach>
		</select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Student.ALIAS_GRADE_ID%>:</label></th>
	<td class="frmtd">
   		<select id="gradeId" name="gradeId" style="width: 120px">
       		<c:forEach items="${gradeList}" var="item" varStatus="status">
			 	<option value="${item.id}" <c:if test="${item.id == query.gradeId}">selected="selected"</c:if> >${item.name}</option>
       		</c:forEach>
		</select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Student.ALIAS_SCHOOL_ID%>:</label></th>
	<td class="frmtd">
       	<select id="schoolId" name="schoolId"></select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Student.ALIAS_LOGIN_NAME%>:</label></th>
	<td class="frmtd">
		<c:if test="${student.id==null}">
			<form:input path="loginName" id="loginName" class="{required:true,byteMax:20,remote:'${ctx}/admin/jtzs/student/isUnique?id=${teacher.id}', messages:{remote:'登录不能重复',byteMax:'不能大于20字'}} input_txt" maxlength="20" />
			<font color='red'>*<form:errors path="loginName" /> </font>
		</c:if>
		<c:if test="${student.id!=null}">
			<c:out value="${student.loginName}"></c:out>
		</c:if>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Student.ALIAS_NICK_NAME%>:</label></th>
	<td class="frmtd">
		<form:input path="nickName" id="nickName" class="{required:true,byteMax:10, messages:{required:'请填写内容',byteMax:'不能大于10字'}} input_txt" maxlength="10" />
		<font color='red'>*<form:errors path="nickName" /> </font>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Student.ALIAS_SEX%>:</label></th>
	<td class="frmtd">
		<c:forEach items="${sexArray}" var="item">
			${item.name}<input type="radio" id="sex" <c:if test="${student.sex==item.index || (student.sex==null && item.index==1)}">checked="checked"</c:if> name="sex" value="${item.index}"/>
		</c:forEach>	
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Student.ALIAS_STATUS%>:</label></th>
	<td class="frmtd">
		<c:forEach items="${statusArray}" var="item">
			${item.name}<input type="radio" id="status" <c:if test="${student.status==item.index || (student.status==null && item.index==1)}">checked="checked"</c:if> name="status" value="${item.index}"/>
		</c:forEach>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Student.ALIAS_IMG_PATH%>:</label></th>
	<td class="frmtd">
		<div style="width:100%;margin-top:15px;">
			<%=Student.ALIAS_IMG_PATH%>:<a style="width:120px;height:120px;overflow:hidden;" href="javascript:void(0)">
				<img width="120" src="${ctx}<c:out value='${student.imgPath}'/>"  id="logoFileImg">
			</a>
			<div style="margin-top:5px;">
				<input type="hidden" value="${student.imgPath}" id="imgPath" name="imgPath"><input type="button" onclick="uploadFile()" value="上传">
			</div>
		</div>
	</td>
</tr>
 <script type="text/javascript" >
	$(document).ready(function() {
		makeSelectSchool();
		$("#admin_jtzs_student_edit_form #provinceId").change(function(){makeSelectSchool()});
		$("#admin_jtzs_student_edit_form #gradeId").change(function(){makeSelectSchool()});
	});
	function makeSelectSchool(){
	   var provinceId = $("#admin_jtzs_student_edit_form #provinceId option:selected").val();
	   var gradeId = $("#admin_jtzs_student_edit_form #gradeId option:selected").val();
	   $.ajax({
			url : "${ctx}/admin/jtzs/school/queryJsonSchoolList",
			type : "post",
			data : "provinceId="+provinceId+"&gradeId="+gradeId,
			dataType : "json",
			success : function(jsondatas){
				var schoolList = eval(jsondatas.schoolList);
				var html = '';
				for (index in schoolList){
					html+='<option value="'+schoolList[index].id+'" ';
					if('${query.schoolId}'==schoolList[index].id){
						html+='selected="selected"';
					}
					html+=' >'+decodeURIComponent(schoolList[index].name)+'</option>';
				}
				$("#admin_jtzs_student_edit_form #schoolId").html(html);
			},
			error : function(){
			}
		});
	}
	
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
</script> 