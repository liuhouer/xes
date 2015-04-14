<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营基础平台管理登录</title>
<script src="<c:url value="/scripts/jquery.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/validate/jquery.validate.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/validate/messages_cn.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/validate/jquery.metadata.js"/>" type="text/javascript"></script>
<script src="<c:url value="/images/flash.js"/>" type="text/javascript"></script>

<style type="text/css">
<!--
/*global*/
body{ margin:0px; padding:0px; color:#999; font-family:'Microsoft YaHei','SimHei','\5B8B\4F53'; background:#bbb url(${ctx}/styles/system/skins/mac/images/bg.png) center no-repeat; background-size:100% 100%; }
ul,li,img{ padding:0px; margin:0; list-style:none; }
input, textarea, select{ border-radius:2px; }
form{ padding:0px; margin:0px; }
/*login layer*/
table.contents{ width:538px; height:42px; margin:0 auto; box-shadow:0 2px 10px rgba(0,0,0,0.5); border-radius:8px 8px 0 0;background:url(${ctx}/styles/system/skins/mac/images/login_bg.png) repeat-x; /*background-color:#5b6f8a; background:-webkit-gradient(linear,left top,right top,from(#5f7334),to(#26516e)); background:-moz-linear-gradient(left,#9F701c,#5f7334,#26516e); background:transparent\9; filter:progid:DXImageTransform.Microsoft.gradient(gradientType=1,startColorstr='#5f7334',endColorstr='#26516e');*/ padding:1px; }
table.contents tr td.con_header{ height:42px; line-height:42px; font-size:16px; font-family:"微软雅黑"; color:#fff; text-indent:1em; }
table.contents tr td.con_wrapper{ background:#fff; border-top:solid 1px #eee; box-shadow:0 8px 10px rgba(0,0,0,0.15) inset; }
ul.Manager{ margin:0 auto; padding:5px 0 20px; }
/*login input name/password*/
ul.Manager li{ padding-bottom:20px; height:51px; line-height:51px; }
ul.Manager li.userlogin{ padding-bottom:10px; }
ul.Manager li.userradio{ height:20px; line-height:20px; }
ul.Manager li label.caption,ul.Manager li span.input{ display:block; height:51px; line-height:51px; }
ul.Manager li.userlogin span.input{ padding-top:11px; height:22px; line-height:22px; }
ul.Manager li.userradio label.caption,ul.Manager li.userradio span.input{ height:20px; line-height:20px; }
ul.Manager li label.caption{ text-align:right; width:110px; padding-right:8px; font-size:14px; color:#666; float:left; }
ul.Manager li label.logint{ font-size:24px; color:#ddd; }
ul.Manager li span.input{ font-size:12px; text-align:left; }
ul.Manager li span.input input.text{background:url(${ctx}/styles/system/skins/mac/images/input_bg.png) no-repeat; height:51px; _line-height:51px; padding:5px; width:312px; font-weight:bold; font-family:Verdana; border:none; cursor:text; }
/*-input*/
.input_out{ background:#f8fcff; height:35px; }
.input_move,ul.Manager li span.input input.text:hover{box-shadow:0 1px 2px rgba(0,0,0,0.15) inset;}
ul.Manager li span.input input.text:active, ul.Manager li span.input input.text:focus{box-shadow:0 1px 2px rgba(0,0,0,0.15) inset; }
ul.Manager li span.input radio{ vertical-align:bottom; }
ul.Manager li span.input img{ cursor:pointer; height:22px; vertical-align:middle; margin-bottom:5px; }
/*login button*/
div.button{ height:51px; line-height:51px; padding:0px 0 50px 0; text-align:left; margin-left:120px;_margin-left:80px; }
div.button input{ font-size:18px; cursor:pointer; border:none; width:103px; height:51px; line-height:51px; border-radius:3px; color:#fff; font-family:"微软雅黑"; }
/*-button*/
.button .input_button{background:url(${ctx}/styles/system/skins/mac/images/login_input.png) no-repeat;/* background:#1199dc; background:-webkit-gradient(linear,left top,left bottom,from(#8fdcfd),to(#1199dc)); background:-moz-linear-gradient(top,#8fdcfd,#1199dc); background:transparent\9; filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#8fdcfd',endColorstr='#1199dc'); border:1px solid #34b2f1;*/ }


.button .input_button_none{background:url(${ctx}/styles/system/skins/mac/images/reset.png) no-repeat;/* background:#c9c9c9; background:-webkit-gradient(linear,left top,left bottom,from(#c9c9c9),to(#7f7f7f)); background:-moz-linear-gradient(top,#c9c9c9,#7f7f7f); background:transparent\9; filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#c9c9c9',endColorstr='#7f7f7f'); border:1px solid #9b9b9b; */}
.button .input_button_none:hover{background:#6a6969; background:-webkit-gradient(linear,left top,left bottom,from(#6a6969),to(#807e7e)); background:-moz-linear-gradient(top,#6a6969,#807e7e); background:transparent\9; filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#6a6969',endColorstr='#807e7e'); border:1px solid #666; }
.button .input_button_none:active { box-shadow:0 1px 2px rgba(0,0,0,0.3) inset; }
.button .input_button:hover{background-color:#1b6fc5; background:-webkit-gradient(linear,left top,left bottom,from(#4b90d7),to(#1b6fc5)); background:-moz-linear-gradient(top,#4b90d7,#1b6fc5); background:transparent\9; filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#4b90d7',endColorstr='#1b6fc5'); border:1px solid #1259a2; }
.button .input_button:active { box-shadow:0 1px 2px rgba(0,0,0,0.3) inset; }
/*error*/
.error{ padding-left:5px; color:#c00; }

.ez-radio{ background: url(${ctx}/styles/system/skins/mac/images/checkbox-blue.png) no-repeat scroll 0 0 transparent;
    display: inline-block;
    height: 22px;
    margin: 8px 6px 0 12px;
    width: 22px; float:left;}
.ez-selected {
	 
    background-position: 0 -26px;
}
/*#radio{background:url(${ctx}/styles/system/skins/mac/images/radio.png) no-repeat;z-index:999;}*/

.ez-hide {
    opacity: 0;
}
.ea_label{width:100px; line-height:40px; float:left;}
-->
</style>
</head>
<body onLoad="nameFocus(); ">
<form id="ucenter_admin_member_form" name="auth_admin_member_form" method="post" action="${ctx}/admin/authUser/doLogin">
  <input type="hidden" name="_method" value="post" />
  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td align="center" valign="middle"><table width="100%" height="100%" valign="middle" border="0" cellpadding="0" cellspacing="0" class="contents">
       <div style="width:665px; margin:0 auto; padding:0 0 30px 0;"><img src="${ctx}/styles/system/skins/mac/images/login_qmt.png" width="665" height="104"></div>
          <tr>
            <td class="con_header" align="left">管理员登录</td>
          </tr>
          <tr>
            <td align="center" class="con_wrapper" valign="top">            
                <ul class="Manager">
                    <li class="userlogin">
                      <label class="caption logint"></label>
                      <span class="input error"><c:if test="${flash.error != null}"> ${flash.error} </c:if></span>
                    </li>
                    <li>
                      <label class="caption">用户名：</label>
                      <span class="input"><input name="userName" id="userName" type="text" class="text input_out {required:true,messages:{required:'用户名不能为空！'}}" tabIndex="1" value='' /></span>
                    </li>
                    <li>
                      <label class="caption">密　码：</label>
                      <span class="input"><input name="password" id="password" class="text input_out {required:true,messages:{required:'密码不能为空！'}}" type="password" tabIndex="2" value='' /></span>
                    </li>
                    <li class="userradio">
                      <label class="caption"> </label>
                      <span class="input">
                      <div class="ez-radio"><input type="radio" class="ez-hide" checked="checked" name="code" value="2" style="cursor:pointer;"/></div><label class="ea_label">后台管理员&nbsp;&nbsp;</label>
                      <div class="ez-radio ez-selected"><input type="radio" name="code" value="5" class="ez-hide" style="cursor:pointer;"/></div><label class="ea_label">系统管理员</label></span>
                    </li>
                  </ul>                  
                  <div class="button"><input name="button" type="submit" tabIndex="4" value="登 录" class="input_button" />&nbsp;&nbsp;&nbsp;<input name="Input" type="reset" value="重 置" class="input_button_none" onClick="nameGetFocus(); "  tabIndex="5" /></div>
              </td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
<script language="javascript" type="text/javascript">
$("#auth_admin_member_form").validate(); function nameFocus(){
	var name = document.getElementById('userName');
	setTimeout(function(){name.focus(); },0);
};
$(".text").each(function(){
	$(this).bind('mouseenter mouseleave', function(){
  		$(this).toggleClass('input_move');
	});
});
</script>
</body>
</html>
