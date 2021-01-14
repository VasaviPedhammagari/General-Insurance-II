package com.lti.dto;

import com.lti.entity.Vehicle;

public class VehicleStatus extends Status{
	
	private Vehicle vehicle;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
