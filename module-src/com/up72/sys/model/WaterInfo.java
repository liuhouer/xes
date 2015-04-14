package com.up72.sys.model;

import java.util.Map;

public class WaterInfo {
	Integer pattern;
	// 水印的位置 WaterBean对应的九个变量
	Integer location;
	// 水印图片的偏移
	Integer top;
	Integer left;
	// 透明度 1 - 100
	Integer opacity;
	// 是否覆盖原有文件
	Integer isCover;
	// 水印的值 图片为图片的相对路径，文字为文字
	String value;
	Map<String, Object> fontParam;

	public Integer getPattern() {
		return pattern;
	}

	public void setPattern(Integer pattern) {
		this.pattern = pattern;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}

	public Integer getOpacity() {
		return opacity;
	}

	public void setOpacity(Integer opacity) {
		this.opacity = opacity;
	}

	public Integer getIsCover() {
		return isCover;
	}

	public void setIsCover(Integer isCover) {
		this.isCover = isCover;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Map<String, Object> getFontParam() {
		return fontParam;
	}

	public void setFontParam(Map<String, Object> fontParam) {
		this.fontParam = fontParam;
	}
}
