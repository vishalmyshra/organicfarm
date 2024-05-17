package com.organiccropmastery.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.organiccropmastery.dto.MobileNumberDto;
import com.organiccropmastery.resttemplate.AppConfigRestTemplate;

@RestController
@RequestMapping("/html/otp")
public class OTPController {
	
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/generateotp")
	public String generateOTP(@RequestBody MobileNumberDto mobileNumberDto) {
		String numberUser = mobileNumberDto.getMobileNumber();
		System.out.println(numberUser);
		
		  // Prepare request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization", "qanTxNrz28Lo9mC13MYVZuphASRJeBQKvcDjb0U5Hs7P4kEtIXs95U07BXi8gLV6xtjDOTcERm2qlGrZ");

        // Prepare request body
        String requestBody = "{\"route\":\"otp\",\"variables_values\":\"965088\",\"numbers\":\"9555512824\"}";

        // Create HTTP entity with headers and body
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Call the third-party API endpoint
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://www.fast2sms.com/dev/bulkV2", entity, String.class);
        String response = responseEntity.getBody();
        
        System.out.println(response);

        return response;
	}
}
