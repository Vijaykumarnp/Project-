package com.xworkz.rentalapp.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(name = "userdetails")
@NamedQueries({
		@NamedQuery(name = "findByUserEmail", query = "select entity from UserEntity as entity where entity.emailId=:email"),
		@NamedQuery(name = "findByUserEmailId", query = "select entity from UserEntity as entity where entity.emailId=:email"),
		@NamedQuery(name = "updateOTP", query = "update UserEntity entity set entity.oneTimePassword=:onetime where entity.emailId=:mail")})
@Setter
@Getter
public class UserEntity implements Serializable, Comparable<UserEntity> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_Id;

	private String userName;

	private String emailId;

	private long contactNo;

	private LocalTime otpExpiryTime;

	private String oneTimePassword;

	private String loginTime;

	@Override
	public int compareTo(UserEntity o) {
		// TODO Auto-generated method stub
		return userName.compareTo(o.getUserName());
	}

//	public boolean isOTPRequired() {
//		if (this.getOneTimePassword() == null) {
//			return false;
//		}
//
//		long currentTimeInMillis = System.currentTimeMillis();
//		long otpRequestedTimeInMillis = this.loginTime.getTime();
//
//		if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
//			// OTP expires
//			return false;
//		}
//
//		return true;
//	}

}
