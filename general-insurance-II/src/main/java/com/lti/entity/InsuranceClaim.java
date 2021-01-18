package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "insurance_claim")
public class InsuranceClaim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_claim_seq")
	@SequenceGenerator(sequenceName = "claim_seq", allocationSize = 1, name = "my_claim_seq")
	@Column(name = "claim_no")
	private int claimNumber;
	
	@Column(name = "claim_reason")
	private String  claimReason;
	
	@Column(name = "claim_date")
	private LocalDate claimDate;
	
	@Column(name = "claim_status")
	private String claimStatus;
	
	@Column(name = "claim_amount")
	private double claimAmount;
	
	//@JsonIgnoreProperties(value = {"insuranceClaim"},allowSetters = true)
	@ManyToOne//(cascade = { CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "policy_no")
	private MotorInsurance motorInsurance;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public int getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(int claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getClaimReason() {
		return claimReason;
	}

	public void setClaimReason(String claimReason) {
		this.claimReason = claimReason;
	}

	public LocalDate getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public MotorInsurance getMotorInsurance() {
		return motorInsurance;
	}

	public void setMotorInsurance(MotorInsurance motorInsurance) {
		this.motorInsurance = motorInsurance;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}