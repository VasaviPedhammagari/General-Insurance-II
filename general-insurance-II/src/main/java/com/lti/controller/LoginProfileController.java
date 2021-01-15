package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.MotorInsurance;
import com.lti.exception.UserServiceException;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class LoginProfileController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/insurancedetails")
	public @ResponseBody List<MotorInsurance> insuranceDetails(@RequestParam int userId){
		try {
			List<MotorInsurance> list = userService.getUserInsuranceDetails(userId);
			return list;
		}catch(UserServiceException e) {
			e.printStackTrace();
			return null;
		}
	}
}
