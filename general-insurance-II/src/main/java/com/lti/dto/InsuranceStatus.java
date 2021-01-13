
package com.lti.dto;

import com.lti.entity.MotorInsurance;

public class InsuranceStatus extends Status{

	private MotorInsurance motorInsurance;

	public MotorInsurance getMotorInsurance() {
		return motorInsurance;
	}

	public void setMotorInsurance(MotorInsurance motorInsurance) {
		this.motorInsurance = motorInsurance;
	}

}
