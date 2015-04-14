package com.up72.auth.config;

public class ProductSetting {
	private String code;
	private String name;
	// package 关键词 a -> v
	private String pvckage;
	private Long sortId;
	private String description;
	private String imgPath;
	// 类型 1系统产品 2用户产品
	private Integer status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPvckage() {
		return pvckage;
	}

	public void setPvckage(String pvckage) {
		this.pvckage = pvckage;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String toString() {
		StringBuffer result = new StringBuffer("{");
		result.append("code=" + code + ",");
		result.append("name=" + name + ",");
		result.append("package=" + pvckage+",");
		result.append("sortId=" + sortId+",");
		result.append("description=" + description+",");
		result.append("imgPath=" + imgPath);
		return result.toString();
	}
}
