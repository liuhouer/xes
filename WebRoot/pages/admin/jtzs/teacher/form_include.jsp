<%@page import="com.xes.jtzs.model.*"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page trimDirectiveWhitespaces="true"%>
<input type="hidden" id="id" name="id" value="${teacher.id}" />
<tbody><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
<tr class="frmtr">
	<th class="frmth"><label><%=Teacher.ALIAS_PROVINCE_ID%>:</label></th>
	<td class="frmtd">
     	<select id="provinceId" name="provinceId" style="width: 120px">
       		<c:forEach items="${provinceList}" var="item" varStatus="status">
			 	<option value="${item.id}" <c:if test="${item.id == teacher.provinceId}">selected="selected"</c:if> >${item.name}</option>
       		</c:forEach>
		</select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Teacher.ALIAS_LOGIN_NAME%>:</label></th>
	<td class="frmtd">
		<c:if test="${teacher.id==null}">
			<form:input path="loginName" id="loginName" class="{required:true,byteMax:20,remote:'${ctx}/admin/jtzs/teacher/isUnique?id=${teacher.id}', messages:{remote:'登录不能重复',byteMax:'不能大于20字'}} input_txt" maxlength="20" />
			<font color='red'>*<form:errors path="loginName" /> </font>
		</c:if>
		<c:if test="${teacher.id!=null}">
			<c:out value="${teacher.loginName}"></c:out>
		</c:if>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Teacher.ALIAS_NICK_NAME%>:</label>
	</th>
	<td class="frmtd">
		<form:input path="nickName" id="nickName" class="{required:true,byteMax:10, messages:{required:'请填写内容',byteMax:'不能大于10字'}} input_txt" maxlength="10" />
		<font color='red'>*<form:errors path="nickName" /> </font>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Teacher.ALIAS_REAL_NAME%>:</label></th>
	<td class="frmtd">
		<form:input path="realName" id="realName" class="{required:true,byteMax:10, messages:{required:'请填写内容',byteMax:'不能大于10字'}} input_txt" maxlength="10" />
		<font color='red'>*<form:errors path="realName" /> </font>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Teacher.ALIAS_LEVEL%>:</label></th>
	<td class="frmtd">
		<select id="level" name="level" style="width: 120px">
			<option value="1" <c:if test="${1 == teacher.level}">selected="selected"</c:if> >教师</option>
			<option value="2" <c:if test="${2 == teacher.level}">selected="selected"</c:if> >专家</option>
		</select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Teacher.ALIAS_SEX%>:</label></th>
	<td class="frmtd">
		<c:forEach items="${sexArray}" var="item">
			<input type="radio" id="sex" <c:if test="${teacher.sex==item.index || (teacher.sex==null && item.index==1)}">checked="checked"</c:if> name="sex" value="${item.index}"/>${item.name}&nbsp;
		</c:forEach>	
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Teacher.ALIAS_EXPERT_GRADE_IDS%>:</label></th>
	<td class="frmtd">
		<c:forEach items="${gradeList}" var="item" varStatus="status">
			<input type="checkbox" id="expertGradeId" 
			<c:forEach items="${teacher.expertGradeList}" var="item2">
				<c:if test="${item2.id==item.id}">checked="checked"</c:if>
			</c:forEach>
		 name="expertGradeId" value="${item.id}"/>${item.name}&nbsp;&nbsp;
		 <c:if test="${status.count%6==0}"><br/></c:if>
		</c:forEach>	
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Teacher.ALIAS_EXPERT_SUBJECT_ID%>:</label></th>
	<td class="frmtd">
		<select id="expertSubjectId" name="expertSubjectId" style="width: 120px">
			<c:forEach items="${subjectList}" var="item">
				<option value="${item.id}" <c:if test="${item.id == teacher.expertSubjectId}">selected="selected"</c:if> >${item.name}</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Teacher.ALIAS_STATUS%>:</label></th>
	<td class="frmtd">
		<c:forEach items="${statusArray}" var="item">
			<input type="radio" id="status" <c:if test="${teacher.status==item.index || (teacher.status==null && item.index==1)}">checked="checked"</c:if> name="status" value="${item.index}"/>${item.name}&nbsp;
		</c:forEach>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Teacher.ALIAS_FREE_CYCLE%>:</label></th>
	<td class="frmtd">
		<input type="checkbox" id="freeCycles" <c:if test="${teacher.freeMonday}">checked="checked"</c:if> name="freeCycles" value="1000000"/>星期一&nbsp;
		<input type="checkbox" id="freeCycles" <c:if test="${teacher.freeTuesday}">checked="checked"</c:if> name="freeCycles" value="0100000"/>星期二&nbsp;
		<input type="checkbox" id="freeCycles" <c:if test="${teacher.freeWednesday}">checked="checked"</c:if> name="freeCycles" value="0010000"/>星期三&nbsp;
		<input type="checkbox" id="freeCycles" <c:if test="${teacher.freeThursday}">checked="checked"</c:if> name="freeCycles" value="0001000"/>星期四&nbsp;
		<input type="checkbox" id="freeCycles" <c:if test="${teacher.freeFriday}">checked="checked"</c:if> name="freeCycles" value="0000100"/>星期五&nbsp;
		<input type="checkbox" id="freeCycles" <c:if test="${teacher.freeSaturday}">checked="checked"</c:if> name="freeCycles" value="0000010"/>星期六&nbsp;
		<input type="checkbox" id="freeCycles" <c:if test="${teacher.freeSunday}">checked="checked"</c:if> name="freeCycles" value="0000001"/>星期日&nbsp;
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label>空闲时间:</label></th>
	<td class="frmtd">
        <input value="${teacher.freeStartTimeStr}" onFocus="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" id="freeStartTimeString" name="freeStartTimeString" class=" input_text" />至
    	<input value="${teacher.freeStopTimeStr}" onFocus="WdatePicker({readOnly:true,dateFmt:'HH:mm'})"  id="freeStopTimeString" name="freeStopTimeString" class=" input_text" />
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label>有效时间:</label></th>
	<td class="frmtd">
	  <input value="<fmt:formatDate value="${teacher.validStartTimeDate}" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" onchange="isRightValidTime();" id="validStartTimeString" name="validStartTimeString"  class=" input_text" />至
      <input value="<fmt:formatDate value="${teacher.validStopTimeDate}" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" onchange="isRightValidTime();" id="validStopTimeString" name="validStopTimeString"  class=" input_text" />
      <font color='red'>*<form:errors path="realName" /></font>
      <label class="error noFreeRightTime" style="display: none;">有效结束时间不能小于有效开始时间</label>
	</td>
</tr>
</table></td>
<td width="22%" valign="top" id="tdInfo">
	<div style="width:100%;margin-top:15px;">
		<%=Teacher.ALIAS_IMG_PATH%>:<a style="width:120px;height:120px;overflow:hidden;" href="javascript:void(0)">
			<img width="120" src="${ctx}<c:out value='${teacher.imgPath}'/>"  id="logoFileImg">
		</a>
		<div style="margin-top:5px;">
			<input type="hidden" value="${teacher.imgPath}" id="imgPath" name="imgPath"><input type="button" onclick="uploadFile()" value="上传">
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
	   		$("#logoFileImg").attr("src",filePath);
	   		$("#imgPath").val(filePath);
	   		closeBox();
		});
	}
	function upload_cut_call(imgPath){
		$("#logoFileImg").attr("src",ctx+imgPath);
		$("#imgPath").val(imgPath);
		closeBox();
	}
	function isRightValidTime(){
		var status = false;
		var validStartTimeString = $('#validStartTimeString').val();
		var validStopTimeString = $('#validStopTimeString').val();
		$.ajax({
			url : "${ctx}/admin/jtzs/teacher/isRightValidTime",
			type : "post",
			data : "validStartTimeString="+validStartTimeString+"&validStopTimeString="+validStopTimeString,
			dataType : "json",
			success : function (result){
				if(result){
					$('.noFreeRightTime').hide();
				}else{
					$('.noFreeRightTime').show();
				}
				status = result;
			}
		});
		return status; 
	}
</script> 