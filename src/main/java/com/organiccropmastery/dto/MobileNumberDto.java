package com.organiccropmastery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MobileNumberDto {
	
	@JsonProperty("userMobileNumber")
	private String mobileNumber;

	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public MobileNumberDto(String mobileNumber) {
		super();
		this.mobileNumber = mobileNumber;
	}

	public MobileNumberDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MobileNumberDto [mobileNumber=" + mobileNumber + "]";
	}
	

}
