package Application.Notification;

import java.io.Serializable;

import javax.mail.MessagingException;

import Base.Person;

public class Notification implements Serializable {
	Person recipient;
	EmailData emailData;

	public Notification(EmailData emailData) {
		this.emailData = emailData;
		this.recipient = emailData.getRecipient();
	}

	public Person getRecipient() {
		return recipient;

	}
	public void send() {
		try {
			EmailSenderUtil.sendEmail(emailData);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}