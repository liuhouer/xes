/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	config.language = 'zh-cn'; // 配置语言
	//config.width = 750,
	config.uiColor = '#f8fcff'; // 背景颜色
	/**
	config.toolbar = [
		['Source','-','Save','Preview','-','Templates'],
		['Cut','Copy','Paste','PasteText','PasteFromWord'],
		['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
		['Form','Checkbox','Radio','TextField','Textarea','Select','Button','ImageButton','HiddenField'],
		'/',
		['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
		['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
		['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
		'/',
		['Styles','Format','Font','FontSize'],
		['TextColor','BGColor'],
		['Maximize','ShowBlocks','-','About']
	];
	**/
	config.enterMode = CKEDITOR.ENTER_BR,//启用回车键，回车时新建<p>标签
	config.extraPlugins = 'autogrow',
	config.autoGrow_maxHeight = 800,
	//config.filebrowserUploadUrl='/ckeditor/uploader?Type=File';   
 	config.filebrowserImageUploadUrl='/ckeditor/uploader?Type=Image';   
	config.filebrowserFlashUploadUrl='/ckeditor/uploader?Type=Flash';
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
};
