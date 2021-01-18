
package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.InsuranceStatus;
import com.lti.dto.Status.StatusType;
import com.lti.entity.MotorInsurance;
import com.lti.entity.Payment;
import com.lti.exception.UserServiceException;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class InsurancePlanController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/choose-plan")
	public @ResponseBody InsuranceStatus choosePlan(@RequestBody MotorInsurance motorInsurance) {
		try {
			Payment payment = userService.storeInsuranceDetails(motorInsurance);
			InsuranceStatus status = new InsuranceStatus();
			
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("success");
			status.setPayment(payment);
			
			return status;
		}catch(UserServiceException e) {
			
            InsuranceStatus status = new InsuranceStatus();
			
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
            
			return status;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
