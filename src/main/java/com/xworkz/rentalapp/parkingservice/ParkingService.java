package com.xworkz.rentalapp.parkingservice;

import java.util.List;

import com.xworkz.rentalapp.parkingdto.AdminDTO;


public interface ParkingService {

	boolean sendMail(String email);
	
 AdminDTO login(String email , String Password);
	
	
	
	
}
