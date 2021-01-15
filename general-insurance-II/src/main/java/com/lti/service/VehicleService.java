package com.lti.service;

import java.util.List;

import com.lti.entity.Estimate;
import com.lti.entity.Vehicle;
import com.lti.entity.VehicleModels;

public interface VehicleService {

	public List<Estimate> getPremiumPlans(Vehicle vehicle);

	public Vehicle register(Vehicle vehicle);
	
	public VehicleModels addNewVehicle(VehicleModels vehicleModel);

	public List<VehicleModels> listAvailableVehicle();
}
