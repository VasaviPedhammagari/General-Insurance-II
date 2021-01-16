package com.lti.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

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

import org.springframework.stereotype.Service;

@Service
public class MailService {

	
	public void sendotp(int otpNumber,String email){
		  
		try {
		Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("insuranceprojectlti@gmail.com", "xbmvardhjppvwzyj");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("tutorialspoint@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		   msg.setSubject("OTP");
		   msg.setContent(otpNumber+"", "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Tutorials point email", "text/html");

		 
		   Transport.send(msg); 
		}catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	public void sendDetails(String email,int custId,String loginId, String password, int accountNumber) {
		try {
			Properties props = new Properties();
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.starttls.enable", "true");
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.port", "587");
			   
			   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			      protected PasswordAuthentication getPasswordAuthentication() {
			         return new PasswordAuthentication("insuranceprojectlti@gmail.com", "xbmvardhjppvwzyj");
			      }
			   });
			   Message msg = new MimeMessage(session);
			   msg.setFrom(new InternetAddress("tutorialspoint@gmail.com", false));

			   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			   msg.setSubject("OTP");
			   msg.setContent("Your Account is Activated! Your LoginId is"+loginId+" Your Password is " +password+" Your Account Number is " + accountNumber + " Your Customer Id is "+ custId, "text/html");
			   msg.setSentDate(new Date());

			   MimeBodyPart messageBodyPart = new MimeBodyPart();
			   messageBodyPart.setContent("Tutorials point email", "text/html");

			 
			   Transport.send(msg); 
			}catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
