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


public class ExelImportPoiUtil<T> {

	private ExcelReadPoiUtil erp;
	private List<ColumnConfig> configList;
	private Class clazz;
	private Map<String,Short> columnParam;
	
	public ExelImportPoiUtil(Class clazz, String fileName,
			List<ColumnConfig> configList) throws FileNotFoundException,
			IOException {
		this.erp = new ExcelReadPoiUtil(fileName);
		this.configList = configList;
		this.clazz = clazz;
		this.init();
	}

	public ExelImportPoiUtil(Class clazz, InputStream inputStream,
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
				for(short i=row.getFirstCellNum();i<row.getLastCellNum();i++){
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
		for(int i=0;i<this.configList.size();i++){
			if(exportName.trim().equals(this.configList.get(i).getExportName().trim())){
				result = this.configList.get(i).getAttribute();
				break;
			}
		}
		return result;
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
		
		return null;
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
		HSSFCell cell = row.getCell(this.columnParam.get(config.getAttribute()));
		// 获得放回类型
		String returnType = method.getReturnType().getName();
		if(null != config.getDictionary() 
				&& config.getDictionary().size() > 0){
			String value = getDictionaryKey(this.erp.getStringValue(cell), config.getDictionary());
			result = this.getObjectFromString(returnType, value, config);
		}else if(null != config.getDictionary() 
				&& config.isParseHTML()){
			result = stringUtil.removeHtmlTag(erp.getStringValue(cell));
		}else{
			result = this.getObjectByType(returnType, cell,config);
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
	protected Object getObjectFromString(String type,String value,ColumnConfig config){
		Object result = null;
		if(type.equals("java.lang.String")){
			result = value;
		} else if(type.equals("java.lang.Integer")
				|| type.equals("int")){
			result = stringUtil.parseInt(value);
		} else if(type.equals("java.lang.Long")
				|| type.equals("long")){
			result = stringUtil.parseLong(value);
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
			result = stringUtil.parseDate(value, config.getDateFormat());
		} else if(type.equals("java.sql.Date")){
			result = stringUtil.parseDate(value, config.getDateFormat());
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
	
//	public static void main(String[] args) throws FileNotFoundException,
//			IOException {
//		String file = "C:/poi/test.xls";
//
//		List<ColumnConfig> configList = new ArrayList<ColumnConfig>();
//		configList.add(new ColumnConfig("email", "邮箱", (short) 30));
//		configList.add(new ColumnConfig("name", "姓名"));
//		configList.add(new ColumnConfig("age", "年龄"));
//		configList.add(new ColumnConfig("birthday", "生日"));
//
//		ColumnConfig sexConf = new ColumnConfig("sex", "性别");
//		Map<String, String> dictionary = new HashMap<String, String>();
//		dictionary.put("1", "男");
//		dictionary.put("0", "女");
//		sexConf.setDictionary(dictionary);
//		configList.add(sexConf);
//
//		ExelImportPoiUtil<User> eip = new ExelImportPoiUtil<User>(User.class,
//				file, configList);
//		List<User> list = eip.getAllList();
//		if(null != list && list.size() > 0){
//			for(int i=0;i<list.size();i++){
//				User user = list.get(i);
//				System.out.print(user.getName()+"\t");
//				System.out.print(user.getAge()+"\t");
//				System.out.print(user.getEmail()+"\t");
//				System.out.print(user.getBirthday()+"\t");
//				System.out.println(user.getSex()+"\t");
//				System.out.println();
//			}
//		}else{
//			System.out.println("list is null...");
//		}
//	}
}
