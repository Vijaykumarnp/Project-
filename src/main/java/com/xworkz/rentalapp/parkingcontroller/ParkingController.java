package com.xworkz.rentalapp.parkingcontroller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.rentalapp.parkingdto.AdminDTO;

import com.xworkz.rentalapp.parkingservice.ParkingService;
@RequestMapping("/")
@Component
public class ParkingController {

	@Autowired
	ParkingService service;
	
	public ParkingController() {
		System.out.println("create" + this.getClass().getSimpleName() );
	}
	
	@PostMapping("/login")
	public String adminLogin(@RequestParam("emailId")String emailId,@RequestParam("password") String password,Model model,AdminDTO dto,HttpServletRequest req) {
		System.out.println("Running login");
		
		AdminDTO dtos=service.login(emailId, password);
		if(dtos !=null) {
			System.out.println(dtos);
			//session forr take the login time
			HttpSession session=req.getSession(true);
			session.setAttribute("dto", dtos);
//			model.addAttribute("name", dtos.getAdminName());
//			model.addAttribute("time", dtos.getLoginTime());
			return "/Info.jsp";
		}
		System.out.println("Incorrect credential");
		model.addAttribute("error", "Invalid credential, please re-enter correct data");
		return "/Admin.jsp";
	}
	
}
