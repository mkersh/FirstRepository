package com.mk.Delicious;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class SendEmail {
	private DeliciousPropertyFile prop = DeliciousPropertyFile.getInstance(); 
	private String gmailUser, gmailPassword, toEmailAddress;
	public SendEmail(){
		gmailUser = prop.getProperty("GmailUser");
		gmailPassword = prop.getProperty("GmailPassword");
		//toEmailAddress = prop.getProperty("ToEmailAddress");
		
	}
	
	public void post(String from, String to, String title, String body){

		try {
			GoogleMail.Send(gmailUser, gmailPassword, to, title, body);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void debugPostMsg(String from, String to, String title, String body){
		System.out.println("=======================================");
		System.out.println("From: " + from);
		System.out.println("To: " + to);
		System.out.println("Title: " + title);
		System.out.println(body);
		System.out.println("=======================================");
	}

}
