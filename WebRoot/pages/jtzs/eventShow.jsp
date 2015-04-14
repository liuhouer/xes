<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<up72:override name="head">
</up72:override>
<up72:override name="content">
	<div class="main">
		<div class="con">
			<div class="eventstime">
		        <div class="w100 oblique f12 right bold">本期活动时间：<span class="ff00 f125"><fmt:formatDate value="${event.startTimeDate}" pattern="MM-dd"/> ~ <fmt:formatDate value="${event.endTimeDate}" pattern="MM-dd"/></span></div>
		        <p class="w100 f666 f125 m2"><c:out value="${event.content}"/></p>
		        <p class="m2"><img src="${event.imgPath}"></p>
		    	<ul>
		        	<c:if test="${event.question1 ne '' }"><li><input class="checkbox" name="question" id="question1" type="checkbox"  onchange="setIdvalue(this)"  value="1"><label for="question1"><span>A:<c:out value="${event.question1 }"/></span></label></li></c:if>
		            <c:if test="${event.question2 ne '' }"><li><input class="checkbox" name="question" id="question2" type="checkbox"  onchange="setIdvalue(this)"  value="2"><label for="question2"><span>B:<c:out value="${event.question2 }"/></span></label></li></c:if>
		            <c:if test="${event.question3 ne '' }"><li><input class="checkbox" name="question" id="question3" type="checkbox"  onchange="setIdvalue(this)"  value="3"><label for="question3"><span>C:<c:out value="${event.question3 }"/></span></label></li></c:if>
		            <c:if test="${event.question4 ne '' }"><li><input class="checkbox" name="question" id="question4" type="checkbox"  onchange="setIdvalue(this)"  value="4"><label for="question4"><span>D:<c:out value="${event.question4 }"/></span></label></li></c:if>
		            <c:if test="${event.question5 ne '' }"><li><input class="checkbox" name="question" id="question5" type="checkbox"  onchange="setIdvalue(this)"  value="5"><label for="question5"><span>E:<c:out value="${event.question5 }"/></span></label></li></c:if>
		            <c:if test="${event.question6 ne '' }"><li><input class="checkbox" name="question" id="question6" type="checkbox"  onchange="setIdvalue(this)"  value="6"><label for="question6"><span>F:<c:out value="${event.question6 }"/></span></label></li></c:if>
		            <c:if test="${event.question7 ne '' }"><li><input class="checkbox" name="question" id="question7" type="checkbox"  onchange="setIdvalue(this)"  value="7"><label for="question7"><span>G:<c:out value="${event.question7 }"/></span></label></li></c:if>
		        </ul>
		    </div> 
		    <div class="w92 auto">
		        <div class="neir m10"><input type="button" value="提交答案" class="giveup_bg pointer" name="idValue"></div>
		        <div class="giveup_bgb f12 display">&nbsp;</div>
		    </div>
		</div>
	</div>
<script type="text/javascript">
function setIdvalue(obj){
	var ids = $("input[name=idValue]").attr("id");
	var value = $(obj).val();
	var checked = $(obj).attr("checked");
	if(checked){
		if(ids!=null&&ids!=""){
			var idArray = ids.split(",");
			for(var i= 0;i<idArray.length;i++){
				if(obj==idArray[i]){
					return;
				}
			}
		}
		ids+=value+",";
		$("input[name=idValue]").attr("id",ids);
	}else{
		if(ids!=null&&ids!=""){
			var idArray = ids.split(",");
			for(var i= 0;i<idArray.length;i++){
				if(value==idArray[i]){
					idArray.splice(i,1);
				}
			}
			var idss = idArray.join(",");
			$("input[name=idValue]").attr("id",idss);
		}
	}
	
}

$(document).ready(function(){ 
	 t = document.getElementsByTagName("img");
  	 for(i = 0; i < t.length; i++){
      t.item(i).onerror = function(){
        if(this.id =="defaultImg"){
            this.src = "/pages/jtzs/images/img.jpg";
            this.onerror = null;
          }
        
      }
    }

		});
</script>	
</up72:override>
<%@include file="pageBase.jsp"%>