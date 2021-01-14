package com.lti.service;

import org.springframework.stereotype.Service;

import com.lti.exception.UserServiceException;

@Service
public class AdminServiceImpl implements AdminService {

	@Override
	public int adminlogin(String email, String password) {
		if(email.equals("rutvik@admin.com") && password.equals("admin123")){
				return 1;
		}
		else if	(email.equals("vasavi@admin.com") && password.equals("admin123")) {
				return 2;
		}
		else if	(email.equals("rajpriya@admin.com") && password.equals("admin123")) {
				return 3;
		}
		else if (email.equals("sripratha@admin.com") && password.equals("admin123")) {
				return 4;
		}
		else if	(email.equals("ijaz@admin.com") && password.equals("admin123")) {
				return 5;
		}
		else {
			throw new UserServiceException("Admin not found");
		}
			
		
	}

}
