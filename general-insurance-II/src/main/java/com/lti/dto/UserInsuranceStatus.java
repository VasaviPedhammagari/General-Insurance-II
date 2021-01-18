package com.lti.dto;

import java.util.List;

import com.lti.entity.MotorInsurance;
import com.lti.entity.Payment;
import com.lti.entity.Vehicle;

public class UserInsuranceStatus extends Status{

	private List<Vehicle> vehicles;
	private List<MotorInsurance> insurances;
	private List<Payment> payments;
	
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public List<MotorInsurance> getInsurances() {
		return insurances;
	}
	public void setInsurances(List<MotorInsurance> insurances) {
		this.insurances = insurances;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
}
