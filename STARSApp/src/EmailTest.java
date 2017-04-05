import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTest {
	
	public static void send(final String mailFrom,final String password,String mailTo,String mailSubject,String mailText){  
        //Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected javax.mail.PasswordAuthentication getPasswordAuthentication() {    
         return new javax.mail.PasswordAuthentication(mailFrom,password);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(mailTo));    
         message.setSubject(mailSubject);    
         message.setText(mailText);    
         //send message  
         Transport.send(message);    
         System.out.println("message sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);}    
           
  }  
}  


	/*public static void main(String[] args) {

		//String mailSmtpHost = "smtp.live.com";
		String studentId="YIUH0001";
		String mailTo = studentId +"@e.ntu.edu.sg";
		String mailFrom = "xxsacrifarxx@gmail.com";
		String mailSubject = "random shit";
		String mailText = "This is an email from random p******";

sendEmail(mailTo, mailSubject, mailText);		
	}

	public static void sendEmail(String to,String subject, String text) {
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.live.com");
			properties.put("mail.smtp.port", "587");
			properties.setProperty("mail.smtp.starttls.enable", "true");

			 properties.put("mail.transport.protocol","smtp");
			 properties.put("mail.smtp.auth", "true");
			// properties.put("mail.smtp.starttls.enable", "true");
			 properties.put("mail.smtp.tls", "true");
	         
			//properties.put("mail.smtp.user","zchen035@e.ntu.edu.sg");
			//properties.put("mail.password","Password11"); 

		//	Session emailSession = Session.getDefaultInstance(properties);
	
			javax.mail.Authenticator auth = null;
	         auth = new javax.mail.Authenticator(){
	             @Override
	             public javax.mail.PasswordAuthentication getPasswordAuthentication()
	             {
	             return new javax.mail.PasswordAuthentication("xxsacrifarxx@gmail.com","cross1994");
	             }
	 
	             };
	             
	         Session emailSession = Session.getDefaultInstance(properties, auth);
	 
			Message emailMessage = new MimeMessage(emailSession);
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//emailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			emailMessage.setFrom(new InternetAddress("yiuh0001@e.ntu.edu.sg"));
			emailMessage.setSubject(subject);
			emailMessage.setText(text);

			emailSession.setDebug(true);

			Transport.send(emailMessage);
			//Transport a = new Transport(a.close());
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
	}
}*/