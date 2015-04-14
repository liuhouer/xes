/**
 * 数据编辑组件
 * datatype : 数据类型，映射数据类型及编辑地址，下一个版本会与内容模型对接
 如：一般后台默认tag地址为，/admin/模块/表/tab.jsp?id={id} CmsResource对应 cms_resource
 * dataid : 数据Id，页面有该标示dom元素，系统会逐一添加编辑事件
 * url : 编辑地址，如果需要传递参数id则使用{id}例如：/admin/cms/resource/edit?id={id}，系统会自动替换掉{id}为真实id
 * @author wqtan
 */
(function($) {
	var AdminData = function(index,view) {
		var self = this;
		self.closeTimeout = null;
		self.view = view;
		self.index = index;
		self.datatype = isNull($(view).attr("datatype")) ? "" : $(view).attr("datatype");
		self.dataid = isNull($(view).attr("dataid")) ? "" : $(view).attr("dataid");
		self.url = isNull($(view).attr("url")) ? "" : $(view).attr("url");
		
		this.getEditMenu = function(){
			var result = "editor_"+self.index;
			if(isNull($("#"+result)) || isNull($("#"+result).attr("tagName"))){
				var editorHtml = "<div class='editor' style='display: none;' id='"+result+"'>";
					editorHtml += 	"<div class='editor_header'></div>";
					editorHtml += 	"<div class='editor_option'>";
					editorHtml += 		"<a href='javascript:;'>编辑</a>";
					editorHtml += 	"</div>";
					// editorHtml += 	"<div class='editor_border'></div>";
					editorHtml += "</div>";
				$(document.body).append(editorHtml);
				self.setStyle($("#"+result));
				$("#"+result).find(".editor_option a").bind("click",function(){
					self.showEdit();
				});
			}
			return $("#"+result);
		}
	
		this.setStyle = function(editorMenu){
			var _top = $(self.view).offset().top+1;
			var _left = $(self.view).offset().left-1;
			var _width = $(self.view).width()-2;
			$(editorMenu).css({"top": _top, "left": _left });
			$(editorMenu).width(_width);
		}
		
		self.editMenu = this.getEditMenu();
		
		this.showEdit = function(){
			if(!isNull(self.url)){
				var url = self.url.replace("{id}",self.dataid);
				show("iframe#"+url,"编辑碎片",750,700);
			}else if(!isNull(self.datatype)){
				var url = "/pages/admin/"+self.datatype.replace("_","/")+"/tab.jsp?id="+self.dataid;
				show("iframe#"+url,"编辑碎片",750,700);
			}else{
				alert("没有映射数据编辑地址。");
			}
		}
		
		this.bindAction = function(){
			$(self.view).bind("mouseover",function(e){
				if(null != self.closeTimeout) {
					window.clearTimeout(self.closeTimeout);
				}
				$(self.editMenu).show();
				self.canClose = false;
			});
			
			$(this.view).bind("mouseout",function(e){
				if(null != self.closeTimeout) {
					window.clearTimeout(self.closeTimeout);
				}
				self.canClose = true;
				self.closeTimeout = window.setTimeout(function(e){
					if(self.canClose){
						$(self.editMenu).hide();
					}
					self.closeTimeout = null;
				},500);
			});
		}
		self.bindAction();
	}
	
	
	var AdminDatas = function() {
		var self = this;
		self.templateId = 0;
		this.bindAction = function(){
			var editList = $("[dataid]");
			$(editList).each(function(i,obj){
				new AdminData(i,obj);
			});
		}
		
		this.loadScripts = function(){
			$(document.head).append("<link href='/scripts/admindata/admindata.css' type='text/css' rel='stylesheet' />");
		}
		
		this.initAdminData = function(options){
			$.ajax({
				url : "/admin/data/edit/hasPerm",
				type : "post",
				dataType : "json",
				success : function(jsonDatas){
					if(jsonDatas.status == "success"){
						self.loadScripts();
						$(document).ready(function(){
							self.bindAction();
						});
					}
				}
			});
		}
	}
	$.extend({adminDatas: new AdminDatas()});
})(jQuery);