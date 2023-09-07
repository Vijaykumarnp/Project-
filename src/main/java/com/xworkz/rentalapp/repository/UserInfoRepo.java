package com.xworkz.rentalapp.repository;

import java.util.List;

import com.xworkz.rentalapp.entity.UserEntity;
import com.xworkz.rentalapp.entity.UserInfoEntity;
import com.xworkz.rentalapp.parkingdto.UserInfoDTO;

public interface UserInfoRepo {



	public void validateAndSave(UserInfoEntity entity);

	public void saveUserInformation(String location, String vehicleType , String vehicleClassification , String terms , int price , String discount, int totalAmount,UserInfoEntity entity);

	public List<UserInfoDTO> fectchAndUpdate(UserInfoEntity entity2 , String email);

	public UserInfoEntity update(UserInfoEntity enti );

public List<UserInfoEntity>	findByUserId(int id);

public UserInfoEntity findByUserIdForUpdate(int object);

public List<UserInfoDTO> deleteById(int parkingId);

	

	
	
	
}
