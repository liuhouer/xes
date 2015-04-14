package com.up72.util;

import java.util.ArrayList;

/**
 * 用于格式字符串
 * @author jhe
 *
 */
public class StringFormat {
	private String formatStr;
	@SuppressWarnings("unchecked")
	private ArrayList params = new ArrayList();

	public StringFormat() {
	}

	public StringFormat(String str) {
		this.formatStr = str;
	}

	public void setFormatStr(String formatStr) {
		this.formatStr = formatStr;
	}

	@SuppressWarnings("unchecked")
	public void add(String v) {
		this.params.add(v);
	}

	public void add(long v) {
		add(String.valueOf(v));
	}

	public void add(int v) {
		add(String.valueOf(v));
	}

	public void add(float v) {
		add(String.valueOf(v));
	}

	public void add(double v) {
		add(String.valueOf(v));
	}

	public void add(Object v) {
		add(String.valueOf(v));
	}

	@SuppressWarnings("unchecked")
	public static String[] splitEx(String str, String spilter) {
		if (str == null) {
			return null;
		}
		if ((spilter == null) || (spilter.equals("")) || (str.length() < spilter.length())) {
			String[] t = { str };
			return t;
		}
		ArrayList al = new ArrayList();
		char[] cs = str.toCharArray();
		char[] ss = spilter.toCharArray();
		int length = spilter.length();
		int lastIndex = 0;
		for (int i = 0; i <= str.length() - length;) {
			boolean notSuit = false;
			for (int j = 0; j < length; ++j) {
				if (cs[(i + j)] != ss[j]) {
					notSuit = true;
					break;
				}
			}
			if (!(notSuit)) {
				al.add(str.substring(lastIndex, i));
				i += length;
				lastIndex = i;
			} else {
				++i;
			}
		}
		if (lastIndex <= str.length()) {
			al.add(str.substring(lastIndex, str.length()));
		}
		String[] t = new String[al.size()];
		for (int i = 0; i < al.size(); ++i) {
			t[i] = ((String) al.get(i));
		}
		return t;
	}

	public String toString() {
		String[] arr = splitEx(this.formatStr, "?");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length - 1; ++i) {
			sb.append(arr[i]);
			sb.append(this.params.get(i));
		}
		sb.append(arr[(arr.length - 1)]);
		return sb.toString();
	}

	public String toString(String str) {
		String[] arr = splitEx(this.formatStr, str);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length - 1; ++i) {
			sb.append(arr[i]);
			sb.append(getParams(i));
		}
		sb.append(arr[(arr.length - 1)]);
		return sb.toString();
	}
	
	public String toString(String str, int j) {
		if(j == 0){
			j = 1;
		}
		String[] arr = splitEx(this.formatStr, str);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length - 1; ++i) {
			sb.append(arr[i]);
			if(i == (j - 1)){
				sb.append(getParams(i));
			}else{
				sb.append(str);
			}
			
		}
		sb.append(arr[(arr.length - 1)]);
		return sb.toString();
	}

	public String getParams(int i) {
		if (this.params.size() > i) {
			return this.params.get(i).toString();
		} else {
			return getParams(i - 1);
		}
	}

	/**
	 * 统计字符串字节码长度
	 * @param str
	 * @return
	 */
	public static int countBytesLength(String str) {
		return str.replaceAll("[^\\x00-\\xff]", "aa").length();
	}

	public static String replaceHtml(String str) {
		return str.replace("&apos;", "'").replace("&quot;", "\"");
	}

	public static String reomvea(String str) {
		return str.replaceAll("<[Aa](.+?)>|</[Aa]>", "");
	}

	public static void main(String[] args) {
		//格式字符串
		StringFormat sf = new StringFormat("c asd b ${} asd${}a ");
		sf.add(1);
		sf.add(2);
		sf.add(3);
		System.out.println(sf.toString("asd",3));

		//统计字符串
		//		String string = "s中国";
		//		System.out.println(string.replaceAll("[^\\x00-\\xff]","aa").length());
		//		 int count = 0;
		//		  String regex = "[\u4e00-\u9fa5]";
		//		  String str = "今天阳光明媚zh2345678{不是吗},是的.";
		//		  Pattern p = Pattern.compile(regex);
		//		  Matcher m = p.matcher(str);
		//		  System.out.print("提取出来的中文有：");
		//		  while (m.find()) {
		//		   count++;
		//		   System.out.print(m.group() + " ");
		//		  }
		//		  System.out.println();
		//		  System.out.println("汉字出现的频率：" + count);

		//	  String string = "<A title=北极光邓锋：移动互联网不能完全寄托3G href=\"http://media.ifeng.com/news/newmedia/web/201001/0105_4266_1498737.shtml\" target=_blank><STRONG><FONT color=#1e00ff><FONT target=\"_blank\">北极光邓锋：移动互联网不能完全寄托3G</FONT> </FONT></STRONG></A></P> <P><A title=\"工信部官员披露3G规划 未来三年拉动万亿需求\" href=\"http://media.ifeng.com/news/newmedia/web/200911/1103_4266_1417477.shtml\" target=_blank>";
		//	  System.out.println(string.replaceAll("<[aA](.+?)>|</[aA]>", ""));
	}
}
