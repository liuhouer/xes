package com.up72.common.version;

import static com.up72.common.CommonUtils.fileUtil;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * 
 * @author yjliang
 * 
 */
public final class VersionUtil {
	/**
	 * 读取版本
	 * @param path
	 * @return
	 */
	public  LinkedHashMap<String,Version> readVersion(String path) {
		LinkedHashMap<String,Version> result = null;
		
		if(fileUtil.exists(path)){
			// 文件存在在硬盘中读取原来的对象
			result=(LinkedHashMap<String,Version>)SerializeUtil.readObject(path);
		}else{
			//不存在new新的HashMap对象
			result= new LinkedHashMap<String,Version> ();
		}
		
		
		return result;
	}
	
	/**
	 * 添加版本
	 * @param path
	 * @param obj
	 * @return 添加成功与否
	 */
	public  boolean addVersion(IVersion obj) {
		//初始化version对象
		Version version=new Version();
		version.setCreateDate(new Date());
		version.setVersionObj(obj);
		LinkedHashMap<String,Version> result=null;
		String path = version.getSavePath();
		//判断文件是否存在
		if(fileUtil.exists(path)){
			// 文件存在在硬盘中读取原来的对象
			 result=readVersion(path);
		}else{
			//不存在new新的HashMap对象
			result= new LinkedHashMap<String,Version> ();
		}
		// 添加新版本
		String key = Long.toString(new Date().getTime());
		result.put(key, version);
		// 序列化到硬盘
		return SerializeUtil.writeObject(path, result);
	}
	
	/**
	 * 写入版本map
	 * 
	 * @param path
	 * @param versionMap
	 * @return 写入map成功与否
	 */
	public  boolean writeVersion(String path, LinkedHashMap<String, Version> versionMap){
		boolean result=false;
		if(null!=versionMap)
		{
			result=SerializeUtil.writeObject(path, versionMap);
		}
		
		return result;
	}

	
	/**
	 * 删除指定的版本
	 * @param path
	 * @param versionKey
	 * @return 删除成功与否
	 */
	public  boolean deleteVersion(String path,String versionKey){
		boolean result=false;
		boolean isconta=false;
		LinkedHashMap<String,Version> map=readVersion(path);
		/*Iterator it= map.keySet().iterator();
		*String delTar="";
		
		while(it.hasNext())
		{
			String key=it.next()+"";
			Version version=(Version)map.get(key);
			if(version.getVersionNum().equals(versionNum))
			{
				delTar=key;
				isconta=true;
				break;
			}
		}
		
		if(isconta)
		{
			map.remove(delTar);
			result= writeVersion(path,map);
		}*/
		map.remove(versionKey);
		result= writeVersion(path,map);
		
		return result;
	}
	
	/**
	 * 删除全部版本
	 * @param path
	 * @return 删除成功与否
	 */
	public  boolean deleteVersion(String path){
		return fileUtil.deleteFile(path);
	}
}
