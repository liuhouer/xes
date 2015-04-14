/**
 * 
 */
package com.up72.common.xml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/** 
 * @author 作者:DexinWang
 * @version 创建时间：2012-9-3 下午05:44:16
 * @manual 说明：导出信息的一个实体
 */
@SuppressWarnings("unchecked")
public class DatasXmlVo {
	/**为装有所有表名的集合**/
	private List<String> tablenamelist;
	/***装有所有记录的map  key为表名value为表内的所有记录*/
	private LinkedHashMap<String, List> recodeMap;
	/***装有pk的map  key为表名value为表内的pk*/
	private LinkedHashMap<String, String> pkMap;
	
	private List<String> syncFileList;
	
	public List<String> getSyncFileList() {
		return syncFileList;
	}
	
	public void addSyncFileList(List<String> list) {
		if(null!=list && !list.isEmpty()){
			syncFileList.addAll(list);
		}
	}

	public DatasXmlVo() {
		tablenamelist = new ArrayList<String>();
		recodeMap = new LinkedHashMap<String, List>();
		pkMap = new LinkedHashMap<String, String>();
		syncFileList = new ArrayList<String>();
	}
	
	/**
	 * @return the tablenamelist
	 */
	public List getTablenamelist() {
		return tablenamelist;
	}
	/**
	 * @param tablenamelist the tablenamelist to set
	 */
	public void addTablenamelist(List tablenamelist) {
		if(null!=tablenamelist && !tablenamelist.isEmpty()){
			this.tablenamelist.addAll(tablenamelist);
		}
	}
	/**
	 * @return the recodeMap
	 */
	public LinkedHashMap<String, List> getRecodeMap() {
		return recodeMap;
	}
	/**
	 * @param recodeMap the recodeMap to set
	 */
	public void addRecodeMap(LinkedHashMap<String, List> recodeMap) {
		if(null!=recodeMap && !recodeMap.isEmpty()){
			this.recodeMap.putAll(recodeMap);
		}
	}
	/**
	 * @return the pkMap
	 */
	public LinkedHashMap<String, String> getPkMap() {
		return pkMap;
	}
	/**
	 * @param pkMap the pkMap to set
	 */
	public void addPkMap(LinkedHashMap<String, String> pkMap) {
		if(null!=pkMap && !pkMap.isEmpty()){
			this.pkMap.putAll(pkMap);
		}
	}
	
	public void clearAll(){
		this.syncFileList.clear();
		this.pkMap.clear();
		this.recodeMap.clear();
		this.tablenamelist.clear();
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("tablenamelist:"+tablenamelist);
		result.append("recodeMap:"+recodeMap);
		result.append("pkMap:"+pkMap);
		return result.toString();
	}
	
}
