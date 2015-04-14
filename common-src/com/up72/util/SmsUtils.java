package com.up72.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.up72.common.CommonConstants;

public class SmsUtils {
//	private IExamSmsService examSmsService;
//	
//	public void setExamSmsService(IExamSmsService examSmsService) {
//		this.examSmsService = examSmsService;
//	}

	/**
	 * 发送短信
	 * 
	 * @param sendPhone
	 * @param content
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	private String sendMsg(String sendPhone, String content, String areaCode) throws Exception{
//		System.out.println("短信记录: >>>>>电话： " + sendPhone + " >>>> " + content);
		HttpClient client = new HttpClient();
		
		PropertiesUtil pu = PropertiesUtil.loadAllPropertiesFromClassLoader("smsTemplates.properties");
		String postMethod = pu.getProperty("postMethod");
		String corpID = pu.getProperty(areaCode + "CorpID");
		String pwd = pu.getProperty(areaCode + "Pwd");
		String host = pu.getProperty("Host");
//		System.out.println(corpID+"\t"+pwd+"\t"+host);
		PostMethod method = new PostMethod(postMethod);
		NameValuePair[] data = { new NameValuePair("CorpID", corpID),
				new NameValuePair("Pwd", pwd),
				new NameValuePair("Mobile", sendPhone),
				new NameValuePair("Content", content),
				new NameValuePair("Cell", ""),
				new NameValuePair("SendTime", ""), };
		method.setRequestHeader("Content-type","application/x-www-form-urlencoded;charset=UTF-8");
		method.setRequestHeader("Host", host);

		method.setRequestBody(data);
		
//		ExamSms sms = new ExamSms();
		String msg = "error";
		try{
			client.executeMethod(method);
//			System.out.println("status:" + method.getStatusCode());
//			System.out.println("msg:" + method.getResponseBodyAsString());
			msg = method.getResponseBodyAsString();
		}catch (Exception e) {
//			e.printStackTrace();
		}
//		if (!"0".equals(msg) && !"-1".equals(msg) && !"-2".equals(msg)
//				&& !"-3".equals(msg) && !"-4".equals(msg) && !"-5".equals(msg)
//				&& !"-6".equals(msg) && !"-7".equals(msg)) {
//			//sms.setMsg("1");
//		} else {
//			//sms.setMsg(msg);
//		}
		
//		sms.setPhone(sendPhone);
//		sms.setContent(messages);
//		sms.setStatus(Integer.toString(method.getStatusCode()));
//		sms.setAreaCode(areaCode);
//		sms.setCreateTime(new Date().getTime());
//		try{
//			this.examSmsService.addExamSms(sms);
//		}catch (Exception e) {
//			
//		}
		return msg;
	}

	public String sendMsg010(String sendPhone, String content) throws Exception{
		return this.sendMsg(sendPhone, content, "010");
	}
	public String sendMsg011(String sendPhone, String content) throws Exception{
		return this.sendMsg(sendPhone, content, "011");
	}
	
	public static String geneNums(){//生成随机码
		 Random random = new Random(); 
		 String result="";
		 for(int i=0;i<6;i++){
	        result += random.nextInt(10);    
		 }
		 return result;
	}
	public static void main(String[] args) {
		SmsUtils smsUtils = new SmsUtils();
		String sendsms = "11111111";

		try {//13201304402
			String result = smsUtils.sendMsg011("18630342782", sendsms);
			System.out.println(result);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}