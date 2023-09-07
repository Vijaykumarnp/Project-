package com.xworkz.rentalapp.parkingcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.rentalapp.entity.InformationEntity;
import com.xworkz.rentalapp.parkingdto.InformationDTO;
import com.xworkz.rentalapp.parkingservice.Information;

@Controller
@Component
@RequestMapping("/")
public class InformationController {

	@Autowired
	Information service;
	
	public InformationController() {
		System.out.println("created" +this.getClass().getSimpleName());
	}
	
	private List<InformationDTO> dtos = new ArrayList<InformationDTO>();
	@PostMapping("/saveInformation")
	public String onSave(@Valid InformationDTO dto , Model model , BindingResult bind) {
		
		
		
		if(bind.hasErrors()) {
			System.out.println("data is valid");
			List<ObjectError> l = bind.getAllErrors();
			l.forEach(ob -> System.err.println(ob.getDefaultMessage()));
			model.addAttribute("errors", l);
			model.addAttribute("dto", dto);
			
			
		}else{
			
			this.dtos.add(dto);
			System.out.print("dto saved with details");
			System.out.print("data is valid");
			service.onSave(dto);
		}
		return "Information.jsp";		
		
	}
	@GetMapping("/informationView")
	public String getByLocation( String location , Model model) {
		
		List<InformationDTO> list	=    service.getByLocation(location);
		model.addAttribute("list", list);
		return "InformationView.jsp";
	}
	

	
	
}
