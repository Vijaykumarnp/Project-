package com.xworkz.rentalapp.parkingcontroller;

import org.springframework.stereotype.Component;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.rentalapp.parkingdto.AdminDTO;

@Component
@RequestMapping("/")
public class DefaultControllelr {

	
	public DefaultControllelr() {
		System.out.println("created" + this.getClass().getSimpleName());
	}
	@RequestMapping("/start")
	public String onStart(Model model) {
		System.out.println("inside onstart method");
		model.addAttribute("dto" ,new AdminDTO()); // setting the data for dto
		return "MainPage.jsp";
	}
	
	
	
}
