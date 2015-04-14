package com.up72.common.excel;

import static com.up72.common.CommonUtils.stringUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import com.up72.common.reflect.BeanUtil;

public class ExcelExportPoiUtil {
	/**
	 * excel写入处理工具类
	 */
	private ExcelWritePoiUtil ewp;
	private HSSFSheet sheet;

	private String fileName;
	private List<ColumnConfig> configList;
	/**
	 * 是否有边框
	 */
	private HSSFColor borderColor;
	/**
	 * 导出的背景颜色列表
	 */
	private HSSFColor[] backColors;
	/**
	 * 背景色使用间隔
	 */
	private Integer spacing;

	private HSSFCellStyle[] styles;

	public ExcelExportPoiUtil(String fileName, List<ColumnConfig> configList) {
		this(fileName, -1, configList);
	}

	public ExcelExportPoiUtil(String fileName, int sheetIndex,
			List<ColumnConfig> configList) {
		this.fileName = fileName;
		this.configList = configList;

		init(sheetIndex);
	}

	/*
	 * 初始化操作，初始化ExcelWriteUtil工具类，HSSFSheet等相关数据
	 */
	protected void init(int sheetIndex) {
		// 初始化操作工具
		try {
			ewp = new ExcelWritePoiUtil(this.fileName);
		} catch (FileNotFoundException e) {
			ewp = new ExcelWritePoiUtil();
		} catch (IOException e) {
			ewp = new ExcelWritePoiUtil();
		}
		// 初始化工作区
		if (sheetIndex >= 0 && ewp.getExcel().getNumberOfSheets() > sheetIndex) {
			this.sheet = ewp.getSheet(sheetIndex);
		}
		if (null == this.sheet) {
			this.sheet = ewp.createSheet();
		}
		// 初始化单元格宽度
		for (int i = configList.size() - 1; i >= 0; i--) {
			if (configList.get(i) != null) {
				Short width = configList.get(i).getWidth();
				if (null != width && width > 0) {
					ewp.setWidth(this.sheet, (short) i, width);
				}
			} else {
				configList.remove(i);
			}
		}
	}

	/**
	 * 头部导出
	 * 
	 * @param borderColor
	 *            边框颜色
	 * @param backColor
	 *            背景色
	 */
	public void exportHeader(HSSFColor borderColor, HSSFColor backColor) {
		String[] exportNames = new String[configList.size()];
		for (int i = 0; i < configList.size(); i++) {
			exportNames[i] = configList.get(i).getExportName();
		}
		
		HSSFCellStyle style = ewp.createStyleWithBorder(borderColor, backColor);
		ewp.addRow(sheet, 0, exportNames, style);
	}

	/**
	 * 从指定的list导出excel
	 * 
	 * @param list
	 */
	@SuppressWarnings( { "unchecked" })
	public void export(List list) {
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			if (null != obj) {
				this.export(obj);
			}
		}
	}

	/*
	 * 导出一行数据
	 */
	protected void export(Object obj) {
		String[] values = new String[this.configList.size()];
		for (int i = 0; i < this.configList.size(); i++) {
			ColumnConfig config = this.configList.get(i);
			values[i] = getExportString(config, obj);
		}
		if (null != this.spacing
				&& this.spacing > 0) {
			ewp.addRow(sheet, values, this.styles, this.spacing);
		} else{
			ewp.addRow(sheet, values, this.styles);
		}
	}
	
	/*
	 * 获得导出数据的string形式
	 */
	protected String getExportString(ColumnConfig config,Object obj) {
		String result = null;
		Object value = this.getBeanValue(config.getAttribute(), obj);
		// 对象转为string
		result = parseObjectToString(config, value);
		if(null != config){
			// 数据字典处理
			if(null != config.getDictionary() 
					&& config.getDictionary().size() > 0){
				result = this.dictionaryParse(config.getDictionary(), result);
			}
			// html处理
			if(null != config.isParseHTML() 
					&& config.isParseHTML()){
				result = htmlParse(result);
			}
		}
		return result;
	}

	/*
	 * 转换对象成为string
	 */
	protected String parseObjectToString(ColumnConfig config,
			Object obj) {
		String result = null;
		// 正常的对象数据处理
		if (null == obj || null == config) {
			result = " ";
		} else if (obj instanceof java.util.Date 
				&& null != config.getDateFormat()) {
			result = config.getDateFormat().format(obj);
		} else if (obj instanceof java.lang.Long 
				&& null != config.getDateFormat()) {
			if(null != obj){
				Long longDate = stringUtil.parseLong(obj.toString());
				if(null != longDate){
					Date date = new Date(longDate);
					result = config.getDateFormat().format(date);
				}
			}else{
				result = "";
			}
		} else {
			result = obj.toString();
		}
		return result;
	}
	
	/*
	 * html处理
	 */
	protected String htmlParse(String str){
		String result = str;
		if(null != result
				&& !result.trim().equals("")){
			result = stringUtil.removeHtmlTag(str);
		}
		return result;
	}
	
	/*
	 * 数据字典处理
	 */
	protected String dictionaryParse(Map<String,String> dictionary, String str){
		String result = str;
		// 数据字典处理
		if ( null != str
				&& !str.trim().equals("")) {
			result = dictionary.get(str.trim());
		}
		return result;
	}

	/**
	 * 获得指定javabean的指定属性值
	 * 
	 * @param attribute
	 *            属性名
	 * @param javabean对象
	 */
	protected Object getBeanValue(String attribute, Object obj) {
		Object result = null;

		while (attribute.indexOf('.') != -1) {
			String attr = attribute.substring(0, attribute.indexOf('.'));
			attribute = attribute.substring(attribute.indexOf('.') + 1);
			if (null != obj) {
				obj = BeanUtil.getPropertyByMethod(obj, attr);
			} else {
				break;
			}
		}
		if (null != obj) {
			result = BeanUtil.getPropertyByMethod(obj, attribute);
		}

		return result;
	}
	
	protected void initStyles(){
		// 初始化单元格样式
		if (null != this.backColors
				&& this.backColors.length > 0) {
			List<HSSFCellStyle> list = new LinkedList<HSSFCellStyle>();
			for (int i = 0; i < this.backColors.length; i++) {
				HSSFColor backColor = this.backColors[i];
				if (null != backColor) {
					if(null == this.borderColor){
						list.add(ewp.createStyle(backColor));
					}else{
						list.add(ewp.createStyleWithBorder(borderColor, backColor));
					}
				}
			}
			if (list.size() > 0) {
				this.styles = new HSSFCellStyle[list.size()];
				for (int i = 0; i < list.size(); i++) {
					this.styles[i] = list.get(i);
				}
			}
		}else{
			HSSFCellStyle style = ewp.createStyleWithBorder(this.borderColor);
			this.styles = new HSSFCellStyle[]{style};
		}
	}

	/**
	 * 保存excel
	 * 
	 * @throws IOException
	 */
	public void save() throws IOException {
		ewp.saveExcel(this.fileName);
	}

	/**
	 * 保存excel
	 * 
	 * @throws IOException
	 */
	public void save(String fileName) throws IOException {
		ewp.saveExcel(fileName);
	}

	public List<ColumnConfig> getConfigList() {
		return configList;
	}

	public void setConfigList(List<ColumnConfig> configList) {
		this.configList = configList;
	}

	public HSSFColor[] getBackColors() {
		return backColors;
	}

	public HSSFColor getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(HSSFColor borderColor) {
		this.borderColor = borderColor;
		initStyles();
	}

	public void setBackColors(HSSFColor[] backColors) {
		this.backColors = backColors;
		initStyles();
	}

	public Integer getSpacing() {
		return spacing;
	}

	public void setSpacing(Integer spacing) {
		this.spacing = spacing;
	}

	public HSSFCellStyle[] getStyles() {
		return styles;
	}

	public void setStyles(HSSFCellStyle[] styles) {
		this.styles = styles;
	}

	public String getFileName() {
		return fileName;
	}
	
	public static void main(String[] args) throws Exception {
		List<User> dataList = new LinkedList<User>();
		for (int i = 0; i < 50; i++) {
			User user = new User();
			user.setId(new Long(i));
			user.setName("name" + i);
			user.setAge(i * 5 % 100);
			user.setEmail("xx" + i + "@" + i + ".com");
			user.setBirthday(new java.util.Date());
			user.setSex(i % 2);

			dataList.add(user);
		}

		List<ColumnConfig> configList = new ArrayList<ColumnConfig>();
		configList.add(new ColumnConfig("email", "邮箱", (short) 30));
		configList.add(new ColumnConfig("name", "姓名"));
		configList.add(new ColumnConfig("age", "年龄"));
		configList.add(new ColumnConfig("birthday", "生日"));

		ColumnConfig sexConf = new ColumnConfig("sex", "性别");
		Map<String, String> dictionary = new HashMap<String, String>();
		dictionary.put("1", "男");
		dictionary.put("0", "女");
		sexConf.setDictionary(dictionary);
		configList.add(sexConf);

		ExcelExportPoiUtil eep = new ExcelExportPoiUtil("C:/poi/export.xls", 0,
				configList);

		eep.exportHeader(new HSSFColor.BLACK(),
				new HSSFColor.WHITE());

		eep.export(dataList);
		eep.save();
	}

}


class User{
	private String name;
	private String email;
	private Long id;
	private int age;
	private Date birthday;
	private int sex;
	
	public User(){
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
}
