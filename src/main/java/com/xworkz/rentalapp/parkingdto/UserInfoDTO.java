package com.xworkz.rentalapp.parkingdto;

import java.io.Serializable;

import lombok.Data;
@Data
public class UserInfoDTO implements Serializable , Comparable<UserInfoDTO> {
	
	private int user_Id;
	
	private int parkingId;
	
	private String location;
	
	private String vehicleType;
	
	private String vehicleClassification;
	
	
	private String terms;
	
	private int price;
	
	private String discount;
	
	
	private int totalAmount;

private String contentType;
	
	private String imageName;
	
	private long fileSize;


	@Override
	public int compareTo(UserInfoDTO o) {
		// TODO Auto-generated method stub
		return location.compareTo(o.getLocation()) ;
	}
	
	
	
	
	
}
