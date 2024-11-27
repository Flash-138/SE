package Application.Notification;

import javax.mail.MessagingException;

import Base.Person;

public class Notification {
	Person[] recipients;
	
	
	public Notification(EmailData emailData) throws Exception {
		EmailSenderUtil.sendEmail(emailData);
		
	}
}