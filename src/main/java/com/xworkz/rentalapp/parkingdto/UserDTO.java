package com.xworkz.rentalapp.parkingdto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class UserDTO implements Serializable , Comparable<UserDTO>{

	
	private int user_Id;
	
	private String userName;
	
	private String emailId;
	
	private long contactNo;

	 private String oneTimePassword;
	 
	  private Date otpRequestedTime;
	
	@Override
	public int compareTo(UserDTO o) {
		// TODO Auto-generated method stub
		return userName.compareTo(o.getUserName());
	}
	
	
	
	
	
}
