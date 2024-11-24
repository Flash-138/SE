package Application.Notification;

import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 

public class EmailSenderUtil {
	public static void sendEmail(EmailData emailData) throws MessagingException {
		System.out.println("Message sneding process started...");
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth",  "true");
		properties.put("mail.smtp.starttls.enable",  "true");
		properties.put("mail.smtp.host",  "smtp.gmail.com");
		properties.put("mail.smtp.port",  "587");

        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		String accountEmail = "gahfacilities@gmail.com";
		String password = "kvxu qmyl kyrt tosw";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(accountEmail, password);
			}
		});
		
		Message message = prepareMessage(session, accountEmail, emailData);
		Transport.send(message);
		System.out.print("Message sent");
		 
		
	}
	
	
	private static Message prepareMessage(Session session, String accountEmail, EmailData emailData) {
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(accountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailData.getRecipient().getEmail()));
			message.setSubject(emailData.getSubject());
			message.setContent(emailData.getContent(), "text/html");
			
			
			return message;
			
		} catch(Exception e) {
			System.out.print("Didnt send email");
			return null;
		}
	}
}