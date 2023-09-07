package com.xworkz.rentalapp.parkingservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.rentalapp.entity.InformationEntity;
import com.xworkz.rentalapp.parkingdto.InformationDTO;
import com.xworkz.rentalapp.repository.InformationRepo;
@Service
public class InformationServiceIMPL implements Information {

	@Autowired
	InformationRepo repo;
	
	@Override
	public void onSave(InformationDTO dto) {
		
		InformationEntity entity =  new InformationEntity();
		BeanUtils.copyProperties(dto, entity);
		
		repo.onSave(entity);
		
		
	}

	@Override
	public List<InformationDTO> getByLocation(String location) {
	System.out.println("get by location service ");
	List<InformationEntity> entity = this.repo.getByLocation(location);
	List<InformationDTO> dto = entity.stream().map(ent ->{
		
     InformationDTO info = new InformationDTO();
	   BeanUtils.copyProperties(ent ,  info);
		return info;
	}).collect(Collectors.toList());
	
		return dto;
	}

	@Override
	public InformationEntity getByAll(String location, String vehicleType, String vehicleClassification, String terms) {
	
		InformationEntity entity =  this.repo.getByAll(location ,vehicleType , vehicleClassification , terms );
		System.out.println("entity :"+entity);
		
		
		return  entity ;
	}

}
