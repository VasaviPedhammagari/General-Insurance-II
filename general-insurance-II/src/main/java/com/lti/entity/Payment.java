package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_payment_seq")
	@SequenceGenerator(sequenceName = "payment_seq", allocationSize = 1, name = "my_payment_seq")
	@Column(name = "payment_id")
	private int paymentId;
	
	@Column(name = "insurance_price")
	private int insurancePrice;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "payment_status")
	private String paymentStatus;
	
	@Column(name = "insurance_status")
	private String insuranceStatus;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@OneToOne(cascade = { CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "policy_no")
	private MotorInsurance motorInsurance;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getInsurancePrice() {
		return insurancePrice;
	}

	public void setInsurancePrice(int insurancePrice) {
		this.insurancePrice = insurancePrice;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getInsuranceStatus() {
		return insuranceStatus;
	}

	public void setInsuranceStatus(String insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public MotorInsurance getMotorInsurance() {
		return motorInsurance;
	}

	public void setMotorInsurance(MotorInsurance motorInsurance) {
		this.motorInsurance = motorInsurance;
	}
}
