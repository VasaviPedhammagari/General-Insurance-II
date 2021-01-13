package com.lti.service;

import com.lti.entity.InsuranceClaim;
import java.util.List;

import com.lti.entity.Estimate;
import com.lti.entity.User;
import com.lti.entity.Vehicle;
import com.lti.entity.VehicleModels;

public interface UserService {

	public int register(User user);

	public User login(String email, String password);
	
	public InsuranceClaim claim(int policyNumber, String email, String password, String claimReason, double claimAmount);
	
	public List<VehicleModels> fetchVehicles();

}
