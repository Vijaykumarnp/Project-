package com.xworkz.rentalapp.parkingservice;

import java.util.List;

import javax.validation.Valid;

import com.xworkz.rentalapp.entity.InformationEntity;
import com.xworkz.rentalapp.parkingdto.InformationDTO;

public interface Information {

    void onSave( InformationDTO dto);

	List<InformationDTO> getByLocation(String location);

	InformationEntity getByAll(String location, String vehicleType, String vehicleClassification, String terms);

	
	
	
}
