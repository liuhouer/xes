package com.up72.auth.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={java.lang.annotation.ElementType.METHOD,java.lang.annotation.ElementType.TYPE})
/**
 * 系统权限标注，需要用到的用改标注标注
 */
public @interface PermissionGroupAnnotation {

	/**
	 * 权限类型 用户
	 */
	public static final String STATUS_USER = "2";
	/**
	 * 权限类型 系统
	 */
	public static final String STATUS_SYSTEM = "1";
	
	/**
	 * 权限组名称
	 */
	String name() ;
	
	/**
	 * 权限组描述
	 */
	String description() default "";
	
	String status() default STATUS_USER;
	
	String type() default "";
}
