package com.organiccropmastery.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organiccropmastery.model.GeneratedOTP;

public interface OTPDao extends JpaRepository<GeneratedOTP, String>{
	
}
