package com.up72.common.excel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

public class ColumnConfig {
	private String attribute;
	private String exportName;
	private Short width;
	private HSSFCellStyle style;
	private Map<String,String> dictionary;
	private Boolean parseHTML;
	/**
	 * 时间格式化对象
	 */
	private DateFormat dateFormat;
	
	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Map<String, String> getDictionary() {
		return dictionary;
	}

	public void setDictionary(Map<String, String> dictionary) {
		this.dictionary = dictionary;
	}

	protected ColumnConfig() {
		
	}
	
	public ColumnConfig(String attribute,String exportName){
		this.attribute = attribute;
		this.exportName = exportName;
	}
	
	public ColumnConfig(String attribute,String exportName,short width){
		this.attribute = attribute;
		this.exportName = exportName;
		this.width = width;
	}
	
	public ColumnConfig(String attribute,String exportName,short width,HSSFCellStyle style){
		this.attribute = attribute;
		this.exportName = exportName;
		this.width = width;
		this.style = style;
	}
	
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getExportName() {
		return exportName;
	}
	public void setExportName(String exportName) {
		this.exportName = exportName;
	}
	public Short getWidth() {
		return width;
	}
	public void setWidth(Short width) {
		this.width = width;
	}
	public HSSFCellStyle getStyle() {
		return style;
	}
	public void setStyle(HSSFCellStyle style) {
		this.style = style;
	}

	public Boolean isParseHTML() {
		return parseHTML;
	}

	public void setParseHTML(Boolean parseHTML) {
		this.parseHTML = parseHTML;
	}
	
	public String getDateFormatString() {
		String result = null;
		if(this.dateFormat != null
				&& this.dateFormat instanceof SimpleDateFormat){
			SimpleDateFormat sdf = (SimpleDateFormat)this.dateFormat;
			result = sdf.toPattern();
		}
		return result;
	}
}
