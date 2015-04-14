<%@page import="com.xes.jtzs.model.*"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page trimDirectiveWhitespaces="true"%>
<input type="hidden" id="id" name="id" value="${area.id}" />
<input type="hidden" id="sort" name="sort" value="${area.sort}" />
<tr class="frmtr">
	<th class="frmth">
		<label><%=Area.ALIAS_PROVINCE_ID%>:</label>
	</th>
	<td class="frmtd">
     	<select id="provinceId" name="provinceId" style="width: 120px">
       		<c:forEach items="${provinceList}" var="item" varStatus="status">
			 	<option value="${item.id}" <c:if test="${item.id == area.provinceId}">selected="selected"</c:if> >${item.name}</option>
       		</c:forEach>
		</select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth">
		<label><%=Area.ALIAS_CITY_ID%>:</label>
	</th>
	<td class="frmtd">
      	<select id="cityId" name="cityId" style="width: 120px">
		</select>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth">
		<label><%=Area.ALIAS_NAME%>:</label>
	</th>
	<td class="frmtd">
		<form:input path="name" id="name" class="input_txt" maxlength="50" />
		<font color='red'>*<form:errors path="name" /></font>
	</td>
</tr>
<tr class="frmtr">
	<th class="frmth">
		<label><%=Area.ALIAS_STATUS%>:</label>
	</th>
	<td class="frmtd">
		<c:forEach items="${statusArray}" var="item">
			${item.name}<input type="radio" id="status" <c:if test="${area.status==item.index || (item.index==1 && area.status==null)}">checked="checked"</c:if> name="status" value="${item.index}"/>
		</c:forEach>
	</td>
</tr>
<script type="text/javascript" >
	$(document).ready(function() {
		makeSelectCity();
		$("#admin_jtzs_area_edit_form #provinceId").change(function(){makeSelectCity()});
	});
		
	function makeSelectCity(){
	   var provinceId = $("#admin_jtzs_area_edit_form #provinceId option:selected").val();
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
				$("#admin_jtzs_area_edit_form #cityId").html(html);
			},
			error : function(){
			}
		});
	}
</script> 