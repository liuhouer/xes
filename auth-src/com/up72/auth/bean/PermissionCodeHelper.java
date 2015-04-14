package com.up72.auth.bean;

import com.up72.auth.model.Permission;
import com.up72.auth.model.PermissionGroup;
import com.up72.util.EncryptUtil;

/**
 * 权限码创建工具
 * 
 * @author wqtan
 * @date 2012-03-22
 */
public final class PermissionCodeHelper {
	/**
	 * 根据权限地址和名称做编码
	 * @author wqtan
	 */
	public static String createPermissionCode(Permission permission){
		return createPermissionCode(permission.getPermissionGroupCode(), permission.getUrl(), permission.getName());
	}
	
	public static String createPermissionCode(String permissionGroupCode,String url,String permissionName){
		String key = permissionGroupCode+":"+url+":"+permissionName;
		return EncryptUtil.md5(key);
	}
	
	/**
	 * 根据product编码和权限组名字做编码
	 * @author wqtan
	 */
	public static String createGroupCode(PermissionGroup permissionGroup){
		return createGroupCode(permissionGroup.getProductCode(), permissionGroup.getName());
	}
	
	public static String createGroupCode(String productCode,String permissionGroupName){
		String key = productCode+":"+permissionGroupName;
		return EncryptUtil.md5(key);
	}
	
	/**
	 * 根据product名字做编码
	 * @author wqtan
	 */
	public static String createCode(String productName){
		String key = productName;
		return EncryptUtil.md5(key);
	}
	
	
}
