package com.up72.iphone.util;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

public class IPhonePush {
	private static String certificatePath = IPhonePush.class.getResource("/").toString().replace("file:/", ""); 

	public static void push(String token, String content, Integer badge,String p12) {
		try {
			//被推送的iphone应用程序标示符      
			PayLoad payLoad = new PayLoad();
			payLoad.addAlert(content);
			payLoad.addBadge(badge);
			payLoad.addSound("default");
			PushNotificationManager pushManager = PushNotificationManager.getInstance();
			pushManager.addDevice("iphone", token);

			//Device c = pushManager.getDevice("iphone");
			String host = "gateway.sandbox.push.apple.com"; //测试用的苹果推送服务器
			int port = 2195;
			

			String certificatePassword = "123456";

			pushManager.initializeConnection(host, port, certificatePath+p12, certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);//初始化tcp连接，公司网络代理上网，不能连上外网的tcp连接，坑死人

			//Send Push
			Device client = pushManager.getDevice("iphone");
			pushManager.sendNotification(client, payLoad); //推送消息
			pushManager.stopConnection();
			pushManager.removeDevice("iphone");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("push succeed!");
	}

	public static void main(String[] args) throws Exception {
		String certificatePath = "push.p12"; //刚才在mac系统下导出的证书
		push("0c897b31bcfdce1d5db8ccd5be76a73ac4bbbcf39066cf4f1d6228ad4a95570d", "测试推送12322", 5, certificatePath);
	}
}
