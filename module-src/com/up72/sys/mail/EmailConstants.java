package com.up72.sys.mail;

public class EmailConstants {
	/** 默认用户ID */
	public static Long DEFAULT_USER_ID = 0L;

	/** 成功 */
	public static String SUCCESS = "1";

	/** 失败 */
	public static String FAILURE = "0";

	/** 返回状态 存在 */
	public static String EXIST_STATUS = "1";

	/** 返回状态 不存在 */
	public static String NOT_EXIST_STATUS = "0";

	/** 邮件订阅 */
	public static Integer SUBSCRIBE_STATUE_TRUE = 1;

	/** 取消邮件订阅 */
	public static Integer SUBSCRIBE_STATUE_CANCLE = 0;

	/** 返回启用状态 */
	public static String ENABLED = "2";

	/** 返回禁用状态 */
	public static String DISABLED = "3";

	/** properties文件 */
	public static String PROPERTIES_FILE = "mail.properties";

	/** 邮件服务器 */
	public static String MAIL_SMTP_HOST = "mail.smtp.host";
	/** 是否验证 */
	public static String MAIL_SMTP_AUTH = "mail.smtp.auth";
	/** 用户名 */
	public static String MAIL_SMTP_USER = "mail.smtp.user";
	/** 邮件发送显示的名称 */
	public static String MAIL_SMTP_USER_TITLE = "mail.smtp.userTitle";
	/** 密码 */
	public static String MAIL_SMTP_PASSWORD = "mail.smtp.password";
	/** 编码 */
	public static String MAIL_SMTP_ENCODE = "mail.smtp.encode";
	/** 信息编码 */
	public static String MAIL_SMTP_MESSAGE_ENCODE = "mail.smtp.messageEncode";
	/** 服务状态 1为启用，0为禁用 */
	public static String MAIL_SMTP_STATUS = "mail.smtp.status";

	/** 服务启用状态标识 */
	public static String SERVICE_STATUS_ENABLED = "1";

	/** 服务禁用状态标识 */
	public static String SERVICE_STATUS_DISABLED = "0";

	/** 邮件未发送 */
	public static Integer SEND_EMAIL_UNFINISH = 0;

	/** 邮件发送完毕 */
	public static Integer SEND_EMAIL_FINISH = 1;

	/** 邮件发送中.. */
	public static Integer SENDING_EMAIL = 2;
}
