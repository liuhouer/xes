var fileIco = {"ext.xls":"2", "ext.xlsx":"2", "ext.csv":"2", "ext.doc":"3", "ext.docx":"3", "ext.wps":"3", "ext.ppt":"4", "ext.pptx":"4", "ext.7z":"5", "ext.rar":"6", "ext.cab":"6", "ext.jar":"6", "ext.arj":"6", "ext.tar":"6", "ext.gz":"6", "ext.tgz":"6", "ext.zip":"7", "ext.iso":"8", "ext.img":"8", "ext.nrg":"8", "ext.mdf":"8", "ext.mds":"8", "ext.eml":"9", "ext.htm":"10", "ext.html":"10", "ext.shtml":"10", "ext.xhtml":"10", "ext.mht":"10", "ext.asp":"10", "ext.aspx":"10", "ext.js":"11", "ext.css":"12", "ext.ini":"12", "ext.inf":"12", "ext.xml":"12", "ext.hlp":"13", "ext.chm":"13", "ext.exe":"14", "ext.app":"14", "ext.com":"14", "ext.bat":"15", "ext.cmd":"15", "ext.ttf":"16", "ext.fon":"16", "ext.pdf":"17", "ext.psd":"18", "ext.pdd":"18", "ext.ps":"18", "ext.ai":"19", "ext.eps":"19", "ext.fla":"20", "ext.swf":"21", "ext.flv":"21", "ext.txt":"22", "ext.java":"22", "ext.jsp":"22", "ext.c":"22", "ext.cpp":"22", "ext.h":"22", "ext.hpp":"22", "ext.py":"22", "ext.cs":"22", "ext.sh":"22", "ext.rtf":"23", "ext.reg":"24", "ext.ra":"25", "ext.ram":"25", "ext.rpm":"25", "ext.rmx":"25", "ext.rm":"25", "ext.rmvb":"25", "ext.wm":"26", "ext.wma":"26", "ext.wmv":"26", "ext.asf":"26", "ext.wmp":"26", "ext.mov":"27", "ext.qt":"27", "ext.3gp":"27", "ext.3gpp":"27", "ext.amr":"27", "ext.avi":"28", "ext.mkv":"28", "ext.mp4":"28", "ext.mpg":"28", "ext.mpeg":"28", "ext.vob":"28", "ext.dat":"28", "ext.ts":"28", "ext.tp":"28", "ext.m2ts":"28", "ext.evo":"28", "ext.pmp":"28", "ext.vp6":"28", "ext.ivf":"28", "ext.dsm":"28", "ext.dsv":"28", "ext.dsa":"28", "ext.dss":"28", "ext.fli":"28", "ext.flc":"28", "ext.flic":"28", "ext.roq":"28", "ext.mpa":"29", "ext.m1a":"29", "ext.m2a":"29", "ext.mp2":"29", "ext.mp3":"29", "ext.ac3":"29", "ext.dts":"29", "ext.ddp":"29", "ext.flac":"29", "ext.ape":"29", "ext.mac":"29", "ext.apl":"29", "ext.shn":"29", "ext.tta":"29", "ext.wv":"29", "ext.cda":"29", "ext.wav":"29", "ext.aac":"29", "ext.m4a":"29", "ext.mka":"29", "ext.ogg":"29", "ext.mpc":"29", "ext.mp+":"29", "ext.mpp":"29", "ext.au":"29", "ext.aif":"29", "ext.aifc":"29", "ext.aiff":"29", "ext.mid":"29", "ext.midi":"29", "ext.rmi":"29", "ext.todo":"30", "ext.jpg":"31", "ext.jpeg":"31", "ext.gif":"32", "ext.png":"33", "ext.bmp":"34", "ext.dib":"34", "ext.rle":"34", "ext.tif":"35", "ext.tiff":"35", "ext.xif":"35", "ext.emf":"36", "ext.wmf":"37", "ext.ico":"38", "ext.icl":"38", "ext.cur":"38", "ext.ani":"38", "ext.raw":"39", "ext.mos":"39", "ext.fff":"39", "ext.032":"39", "ext.bay":"39", "ext.cdr":"40", "ext.csl":"40", "ext.cmx":"40", "ext.wpg":"40", "ext.dll":"41", "ext.ocx":"41", "ext.chk":"41", "ext.manifest":"41", "ext.torrent":"42"};
var imgFile = {"ext.bmp":1, "ext.png":1,"ext.gif":1,"ext.jpeg":1,"ext.jpg":1,"ext.ico":1};
var textFile = {"ext.html":2,"ext.htm":2,"ext.txt":2,"ext.css":2,"ext.js":2};
var zipFile = {"ext.zip":3};
var actionHtml = {
		zipDownload:'<a href="{value}">\u6253\u5305\u4e0b\u8f7d</a>',
		download:'<a href="{value}">\u4e0b\u8f7d</a>',
		rename:'<a href="javascript:;" onclick="show(\'{value}\',\'文件改名\',600)">\u6539\u540d</a>',
		delFile:'<a href="javascript:;" onclick="delFile(\'{value}\')">\u5220\u9664</a>',
		imgView:'<a href="{value}" rel=\"clearbox\" target=\"_blank\">\u9884\u89c8</a>',
		editFile:'<a href="javascript:;" onclick="show(\'{value}\',\'编辑文件\',820)">\u7f16\u8f91</a>',
		unzip:'<a href="javascript:;" onclick="unzipFile(\'{vlaue}\')">\u89e3\u538b\u5230\u5f53\u524d\u76ee\u5f55</a>'
	}
var actionForm = "#admin_tools_file_page_form";
function El() {}
function EV() {}
El.descendantOf = fElementDescendantOf;
EV.getEvent = fGetEvent;
function $getE() {
	var d = [];
	var c = arguments.length;
	for (var b = 0; b < c; b++) {
		var a = arguments[b];
		if (typeof a == "string") {
			a = document.getElementById(a);
		}
		fExtendObj(El, a);
		if (c == 1) {
			return a;
		}
		d.push(a);
	}
	return d;
}
function fExtendObj(a, b) {
	for (var d in a) {
		try {
			if (!b[d]) {
				b[d] = a[d];
			}
		}
		catch (c) {
		}
	}
}
function fGetEvent(h) {
	var g = h || window.event || top.event;
	if (!g) {
		var d = [];
		var j = this.getEvent.caller;
		while (j) {
			g = j.arguments[0];
			if (g && Event == g.constructor) {
				break;
			}
			var a = false;
			for (var f = 0; f < d.length; f++) {
				if (j == d[f]) {
					a = true;
					break;
				}
			}
			if (a) {
				break;
			} else {
				d.push(j);
			}
			j = j.caller;
		}
	}
	return g;
}
function fElementDescendantOf(b, a) {
	if (!a) {
		a = b;
		element = this;
	} else {
		element = b;
	}
	element = $getE(element), a = $getE(a);
	while (element) {
		if (element == a) {
			return true;
		}
		element = element.parentNode;
	}
	return false;
}
function fCheckIsOnMouseOver(c, a) {
	try {
		var a = a || EV.getEvent();
		var f = a.toElement || a.target;
		var d = a.fromElement || a.relatedTarget;
		if (El.descendantOf(f, c) && !El.descendantOf(d, c)) {
			return true;
		} else {
			return false;
		}
	}
	catch (b) {
		return true;
	}
}
function fCheckIsOnMouseOut(c, a) {
	try {
		var a = a || EV.getEvent();
		var f = a.toElement || a.relatedTarget;
		var d = a.fromElement || a.target;
		if (!El.descendantOf(f, c) && El.descendantOf(d, c)) {
			return true;
		} else {
			return false;
		}
	}
	catch (b) {
		return true;
	}
}
function fWpGetType() {
	return "list";
}
function fWpDoAllCheck(f) {
	var h = f.checked;
	var d = $getE("listBody");
	var a = d.getElementsByTagName("INPUT");
	for (var b = 0; b < a.length; b++) {
		a[b].checked = h;
		var c = a[b].parentNode.parentNode;
		if (h) {
			c.style.backgroundColor = "#FFFFCA";
		} else {
			c.style.backgroundColor = "";
		}
	}
	if (h) {
		gSelectCount = gAllCount;
	} else {
		gSelectCount = 0;
	}
}
function fWpInitList() {
	fWpInitListTr();
}
function fWpInitListTr() {
	var d = $getE("listBody");
	if (!d) {
		return;
	}
	var c = d.getElementsByTagName("tr");
	var e = c.length;
	for (var a = 0; a < e; a++) {
		var b = c[a];
		var te = $(b).children("td[showColumn='name']");
		var name = $(te[0]).text();
		name = name.replace(/(^\s*)|(\s*$)/g, "");
		var p = (name.replace(/(.|\n)*\./, "ext.")).replace(/\s+/g,"").replace(" ", "");
		var type = $(b).find("#filetype");
		var filetype = 0;//0文件夹 1图片 2可编辑文件 
		if($(type[0]).text()!=2){
			var x = fileIco[p];
			$(te[0]).prepend("<b class=\"ico ico-file ico-file-" + x + "\"></b>")
			if(p.indexOf("ext.")>=0){
				if(imgFile[p]){
					filetype = imgFile[p];
				}else if(textFile[p]){
					filetype = textFile[p];
				}else if(zipFile[p]){
					filetype = zipFile[p];
				}else{
					filetype = 4;
				}
			}
		}else{
			$(te[0]).prepend("<b class=\"ico ico-file ico-file-dir\"></b>")
		}
		
		var m = "[ ";
		var v = $(b).find("input[id='items']");
		if(v[0]){
			if (filetype == 0) {
				//打包下载 更名 
				m += actionHtml.zipDownload.replace(/{value}/, ctxUrl+"/pages/admin/tools/file/download.jsp?items="+v[0].value) +
					 " | " + actionHtml.rename.replace(/{value}/, ctxUrl+"/admin/tools/file/edit?file="+v[0].value) ;
				var fcunt = $(te[0]).find(".txt-info").text();
				if(fcunt.match(/\d+/g)<1){
					m += " | " + actionHtml.delFile.replace(/{value}/,v[0].value) ;
				}
			} else if(filetype == 1){
				//下载 预览 更名 删除
				m += actionHtml.download.replace(/{value}/, ctxUrl+"/pages/admin/tools/file/download.jsp?items="+v[0].value) +
					 " | " + actionHtml.imgView.replace(/{value}/,v[0].value) +
					 " | " + actionHtml.rename.replace(/{value}/, ctxUrl+"/admin/tools/file/edit?file="+v[0].value) +
					 " | " + actionHtml.delFile.replace(/{value}/,v[0].value) ;
			} else if(filetype == 2){
				//下载 编辑 更名 删除
				m += actionHtml.download.replace(/{value}/, ctxUrl+"/pages/admin/tools/file/download.jsp?items="+v[0].value) +
					 " | " + actionHtml.editFile.replace(/{value}/, ctxUrl+"/admin/tools/file/editFile?filePath="+v[0].value)+
					 " | " + actionHtml.rename.replace(/{value}/, ctxUrl+"/admin/tools/file/edit?file="+v[0].value) +
					 " | " + actionHtml.delFile.replace(/{value}/,v[0].value);
			} else if(filetype ==3){
				//下载 解压到当前目录 更名 删除
				m += actionHtml.download.replace(/{value}/, ctxUrl+"/pages/admin/tools/file/download.jsp?items="+v[0].value) +
					 " | " + actionHtml.unzip.replace(/{value}/, v[0].value) +
					 " | " + actionHtml.rename.replace(/{value}/, ctxUrl+"/admin/tools/file/edit?file="+v[0].value) +
					 " | " + actionHtml.delFile.replace(/{value}/,v[0].value);
			}else {
				//下载 更名 删除
				m += actionHtml.download.replace(/{value}/, ctxUrl+"/pages/admin/tools/file/download.jsp?items="+v[0].value) +
					 " | " + actionHtml.rename.replace(/{value}/, ctxUrl+"/admin/tools/file/edit?file="+v[0].value) +
					 " | " + actionHtml.delFile.replace(/{value}/,v[0].value);
			}
			m += "]";
			var j = b.getElementsByTagName("SPAN")[1];
			j.innerHTML = m;
			
			b.onmouseover = (function (f) {
				return function () {
					if (!fCheckIsOnMouseOver(f)) {
						return;
					}
					//f.style.backgroundColor = "#FFFFE1";
					var j = f.getElementsByTagName("SPAN")[1];
					$(j).removeClass("disspan");
				};
			})(b);
			
			b.onmouseout = (function (f) {
				return function () {
					if (!fCheckIsOnMouseOut(f)) {
						return;
					}
					//var h = f.getElementsByTagName("INPUT")[0];
					//if (h && h.checked) {
						//f.style.backgroundColor = "#FFFFCA";
					//} else {
						//f.style.backgroundColor = "";
					//}
					var g = f.getElementsByTagName("SPAN")[1];
					$(g).addClass("disspan");
				};
			})(b);
		}
	}
}

function delFile(vlaue){
	confirm("此文件删除后不可恢复！！",function(){
	 	$.ajax({
	       type: "POST",
	       url: delUrl,
	       cache: false,
	       data: "file=" + vlaue,
	       success: onBackMsg,
	       error: onBackMsg
	    })
	});
}

function unzipFile(vlaue){
	confirm('\u538b\u7f29\u6587\u4ef6\u4e0d\u80fd\u5305\u542b\u4e2d\u6587\uff0c\u89e3\u538b\u6587\u4ef6\u4f1a\u8986\u76d6\u540c\u540d\u6587\u4ef6\uff0c\u662f\u5426\u786e\u8ba4\u64cd\u4f5c\uff1f',function(){
	 	$.ajax({
	       type: "POST",
	       url: unzipUrl,
	       cache: false,
	       data: "zipFile=" + vlaue,
	       success: function(jsonDatas){
				alert("解压成功！",2,reloadList);
			}
	    })
	});
}



function onBackMsg(result){
	result = eval("(" + result + ")");
	if(result.success){
		alert("删除成功！",2,reloadList);
	}else{
		if(result.state==2)alert("删除失败，目录不为空！");
		else if(result.state==1)alert("文件已不存在！",3,reloadList);
		else if(result.state==0)alert("参数错误！");
	}
}

function reloadList(){
	window.location.reload();
}
