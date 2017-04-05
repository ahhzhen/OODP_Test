
public class SendMailSSL {

	 public static void main(String[] args) {    
	     //from,password,to,subject,message  
			String studentId="YIUH0001";
			String mailTo = studentId +"@e.ntu.edu.sg";
			String password="cz2002oodp";
			String mailFrom = "oodp2002@gmail.com";
			String mailSubject = "random p******";
			String mailText = "username: oodp2002@gmail.com, password: cz2002oodp. im sending from java btw";
	     EmailTest.send(mailFrom,password,mailTo,mailSubject,mailText);  
	     //change from, password and to  
	 }    
	}
//this class is to be removed when main is set up. 