package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.Status.StatusType;
import com.lti.entity.User;
import com.lti.exception.UserServiceException;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class LoginController {
	
	@Autowired
	private UserService userService;
	

	@PostMapping("/login")
	public LoginStatus login(@RequestBody Login login) {
		 try {
			 User user = userService.login(login.getEmail(), login.getPassword());
			 
			 LoginStatus status = new LoginStatus();
			 status.setStatus(StatusType.SUCCESS);
			 status.setMessage("Login Successful!!");
			 status.setUserId(user.getUserId());
			 status.setUserName(user.getUserName());
			 
			 return status;
		 }catch(UserServiceException e) {
			 LoginStatus status = new LoginStatus();
			 status.setStatus(StatusType.FAILED);
			 status.setMessage(e.getMessage());
			 
			 return status;

		 }
	}
}
