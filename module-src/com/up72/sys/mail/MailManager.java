package com.up72.sys.mail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.up72.sys.SystemConfig;
import com.up72.util.PropertiesUtil;

public class MailManager {
	public static boolean DEBUG = true;
	
	private Authenticator auth = null;
	private String smtpHost;
	private String smtpAuth;
	private String smtpAuthUser;
	private String smtpAuthPwd;
	private String smtpEncode;
	private String smtpME;

	PropertiesUtil propertiesUtil = null;

	public MailManager() {
		try {
			propertiesUtil = PropertiesUtil
					.loadAllPropertiesFromClassLoader(EmailConstants.PROPERTIES_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 smtpAuthUser = SystemConfig.instants().getValue("mail.smtp.user");
		 smtpAuthPwd = SystemConfig.instants().getValue("mail.smtp.password");
		 smtpHost = SystemConfig.instants().getValue("mail.smtp.host");
		 smtpAuth = SystemConfig.instants().getValue("mail.smtp.auth");
		 smtpEncode = SystemConfig.instants().getValue("mail.smtp.encode");
		 smtpME = SystemConfig.instants().getValue("mail.smtp.messageEncode");

		auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(smtpAuthUser, smtpAuthPwd);
			}
		};
	}

	public void sendMail(String toAddr, String subject, String body) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.auth", smtpAuth);

			Session session = Session.getInstance(props, auth);
			Message msg = new MimeMessage(session);
			// 指定发信人
			msg.setFrom(new InternetAddress(smtpAuthUser, "美国福禄克公司"));

			// 指定收件人
			InternetAddress[] tos = InternetAddress.parse(toAddr);
			msg.setRecipients(Message.RecipientType.TO, tos);
			// 标题//转码BASE64Encoder
			if (null == smtpEncode || smtpEncode.trim().equals("")) {
				smtpEncode = "utf-8";
			}
			((MimeMessage) msg).setSubject(subject, smtpEncode);
			// 得到中文标题for linux，windows下不用;
			// 内容
			msg.setText(body);
			// 发送时间
			msg.setSentDate(new Date());
			// 内容类型Content-type
			if (null == smtpME || smtpME.trim().equals("")) {
				smtpME = "utf-8";
			}
			msg.setContent(body, "text/html;charset=" + smtpME);
			// 发送
			Transport.send(msg);
		} catch (MessagingException e) {
			if (DEBUG) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			if (DEBUG) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送带有附件的邮件
	 */
	public void sendMail(String toAddr, String subject, String body,
			Map<String, String> files) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.auth", smtpAuth);

			Session session = Session.getInstance(props, auth);
			Message msg = new MimeMessage(session);
			// 指定发信人
			msg.setFrom(new InternetAddress(smtpAuthUser, "美国福禄克公司"));

			// 指定收件人
			InternetAddress[] tos = InternetAddress.parse(toAddr);
			msg.setRecipients(Message.RecipientType.TO, tos);
			// 标题//转码BASE64Encoder
			if (null == smtpEncode || smtpEncode.trim().equals("")) {
				smtpEncode = "utf-8";
			}
			((MimeMessage) msg).setSubject(subject, smtpEncode);
			// 得到中文标题for linux，windows下不用;
			// 内容
			msg.setText(body);
			// 发送时间
			msg.setSentDate(new Date());
			// 内容类型Content-type
			if (null == smtpME || smtpME.trim().equals("")) {
				smtpME = "utf-8";
			}
			msg.setContent(body, "text/html;charset=" + smtpME);
			//附件
			Multipart multipart = new MimeMultipart();
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			if (body != null && !body.equals("")) {
				mimeBodyPart.setText(body, "GBK");
			} else {
				mimeBodyPart.setText(new String(), "GBK");
			}
			multipart.addBodyPart(mimeBodyPart);
			if (null != files && !files.isEmpty()) {
				Iterator<String> it = files.keySet().iterator();
				while (it.hasNext()) {
					// 获取文件名称
					String fileName = it.next();
					// 得到文件路径
					String path = files.get(fileName);

					MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(path);
					mimeBodyPart2.setDataHandler(new DataHandler(fds));
					mimeBodyPart2.setFileName(MimeUtility.encodeText(fileName));
					multipart.addBodyPart(mimeBodyPart2);
				}
			}
			msg.setContent(multipart);
			// 发送
			Transport.send(msg);

		} catch (MessagingException e) {
			if (DEBUG) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			if (DEBUG) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 修改mail.properties配置文件
	 * @author wgf
	 * @param key
	 * @param value
	 */
	public void eidteEmailProperty(String key,String value){
		if (null != key && !key.trim().equals("") && null != value && !value.trim().equals("")) {
			try {
				Properties properties = PropertiesUtil.loadAllPropertiesFromClassLoader(EmailConstants.PROPERTIES_FILE).getProperties();
				propertiesUtil.setProperty(key, value);
				PropertiesUtil.save(properties, EmailConstants.PROPERTIES_FILE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]) {
//		Map<String, String> files = new HashMap<String, String>();
//		files.put("图片1", "E:/workspace/upapp2012/WebRoot/images/mojave.jpg");
//		files.put("图片2", "E:/workspace/upapp2012/WebRoot/images/sea-mist.jpg");
//
//		String content = "邮件测试"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//		
//		new MailManager().sendMail("twq0824@yeah.net","密码重置",content, files);
	}
}