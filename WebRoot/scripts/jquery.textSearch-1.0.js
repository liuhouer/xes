// by zhangxixnu 2010-06-21  welcome to visit my personal website http://www.zhangxinxu.com/
// textSearch.js v1.0 文字，关键字的页面纯客户端查询
// 2010-06-23 修复多字母检索标签破碎的问题
// 2010-06-29 修复页面注释显示的问题
// 不论何种情况，务必保留作者署名。 
/*
使用方法是textSearch，具体为：$(选择器). textSearch(String,可选参数)
divFlag	true	布尔型，true表示对字符串进行多关键字处理，false表示不处理，整个字符串看成1个关键字
//divStr			" "	字符串，表示分割多个关键字的字符，默认为空格，如果divFlag为false，此参数将失效
markClass	""	代码高亮的class类，默认为没有样式，如果设置样式，将覆盖默认的红色高亮颜色值
markColor	"red"	字符串，指高亮文字的颜色值，默认红色。markClass不为空，则此参数失效。
nullReport	true	布尔型，表示当查询结果为空时，是否弹出提示信息。默认弹出。
callback	return false;	回调函数，默认无效果。当存在查询结果时执行。
*/


(function($){
	$.fn.textSearch = function(str,options){
		var defaults = {
			divFlag: true,
			//divStr: " ",
			markClass: "",
			markColor: "red",
			nullReport: true,
			callback: function(){
				return false;	
			}
		};
		var sets = $.extend({}, defaults, options || {}), clStr;
		if(sets.markClass){
			clStr = "class='"+sets.markClass+"'";	
		}else{
			clStr = "style='color:"+sets.markColor+";'";
		}
		
		//对前一次高亮处理的文字还原
		$("span[rel='mark']").removeAttr("class").removeAttr("style").removeAttr("rel");
		
		
		//字符串正则表达式关键字转化
		$.regTrim = function(s){
			var imp = /[\^\.\\\|\(\)\*\+\-\$\[\]\?]/g;
			var imp_c = {};
			imp_c["^"] = "\\^";
			imp_c["."] = "\\.";
			imp_c["\\"] = "\\\\";
			imp_c["|"] = "\\|";
			imp_c["("] = "\\(";
			imp_c[")"] = "\\)";
			imp_c["*"] = "\\*";
			imp_c["+"] = "\\+";
			imp_c["-"] = "\\-";
			imp_c["$"] = "\$";
			imp_c["["] = "\\[";
			imp_c["]"] = "\\]";
			imp_c["?"] = "\\?";
			s = s.replace(imp,function(o){
				return imp_c[o];					   
			});	
			return s;
		};
		$(this).each(function(){
			var t = $(this);
			str = $.trim(str);
			if(str === ""){
				//alert("关键字为空");	
				return false;
			}else{
				//将关键字push到数组之中
				var arr = null;
				if(sets.divFlag){
					arr = AnalyzeHighLightWords(str);	
				}else{
					arr = str;	
				}
			}
			var v_html = t.html();
			//删除注释
			v_html = v_html.replace(/<!--(?:.*)\-->/g,"");
			
			//将HTML代码支离为HTML片段和文字片段，其中文字片段用于正则替换处理，而HTML片段置之不理
			var tags = /[^<>]+|<(\/?)([A-Za-z]+)([^<>]*)>/g;
			var a = v_html.match(tags), test = 0;
			$.each(a, function(i, c){
				if(!/<(?:.|\s)*?>/.test(c)){//非标签
					//开始执行替换
					var reg = new RegExp("("+arr+")", "gi");
					if(reg.test(c)){
						//正则替换
						//c = c.replace(reg,"♂"+con+"♀");
						c = c.replace(reg,"<span rel='mark' "+clStr+">$1</span>");    
						test = 1;
					}
					//c = c.replace(/(♂)+/g,"♂").replace(/(♀)+/g,"♀").replace(/♀♂/g,"");
					//c = c.replace(/♂/g,"<span rel='mark' "+clStr+">").replace(/♀/g,"</span>");
					a[i] = c;
				}
			});
			//将支离数组重新组成字符串
			var new_html = a.join("");
			
			$(this).html(new_html);
			
			if(test === 0 && sets.nullReport){
				alert("没有查询结果");	
				return false;
			}
			
			//执行回调函数
			sets.callback();
		});
	};
})(jQuery);


//----------分析关键词----------------------
function AnalyzeHighLightWords(hlWords)
{
    if(hlWords==null) return "";
    hlWords=hlWords.replace(/\s+/g,"|").replace(/\|+/g,"|");           
    hlWords=hlWords.replace(/(^\|*)|(\|*$)/g, "");
   
    if(hlWords.length==0) return "";
    var wordsArr=hlWords.split("|");
   
    if(wordsArr.length>1){
        var resultArr=BubbleSort(wordsArr);
        var result="";
        for(var i=0;i<resultArr.length;i++){
            result=result+"|"+resultArr[i];
        }               
        return result.replace(/(^\|*)|(\|*$)/g, "");

    }else{
        return hlWords;
    }
}   

//-----利用冒泡排序法把长的关键词放前面-----   
function BubbleSort(arr){       
    var temp, exchange;   
    for(var i=0;i<arr.length;i++){           
        exchange=false;               
        for(var j=arr.length-2;j>=i;j--){               
            if((arr[j+1].length)>(arr[j]).length){                   
                temp=arr[j+1]; arr[j+1]=arr[j]; arr[j]=temp;
                exchange=true;
            }
        }               
        if(!exchange)break;
    }
    return arr;           
}