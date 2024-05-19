package com.organiccropmastery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.organiccropmastery.dao.PaymentDao;
import com.organiccropmastery.model.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	@Override
	public boolean savePayment(Payment payment) {
		Payment saveStatus = paymentDao.save(payment);
		System.out.println("Payment Response" + saveStatus.toString());
		return true;
	}
}