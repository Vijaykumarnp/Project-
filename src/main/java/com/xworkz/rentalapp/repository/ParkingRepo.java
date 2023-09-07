package com.xworkz.rentalapp.repository;

import java.util.List;

import com.xworkz.rentalapp.entity.AdminEntity;
import com.xworkz.rentalapp.parkingdto.AdminDTO;


public interface ParkingRepo {

	AdminEntity login(String emailId );
	
	boolean updatedTime(AdminEntity entity);
	
}
