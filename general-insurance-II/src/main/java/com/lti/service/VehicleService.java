package com.lti.service;

import java.util.List;

import com.lti.entity.Estimate;
import com.lti.entity.Vehicle;

public interface VehicleService {
	
	public List<Estimate> getPremiumPlans(Vehicle vehicle);
	
}
