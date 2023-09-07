package com.xworkz.rentalapp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.xworkz.rentalapp.parkingdto.AdminDTO;

import lombok.Data;
@Data
@Table(name = "parking")
@NamedQuery(name = "findByEmail" , query = "select entity from AdminEntity entity where entity.emailId=:emailId ")
@Entity
public class AdminEntity implements  Serializable , Comparable<AdminDTO> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String adminName;
	
	private String emailId;
	
	private String password;
	
	
	private String createdBy;
	
	
	private String updatedBy;
	
	private String loginTime;

	@Override
	public int compareTo(AdminDTO o) {
		// TODO Auto-generated method stub
		return adminName.compareTo(o.getAdminName());
	}

	
}
