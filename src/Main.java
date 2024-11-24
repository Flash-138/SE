import javax.mail.MessagingException;

public class Main {

	static Person self = new Person("GAH Facilities", "", "gahfacilities@gmail.com");
	
	public static void main(String[] args) throws MessagingException {
		
		EmailData data = new EmailData();
		data.setSubject("The GAH Notification System Update");
		data.setText("<h3>The Notification System is up and running from your Java app</h3>\n"
				+ "<p>This email serves to show that after starting your java app, the Notification "
				+ "System was succesffully able to send a message to itself.<p><br>"
				+ "<bold> Server status: 😍</bold>");
		data.setRecipient(self);
		
		new Notification(data); // The notification is auto sent
		
	}

}
