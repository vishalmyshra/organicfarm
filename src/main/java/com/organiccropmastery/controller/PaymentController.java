package com.organiccropmastery.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.organiccropmastery.model.Payment;
import com.organiccropmastery.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.springframework.core.env.Environment;

@RestController
@RequestMapping("/pay")
public class PaymentController {

	@Autowired
	private Environment environment;

	@Autowired
	private PaymentService paymentService;

	// added by vishal-mishra
	@PostMapping("/processPayment")
	@ResponseBody
	public String processPayment(@RequestBody Map<String, Object> data) throws Exception {
		System.out.println(data);
		int amountToCreateOrder = Integer.parseInt(data.get("amount").toString());
		System.out.println("amountToCreateOrder " + amountToCreateOrder);
		String key_id = environment.getProperty("key_id");
		String key_secret = environment.getProperty("key_secret");
		RazorpayClient razorpayClient = new RazorpayClient(key_id, key_secret);
		JSONObject options = new JSONObject();
		options.put("amount", amountToCreateOrder * 100);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		Order order = razorpayClient.orders.create(options);
		System.out.println(order);
		return order.toString();
	}

	@PostMapping("/savePaymentSatus")
	public String savePaymenStatus(@RequestBody Payment payment) throws Exception {
		//System.out.println(Integer.parseInt(payment.getAmount().toString()));
		Payment payment2 = new Payment();
		payment2.setOrderId(payment.getOrderId());
		payment2.setAttempts(payment.getAttempts());
		payment2.setAmount(payment.getAmount());
		payment2.setStatus(payment.getStatus());
		payment2.setOrderEntity(payment.getOrderEntity());

		paymentService.savePayment(payment2);
		return "Saved";
	}
}
