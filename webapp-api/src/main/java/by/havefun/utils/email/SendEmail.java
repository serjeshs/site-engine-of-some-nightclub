package by.havefun.utils.email;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendEmail extends Thread {
	
	private EmailAccount emailAccount;
	
	protected String AUTH = "true";
	protected String emailTo;
	protected String emailCc;
	protected String subject;
	protected String messageBody;
	protected List<File> attachmentFiles;
	
	protected Logger logger = LoggerFactory.getLogger(getClass().getName());

	private String emailLOGName;
	
	
	public SendEmail(EmailAccount myemailAccount) {
		emailAccount = myemailAccount;
	}
	
	public void setparams(String emailTo, String subject, String messageBody,String emailLOGName) {
		this.emailTo = emailTo;
		this.subject = subject;
		this.messageBody = messageBody;
		this.attachmentFiles = null;
		this.emailLOGName = emailLOGName;
	}

	public void setparams(String emailTo, String emailCc, String subject,
			String messageBody, List<File> attachmentFiles,String emailLOGName) {
		this.emailTo = emailTo;
		if (emailCc == null) {
			
		} else {
			this.emailCc = emailCc;
		}
		
		this.subject = subject;
		this.messageBody = messageBody;
		this.attachmentFiles = attachmentFiles;
		this.emailLOGName = emailLOGName;
	}
	
	@Override
	public void run() {
		logger.debug("Start " + emailLOGName + ".run()");
		Properties props = new Properties();
		props.put("mail.smtp.auth", AUTH);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", emailAccount.getSERVER());
		props.put("mail.smtp.port", emailAccount.getSMTPPORT());
 
		Session session = Session.getInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailAccount.getLOGIN(), emailAccount.getPASSWORD());
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailAccount.getEMAIL()));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailTo));
			message.setSubject(subject);
			message.setText(messageBody);
			
			if (attachmentFiles != null) {
				BodyPart messageBodyPart = new MimeBodyPart();
		        messageBodyPart.setText("messageBody");
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
		        messageBodyPart = new MimeBodyPart();
				for (File file : attachmentFiles) {
					DataSource source = new FileDataSource(file);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(file.getName());
					multipart.addBodyPart(messageBodyPart);
				}
				message.setContent(multipart);
			} else {
				message.setContent(messageBody,"text/html; charset=utf-8");
			}
			
			Transport.send(message); 
		} catch (MessagingException e) {
			//throw new RuntimeException(e);
		}
		logger.debug("END " + emailLOGName + ".run()");
	}
}
