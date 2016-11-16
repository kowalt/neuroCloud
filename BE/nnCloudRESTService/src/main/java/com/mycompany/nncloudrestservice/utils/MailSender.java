package com.mycompany.nncloudrestservice.utils;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import com.mycompany.nncloudrestservice.pojo.User;

public class MailSender {
	private final String FROM = "admin@nncloud.nodomainyet.com";
	private final String HOST = "localhost";
	private Properties properties;

	public MailSender()
	{
		properties = System.getProperties();
		properties.setProperty("mail.smtp.host", HOST);
	}
	
	public void sendNofificationAfterTraining(String networkName)
	{
        Session session = Session.getDefaultInstance(properties);
		final String SUBJECT = "nnCloud network training";
		final String BODY = "Your network " + networkName + " has finished training";
		
		User u = CurrentUserContainer.getInstance();
		final String RECIPIENT_ADDRESS = u.getEmail();
		
		try
		{
			
			
		}
		catch(MessagingException mex)
		{
			mex.printStackTrace();
		}
		
	}
}
