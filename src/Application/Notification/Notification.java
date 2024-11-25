package Application.Notification;

import javax.mail.MessagingException;

import Base.Person;

public class Notification {
	Person[] recipients;
	public Notification(EmailData emailData) throws MessagingException {
		
		EmailSenderUtil.sendEmail(emailData);
		
	}
	
	
	public String serialize() {
		return "";
	}
}