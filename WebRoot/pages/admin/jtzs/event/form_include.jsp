<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${event.id}"/>
<input type="hidden" id="sendUser" name="sendUser" value="${event.sendUser}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_TITLE%>:</label></th>
  <td class="frmtd"> <form:input path="title" id="title" class="{required:true,messages:{required:'请填写内容'}}  input_txt" maxlength="50" /><span><font color="red"> *</font></span>
    <font color='red'>
    <form:errors path="title"/>
    </font></td>
  </tr>
  <tr class="frmtr">
	<th class="frmth">
		<label><%=Area.ALIAS_PROVINCE_ID%>:</label>
	</th>
	<td class="frmtd">
     	<select id="provinceId" name="provinceId" style="width: 120px">
     	    <option value="0" <c:if test="${item.id == 0}">selected="selected"</c:if> >全国</option>
       		<c:forEach items="${provinceList}" var="item" varStatus="status">
			 	<option value="${item.id}" <c:if test="${item.id == event.provinceId}">selected="selected"</c:if> >${item.name}</option>
       		</c:forEach>
		</select>
		<select id="cityId" name="cityId" style="width: 120px">
		</select>
	</td>
</tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_SEND_TO%>:</label></th>
  <td class="frmtd">    <%-- <form:input path="sendTo" id="sendTo"  class="digits  input_txt" maxlength="3" /> --%>
                        <input type="checkbox" id="sendTo1" name="sendTo1" class="digits " maxlength="3"  value="0" <c:if test="${event.sendTo ==0 }">checked="checked"</c:if>><label for="sendTo1" >学生</label></input>
                        <input type="checkbox" id="sendTo2" name="sendTo2" class="digits " maxlength="3"  value="1" <c:if test="${event.sendTo ==1 }">checked="checked"</c:if>><label for="sendTo2">老师</label></input>
                        <input type="checkbox" id="sendTo3" name="sendTo3" class="digits " maxlength="3"  value="3" onclick="chg(this)" <c:if test="${event.sendTo ==3 }">checked="checked"</c:if>><label for="sendTo3" >选择接收对象</label></input>
    <font color='red'>
    <form:errors path="sendTo"/>
    </font></td>
  </tr>

<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_CONTENT%>:</label></th>
  <td class="frmtd">   <%--  <form:input path="content" id="content" class=" input_txt" maxlength="65535" /> --%>
    <textarea rows="3" cols="44"  id="content" name="content" class="{required:true,messages:{required:'请填写内容'}} input_txt" maxlength="65535"><c:out value="${event.content}"></c:out></textarea><span><font color="red"> *</font></span>
    <font color='red'>
    <form:errors path="content"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_QUESTION1%>:</label></th>
  <td class="frmtd">    <form:input path="question1"  id="question1" class=" input_txt quest" maxlength="50" />
    <font color='red'>
    <form:errors path="question1"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_QUESTION2%>:</label></th>
  <td class="frmtd">    <form:input path="question2" id="question2" class=" input_txt quest" maxlength="50" />
    <font color='red'>
    <form:errors path="question2"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_QUESTION3%>:</label></th>
  <td class="frmtd">    <form:input path="question3"  id="question3" class=" input_txt quest" maxlength="50" />
    <font color='red'>
    <form:errors path="question3"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_QUESTION4%>:</label></th>
  <td class="frmtd">    <form:input path="question4"  id="question4" class=" input_txt quest" maxlength="50" />
    <font color='red'>
    <form:errors path="question4"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_QUESTION5%>:</label></th>
  <td class="frmtd">    <form:input path="question5"  id="question5" class=" input_txt quest" maxlength="50" />
    <font color='red'>
    <form:errors path="question5"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_QUESTION6%>:</label></th>
  <td class="frmtd">    <form:input path="question6" id="question6" class=" input_txt quest" maxlength="50" />
    <font color='red'>
    <form:errors path="question6"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_QUESTION7%>:</label></th>
  <td class="frmtd">    <form:input path="question7" id="question7" class=" input_txt quest" maxlength="50" />
    <font color='red'>
    <form:errors path="question7"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_ANSWER%>:</label></th>
  <td class="frmtd">    
  		<select id="answer" name="answer" style="width: 120px" class="{required:true,messages:{required:'请选择内容'}}">
			 	<option value="">请选择</option>
			 	<option value="0" <c:if test="${event.answer ==0 }">selected="selected"</c:if>>无正确答案</option>
			 	<option value="1" <c:if test="${event.answer ==1 }">selected="selected"</c:if>>A</option>
			 	<option value="2" <c:if test="${event.answer ==2 }">selected="selected"</c:if>>B</option>
			 	<option value="3" <c:if test="${event.answer ==3 }">selected="selected"</c:if>>C</option>
			 	<option value="4" <c:if test="${event.answer ==4 }">selected="selected"</c:if>>D</option>
			 	<option value="5" <c:if test="${event.answer ==5 }">selected="selected"</c:if>>E</option>
			 	<option value="6" <c:if test="${event.answer ==6 }">selected="selected"</c:if>>F</option>
			 	<option value="7" <c:if test="${event.answer ==7 }">selected="selected"</c:if>>G</option>
		</select><span><font color="red"> *</font></span>
    <font color='red'>
    <form:errors path="answer"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label>有效期:</label></th>
  <td  class="frmtd" >
    <input value="<fmt:formatDate value="${event.startTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onchange="checkTime()"  onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="startTimeString" name="startTimeString" class="{required:true,messages:{required:'请选择时间'}}"  />
    <span><font color="red"> *</font></span>
    <font color='red'>
    <form:errors path="startTime"/>
    </font> 至:
    <input value="<fmt:formatDate value="${event.endTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onchange="checkTime()" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="endTimeString" name="endTimeString"   class="{required:true,messages:{required:'请选择时间'}}"/>
    <span><font color="red"> *</font></span>
    <font color='red'>
    <form:errors path="endTime"/>
    </font>
    
    
    </td>

  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_ADD_SCORE%>:</label></th>
  <td class="frmtd">    <form:input path="addScore" id="addScore" class="{required:true,messages:{required:'请填写分数'}} digits input_txt" maxlength="10" />
     <span><font color="red"> *</font></span>
    <font color='red'>
    <form:errors path="addScore"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Event.ALIAS_DEL_SCORE%>:</label></th>
  <td class="frmtd">    <form:input path="delScore" id="delScore" class="{required:true,messages:{required:'请填写分数'}} digits  input_txt" maxlength="10" />
    <span><font color="red"> *</font></span>
    <font color='red'>
    <form:errors path="delScore"/>
    </font></td>
  </tr>
  <tr class="frmtr"> 
   	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <td class="frmtd">
			<div style="width:100%;text-align:right;margin-top:15px;">
				<%=Event.ALIAS_IMG_PATH%>:
				<a style="width:120px;height:120px;overflow:hidden;text-align: center;" href="javascript:void(0)"> 
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img width="120"  src="<c:out value='${event.imgPath}'/>"  id="logoFileImg">
				</a>
				<div style="margin-top:5px;">
					<input type="hidden" value="${event.imgPath}" id="imgPath" name="imgPath">
					<input type="button" onclick="uploadFile()" value="上传">
				</div>
			</div>
		</td>
  </tr>
  <script type="text/javascript">
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
	
	$(document).ready(function() {
	makeSelectCity();
	$("#admin_jtzs_event_edit_form #provinceId").change(function(){makeSelectCity()});
	if(document.getElementById("sendTo3").checked){
	     document.getElementById("sendTo1").checked=false;
	     document.getElementById("sendTo2").checked=false;
	     document.getElementById("sendTo1").disabled=true;
	     document.getElementById("sendTo2").disabled=true;
	}
	});
	
	function makeSelectCity(){
	   var provinceId = $("#admin_jtzs_event_edit_form #provinceId option:selected").val();
	   $.ajax({
			url : "${ctx}/admin/jtzs/city/queryJsonCityList",
			type : "post",
			data : "provinceId="+provinceId,
			dataType : "json",
			success : function(jsondatas){
				var cityList = eval(jsondatas.cityList);
				var html = '';
				html+='<option value="'+00+'" '+'>'+'所有'+'</option>';
				for (index in cityList){
					html+='<option value="'+cityList[index].id+'" ';
					if('${event.cityId}'==cityList[index].id){
						html+='selected="selected"';
					}
					html+=' >'+decodeURIComponent(cityList[index].name)+'</option>';
				}
				$("#admin_jtzs_event_edit_form #cityId").html(html);
			},
			error : function(){
			}
		});
	}
	
	
	function chg(obj){
	     if(obj.checked){
	     document.getElementById("sendTo1").checked=false;
	     document.getElementById("sendTo2").checked=false;
	     document.getElementById("sendTo1").disabled=true;
	     document.getElementById("sendTo2").disabled=true;
	     }else{
	     document.getElementById("sendTo1").disabled=false;
	     document.getElementById("sendTo2").disabled=false;
	     }
	
	}
	
	function checkTime(){
	   var flag = true;
	   var t1 = $("#startTimeString").val();
	   var t2 = $("#endTimeString").val();
	   if(t1!=""&&t2!=""){
	     if(t2<t1){
	      alert("开始时间不能大于结束时间");
	      flag = false;
	     }
	   }
	   return flag;
	
	}
	
	
</script> 
