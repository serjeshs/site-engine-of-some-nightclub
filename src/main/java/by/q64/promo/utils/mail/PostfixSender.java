package by.q64.promo.utils.mail;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PostfixSender extends Thread {

	private String EMAILFROM = "bizflow-no-reply@quadrate64.com";
	
	private String emailTo;
	private String emailCc;
	private String subject;
	private String messageBody;
	@SuppressWarnings("unused")
	private List<File> attachmentFiles;
	
	
	public PostfixSender(String emailTo, String subject, String messageBody) {
		this.emailTo = emailTo;
		this.emailCc = "agumaas@yandex.kz";
		this.subject = subject;
		this.messageBody = messageBody;
		this.attachmentFiles = null;
	}

	public PostfixSender(String emailTo, String emailCc, String subject,
			String messageBody, List<File> attachmentFiles) {
		this.emailTo = emailTo;
		this.emailCc = emailCc;
		this.subject = subject;
		this.messageBody = messageBody;
		this.attachmentFiles = attachmentFiles;
	}
	
	public void run() {
	      String host = "localhost";
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtp.host", host);
	      Session session = Session.getDefaultInstance(properties, null);
	      try{
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(EMAILFROM));
	         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailTo));
	         message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(emailCc));
	         message.setSubject(subject);
	         message.setText(messageBody);
	         Transport.send(message); 
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
}
