package com.xworkz.rentalapp.parkingservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.rentalapp.entity.AdminEntity;
import com.xworkz.rentalapp.parkingdto.AdminDTO;
import com.xworkz.rentalapp.repository.ParkingRepo;


@Service
public class ParkingServiceIMPL implements ParkingService {
    @Autowired
	ParkingRepo repo;
	
    
    
    @Override
	public boolean sendMail(String email) {
		System.out.println("Inside sendMail");
		
		String portNumber="587";
		String hostName="smtp.office365.com";
		final String fromEmail="vijaykumarvini59@outlook.com";
		final String password="vijaykumar@";
		String to=email;
		
		Properties prop=new Properties();
		prop.put("mail.smtp.host", hostName);
		prop.put("mail.smtp.port", portNumber);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.debug",true);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.transport.protocol", "smtp");
		
		Session session=Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});
		
		MimeMessage message=new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(fromEmail));
			message.setSubject("Registration completed");
			message.setText("Thanks for registering!!!");
			//here we need to add code
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			Transport.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return true;
	}
    
	@Override
	public AdminDTO  login(String emailId, String Password) {
		
		AdminEntity entity=this.repo.login(emailId);
		
		if(entity.getEmailId().equals(emailId) && entity.getPassword().equals(Password)){
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss");
			String formatedDate=dateFormat.format(new Date()).toString();
			
			entity.setLoginTime(formatedDate);
			boolean status=repo.updatedTime(entity);
			AdminEntity updatedEntity=repo.login(emailId);
			AdminDTO  dto=new AdminDTO();
			BeanUtils.copyProperties(updatedEntity,dto);
			return dto;
		}
		
		AdminEntity dto =  repo.login(emailId);
		return null ;
	}

}
