package com.xworkz.rentalapp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data

@Table(name = "information")
@NamedQueries({
@NamedQuery(name = "getByLocation", query = "select entity from InformationEntity entity where entity.location=:location") ,
@NamedQuery(name = "getByAll", query = "select ent from InformationEntity as ent where ent.location=:location and ent.vehicleType=:vehicleType and ent.vehicleClassification =:vehicleClassification and ent.terms =:terms")

})
@Entity
public class InformationEntity implements Serializable  {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private	int id;
	@NotNull(message = "locaton should not be null")
	@NotEmpty(message = "locaton should not be empty")
private	String location;
	@NotNull(message = "vehicleType should not be null")
	@NotEmpty(message = "vehicleType should not be empty")
	private String vehicleType;
	@NotNull(message = "vehicleClassification should not be null")
	@NotEmpty(message = "vehicleClassification should not be empty")
	private String vehicleClassification;
	@NotNull(message = "terms should not be null")
	@NotEmpty(message = "terms should not be empty")
	private String terms;
	
	private  int price;
	
	private String discount;

	
	
}
