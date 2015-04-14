package com.up72.common.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 * Excel写工具
 * @author wqtan
 *
 */

public class ExcelWritePoiUtil {

//	public final static short ENCODING = HSSFWorkbook.ENCODING_UTF_16;

	/**
	 * excel对象
	 */
	private HSSFWorkbook excel;

	/**
	 * 无参的构造方法，创建一个新的excel文档
	 */
	public ExcelWritePoiUtil() {
		this.excel = this.createExcel();
	}

	/**
	 * 根据给定的excel文件创建一个excel文档，如果file不存在，则创建。
	 * 
	 * @param fileName
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public ExcelWritePoiUtil(String fileName) throws FileNotFoundException,
			IOException {
		File file = new File(fileName);
		if(file.exists()){
			this.excel = this.getExcel(fileName);
		}else{
			this.excel = this.createExcel();
		}
	}

	/**
	 * 读取一个excel文件，获得操作对象
	 * 
	 * @param fileName
	 *            excle文件路径
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	protected HSSFWorkbook getExcel(String fileName)
			throws FileNotFoundException, IOException {
		HSSFWorkbook result = null;

		result = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(
				fileName)));

		return result;
	}

	/**
	 * 创建一个新的excel文档
	 */
	protected HSSFWorkbook createExcel() {
		HSSFWorkbook result = null;
		result = new HSSFWorkbook();
		return result;
	}

	/**
	 * 创建一个新的工作区
	 * 
	 * @param name 工作区名称
	 * @return HSSFSheet
	 */
	public HSSFSheet createSheet() {
		HSSFSheet result = this.excel.createSheet();
		return result;
	}
	
	/**
	 * 创建一个新的工作区
	 * 
	 * @param name 工作区名称
	 * @return HSSFSheet
	 */
	public HSSFSheet createSheet(String name) {
		HSSFSheet result = this.getSheet(name);
		if(null == result){
			result = this.excel.createSheet();
			this.excel.setSheetName(this.excel.getNumberOfSheets()-1, name);
		}
		return result;
	}

	/**
	 * 获得指定下标的工作区
	 * 
	 * @param name
	 * @param index
	 * @return HSSFSheet
	 */
	public HSSFSheet getSheet(String name, int index) {
		HSSFSheet result = null;
		this.excel.setSheetName(index, name);
		result = this.excel.getSheetAt(index);
		return result;
	}

	/**
	 * 获得指定下标的工作区
	 * 
	 * @param index
	 * @return HSSFSheet
	 */
	public HSSFSheet getSheet(int index) {
		HSSFSheet result = null;
		result = this.excel.getSheetAt(index);
		return result;
	}

	/**
	 * 获得指定下标的工作区
	 * 
	 * @param name
	 * @return HSSFSheet
	 */
	public HSSFSheet getSheet(String name) {
		HSSFSheet result = null;
		result = this.excel.getSheet(name);
		return result;
	}

	/**
	 * 设置单元格的宽度，宽度的单位是一个字节
	 */
	public void setWidth(HSSFSheet sheet,short cellIndex,short ecellWidthllWidth){
		sheet.setColumnWidth(cellIndex, (short) (ecellWidthllWidth * 256));
	}
	
	/**
	 * 设置单元格的宽度，宽度的单位是一个字节
	 */
	public void setWidth(HSSFSheet sheet,short[] cellIndexs,short[] cellWidths){
		for(int i=0;i<cellIndexs.length;i++){
			this.setWidth(sheet, cellIndexs[i], cellWidths[i]);
		}
	}
	
	/**
	 * excel末尾追加一行记录，每行的样式循环使用
	 * 
	 * @param sheet
	 *            工作区
	 * @param cells
	 *            单元格的值
	 * @param styles
	 *            单元格样式样式数组
	 */
	public void addRow(HSSFSheet sheet, String[] cells, HSSFCellStyle[] styles) {
		int rowNum = sheet.getLastRowNum() + 1;
		HSSFRow row = sheet.createRow(rowNum);
		for (int i = 0; i < cells.length; i++) {
			if (styles != null && styles.length > 0) {
				HSSFCellStyle style = styles[row.getRowNum() % styles.length];
				addCell(row, cells[i], style);
			} else {
				addCell(row, cells[i], null);
			}
		}
	}
	
	/**
	 * 在excel指定下标处添加一条记录
	 * 
	 * @param sheet
	 *            工作区
	 * @param index
	 * 			  下标
	 * @param cells
	 *            单元格的值
	 * @param style
	 *            单元格样式样式
	 */
	public void addRow(HSSFSheet sheet,int index, String[] cells, HSSFCellStyle style) {
		HSSFRow row = sheet.createRow(index);
		if(null != row){
			for (int i = 0; i < cells.length; i++) {
				addCell(row, cells[i], style);
			}
		}
	}

	/**
	 * excel末尾追加一行记录，每行的样式循环使用按spacing循环使用
	 * 
	 * @param sheet
	 *            工作区
	 * @param cells
	 *            单元格的值
	 * @param styles
	 *            单元格样式数组
	 * @param spacing
	 *            样式改变行间隔
	 */
	public void addRow(HSSFSheet sheet, String[] cells, HSSFCellStyle[] styles,
			int spacing) {
		if (spacing <= 0) {
			spacing = 1;
		}

		int rowNum = sheet.getLastRowNum() + 1;
		HSSFRow row = sheet.createRow(rowNum);
		for (int i = 0; i < cells.length; i++) {
			if (styles != null && styles.length > 0) {
				HSSFCellStyle style = styles[(row.getRowNum() / spacing)
						% styles.length];
				addCell(row, cells[i], style);
			} else {
				addCell(row, cells[i], null);
			}
		}
	}
	
	/**
	 * excel末尾追加一行记录
	 * 
	 * @param sheet
	 *            工作区
	 * @param cells
	 *            单元格的值
	 * @param style
	 *            单元格样式
	 */
	public void addRow(HSSFSheet sheet, String[] cells, HSSFCellStyle style) {
		int rowNum = sheet.getLastRowNum() + 1;
		HSSFRow row = sheet.createRow(rowNum);
		for (int i = 0; i < cells.length; i++) {
			addCell(row, cells[i], style);
		}
	}
	
	/**
	 * excel末尾追加一行记录，每行的样式循环使用
	 * 
	 * @param sheet
	 *            工作区
	 * @param cells
	 *            单元格的值
	 * @param styles
	 *            单元格样式样式数组
	 */
	public void editRow(HSSFSheet sheet,int index, String[] cells, HSSFCellStyle style) {
		HSSFRow row = sheet.getRow(index);
		if(null != row){
			for (short i = 0; i < cells.length; i++) {
				editCell(row,i, cells[i], null);
			}
		}
	}
	
	/*
	 * 添加一个单元格
	 */
	protected void editCell(HSSFRow row,short index, String data, HSSFCellStyle style) {
		HSSFCell cell = row.getCell(index);
//		cell.setEncoding(ENCODING);
		cell.setCellValue(data);
		if (null != style) {
			cell.setCellStyle(style);
		}
	}

	/*
	 * 添加一个单元格
	 */
	protected void addCell(HSSFRow row, String data, HSSFCellStyle style) {
		HSSFCell cell = row.createCell(row.getLastCellNum()==-1? row.getLastCellNum()+1:row.getLastCellNum());
		
//		cell.setEncoding(ENCODING);
		cell.setCellValue(data);
		if (null != style) {
			cell.setCellStyle(style);
		}
	}
	
	/**
	 * excel删除指定下标的行
	 * 
	 * @param sheet
	 *            工作区
	 * @param cells
	 *            单元格的值
	 * @param style
	 *            单元格样式
	 */
	public void deleteRow(HSSFSheet sheet, int index) {
		HSSFRow row = sheet.getRow(index);
		if(null != row){
			sheet.removeRow(row);
		}
	}

	/**
	 * 创建一个不带有任何样式的单元格样式创建单元格样式
	 * @return HSSFCellStyle
	 */
	public HSSFCellStyle createStyle() {
		HSSFCellStyle style = this.excel.createCellStyle();
		style.setFillPattern(HSSFCellStyle.SPARSE_DOTS);
		return style;
	}
	
	/**
	 *  根据指定的背景颜色创建单元格样式
	 * @param backColor 背景颜色
	 * @return HSSFCellStyle
	 */
	public HSSFCellStyle createStyle(HSSFColor backColor) {
		HSSFCellStyle style = this.excel.createCellStyle();
		// 背景色设置
		style.setFillPattern(HSSFCellStyle.SPARSE_DOTS);
		
		if(null != backColor){
			style.setFillForegroundColor(backColor.getIndex());
			style.setFillBackgroundColor(backColor.getIndex());
		}
		
		return style;
	}
	
	/**
	 * 根据指定的边框颜色创建单元格样式
	 * @param borderColor 边框颜色
	 * @return HSSFCellStyle
	 */
	public HSSFCellStyle createStyleWithBorder(HSSFColor borderColor) {
		HSSFCellStyle style = this.excel.createCellStyle();
		if(null == borderColor){
			style = this.setBorder(style, new HSSFColor.BLACK());
		}else{
			style = this.setBorder(style, borderColor);
		}
		return style;
	}
	
	/**
	 * 根据指定的边框颜色和背景颜色创建单元格样式
	 * @param borderColor 边框颜色
	 * @param backColor 背景颜色
	 * @return HSSFCellStyle
	 */
	public HSSFCellStyle createStyleWithBorder(HSSFColor borderColor,HSSFColor backColor) {
		HSSFCellStyle style = this.excel.createCellStyle();
		// 背景色设置
		style.setFillPattern(HSSFCellStyle.SPARSE_DOTS);
		
		if(null == borderColor){
			style = this.setBorder(style, new HSSFColor.BLACK());
		}else{
			style = this.setBorder(style, borderColor);
		}
		
		if(null != backColor){
			style.setFillForegroundColor(backColor.getIndex());
			style.setFillBackgroundColor(backColor.getIndex());
		}
		
		return style;
	}
	
	/*
	 * 设置边框
	 */
	protected HSSFCellStyle setBorder(HSSFCellStyle style,HSSFColor borderColor){
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
		style.setBottomBorderColor(borderColor.getIndex());
		style.setLeftBorderColor(borderColor.getIndex());
		style.setRightBorderColor(borderColor.getIndex());
		style.setTopBorderColor(borderColor.getIndex());
		return style;
	}

	public void saveExcel(String fileName) throws IOException {
		File file = new File(fileName);
		if (!file.exists()) {
			if(fileName.indexOf("\\") != -1){
				fileName = fileName.replaceAll("\\", "/");
			}
			String filePath = fileName.substring(0, fileName.lastIndexOf('/'));
			file = new File(filePath);
			file.mkdirs();
		}
		FileOutputStream fileOut = new FileOutputStream(fileName);
		this.excel.write(fileOut);
		fileOut.close();
	}
	
	/**
	 * 获得excel的实例
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook getExcel() {
		return excel;
	}
	
}
