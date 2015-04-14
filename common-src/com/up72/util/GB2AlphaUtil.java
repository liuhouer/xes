package com.up72.util;

/**
 * 获得汉语字符串拼音首字母
 */
public class GB2AlphaUtil {

	private final char[] alphatable =

	{

	'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',

	'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z'

	};


	private final int[] table = new int[] {

	45217, 45253, 45761, 46318, 46826,

	47010, 47297, 47614, 47614, 48119,

	49062, 49324, 49896, 50371, 50614,

	50622, 50906, 51387, 51446, 52218,

	52218, 52218, 52698, 52980, 53689, 54481, 55289 };

	public GB2AlphaUtil() {

	}
	
	/**
	 * 
	 * 主函数, 输入字符, 得到他的声母, 英文字母返回对应的大写字母 其他非简体汉字返回 '*'
	 * 
	 */

	public char Char2Alpha(char ch) {

		if (ch >= 'a' && ch <= 'z')

			return (char) (ch - 'a' + 'A');

		if (ch >= 'A' && ch <= 'Z')

			return ch;

		int gb = gbValue(ch);

		if (gb < table[0])

			return '*';

		for (int i = 0; i < 26; ++i) {

			if (match(i, gb)) {

				if (i >= 26)

					return '*';

				else

					return alphatable[i];

			}

		}

		return '*';

	}

	/**
	 * 
	 * 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串
	 * 
	 */

	public String String2Alpha(String str) {

		String Result = "";

		try {

			for (int i = 0; i < str.length(); i++) {

				Result += Char2Alpha(str.charAt(i));

			}

		} catch (Exception e) {

			Result = " ";

		}

		return Result;

	}

	private boolean match(int i, int gb) {

		if (gb < table[i])

			return false;

		int j = i + 1;

		// 字母Z使用了两个标签

		while (j < 26 && (table[j] == table[i]))

			++j;

		if (j == 26)

			return gb <= table[j];

		else

			return gb < table[j];

	}

	/**
	 * 
	 * 取出传入汉字的编码
	 * 
	 */

	private int gbValue(char ch) {

		String str = new String();

		str += ch;

		try {

			byte[] bytes = str.getBytes("GB2312");

			if (bytes.length < 2)

				return 0;

			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);

		} catch (Exception e) {

			return '*';

		}

	}

	/**
	 * 
	 * 测试输出
	 * 
	 */

	public static void main(String[] args) {

		GB2AlphaUtil gb2A = new GB2AlphaUtil();

	//	System.out.println("黑色头发的拼音首字母为" + gb2A.String2Alpha("黑色头发!"));// 输出
																		// HSTF*

		System.out.println(gb2A.String2Alpha("黑色头发df"));
	}

}