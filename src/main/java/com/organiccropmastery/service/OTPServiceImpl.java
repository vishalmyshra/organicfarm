package com.organiccropmastery.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.organiccropmastery.dao.OTPDao;
import com.organiccropmastery.model.GeneratedOTP;

public class OTPServiceImpl implements OTPService {
	@Autowired
	private OTPDao otpDao;

	@Override
	public String saveOTP(GeneratedOTP generatedOTP) {
		otpDao.save(generatedOTP);
		return "saved";
	}

}
