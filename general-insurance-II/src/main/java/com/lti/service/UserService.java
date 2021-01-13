package com.lti.service;

import com.lti.entity.InsuranceClaim;
import com.lti.entity.User;

public interface UserService {

	public int register(User user);

	public User login(String email, String password);
	
	public InsuranceClaim claim(int policyNumber, String email, String password, String claimReason, double claimAmount);

}
