
package com.lti.dto;

import com.lti.entity.MotorInsurance;
import com.lti.entity.Payment;

public class InsuranceStatus extends Status{

	private MotorInsurance motorInsurance;
	private Payment payment;

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public MotorInsurance getMotorInsurance() {
		return motorInsurance;
	}

	public void setMotorInsurance(MotorInsurance motorInsurance) {
		this.motorInsurance = motorInsurance;
	}

}
