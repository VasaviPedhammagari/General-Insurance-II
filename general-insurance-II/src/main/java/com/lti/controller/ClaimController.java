package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Claim;
import com.lti.dto.ClaimStatus;
import com.lti.dto.Status.StatusType;
import com.lti.entity.InsuranceClaim;
import com.lti.entity.MotorInsurance;
import com.lti.exception.UserServiceException;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class ClaimController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/claim")
	public @ResponseBody ClaimStatus claim(@RequestBody Claim claim) {
		try {
			InsuranceClaim insuranceClaim = userService.claim(claim.getPolicyNumber(), claim.getEmail(), claim.getPassword(), 
					claim.getClaimReason(), claim.getClaimAmount());
			//MotorInsurance motorInsurance = insuranceClaim.getMotorInsurance();
			ClaimStatus status = new ClaimStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Claim successful");
			status.setClaimNumber(insuranceClaim.getClaimNumber());
			status.setClaimAmount(insuranceClaim.getClaimAmount());
			return status;
		}
		catch(UserServiceException e) {
			e.printStackTrace();
			ClaimStatus status = new ClaimStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
}

