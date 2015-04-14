/*
 * Created on 2005-3-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.up72.util;

import static com.up72.common.CommonUtils.dateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.up72.exception.UtilException;

/**
 * @author sunan
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class DateUtils {

	 Calendar cld;

	public  final long YEAR = 10000000000l;

	public  final long MONTH = 100000000;

	public  final long DAY = 1000000;

	public  final long HOUR = 10000;

	public  final long MINUTE = 100;

	public  long newDate() {
		long date = 0;
		return date;
	}

	public  long setYear(long date, long year) {
		date = year * YEAR + date % YEAR;
		return date;
	}

	public  long setYear(long date, String year) {
		long thisYear = 0;
		try {
			thisYear = Long.parseLong(year);
		} catch (Exception e) {
			return date;
		}

		return setYear(date, thisYear);
	}

	public  long getYear(long date) {

		return date / YEAR;
	}

	public  long setMonth(long date, long month) {
		long year = getYear(date);
		date = date - year * YEAR;
		date = month * MONTH + date % MONTH;

		return year * YEAR + date;
	}

	public  long setMonth(long date, String month) {
		long thisMonth = 0;
		try {
			thisMonth = Long.parseLong(month);
		} catch (Exception e) {
			return date;
		}

		return setMonth(date, thisMonth);
	}

	public  long getMonth(long date) {
		long year = getYear(date);
		date = date - year * YEAR;

		return date / MONTH;
	}

	public  long setDay(long date, long day) {
		long year = getYear(date);
		long month = getMonth(date);
		date = (date - year * YEAR) - month * MONTH;
		date = day * DAY + date % DAY;

		return year * YEAR + month * MONTH + date;
	}

	public  long setDay(long date, String day) {
		long thisDay = 0;
		try {
			thisDay = Long.parseLong(day);
		} catch (Exception e) {
			return date;
		}

		return setDay(date, thisDay);
	}

	public  long getDay(long date) {
		long year = getYear(date);
		long month = getMonth(date);
		date = date - year * YEAR - month * MONTH;

		return date / DAY;
	}

	public  long setHour(long date, long hour) {
		long thisDate = 0;
		long year = getYear(date);
		long month = getMonth(date);
		long day = getDay(date);

		thisDate = date % HOUR;
		thisDate = year * YEAR + month * MONTH + day * DAY + hour * HOUR
				+ thisDate;

		return thisDate;
	}

	public  long setHour(long date, String hour) {
		long thisHour = 0;
		try {
			thisHour = Long.parseLong(hour);
		} catch (Exception e) {
			return date;
		}

		return setHour(date, thisHour);

	}

	public  long getHour(long date) {
		long hour = 0;
		hour = date % DAY;
		hour = hour / HOUR;
		return hour;
	}
	
	public  String getHour() {
		Calendar c = Calendar.getInstance();
		return towStr(c.get(Calendar.HOUR_OF_DAY));
	}

	public  long setMinute(long date, long minute) {
		long thisDate = 0;
		long year = getYear(date);
		long month = getMonth(date);
		long day = getDay(date);
		long hour = getHour(date);

		thisDate = date % MINUTE;
		thisDate = year * YEAR + month * MONTH + day * DAY + hour * HOUR
				+ minute * MINUTE + thisDate;

		return thisDate;
	}

	public  long setMinute(long date, String minute) {
		long thisMinute = 0;
		try {
			thisMinute = Long.parseLong(minute);
		} catch (Exception e) {
			return date;
		}

		return setMinute(date, thisMinute);
	}

	public  long getMinute(long date) {
		long minute = 0;

		minute = date % HOUR;
		minute = minute / MINUTE;

		return minute;
	}

	public  long setSecond(long date, long second) {
		long thisDate = 0;
		long year = getYear(date);
		long month = getMonth(date);
		long day = getDay(date);
		long hour = getHour(date);
		long minute = getMinute(date);

		thisDate = year * YEAR + month * MONTH + day * DAY + hour * HOUR
				+ minute * MINUTE + second;

		return thisDate;
	}

	public  long setSecond(long date, String second) {
		long thisSecond = 0;
		try {
			thisSecond = Long.parseLong(second);
		} catch (Exception e) {
			return date;
		}

		return setSecond(date, thisSecond);
	}

	public  long getSecond(long date) {
		long second = 0;
		second = date % MINUTE;

		return second;
	}

	public  long getNowDate() {
		long date = 0;
		Date nowDate = new Date();
		DateFormat df = DateFormat.getDateInstance();
		String sDate = df.format(nowDate);
		int start = 0;
		int end = 0;
		String year = null;
		String month = null;
		String day = null;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			year = sDate.substring(start, end);
		}
		start = end + 1;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			month = sDate.substring(start, end);
		}
		start = end + 1;
		day = sDate.substring(start);
		/**
		 * debug start
		 */
		// System.out.println("year: " + year + "/month: " + month + "/day: " +
		// day);
		/**
		 * debug end
		 */
		date = setYear(date, year);
		date = setMonth(date, Long.parseLong(month));
		date = setDay(date, day);

		return date;
	}

	public  long getNowDateTime() {
		long date = 0;

		Date nowDate = new Date();
		DateFormat df = DateFormat.getDateTimeInstance();
		String sDate = df.format(nowDate);
		int start = 0;
		int end = 0;
		String year = null;
		String month = null;
		String day = null;
		String hour = null;
		String minute = null;
		String second = null;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			year = sDate.substring(start, end);
		}
		start = end + 1;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			month = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(" ", start);
		if (end > 0) {
			day = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(":", start);
		if (end > 0) {
			hour = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(":", start);
		if (end > 0) {
			minute = sDate.substring(start, end);
		}

		start = end + 1;
		second = sDate.substring(start);

		/**
		 * debug start
		 */
		// System.out.println("year: " + year + "/month: " + month + "/day: " +
		// day + "/hour: " + hour + "/minute: " + minute + "/second: " + second
		// + "\n" + sDate);
		/**
		 * debug end
		 */

		date = setYear(date, year);
		date = setMonth(date, month);
		date = setDay(date, day);
		date = setHour(date, hour);
		date = setMinute(date, minute);
		date = setSecond(date, second);
		return date;
	}

	public  String getDate(long date, String separator) {
		String returnDate = null;
		String day = null;
		String month = null;
		String year = null;
		year = Long.toString(getYear(date));
		if (getMonth(date) < 10) {
			month = "0" + Long.toString(getMonth(date));
		} else {
			month = Long.toString(getMonth(date));
		}

		if (getDay(date) < 10) {
			day = "0" + Long.toString(getDay(date));
		} else {
			day = Long.toString(getDay(date));
		}
		returnDate = year + separator + month + separator + day;

		return returnDate;
	}

	public  String getDate(long date) {
		String returnDate = "";
		String day = null;
		String month = null;
		String year = null;
		year = Long.toString(getYear(date));
		if (getMonth(date) < 10) {
			month = "0" + Long.toString(getMonth(date));
		} else {
			month = Long.toString(getMonth(date));
		}

		if (getDay(date) < 10) {
			day = "0" + Long.toString(getDay(date));
		} else {
			day = Long.toString(getDay(date));
		}
		returnDate = year + "-" + month + "-" + day;

		return returnDate;
	}

	public  String getDateCn(long date) {
		String returnDate = "";
		String day = null;
		String month = null;
		String year = null;
		String hour = null;
		String minute = null;
		String second = null;

		year = Long.toString(getYear(date));
		if (getMonth(date) < 10) {
			month = "0" + Long.toString(getMonth(date));
		} else {
			month = Long.toString(getMonth(date));
		}

		if (getDay(date) < 10) {
			day = "0" + Long.toString(getDay(date));
		} else {
			day = Long.toString(getDay(date));
		}

		if (getHour(date) < 10) {
			hour = "0" + Long.toString(getHour(date));
		} else {
			hour = Long.toString(getHour(date));
		}

		if (getMinute(date) < 10) {
			minute = "0" + Long.toString(getMinute(date));
		} else {
			minute = Long.toString(getMinute(date));
		}

		if (getSecond(date) < 10) {
			second = "0" + Long.toString(getSecond(date));
		} else {
			second = Long.toString(getSecond(date));
		}

		returnDate = year + "年" + month + "月" + day + "日" + hour + "点" + minute
				+ "分" + second + "秒";

		return returnDate;

	}

	public  long getDateTime(Date someDate) {
		long date = 0;

		Date nowDate = someDate;
		DateFormat df = DateFormat.getDateTimeInstance();
		String sDate = df.format(nowDate);
		int start = 0;
		int end = 0;
		String year = null;
		String month = null;
		String day = null;
		String hour = null;
		String minute = null;
		String second = null;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			year = sDate.substring(start, end);
		}
		start = end + 1;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			month = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(" ", start);
		if (end > 0) {
			day = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(":", start);
		if (end > 0) {
			hour = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(":", start);
		if (end > 0) {
			minute = sDate.substring(start, end);
		}

		start = end + 1;
		second = sDate.substring(start);

		/**
		 * debug start
		 */
		// System.out.println("year: " + year + "/month: " + month + "/day: " +
		// day + "/hour: " + hour + "/minute: " + minute + "/second: " + second
		// + "\n" + sDate);
		/**
		 * debug end
		 */

		date = setYear(date, year);
		date = setMonth(date, month);
		date = setDay(date, day);
		date = setHour(date, hour);
		date = setMinute(date, minute);
		date = setSecond(date, second);
		return date;
	}

	// 获取当前日期
	public  String getStrDate() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR) + "-" + towStr((c.get(Calendar.MONTH) + 1))
				+ "-" + towStr(c.get(Calendar.DATE));
	}

	public  String getStrDateTime() {
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dd.format(new Date());
	}

	public  String format(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR) + "-" + towStr(c.get(Calendar.MONTH) + 1)
				+ "-" + towStr(c.get(Calendar.DATE));

	}

	public  String format(long date) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date(date));
		return c.get(Calendar.YEAR) + "-" + towStr(c.get(Calendar.MONTH) + 1)
				+ "-" + towStr(c.get(Calendar.DATE));

	}

	public static String formatLongDate(long date) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(date));
		return c.get(Calendar.YEAR) + "-" + towStr(c.get(Calendar.MONTH) + 1)
				+ "-" + towStr(c.get(Calendar.DATE)) + " " + towStr(c.get(Calendar.HOUR))
				+ ":" + towStr(c.get(Calendar.MINUTE))+ ":" + towStr(c.get(Calendar.SECOND));

	}
	public  long getDateTime(String someDate) {
		long date = 0;

		String format = "yyyy-MM-dd";
		if (someDate.length() > 10) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat dd = new SimpleDateFormat(format);
		Date nowDate = new Date();
		try {
			nowDate = dd.parse(someDate);
		} catch (ParseException e) {
			throw new UtilException(e);
		}

		// Date nowDate = someDate;
		DateFormat df = DateFormat.getDateTimeInstance();
		String sDate = df.format(nowDate);
		int start = 0;
		int end = 0;
		String year = null;
		String month = null;
		String day = null;
		String hour = null;
		String minute = null;
		String second = null;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			year = sDate.substring(start, end);
		}
		start = end + 1;
		end = sDate.indexOf("-", start);
		if (end > 0) {
			month = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(" ", start);
		if (end > 0) {
			day = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(":", start);
		if (end > 0) {
			hour = sDate.substring(start, end);
		}

		start = end + 1;
		end = sDate.indexOf(":", start);
		if (end > 0) {
			minute = sDate.substring(start, end);
		}

		start = end + 1;
		second = sDate.substring(start);

		/**
		 * debug start
		 */
		// System.out.println("year: " + year + "/month: " + month + "/day: " +
		// day + "/hour: " + hour + "/minute: " + minute + "/second: " + second
		// + "\n" + sDate);
		/**
		 * debug end
		 */

		date = setYear(date, year);
		date = setMonth(date, month);
		date = setDay(date, day);
		date = setHour(date, hour);
		date = setMinute(date, minute);
		date = setSecond(date, second);
		return date;
	}

	public static long stringToLong(String someDate) {
		String format = "yyyy-MM-dd";
		if (someDate.length() > 10) {
			format = "yyyy-MM-dd HH:mm";
		}
		if (someDate.length() > 16) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat dd = new SimpleDateFormat(format);
		try {
			return dd.parse(someDate).getTime();
		} catch (Exception e) {
			return new Date().getTime();
		}
	}
	
	private int[] timeArray = {60,60,1000,};
	/**
	 * 时分秒转换成时间戳
	 * @author liutongling
	 * @param time 00:00:00
	 * @return long
	 */
	public long timeToLong(String time){
		long result = 0L;
		if(null!=time){
			if(time.matches("[\\d]?\\d:[\\d]?\\d")){
				time +=":00";
			}
			if(time.matches("[\\d]?\\d:[\\d]?\\d:[\\d]?\\d")){
				String[] tArry = time.split(":");
				for (int i = 0; i < tArry.length; i++) {
					String str = tArry[i];
					long t = Long.parseLong(str);
					for (int j = i; j < timeArray.length; j++) {
						t = t*timeArray[j];
					}
					result += t;
				}
			} 
		}
		return result;
	}
	/**
	 * 时间戳转换成时分秒
	 * @author liutongling
	 * @param long time
	 * @return 00:00:00
	 */
	public String longToTime(long time){
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < timeArray.length; i++) {
			long temp = 1;
			for (int j = i; j < timeArray.length; j++) {
				temp = temp * timeArray[j];
			}
			long t = time/temp;
			if(i!=0){
				result.append(":");
			}
			if(t < 10){
				result.append("0").append(t);
			}else{
				result.append(t);
			}
			time = time % temp;
		}
		return result.toString();
	}
	
	/**
	 * 获得给定时间的开始
	 * @param time
	 * @param dayNum 平移天数
	 * @return
	 */
	public static long getDayStart(long time,int dayNum){
		Date date = new Date(stringToLong(formatLongDate(time)));
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		date.setDate(date.getDate()+dayNum);
		return date.getTime();
	}
	
	/**
	 * 获得当前时分秒时间戳
	 * @author liutongling
	 * @return
	 */
	public static long getDayTimeToLong(){
		long time = new Date().getTime();
		time = time - getDayStart(time,0);
		return time;
	}
	

	public  Date longToDate(Long param) {
		if (cld == null) {
			cld = new GregorianCalendar();
		}
		if (param != null) {
			cld.clear();
			cld.setTimeInMillis(param);
			Date d = cld.getTime();
			return d;
		} else {
			return null;
		}
	}

	
	public  long dateToLong(Date param) {
		long date = param.getTime();
		return date;
	}
	
	public  long dateToLong(int day) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		return date.getTime();
	}

	/**
	 * Parse a datetime string.
	 * 
	 * @param param
	 *            datetime string, pattern: "yyyy-MM-dd HH:mm:ss".
	 * @return java.util.Date
	 */
	public  Date parse(String param) {
		Date date = null;
		if (param == null) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = sdf.parse(param);
			} catch (ParseException ex) {
			}
			return date;
		}
	}

	/**
	 * 获取一周中的第一天
	 */
	public  String getFirstDateOfWeek() {
		Calendar c = Calendar.getInstance();
		int dd = c.get(GregorianCalendar.DAY_OF_WEEK);
		if (dd == 1)
			dd = 7;
		else if (dd > 1)
			dd = dd - 1;
		c.add(Calendar.DATE, 1 - dd);
		String date = c.get(Calendar.YEAR) + "-"
				+ towStr(1 + c.get(Calendar.MONTH)) + "-"
				+ towStr(c.get(Calendar.DATE));
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// try {
		// return sdf.parse(date);
		// } catch (ParseException e) {
		// return new Date();
		// }
		return date;
	}

	/**
	 * 获取一周中的第一天
	 */
	public  String getLastDateOfMonth() {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.DATE, 1);
		c.roll(Calendar.DATE, -1);
		String date = c.get(Calendar.YEAR) + "-"
				+ towStr(1 + c.get(Calendar.MONTH)) + "-"
				+ towStr(c.get(Calendar.DATE));
		// SimpleDateFormat simpleFormate = new SimpleDateFormat( " yyyy-MM-dd "
		// );
		// System.out.println(simpleFormate.format(calendar.getTime()));
		return date;
	}

	/**
	 * 获取当月中的第一天
	 */
	public  String getFirstDateOfCurrentMonth() {
		Calendar c = Calendar.getInstance();

		String date = c.get(Calendar.YEAR) + "-"
				+ towStr(1 + c.get(Calendar.MONTH)) + "-01";

		return date;
	}

	/**
	 * 获取一周中的第一天
	 */
	public  String getFirstDateOfWeek(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int dd = c.get(GregorianCalendar.DAY_OF_WEEK);
		if (dd == 1)
			dd = 7;
		else if (dd > 1)
			dd = dd - 1;
		c.add(Calendar.DATE, 1 - dd);
		String date = c.get(Calendar.YEAR) + "-"
				+ towStr(1 + c.get(Calendar.MONTH)) + "-"
				+ towStr(c.get(Calendar.DATE));
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// try {
		// return sdf.parse(date);
		// } catch (ParseException e) {
		// return new Date();
		// }
		return date;
	}

	public  String getDateOfWeek(String week) {
		if (week == null)
			return "";

		Calendar c = Calendar.getInstance();
		int dd = c.get(GregorianCalendar.DAY_OF_WEEK);

		if (week.equals("星期一")) {
			c.add(Calendar.DATE, 2 - dd);
		} else if (week.equals("星期二")) {
			c.add(Calendar.DATE, 3 - dd);
		} else if (week.equals("星期三")) {
			c.add(Calendar.DATE, 4 - dd);
		} else if (week.equals("星期四")) {
			c.add(Calendar.DATE, 5 - dd);
		} else if (week.equals("星期五")) {
			c.add(Calendar.DATE, 6 - dd);
		} else if (week.equals("星期六")) {
			c.add(Calendar.DATE, 7 - dd);
		} else if (week.equals("星期日")) {
			c.add(Calendar.DATE, 8 - dd);
		}

		String date = c.get(Calendar.YEAR) + "-"
				+ towStr(1 + c.get(Calendar.MONTH)) + "-"
				+ towStr(c.get(Calendar.DATE));
		return date;
	}

	/**
	 * 获取一周中的最后一天
	 */
	public  String getLastDateOfWeek() {
		Calendar c = Calendar.getInstance();
		int dd = c.get(GregorianCalendar.DAY_OF_WEEK);
		if (dd == 1)
			dd = 7;
		else if (dd > 1)
			dd = dd - 1;
		c.add(Calendar.DATE, 7 - dd);
		String date = c.get(Calendar.YEAR) + "-"
				+ towStr(1 + c.get(Calendar.MONTH)) + "-"
				+ towStr(c.get(Calendar.DATE));
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// try {
		// return sdf.parse(date);
		// } catch (ParseException e) {
		// return new Date();
		// }
		return date;
	}

	/**
	 * 获取一周中的最后一天
	 */
	public  String getLastDateOfWeek(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int dd = c.get(GregorianCalendar.DAY_OF_WEEK);
		if (dd == 1)
			dd = 7;
		else if (dd > 1)
			dd = dd - 1;
		c.add(Calendar.DATE, 7 - dd);
		String date = c.get(Calendar.YEAR) + "-"
				+ towStr(1 + c.get(Calendar.MONTH)) + "-"
				+ towStr(c.get(Calendar.DATE));
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// try {
		// return sdf.parse(date);
		// } catch (ParseException e) {
		// return new Date();
		// }
		return date;
	}
	
	/**
	 * 获取某个时间段中的日期列表
	 */
	public  List<String> getDatesBetween(String beginDate, String endDate) {
		List<String> dates = new ArrayList<String>();
		int num = diffDate(stringToLong(beginDate), stringToLong(endDate));
		for(int i = 0; i < num; i++)
		{
			dates.add(diffDate(beginDate, i));
		}
		
		return dates;
	}

	public static String towStr(int n) {
		if (n < 10)
			return "0" + n;
		else
			return "" + n;
	}

	/**
	 * 将未指定格式的日期字符串转化成java.util.Date类型日期对象 <br>
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public  Date parseStringToDate(String date) throws ParseException {
		Date result = null;
		String parse = date;
		parse = parse.replaceFirst("^[0-9]{4}([^0-9]?)", "yyyy$1");
		parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
		parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");

		DateFormat format = new SimpleDateFormat(parse);

		result = format.parse(date);

		return result;
	}

	/**
	 * 将未指定格式的日期字符串转化成java.util.Date类型日期对象 <br>
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public  int getWeeksBetwinDate(Date beginDate) {
		Calendar now = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		b.setTime(beginDate);
		int beginWeek = b.get(Calendar.WEEK_OF_YEAR);
		int nowWeek = now.get(Calendar.WEEK_OF_YEAR);

		int beginYear = b.get(Calendar.YEAR);
		String lastDayOfWeek = getLastDateOfWeek();
		Date lastDate = new Date(stringToLong(lastDayOfWeek));

		Calendar last = Calendar.getInstance();
		last.setTime(lastDate);
		int nowYear = last.get(Calendar.YEAR);

		// System.out.println((nowYear-beginYear) * 52 + nowWeek-beginWeek + 1);
		return ((nowYear - beginYear) * 52 + nowWeek - beginWeek + 1);
	}

	/**
	 * 将未指定格式的日期字符串转化成java.util.Date类型日期对象 <br>
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public  int getWeeksBetwinDate(Date beginDate, String date) {
		Date nowDate = new Date(stringToLong(date));

		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);

		Calendar b = Calendar.getInstance();
		b.setTime(beginDate);
		int beginWeek = b.get(Calendar.WEEK_OF_YEAR);
		int nowWeek = now.get(Calendar.WEEK_OF_YEAR);

		int beginYear = b.get(Calendar.YEAR);
		String lastDayOfWeek = getLastDateOfWeek(nowDate);
		Date lastDate = new Date(stringToLong(lastDayOfWeek));

		Calendar last = Calendar.getInstance();
		last.setTime(lastDate);
		int nowYear = last.get(Calendar.YEAR);

		// System.out.println((nowYear-beginYear) * 52 + nowWeek-beginWeek + 1);
		return ((nowYear - beginYear) * 52 + nowWeek - beginWeek + 1);
	}

	/**
	 * 两日期相减
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public  int diffDate(Date beginDate, Date endDate) {
		return diffDate(beginDate.getTime(), endDate.getTime());
	}

	/**
	 * 两日期相减
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public  int diffDate(long beginDate, long endDate) {
		long diff = endDate - beginDate;

		long mint = (diff) / (1000);
		int hor = (int) mint / 3600;
//		int secd = (int) mint % 3600;
		int day = (int) hor / 24;

		// System.out.println(day+1);
		return (day + 1);
	}

	/**
	 * 两日期相减
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public  String diffDate(String beginDate, int num) {
		Date d = new Date();

		d = new Date(stringToLong(beginDate));

		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_YEAR, num);
//		c.roll(Calendar.DAY_OF_YEAR, true);
		// return getDate(c.getTime().getTime());
		return c.get(Calendar.YEAR) + "-" + towStr(c.get(Calendar.MONTH) + 1) + "-"
				+ towStr(c.get(Calendar.DATE));

		// System.out.println(day+1);
	}

	/**
	 * 两日期相减
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public  String diffDate(Date date, int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		// System.out.println(c.get(Calendar.YEAR));
		// System.out.println(c.get(Calendar.MONTH));
		// System.out.println(c.get(Calendar.DAY_OF_MONTH));
		c.add(Calendar.DAY_OF_MONTH, (0 - num));
		// c.roll(Calendar.DAY_OF_MONTH, false);
		// System.out.println(c.get(Calendar.YEAR));
		// System.out.println(c.get(Calendar.MONTH));
		// System.out.println(c.get(Calendar.DAY_OF_MONTH));

		String strDate = c.get(Calendar.YEAR) + "-"
				+ towStr(c.get(Calendar.MONTH) + 1) + "-"
				+ towStr(c.get(Calendar.DATE));

		return strDate;

		// System.out.println(day+1);
	}

	/**
	 * 返回指定日期是星期几
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public  int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 返回某月的开始与结束日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public  Long[] getBeginAndEndDateOfMonth(String date) {
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(date);
		// return calendar.get(calendar.DAY_OF_WEEK)-1;

		String d[] = date.split("-");
		int year = Integer.parseInt(d[0]);
		int month = Integer.parseInt(d[1]);
		// Calendar bd = Calendar.getInstance();
		// c.set(c.YEAR, year);
		// c.set(c.MONTH, month-1);
		// c.set(c.DATE, 15);

		Long[] ld = new Long[2];
		// ld[0] = c.get(c.DAY_OF_MONTH);
		Calendar bd = Calendar.getInstance();
		bd.set(Calendar.YEAR, year);
		bd.set(Calendar.MONTH, month - 1);
		bd.set(Calendar.DAY_OF_MONTH, 1);
		String bds = bd.get(Calendar.YEAR) + "-" + towStr(bd.get(Calendar.MONTH) + 1) + "-"
				+ towStr(bd.get(Calendar.DATE));
		System.out.println(bds);

		Calendar ed = Calendar.getInstance();
		ed.set(Calendar.YEAR, year);
		ed.set(Calendar.MONTH, month);
		ed.set(Calendar.DAY_OF_MONTH, 1);
		ed.add(Calendar.DATE, -1);
		String eds = ed.get(Calendar.YEAR) + "-" + towStr(ed.get(Calendar.MONTH) + 1) + "-"
				+ towStr(ed.get(Calendar.DATE));
		System.out.println(eds);

		ld[0] = stringToLong(bds);
		ld[1] = stringToLong(eds);

		return ld;

	}

	// 根据当前选择时间获得规定上传时间
	public  String getUploadDateOfWeek(String uploadValue, Date date) {
		// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// Date date = format.parse(weekDate);
		// Calendar c= Calendar.getInstance();
		// c.setTime(date);
		// c.add(Calendar.DATE, 1);
		// String d = c.get(Calendar.YEAR) + "-" + (1+c.get(Calendar.MONTH)) +
		// "-" + (c.get(Calendar.DATE));
		// System.out.println(d);

		if (uploadValue == null)
			return "";

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (uploadValue.equals("星期一")) {
			c.add(Calendar.DATE, 0);
		} else if (uploadValue.equals("星期二")) {
			c.add(Calendar.DATE, 1);
		} else if (uploadValue.equals("星期三")) {
			c.add(Calendar.DATE, 2);
		} else if (uploadValue.equals("星期四")) {
			c.add(Calendar.DATE, 3);
		} else if (uploadValue.equals("星期五")) {
			c.add(Calendar.DATE, 4);
		} else if (uploadValue.equals("星期六")) {
			c.add(Calendar.DATE, 5);
		} else if (uploadValue.equals("星期日")) {
			c.add(Calendar.DATE, 6);
		}
		String d = c.get(Calendar.YEAR) + "-" + (1 + c.get(Calendar.MONTH))
				+ "-" + (c.get(Calendar.DATE));
		return d;
	}

	public  Date parseStr(String param) {
		Date date = null;
		if (param == null) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = sdf.parse(param);
			} catch (ParseException ex) {
			}
			return date;
		}
	}

	public  String DateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public  String addDate(Date date, int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		// System.out.println(c.get(Calendar.YEAR));
		// System.out.println(c.get(Calendar.MONTH));
		// System.out.println(c.get(Calendar.DAY_OF_MONTH));
		c.add(Calendar.DAY_OF_MONTH, num);
		// c.roll(Calendar.DAY_OF_MONTH, false);
		// System.out.println(c.get(Calendar.YEAR));
		// System.out.println(c.get(Calendar.MONTH));
		// System.out.println(c.get(Calendar.DAY_OF_MONTH));

		String strDate = c.get(Calendar.YEAR) + "-"
				+ towStr(c.get(Calendar.MONTH) + 1) + "-"
				+ towStr(c.get(Calendar.DATE));

		return strDate;

		// System.out.println(day+1);
	}
	public  String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public Date parseToDate(String timeString,String formate){
		Date result = null;
		if(null==timeString || timeString.trim().equals("")){
			throw new UtilException("参数不能为空");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		try {
			result = sdf.parse(timeString);
		} catch (ParseException e) {
			throw new UtilException(e);
		}
		return result;
	}
	
	public Long parseToLong(String timeString,String formate){
		Long result = null;
		Date date = parseToDate(timeString, formate);
		if(null!=date){
			result = date.getTime();
		}
		return result;
	}
	
	public static String getDateTimeStr(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dt = sdf.format(new Date());
		return dt;
	}
	///
	/**
	 * 获得相对时间，24小时以内的，提示多久前，24以后的，提示具体时间
	 * @author wqtan
	 */
	public String getRelativeTime(long time){
		long relative = (new Date().getTime()-time);
		StringBuilder result = new StringBuilder("");
		if(relative < 24*60*60*1000L){//24小时内
			// 时
			if(relative / (60*1000) > 60){
				result.append(relative / (60*60*1000)+"小时");
			}
			// 分
			else if(relative / 1000 > 60){
				result.append(relative / (60*1000)+"分钟");
			}else{
				result.append(relative / 1000+"秒钟");
			}
		}else if(relative < 24*60*60*1000 * 30L){
			result.append(relative / (24*60*60*1000L)+"天");
		}else{
			result.append(dateUtils.dateToString(new Date(time)));
		}
		
		return result.toString();
	}
	
	public static void main(String[] args) throws ParseException {
		DateUtils dateUtils = new DateUtils();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dd = new SimpleDateFormat("HH:mm:ss");
		System.out.println(dd.format(c.getTime()));
//		System.out.println(dateUtils.getHour(d2.getTime()-d1.getTime()));
//		System.out.println(new Date().getTime());
//		System.out.println(c.getTimeInMillis());
		System.out.println(dateUtils.stringToLong(dd.format(c.getTime())));
	}
}
