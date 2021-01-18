package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="motor_insurance")
public class MotorInsurance {
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_policy_seq")
	@SequenceGenerator(sequenceName = "policy_seq", allocationSize = 1, name = "my_policy_seq")
	@Column(name = "policy_no")
	private int policyNumber;
	
	@Column(name = "plan_type")
	private String planType;
	
	@Column(name = "no_of_yrs")
	private int noOfYrs;
	
	@Column(name = "starting_date")
	private LocalDate planStartDate;
	
	@Column(name = "expiry_date")
	private LocalDate planExpiryDate;
	
	@Column(name = "total_claim_amount")
	private double totalClaimAmount;
	
	@Column(name = "balance_claim_amount")
	private double balanceClaimAmount;
	
	@Column(name = "insurance_premium")
	private double insurancePremium;
	
	@JsonIgnoreProperties(value = {"insurances"},allowSetters = true)
	@ManyToOne(cascade = { CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonIgnoreProperties(value = {"insurances"},allowSetters = true)
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	//@OneToOne(cascade = { CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;
	
    //@JsonIgnoreProperties(value = {"motorInsurance"},allowSetters = true)
	//@OneToMany(mappedBy = "motorInsurance",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	//private List<InsuranceClaim> insuranceClaim;

	public int getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(int policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public int getNoOfYrs() {
		return noOfYrs;
	}

	public void setNoOfYrs(int noOfYrs) {
		this.noOfYrs = noOfYrs;
	}

	public LocalDate getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(LocalDate planStartDate) {
		this.planStartDate = planStartDate;
	}

	public LocalDate getPlanExpiryDate() {
		return planExpiryDate;
	}

	public void setPlanExpiryDate(LocalDate planExpiryDate) {
		this.planExpiryDate = planExpiryDate;
	}

	public double getTotalClaimAmount() {
		return totalClaimAmount;
	}

	public void setTotalClaimAmount(double totalClaimAmount) {
		this.totalClaimAmount = totalClaimAmount;
	}

	public double getBalanceClaimAmount() {
		return balanceClaimAmount;
	}

	public void setBalanceClaimAmount(double balanceClaimAmount) {
		this.balanceClaimAmount = balanceClaimAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public double getInsurancePremium() {
		return insurancePremium;
	}

	public void setInsurancePremium(double insurancePremium) {
		this.insurancePremium = insurancePremium;
	}
	
}
