package com.up72.sys;

public class SysLogConstants {
	public static final class systemLog{
		public static class type{
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
		
		public static class result{
			/** 成功 */
			public static String SUCCESS = "成功";
			/** 失败 */
			public static String FAIL = "失败";
		}

		public static class function{
			/** 登录系统 */
			public static String LOGIN_SYSTEM = "登录系统";
			/** 退出系统 */
			public static String LOGOUT_SYSTEM = "退出系统";
		}
	}
	
}