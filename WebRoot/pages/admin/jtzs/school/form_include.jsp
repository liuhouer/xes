<%@page import="com.xes.jtzs.model.*"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page trimDirectiveWhitespaces="true"%>
<input type="hidden" id="id" name="id" value="${school.id}" />
<input type="hidden" id="sort" name="sort" value="${school.sort}" />
<input type="hidden" id="status" name="status" value="${school.status}" />
<tr class="frmtr">
	<th class="frmth"><label><%=Area.ALIAS_PROVINCE_ID%>:</label></th>
	<td class="frmtd">
		<select id="provinceId" name="provinceId" style="width: 120px">
			<c:forEach items="${provinceList}" var="item" varStatus="status">
				<option value="${item.id}"
					<c:if test="${item.id == school.provinceId}">selected="selected"</c:if>>${item.name}
				</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=Area.ALIAS_CITY_ID%>:</label></th>
	<td class="frmtd">
		<select id="cityId" name="cityId" style="width: 120px"></select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=School.ALIAS_AREA_ID%>:</label></th>
	<td class="frmtd">
		<select id="areaId" name="areaId" style="width: 120px"></select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=School.ALIAS_DIVISIONS%>:</label></th>
	<td class="frmtd">
		<c:forEach items="${divisionArray}" var="item" varStatus="status">
			<input type="checkbox" value="${item.index}"  
			<c:forEach items="${school.divisionMap}" var="item2">
				<c:if test="${item2.key == item.index}">checked="checked"</c:if>
			</c:forEach>
			name="division" id="division" />${item.name}&nbsp;&nbsp;
		</c:forEach>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth"><label><%=School.ALIAS_NAME%>:</label></th>
	<td class="frmtd">
		<form:input path="name" id="name" class=" input_txt" maxlength="100" />
		<font color='red'>*<form:errors path="name" /></font>
	</td>
</tr>
<script type="text/javascript" >
	$(document).ready(function() {
		makeSelectCity();
		$("#admin_jtzs_school_edit_form #provinceId").change(function(){makeSelectCity()});
		$("#admin_jtzs_school_edit_form #cityId").change(function(){makeSelectArea()});
	});
		
	function makeSelectCity(){
	   var provinceId = $("#admin_jtzs_school_edit_form #provinceId option:selected").val();
	   $.ajax({
			url : "${ctx}/admin/jtzs/city/queryJsonCityList",
			type : "post",
			data : "provinceId="+provinceId,
			dataType : "json",
			success : function(jsondatas){
				var cityList = eval(jsondatas.cityList);
				var html = '';
				for (index in cityList){
					html+='<option value="'+cityList[index].id+'" ';
					if('${query.cityId}'==cityList[index].id){
						html+='selected="selected"';
					}
					html+=' >'+decodeURIComponent(cityList[index].name)+'</option>';
				}
				$("#admin_jtzs_school_edit_form #cityId").html(html);
				makeSelectArea();
			},
			error : function(){
			}
		});
	}
	
	function makeSelectArea(){
	   var cityId = $("#admin_jtzs_school_edit_form #cityId option:selected").val();
	   $.ajax({
			url : "/admin/jtzs/area/queryJsonAreaList",
			type : "post",
			data : "cityId="+cityId,
			dataType : "json",
			success : function(jsondatas){
				var areaList = eval(jsondatas.areaList);
				var html = '';
				for (index in areaList){
					html+='<option value="'+areaList[index].id+'" ';
					if('${query.areaId}'==areaList[index].id){
						html+='selected="selected"';
					}
					html+=' >'+decodeURIComponent(areaList[index].name)+'</option>';
				}
				$("#admin_jtzs_school_edit_form #areaId").html(html);
			},
			error : function(){
			}
		});
	}
</script> 