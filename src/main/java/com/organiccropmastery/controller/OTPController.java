package com.organiccropmastery.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.Console;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.organiccropmastery.dao.OTPDao;
import com.organiccropmastery.dao.UserDao;
import com.organiccropmastery.dto.MobileNumberDto;
import com.organiccropmastery.model.GeneratedOTP;
import com.organiccropmastery.model.User;
import com.organiccropmastery.resttemplate.AppConfigRestTemplate;
import com.organiccropmastery.service.OTPServiceImpl;
import com.organiccropmastery.service.UserServiceImp;

@RestController
@RequestMapping("/otp")
public class OTPController {

	boolean testOtpFeature = false;
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserServiceImp userServiceImp;
	
	private final OTPDao otpDao;
	
	public OTPController(OTPDao otpDao) {
		this.otpDao = otpDao;
	}

	String response = "";
	HttpStatus responsStatus;

	@PostMapping("/generateotp")
	public String generateOTP(@RequestBody MobileNumberDto mobileNumberDto) {
		try {
			OTPServiceImpl otpServiceImpl = new OTPServiceImpl(otpDao);
			Random random = new Random();
			
			
			
			GeneratedOTP generatedOTP = new GeneratedOTP();
			int randomNumber = random.nextInt(900000) + 100000; // Generates a random number between 100000 and 999999
			// Ensure the random number is exactly 6 digits long
			String formattedRandomNumber = String.format("%06d", randomNumber);

			
			String numberUser = mobileNumberDto.getMobileNumber();
			User user =  userServiceImp.loginUserMobile(numberUser);
			System.out.println("User mobile number ->" + numberUser);
			System.out.println("Generated OTP -> " + formattedRandomNumber);

			// Prepare request body
			String requestBody = "{\"route\":\"otp\",\"variables_values\":\"" + formattedRandomNumber + "\",\"numbers\":\"" + numberUser + "\"}";
			
			

			// Prepare request headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("authorization",
					"qanTxNrz28Lo9mC13MYVZuphASRJeBQKvcDjb0U5Hs7P4kEtIXs95U07BXi8gLV6xtjDOTcERm2qlGrZ");

			// Create HTTP entity with headers and body
			HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

			if (testOtpFeature) {
				// Call the third-party API endpoint
				ResponseEntity<String> responseEntity = restTemplate
						.postForEntity("https://www.fast2sms.com/dev/bulkV2", entity, String.class);
				response = responseEntity.getBody();
				responsStatus = responseEntity.getStatusCode();
				generatedOTP.setUsername(user.getUsername());
				generatedOTP.setMobileNumber(numberUser);
				generatedOTP.setOtp(formattedRandomNumber);
				generatedOTP.setStatus(responseEntity.getStatusCode().toString());
				otpServiceImpl.saveOTP(generatedOTP);
			} else {
				response = "Testing otp with dummy call.";
				responsStatus = HttpStatus.CONTINUE;
				generatedOTP.setUsername("dummy-user"+formattedRandomNumber + "");
				generatedOTP.setMobileNumber(numberUser);
				generatedOTP.setOtp(formattedRandomNumber);
				generatedOTP.setStatus("200");
				System.out.println(generatedOTP.toString());
				otpServiceImpl.saveOTP(generatedOTP);
			}
			System.out.println("Response from resttemplate " + response);
			System.out.println("ResponseStatus form resttemplate " + responsStatus.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
