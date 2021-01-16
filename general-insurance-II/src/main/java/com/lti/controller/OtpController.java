package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Response;
import com.lti.entity.Otp;
import com.lti.exception.OtpMismatchException;
import com.lti.service.OtpService;

@RestController
@CrossOrigin
public class OtpController {
	@Autowired
	OtpService otpService;

	@PostMapping("/otp/generate")
	public Response generateOtp(@RequestBody String emailId) {
		
		Response response =new Response();
		int otpNumber=otpService.generateOtp(emailId);
		//response.setPayload(otpNumber);
		response.setMessage("otp generated");
		response.setStatusCode(200);
		return response;
	}
	
	@PostMapping("/otp/validate")
	public Response validateOtp(@RequestBody Otp otp) {
		
		Response response=new Response();
		try {
		boolean isValid=otpService.validateOtp(otp);
		if(isValid) {
			response.setMessage("valid OTP");
		}
		else {
			response.setMessage("In Valid OTP");
		}
		response.setPayload(isValid);
		response.setStatusCode(200);
		return response;
		}
		catch(OtpMismatchException e) {
			response.setMessage("Invalid otp");
			response.setStatusCode(200);
		}
		return response;
	}
}
