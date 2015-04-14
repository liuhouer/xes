package com.up72.common.excel;

import static com.up72.common.CommonUtils.stringUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.up72.common.reflect.BeanUtil;


public class ExcelImportPoiUtil<T> {

	private ExcelReadPoiUtil erp;
	private List<ColumnConfig> configList;
	private Class clazz;
	private Map<String,Short> columnParam;
	
	public ExcelImportPoiUtil(Class clazz, String fileName,
			List<ColumnConfig> configList) throws FileNotFoundException,
			IOException {
		this.erp = new ExcelReadPoiUtil(fileName);
		this.configList = configList;
		this.clazz = clazz;
		this.init();
	}

	public ExcelImportPoiUtil(Class clazz, InputStream inputStream,
			List<ColumnConfig> configList) throws IOException {
		this.erp = new ExcelReadPoiUtil(inputStream);
		this.configList = configList;
		this.clazz = clazz;
		this.init();
	}
	
	protected void init(){
		if(null != this.configList
				&& this.configList.size() > 0){
			columnParam = new HashMap<String, Short>();
			
			HSSFSheet sheet = this.erp.getSheet(0);
			HSSFRow row = this.erp.getRow(sheet, sheet.getFirstRowNum());
			
			if(null != row){
				for(short i=row.getFirstCellNum();i<=row.getLastCellNum();i++){
					String str = erp.getStringValue(row.getCell(i));
					String attribute = getAttribute(str);
					columnParam.put(attribute, new Short(i));
				}
			}
		}
	}
	
	/*
	 * 根据给定的别名，查找对应的属性名
	 */
	protected String getAttribute(String exportName){
		String result = null;
		if(null != exportName
				&& !exportName.trim().equals("")){
			for(int i=0;i<this.configList.size();i++){
				if(exportName.trim().equals(this.configList.get(i).getExportName().trim())){
					result = this.configList.get(i).getAttribute();
					break;
				}
			}
		}
		return result;
	}
	
	public HSSFSheet[] getAllSheets(){
		return this.erp.getSheets();
	}

	/**
	 * 读取指定工作区，从start开始的size条数据
	 * 
	 * @param sheet
	 *            工作区
	 * @param start
	 *            开始记录
	 * @param size
	 *            条数
	 * @return List<T>
	 */
	public List<T> getSheetPager(HSSFSheet sheet, int start, int size) {
		List<T> result = new ArrayList<T>();
		if(null != sheet){
			for(int i=start;i<start+size;i++){
				HSSFRow row = erp.getRow(sheet, i);
				if(null != row){
					T obj = this.getObject(row);
					result.add(obj);
				}
			}
		}
		return result;
	}

	/**
	 * 获得所有excel中所有的list
	 * 
	 * @return
	 */
	public List<T> getAllList() {
		List<T> result = new ArrayList<T>();
		HSSFSheet[] sheets = erp.getSheets();
		if (null != sheets && sheets.length > 0) {
			for (int i = 0; i < sheets.length; i++) {
				HSSFRow[] rows = erp.getRows(sheets[i]);
				if(null != rows && rows.length > 0){
					for(int j=1;j<rows.length;j++){
						T obj = this.getObject(rows[j]);
						result.add(obj);
					}
				}
			}
		}
		return result;
	}

	@SuppressWarnings({"unchecked"})
	public T getObject(HSSFRow row) {
		Object obj = createNewObject();
		
		for(int i=0;i<this.configList.size();i++){
			ColumnConfig config = configList.get(i);
			// 获得get方法
			Method method = BeanUtil.getPropertyGetMethod(obj, config.getAttribute());
			// 根据方法返回值，获得对应数据类型的值
			Object value = this.getValue(method, row, config);
			// 设置属性的值
			setValue(obj,config.getAttribute(),value,method.getReturnType());
		}
		T result = null;
		result = (T) obj;
		return result;
	}
	
	protected void setValue(Object obj,String attribute,Object value,Class type){
		BeanUtil.setPropertyByMethod(obj, attribute, value,type);
	}
	
	/**
	 * 根据方法返回值，获得对应数据类型的值
	 * @param method 方法
	 * @param row 行
	 * @param config 列配置
	 * @return Object
	 */
	protected Object getValue(Method method,HSSFRow row,ColumnConfig config){
		Object result = null;
		// 获得列
		Short index = this.columnParam.get(config.getAttribute());
		if(null != index){
			HSSFCell cell = row.getCell(index);
			// 获得放回类型
			String returnType = method.getReturnType().getName();
			String value = this.erp.getStringValue(cell);
			if(null != config.getDictionary() 
					&& config.getDictionary().size() > 0){
				result = getDictionaryKey(value, config.getDictionary());
			}else {
				result = this.getObjectFromString(returnType, value, config,cell);
			}
		}else{
		//	System.out.println(columnParam);
		//	System.out.println(index +"\t"+config.getAttribute());
		//	System.out.println("-------------------------------");
		}
		return result;
	}
	
	/*
	 * 根据给定的value获得对应的key值
	 */
	protected String getDictionaryKey(String attribute,Map<String,String> dictionary){
		String result = null;
		Iterator<String> it = dictionary.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(attribute.trim().equals(dictionary.get(key))){
				result = key;
				break;
			}
		}
		return result;
	}
	
	/*
	 * 格式化string成为Object对象
	 */
	protected Object getObjectFromString(String type,String value,ColumnConfig config,HSSFCell cell){
		Object result = null;
		if(type.equals("java.lang.String")){
			result = value;
		} else if(type.equals("java.lang.Integer")
				|| type.equals("int")){
			result = stringUtil.parseInt(value);
		} else if(type.equals("java.lang.Long")
				|| type.equals("long")){
			if(null != config.getDateFormat()){
				Date date =  this.erp.getDateValue(cell, config.getDateFormat());
				if(null != date){
					result = date.getTime();
				}else{
					result = null;
				}
			}else{
				result = stringUtil.parseLong(value);
			}
		} else if(type.equals("java.lang.Double")
				|| type.equals("double")){
			result = stringUtil.parseDouble(value);
		} else if(type.equals("java.lang.Float")
				|| type.equals("float")){
			result = stringUtil.parseFloat(value);
		} else if(type.equals("java.lang.Short")
				|| type.equals("short")){
			result = stringUtil.parseShort(value);
		} else if(type.equals("java.util.Date")){
			result =  this.erp.getDateValue(cell, config.getDateFormat());
		} else if(type.equals("java.sql.Date")){
			result = this.erp.getDateValue(cell, config.getDateFormat());
			if(null != result){
				Date date = (Date)result;
				result = new java.sql.Date(date.getTime()); 
			}
		} else if(type.equals("java.lang.Byte")
				|| type.equals("byte")){
			
		} else if(type.equals("java.lang.Boolean")
				|| type.equals("boolean")){
			result = stringUtil.parseBoolean(value);
		} else if(type.equals("java.lang.Character")
				|| type.equals("char")){
			result = stringUtil.parseChar(value);
		} 
		return result;
	}
	
	/*
	 * 根据给定的类型，获得指定单元格的值
	 */
	protected Object getObjectByType(String type,HSSFCell cell,ColumnConfig config){
		Object result = null;
		if(type.equals("java.lang.String")){
			result = this.erp.getStringValue(cell);
		} else if(type.equals("java.lang.Integer")
				|| type.equals("int")){
			result = this.erp.getIntValue(cell);
		} else if(type.equals("java.lang.Long")
				|| type.equals("long")){
			result = this.erp.getLongValue(cell);
		} else if(type.equals("java.lang.Double")
				|| type.equals("double")){
			result = this.erp.getDoubleValue(cell);
		} else if(type.equals("java.lang.Float")
				|| type.equals("float")){
			result = this.erp.getFloatValue(cell);
		} else if(type.equals("java.lang.Short")
				|| type.equals("short")){
			result = this.erp.getShortValue(cell);
		} else if(type.equals("java.util.Date")){
			result = this.erp.getDateValue(cell,config.getDateFormat());
		} else if(type.equals("java.sql.Date")){
			result = this.erp.getDateValue(cell,config.getDateFormat());
			if(null != result){
				Date date = (Date)result;
				result = new java.sql.Date(date.getTime()); 
			}
		} else if(type.equals("java.lang.Byte")
				|| type.equals("byte")){
			result = this.erp.getByteValue(cell);
		} else if(type.equals("java.lang.Boolean")
				|| type.equals("boolean")){
			result = this.erp.getBooleanValue(cell);
		} else if(type.equals("java.lang.Character")
				|| type.equals("char")){
			result = this.erp.getCharValue(cell);
		} 
		return result;
	}
	
	protected Object createNewObject(){
		Object result = null;
		if(null != this.clazz){
			try {
				result = this.clazz.newInstance();
			} catch (InstantiationException e) {
				result = null;
			} catch (IllegalAccessException e) {
				result = null;
			}
		}
		return result;
	}
}
