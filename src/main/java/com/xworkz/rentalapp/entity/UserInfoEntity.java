package com.xworkz.rentalapp.entity;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.xworkz.rentalapp.parkingdto.UserInfoDTO;

import lombok.Data;
@Data
@Table(name ="userinfo")

@NamedQueries({
@NamedQuery(name = "fetch", query = "select entity from UserInfoEntity as entity where entity.user_Id=:id") ,
@NamedQuery(name = "fetchbyparking" , query = "select entity from UserInfoEntity as entity where entity.parkingId=:pid" ),
@NamedQuery(name= "updatebyid" , query = "update UserInfoEntity entity set  entity.location =:l , entity.vehicleType =:vt  , entity.vehicleClassification =:vc , entity.terms =:t , entity.price =:p ,entity.discount =:d  , entity.totalAmount =:ta , entity.imageName=:imag where entity.parkingId =:pid"),
@NamedQuery(name = "delete" , query = "delete from UserInfoEntity entity where entity.parkingId =:id") ,
})
@Entity
public class UserInfoEntity implements Serializable , Comparable<UserInfoEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int parkingId;
	
	private int user_Id;
	
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
	public int compareTo(UserInfoEntity o) {
	
		return location.compareTo(o.getLocation());
	}
	
	
	
}
