package com.up72.base;

import org.apache.log4j.Logger;

import bsh.EvalError;
import bsh.Interpreter;

public class GenericManager {
	public static Logger logger = Logger.getLogger(sun.reflect.Reflection
			.getCallerClass(1));
	/**
	 * 解析字符串
	 * 
	 * @param str
	 *            如 *(1,"1sds",1.11)
	 * @return Object[]
	 */
	public Object[] parserStr(String str) {
		Object[] object = new Object[] {};
		if (str == null || str.equals("")) {
			return object;
		}

		Interpreter bsh = new Interpreter();
		try {
			str = str.substring(str.indexOf("(") + 1).replace(")", "");
			String temp[] = str.split(",");
			if (!temp[0].equals("")) {
				object = new Object[temp.length];
				for (int i = 0, j = temp.length; i < j; i++) {
					object[i] = bsh.eval(temp[i]);
				}
			}

		} catch (EvalError e) {
			e.getMessage();
		}
		return object;
	}

	


}
