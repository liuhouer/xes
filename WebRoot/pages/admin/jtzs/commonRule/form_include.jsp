<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${commonRule.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=CommonRule.ALIAS_TITLE%>:</label></th>
  <td class="frmtd"><c:out value="${commonRule.title}"></c:out></td>
  </tr>
<c:if test="${commonRule.ruleType==1 || commonRule.ruleType==2 || commonRule.ruleType==7 || commonRule.ruleType==8 || 
 		commonRule.ruleType==9 || commonRule.ruleType==10 || commonRule.ruleType==11 || commonRule.ruleType==12}">
	<tr class="frmtr"> 
	  <th class="frmth"><label>值</label></th>
	  <td class="frmtd">
		<select id="scoreType" name="scoreType" style="width: 60px">
	  		<option value="1" <c:if test="${commonRule.scoreType==1}">selected="selected"</c:if>>增加</option>
	  		<option value="2" <c:if test="${commonRule.scoreType==2}">selected="selected"</c:if>>扣除</option>
	  	</select>    
	  	<form:input path="score" id="score" class="required digits" maxlength="3" />积分
		<font color='red'>*<form:errors path="score" /></font>
	  </td>
	  </tr>
</c:if>
<c:if test="${commonRule.ruleType==5 || commonRule.ruleType==6}">
	<tr class="frmtr"> 
	  <th class="frmth"><label>值</label></th>
	  <td class="frmtd">
	  <c:if test="${commonRule.ruleType==5}">
	    ${commonRule.minute}分钟&nbsp;
	  </c:if>
		<select id="scoreType" name="scoreType" style="width: 60px">
	  		<option value="1" <c:if test="${commonRule.scoreType==1}">selected="selected"</c:if>>增加</option>
	  		<option value="2" <c:if test="${commonRule.scoreType==2}">selected="selected"</c:if>>扣除</option>
	  	</select>    
	  	<form:input path="score" id="score" class="required digits" maxlength="3" style="width: 30px"/>&nbsp;积分<font color='red'>*<form:errors path="score" /></font>&nbsp;
	  </td>
	  </tr>
</c:if>
<c:if test="${commonRule.ruleType==4}">
	<tr class="frmtr"> 
	  <th class="frmth"><label><%=CommonRule.ALIAS_NUM%></label></th>
	  <td class="frmtd">
	  	<form:input path="num" id="num" class="required digits input_txt" style="width: 30px" maxlength="2" />
		<font color='red'>*<form:errors path="num" /></font>
	  </td>
	  </tr>  
</c:if>
<c:if test="${commonRule.ruleType==4 || commonRule.ruleType==3}">
<tr class="frmtr"> 
  <th class="frmth"><label><%=CommonRule.ALIAS_BEGIN_TIME%>:</label></th>
  <td class="frmtd">
    <input value="${commonRule.beginTimeStr}" onFocus="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" id="beginTimeString" name="beginTimeString" class=" input_text" />至
    <input value="${commonRule.endTimeStr}" onFocus="WdatePicker({readOnly:true,dateFmt:'HH:mm'})"  id="endTimeString" name="endTimeString" class=" input_text" />
    <font color='red'>*<form:errors path="endTime" /> </font>
    <label class="error noendTime" for="endTime" generated="true" style="display: none;">请选择正确时间</label>
  </td>
</tr>
</c:if>
<tr class="frmtr"> 
  <th class="frmth"><label>有效期:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${commonRule.validStartTimeDate}" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" id="validStartTimeString" name="validStartTimeString" class=" input_text" />至
    <input value="<fmt:formatDate value="${commonRule.validStopTimeDate}" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" id="validStopTimeString" name="validStopTimeString" class=" input_text" />
    <font color='red'>*<form:errors path="validStopTime" /></font>
    <label class="error noRightValidTime" for="validStopTime" generated="true" style="display: none;">请选择正确有效时间</label>
  </td>
</tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=CommonRule.ALIAS_RULE_TYPE%>:</label></th>
  <td class="frmtd"><c:out value="${commonRule.ruleTypeStr}"></c:out> </td>
  </tr>
<script type="text/javascript">
		var regEx = new RegExp("\\-","gi");
	function isRightTime(){
		var status = false;
		var beginTimeString = $('#beginTimeString').val();
		var endTimeString = $('#endTimeString').val();
		if(!isNaN(beginTimeString) && !isNaN(endTimeString)){
			if(beginTimeString!="" && endTimeString!=""){
				$('.noRightValidTime').hide();
				status = true;
			}else{
				$('.noRightValidTime').show();
			}
		}else{
			status = true;
		}
		return status;
	}
	function isRightValidTime(){
		var status = false;
		var validStartTimeString = $('#validStartTimeString').val();
		var validStopTimeString = $('#validStopTimeString').val();
		if(validStartTimeString!="" && validStopTimeString!=""){
    		validStartTimeString = validStartTimeString.replace(regEx,"/");
    		validStopTimeString = validStopTimeString.replace(regEx,"/")
			var validStartTime = new Date(validStartTimeString+" 00:00:00");
			var validStopTime = new Date(validStopTimeString+" 00:00:00");
			if(Number(validStartTime) <= Number(validStopTime)){
				$('.noRightValidTime').hide();
				status = true;
			}else{
				$('.noRightValidTime').show();
			}
		}else{
			$('.noRightValidTime').show();
		}
		return status;
	}
</script>