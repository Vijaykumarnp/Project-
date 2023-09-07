package com.xworkz.rentalapp.parkingcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.xworkz.rentalapp.entity.InformationEntity;
import com.xworkz.rentalapp.entity.UserEntity;
import com.xworkz.rentalapp.parkingdto.UserDTO;
import com.xworkz.rentalapp.parkingservice.Information;
import com.xworkz.rentalapp.parkingservice.UserInfoService;
@RestController
@EnableWebMvc
@RequestMapping("/")
public class AjaxController {

	@Autowired
	Information service;
	@Autowired
	UserInfoService services;
	
	@GetMapping(value= "/userAjax/{location}/{vehicleType}/{vehicleClassification}/{terms}", produces = MediaType.APPLICATION_JSON_VALUE)
	public InformationEntity getByAll(@PathVariable String location ,@PathVariable String vehicleType ,@PathVariable String vehicleClassification , @PathVariable String terms ) {
		
		
		InformationEntity entity = 		service.getByAll(location , vehicleType , vehicleClassification , terms);
	     System.out.println("entity : " + entity);
		System.out.println(entity);
		
		return entity;
	}
	
	
	@PostMapping(value= "/userAjax/{emailId}/{oneTimePassword}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public UserEntity sendOtp(@PathVariable String emailId ,  @PathVariable String oneTimePassword ,  UserEntity dto ) {
		
		UserEntity entity =  services.userSignIn(emailId, dto);
		System.out.println(entity);
		
		
		
		return entity;
	}
	
	
	
	
}
