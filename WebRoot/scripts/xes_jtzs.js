	var endtimes=new Array();//结束时间                  
	var nowtimes;
	function givetime(){
		nowtimes = new Date();//当前服务器时间
		window.setTimeout("DownCount()",1000)
	}
	function DownCount(){
	    nowtimes = Number(nowtimes) + 1000;
		for (var i = 0; i < endtimes.length; i++){
			var theDay = endtimes[i];
			if(theDay.time <= nowtimes){
				$("#times" + theDay.id).innerHTML = "00分00秒" ;
			}else{
				timechange(theDay.time,theDay.id);
			}
		}
		window.setTimeout("DownCount()",1000)
	}
	function timechange(theDay,i){
		var theDays= theDay;
		var seconds = (theDays - nowtimes)/1000;
		var minutes = Math.floor(seconds/60);
		var hours = Math.floor(minutes/60);
		var days = Math.floor(hours/24);
		var CDay= days;
		var CHour= hours % 24;
		var CMinute= minutes % 60;
		var CSecond= Math.floor(seconds % 60);
		var CHour = CHour + CDay * 24;
		
		if(CMinute<10){
			CMinute="0"+CMinute;
		}
		if(CHour<10){
			CHour="0"+CHour;
		}
		if(CSecond<10){
			CSecond="0"+CSecond;
		}
		if(isNaN(CMinute)){
			CMinute = "00";
		}
		if(isNaN(CSecond)){
			CSecond = "00";
		}
		var item = $("#times" + i);
		if(null!=item){
			item.text(CMinute + "分" + CSecond + "秒");
		}
	}
	function innerendtime(index, endtime) {
		var obj = new Object;
		obj.id = index;
		obj.time = endtime;
	    endtimes.push(obj);
	}
	
	//无图处理 
	function ErrNoImg(ImgD) {//暂无图片
		ImgD.onerror = null;
		ImgD.src = "/images/nipic.png";
	}
	function DefaultSamllHandPic(ImgD) {//默认小头像
		ImgD.onerror = null;
		ImgD.src = "/images/defaultM.png";
	}
	function DefaultBigHandPic(ImgD) {//默认大头像
		ImgD.onerror = null;
		ImgD.src = "/images/defaultM.png";
	}
	
	//等比处理图片
	function DrawImageForWidth(ImgD, w) {
		var image = new Image();
		image.src = ImgD.src;
		if (image.width > 0 && image.height > 0 && image.width > w) {
			ImgD.width = w;
		}
	}
	function DrawImageForHeigth(ImgD, h) {
		var image = new Image();
		image.src = ImgD.src;
		if (image.width > 0 && image.height > 0 && image.height > h) {
			ImgD.height = h;
		}
	}
	//根据宽高处理图片
	function DrawImage(ImgD, w, h) {
		var image = new Image();
		image.src = ImgD.src;
		if (image.width > 0 && image.height > 0) {
			if (image.width / image.height >= w / h) {
				if (image.width > w) {
					ImgD.width = w;
					ImgD.height = (image.height * w) / image.width;
				} else {
					ImgD.width = image.width;
					ImgD.height = image.height;
				}
			} else {
				if (image.height > h) {
					ImgD.height = h;
					ImgD.width = (image.width * h) / image.height;
				} else {
					ImgD.width = image.width;
					ImgD.height = image.height;
				}
			}
		}
	}
	
	function pageMore(){
		if (YZYPROFILE.ajaxUrl) {
			$.ajax({
				url : YZYPROFILE.ajaxUrl,
				type : "post",
				dataType : "json",
				data : "range="+YZYPROFILE.range+"&split="+YZYPROFILE.split+"&start="+YZYPROFILE.start,
				success : function(jsondatas){
					var data = eval(jsondatas);
					if(jsondatas.type=="my"){
						$.template('template', questionTmpl); 
					}else if(jsondatas.type=="new"){
						$.template('template', questionTmpl); 
					}else if(jsondatas.type=="tea"){
						$.template('template', questionTmpl+getAnswerTmpl);
					}else if(jsondatas.type=="exp"){
						$.template('template', questionTmpl+getAnswerTmpl); 
					}else if(jsondatas.type=="event"){
						$.template('template', eventTmpl); 
					}else if(jsondatas.type=="comment"){
						$.template('template', commentTmpl); 
					}else if(jsondatas.type=="answer"){
						$.template('template', questionTmpl); 
					}else if(jsondatas.type=="show"){
						$.template('template', commentTmpl); 
					}
					$.tmpl('template', data.result).appendTo('#jsonPageId'); 
					$("img.lazy").lazyload();
					YZYPROFILE.range = jsondatas.range;
					YZYPROFILE.split = jsondatas.split;
					YZYPROFILE.start = jsondatas.start;
				},
				error : function(){
				}
			});
		}
	}
	
	var questionTmpl = '<div class="display w100 border_top"></div><div class="con gradual" id="status=${status}_id=${id}">'+
				    '<div class="conr display">'+
				        '<div class="cleft">'+
				        	'<img src="/images/defaultM.png" class="lazy" data-original="${student.imgPath}" onerror="DefaultSamllHandPic(this)" />'+
				        '</div>'+
				        '<div class="cright">'+
				            '<div class="tops">'+
				                '<div class="lef">'+
				                  '<span class="name">${student.nickName}</span>'+
				                  '<span class="time">${addTimeStr}</span>'+
				                '</div>'+
				                '<div class="rig">'+
				                	'{{if type=="tea" || type=="exp"}}'+
					                	'{{if sourceType==0}}'+
						                	'<span class="teacher">学生提问</span>'+
					                	'{{else sourceType==1}} '+
						                	'<span class="teacher">专家作答</span>'+
										'{{else sourceType==2}}'+
						                	'<span class="teacher">老师放弃</span>'+
					                	'{{/if}}'+
					                '{{/if}}'+
									'{{if status!=2}}'+
					                  	'<input name="" class="noanswer m10" type="button" value="未解答">'+
					                '{{/if}}'+
									'{{if status==2}}'+
				            	    	'<input name="" class="answer m10" type="button" value="已解答">'+
					                '{{/if}}'+
				                '</div>'+
				            '</div>'+
				        '</div>'+
					'</div>'+
				    '<div class="conr display">'+
				        '<div class="neir">'+
				            '<span class="nr w100">'+
				            	'${content}'+
				            '</span>'+
				        '</div>'+
				        '<div class="neir">'+
				            '<span class="tu m4">'+
				            	'<img src="/images/nipic.png" showImg="1" class="lazy" data-original="${imgPath}" onerror="ErrNoImg(this)" />'+
				            '</span>'+
				        '</div>'+
				        '<div class="neir">'+
				            '<div class="bq">'+
				            	'<img src="/pages/jtzs/images/icon_label.png" />'+
				            	'<span class="f333">${grade}</span>'+
				            	'<span class="f666">${subject}</span>'+
				            '</div>'+
				            '{{if status!=2 && (type=="tea" || type=="exp" || type=="my")}}'+
				            	'<div class="manyi">'+
				            		'<img src="/pages/jtzs/images/icon_clock.png" />距解题完成还有'+
				            		'<span class="ff00 f14" id="times${id}" stopTime="${stopTime}">00分00秒</span>'+
				            	'</div>'+
				            '{{/if}}'+
				            '{{if status==2222}}'+
				               '<div class="manyis"><img src="/pages/jtzs/images/icon_unclear.png">问题不清晰</div>'+
				            '{{/if}}'+
				            '{{if status==2}}'+
					            '<div class="manyis">'+
					                '<ul>'+
					                 '<li class="bg_blue">'+
					                 	'<span class="w30"><img src="/pages/jtzs/images/icon_manyi.png"></span>'+
					                 	'<font>${satisfiedCount}</font>'+
					                 '</li>'+
					                 '<li class="bg_blue">'+
					                 	'<span class="w30"><img src="/pages/jtzs/images/icon_bumanyi.png"></span>'+
					                 	'<font>${unsatisfiedCount}</font>'+
					                 '</li>'+
					                 '<li class="bg_green">'+
					                 	'<span class="w30"><img src="/pages/jtzs/images/icon_liulan.png"></span>'+
					                 	'<font>${pageViewCount}</font>'+
					                 '</li>'+
					                '</ul>'+                
					            '</div>'+
				            '{{/if}}'+
				        '</div>'+            
				    '</div>'+
				'</div>'+
				'{{if stopTime > 0}}'+
				'<script >innerendtime(${id},${stopTime});</script>'+
				'{{/if}}';
				
	var eventTmpl = '<a href="/jtzs/commonData/${id}/eventShow">'+
						'<div class="con">'+
					    '<div class="dates center ffff f14"><fmt:formatDate value="${sendTimeDate}" pattern="yyyy-MM-dd HH:mm"/></div>'+
					    '<div class="activity m2">'+
					    	'<div class="pic"><img src="${imgPath }">'+
					        	'<div class="hotnews_img_title">${title}</div>'+
					        '</div>'+
					    	'<div class="read">'+
					    		'<a>阅读全文</a>'+
					    		'<a><img src="/pages/jtzs/images/icon.png"></div>'+
					    '</div>'+
					'</div>'+
					'</a>';
	var commentTmpl =  '<div class="con bgeee bore0e0e0">'+
						    '<div class="conr display">'+
						        '<div class="cleft"><img src="/images/defaultM.png" class="lazy" data-original="${student.imgPath}" onerror="DefaultSamllHandPic(this)"></div>'+
						        '<div class="cright">'+
						            '<div class="tops">'+
						                '<div class="lef2">'+
						                  '<span class="name">${student.nickName}</span><span class="time">${addTimeStr}</span>'+
						                '</div>'+
						                '<div class="con2">${content}</div>'+
						                '<div class="rig2 ff00 center">'+
						                	'{{if isSatisfied==1}}'+
							                	'<img src="/pages/jtzs/images/hand.png">'+
							                '{{/if}}'+
						                	'{{if isSatisfied!=1}}'+
							                	'<img src="/pages/jtzs/images/hand2.png">'+
						                 	'{{/if}}'+
						                '</div>'+
						            '</div>'+
						        '</div>'+
						    '</div>'+    
						'</div>';
	var getAnswerTmpl = '{{if status==0}}'+
							'<div class="neir">'+
								'<input type="button" value="抢答" id="${id}" class="answer_bg pointer" name="">'+
								'<span class="answer22">&nbsp;</span>'+
							'</div>'+
						'{{/if}}';
	