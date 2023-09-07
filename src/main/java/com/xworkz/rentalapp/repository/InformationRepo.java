package com.xworkz.rentalapp.repository;

import java.util.List;

import com.xworkz.rentalapp.entity.InformationEntity;
import com.xworkz.rentalapp.parkingdto.InformationDTO;

public interface InformationRepo {

	
	void onSave(InformationEntity entity);

	List<InformationEntity> getByLocation(String location);

	InformationEntity getByAll(String location, String vehicleType, String vehicleClassification, String terms);
	
}
