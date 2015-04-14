<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/function.jsp"%>
<up72:override name="head">
	<link href="${ctx}/styles/gongan/ucenter.css" rel="stylesheet" type="text/css" />
</up72:override>
<up72:override name="content">
<div class="myint_content">
	<div style="height:150px; text-align:center; margin:50px;">
		<span style="font-size:18px;">${resultMessage}！</span><br /><span style="color:#FF0000" id="timer_span">5</span>秒钟后转向首页，如果您的页面没有反映，请<a style="color:#FF0000" href="${ctx}/">点击这里</a>
	</div>
</div>
</up72:override>
<%@include file="/base.jsp"%>
<script type="text/javascript">
var timer = 5;
var interval = window.setInterval(function(){
	if(timer <= 1){
		window.clearInterval(interval);
		window.location.href="${ctx}/";
	}else{
		timer --;
		$("#timer_span").html(timer);
	}
},1000);
</script>