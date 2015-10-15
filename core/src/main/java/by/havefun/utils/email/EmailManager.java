package by.havefun.utils.email;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailManager{
	

	
	public final static String qdcovVersion = "[Promo] v0.8.6";
	
	public static void send(String emailTo, String subject, String messageBody) {
		Logger logger = LoggerFactory.getLogger(EmailManager.class);
		logger.info("\nSend message : \n"
				+ "Email to : " + emailTo 
				+ "SUBJ : " + subject
				+ "MESSAGE : " + messageBody);
		try {
			SendEmail sendEmail = new SendEmail(EmailFactory.getPromo());
			sendEmail.setparams(emailTo, subject, messageBody,qdcovVersion);
			sendEmail.start();
		} catch (Exception ex) {
			try {
				new PostfixSender(emailTo,subject, messageBody).start();
			} catch (Exception ex1) {
				//ex1.printStackTrace();
			}
			//ex.printStackTrace();
		}
		
		logger.debug("Send constructor done");
	}

	public static void send(String emailTo, String emailCc, String subject, String messageBody, List<File> attachmentFiles) {
		Logger logger = LoggerFactory.getLogger("com.quadrate64.qdoc.util.mail.EmailManager");
		logger.info("\nSend message : "
				+ "\nEmail to : " + emailTo 
				+ "\nSUBJ : " + subject
				+ "\nMESSAGE : " + messageBody);
		try {
			SendEmail sendEmail = new SendEmail(EmailFactory.getPromo());
			sendEmail.setparams(emailTo,null, subject, messageBody,attachmentFiles,qdcovVersion);
			sendEmail.start();
		} catch (Exception ex) {
			try {
				new PostfixSender(emailTo,null, subject, messageBody,attachmentFiles).start();
			} catch (Exception ex1) {
				//ex1.printStackTrace();
			}
			//ex.printStackTrace();
		}
		logger.debug("Send constructor done");
	}
	
}
