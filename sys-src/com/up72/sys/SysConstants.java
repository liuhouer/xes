package com.up72.sys;

/**
 * 工具模块常量定义类
 * 
 * @author wqtan
 */
public class SysConstants {
	
	
	
	
	/**
	 * 文件类型，样式文件
	 * 
	 * @author wqtan
	 */
	public static final String FILE_TYPE_CSS = "css";
	/**
	 * 样式文件对应的扩展名
	 * 
	 * @author wqtan
	 */
	public static final String FILE_EXT_CSS = ".css";

	/**
	 * 文件类型，图片文件
	 * 
	 * @author wqtan
	 */
	public static final String FILE_TYPE_IMG = "img";
	/**
	 * 图片文件对应的扩展名
	 * 
	 * @author wqtan
	 */
	public static final String FILE_EXT_IMG = ".jpg.gif.png";
	/**
	 * 文件类型，静态脚本文件
	 * 
	 * @author wqtan
	 */
	public static final String FILE_TYPE_SCRIPT = "script";
	/**
	 * 脚本文件对应的扩展名
	 * @author wqtan
	 */
	public static final String FILE_EXT_SCRIPT = ".js.as";
	/**
	 * 文件类型，动态脚本文件
	 * 
	 * @author wqtan
	 */
	public static final String FILE_TYPE_ACTION = "action";
	/**
	 * 脚本文件对应的扩展名
	 * @author wqtan
	 */
	public static final String FILE_EXT_ACTION = ".jsp.ftl.html.xml";
	/**
	 * 文件类型:文件
	 */
	public static final Integer FILE_TYPE_FILE = 1;
	/**
	 * 文件类型:路径
	 */
	public static final Integer FILE_TYPE_PATH = 2;
	
	/**
	 * 文件排序类型：文件名
	 */
	public static final Integer FILE_SORT_TYPE_NAME = 1;
	/**
	 * 文件排序类型：大小
	 */
	public static final Integer FILE_SORT_TYPE_SIZE = 2;
	/**
	 * 文件排序类型：修改时间
	 */
	public static final Integer FILE_SORT_TYPE_MODIFYTIME = 3;
	
	/**
	 * 文件排序规则：升序
	 */
	public static final Integer FILE_SORT_RULE_ASC = 1;
	/**
	 * 文件排序规则：降序
	 */
	public static final Integer FILE_SORT_RULE_DESC = 2;
	
	
	/**
	 * 图片处理，按比例缩放
	 */
	public static final Integer IMAGE_ZOOM_SCALE = 1;
	/**
	 * 图片处理，按最大值缩放
	 */
	public static final Integer IMAGE_ZOOM_MAXSIDE = 2;
	/**
	 * 图片处理，加图片水印
	 */
	public static final Integer IMAGE_WATER_IMAGE = 1;
	/**
	 * 图片处理，加文字水印
	 */
	public static final Integer IMAGE_WATER_WORD = 2;
	
	/**
	 * 允许上传文件类型
	 */
	public static final String UPLOAD_FILE_TYPE = "*.zip;*.html;*.htm;*.png;*.jpg;*.jpeg;*.txt;*.css;*.js;*.ico;*.gif;*.txt;*.xml";
	
	/**
	 * 文件编码 GBK
	 */
	public static final String FILE_CHARSET_GBK = "GBK";
	
	/**
	 * 文件编码 UTF-8
	 */
	public static final String FILE_CHARSET_UTF8 = "UTF-8";
}