package com.lti.dto;

import java.util.List;

import com.lti.entity.VehicleModels;

public class VehicleModelStatus extends Status{
	
	private VehicleModels vehicleModel;
	private List<VehicleModels> vehicleModels;

	public VehicleModels getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(VehicleModels vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public List<VehicleModels> getVehicleModels() {
		return vehicleModels;
	}

	public void setVehicleModels(List<VehicleModels> vehicleModels) {
		this.vehicleModels = vehicleModels;
	} 
	
	

}
