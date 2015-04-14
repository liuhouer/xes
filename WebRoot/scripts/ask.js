(function($) {
 
$.extend({
	setCorrectAnswer : function(id){
		confirm("确定将该答案设置为正确答案吗？",function(){
			$.ajax({
				type : "POST",
				url : "/ask/reply/do.jsp",
				data : "method=setCorrectAnswer&id="+id,
				dataType:"json",
				success : function(data){
					if(data.result == "success"){
						window.location.reload();
					}else if(data.result == "login"){
						alert(data.message,2,function(){
							window.location.href="/ucenter/member/login";
						});
					}else{
						alert(data.message+"11");
					}
					//window.location.href="/ask/question/do.jsp?method=view&id="+id;
				},
				error : function(){
					alert("您的操作有误；");
				}	
			});
		});
	},
	closeAskQuestion : function(id){
		confirm("确定关闭该问题吗？",function(){
			$.ajax({
				type : "POST",
				url : "/ask/question/do.jsp",
				data : "method=closeAskQuestion&id="+id,
				dataType:"json",
				success : function(data){
					if(data.result == "success"){
						alert("操作成功",2,function(){window.location.reload();});
					}else if(data.result == "login"){
						alert(data.message,2,function(){
							window.location.href="/ucenter/member/login";
						});
					}else{
						alert(data.message+"");
					}
					//window.location.href="/ask/question/do.jsp?method=view&id="+id;
				},
				error : function(){
					alert("您的操作有误；");
				}	
			});
		});
	}
});

$.fn.extend({
 
 
});

})(jQuery);