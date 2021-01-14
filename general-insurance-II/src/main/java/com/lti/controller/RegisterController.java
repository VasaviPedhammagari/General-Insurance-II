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
			Address address = user.getAddress();
			System.out.println(address.getAddressLine());
		    user = userService.register(user);
			System.out.println(user.getEmail());
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successfull");
			status.setUser(user);
			return status;
		}
		catch(UserServiceException e) {
			e.printStackTrace();
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage("Registration failed");
			return status;
		}
	}
	
}
