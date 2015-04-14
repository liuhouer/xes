package com.up72.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	/**
	 * 获得汉语拼音的输出格式
	 * 
	 * @return
	 */
	protected HanyuPinyinOutputFormat getFormat() 
	{
		HanyuPinyinOutputFormat result = new HanyuPinyinOutputFormat();
		result.setVCharType(HanyuPinyinVCharType.WITH_V);
		result.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		result.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		return result;
	}

	/**
	 * 转换一个汉字字符成拼音的字符串
	 * 
	 * @param c
	 * @return
	 */
	public String paraseCharToPinyin(char c) 
	{
		String result = null;

		String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);

		try 
		{
			pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, getFormat());
		} catch (BadHanyuPinyinOutputFormatCombination e) 
		{
			pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
		}

		result = (null == pinyinArray || pinyinArray.length == 0) ? 
					Character.toString(c) : pinyinArray[0];
		
		return result;
	}

	/**
	 * 转换一个串成为汉语拼音
	 * 
	 * @param str
	 * @return
	 */
	public String paraseStringToPinyin(String str) {
		if(null==str||str.trim().length()==0)
		{
			return str;
		}
		
		StringBuffer result = new StringBuffer();
		
		for (int i = 0; i < str.length(); i++) 
		{
			result.append(paraseCharToPinyin(str.charAt(i)));
		}
		
		return result.toString();
	}
}
