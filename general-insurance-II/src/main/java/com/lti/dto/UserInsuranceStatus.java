package com.lti.dto;

import java.util.List;

import com.lti.entity.MotorInsurance;
import com.lti.entity.Payment;
import com.lti.entity.Vehicle;

public class UserInsuranceStatus extends Status{

	private Vehicle vehicle;
	private MotorInsurance motorInsurance;
	private Payment payment;

	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public MotorInsurance getMotorInsurance() {
		return motorInsurance;
	}
	public void setMotorInsurance(MotorInsurance motorInsurance) {
		this.motorInsurance = motorInsurance;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
}
