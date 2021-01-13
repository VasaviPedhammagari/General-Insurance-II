package com.lti.service;

import java.util.List;

import com.lti.entity.Estimate;
import com.lti.entity.User;
import com.lti.entity.Vehicle;
import com.lti.entity.VehicleModels;

public interface UserService {

	public int register(User user);

	public User login(String email, String password);
	
	public List<VehicleModels> fetchVehicles();

}
