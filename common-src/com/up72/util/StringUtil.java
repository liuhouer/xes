package com.up72.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.up72.framework.util.ObjectUtils;

public class StringUtil {
	/**
	 * 将空对象赋予默认值
	 * 
	 * @param value
	 *            操作对象
	 * @param defaultValue
	 *            默认值
	 * @author jhe
	 * @return
	 */
	public  String defaultIfEmpty(Object value, String defaultValue) {
		if (value == null || "".equals(value)) {
			return defaultValue;
		}
		return value.toString();
	}

	public  String replace(String inString, String oldPattern,
			String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}

	/** copy from spring */
	public  String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	/** copy from spring */
	public  String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	/** copy from spring */
	private  String changeFirstCharacterCase(String str,
			boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		} else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}

	public  String removeEndWiths(String inputString, String... endWiths) {
		for (String endWith : endWiths) {
			if (inputString.endsWith(endWith)) {
				return inputString.substring(0, inputString.length()
						- endWith.length());
			}
		}
		return inputString;
	}

	/**
	 * 将list按照 按 ‘seperator’ 连接
	 * 
	 * @param list
	 *            连接目标对象
	 * @param seperator
	 *            连接字符串
	 * @author jhe
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  String join(List list, String seperator) {
		return join(list.toArray(new Object[0]), seperator);
	}
	

	public  String join(Object[] array, String seperator) {
		if (array == null)
			return null;
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			result.append(array[i]);
			if (i != array.length - 1) {
				result.append(seperator);
			}
		}
		return result.toString();
	}
	
	/**
	 * 得到用下划线分隔的名称，如className=UserInfo,则underscoreName=user_info
	 * 
	 * @author jhe
	 * @return
	 */
	public  String getUnderscoreStr(String str) {
		return this.toUnderscoreName(str).toLowerCase();
	}

	/**
	 * 返回值为name的第一个字母小写,如className=UserInfo,则ClassNameFirstLower=userInfo
	 * 
	 * @author jhe
	 * @return
	 */
	public  String getStrFirstLower(String str) {
		return this.uncapitalize(str);
	}

	/**
	 * 根据name计算而来,用于得到常量名,如className=UserInfo,则constantName=USER_INFO
	 * 
	 * @author jhe
	 * @return
	 */
	public  String getConstantStr(String str) {
		return this.toUnderscoreName(str).toUpperCase();
	}

	/**
	 * Convert a name in camelCase to an underscored name in lower case. Any
	 * upper case letters are converted to lower case with a preceding
	 * underscore.
	 * 
	 * @param filteredName
	 *            the string containing original name
	 * @return the converted name
	 */
	public  String toUnderscoreName(String name) {
		if (name == null)
			return null;

		String filteredName = name;
		if (filteredName.indexOf("_") >= 0
				&& filteredName.equals(filteredName.toUpperCase())) {
			filteredName = filteredName.toLowerCase();
		}
		if (filteredName.indexOf("_") == -1
				&& filteredName.equals(filteredName.toUpperCase())) {
			filteredName = filteredName.toLowerCase();
		}

		StringBuffer result = new StringBuffer();
		if (filteredName != null && filteredName.length() > 0) {
			result.append(filteredName.substring(0, 1).toLowerCase());
			for (int i = 1; i < filteredName.length(); i++) {
				String preChart = filteredName.substring(i - 1, i);
				String c = filteredName.substring(i, i + 1);
				if (c.equals("_")) {
					result.append("_");
					continue;
				}
				if (preChart.equals("_")) {
					result.append(c.toLowerCase());
					continue;
				}
				if (c.matches("\\d")) {
					result.append(c);
				} else if (c.equals(c.toUpperCase())) {
					result.append("_");
					result.append(c.toLowerCase());
				} else {
					result.append(c);
				}
			}
		}
		return result.toString();
	}

	/**
	 * 统计字符串keyword出现的次数
	 * 
	 * @param string
	 * @param keyword
	 * @author jhe
	 * @return
	 */
	public  int containsCount(String string, String keyword) {
		if (string == null)
			return 0;
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			int indexOf = string.indexOf(keyword, i);
			if (indexOf < 0) {
				break;
			}
			count++;
			i = indexOf;
		}
		return count;
	}

	/**
	 * 把含有基本数据类型及包装数据类型的list，装换成item1,item2,item3的字符串
	 * 
	 * @author wqtan
	 */
	@SuppressWarnings("unchecked")
	public  String parseListToString(List list) {
		String result = null;
		if (ObjectUtils.isNotEmpty(list)) {
			StringBuffer buff = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				buff.append(list.get(i).toString() + ",");
			}
			result = buff.substring(0, buff.length() - 1);
		}

		return result;
	}

	/**
	 * 是否为空
	 * 
	 * @author wqtan
	 */
	public  boolean isEmpty(String str) {
		boolean result = false;
		if (str == null || str.trim().equals("")) {
			result = true;
		}
		return result;
	}

	/**
	 * 用指定的规则拆分字符串
	 * 
	 * @param str
	 *            要拆分的字符串
	 * @param delim
	 *            拆分规则
	 */
	public  List<String> split(String str, String delim) {
		// 如果要拆分的字符串为空返回null
		if (null == str) {
			return null;
		}
		// 定义拆分规则
		StringTokenizer st = null;

		if (delim != null) {
			st = new StringTokenizer(str, delim);
		} else {
			st = new StringTokenizer(str);
		}
		// 如果没有拆分出的字符串，返回null
		if (!st.hasMoreTokens()) {
			return null;
		}
		// 遍历拆分出的字符串，添加到result中
		List<String> result = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			result.add(st.nextToken());
		}

		return result;
	}

	/**
	 * 将指定类名的转换为首字母小写的简单名称
	 * 
	 * @author wqtan
	 */
	public  String simpleClassNameAlifLC(String className) {
		String result = className;
		if (result != null && !result.trim().equals("")) {
			result = className.trim();
			result = result.indexOf('.') == -1 ? result : result
					.substring(result.lastIndexOf('.') + 1);
			result = (result.substring(0, 1).toLowerCase() + result.substring(
					1, result.length())).trim();
		}
		return result;
	}

	/**
	 * 将指定字符串首字母置为小写
	 * 
	 * @author wqtan
	 */
	public  String alifLowerCase(String str) {
		String result = str;
		if (result != null && !result.trim().equals("")) {
			result = result.trim();
			result = (result.substring(0, 1).toLowerCase() + result.substring(
					1, result.length())).trim();
		}
		return result;
	}

	/**
	 * 将指定字符串首字母置为大写
	 * 
	 * @author wqtan
	 */
	public  String alifUpperCase(String str) {
		String result = str;
		if (result != null && !result.trim().equals("")) {
			result = result.trim();
			result = (result.substring(0, 1).toUpperCase() + result.substring(
					1, result.length())).trim();
		}
		return result;
	}

	/**
	 * 根据表名获得对应的页面所在的路径，形如："aa/bb/"
	 * 
	 * @param tableName
	 *            表名
	 * @param prefix
	 *            是否有前缀
	 * @author wqtan
	 */
	public  String getPathByTableName(String tableName, boolean prefix) {
		// 如果表名为空，返回null
		if (null == tableName || tableName.trim().equals("")) {
			return null;
		}
		if (tableName.indexOf("_") == -1) {
			return alifLowerCase(tableName + "/");
		}
		List<String> list = split(tableName, "_");
		if (null == list || list.size() == 0) {
			return null;
		}

		StringBuffer result = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i).toLowerCase();
			if (prefix && i == 0) {
				result.append(str).append("/");
			} else if (list.size() > 1 && i == 1) {
				result.append(str);
			} else {
				result.append(alifUpperCase(str));
			}
		}
		result.append("/");
		return result.toString();
	}

	/*------------------------------------------------------------*/

	/**
	 * 格式化表名为没有下划线的形式，首字母小写 如：表名为cm_comtent_model格式化为cmContentModel
	 */
	public  String parseAlifLowerCase(String tableName) {
		String className = "";
		tableName = tableName.toLowerCase();
		String[] name = tableName.split("_");
		for (String str : name) {
			className += str.substring(0, 1).toUpperCase();
			className += str.substring(1, str.length()).toLowerCase();
		}
		String result = className.substring(0, 1).toLowerCase()
				+ className.substring(1, className.length());
		return result;
	}

	/**
	 * 格式化表名为没有下划线的形式，首字母大写
	 * 
	 * @param tableName
	 * @return
	 */
	public  String parseAlifUpperCase(String tableName) {
		String className = "";
		tableName = tableName.toLowerCase();
		String[] name = tableName.split("_");
		int i = 0;
		for (String str : name) {
			if (i > 0) {
				className += str.substring(0, 1).toUpperCase();
				className += str.substring(1, str.length()).toLowerCase();
			}
			i++;
		}
		if (className.equals(""))
			className = tableName.substring(0, 1).toUpperCase()
					+ tableName.substring(1).toLowerCase();
		return className;
	}

	/**
	 * 将给定数组的string拼接成 str1/str2/str3的格式
	 * 
	 * @param params
	 */
	public  String jointToPath(String[] params) {
		StringBuffer result = null;
		if (null != params && params.length > 0) {
			result = new StringBuffer();
			for (int i = 0; i < params.length; i++) {
				if (null == params[i] || params[i].trim().equals("")) {
					continue;
				} else {
					result.append(parseToPath(params[i]));
				}
			}
		}

		return result.toString();
	}

	public  String parseToPath(String str) {
		str = str.replace('\\', '/');
		str = str.replaceAll("/{2,}","/");
		return str;
	}

	/**
	 * 将表达式转化为分割符
	 * 
	 */
	public  String expParamStr(String str) {
		String rep = "";
		rep = str.replaceAll("[\\+\\-\\*\\/]", "|");
		rep = rep.replaceAll("[()]", "");
		StringTokenizer st = new StringTokenizer(rep, "|");

		// 属性列表
		List<String> list = new ArrayList<String>();

		while (st.hasMoreTokens()) {

			String pro = st.nextToken();
			pro = pro.trim();
			if (!isInt(pro)) {
				String rePro = toProperty(pro);
				list.add(rePro);
			}
		}

		String sp = str.replaceAll("([a-zA-Z]{1}[0-9]+)", "param");

		return getExpr(sp, list);
	}

	public  String toProperty(String s) {
		if (isEmpty(s)) {
			return "";
		}
		if (isInt(s)) {
			return s;
		}
		s = s.trim();
		String bean = "bean";
		s = s.toLowerCase();
		// 分隔property
		String property = "";
		property = property + bean + ".";
		property = property + s;
		property = "(((" + property + ")!0)?number)";

		return property;
	}

	public  String getExpr(String str, List<String> strList) {
		for (int i = 0; i < strList.size(); i++) {
			str = str.replaceFirst("param", strList.get(i));
		}
		return str;
	}

	public  boolean isInt(String str) {
		boolean result = true;
		if (null == str || str.trim().equals("")) {
			result = false;
		} else {
			try {
				Integer.parseInt(str);
			} catch (NumberFormatException e) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * 转换string成为int值，转换失败返回null
	 * 
	 * @author wqtan
	 * @param str
	 * @return integer
	 */
	public  Integer parseInt(String str) {
		Integer result = null;

		if (null != str && !str.trim().equals("")) {
			try {
				str = str.trim();
				result = Integer.parseInt(str);
			} catch (NumberFormatException e) {
			}
		}

		return result;
	}

	/**
	 * 转换string成为Long值，转换失败返回null
	 * 
	 * @author wqtan
	 * @param str
	 * @return Long
	 */
	public  Long parseLong(String str) {
		Long result = null;

		if (null != str && !str.trim().equals("")) {
			try {
				str = str.trim();
				result = Long.parseLong(str);
			} catch (NumberFormatException e) {
			}
		}

		return result;
	}

	/**
	 * 转换string成为Short值，转换失败返回null
	 * 
	 * @author wqtan
	 * @param str
	 * @return Short
	 */
	public  Short parseShort(String str) {
		Short result = null;

		if (null != str && !str.trim().equals("")) {
			try {
				str = str.trim();
				result = Short.parseShort(str);
			} catch (NumberFormatException e) {
			}
		}

		return result;
	}

	/**
	 * 转换string成为Date值，转换失败返回null
	 * 
	 * @author wqtan
	 * @param str
	 * @return Date
	 */
	public  Date parseDate(String str, String format) {
		Date result = null;
		if (null != str && !str.trim().equals("") && null != format
				&& !format.trim().equals("")) {
			try {
				result = new SimpleDateFormat(format).parse(str);
			} catch (ParseException e) {
				result = null;
			}
		}
		return result;
	}

	/**
	 * 转换string成为Date值，转换失败返回null
	 * 
	 * @author wqtan
	 * @param str
	 * @return Date
	 */
	public  Date parseDate(String str, DateFormat format) {
		Date result = null;
		if (null != str && !str.trim().equals("") && null != format) {
			try {
				result = format.parse(str);
			} catch (ParseException e) {
				result = null;
			}
		}
		return result;
	}

	/**
	 * 转换string成为Character值，转换失败返回null
	 * 
	 * @author wqtan
	 * @param str
	 * @return Character
	 */
	public  Character parseChar(String str) {
		Character result = null;

		if (null != str && !str.trim().equals("")) {
			result = str.charAt(0);
		}

		return result;
	}

	/**
	 * 转换string成为Float值，转换失败返回null
	 * 
	 * @author wqtan
	 * @param str
	 * @return Float
	 */
	public  Float parseFloat(String str) {
		Float result = null;

		if (null != str && !str.trim().equals("")) {
			try {
				str = str.trim();
				result = Float.parseFloat(str);
			} catch (NumberFormatException e) {
			}
		}

		return result;
	}

	/**
	 * 转换string成为Double值，转换失败返回null
	 * 
	 * @author wqtan
	 * @param str
	 * @return Double
	 */
	public  Double parseDouble(String str) {
		Double result = null;

		if (null != str && !str.trim().equals("")) {
			try {
				str = str.trim();
				result = Double.parseDouble(str);
			} catch (NumberFormatException e) {
			}
		}

		return result;
	}

	/**
	 * 转换string成为Boolean值，转换失败返回null
	 * 
	 * @author wqtan
	 * @param str
	 * @return Double
	 */
	public  Boolean parseBoolean(String str) {
		Boolean result = null;

		if (null != str && !str.trim().equals("")) {
			try {
				str = str.trim();
				result = Boolean.parseBoolean(str);
			} catch (NumberFormatException e) {
			}
		}

		return result;
	}

	/**
	 * xxh 将请示参数列表转换成List
	 * 
	 * @param en
	 *            Enumeration
	 * @return List
	 */
	@SuppressWarnings( { "unchecked" })
	public  List<String> enumToList(Enumeration en) {
		List<String> result = null;
		if (null != en) {
			result = new ArrayList<String>();

			while (en.hasMoreElements()) {
				Object obj = en.nextElement();
				if (null != obj && !obj.toString().trim().equals("")) {
					result.add(obj.toString());
				}
			}
		}
		return result;
	}

	/**
	 * 去掉所有html标签
	 * 
	 * @param input
	 * @return
	 */
	public  String removeHtmlTag(String html) {
		String result = html;
		if (null != result && !result.trim().equals("")) {
			result = html.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
					"<[^>]*>", "");
			result = result.replaceAll("[(/>)<]", "");
		}
		return result;
	}

	// //////////////////////////////////////////////////////temp

	// 返回指定长度的字符串
	public  String toStrOfNum(String str, int num, String s) {
		if (isEmpty(str))
			return getNumOfStr(num, s);
		int slen = str.length();
		if (num > slen)
			return getNumOfStr((num - slen), s) + str;
		else
			return str.substring(slen - num);

	}

	// 返回指定长度的字符串
	public  String getNumOfStr(int num, String s) {
		String str = "";
		for (int i = 0; i < num; i++)
			str = str + s;
		return str;
	}

	public  String toStrOfNum(int n, int num) {
		String str = String.valueOf(n);
		return toStrOfNum(str, num, "0");
	}

	/**
	 * 将单词首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public  String convertStartBig(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 将单词首字母小写
	 * 
	 * @param str
	 * @return
	 */
	public  String convertStartSmall(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	// 将以","号分隔的字符串转换为List
	public  List<Integer> stringToIntList(String s) {
		List<Integer> lst = new ArrayList<Integer>();
		if (s == null)
			return lst;
		String temp[] = s.split(",");
		for (int i = 0; i < temp.length; i++) {
			if (!"".equals(temp[i]))
				lst.add(Integer.parseInt(temp[i]));
		}
		return lst;
	}

	public  char chinese2Letter(char chinese) {
		GB2AlphaUtil util = new GB2AlphaUtil();
		return util.Char2Alpha(chinese);
	}

	public  String encode(String str) {
		String result = str;
		try {
			result = URLEncoder.encode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("stringUtil encode error : " + e.getMessage());
		} catch (NullPointerException e) {
		}

		return result;
	}

	public  String decode(String str) {
		String result = null == str ? "" : str;
		try {
			result = URLDecoder.decode(result, "utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("stringUtil decode error : " + e.getMessage());
		}
		return result;
	}

	public  String convertToChinese(String strInput) {
		String str = "";
		if (str == null || str.length() <= 0) {
			return str;
		}
		try {
			str = new String(strInput.getBytes("iso-8859-1"), "gb2312");
		} catch (Exception e) {
			str = strInput;
		}
		return str;
	}

	/**
	 * New Add
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public  List<String> parseToList(String str, char split) {
		List<String> result = new LinkedList<String>();
		if (null != str && !str.trim().equals("")) {
			String[] temp = str.split(split + "");
			for (int i = 0; i < temp.length; i++) {
				String s = temp[i];
				s = getNumbers(s);
				if (null != s && !s.trim().equals("")) {
					result.add(s);
				}
			}
		}
		return result;
	}

	// 截取数字
	private  String getNumbers(String content) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}
	
	
	public Object[] wipeRepeatList(List list){
		Object[] result = null;
		HashSet set=new HashSet();  
		for(Object e:list){  
			set.add(e);
		}
		result = set.toArray();
		return result;
	}

	// //////////////////////////////////////////////////////temp

	/*------------------------------------------------------------*/
	public static void main(String[] args) {
		// System.out.println(split("[a][f][b][c]", "[]"));
		// System.out.println(split("a,f,b,c", ","));

		// System.out.println(getPathByTableName("cms_paGE_config_template",
		// true));

		// System.out.println(parseAlifUpperCase("abc_dd_bb"));

		// System.out.println(parseInt("f"));
		System.out.println(UUID.randomUUID().toString());
	}
}
