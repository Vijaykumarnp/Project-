package com.xworkz.rentalapp.repository;

import java.util.List;

import com.xworkz.rentalapp.entity.AdminEntity;
import com.xworkz.rentalapp.entity.UserEntity;
import com.xworkz.rentalapp.entity.UserInfoEntity;
import com.xworkz.rentalapp.parkingdto.UserDTO;

public interface UserRepo {

	
	public boolean onSave(UserEntity dto);

	UserEntity findByUserEmail(String userEmail);

	boolean userSignInUpdate(String oneTimePassword, String emailId);

	public UserEntity login(String emailId);

	public boolean updatedTime(UserEntity entity);

	public UserEntity fectchAndUpdate( String email);

	
	
}
