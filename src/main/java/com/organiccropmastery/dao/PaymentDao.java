package com.organiccropmastery.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organiccropmastery.model.Payment;
@Repository
public interface PaymentDao extends JpaRepository<Payment,String>{
	

	
}
