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
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public @ResponseBody RegisterStatus register(@RequestBody User user) {
		int id;
		try {
			id = userService.register(user);
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successfull");
			status.setRegisteredUserId(id);
			return status;
		}
		catch(UserServiceException e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage("Registration failed");
			return status;
		}
	}
	
}