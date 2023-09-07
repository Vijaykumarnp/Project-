package com.xworkz.rentalapp.parkingdto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
@Data
public class AdminDTO  implements Serializable , Comparable<AdminDTO>{
  
	
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
