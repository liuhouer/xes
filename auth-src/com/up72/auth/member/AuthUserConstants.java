package com.up72.auth.member;

import com.up72.base.BaseQuery;

  
public class AuthUserConstants {
	/**用户积分*/
	public final static Integer MEMBER_CREDIT = 0;
	/**删除标志*/
	public final static Integer MEMBER_DEL_STATUS = 0;
	/**导航页面*/
	public final static String CURRENT_PAGE = "current_page";
	/**导航文字-回收站*/
	public final static String CURRENT_PAGE_TEXT_RECYCLE = "回收站";
	

	/**发送消息*/
	public final static String SEND_MESSAGE = "发送消息";
	/**发送邮件*/
	public final static String SEND_EMAIL = "发送邮件";
	/**发送短信*/
	public final static String SEND_NOTE = "发送短信";
	

	/** 消息类型 - 好友消息 */
	public final static Integer MESSAGE_TYPE_MESSAGE = 0;
	/** 消息类型 - 系统消息 */
	public final static Integer MESSAGE_TYPE_SYS_MESSAGE = 1;
	/** 消息类型 - 邮件 */
	public final static Integer MESSAGE_TYPE_EMAIL = 2;
	/** 消息类型 - 短信 */
	public final static Integer MESSAGE_TYPE_NOTE = 3;
	
	
	/** 消息已读 */
	public final static Integer MESSAGE_RAED = 0;
	/** 消息未读 */
	public final static Integer MESSAGE_UNRAED = 1;
	/** 消息不显示 */
	public final static Integer MESSAGE_NONE = 2;
	/** 消息显示已读和未读 */
	public final static Integer MESSAGE_ALL = 3;
	
	
	/** 虚拟发件人ID */
	public final static Long FORM_MEMBER_ID = 1L;
	/** 虚拟发件人名称 */
	public final static String FORM_MEMBER_NAME = "admin";
	
	
	/** Long 型 初始默认值 0 */
	public final static Long DEFAULT_LONG_VALUE = 0L;
	
	
	/** 消息发送者未删除 */
	public final static Integer MESSAGE_STATES_UNDELETE = 0;
	/** 消息发送者删除 */
	public final static Integer MESSAGE_STATES_DELETE = 1;
	
    /**
	 * 默认头像地址
	 * @author wqtan
	 */
	public static final String MEMBER_DEFAULT_PHOTO = "/images/default.jpg";
	/**
	 * 默认头像上传路径
	 * @author wqtan
	 */
	public static final String MEMBER_PHOTO_UPLOAD_PATH = "uploads";
	
	
	/**
	 * 记录当前页数
	 * @author bxmen
	 */
	public static Integer PAGE_NUMBER = 0;
	/**
	 * 记录当前显示条数
	 * @author bxmen
	 */
	public static Integer PAGE_SIZE = BaseQuery.DEFAULT_PAGE_SIZE;
	
	/** 默认风格 */
	public static String DEFAULT_STYLE = "system";
	/** 默认风皮肤 */
	public static String DEFAULT_SKIN = "mac";
}
