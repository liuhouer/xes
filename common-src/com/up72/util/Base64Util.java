package com.up72.util;

import java.io.UnsupportedEncodingException;
public class Base64Util{
	 public String getBASE64(byte[] b) {
	  String s = null;
	  if (b != null) {
	   s = new sun.misc.BASE64Encoder().encode(b);
	  }
	  return s;
	 }
	 public static byte[] getFromBASE64(String s) {
	  byte[] b = null;
	  if (s != null) {
		  sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	   try {
	    b = decoder.decodeBuffer(s);
	    return b;
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	  }
	  return b;
	 }
	 public static void main(String[] args) throws UnsupportedEncodingException {
		 Base64Util bs = new Base64Util();
		 String s = "abcd";
	        System.out.println("加密前：" + s);
	        String x = bs.getBASE64(s.getBytes());
	        System.out.println("加密后：" + x);
	        String x1 = new String(getFromBASE64(x));
	        System.out.println("解密后：" + x1);
	 }
}
