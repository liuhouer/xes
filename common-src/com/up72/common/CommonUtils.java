package com.up72.common;

import com.up72.auth.UserUtils;
import com.up72.common.email.MailUtil;
import com.up72.common.encrypt.EncryptUtil;
import com.up72.common.json.JsonUtil;
import com.up72.common.version.VersionUtil;
import com.up72.common.xml.ReadXml;
import com.up72.common.xml.WriteXml;
import com.up72.util.Base64Util;
import com.up72.util.ClassScanUtil;
import com.up72.util.CookieUtil;
import com.up72.util.DESPlusUtil;
import com.up72.util.DateUtils;
import com.up72.util.FileUtil;
import com.up72.util.GB2AlphaUtil;
import com.up72.util.HTMLParserUtil;
import com.up72.util.IOUtil;
import com.up72.util.ObjectUtils;
import com.up72.util.ParamUtils;
import com.up72.util.PinyinUtil;
import com.up72.util.ReflectUtil;
import com.up72.util.SessionUtil;
import com.up72.util.StringUtil;
import com.up72.util.TokenUtil;

/**
 * 公用工具汇总类，汇总了系统中可外放的所有Utils工具类。 其他模块工具类，必须要实现该类
 * 
 * @author wqtan
 * @create 2012-04-20
 */
public interface CommonUtils {
	public static final UserUtils userUtils = new UserUtils();
	/**
	 * 日期操作
	 */
	public static final DateUtils dateUtils = new DateUtils();
	/**
	 * 字符串操作
	 */
	public static final StringUtil stringUtil = new StringUtil();
	/**
	 * 对象操作
	 */
	public static final ObjectUtils objectUtils = new ObjectUtils();
	/**
	 * request获取参数操作
	 */
	public static final ParamUtils paramUtils = new ParamUtils();
	/**
	 * session操作
	 */
	public static final SessionUtil sessionUtil = new SessionUtil();
	/**
	 * cookie操作
	 */
	public static final CookieUtil cookieUtil = new CookieUtil();
	/**
	 * json操作
	 */
	public static final JsonUtil jsonUtil = new JsonUtil();
	/**
	 * IO操作
	 */
	public static final IOUtil ioUtil = new IOUtil();
	/**
	 * 文件操作
	 */
	public static final FileUtil fileUtil = new FileUtil();
	/**
	 * md5加密操作
	 */
	public static final EncryptUtil encryptUtil = new EncryptUtil();
	/**
	 * Base64加密
	 */
	public static final Base64Util base64Util = new Base64Util();
	/**
	 * 版本操作
	 */
	public static final VersionUtil versionUtil = new VersionUtil();
	/**
	 * 拼音操作
	 */
	public static final PinyinUtil pinyinUtil = new PinyinUtil();
	/**
	 * 获得汉语字符串拼音首字母
	 */
	public static final GB2AlphaUtil gb2AlphaUtil = new GB2AlphaUtil();
	/**
	 * html处理
	 */
	public static final HTMLParserUtil htmlParserUtil = new HTMLParserUtil();
	/**
	 * 加密操作
	 */
	public static final DESPlusUtil desPlus = new DESPlusUtil();
	/**
	 * 邮件操作
	 */
	public static final MailUtil mailUtil = new MailUtil();
	/**
	 * 类扫描
	 */
	public static final ClassScanUtil classScanUtil = new ClassScanUtil();
	/**
	 * 反射
	 */
	public static final ReflectUtil reflectUtil = new ReflectUtil();
	/**
	 * Token工具（防止重复提交）
	 */
	public static final TokenUtil tokenUtil = TokenUtil.getInstance();

	/**
	 * xml数据导入
	 */
	public static final ReadXml xmlImportUtils = new ReadXml();
	/**
	 * xml数据导出
	 */
	public static final WriteXml xmlExportUtils = new WriteXml();
}
