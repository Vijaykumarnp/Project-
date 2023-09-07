package com.xworkz.rentalapp.parkingservice;

import java.text.DecimalFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.xworkz.rentalapp.entity.AdminEntity;
import com.xworkz.rentalapp.entity.UserEntity;
import com.xworkz.rentalapp.entity.UserInfoEntity;
import com.xworkz.rentalapp.parkingdto.AdminDTO;
import com.xworkz.rentalapp.parkingdto.UserDTO;
import com.xworkz.rentalapp.parkingdto.UserInfoDTO;
import com.xworkz.rentalapp.repository.UserInfoRepo;
import com.xworkz.rentalapp.repository.UserRepo;
@Service
public class UserInfoServiceImpl  implements UserInfoService{
    @Autowired
	UserInfoRepo repo;
	
    @Autowired
    UserRepo repos;
 
	@Override
	public void validateAndSave(UserInfoDTO dto , UserDTO dtos ) {
		
		System.out.println("running validateAndSave");	
		UserEntity record=repos.findByUserEmail(dtos.getEmailId());
		if(record==null) {
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(dtos, entity);
		System.out.println(entity);
	
		
	boolean save = 	repos.onSave(entity);
		
	if(save == true) {
		UserInfoService service = new UserInfoServiceImpl();
		service.sendMail(dtos.getEmailId());
		System.out.println("after save");
		
		
	}else {
		
		
		System.out.println("registration not completed");
	}
	
	
	
		if(entity!=null) {
		
			
			UserInfoEntity entity1= new UserInfoEntity();
			
		//	System.out.println("the current user id" +entity.getUserId());
			UserEntity userByEmail=repos.findByUserEmail(dtos.getEmailId());
			dto.setUser_Id(userByEmail.getUser_Id());
			BeanUtils.copyProperties(dto, entity1);
			System.out.println(entity1);
			repo.validateAndSave(entity1);;
			
			
			
		}
		}
	return ;
	
	}
	
	 @Override
		public boolean sendMail(String emailId ) {
			System.out.println("Inside sendMail");
			String portNumber="587";
			String hostName="smtp.office365.com";
			final String fromEmail="vijaykumarvini59@gmail.com";
			final String password="vijaykumar@";
			String to=emailId;
			
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
				message.setSubject("Your Registratio is completed for the Parking ");
				message.setText("Thanks for Registering ");
			
				// String body = "<h1> OTP = " + otp + "</h1>";
				//here we need to add code
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
				Transport.send(message);
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			return true;
		}
	
	
	
	
	

	 @Override
		public boolean otpSendMail(String emailId , UserEntity   entity) {
		 
		
		 
			System.out.println("Inside sendMail");
			String portNumber="587";
			String hostName="smtp.office365.com";
			final String fromEmail="vijaykumarvini59@gmail.com";
			final String password="vijaykumar@";
			String to=emailId;
			
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
				
				
				 
				 String otp= new DecimalFormat("0000").format(new Random().nextInt(9999));
					System.out.println(otp);
				
					// oneTimePassword = otp;
				entity.setOneTimePassword(otp);
					
			
				
				message.setFrom(new InternetAddress(fromEmail));
				message.setSubject("Here Your one Time Password Sent BY Vijay");
				message.setText(" OTP = " + entity.getOneTimePassword() + "");
				
				
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
				Transport.send(message);
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		 
			return true;
		}
	
	  
	 
	
	 
	 @Override
	 public UserEntity userSignIn(String emailId , UserEntity entity) {
		 
		 UserEntity record=repos.findByUserEmail(entity.getEmailId());
		 
//		 if(emailId.equals(record)) {
				UserInfoService service = new UserInfoServiceImpl();
			service.otpSendMail(emailId, entity);
			UserDTO dto = new UserDTO(); 
			String oneTimePassword =entity.getOneTimePassword();
			
			
			repos.userSignInUpdate(oneTimePassword,emailId);
//		 }else {
//			 
//			 System.out.println("email is not valid");
//			 
//		 }
		 
		 
		 
		return record; 
	 }

		@Override
		public boolean  login(String emailId, String OneTimePassword) {
			System.out.println("inside login ");
			UserEntity entity=this.repos.login(emailId);
			UserDTO  dto=new UserDTO();
			if(entity.getEmailId().equals(emailId) && entity.getOneTimePassword().equals(OneTimePassword)){
				SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss");
				String formatedDate=dateFormat.format(new Date()).toString();
				
				entity.setLoginTime(formatedDate);
				boolean status=repos.updatedTime(entity);
				UserEntity updatedEntity=repos.login(emailId);
				
				BeanUtils.copyProperties(updatedEntity,dto);
				return true;
			}
			
			//UserDTO dto = new UserDTO();
			return false ;
		}

		@Override
	public UserEntity findByUserEmail(String emailId) {
			return repos.findByUserEmail(emailId);
		}

		@Override
		public void onSave(UserInfoDTO dto, String email) {
			
			UserInfoEntity entity =  new UserInfoEntity();
			BeanUtils.copyProperties(dto, entity);
			UserEntity userEntity = repos.findByUserEmail(email);
			entity.setUser_Id(userEntity.getUser_Id());
			repo.validateAndSave(entity);
		}

		@Override
		public UserDTO fetchAndUpdate(String email) {
		
			UserEntity userEntity = repos.findByUserEmail(email);
			UserDTO dto = new UserDTO();
			BeanUtils.copyProperties(userEntity, dto);
			return dto;
		}
			
			@Override
			public List<UserInfoDTO> fetchUserParkingInfo(String email) {
				UserEntity userEntity = repos.findByUserEmail(email);
		List<UserInfoEntity> en =	repo.findByUserId(userEntity.getUser_Id());
		List<UserInfoDTO> dtos=en.stream().map(e->{
			UserInfoDTO dto1 = new UserInfoDTO();
			BeanUtils.copyProperties(e, dto1);
			return dto1;
			
		}).collect(Collectors.toList());
		

			
			return dtos;
		}

		@Override
		public UserInfoDTO updateByEmail(UserInfoDTO dto    , String email) {
			UserEntity userEntity = repos.findByUserEmail(email);
			//List<UserInfoEntity> en =	repo.findByUserId(userEntity.getUser_Id());
			UserInfoEntity entity =  new UserInfoEntity();
			BeanUtils.copyProperties(dto, entity);
			
			//repo.update(entity , userEntity.getUser_Id());
			
			return dto ;
		}

		@Override
		public UserInfoDTO fetchUserById(int parkingId  ) {
		
			
			
		
			UserInfoEntity en =	repo.findByUserIdForUpdate(parkingId);
			if(en!= null) {
				UserInfoDTO dto = new UserInfoDTO();
			BeanUtils.copyProperties(en, dto);
			return dto;
			}
				
				return null;
		}

		@Override
		public void updateById(UserInfoDTO dto) {
		
			UserInfoEntity entity = new UserInfoEntity();
			BeanUtils.copyProperties(dto, entity);
//			UserInfoEntity userEntity = repo.findByUserIdForUpdate(.getParkingId());
//			entity.setUser_Id(userEntity.getParkingId());
			repo.update(entity);
			
				System.out.println("service"+entity);
			}

		@Override
		public List<UserInfoDTO> vlidateAndDelete(int parkingId) {
			// TODO Auto-generated method stub
			return repo.deleteById(parkingId);
		}
			
		}

	
	
		

		
	
	 

		
		
