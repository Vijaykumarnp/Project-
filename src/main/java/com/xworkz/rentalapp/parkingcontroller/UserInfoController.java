package com.xworkz.rentalapp.parkingcontroller;

import java.io.File;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;


import com.xworkz.rentalapp.Constants.*;

import com.xworkz.rentalapp.entity.UserEntity;
import com.xworkz.rentalapp.entity.UserInfoEntity;
import com.xworkz.rentalapp.parkingdto.AdminDTO;
import com.xworkz.rentalapp.parkingdto.UserDTO;
import com.xworkz.rentalapp.parkingdto.UserInfoDTO;
import com.xworkz.rentalapp.parkingservice.UserInfoService;
import com.xworkz.rentalapp.parkingservice.UserService;
import com.xworkz.rentalapp.repository.UserRepo;

@Controller
@Component
@RequestMapping("/")
public class UserInfoController {
    @Autowired
	UserInfoService service;

    
	List<UserInfoDTO> list =  new ArrayList<UserInfoDTO>();
	@PostMapping("/user")
	public String onSave(@Valid UserInfoDTO dto , @Valid UserDTO dtos,BindingResult bind ,  Model model) {
		
		
		if(bind.hasErrors()) {
			
			List<ObjectError> err = bind.getAllErrors();
			err.forEach(ob -> System.out.println(ob));
			model.addAttribute("err", err);
			model.addAttribute("dto", dto);
			
		}else {
			
			this.list.add(dto);
			System.out.println("data is valid");
			service.validateAndSave(dto , dtos);
			
		}
		
		
		
		
		
		return "UserInfo.jsp";
	}
	
	// private EmailSenderService emailService;

	    //Random random = new Random(1000);
	@RequestMapping("/sendotp")
	public String sendOTP(String generateOtp ,String login , String emailId, Model model, UserEntity entity , String oneTimePassword , HttpServletRequest req ) {
		System.out.println("Running sendOTP method");
		
		System.out.println("controller : generateOtp " + generateOtp );
		System.out.println("Controller : login from UI : " + login);
		System.out.println("Controller : email from UI :" + emailId);
		System.out.println("Controller : otp from UI " +oneTimePassword );
		//userService.otpSendMail(email,entity);
		
		if(generateOtp != null && "generateOtp".equals(generateOtp)) {
			System.out.println("inside otp");
		UserEntity entity1 =	service.findByUserEmail(emailId);
			
		HttpSession session = req.getSession(true);
		session.setAttribute("dto", entity1);
		session.setAttribute("emailId", emailId);
		
		System.out.println(entity1);
		if(entity1 != null) {
		UserEntity ent =	service.userSignIn(emailId, entity);
			if(ent != null) {
				model.addAttribute("emailId", emailId);
				model.addAttribute("successMsg","OTP send to email successfully");
			}else {
				model.addAttribute("emailId", emailId);
				model.addAttribute("successMsg","Re - generate the otp");
				
			}
		}else {
			model.addAttribute("emailId", emailId);
			model.addAttribute("emailError","Not a registered mail id");
			
		}
		
			
		}else if(login != null && "Login".equals(login) ) {
			System.out.println("validating otp");
			System.out.println("controller : on generate otp" + emailId );
			boolean isOtpValid =   service.login(emailId, oneTimePassword);
			UserEntity eneen = service.findByUserEmail(emailId);
		//	System.out.println("eneen:"+eneen);
		//	boolean otpNotExpired = eneen.getOtpExpiryTime().isAfter(LocalTime.now());
		//     if(otpNotExpired) {
		    	 
		//UserDTO isOtpValid =   service.login(emailId, oneTimePassword);
		
		if(isOtpValid == true) {
			HttpSession session = req.getSession();
			session.setAttribute("userDto", eneen);
			
			return "UserLoginSucess.jsp";
		}else {
			
			model.addAttribute("error", "Invalid OTP");
			
			return "UserLogin.jsp";
		}
		
		     }
		return "UserLogin.jsp";
		
	}
	
	
	
	@PostMapping("/save")
	public String onSave(@Valid @ModelAttribute UserInfoDTO dto, BindingResult bind, Model model,MultipartFile file, HttpServletRequest req) throws IOException {

		if (bind.hasErrors()) {
			System.out.print("data is invalid");
			List<ObjectError> l = bind.getAllErrors();
			l.forEach(ob -> System.err.println(ob.getDefaultMessage()));
			model.addAttribute("errors", l);
			model.addAttribute("dto", dto);
		} else {
			
			
			   System.out.println("File Recived:" + file.getOriginalFilename());
			   System.out.println("File size:" + file.getSize());
			   System.out.println("File bytes:" + file.getBytes());
			   System.out.println("File contents:" + file.getContentType());
			dto.setImageName(System.currentTimeMillis() + " " +   file.getOriginalFilename());
			//dto.setFileName(  file.getOriginalFilename());
			dto.setContentType(file.getContentType());
			dto.setFileSize(file.getSize());

			File physicalFile = new File(Constants.FILE_LOCATION + dto.getImageName());
			
			try (OutputStream s = new FileOutputStream(physicalFile)) {
				s.write(file.getBytes());
			}
			HttpSession session = req.getSession();
			UserEntity sessionDto=(UserEntity)session.getAttribute("userDto");
			service.onSave(dto , sessionDto.getEmailId());
			System.out.println(dto);
			this.list.add(dto);
			System.out.println("dto saved with details");
			System.out.println     ("data is valid");
			//model.addAttribute("sucessmsg", "contact info "   + dto.getName() +   " is saved .. ");
			

		}

		return "/Parking.jsp";

	}
	
	@GetMapping("/fetchAndUpdate")
	public String fetchAndUpdate(UserEntity entity1 , UserInfoEntity entity2 ,String imageName , String contentType ,  HttpServletRequest req , Model model , HttpServletResponse response  ) throws IOException {
		HttpSession session = req.getSession();
		
		
		
		UserEntity sessionDto=(UserEntity)session.getAttribute("userDto");
		UserDTO dtos = service.fetchAndUpdate(sessionDto.getEmailId());
   	 List<UserInfoDTO> dtoss =  service.fetchUserParkingInfo(sessionDto.getEmailId());

 	
	model.addAttribute("userDto", dtos);
	model.addAttribute("userInfoDto", dtoss);
	System.out.println("viewing parking info" +dtoss);
	
		return "/UserView.jsp";
	}
	
	
	@GetMapping("/filedownload")
	public void sendImage(String imageName, String contentType, HttpServletResponse response) throws IOException {
		System.out.print("running sendImage...");
		
		File file = new File(Constants.FILE_LOCATION + imageName);
		response.setContentType(contentType);
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[4096];
		int length;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.flush();
	}
	
	
	@GetMapping("/update/{parkingId}")
	public String getById(@PathVariable("parkingId") int parkingId , HttpServletRequest req  ) {
	
HttpSession session = req.getSession();
		 UserInfoDTO dto34 =   service.fetchUserById(parkingId);
		System.out.println(dto34);
		req.setAttribute("dto", dto34);
		
		RedirectView view =  new RedirectView();
		view.setUrl(req.getContextPath()+"/");
		return "/UpdateUser.jsp";
		
		
	}
	
	
	
	
	
	
	
	@PostMapping("/update")
	public String updateById(@ModelAttribute UserInfoDTO dto , HttpServletRequest req , BindingResult bind , Model model , MultipartFile file ) throws FileNotFoundException, IOException {
		
		 System.out.println("File Recived:" + file.getOriginalFilename());
		   System.out.println("File size:" + file.getSize());
		   System.out.println("File bytes:" + file.getBytes());
		   System.out.println("File contents:" + file.getContentType());
		dto.setImageName(System.currentTimeMillis() + " " +   file.getOriginalFilename());
		//dto.setFileName(  file.getOriginalFilename());
		dto.setContentType(file.getContentType());
		dto.setFileSize(file.getSize());

		File physicalFile = new File(Constants.FILE_LOCATION + dto.getImageName());
		
		try (OutputStream s = new FileOutputStream(physicalFile)) {
			s.write(file.getBytes());
		}
		
		service.updateById(dto);
		System.out.println("controller"+dto);
		req.setAttribute("dto", dto);
		
		
		return "/UpdateSucess.jsp";
		
		
		
	}
	
	
	
	@GetMapping("/delete/{parkingId}")
	public  RedirectView deleteById(@PathVariable("parkingId") int parkingId,HttpServletRequest req) {
	List<UserInfoDTO> list = service.vlidateAndDelete(parkingId);
		
		req.setAttribute("id" , list);	
		RedirectView rd = new RedirectView();
		rd.setUrl(req.getContextPath());
		rd.setUrl(req.getContextPath() + "/fetchAndUpdate");
		
		return rd;
		}
	
}
	    
	    
	

