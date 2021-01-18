package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.RegisterStatus;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Address;
import com.lti.entity.User;
import com.lti.exception.UserServiceException;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class UpdateDetailsController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/update")
	public @ResponseBody RegisterStatus update(@RequestBody User user) {
		try {
		    userService.updateUserDetails(user);
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Update Successfull");
			status.setUser(user);
			return status;
		}
		catch(UserServiceException e) {
			e.printStackTrace();
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage("Update failed");
			return status;
		}
	}
	
}
