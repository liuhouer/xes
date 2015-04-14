package com.up72.common.reflect;

import static com.up72.common.CommonUtils.stringUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean处理简单工具类
 * 
 * @author xxh
 * @link
 * 
 * @version $Revision: 1.00 $ $Date: 2009-08-12
 */

public class BeanUtil {

	/**
	 * xxh 将源对象的属性值copy到目标对象，只copy当前表单的元素
	 * 
	 * @param dest
	 *            目标对象
	 * @param orig
	 *            源对象
	 * @param paramNames
	 *            当前表单的元素集合
	 */
	public static void copyProperties(Object dest, Object orig,
			List<String> paramNames) {
		if (null != dest && null != orig
				&& dest.getClass().getName().equals(orig.getClass().getName())
				&& null != paramNames && paramNames.size() > 0) {
			List<String> fieldNames = getFieldNames(orig);
			for (int i = 0; i < paramNames.size(); i++) {
				// 属性名
				String attribute = paramNames.get(i);
				// 当前表单元素为对象属性名时copy属性值
				if (fieldNames.contains(attribute)) {
					// 源对象属性值
					Object value = getPropertyByMethod(orig, attribute);
					// 拷贝属性
					setProperty(dest, attribute, value);
				}
			}
		}
	}

	/**
	 * xxh 获取一个对象的所有属性名
	 * 
	 * @param obj
	 *            要获取的对象
	 * @return 对象属性名的集合
	 */
	@SuppressWarnings( { "unchecked" })
	public static List<String> getFieldNames(Object obj) {
		List<String> result = new ArrayList<String>();

		Class cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			result.add(fields[i].getName());
		}

		return result;
	}

	/**
	 * xxh 设置某个对象某个字段的值
	 * 
	 * @param obj
	 *            对象
	 * @param field
	 *            字段
	 * @param value
	 *            值
	 */
	public static void setProperty(Object obj, String field, Object value) {
		Field fld = null;
		try {
			fld = obj.getClass().getDeclaredField(field);
			fld.setAccessible(true);
			fld.set(obj, value);
		} catch (SecurityException e) {
		} catch (NoSuchFieldException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
	}

	/**
	 * xxh 获取某个对象某个字段的值
	 * 
	 * @param obj
	 *            对象
	 * @param field
	 *            字段
	 * @return Object
	 */
	public static Object getProperty(Object obj, String field) {
		Object result = null;
		try {
			Field fld = obj.getClass().getDeclaredField(field);
			fld.setAccessible(true);
			result = fld.get(obj);
		} catch (SecurityException e) {
		} catch (NoSuchFieldException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}

		return result;
	}

	/**
	 * xxh 根据给定的属性，调用对应的get方法获取值
	 * 
	 * @param obj
	 *            对象
	 * @param field
	 *            字段
	 * @return Object
	 */
	public static Object getPropertyByMethod(Object obj, String field) {
		String method = "get" + stringUtil.alifUpperCase(field);
		Object value = invoke(obj, method, new Class[] {}, new Object[] {});
		return value;
	}

	/**
	 * xxh 获取某个对象级联字段的值
	 * 
	 * @param obj
	 *            对象
	 * @param field
	 *            字段
	 * @return Object
	 */
	public static Object getForeignProperty(Object obj, String field) {
		Object result = null;
		if (null != obj && null != field && !field.trim().equals("")) {
			while (field.indexOf('.') != -1) {
				String attr = field.substring(0, field.indexOf('.'));
				field = field.substring(field.indexOf('.') + 1);
				if (null != obj) {
					obj = BeanUtil.getPropertyByMethod(obj, attr);
				} else {
					break;
				}
			}
			if (null != obj) {
				result = BeanUtil.getPropertyByMethod(obj, field);
			}
		}

		return result;
	}

	/**
	 * 得到某类的静态公共属性
	 * 
	 * @param className
	 *            类名
	 * @param fieldName
	 *            属性名
	 * @return 该属性对象
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked" })
	public static Object getStaticProperty(String className, String fieldName) {
		Object result = null;
		try {
			Class clazz = Class.forName(className);
			Field field = clazz.getField(fieldName);
			result = field.get(clazz);
		} catch (ClassNotFoundException e) {
		} catch (SecurityException e) {
		} catch (NoSuchFieldException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}

		return result;

	}

	/**
	 * xxh 将对象属性值设置为NULL
	 * 
	 * @param obj
	 *            对象
	 */
	public static void setNull(Object obj, String field) {
		setProperty(obj, field, null);
	}

	/**
	 * xxh 调用方法, 该方法含多个参数
	 * 
	 * @param obj
	 *            对象
	 * @param method
	 *            方法名
	 * @param paramClass
	 *            方法参数所属类
	 * @param params
	 *            参数值
	 */
	@SuppressWarnings( { "unchecked" })
	public static Object invoke(Object obj, String method, Class[] paramClass,
			Object[] params) {
		Object result = null;
		try {
			Method mtd = obj.getClass().getDeclaredMethod(method, paramClass);
			mtd.setAccessible(true);
			result = mtd.invoke(obj, params);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}

		return result;

	}

	/**
	 * xxh 调用方法, 该方法无参数
	 * 
	 * @param obj
	 *            对象
	 * @param method
	 *            方法名
	 */

	@SuppressWarnings( { "unchecked" })
	public static Object invoke(Object obj, String method) {
		Object result = null;
		Method mtd = null;

		try {
			mtd = obj.getClass().getDeclaredMethod(method, null);
			mtd.setAccessible(true);
			mtd.invoke(obj, null);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}

		return result;
	}

	/**
	 * xxh 调用方法, 该方法含多个参数
	 * 
	 * @param obj
	 *            对象
	 * @param method
	 *            方法名
	 * @param params
	 *            参数值
	 */
	public static Object invoke(Object obj, String method, Object[] params) {
		Object result = null;
		Class[] cls = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			cls[i] = params[i].getClass();
		}

		Method mtd = null;
		try {
			mtd = obj.getClass().getDeclaredMethod(method, cls);
			mtd.setAccessible(true);
			result = mtd.invoke(obj, params);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}

		return result;
	}

	/**
	 * xxh 调用方法, 该方法一个参数
	 * 
	 * @param obj
	 *            对象
	 * @param method
	 *            方法名
	 * @param params
	 *            参数值
	 */
	public static Object invoke(Object obj, String method, Object params) {
		Object result = null;

		Class cls = params.getClass();
		Method mtd = null;
		try {
			mtd = obj.getClass().getDeclaredMethod(method, cls);
			mtd.setAccessible(true);
			result = mtd.invoke(obj, params);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}

		return result;
	}

	/**
	 * xxh 调用方法, 该方法含一个参数
	 * 
	 * @param obj
	 *            对象
	 * @param method
	 *            方法名
	 * @param paramClass
	 *            方法参数所属类
	 * @param param
	 *            参数值
	 * @return 执行方法返回的结果
	 */
	@SuppressWarnings( { "unchecked" })
	public static Object invoke(Object obj, String method, Class paramClass,
			Object param) {
		Object result = null;
		Method mtd = null;
		try {
			mtd = obj.getClass().getDeclaredMethod(method, paramClass);
			mtd.setAccessible(true);
			result = mtd.invoke(obj, param);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}

		return result;
	}

	/**
	 * xxh 执行某类的静态方法, 该方法一个参数
	 * 
	 * @param className
	 *            类名
	 * @param methodName
	 *            方法名
	 * @param args
	 *            参数数组
	 * @return 执行方法返回的结果
	 */
	public static Object invokeStaticMethod(String className,
			String methodName, Object args) {
		Object result = null;
		Class ownerClass = null;
		Class argsClass = null;
		
		if(null != args){
			args.getClass();
		}
		
		Method method = null;
		try {
			ownerClass = Class.forName(className);
			method = ownerClass.getMethod(methodName, argsClass);
			result = method.invoke(null, args);
		} 
		catch (ClassNotFoundException e) {} 
		catch (SecurityException e) {} 
		catch (NoSuchMethodException e) {} 
		catch (IllegalArgumentException e) {} 
		catch (IllegalAccessException e) {} 
		catch (InvocationTargetException e) {}
		return result;
	}

	/**
	 * xxh 执行某类的静态方法, 该方法一个参数
	 * 
	 * @param className
	 *            类名
	 * @param methodName
	 *            方法名
	 * @param argsClass
	 *            参数所属类
	 * @param args
	 *            参数数组
	 * @return 执行方法返回的结果
	 */
	@SuppressWarnings ({"unchecked"})
	public static Object invokeStaticMethod(String className,
			String methodName, Class argsClass, Object args) {
		Object result = null;
		try{
			Class ownerClass = Class.forName(className);
			Method method = ownerClass.getMethod(methodName, argsClass);
			result = method.invoke(null, args);
		}
		catch (ClassNotFoundException e) {} 
		catch (SecurityException e) {} 
		catch (NoSuchMethodException e) {} 
		catch (IllegalArgumentException e) {} 
		catch (IllegalAccessException e) {} 
		catch (InvocationTargetException e) {}
		
		return result;
	}

	/**
	 * xxh 执行某类的静态方法, 该方法无参数
	 * 
	 * @param className
	 *            类名
	 * @param methodName
	 *            方法名
	 * @return 执行方法返回的结果
	 */
	public static Object invokeStaticMethod(String className, String methodName) {
		try {
			Class ownerClass = Class.forName(className);
			Method method = ownerClass.getMethod(methodName, null);
			return method.invoke(null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * xxh 执行某类的静态方法, 该方法多个参数
	 * 
	 * @param className
	 *            类名
	 * @param methodName
	 *            方法名
	 * @param args
	 *            参数数组
	 * @return 执行方法返回的结果
	 */
	public static Object invokeStaticMethod(String className,
			String methodName, Object[] args) {
		try {
			Class ownerClass = Class.forName(className);
			Class[] argsClass = new Class[args.length];
			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}
			Method method = ownerClass.getMethod(methodName, argsClass);
			return method.invoke(null, args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * xxh 执行某类的静态方法, 该方法多个参数
	 * 
	 * @param className
	 *            类名
	 * @param methodName
	 *            方法名
	 * @param argsClass
	 *            参数类型
	 * @param args
	 *            参数数组
	 * @return 执行方法返回的结果
	 */
	public static Object invokeStaticMethod(String className,
			String methodName, Class[] argsClass, Object[] args) {
		try {
			Class ownerClass = Class.forName(className);
			Method method = ownerClass.getMethod(methodName, argsClass);
			return method.invoke(null, args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * xxh 新建实例，构造参数含多个参数
	 * 
	 * @param className
	 *            类名
	 * @param args
	 *            构造函数的参数
	 * @return 新建的实例
	 */
	public static Object newInstance(String className, Object[] args) {
		try {
			Class newoneClass = Class.forName(className);
			Class[] argsClass = new Class[args.length];
			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}
			Constructor cons = newoneClass.getConstructor(argsClass);

			return cons.newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * xxh 新建实例，构造参数含多个参数
	 * 
	 * @param className
	 *            类名
	 * @param argsClass
	 *            构造函数的参数所属类
	 * @param args
	 *            构造函数的参数
	 * @return 新建的实例
	 */
	public static Object newInstance(String className, Class[] argsClass,
			Object[] args) {
		try {
			Class newoneClass = Class.forName(className);
			Constructor cons = newoneClass.getConstructor(argsClass);
			return cons.newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * xxh 新建实例，构造参数含一个参数
	 * 
	 * @param className
	 *            类名
	 * @param args
	 *            构造函数的参数
	 * @return 新建的实例
	 */
	public static Object newInstance(String className, Object args) {
		try {
			Class newoneClass = Class.forName(className);
			Class argsClass = args.getClass();
			Constructor cons = newoneClass.getConstructor(argsClass);
			return cons.newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * xxh 新建实例，构造参数含一个参数
	 * 
	 * @param className
	 *            类名
	 * @param argsClass
	 *            构造函数的参数所属类
	 * @param args
	 *            构造函数的参数
	 * @return 新建的实例
	 */
	public static Object newInstance(String className, Class argsClass,
			Object args) {
		try {
			Class newoneClass = Class.forName(className);
			Constructor cons = newoneClass.getConstructor(argsClass);
			return cons.newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * xxh 新建实例，构造参数无参数
	 * 
	 * @param className
	 *            类名
	 * @return 新建的实例
	 */
	public static Object newInstance(String className) {
		try {
			Class newClass = Class.forName(className);
			return newClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * xxh 新建实例，构造参数无参数
	 * 
	 * @param packageName
	 *            包名
	 * @param className
	 *            类名
	 * @return 新建的实例
	 */
	public static Object newInstance(String packageName, String className) {
		try {
			Class newClass = Class.forName(packageName + "." + className);
			return newClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * xxh 某对象是不是某个类的实例
	 * 
	 * @param obj
	 *            实例
	 * @param cls
	 *            类
	 * @return 如果 obj 是此类的实例，则返回 true
	 */
	public static boolean isInstance(Object obj, Class cls) {
		return cls.isInstance(obj);
	}

	/***************************************************************************
	 * xxh 获取指定List 属性列表对应的值
	 * 
	 * @param obj
	 *            对象
	 * @param fieldList
	 *            属性列表
	 * @return List
	 */
	public static List getProperties(Object obj, List<String> fieldList) {
		List values = new ArrayList();
		for (int i = 0; i < fieldList.size(); i++) {
			String fieldSeperator = fieldList.get(i);
			String fields[] = fieldSeperator.split("\\.");
			if (fields == null && fields.length == 0)
				values.add(null);
			else {
				if (fields.length == 1) // 简单属性
					values.add(BeanUtil.getPropertyByMethod(obj, fields[0]));
				else {
					// 复合属性
					int n = 0;
					Object temp = BeanUtil
							.getPropertyByMethod(obj, fields[n++]);
					while (n < fields.length && temp != null) {
						temp = BeanUtil.getPropertyByMethod(temp, fields[n++]);
					}
					values.add(temp);
				}
			}
		}
		return values;
	}

	/***************************************************************************
	 * xxh 获取指定List 属性列表对应的值
	 * 
	 * @param obj
	 *            对象
	 * @param fieldList
	 *            属性列表
	 * @param type
	 *            类型列表
	 * @return List
	 */
	public static List getProperties(Object obj, List<String> fieldList,
			List<String> type) {
		List values = new ArrayList();
		for (int i = 0; i < fieldList.size(); i++) {
			String fieldSeperator = fieldList.get(i);
			String fields[] = fieldSeperator.split("\\.");
			if (fields == null && fields.length == 0)
				values.add(null);
			else {
				if (fields.length == 1) // 简单属性
					values.add(BeanUtil.getPropertyByMethod(obj, fields[0]));
				else {
					// 复合属性
					int n = 0;
					Object temp = BeanUtil
							.getPropertyByMethod(obj, fields[n++]);
					while (n < fields.length && temp != null) {
						temp = BeanUtil.getPropertyByMethod(temp, fields[n++]);
					}
					temp = convert(temp, type.get(i));
					values.add(temp);
				}
			}
		}
		return values;
	}

	/***************************************************************************
	 * xxh 根据Model名创建一个Model的实例
	 * 
	 * @param className
	 *            Model名
	 * @return Object
	 */
	public static Object forName(String packageName, String className) {
		try {
			return Class.forName(packageName + "." + className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * xxh 根据给定的属性，调用对应的get方法获取值
	 * 
	 * @param obj
	 *            对象
	 * @param field
	 *            字段
	 * @return Object
	 */
	public static Method getPropertyGetMethod(Object obj, String field) {
		Method result = null;
		String method = "get" + stringUtil.alifUpperCase(field);
		try {
			result = obj.getClass().getDeclaredMethod(method, null);
		} catch (SecurityException e) {
			result = null;
		} catch (NoSuchMethodException e) {
			result = null;
		}
		return result;
	}
	
	/**
	 * xxh 设置某个对象某个字段的值
	 * 
	 * @param obj
	 *            对象
	 * @param field
	 *            字段
	 * @param value
	 *            值
	 */
	public static void setPropertyByMethod(Object obj, String field, Object value) {
		String methodName = "set"+stringUtil.alifUpperCase(field);
		try {
			if(null != value){
				Method met = obj.getClass().getDeclaredMethod(methodName, value.getClass());
				if(null != met){
					met.invoke(obj, value);
				}
			}
		} 
		catch (SecurityException e) {} 
		catch (NoSuchMethodException e) {} 
		catch (IllegalArgumentException e) {} 
		catch (IllegalAccessException e) {} 
		catch (InvocationTargetException e) {}
	}
	
	/**
	 * xxh 设置某个对象某个字段的值
	 * 
	 * @param obj
	 *            对象
	 * @param field
	 *            字段
	 * @param value
	 *            值
	 */
	public static void setPropertyByMethod(Object obj, String field, Object value,Class type) {
		String methodName = "set"+stringUtil.alifUpperCase(field);
		try {
			if(null != type){
				Method met = obj.getClass().getDeclaredMethod(methodName, type);
				if(null != met){
					met.invoke(obj, value);
				}
			}
		} 
		catch (SecurityException e) {} 
		catch (NoSuchMethodException e) {} 
		catch (IllegalArgumentException e) {} 
		catch (IllegalAccessException e) {} 
		catch (InvocationTargetException e) {}
	}

	/***************************************************************************
	 * xxh 数据类型转换处理
	 * 
	 * @param value
	 *            需转换的数据
	 * @param type
	 *            转换后的类型
	 * @return List
	 */
	public static String convert(Object value, String type) {
		return "" + value;
	}

}
