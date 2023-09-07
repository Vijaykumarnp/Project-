package com.xworkz.rentalapp.parkingservice;

import java.util.List;

import javax.validation.Valid;

import com.xworkz.rentalapp.entity.UserEntity;
import com.xworkz.rentalapp.entity.UserInfoEntity;
import com.xworkz.rentalapp.parkingdto.UserDTO;
import com.xworkz.rentalapp.parkingdto.UserInfoDTO;

public interface UserInfoService {

	public void validateAndSave(UserInfoDTO dto , UserDTO dtos);

	
	boolean sendMail(String emailId);

	boolean otpSendMail(String emailId, UserEntity entity);




	UserEntity userSignIn(String emailId, UserEntity entity);


	public boolean login(String emailId, String oneTimePassword);


	public UserEntity findByUserEmail(String emailId);


	//public void saveUserInformation(String location, String vehicleType , String vehicleClassification , String terms , int price , String discount, int totalAmount,UserInfoEntity entity);
	public void onSave(@Valid UserInfoDTO dto, String emailId);


	public UserDTO fetchAndUpdate( String email);


	public UserInfoDTO updateByEmail(UserInfoDTO dto , String email );

	public List<UserInfoDTO> fetchUserParkingInfo(String email);

	
public	 UserInfoDTO fetchUserById(int id  );


public void updateById(UserInfoDTO dto);


public List<UserInfoDTO> vlidateAndDelete(int parkingId);
	
	
	

	}
