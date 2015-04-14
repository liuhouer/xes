/**
 * 可视化编辑组件
 * pageTemplateId : 页面模板Id
 *
 * @author wqtan
 */
(function($) {
	var AdminView = function(index,pageTemplateId,view) {
		var self = this;
		self.view = view;
		self.chipId = isNull($(view).attr("iid")) ? "" : $(view).attr("iid");
		self.taglib = $(view).attr("taglib");
		self.replace = $(view).attr("replace");
		self.index = index;
		self.closeTimeout = null;
		self.canClose = false;
		
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
			var datas = "pageTemplateId="+pageTemplateId;
			if(self.chipId > 0){
				datas += "&chipId="+self.chipId;
				datas += "&index="+self.index;
			}else{
				datas += "&index="+self.index;
			}
			if(!isNull(self.taglib)){
				datas += "&taglib="+self.taglib;
			}
			if(!isNull(self.replace)){
				datas += "&replace="+self.replace;
			}
			show("iframe#/admin/cms/pageChip/showChip?"+datas,"编辑碎片",750,700);
			// show("/admin/cms/pageChip/showChip?"+datas,"编辑碎片",750,600);
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
	
	
	var AdminViews = function() {
		var self = this;
		self.templateId = 0;
		this.bindAction = function(){
			var editList = $("[edit='true']");
			$(editList).each(function(i,obj){
				new AdminView(i,self.templateId,obj);
			});
		}
		
		this.loadScripts = function(){
			$(document.head).append("<link href='/scripts/adminview/adminview.css' type='text/css' rel='stylesheet' />");
			//$(document.head).append("<link type='text/css' rel='stylesheet' href='/styles/global.css' />");
			//$(document.head).append("<link type='text/css' rel='stylesheet' href='/styles/structs_inner.css' />");
			//$(document.head).append("<link type='text/css' rel='stylesheet' href='/styles/skins/deepBlue/skin_inner.css' />");
		}
		
		this.initAdminView = function(pageTemplateId,options){
			self.templateId = pageTemplateId;
			$.ajax({
				url : "/admin/cms/pageTemplate/canEdit",
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
	$.extend({adminViews: new AdminViews()});
})(jQuery);