package com.up72.auth.bean;

import java.util.HashMap;



public class PermissionTreeManager {
	
	private HashMap<String ,PermissionTree> permissionTreeMap = new HashMap<String, PermissionTree>();
	
	
	public void addPermissionTree(Object[] object){
		PermissionTree permissionTree = null;
		String key = String.valueOf(object[3]);
		
		if (permissionTreeMap.containsKey(key)){
			permissionTree = permissionTreeMap.get(key);
			permissionTree.addPermission(object);
		}else{
			permissionTree = new PermissionTree();
			permissionTree.addPermission(object);
			permissionTreeMap.put(key, permissionTree);
		}
	}

	public HashMap<String, PermissionTree> getPermissionTreeMap() {
		return permissionTreeMap;
	}

	public void setPermissionTreeMap(
			HashMap<String, PermissionTree> permissionTreeMap) {
		this.permissionTreeMap = permissionTreeMap;
	}


	
	
	
	
	
	
	
	
	
	

}
