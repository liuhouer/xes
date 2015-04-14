package com.up72.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
/**
 * 系统权限标注，需要用到的用改标注标注
 */
public @interface PermissionAnnotation {

	/**
	 * 权限类型 用户
	 */
	public static final String STATUS_USER = "2";
	/**
	 * 权限类型 系统
	 */
	public static final String STATUS_SYSTEM = "1";
	
	/**
	 * 权限类型 菜单
	 */
	public static final String TYPE_MENU = "1";
	/**
	 * 权限类型 操作
	 */
	public static final String TYPE_OPTION = "2";
	/**
	 * 权限类型 标签
	 */
	public static final String TYPE_TAG = "3";

	/**
	 * 权限名称
	 */
	String name();
	
	/**
	 * 权限类型
	 */
	String type() default TYPE_OPTION;
	
	/**
	 * 访问地址
	 */
	String url() default "";
	
	/**
	 * 描述
	 */
	String description() default "";
	/**
	 * 类型
	 */
	String status() default STATUS_USER;
}
