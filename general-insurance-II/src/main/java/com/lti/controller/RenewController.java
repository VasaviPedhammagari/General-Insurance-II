package com.lti.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.RenewDetails;
import com.lti.dto.InsuranceStatus;
import com.lti.dto.Status;
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
	public @ResponseBody InsuranceStatus renew(@RequestBody RenewDetails renewDetails) {
		try {
			MotorInsurance motorInsurance = userService.getDetails(renewDetails);

			if (motorInsurance.getPlanExpiryDate().compareTo(LocalDate.now()) <= 0) {
				motorInsurance.getUser().getAddress().setUser(null);
				InsuranceStatus status = new InsuranceStatus();
				status.setMotorInsurance(motorInsurance);
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("User Found");

				return status;
			} else {
				InsuranceStatus status = new InsuranceStatus();
				status.setStatus(StatusType.FAILED);
				status.setMessage("Insurance is not expired");
				status.setMotorInsurance(motorInsurance);

				return status;
			}
		} catch (UserServiceException e) {
			InsuranceStatus status = new InsuranceStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());

			return status;
		}
	}
}
