package com.organiccropmastery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import com.organiccropmastery.dao.PaymentDao;
import com.organiccropmastery.model.Payment;

import freemarker.core.ReturnInstruction.Return;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	@Override
	public boolean savePayment(Payment payment) {
		boolean result = false;
		Payment saveStatus = paymentDao.save(payment);
		System.out.println("Payment Response" + saveStatus.toString());
		return true;
}
}