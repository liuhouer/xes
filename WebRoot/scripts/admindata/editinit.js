/**
 * @author wqtan
 */
(function($) {
	function loadAdminData(opt){
		var scripts = $("script[src]");
		var loadJs = [
			"/scripts/main.js",
			"/scripts/weebox/bgiframe.js",
			"/scripts/weebox/weebox.js",
			"/scripts/admindata/admindata.js"
		];
		var loadCss = [
			"/scripts/weebox/weebox.css",
			"/scripts/admindata/admindata.css"
		];
		
		for(var i=0;i<loadCss.length;i++){
			$(document.body).append("<link href='"+loadCss[i]+"' type='text/css' rel='stylesheet' />");
		}
		
		for(var i=0;i<loadJs.length;i++){
			var isLoaded = false;
			for(var j=0;j<scripts.length;j++){
				var _js = scripts[j].src;
				if(_js.indexOf("?") != -1){
					_js = _js.substr(0,_js.indexOf("?"));
				}
				if(_js == loadJs[i]){
					isLoaded = true;
					break;
				}
			}
			if(!isLoaded){
				$(document.body).append("<script type='text/javascript' src='"+loadJs[i]+"'></script>");
			}
		}
		
		var timeoutnum = 20;
		var loadInterval = window.setInterval(function(){
			if(typeof($.adminDatas) != "undefined" 
				&& typeof($.weeboxs) != "undefined" ){
				window.clearInterval(loadInterval);
				$.adminDatas.initAdminData();
			}
			if(timeoutnum <= 0){
				window.clearInterval(loadInterval);
				alert("无法初始化组件，请检查网络连接，或者刷新页面重试。");
			}else{
				timeoutnum = timeoutnum - 1;
			}
		},100);
	}
	
	// 初始化
	$(document).ready(function(){
		var _href = window.location.href;
		if(_href.indexOf("?") != -1){
			_href = _href.substr(_href.indexOf("?")+1);
			var params = _href.split("&");
			var opt = "{";
			for(var i=0;i<params.length;i++){
				var param = params[i];
				var key = "";
				var value = "";
				if(param.indexOf("=") == -1){
					key = param;
				}else{
					key = param.substr(0,param.indexOf("="));
					value = param.substr(param.indexOf("=")+1);
				}
				opt += "\""+key+"\":\""+value+"\"";
				if(i < params.length-1){
					opt += ",";
				}
			}
			opt += "}";
			opt = eval("("+opt+")");
			//if(typeof(opt.dataEdit)!="undefined" 
			//	&& opt.dataEdit!=null && opt.dataEdit!=""){
			loadAdminData(opt);
			//}
		}
	});
})(jQuery);
