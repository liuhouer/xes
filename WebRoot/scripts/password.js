/**
 * @params options
 * 		passwordInput 密码输入框对象的ID
 * 		checkInput 密码确认框的ID
 * 		strengthInfoText 密码强度文字显示信息ID
 * 		strengthInfoBar 密码强度条形信息显示ID
 * 		checkInfoText 密码校验信息（包括出错信息等）
 * 		mustInput:true,
 * 		verdects:["弱","中","强"] 密码强度级别
 * 		colors:["#f00","#ff9933","#3c0"] 不同级别显示的颜色
 * 		infoBarBackground:'Gray' 密码强度条形信息的初始颜色
 * 		scores:[10,25] 通过分数段来区分强度
 * 		common:["123456","12345678"] 一些需要重要提醒为弱密码的密码，
 * 		minLength:6 密码的最小长度为6
 * 		maxLength:18 密码的最大长度为18
 * 
 * description:密码输入组件，校验确认密码是否正确，校验密码的强度
 */
(function($){
	var options={
		passwordInput:'',
		checkInput:'',
		strengthInfoText:'',
		strengthInfoBar:'',
		checkInfoText:'',
		mustInput:true,
		verdects:["弱","中","强"],
		colors:["#f00","#ff9933","#3c0"],
		infoBarBackground:'Gray',
		scores:[10,25],
		common:["123456","12345678"],
		minLength:6,
		maxLength:18
	}
	$.fn.password=function(params){
		//1. 获得参数
		$.getOptions(params);
		//2.判断参数是否正确
		if(!$.checkParams()){
			alert("参数不正确");
			return;
		}
		//3.添加必须输入标志
		$.mustInput();
		//4.密码强度事件
		$.passwordStrength();
		//5.核对事件
		$("#"+options.checkInput).bind("keyup",$.checkPassword);
		$("#"+options.passwordInput).bind("keyup",$.checkPassword);
	}
	$.passwordStrength=function(){
		if(document.getElementById(options.passwordInput)==null)
			return;
		$("#"+options.passwordInput).bind("keyup",$.checkStrength);
	}
	$.checkPassword=function(){
		var passwordInput = $("#"+options.passwordInput);
		var checkInfoText = $("#"+options.checkInfoText);
		var passwordInput = $("#"+options.passwordInput);
		var checkInput = $("#"+options.checkInput);
		if(passwordInput.attr("value").length == 0 || checkInput.attr("value").length == 0)
			return false;
		if(passwordInput.attr("value").length<options.minLength){
			checkInfoText.html("密码长度小于最小长度"+options.minLength+"，请重新输入！");
			checkInfoText.addClass("pw_info_error");
			checkInfoText.removeClass("pw_info_right");
			return false;
		}
		if(passwordInput.attr("value").length>options.maxLength){
			checkInfoText.html("密码长度大于最大长度"+options.maxLength+"，请重新输入！");
			checkInfoText.addClass("pw_info_error");
			checkInfoText.removeClass("pw_info_right");
			return false;
		}
		if(passwordInput.attr("value")!=checkInput.attr("value") &&options.checkInfoText!=""){
			checkInfoText.html("密码与确认密码不匹配，请重新输入确认密码！");
			checkInput.addClass("input_error");
			checkInfoText.addClass("pw_info_error");
			checkInfoText.removeClass("pw_info_right");
			return false;
		}else{
			checkInfoText.html("");
			checkInfoText.addClass("pw_info_right");
			checkInfoText.removeClass("pw_info_error");
			checkInput.removeClass("input_error");
		}
		var score = $.getValue(passwordInput.attr("value"));
		//$("#score").html(score);
		if(score>=options.scores[0]){
			return true;
		}else{
			return false;
		}
	}
	$.checkStrength=function(){
		var value = $('#'+options.passwordInput).attr("value");
		var score=$.getValue(value);
		//分数怎么和表现关联
		var text=null;
		var color=null;
		var barLength=null;
		if(score<0){
			text="太短啦";
			color="gray";
			barLength='0%';
		}else if(score>=0 && score<options.scores[0]){
			text=options.verdects[0];
			color=options.colors[0];
			barLength='33%';
		}else if(score>=options.scores[0] && score<options.scores[1]){
			text=options.verdects[1];
			color=options.colors[1];
			barLength='66%';
		}else if(score>=options.scores[1]){
			text=options.verdects[2];
			color=options.colors[2];
			barLength='100%';
		}
		if(options.strengthInfoText!="")
			$("#"+options.strengthInfoText).html(text).css({
				color:color
			});
		if(options.strengthInfoBar!="")
			$("#"+options.strengthInfoBar).css({
				width:barLength,
				backgroundColor:color
			});
	}
	$.getValue=function(_value){
		var score = 0;
		var num=$.countCharNum(_value);
		// 如果是普通密码则设置为0
		for(var i=0;i<options.common.length;i++){
			if(_value==options.common[i])
				return 0;
		}
		if(_value.length<options.minLength)
			return -100;
		//else
		//	score+=(_value.length-options.minLength)*2;
		//score+=num*2;
		// 有小写字母有数字
		if(_value.match(/[a-z]/)){score+=3}
		// 有大写字母有数字
		if(_value.match(/[A-Z]/)){score+=5}
		// 有数字
		if(_value.match(/\d+/)){score+=2}
		// 一个特殊字符
		if(_value.match(/.[!,@,#,$,%,^,&,*,?,_,~]/)){score+=5}
		// 两个以上特殊符号
		if(_value.match(/(.*[!,@,#,$,%,^,&,*,?,_,~].*[!,@,#,$,%,^,&,*,?,_,~])/)){score+=15}
		return score;
	}
	$.countCharNum=function(_value){
		var charValue=[];
		for(var i=0;i<_value.length;i++){
			if(charValue.join().indexOf(_value.charAt(i))!=-1)
				continue;
			charValue.push(_value.charAt(i));
		}
		return charValue.length;
	}
	$.checkParams=function(){
		if(document.getElementById(options.checkInput)==null || 
			document.getElementById(options.passwordInput)==null)
			return false;
		return true;
	}
	$.mustInput=function(){
		if(!options.mustInput)return;
	}
	$.getOptions=function(params){
		for(var property in params){
			options[property]=params[property];
		}
	}
})(jQuery)