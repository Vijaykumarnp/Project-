package com.xworkz.rentalapp.parkingcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.rentalapp.parkingdto.UserDTO;
import com.xworkz.rentalapp.parkingservice.UserService;
//@Controller
//@Component
//@RequestMapping("/")
//public class UserController {
//    @Autowired
//	UserService service;
//	
//	List<UserDTO> list =  new ArrayList<UserDTO>();
//	//@PostMapping("/user")
//	public String onSave( @Valid UserDTO dto , BindingResult bind , Model model) {
//		System.out.println("on save of user information");
//		
//		if(bind.hasErrors()) {
//			System.out.println("data is valid");
//			List<ObjectError> l = bind.getAllErrors();
//			l.forEach(ob -> System.err.println(ob.getDefaultMessage()));
//			model.addAttribute("errors", l);
//			model.addAttribute("dto", dto);
//			
//			
//			
//		}else {
//			
//			
//			
//			this.list.add(dto);
//			System.out.print("dto saved with details");
//			System.out.print("data is valid");
//			service.onSave(dto);
//		}
//		
//		
//		
//		
//		
//		
//		return "UserInfo.jsp";
//	}
//	
//	
//	
//	
//	
//	
//}
