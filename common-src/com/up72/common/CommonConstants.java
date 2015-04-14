package com.up72.common;

public class CommonConstants {
	/** *** Google验证码组件session key ***** */
	public static String VALID_CODE = "KAPTCHA_SESSION_KEY";

	/** ***系统404******* */
	public static String PAGE_404 = "redirect:/404.html";

	/** ***系统建设中******* */
	public static String PAGE_BUILDING = "redirect:/building.html";

	/** 根绝对路径 * */
	public static String ROOTPATH = "";
	/**
	 * 网站访问域名
	 */
	public static String HOST = "";

	public static String FORM_SUBMIT_TOKEN = "FROM_SUBMIT_TOKEN";

	/** 删除 */
	public static final Integer PUBLIC_DELETE = 1;
	/** 不删除 */
	public static final Integer PUBLIC_UNDELETE = 0;

	/** 不启用 */
	public static final Integer PUBILC_UNENABLE = 0;
	/** 启用 */
	public static final Integer PUBILC_ENABLED = 1;
	/** 未激活 */
	public static final Integer PUBILC_UNACTIVATE = -1;

	/** 显示 */
	public static final Integer PUBILC_VISIBLED = 1;
	/** 不显示 */
	public static final Integer PUBILC_UNVISIBLED = 0;

	/** 文件分割符 */
	public static final String FILE_SEP = System.getProperty("file.separator");

	/** 编码 * */
	public static final String SYSTEM_ENCODING = "UTF-8";
	@Deprecated
	public static final String DEFAULT_ENCODE = "UTF-8";

	public static final class time {
		/**
		 * 1秒兑换毫秒数
		 */
		public static final long SECOND_2_MINISECOND = 1000;
		/**
		 * 1分钟兑换秒数
		 */
		public static final long MINUTE_2_SECOND = 60;
		/**
		 * 一小时兑换分钟数
		 */
		public static final long HOUR_2_MINUTE = 60;
		/**
		 * 一天兑换小时数
		 */
		public static final long DAY_2_HOUR = 24;
		/**
		 * 一周兑换天数
		 */
		public static final long WEEK_2_DAY = 7;
		/**
		 * 一月兑换天数
		 */
		public static final long MONTH_2_DAY = 30;
		/**
		 * 一年兑换天数
		 */
		public static final long YEAR_2_DAY = 365;
		/**
		 * 一年兑换月数
		 */
		public static final long YEAR_2_MONTH = 12;
	}

	public static final class image {
		/**
		 * 最大上传大小 超过该值做缩放
		 */
		// public static long UPLOAD_MAXSIZE =
		// null==SystemConfig.instants().getIntValue("img.upload.maxsize")?512000:SystemConfig.instants().getIntValue("img.upload.maxsize");
		public static long UPLOAD_MAXSIZE = 512000;
		/**
		 * 最大宽度 超过该值做缩放
		 */
		// public static int MAX_WIDTH =
		// null==SystemConfig.instants().getIntValue("img.upload.maxwidth")?1500:SystemConfig.instants().getIntValue("img.upload.maxwidth");
		public static int MAX_WIDTH = 1500;
	}

	public static final class systemLog {
		public static class type {
			/** 用户登录 */
			public static String LOGIN = "用户登录";
			/** 用户退出 */
			public static String LOGOUT = "用户退出";
			/** 创建 */
			public static String CREATE = "创建";
			/** 编辑 */
			public static String EDIT = "编辑";
			/** 删除 */
			public static String DELELTE = "删除";
			/** 导入 */
			public static String IMPORT = "导入";
			/** 导出 */
			public static String EXPORT = "导出";
		}

		public static class result {
			/** 成功 */
			public static String SUCCESS = "成功";
			/** 失败 */
			public static String FAIL = "失败";
		}

		public static class function {
			/** 登录系统 */
			public static String LOGIN_SYSTEM = "登录系统";
			/** 退出系统 */
			public static String LOGOUT_SYSTEM = "退出系统";
		}
		/** 系统设置 */

	}

	public static final class sysConfig {
		/** system.properties */
		public static String SYSTEM_CONFIG = "system.properties";
		/** 更换风格 system.properties 文件里的key */
		public static String CHANGE_STYLE_KEY = "styles.skin.value";
		/** 更换系统名称 system.properties 文件里的key */
		public static String PROJECT_NAME_KEY = "project.common.name";
		public static String PROJECT_CODE_KEY = "project.common.code";
		public static String PROJECT_VERSION_KEY = "project.common.version";
		public static String PROJECT_STARTIME_KEY = "project.common.starTime";
		public static String PROJECT_LASTUPDATETIME_KEY = "project.common.lastUpdateTime";
		public static String PROJECT_ISDEBUG_KEY = "project.common.isDebug";
	}

	public static final class sync {
		public static String ENTITY_SYNC_FILE_PATH = "SYNC_FILE_PATH";
	}

	// public static final EmailConfig emailConfig = new EmailConfig();
	// public static final ImageConfig imageConfig = new ImageConfig();
	// public static final UploadConfig uploadConfig = new UploadConfig();
}
