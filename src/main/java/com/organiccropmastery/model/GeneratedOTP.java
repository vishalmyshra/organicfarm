package com.organiccropmastery.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GeneratedOTP {
	@Id
	private String username;
	private String mobileNumber;
	private String status;
	private String message;
	private Timestamp otpTimeStamp;
	private String otp;
	public GeneratedOTP(String username, String mobileNumber, String status, String message, Timestamp otpTimeStamp,
			String otp) {
		super();
		this.username = username;
		this.mobileNumber = mobileNumber;
		this.status = status;
		this.message = message;
		this.otpTimeStamp = otpTimeStamp;
		this.otp = otp;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getOtpTimeStamp() {
		return otpTimeStamp;
	}
	public void setOtpTimeStamp(Timestamp otpTimeStamp) {
		this.otpTimeStamp = otpTimeStamp;
	}
	public GeneratedOTP(String username, String mobileNumber, String status, String message, Timestamp otpTimeStamp) {
		super();
		this.username = username;
		this.mobileNumber = mobileNumber;
		this.status = status;
		this.message = message;
		this.otpTimeStamp = otpTimeStamp;
	}
	public GeneratedOTP() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "GeneratedOTP [username=" + username + ", mobileNumber=" + mobileNumber + ", status=" + status
				+ ", message=" + message + ", otpTimeStamp=" + otpTimeStamp + ", otp=" + otp + "]";
	}

	
	

}
