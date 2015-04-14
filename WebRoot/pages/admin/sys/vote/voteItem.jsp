<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<title></title>
<link type="text/css" rel="stylesheet" href="../Style/Default.css">

<script src="../Framework/Main.js" type="text/javascript"></script><script src="http://www.zving.com/demo2/Framework/jquery.min.js" type="text/javascript"></script><script src="http://www.zving.com/demo2/Framework/Core.min.js" type="text/javascript"></script><script src="http://www.zving.com/demo2/Framework/components_frontend.min.js" type="text/javascript"></script><link href="http://www.zving.com/demo2/Framework/Lang/zh-cn.css" type="text/css" rel="stylesheet"><link href="http://www.zving.com/demo2/Framework/Components/DialogSkins/zvingClassic.css" type="text/css" rel="stylesheet"><link href="http://www.zving.com/demo2/Framework/Components/msgpop.css" type="text/css" rel="stylesheet"><link href="http://www.zving.com/demo2/Framework/Components/tip.css" type="text/css" rel="stylesheet"><link href="http://www.zving.com/demo2/Framework/Components/select.css" type="text/css" rel="stylesheet"><script src="http://www.zving.com/demo2/Framework/components_backend.min.js" type="text/javascript"></script><script src="http://www.zving.com/demo2/Framework/Retouch.js" type="text/javascript"></script><link href="http://www.zving.com/demo2/Framework/../Style/zvingClassic/Default.css" type="text/css" rel="stylesheet"><script src="http://www.zving.com/demo2/Framework/../Application.js" type="text/javascript"></script>
<script src="../Editor/ckeditor.js"></script>
<style type="text/css">
#voteresult{
margin:0 auto;
margin-top:4px;
padding:10px;}
#voteresult h2{
font-size:12px;
font-weight:bold;
padding:0;
margin:0;
padding-left:5px;
}
.voteresultb h3{
font-size:12px;
font-weight:bold;
line-height:30px;
padding:0;
padding-left:10px;
padding-bottom:5px;
margin:0;
}
.voteresultb table{
width:100%;
font-size:12px;
border-collapse:collapse;}
.voteresultb th {
background-color:#F6F6F6;
border-top:medium none;
color:#666666;
font-weight:normal;
line-height:24px;
padding:0;
text-align:center;
}
.voteresultb td.col1 {
line-height:normal;
padding:6px 10px;
text-indent:0;
}
.col1 span {
background:url(images/imgbg02.gif) no-repeat;
color:#FFFFFF;
display:block;
float:left;
height:12px;
line-height:12px;
margin:0 6px 0 0;
padding:0 2px 2px 0;
text-align:center;
width:18px;
}
.col1 p {
font-size:12px;
margin:-1px 0 0 28px;
}
.percent_bg {
background-color:#D9E4F8;
position:absolute;
right:20px;
top:12px;
width:67px;
}
.percent_bg, .percent {
display:block;
height:8px;
overflow:hidden;
text-align:left;
text-indent:-999em;
}
.voteresultb td {
color:#363636;
line-height:33px;
}
.percent{
	position:relative;
	z-index:9;
	background:url(images/imgbg01.gif) no-repeat scroll 0 0;
}
.percent_bg, .percent {
display:block;
height:8px;
overflow:hidden;
text-align:left;
text-indent:-999em;
}
th.col2, th.col3, td.col2, td.col3 {
border-left-width:1px;
}
.voteresultb td {
background-color:#FFFFFF;
color:#363636;
line-height:33px;
}
.voteresultb td, .voteresultb th{
border-collapse:collapse;
border-color:#D5D9D8;
border-style:solid;
border-width:1px;
}
.col2 {
table-layout:fixed;
width:200px !important;
}
.col2 div {
height:33px;
padding-left:10px;
position:relative;
text-align:left;
width:45px;
}
</style>
<script>

</script>
</head>
<body style="background-color: #D5D9D8">
<form id="form1" style="height: 100%;">
<table width="100%" height="*" cellspacing="0" cellpadding="0" border="0" class="js_layoutTable" id="js_layoutTable">
	<tbody>
	   <tr>
		<td height="33" align="right"><div id="ToolBar1" class="z-toolbar"><div class="z-toolbar-ct"><div class="z-toolbar-overflow"><div class="z-toolbar-nowrap"><a priv="VoteManage.Add||VoteManage.Edit" onclick="add();return false;" onselectstart="return false" class="z-btn z-btn-flat" id="Button2" buttontype="push" ztype="button" href="javascript:void(0);"><img src="../Icons/icon031a2.png"><b>新增调查项<i></i></b></a><script> new Zving.Button('Button2');</script>
		   <a priv="VoteManage" onclick="getResult();return false;" onselectstart="return false" class="z-btn z-btn-flat" id="Button3" buttontype="push" ztype="button" href="javascript:void(0);"><img src="../Icons/icon031a15.png"><b>查看结果<i></i></b></a><script> new Zving.Button('Button3');</script></div></div></div></div><script>Zving.Page.onReady(function(){new Zving.Toolbar('ToolBar1');});</script>
		</td>
	  </tr>
	  <tr>
		<td>
			<input type="hidden" value="0" id="CatalogID">
			<input type="hidden" value="24" id="VoteID">
			<input type="hidden" value="published" id="Status">
			<input type="hidden" value="false" id="IsNeedAudit">
			<div style="padding-left: 5px;">
			<table width="100%" cellspacing="2" cellpadding="2" border="0">
				<tbody>
					<tr>
						<td width="17%" align="right" class="dye">调查主题：</td>
						<td width="33%">首页调查控件</td>
						<td width="18%" align="right" class="dye">关联栏目：</td>
						<td width="32%"></td>
					</tr>
					<tr>
						<td width="17%" align="right" class="dye">是否限制IP：</td>
						<td width="33%">否</td>
						<td width="18%" align="right" class="dye">是否需要验证码：</td>
						<td width="32%">否</td>
					</tr>
					<tr>
						<td width="17%" align="right" class="dye">开始时间：</td>
						<td width="33%">2012-01-06</td>
						<td width="18%" align="right" class="dye">调查查看类型：</td>
						<td width="32%">允许直接查看</td>
					</tr>
				</tbody>
			</table>
			</div>
		</td>
	</tr>
	<tr>
		<td height="*" style="height: 206px;">
		<div id="voteresult">
 		<div class="voteresultb">
			<h3>1.您认为ZCMS应该加强哪方面的功能？  <font color="#999">[单选]</font>&nbsp;&nbsp;
			<input type="button" onclick="edit('35')" value="编辑"> 
				<input type="button" onclick="del('35')" value="删除">
			</h3>
			<table name="ChartTable">
				<tbody>
					<tr class="row0">
						<th width="85%" scope="col" class="col1">选项</th>
						<th width="15%" scope="col" class="col2">己投票数</th>
					</tr>
					<tr class="row1">
						<td class="col1"><span>1</span>
						<p>Web 2.0式的动态功能</p>
						</td>
						<td align="center" class="col2">
							7票
						</td>
					</tr>
					<tr class="row1">
						<td class="col1"><span>2</span>
						<p>标签应该能够扩展</p>
						</td>
						<td align="center" class="col2">
							19票
						</td>
					</tr>
					<tr class="row1">
						<td class="col1"><span>3</span>
						<p>应能拖拽式地生成快速专题</p>
						</td>
						<td align="center" class="col2">
							12票
						</td>
					</tr>
					<tr class="row1">
						<td class="col1"><span>4</span>
						<p>界面美观程度和易用性</p>
						</td>
						<td align="center" class="col2">
							8票
						</td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
		</td>
	</tr>
</tbody></table>
</form>
</body>
</html>
