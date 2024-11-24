import javax.mail.MessagingException;

public class Notification {
	Person[] recipients;
	public Notification(EmailData emailData) throws MessagingException {
		
		EmailSenderUtil.sendEmail(emailData);
		
	}
	
	
	public String serialize() {
		return "";
	}
}