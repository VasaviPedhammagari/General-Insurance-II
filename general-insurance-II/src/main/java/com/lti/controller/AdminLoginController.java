package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.Status.StatusType;
import com.lti.exception.UserServiceException;
import com.lti.service.AdminService;

@RestController
@CrossOrigin
public class AdminLoginController {
	
	@Autowired
	private AdminService adminService;
	

	@PostMapping("/adminlogin")
	public LoginStatus login(@RequestBody Login login) {
		 try {
			 int adminId = adminService.adminlogin(login.getEmail(), login.getPassword());
			 
			 LoginStatus status = new LoginStatus();
			 status.setStatus(StatusType.SUCCESS);
			 status.setMessage("Login Successful!!");
			 status.setUserId(adminId);
			 
			 return status;
			 
		 }catch(UserServiceException e) {
			 LoginStatus status = new LoginStatus();
			 status.setStatus(StatusType.FAILED);
			 status.setMessage(e.getMessage());
			 
			 return status;

		 }
	}
}
