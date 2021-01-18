
package com.lti.dto;

import com.lti.entity.Payment;

public class InsuranceStatus extends Status{

	private Payment payment;

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
}
