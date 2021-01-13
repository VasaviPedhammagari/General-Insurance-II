package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.RenewDetails;
import com.lti.dto.RenewStatus;
import com.lti.dto.Status.StatusType;
import com.lti.entity.MotorInsurance;
import com.lti.exception.UserServiceException;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class RenewController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/renew")
	public @ResponseBody RenewStatus renew(@RequestBody RenewDetails renewDetails) {
		try {
			MotorInsurance motorInsurance = userService.getDetails(renewDetails);
			
			RenewStatus status = new RenewStatus();
			status.setMotorInsurance(motorInsurance);
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("User Found");
			 
			return status;
		} catch(UserServiceException e) {
			return null;
		}
	}
}
