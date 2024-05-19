package com.organiccropmastery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organiccropmastery.dao.OTPDao;
import com.organiccropmastery.model.GeneratedOTP;

@Service
public class OTPServiceImpl implements OTPService {

	private final OTPDao otpDao;
	
	public OTPServiceImpl(OTPDao otpDao) {
		this.otpDao = otpDao;
	}

	@Override
	public String saveOTP(GeneratedOTP generatedOTP) {
		otpDao.save(generatedOTP);
		return "saved";
	}

}
