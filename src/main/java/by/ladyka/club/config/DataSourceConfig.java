package by.ladyka.club.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Properties;

@Configuration
public class DataSourceConfig {
	private static final String USERNAME = "info@republic-club.by";
	private static final String PASSWRD = "";

	@Bean
	public DataSource database() {
		return DataSourceBuilder.create()
				.url("jdbc:mysql://127.0.0.1:3306/afisha?characterEncoding=UTF-8")
				.username("afisha")
				.password("afishapass")
				.driverClassName("com.mysql.jdbc.Driver")
				.build();
	}

	@Bean
	@Qualifier("republic")
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl() {

			@Override
			public void send(MimeMessage... mimeMessages) throws MailException {
				Arrays.asList(mimeMessages).forEach(mimeMessage -> {
					try {
						mimeMessage.setFrom(new InternetAddress(USERNAME, "RE:PUBLIC club"));
					} catch (MessagingException | UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				});
				doSend(mimeMessages, null);
			}
		};
		mailSender.setHost("smtp.yandex.ru");
		mailSender.setPort(587);
		mailSender.setUsername(USERNAME);
		mailSender.setPassword(PASSWRD);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.yandex.ru");
		props.put("mail.smtp.port", 587);
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.debug", "true");
		props.setProperty("mail.smtp.allow8bitmime", "true");
		props.setProperty("mail.smtps.allow8bitmime", "true");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWRD);
			}
		});
		mailSender.setSession(session);
		return mailSender;
	}
}
