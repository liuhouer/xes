package com.up72.auth.bean;

import java.util.ArrayList;
import java.util.List;

import com.up72.auth.model.Permission;
import com.up72.framework.util.ObjectUtils;

public class PermissionTree {
	private String groupName;
	private List<Permission> permissionList  = new ArrayList<Permission>();
	
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<Permission> getPermissionList() {
		return permissionList;
	}
	
	public void addPermission(Object[] object) {
		//object数组： 权限ID、名称、URL、权限组ID
		Permission permission = new Permission();
		permission.setId(Long.valueOf(String.valueOf(object[0])));
		permission.setName(String.valueOf(object[1]));
		permission.setUrl(String.valueOf(object[2]));
		permission.setPermissionGroupCode(String.valueOf(object[3]));
		
		if(ObjectUtils.isEmpty(this.getGroupName())){
			try {
				this.setGroupName(permission.getPermissionGroup().getName());
			} catch (Exception e) {
				e.getMessage();
			}
		}
		
		this.permissionList.add(permission);
	}
	
	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}
	
	
	

}
