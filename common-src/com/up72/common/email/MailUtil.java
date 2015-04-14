package com.up72.common.email;

/**
 * 邮件发送工具类
 * 
 * @author xxh
 * @link 
 * 
 * @version $Revision: 1.00 $ $Date: 2009-12-18
 */

import java.util.Date;
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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.up72.common.CommonConstants;

public class MailUtil {

	private static Logger logger = Logger.getLogger(MailUtil.class);

	/***************************************************************************
	 * 邮件发送，带用户名和密码验证，测试通过
	 * 
	 * @param to
	 * @param from
	 * @param title
	 * @param content
	 * @param smtpServer
	 * @param user
	 * @param password
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(String to, String from, String title, String content,
			String smtpServer, String user, String password, boolean isHTML)
			throws AddressException, MessagingException {

		Properties props = new Properties();
		Authenticator auth = new MailAuthenticator(user, password);
		Session sendMailSession;
		Transport transport;
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
		sendMailSession = Session.getInstance(props, auth);
		Message newMessage = new MimeMessage(sendMailSession);
		newMessage.setFrom(new InternetAddress(from));
		newMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(
				to));
		newMessage.setSubject(title);
		newMessage.setSentDate(new Date());
		if (isHTML) {
			newMessage.setContent(content, "text/html;charset=UTF-8");
		} else {
			newMessage.setText(content);
		}
		transport = sendMailSession.getTransport("smtp");
		Transport.send(newMessage);
		transport.close();
		if (logger.isDebugEnabled()) {
			logger.debug("---------- Mail Send Success ----------");
		}
	}

	/***************************************************************************
	 * 邮件发送，不带用户名和密码验证
	 * 
	 * @param to
	 * @param from
	 * @param title
	 * @param content
	 * @param smtpServer
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(String to, String from, String title, String content,
			String smtpServer) throws AddressException, MessagingException {
		Properties props = new Properties();
		Authenticator auth = new MailAuthenticator();
		Session sendMailSession;
		Transport transport;
		sendMailSession = Session.getInstance(props, auth);
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
		Message newMessage = new MimeMessage(sendMailSession);
		newMessage.setFrom(new InternetAddress(from));
		newMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(
				to));
		newMessage.setSubject(title);
		newMessage.setSentDate(new Date());
		newMessage.setText(content);
		transport = sendMailSession.getTransport("smtp");
		Transport.send(newMessage);
		transport.close();
		if (logger.isDebugEnabled()) {
			logger.debug("---------- Mail Send Success ----------");
		}
	}

	/***************************************************************************
	 * 带附件邮件发送，带用户名和密码验证，测试通过
	 * 
	 * @param to
	 * @param from
	 * @param title
	 * @param content
	 * @param smtpServer
	 * @param user
	 * @param password
	 * @param fileNames
	 * @param paths
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(String to, String from, String title, String content,
			String smtpServer, String user, String password,
			String fileNames[], String[] paths) throws AddressException,
			MessagingException {
		try {
			Properties props = new Properties();
			Authenticator auth = new MailAuthenticator(user, password);
			Session sendMailSession;
			Transport transport;
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.auth", "true");
			sendMailSession = Session.getInstance(props, auth);
			MimeMessage newMessage = new MimeMessage(sendMailSession);
			newMessage.setFrom(new InternetAddress(from));
			newMessage.setRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			// 创建 Multipart 并放入每个 MimeBodyPart
			Multipart mp = new MimeMultipart();

			if (title != null && !title.equals("")) {
				// 设置邮件主题
				newMessage.setSubject(title);
			} else {
				newMessage.setSubject("无主题");
			}
			// 第一部分信息
			MimeBodyPart mbp1 = new MimeBodyPart();
			if (content != null && !content.equals("")) {
				mbp1.setText(content, "GBK");
			} else {
				mbp1.setText(new String(), "GBK");
			}
			mp.addBodyPart(mbp1);

			// 在第二部分信息中附加文件
			if (paths != null) {
				for (int i = 0; i < paths.length; i++) {
					MimeBodyPart mbp2 = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(
							CommonConstants.ROOTPATH + paths[i]);
					mbp2.setDataHandler(new DataHandler(fds));
					mbp2.setFileName(fileNames[i]);
					mp.addBodyPart(mbp2);
				}
			}
			// 增加 Multipart 到信息体
			newMessage.setContent(mp);
			newMessage.setSentDate(new Date());
			transport = sendMailSession.getTransport("smtp");
			Transport.send(newMessage);
			transport.close();
		} catch (Exception e) {
			logger.error("Send Mail Error!", e);
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("---------- Mail Send Success ----------");
		}
	}
}

class MailAuthenticator extends Authenticator {
	private String user;
	private String password;

	public MailAuthenticator() {

	}

	public MailAuthenticator(String user, String password) {
		this.user = user;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}

	public String getMailServer(String email) {
		String mailUrl = "/";
		if (null != email) {
			mailUrl = email.toLowerCase();
			String email_array = email.substring(email.indexOf("@"));
			if ("163.com".equals(email_array)) {// 163邮箱
				mailUrl = "mail.163.com";
			} else if ("vip.163.com".equals(email_array)) {// 163vip邮箱
				mailUrl = "vip.163.com";
			} else if ("sina.com".equals(email_array)) {// 新浪邮箱
				mailUrl = "mail.sina.com.cn";
			} else if ("sina.cn".equals(email_array)) {// 新浪邮箱
				mailUrl = "mail.sina.com.cn";
			} else if ("vip.sina.com".equals(email_array)) {// 新浪vip邮箱
				mailUrl = "vip.sina.com.cn";
			} else if ("2008.sina.com".equals(email_array)) {// 新浪2008邮箱
				mailUrl = "mail.2008.sina.com.cn";
			} else if ("sohu.com".equals(email_array)) {// 搜狐邮箱
				mailUrl = "mail.sohu.com";
			} else if ("vip.sohu.com".equals(email_array)) {// 搜狐vip邮箱
				mailUrl = "vip.sohu.com";
			} else if ("tom.com".equals(email_array)) {// Tom邮箱
				mailUrl = "mail.tom.com";
			} else if ("vip.sina.com".equals(email_array)) {// Tom vip 邮箱
				mailUrl = "vip.tom.com";
			} else if ("sogou.com".equals(email_array)) {// 搜狗邮箱
				mailUrl = "mail.sogou.com";
			} else if ("126.com".equals(email_array)) {// 126邮箱
				mailUrl = "www.126.com";
			} else if ("vip.126.com".equals(email_array)) {// 126 vip 邮箱
				mailUrl = "vip.126.com";
			} else if ("139.com".equals(email_array)) {// 139邮箱
				mailUrl = "mail.10086.cn";
			} else if ("gmail.com".equals(email_array)) {// gmail邮箱
				mailUrl = "www.gmail.com";
			} else if ("hotmail.com".equals(email_array)) {// 139邮箱
				mailUrl = "login.live.com";
			} else if ("189.cn".equals(email_array)) {// 电信邮箱
				mailUrl = "webmail2.189.cn/webmail/";
			} else if ("qq.com".equals(email_array)) {// qq邮箱
				mailUrl = "mail.qq.com";
			} else if ("yahoo.com".equals(email_array)) {// 雅虎邮箱
				mailUrl = "mail.cn.yahoo.com";
			} else if ("yahoo.cn".equals(email_array)) {// 雅虎邮箱
				mailUrl = "mail.cn.yahoo.com";
			} else if ("yahoo.com.cn".equals(email_array)) {// 雅虎邮箱
				mailUrl = "mail.cn.yahoo.com";
			} else if ("21cn.com".equals(email_array)) {// 21cn邮箱
				mailUrl = "mail.21cn.com";
			} else if ("eyou.com".equals(email_array)) {// eyou邮箱
				mailUrl = "www.eyou.com";
			} else if ("188.com".equals(email_array)) {// 188邮箱
				mailUrl = "www.188.com";
			} else if ("yeah.net".equals(email_array)) {// yeah邮箱
				mailUrl = "www.yeah.net";
			} else if ("foxmail.com".equals(email_array)) {// foxmail邮箱
				mailUrl = "foxmail.com";
			} else if ("wo.com.cn".equals(email_array)) {// 联通手机邮箱
				mailUrl = "mail.wo.com.cn";
			} else if ("263.net".equals(email_array)) {// 263邮箱
				mailUrl = "www.263.net";
			} else if ("x263.net".equals(email_array)) {// 263邮箱
				mailUrl = "www.263.net";
			} else if ("263.net.cn".equals(email_array)) {// 263邮箱
				mailUrl = "www.263.net";
			} else {
				mailUrl = "mail." + (email.substring(email.indexOf("@") + 1));
			}
		}
		return mailUrl;
	}

	// public static void main(String args[]){
	// Random random = new Random();
	// String password = random.nextInt()+ "";
	// StringBuffer content = new StringBuffer();
	// content.append("您好！用户 Mr.Men ***** 在radio的密码为：
	// "+password+"<br/>(该邮件为系统邮件，请勿回复，登录后请及时修改您的密码。)");
	//			
	// //PropertiesUtil pu = new
	// PropertiesUtil(PropertiesUtil.getConfigFile(System.getServletContext(),"backpwdmail.properties"));
	// String mailHost = "smtp.163.com";//pu.getValue("mail.send.host");
	// String mailEmail =
	// "EgoTown2010@163.com";//pu.getValue("mail.send.email");
	// String mailUser = "EgoTown2010";//pu.getValue("mail.send.user");
	// String mailPassword = "20100205";//pu.getValue("mail.send.password");
	// try {
	// mailUtil.send("ning-lovelove@163.com", mailEmail,
	// "密码重置", content.toString(), mailHost, mailUser,
	// mailPassword, false);
	// System.out.println("Mail Send Success");
	// } catch (AddressException e) {
	// System.out.println("AddressException-------" + e.getMessage());
	// e.printStackTrace();
	// } catch (MessagingException e) {
	// System.out.println("MessagingException-------" + e.getMessage());
	// e.printStackTrace();
	// }
	// }

}