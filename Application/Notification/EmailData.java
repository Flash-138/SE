package Application.Notification;
import Base.Person;
public class EmailData {
	private Person recipient;
	private String text;
	private String subject;
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getContent() {
		
		String emailContent =  "Hello %s, <br>"
				+ "%s<br><br>"
				+ "Sincerely,<br>"
				+ "The GAH Facilities Team."; 
		return String.format(emailContent, recipient.getName(), text);
		
		
	}
	public void setRecipient(Person recipient) {
		this.recipient = recipient;
	}
	
	public Person getRecipient() {
		return recipient;
	}
	
	public String getSubject() {
		return subject;
	}
}