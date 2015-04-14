package com.up72.sys.model;

import java.util.Date;

public class FileInfo {
	
	//alias
	public static final String TABLE_ALIAS = "文件信息";
	public static final String ALIAS_NAME = "文件名";
	public static final String ALIAS_SIZE = "文件大小";
	public static final String ALIAS_EDITY_TIME = "修改日期";
	public static final String ALIAS_FILE_PATH = "文件路径";
	public static final String ALIAS_TYPE = "文件类型";
	public static final String ALIAS_fileCount = "文件夹内的文件个数";
	
	private String name;
	private String size;
	private Date editTime;
	private String filePath;
	private int type;
	private int fileCount = 0;
	private int childFileCount;
	
	public FileInfo(){
	}
	
	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getPath(){
		return this.filePath;
	}

	public int getChildFileCount() {
		return childFileCount;
	}

	public void setChildFileCount(int childFileCount) {
		this.childFileCount = childFileCount;
	}

}
