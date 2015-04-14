package com.up72.auth;

import com.up72.base.BaseQuery;

public class AuthConstants {

	
	/** 角色 ROLE_KEY */
	public static final String SESSION_ROLE_KEY =  "SESSION_ROLE_KEY";
	
	/** 产品 PRODUCT_KEY */
	public static final String SESSION_PRODUCT_KEY =  "SESSION_PRODUCT_KEY";
	
	/**
	 * 登陆Cookie有效时长
	 * @author wqtan
	 */
	public static final Integer LOGIN_COOKIE_TIME_LENGTH = 30 * 24 * 3600;
	
	public static final Integer LOGIN_COOKIE_TIME_DEFAULT = -1;
	
	
	
	/** 后台用户COOKIE_KEY */
	public static final String COOKIE_ADMIN_MEMBER_KEY =  "COOKIE_ADMIN_MEMBER_KEY";
	/** 后台用户 SESSION_KEY */
	public static final String SESSION_ADMIN_MEMBER_KEY =  "SESSION_ADMIN_MEMBER_KEY";
	
	/**
	 * 性别，保密
	 */
	public static final Integer MEMBER_GENDER_SECRET = 0; 
	/**
	 * 性别，男
	 */
	public static final Integer MEMBER_GENDER_MALE = 1;
	/**
	 * 性别，女
	 */
	public static final Integer MEMBER_GENDER_FEMALE = 2; 
	
	
	/** 用户登陆默认值 */
	public static final Integer  MEMBER_TYPE = 0;
	
	/** 一般用户 */
	public static final Integer  MEMBER_TYPE_USUALLY = 1;
	/** 后台用户 */
	public static final Integer  MEMBER_TYPE_ADMIN = 2;
	/** 系统管理员用户 */
	public static final Integer  MEMBER_TYPE_SYSTEM = 5;

	/*********************************** bxmen ***************************************/
	/** system.properties文件 */
	public static String PROPERTIES_FILE = "system.properties";
	
	/** 私有KEY 值：U-WinSendSubscribeEmail */
	public static String PRIVATE_KEY = "U-WinSendSubscribeEmail";
	
	/** 定时器表达式 uwin.timing.expression */
	public static String TIMING_EXPRESSION = "uwin.timing.expression";

	/** 定时器初始化表达式 uwin.timing.init.expression */
	public static String INIT_TIMING_EXPRESSION = "uwin.timing.init.expression";
	
	/** 密码找回标题 */
	public static String BACK_PASSWORD_TITLE = "mail.password.back.title";
	/** 密码找回模板 */
	public static String BACK_PASSWORD_TEMPLATE = "mail.password.back.template";
	/** 密码找回模板 */
	public static String BACK_PASSWORD_URL = "mail.password.back.url";
	
	
	
	/** 注册标题 */
	public static String REGISTER_TITLE = "register.title";
	/** 注册确认链接 */
	public static String REGISTER_CONFIRM_URL = "register.confirmUrl";
	/** 注册邮件模板 */
	public static String REGISTER_TEMPLATE = "register.template";

	
	/** 订阅邮件标题 */
	public static String SUBSCRIBE_MAIL_TITLE = "mail.subscribe.product.title";
	
	/** 订阅邮件模板 */
	public static String SUBSCRIBE_MAIL_TEMPLATE = "mail.subscribe.product.template";
	
	/** 订阅邮件模板 */
	public static String SUBSCRIBE_SMS_TEMPLATE = "sms.subscribe.product.template";
	
	/** 订阅确认邮件标题 */
	public static String MAIL_SUBSCRIBE_TITLE = "mail.subscribe.title";
	
	/** 退订确认邮件标题 */
	public static String MAIL_CALCEL_TITLE = "mail.cancel.title";
	
	/** 订阅、退订邮件模板 */
	public static String SUBSCRIBE_CALCEL_TEMPLATE = "mail.subscribe.cancel.template";
	
	/** 默认编码 UTF-8 */
	public static String DEFAULT_ENCODE = "utf-8";
	
	/** 启用信息过滤 */
	public static Integer WORDS_ENABLED_TRUE =  1;
	/** 是否禁止IP访问 **/
	public static Integer BANNED_IP_VISIT_TRUE =  2;
	/** 是否禁止IP注册 **/
	public static Integer BANNED_IP_REGISTER_TRUE =  1;
	/*********************************** bxmen ***************************************/

	public static class project{
		public static final String NAME = "公共服务";
	}

	public static final String OP_RESULT_SUCCESS = "0";
	
	/** 审核通过 */
	public static final Integer STATUS_TRUE = 1;
	/** 审核通过 */
	public static final Integer STATUS_FALSE = 2;
	/** 未审核 */
	public static final Integer UN_STATUS = 0;
	
	public static final class workGroupMember {
		/** 是部门管理员 */
		public static final Integer IS_MANAGER_TRUE = 1;
		/** 不是部门管理员 */
		public static final Integer IS_MANAGER_FALSE = 0;
		/** 部门用户审核通过 */
		public static final Integer STATUS_PASS = 1;
		/** 部门用户审核不通过 */
		public static final Integer STATUS_UN_PASS = 0;
	}
	
	/**
	 * 启用or不启用
	 * @author wqtan
	 */
	public static final int PUBILC_ENABLED_ENABLE = 1;
	/** 不启用 */
	public static final int PUBILC_ENABLED_UNENABLE = 0;

    /** 设置默认机构 */
    public static final Long ORGANIZATION_ID = 3L;
    
    /** Long 型 初始值 */
    public static final Long DEFAULT_LONG_VALUE = 0L;

    /**
     * 记录当前页数
     * @author bxmen
     */
    public static Integer PAGE_NUMBER = 0;
    /**
     * 记录当前页面显示条数
     * @author bxmen
     */
    public static Integer PAGE_SIZE = BaseQuery.DEFAULT_PAGE_SIZE;
    
    public static class common{
    	public static final String AUTH_PERM_ID = "AUTH_PERM_ID"; 
    	/** 全局产品 **/
    	public static final Integer AUTH_ALL = 0;
    	/** 系统 */
    	public static final Integer AUTH_SYSTEM = 1;
    	/** 用户 */
    	public static final Integer AUTH_USER = 2;
    }
    
    public static class authUser{
    	/** 后台用户系统管理员 */
    	public static final Integer AUTH_USER_SYSTEM = 5;
    	/** 后台用户管理员 */
    	public static final Integer AUTH_USER_ADMIN = 2;
    }

    /**
     * 权限类型 1为菜单权限、2操作权限、3tag权限
     * @author wqtan
     */
    public static final int PERMISSION_TYPE_MENU = permission.TYPE_MENU;//菜单
    public static final int PERMISSION_TYPE_OPTION = permission.TYPE_OPTION;//操作
    public static final int PERMISSION_TYPE_TAB = permission.TYPE_TAG;//选项卡
    
    public static class permission{
    	/**
    	 * 权限类型 菜单
    	 */
    	public static final Integer TYPE_MENU = 1;
    	/**
    	 * 权限类型 操作
    	 */
    	public static final Integer TYPE_OPTION = 2;
    	/**
    	 * 权限类型 选项卡
    	 */
    	public static final Integer TYPE_TAG = 3;
    	
    } 
    
}
