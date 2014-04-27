
/****************@author Simant Purohit*********************************/

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Email {

	//send email to single recipient
	//send email to multiple recipient
	//handle zero to multiple attachments
	private Session session;
	private Authenticator authentication;
	private Properties properties;
	public String fromEmail;
    public String password;
    
    private Email(final String fromEmail, final String password){    	
    	//try parsing the from address
    	@SuppressWarnings("unused")
		InternetAddress[] fromAddress = parseToEmailAddress(fromEmail);
    	
    	this.fromEmail = fromEmail;
    	this.password = password;
    	
    	this.properties = new Properties();
    	
    	//SMTP Host
    	properties.put("mail.smtp.host", "smtp.gmail.com"); 
    	
    	//SSL Port
    	properties.put("mail.smtp.socketFactory.port", "465"); 
    	
    	//SSL Factory Class
    	properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); 
    	
    	//Enabling SMTP Authentication
    	properties.put("mail.smtp.auth", "true");
    	
    	//SMTP Port
    	properties.put("mail.smtp.port", "465");
    	
    	this.authentication = new Authenticator() {
    		@Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
         
       this.session = Session.getDefaultInstance(properties, authentication);
    }
    
    public static Email getInstance(String fromEmail, String password){
    	Email email = new Email(fromEmail, password);
    	return email;
    }
    
    //Address parser for recipient address
    //pass comma separated recipient email ids
    //returns array of addresses
    private InternetAddress[] parseToEmailAddress(String toEmail){
    	InternetAddress[] email = null;
    	try {
			email = InternetAddress.parse(toEmail, false);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return email;
    }
	
    public boolean sendEmail(String toEmail, String subject, String body){
        try
        {
          MimeMessage msg = new MimeMessage(session);
          //set message headers
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");
 
          msg.setFrom(new InternetAddress(fromEmail, "UMAS-EMAIL"));
 
          msg.setReplyTo(InternetAddress.parse(fromEmail, false));
 
          msg.setSubject(subject, "UTF-8");
 
          msg.setText(body, "UTF-8");
 
          msg.setSentDate(new Date());
 
          msg.setRecipients(Message.RecipientType.TO, parseToEmailAddress(toEmail));
          System.out.println("Sending");
          Transport.send(msg); 
          System.out.println("Email Sent Successfully!!");
          return true;
        }
        catch (Exception e) {
          e.printStackTrace();
          return false;
        }
    }

    public boolean sendEmailWithAttachments(String toEmail, String subject, String body, String[] attachmentLocations){
    	try{
    		 MimeMessage msg = new MimeMessage(session);
             //set message headers
             msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
             msg.addHeader("format", "flowed");
             msg.addHeader("Content-Transfer-Encoding", "8bit");
    
             msg.setFrom(new InternetAddress(fromEmail, "UMAS-EMAIL"));
    
             msg.setReplyTo(InternetAddress.parse(fromEmail, false));
    
             msg.setSubject(subject, "UTF-8");
    
             msg.setText(body, "UTF-8");
    
             msg.setSentDate(new Date());
    
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
              
            // Create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();
    
            // Fill the message
            messageBodyPart.setText(body);
             
            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();
    
            // Set text message part
            multipart.addBodyPart(messageBodyPart);
    
            // Second part is attachment
            for(String fileLocation: attachmentLocations){
            	messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(fileLocation);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(fileLocation);
                multipart.addBodyPart(messageBodyPart);
            }
                
            // Send the complete message parts
            msg.setContent(multipart);
    
            // Send message
            Transport.send(msg);
            System.out.println("Email Sent with all the attachments");
            return true;
         }catch (MessagingException e) {
            e.printStackTrace();
            return false;
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
       }
   }
    
}
