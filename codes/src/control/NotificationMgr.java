package control;

import entity.Student;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotificationMgr {

	private final static String username = "nayang.student.001";
	private final static String password = "Qwer!1234";
	private final static String FROM_ADDRESS = "nayang.student.001@gmail.com";
	private static Properties props;
	private final static String EMAIL_CONTENT_TEMPLATE = "Dear $studentName, \n\n"
			+ "This email notify to you that your course register is $courseCode";

	public NotificationMgr() {
		this.props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}

	public void sendEmail(Student student, String courseCode) {
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_ADDRESS));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(student.getEmail()));
			message.setSubject("Email Subject");
			message.setText(EMAIL_CONTENT_TEMPLATE.replace("$studentName", student.getFirstName()+" "+student.getLastName()).replace("$courseCode",
					courseCode));

			Transport.send(message);
			System.out.println("Sent email to " + student.getEmail());

		} catch (MessagingException e) {
			System.out.println("exception occured");
			throw new RuntimeException(e);
		}
	}

	public static void sendSMS(Student student, String courseCode) {
		// implement future extensions here
	}

	public static void sendWhatsapp(Student student, String courseCode) {
		// implement future extensions here
	}

}
